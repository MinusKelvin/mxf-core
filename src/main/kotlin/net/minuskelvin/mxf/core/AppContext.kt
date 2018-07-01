package net.minuskelvin.mxf.core

expect class AppContext {
    val gl: GL
    val input: Input
    
    fun exit()
}