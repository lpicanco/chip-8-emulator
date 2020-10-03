package com.lpicanco.chip8

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class EmulatorTest {

    @Test
    fun shouldCopyFontsToTheMemory() {
        val emulator = Emulator(intArrayOf())
        val memoryFont = emulator.memory.sliceArray(CPU.FONT_ROM_START until CPU.FONT_ROM_START + FONT.size)
        assertTrue(FONT.contentEquals(memoryFont))
    }

    @Test
    fun shouldCopyRomDataToTheMemory() {
        val romData = (0x0..0xFF).toList().toIntArray()
        val emulator = Emulator(romData)

        val memoryRom = emulator.memory.sliceArray(CPU.PROGRAM_ROM_START until CPU.PROGRAM_ROM_START + romData.size)
        assertTrue(romData.contentEquals(memoryRom))
    }

    @Test
    fun shouldReturnTheKeyPad() {
        val emulator = Emulator(intArrayOf())
        assertTrue(emulator.keyPad.all { !it })
    }

    @Test
    fun shouldReturnTheScreen() {
        val emulator = Emulator(intArrayOf())
        assertTrue(emulator.screen.all { !it })
    }

    @Test
    fun shouldRunTheCPU() {
        val romData = intArrayOf(
            0x6B, // Sets VB
            0x42, // with 0x42
            0x6C, // Sets VC
            0x43, // with 0x43
            0x12, // Jumps to 0x200
            0x00
        )

        val emulator = Emulator(romData)
        runBlocking {
            GlobalScope.launch { emulator.run() }
            delay(100L)
            assertEquals(0x42, emulator.registers[0xB])
            assertEquals(0x43, emulator.registers[0xC])
        }
    }

    @Test
    fun shouldStopTheCPU() {
        val romData = intArrayOf(
            0x6B, // Sets VB
            0x42, // with 0x42
            0x6C, // Sets VC
            0x43, // with 0x43
            0x12, // Jumps to 0x200
            0x00
        )

        val emulator = Emulator(romData)
        runBlocking {
            GlobalScope.launch { emulator.run() }
            GlobalScope.launch { emulator.stop() }
            delay(100L)
            assertEquals(0x0, emulator.registers[0xC])
        }
    }
}
