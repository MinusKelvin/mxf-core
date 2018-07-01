package net.minuskelvin.mxf.core

import android.app.Activity
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.view.Window
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

abstract class MxfActivity : Activity(), GLSurfaceView.Renderer {
    private lateinit var view: GLSurfaceView
    private lateinit var instantiator: (AppContext) -> AppListener
    private val config = Configuration()
    
    private var listener: AppListener? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        instantiator = config.configure()
        
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN)
        
        view = GLSurfaceView(this)
        view.setEGLContextClientVersion(2)
        view.setRenderer(this)
        
        setContentView(view)
    }

    override fun onPause() {
        super.onPause()
        view.onPause()
    }

    override fun onResume() {
        super.onResume()
        view.onResume()
    }
    
    private var lastTime = System.nanoTime()
    override fun onDrawFrame(gl: GL10?) {
        val t = System.nanoTime()
        val delta = (t - lastTime) / 1.0e9
        lastTime = t
        listener?.render(delta.toFloat())
    }
    
    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        if (listener == null) {
            listener = instantiator(AppContext())
        }
        listener!!.resize(width, height)
    }
    
    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        
    }
    
    abstract fun Configuration.configure(): (AppContext) -> AppListener
}