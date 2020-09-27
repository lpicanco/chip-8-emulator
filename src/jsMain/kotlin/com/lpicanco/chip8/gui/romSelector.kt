package com.lpicanco.chip8.gui

import com.lpicanco.chip8.Emulator
import kotlinx.browser.document
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import kotlinx.html.dom.append
import kotlinx.html.option
import org.w3c.dom.HTMLSelectElement

val romSelector = document.getElementById("rom_selector") as HTMLSelectElement

fun renderRomSelector() {
    romSelector.onchange = {
        emulator.stop()
        loadRom(SampleRoms.list.getValue(romSelector.value))
    }
    SampleRoms.list.forEach { rom ->
        romSelector.append {
            option {
                +rom.key
                value = rom.key
            }
        }
    }
}

fun loadRom(data: IntArray) {
    emulator = Emulator(data)
    GlobalScope.promise {
        emulator.run()
    }
}
