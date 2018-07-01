package net.minuskelvin.mxf.core

interface AppListener {
    fun render(delta: Float)
    
    fun resize(width: Int, height: Int)
    
    fun pause()
    
    fun resume()
    
    fun dispose()
}