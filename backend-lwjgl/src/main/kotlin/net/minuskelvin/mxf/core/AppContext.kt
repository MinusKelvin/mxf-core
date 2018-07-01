package net.minuskelvin.mxf.core

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengles.GLES
import org.lwjgl.system.MemoryUtil.NULL

actual class AppContext internal constructor(private val window: Long) {
    actual val gl = GL()
    actual val input = Input()
    
    actual fun exit() {
        glfwSetWindowShouldClose(window, true)
    }
}

fun launch(configurator: Configuration.() -> (AppContext) -> AppListener) {
    glfwInit()
    
    val config = Configuration()
    val instantiator = config.configurator()
    
    glfwWindowHint(GLFW_CONTEXT_CREATION_API, GLFW_EGL_CONTEXT_API)
    glfwWindowHint(GLFW_CLIENT_API, GLFW_OPENGL_ES_API)
    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 2)
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 0)
    
    val window = glfwCreateWindow((config.mode as WindowMode.Windowed).width, (config.mode as WindowMode.Windowed).height, config.title, NULL, NULL)
    glfwMakeContextCurrent(window)
    GLES.createCapabilities()
    
    val listener = instantiator(AppContext(window))
    
    var lastTime = glfwGetTime()
    while (!glfwWindowShouldClose(window)) {
        glfwPollEvents()
        
        val t = glfwGetTime()
        listener.render((t - lastTime).toFloat())
        lastTime = t
        
        glfwSwapBuffers(window)
    }
}