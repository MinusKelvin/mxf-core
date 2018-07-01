package net.minuskelvin.mxf.core

import org.khronos.webgl.WebGLRenderingContext

/**
 * Interface exposing functions for OpenGL ES 2.0
 */
actual class GL(private val ctx: WebGLRenderingContext) {
    actual fun clearColor(r: Float, g: Float, b: Float, a: Float) = ctx.clearColor(r, g, b, a)
    actual fun clear(mask: Int) = ctx.clear(mask)
}