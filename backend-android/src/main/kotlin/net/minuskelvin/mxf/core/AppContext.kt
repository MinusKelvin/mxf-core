package net.minuskelvin.mxf.core

actual class AppContext {
    actual val gl = GL()
    actual val input: Input
        get() = TODO("not implemented")

    actual fun exit() {}
}