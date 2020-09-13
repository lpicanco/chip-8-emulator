package com.lpicanco.chip8

import kotlinx.coroutines.delay

class Emulator(romData: IntArray) {
    private val cpu: CPU
    val memory: Memory
        get() = cpu.memory
    val registers: Registers
        get() = cpu.registers
    val screen: Screen
        get() = cpu.screen

    init {
        val memory = Memory(CPU.MEMORY_SIZE)
        romData.copyInto(memory, CPU.PROGRAM_ROM_START)
        FONT.copyInto(memory, CPU.FONT_ROM_START)
        cpu = CPU(memory = memory)
    }

    suspend fun run() {
        while (true) {
            cpu.tick()
            delay(1000L / cpu.clock)
        }
    }
}
