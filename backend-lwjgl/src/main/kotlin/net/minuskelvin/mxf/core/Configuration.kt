package net.minuskelvin.mxf.core

class Configuration internal constructor() {
    var mode: WindowMode = WindowMode.Windowed(800, 600)
    var title = "MXF Game"
}

sealed class WindowMode {
    class Windowed(
            val width: Int,
            val height: Int,
            var resizable: Boolean = true
    ) : WindowMode()
}