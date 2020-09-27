package com.lpicanco.chip8.gui

import com.lpicanco.chip8.Emulator
lateinit var emulator: Emulator

fun main() {
    renderRomSelector()

    // load a sample rom
    loadRom(SampleRoms.list.values.first())

    renderScreen()
    renderKeyboard()
}
