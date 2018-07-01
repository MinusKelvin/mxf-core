package net.minuskelvin.mxf.core

import android.opengl.GLES20.glClear
import android.opengl.GLES20.glClearColor

/**
 * Interface exposing functions for OpenGL ES 2.0
 */
actual class GL {
    actual fun clearColor(r: Float, g: Float, b: Float, a: Float) = glClearColor(r, g, b, a)
    actual fun clear(mask: Int) = glClear(mask)
}