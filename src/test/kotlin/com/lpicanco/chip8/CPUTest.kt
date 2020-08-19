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
        assertEquals(CPU.PROGRAM_ROM_START + 2, cpu.stack[0])

        // Execute the code at the subRoutineAddress
        cpu.tick()
        assertEquals(0x42, cpu.registers[0xB])
    }

    @Test
    fun `should skip next instruction if VX equals NN`() {
        cpu.registers[0xA] = 0x55

        cpu.memory[CPU.PROGRAM_ROM_START] = 0x3A // Skip if VA
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0x55 // == 0x55

        cpu.memory[CPU.PROGRAM_ROM_START + 4] = 0x6B // Sets VB
        cpu.memory[CPU.PROGRAM_ROM_START + 5] = 0x42 // with 0x42

        cpu.tick()
        cpu.tick()
        assertEquals(0x42, cpu.registers[0xB])
    }

    @Test
    fun `should not skip next instruction if VX not equals NN`() {
        cpu.registers[0xA] = 0x99

        cpu.memory[CPU.PROGRAM_ROM_START] = 0x3A // Skip if VA
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0x55 // == 0x55
        cpu.memory[CPU.PROGRAM_ROM_START + 2] = 0x6B // Sets VB
        cpu.memory[CPU.PROGRAM_ROM_START + 3] = 0x42 // with 0x42

        cpu.tick()
        cpu.tick()
        assertEquals(0x42, cpu.registers[0xB])
    }

    @Test
    fun `should skip next instruction if VX not equals NN`() {
        cpu.registers[0xA] = 0x99

        cpu.memory[CPU.PROGRAM_ROM_START] = 0x4A // Skip if VA
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0x56 // != 0x55

        cpu.memory[CPU.PROGRAM_ROM_START + 4] = 0x6B // Sets VB
        cpu.memory[CPU.PROGRAM_ROM_START + 5] = 0x42 // with 0x42

        cpu.tick()
        cpu.tick()
        assertEquals(0x42, cpu.registers[0xB])
    }

    @Test
    fun `should not skip next instruction if VX equals NN`() {
        cpu.registers[0xA] = 0x56

        cpu.memory[CPU.PROGRAM_ROM_START] = 0x4A // Skip if VA
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0x56 // != 0x55
        cpu.memory[CPU.PROGRAM_ROM_START + 2] = 0x6B // Sets VB
        cpu.memory[CPU.PROGRAM_ROM_START + 3] = 0x42 // with 0x42

        cpu.tick()
        cpu.tick()
        assertEquals(0x42, cpu.registers[0xB])
    }

    @Test
    fun `should skip next instruction if VX equals VY`() {
        cpu.registers[0xA] = 0x55
        cpu.registers[0xB] = 0x55

        cpu.memory[CPU.PROGRAM_ROM_START] = 0x5A // Skip if VA
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xB0 // == VB

        cpu.memory[CPU.PROGRAM_ROM_START + 4] = 0x6B // Sets VB
        cpu.memory[CPU.PROGRAM_ROM_START + 5] = 0x42 // with 0x42

        cpu.tick()
        cpu.tick()
        assertEquals(0x42, cpu.registers[0xB])
    }

    @Test
    fun `should not skip next instruction if VX not equals VY`() {
        cpu.registers[0xA] = 0x55
        cpu.registers[0xB] = 0x66

        cpu.memory[CPU.PROGRAM_ROM_START] = 0x5A // Skip if VA
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xB0 // == VB
        cpu.memory[CPU.PROGRAM_ROM_START + 2] = 0x6B // Sets VB
        cpu.memory[CPU.PROGRAM_ROM_START + 3] = 0x42 // with 0x42

        cpu.tick()
        cpu.tick()
        assertEquals(0x42, cpu.registers[0xB])
    }

    @Test
    fun `should skip next instruction if VX not equals VY`() {
        cpu.registers[0xA] = 0x55
        cpu.registers[0xB] = 0x66

        cpu.memory[CPU.PROGRAM_ROM_START] = 0x9A // Skip if VA
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xB0 // != VB

        cpu.memory[CPU.PROGRAM_ROM_START + 4] = 0x6B // Sets VB
        cpu.memory[CPU.PROGRAM_ROM_START + 5] = 0x42 // with 0x42

        cpu.tick()
        cpu.tick()
        assertEquals(0x42, cpu.registers[0xB])
    }

    @Test
    fun `should not skip next instruction if VX equals VY`() {
        cpu.registers[0xA] = 0x55
        cpu.registers[0xB] = 0x55

        cpu.memory[CPU.PROGRAM_ROM_START] = 0x9A // Skip if VA
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xB0 // != VB
        cpu.memory[CPU.PROGRAM_ROM_START + 2] = 0x6B // Sets VB
        cpu.memory[CPU.PROGRAM_ROM_START + 3] = 0x42 // with 0x42

        cpu.tick()
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
    fun `should set VX to VY`() {
        cpu.registers[0xC] = 0x42
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x8B // Sets VB
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xC0 // with VC

        cpu.tick()

        assertEquals(0x42, cpu.registers[0xB])
    }

    @Test
    fun `should set VX to VX OR VY`() {
        cpu.registers[0xB] = 0b01000010
        cpu.registers[0xC] = 0b10011000
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x8B // Sets VB with VB
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xC1 // OR VC

        cpu.tick()

        assertEquals(0b11011010, cpu.registers[0xB])
    }

    @Test
    fun `should set VX to VX AND VY`() {
        cpu.registers[0xB] = 0b01010010
        cpu.registers[0xC] = 0b10011010
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x8B // Sets VB with VB
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xC2 // AND VC

        cpu.tick()

        assertEquals(0b00010010, cpu.registers[0xB])
    }

    @Test
    fun `should set VX to VX XOR VY`() {
        cpu.registers[0xB] = 0b01010010
        cpu.registers[0xC] = 0b10011010
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x8B // Sets VB with VB
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xC3 // XOR VC

        cpu.tick()

        assertEquals(0b11001000, cpu.registers[0xB])
    }

    @Test
    fun `should add VY to VX`() {
        cpu.registers[0xB] = 0xA3
        cpu.registers[0xC] = 0x3D
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x8B // Adds VC to VB
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xC4

        cpu.tick()

        assertEquals(0xE0, cpu.registers[0xB])
        assertEquals(0x0, cpu.registers[0xF])
    }

    @Test
    fun `should add VY to VX and update VF with carry bit`() {
        cpu.registers[0xB] = 0xA3
        cpu.registers[0xC] = 0xFD
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x8B // Adds VC to VB
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xC4

        cpu.tick()

        assertEquals(0xA0, cpu.registers[0xB])
        assertEquals(0x1, cpu.registers[0xF])
    }

    @Test
    fun `should subtract VY from VX and update VF with carry bit`() {
        cpu.registers[0xB] = 0xF3
        cpu.registers[0xC] = 0xAD
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x8B // VB = VB - VC
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xC5

        cpu.tick()

        assertEquals(0x46, cpu.registers[0xB])
        assertEquals(0x1, cpu.registers[0xF])
    }

    @Test
    fun `should subtract VY from VX`() {
        cpu.registers[0xB] = 0xAD
        cpu.registers[0xC] = 0xF3
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x8B // VB = VB - VC
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xC5

        cpu.tick()

        assertEquals(0xBA, cpu.registers[0xB])
        assertEquals(0x0, cpu.registers[0xF])
    }

    @Test
    fun `should shift right VX`() {
        cpu.registers[0xB] = 0x77
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x8B // VB = VB >> 1
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xC6

        cpu.tick()

        assertEquals(0x3B, cpu.registers[0xB])
        assertEquals(0x1, cpu.registers[0xF])
    }

    @Test
    fun `should shift left VX`() {
        cpu.registers[0xB] = 0xFF
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x8B // VB = VB << 1
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xCE

        cpu.tick()

        assertEquals(0xFE, cpu.registers[0xB])
        assertEquals(0x1, cpu.registers[0xF])
    }

    @Test
    fun `should subtract VX from VY and update VF with carry bit`() {
        cpu.registers[0xB] = 0xAD
        cpu.registers[0xC] = 0xF3
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x8B // VB = VC - VB
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xC7

        cpu.tick()

        assertEquals(0x46, cpu.registers[0xB])
        assertEquals(0x1, cpu.registers[0xF])
    }

    @Test
    fun `should subtract VX from VY`() {
        cpu.registers[0xB] = 0xF3
        cpu.registers[0xC] = 0xAD
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x8B // VB = VC - VB
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xC7

        cpu.tick()

        assertEquals(0xBA, cpu.registers[0xB])
        assertEquals(0x0, cpu.registers[0xF])
    }

    @Test
    fun `should set I to NNN`() {
        cpu.memory[CPU.PROGRAM_ROM_START] = 0xA7 // Sets I
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0x65 // with 0x765

        cpu.tick()

        assertEquals(0x765, cpu.i)
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
