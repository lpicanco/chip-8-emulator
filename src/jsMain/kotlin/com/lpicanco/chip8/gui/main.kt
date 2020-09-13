package com.lpicanco.chip8.gui

import kotlinx.browser.document
import kotlinx.html.dom.append
import kotlinx.html.id
import kotlinx.html.js.canvas
import kotlinx.html.js.h1
import org.w3c.dom.HTMLCanvasElement

fun main() {
    document.getElementById("app")
        ?.also { it.innerHTML = "" }
        ?.append {
            h1 { +"CHIP-8 emulator" }
            canvas {
                id = "displayCanvas"
                width = "640"
                height = "320"
            }
        }

    renderToCanvas(document.getElementById("displayCanvas") as HTMLCanvasElement)
}
