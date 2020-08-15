package com.lpicanco.chip8

import org.junit.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

internal class CPUTest {
    private lateinit var cpu: CPU

    @BeforeTest
    fun init() {
        cpu = CPU()
    }

    @Test
    fun `should set VX to NN`() {
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x6B // Sets VB
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0x42 // with 0x42

        cpu.tick()

        assertEquals(0x42, cpu.registers[0xB])
    }

    @Test
    fun `should reset`() {
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x6B // Sets VB
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0x42 // with 0x42

        cpu.tick()
        cpu.reset()

        // TODO: Modify the stack and other registers

        assertEquals(CPU(), cpu)
    }
}
