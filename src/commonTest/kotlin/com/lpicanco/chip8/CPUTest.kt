package com.lpicanco.chip8

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertNotEquals

internal class CPUTest {
    private lateinit var cpu: CPU

    @BeforeTest
    fun init() {
        cpu = CPU()
        FONT.copyInto(cpu.memory, CPU.FONT_ROM_START)
    }

    @Test
    fun shouldJumpToAddressNNN() {
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
    fun shouldJumpToAddressNNNPlusV0() {
        val jumpAddress = 0xF15
        cpu.registers[0x0] = 2
        cpu.memory[jumpAddress] = 0x6B // Sets VB
        cpu.memory[jumpAddress + 1] = 0x42 // with 0x42

        cpu.memory[CPU.PROGRAM_ROM_START] = 0xBF // Jump to F12 + 2(V0)
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0x13

        // Jump to jumpAddress
        cpu.tick()

        // Execute the code at the jumpAddress
        cpu.tick()

        assertEquals(0x42, cpu.registers[0xB])
    }

    @Test
    fun shouldSetVXToRandomANDNN() {
        cpu.memory[CPU.PROGRAM_ROM_START] = 0xCA // Set VA to random
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xFF // AND 0xFF

        cpu.tick()

        cpu.memory[CPU.PROGRAM_ROM_START + 2] = 0xCB // Set VB to random
        cpu.memory[CPU.PROGRAM_ROM_START + 3] = 0xFF // AND 0xFF

        cpu.tick()

        assertNotEquals(cpu.registers[0xA], cpu.registers[0xB])
    }

    @Test
    fun shouldCallSubroutineAtAddressNNN() {
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
    fun shouldSkipNextInstructionIfVXEqualsNN() {
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
    fun shouldNotSkipNextInstructionIfVXNotEqualsNN() {
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
    fun shouldSkipNextInstructionIfVXNotEqualsNN() {
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
    fun shouldNotSkipNextInstructionIfVXEqualsNN() {
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
    fun shouldSkipNextInstructionIfVXEqualsVY() {
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
    fun shouldNotSkipNextInstructionIfVXNotEqualsVY() {
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
    fun shouldSkipNextInstructionIfVXNotEqualsVY() {
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
    fun shouldNotSkipNextInstructionIfVXEqualsVY() {
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
    fun shouldReturnFromSubroutine() {
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
    fun shouldSetVXToNN() {
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x6B // Sets VB
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0x42 // with 0x42

        cpu.tick()

        assertEquals(0x42, cpu.registers[0xB])
    }

    @Test
    fun shouldAddNNToVX() {
        cpu.registers[0xA] = 0x1A

        cpu.memory[CPU.PROGRAM_ROM_START] = 0x7A // Adds to VA
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0x42 // 0x42

        cpu.tick()

        assertEquals(0x5C, cpu.registers[0xA])
    }

    @Test
    fun shouldAddNNToVXWithoutCarryFlag() {
        cpu.registers[0xA] = 0xDD

        cpu.memory[CPU.PROGRAM_ROM_START] = 0x7A // Adds to VA
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xFC // 0xFC

        cpu.tick()

        assertEquals(0xD9, cpu.registers[0xA])
    }

    @Test
    fun shouldAddVXToIWithoutCarryFlag() {
        cpu.registers[0xA] = 0xFDFF
        cpu.memory[CPU.PROGRAM_ROM_START] = 0xFA // Adds VA to I
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0x1E

        cpu.tick()
        assertEquals(0xFDFF, cpu.i)

        cpu.memory[CPU.PROGRAM_ROM_START + 2] = 0xFA // Adds VA to I again
        cpu.memory[CPU.PROGRAM_ROM_START + 3] = 0x1E

        cpu.tick()

        assertEquals(0xFBFE, cpu.i)
    }

    @Test
    fun shouldSetVXToVY() {
        cpu.registers[0xC] = 0x42
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x8B // Sets VB
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xC0 // with VC

        cpu.tick()

        assertEquals(0x42, cpu.registers[0xB])
    }

    @Test
    fun shouldSetVXToVXORVY() {
        cpu.registers[0xB] = 0b01000010
        cpu.registers[0xC] = 0b10011000
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x8B // Sets VB with VB
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xC1 // OR VC

        cpu.tick()

        assertEquals(0b11011010, cpu.registers[0xB])
    }

    @Test
    fun shouldSetVXToVXANDVY() {
        cpu.registers[0xB] = 0b01010010
        cpu.registers[0xC] = 0b10011010
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x8B // Sets VB with VB
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xC2 // AND VC

        cpu.tick()

        assertEquals(0b00010010, cpu.registers[0xB])
    }

    @Test
    fun shouldSetVXToVXXORVY() {
        cpu.registers[0xB] = 0b01010010
        cpu.registers[0xC] = 0b10011010
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x8B // Sets VB with VB
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xC3 // XOR VC

        cpu.tick()

        assertEquals(0b11001000, cpu.registers[0xB])
    }

    @Test
    fun shouldAddVYToVX() {
        cpu.registers[0xB] = 0xA3
        cpu.registers[0xC] = 0x3D
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x8B // Adds VC to VB
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xC4

        cpu.tick()

        assertEquals(0xE0, cpu.registers[0xB])
        assertEquals(0x0, cpu.registers[0xF])
    }

    @Test
    fun shouldAddVYToVXAndUpdateVFWithCarryFlag() {
        cpu.registers[0xB] = 0xA3
        cpu.registers[0xC] = 0xFD
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x8B // Adds VC to VB
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xC4

        cpu.tick()

        assertEquals(0xA0, cpu.registers[0xB])
        assertEquals(0x1, cpu.registers[0xF])
    }

    @Test
    fun shouldSubtractVYFromVXAndUpdateVFWithCarryFlag() {
        cpu.registers[0xB] = 0xF3
        cpu.registers[0xC] = 0xAD
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x8B // VB = VB - VC
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xC5

        cpu.tick()

        assertEquals(0x46, cpu.registers[0xB])
        assertEquals(0x1, cpu.registers[0xF])
    }

    @Test
    fun shouldSubtractVYFromVX() {
        cpu.registers[0xB] = 0xAD
        cpu.registers[0xC] = 0xF3
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x8B // VB = VB - VC
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xC5

        cpu.tick()

        assertEquals(0xBA, cpu.registers[0xB])
        assertEquals(0x0, cpu.registers[0xF])
    }

    @Test
    fun shouldShiftRightVX() {
        cpu.registers[0xB] = 0x77
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x8B // VB = VB >> 1
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xC6

        cpu.tick()

        assertEquals(0x3B, cpu.registers[0xB])
        assertEquals(0x1, cpu.registers[0xF])
    }

    @Test
    fun shouldShiftLeftVX() {
        cpu.registers[0xB] = 0xFF
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x8B // VB = VB << 1
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xCE

        cpu.tick()

        assertEquals(0xFE, cpu.registers[0xB])
        assertEquals(0x1, cpu.registers[0xF])
    }

    @Test
    fun shouldSubtractVXFromVYAndUpdateVFWithCarryFlag() {
        cpu.registers[0xB] = 0xAD
        cpu.registers[0xC] = 0xF3
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x8B // VB = VC - VB
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xC7

        cpu.tick()

        assertEquals(0x46, cpu.registers[0xB])
        assertEquals(0x1, cpu.registers[0xF])
    }

    @Test
    fun shouldSubtractVXFromVY() {
        cpu.registers[0xB] = 0xF3
        cpu.registers[0xC] = 0xAD
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x8B // VB = VC - VB
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xC7

        cpu.tick()

        assertEquals(0xBA, cpu.registers[0xB])
        assertEquals(0x0, cpu.registers[0xF])
    }

    @Test
    fun shouldSetIToNNN() {
        cpu.memory[CPU.PROGRAM_ROM_START] = 0xA7 // Sets I
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0x65 // with 0x765

        cpu.tick()

        assertEquals(0x765, cpu.i)
    }

    @Test
    fun shouldSetAndGetDelayTimer() {
        val delayTimer = 0x42
        cpu.clock = 59
        cpu.registers[0xB] = delayTimer

        cpu.memory[CPU.PROGRAM_ROM_START] = 0xFB // Sets delay timer to VB
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0x15

        cpu.tick()
        cpu.registers[0xB] = 0

        cpu.memory[CPU.PROGRAM_ROM_START + 2] = 0xFB // Sets VB to delay timer
        cpu.memory[CPU.PROGRAM_ROM_START + 3] = 0x07
        cpu.tick()

        assertEquals(0x42, cpu.registers[0xB])
    }

    @Test
    fun shouldSetTheSoundTimerToVX() {
        val soundTimer = 0x42
        cpu.registers[0xB] = soundTimer

        cpu.memory[CPU.PROGRAM_ROM_START] = 0xFB // Sets sound timer to VB
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0x18

        cpu.tick()

        assertEquals(0x42, cpu.soundTimer)
    }

    @Test
    fun shouldBlockUntilKeyPressedAndStoreTheKeyToVX() {
        val key = 0xA
        cpu.memory[CPU.PROGRAM_ROM_START] = 0xFB // Sets key pressed to VB
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0x0A

        cpu.tick()
        cpu.tick()
        assertEquals(0x0, cpu.registers[0xB])

        cpu.keyPad[key] = true
        cpu.tick()
        assertEquals(key, cpu.registers[0xB])
    }

    @Test
    fun shouldSkipNextInstructionIfKeyStoredAtVXIsPressed() {
        val key = 0x9
        cpu.registers[0xA] = key

        cpu.memory[CPU.PROGRAM_ROM_START] = 0xEA // Skip if key at VA is pressed
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0x9E
        cpu.memory[CPU.PROGRAM_ROM_START + 4] = 0x6B // Sets VB
        cpu.memory[CPU.PROGRAM_ROM_START + 5] = 0x42 // with 0x42

        cpu.keyPad[key] = true

        cpu.tick()
        cpu.tick()
        assertEquals(0x42, cpu.registers[0xB])
    }

    @Test
    fun shouldNotSkipNextInstructionIfKeyStoredAtVXIsNotPressed() {
        val key = 0x9
        cpu.registers[0xA] = key

        cpu.memory[CPU.PROGRAM_ROM_START] = 0xEA // Skip if key at VA is pressed
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0x9E
        cpu.memory[CPU.PROGRAM_ROM_START + 2] = 0x6B // Sets VB
        cpu.memory[CPU.PROGRAM_ROM_START + 3] = 0x42 // with 0x42

        cpu.tick()
        cpu.tick()
        assertEquals(0x42, cpu.registers[0xB])
    }

    @Test
    fun shouldSkipNextInstructionIfKeyStoredAtVXIsNotPressed() {
        val key = 0x9
        cpu.registers[0xA] = key

        cpu.memory[CPU.PROGRAM_ROM_START] = 0xEA // Skip if key at VA is not pressed
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xA1
        cpu.memory[CPU.PROGRAM_ROM_START + 4] = 0x6B // Sets VB
        cpu.memory[CPU.PROGRAM_ROM_START + 5] = 0x42 // with 0x42

        cpu.tick()
        cpu.tick()
        assertEquals(0x42, cpu.registers[0xB])
    }

    @Test
    fun shouldNotSkipNextInstructionIfKeyStoredAtVXIsPressed() {
        val key = 0x9
        cpu.registers[0xA] = key

        cpu.memory[CPU.PROGRAM_ROM_START] = 0xEA // Skip if key at VA is not pressed
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0xA1
        cpu.memory[CPU.PROGRAM_ROM_START + 2] = 0x6B // Sets VB
        cpu.memory[CPU.PROGRAM_ROM_START + 3] = 0x42 // with 0x42

        cpu.keyPad[key] = true

        cpu.tick()
        cpu.tick()
        assertEquals(0x42, cpu.registers[0xB])
    }

    @Test
    fun shouldSetIToTheLocationOfTheSpriteForTheCharacterInVX() {
        cpu.registers[0xB] = 0xE
        cpu.memory[CPU.PROGRAM_ROM_START] = 0xFB // Sets I to sprite at VB
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0x29

        cpu.tick()

        assertEquals(CPU.FONT_ROM_START + 0xE, cpu.i)
    }

    @Test
    fun shouldFillV0UntilVXWithI() {
        val address = 0x600
        cpu.memory[address] = 0x6
        cpu.memory[address + 1] = 0x7
        cpu.memory[address + 2] = 0x8
        cpu.memory[address + 3] = 0x9

        cpu.memory[CPU.PROGRAM_ROM_START] = 0xA6 // Sets I with 0x600
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0x00
        cpu.memory[CPU.PROGRAM_ROM_START + 2] = 0xF3 // Fill V0 until V3 with I
        cpu.memory[CPU.PROGRAM_ROM_START + 3] = 0x65
        cpu.tick()
        cpu.tick()

        assertEquals(0x6, cpu.registers[0x0])
        assertEquals(0x7, cpu.registers[0x1])
        assertEquals(0x8, cpu.registers[0x2])
        assertEquals(0x9, cpu.registers[0x3])
    }

    @Test
    fun shouldSetIAddressToV0UntilVX() {
        val address = 0x600
        cpu.registers[0x0] = 0x6
        cpu.registers[0x1] = 0x7
        cpu.registers[0x2] = 0x8
        cpu.registers[0x3] = 0x9

        cpu.memory[CPU.PROGRAM_ROM_START] = 0xA6 // Sets I with 0x600
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0x00
        cpu.memory[CPU.PROGRAM_ROM_START + 2] = 0xF3 // Set with V0 until V3
        cpu.memory[CPU.PROGRAM_ROM_START + 3] = 0x55
        cpu.tick()
        cpu.tick()

        assertEquals(0x6, cpu.memory[address])
        assertEquals(0x7, cpu.memory[address + 0x1])
        assertEquals(0x8, cpu.memory[address + 0x2])
        assertEquals(0x9, cpu.memory[address + 0x3])
    }

    @Test
    fun shouldSetIWithBCDOfVX() {
        val address = 0x600
        cpu.registers[0xB] = 0x420
        cpu.memory[CPU.PROGRAM_ROM_START] = 0xA6 // Sets I with 0x600
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0x00
        cpu.memory[CPU.PROGRAM_ROM_START + 2] = 0xFB // Sets I with BCD of VB
        cpu.memory[CPU.PROGRAM_ROM_START + 3] = 0x33
        cpu.tick()
        cpu.tick()

        assertEquals(0xA, cpu.memory[address])
        assertEquals(0x5, cpu.memory[address + 0x1])
        assertEquals(0x6, cpu.memory[address + 0x2])
    }

    @Test
    fun shouldThrowExceptionIfTheOpcodeIsInvalid() {
        cpu.memory[CPU.PROGRAM_ROM_START] = 0x0
        cpu.memory[CPU.PROGRAM_ROM_START + 1] = 0x0

        assertFails { cpu.tick() }
    }
}
