package com.lpicanco.chip8.gui

import kotlinx.browser.document
import org.w3c.dom.HTMLLIElement
import org.w3c.dom.asList

fun renderKeyboard() {
    document.getElementById("app")
    document.getElementsByClassName("key").asList().forEach {
        val key = it as HTMLLIElement
        val keySymbol = key.innerText.toInt(16)
        key.onmousedown = {
            emulator.keyPad[keySymbol] = true
            console.log("down $keySymbol")
        }
        key.onmouseup = {
            emulator.keyPad[keySymbol] = false
            console.log("up $keySymbol")
        }
    }
}
