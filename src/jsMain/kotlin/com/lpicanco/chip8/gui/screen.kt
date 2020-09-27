package com.lpicanco.chip8.gui

import com.lpicanco.chip8.CPU
import kotlinx.browser.document
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.promise
import kotlinx.html.dom.append
import kotlinx.html.id
import kotlinx.html.js.canvas
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement

fun renderScreen() {
    val screenCanvas = createScreen()

    GlobalScope.promise {
        while (true) {
            renderToCanvas(screenCanvas)
            delay(1000 / 30)
        }
    }
}

private fun createScreen(): HTMLCanvasElement {
    document.getElementById("screen")
        ?.append {
            canvas {

                id = "displayCanvas"
                width = "640"
                height = "320"
            }
        }
    return document.getElementById("displayCanvas") as HTMLCanvasElement
}

private fun renderToCanvas(canvas: HTMLCanvasElement) {
    val width = 10.0
    val height = 10.0

    val ctx = canvas.getContext("2d") as CanvasRenderingContext2D
    ctx.clearRect(0.0, 0.0, ctx.canvas.width.toDouble(), ctx.canvas.height.toDouble())

    for (i in emulator.screen.indices) {
        val x = (i % CPU.SCREEN_WIDTH) * width
        val y = i.div(CPU.SCREEN_WIDTH) * height

        if (emulator.screen[i]) {
            ctx.fillRect(x, y, width, height)
        }
    }
}
