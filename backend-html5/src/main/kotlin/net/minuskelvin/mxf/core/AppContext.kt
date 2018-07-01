package net.minuskelvin.mxf.core

import org.khronos.webgl.WebGLRenderingContext
import org.w3c.dom.HTMLCanvasElement
import kotlin.browser.document
import kotlin.browser.window

actual class AppContext internal constructor(actual val gl: GL) {
    actual val input = Input()
    
    internal var stopped = false
    
    actual fun exit() {
        stopped = true
    }
}

fun launch(configurator: Configuration.() -> (AppContext) -> AppListener) {
    val config = Configuration()
    val instantiator = config.configurator()
    
    val canvas = document.getElementById(config.canvasId) as? HTMLCanvasElement
            ?: throw IllegalArgumentException("'${config.canvasId}' is not a canvas")
    
    val gl = GL(canvas.getContext("webgl") as? WebGLRenderingContext
            ?: throw IllegalStateException("Could not create webgl context"))
    
    val context = AppContext(gl)
    
    val listener = instantiator(context)

    if (config.fitScreen) {
        canvas.width = window.innerWidth
        canvas.height = window.innerHeight
        listener.resize(window.innerWidth, window.innerHeight)
        
        window.addEventListener("resize", {
            canvas.width = window.innerWidth
            canvas.height = window.innerHeight
            listener.resize(window.innerWidth, window.innerHeight)
        })
    } else
        listener.resize(canvas.width, canvas.height)
    
    var lastTime = window.performance.now()
    fun callback(now: Double) {
        val delta = (now - lastTime) / 1000
        listener.render(delta.toFloat())
        lastTime = now

        if (context.stopped)
            window.close()
        else
            window.requestAnimationFrame(::callback)
    }
    window.requestAnimationFrame(::callback)
}