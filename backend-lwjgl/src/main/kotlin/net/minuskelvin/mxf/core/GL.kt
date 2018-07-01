package net.minuskelvin.mxf.core

import org.lwjgl.opengles.GLES20.glClear
import org.lwjgl.opengles.GLES20.glClearColor

actual class GL internal constructor() {
    actual fun clearColor(r: Float, g: Float, b: Float, a: Float) = glClearColor(r, g, b, a)
    actual fun clear(mask: Int) = glClear(mask)
}