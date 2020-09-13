package com.lpicanco.chip8.gui

import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement

fun renderToCanvas(canvas: HTMLCanvasElement) {
    val ctx = canvas.getContext("2d") as CanvasRenderingContext2D
    ctx.clearRect(0.0, 0.0, ctx.canvas.width.toDouble(), ctx.canvas.height.toDouble())

    ctx.fillStyle = "red"
    ctx.fillRect(10.0, 10.0, 20.0, 40.0)

    ctx.font = "48px sans-serif"
    ctx.fillText("Display", 31.0, 50.0)

    ctx.globalAlpha = 0.3
    ctx.fillRect(190.0, 10.0, 20.0, 40.0)

    ctx.strokeStyle = "blue"
    ctx.strokeRect(0.0, 0.0, ctx.canvas.width.toDouble(), ctx.canvas.height.toDouble())
}
