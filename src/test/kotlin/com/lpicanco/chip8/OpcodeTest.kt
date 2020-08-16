package com.lpicanco.chip8

import kotlin.test.Test
import kotlin.test.assertEquals

internal class OpcodeTest {

    @Test
    fun `should return the opcode instruction`() {
        val opcode = Opcode(0x6A42)
        assertEquals(0x6000, opcode.instruction)
    }

    @Test
    fun `should return the opcode vx`() {
        val opcode = Opcode(0x6A42)
        assertEquals(0xA, opcode.vx)
    }

    @Test
    fun `should return the opcode vy`() {
        val opcode = Opcode(0x6A42)
        assertEquals(0x4, opcode.vy)
    }

    @Test
    fun `should return the opcode nnData`() {
        val opcode = Opcode(0x6A42)
        assertEquals(0x42, opcode.nnData)
    }

    @Test
    fun `should return the opcode nnnData`() {
        val opcode = Opcode(0x1A42)
        assertEquals(0xA42, opcode.nnnData)
    }
}
