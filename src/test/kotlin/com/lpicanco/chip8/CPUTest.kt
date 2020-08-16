package com.lpicanco.chip8

import org.junit.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

@ExperimentalUnsignedTypes
internal class CPUTest {
    private lateinit var cpu: CPU

    @BeforeTest
    fun init() {
        cpu = CPU()
    }

    @Test
    fun `should jump to address NNN`() {
        val jumpAddress = 0xF15
        cpu.memory[jumpAddress] = 0x6B // Sets VB
        cpu.memory[jumpAddress + 1] = 0x42 // with 0x42

        cpu.memory[CPU.PROGRAM_ROM_START] = 0x1F // Jump to F15
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0x15

        // Jump to jumpAddress
        cpu.tick()

        // Execute the code at the jumpAddress
        cpu.tick()

        assertEquals(0x42, cpu.registers[0xB])
    }

    @Test
    fun `should call subroutine at address NNN`() {
        val subRoutineAddress = 0xF15
        cpu.memory[subRoutineAddress] = 0x6B // Sets VB
        cpu.memory[subRoutineAddress + 1] = 0x42 // with 0x42

        cpu.memory[CPU.PROGRAM_ROM_START] = 0x2F // Call subroutine at F15
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0x15

        // Call subRoutineAddress
        cpu.tick()
        assertEquals(CPU.PROGRAM_ROM_START, cpu.stack[0])

        // Execute the code at the subRoutineAddress
        cpu.tick()
        assertEquals(0x42, cpu.registers[0xB])
    }

    @Test
    fun `should return from a subroutine`() {
        val subRoutineAddress = 0xF15
        // Return from the subroutine.
        cpu.memory[subRoutineAddress] = 0x00 // Return from subroutine
        cpu.memory[subRoutineAddress + 1] = 0xEE

        // Call the subroutine.
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x2F // Call subroutine at F15
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0x15

        // Set VB with 0x42
        cpu.memory[CPU.PROGRAM_ROM_START + 2] = 0x6B // Sets VB
        cpu.memory[CPU.PROGRAM_ROM_START + 3] = 0x55 // with 0x42

        // Call subRoutineAddress
        cpu.tick()
        // Return from subRoutineAddress
        cpu.tick()
        // Execute the next code(set VB) after return.
        cpu.tick()

        assertEquals(0x55, cpu.registers[0xB])
    }

    @Test
    fun `should set VX to NN`() {
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x6B // Sets VB
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0x42 // with 0x42

        cpu.tick()

        assertEquals(0x42, cpu.registers[0xB])
    }

    @Test
    fun `should add NN to VX`() {
        cpu.registers[0xA] = 0x1A

        cpu.memory[CPU.PROGRAM_ROM_START] = 0x7A // Adds to VA
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0x42 // 0x42

        cpu.tick()

        assertEquals(0x5C, cpu.registers[0xA])
    }

    @Test
    fun `should add NN to VX without carry flag`() {
        cpu.registers[0xA] = 0xDD

        cpu.memory[CPU.PROGRAM_ROM_START] = 0x7A // Adds to VA
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xFC // 0x42

        cpu.tick()

        assertEquals(0xD9, cpu.registers[0xA])
    }

    @Test
    fun `should reset the CPU`() {
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x6B // Sets VB
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0x42 // with 0x42

        cpu.tick()
        cpu.reset()

        // TODO: Modify the stack and other registers

        assertEquals(CPU(), cpu)
    }
}
