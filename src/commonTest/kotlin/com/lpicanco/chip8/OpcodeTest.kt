package com.lpicanco.chip8

import kotlin.test.Test
import kotlin.test.assertEquals

internal class OpcodeTest {

    @Test
    fun shouldReturnTheOpcodeInstruction() {
        val opcode = Opcode(0x6A42)
        assertEquals(0x6000, opcode.instruction)
    }

    @Test
    fun shouldReturnTheOpcodeVx() {
        val opcode = Opcode(0x6A42)
        assertEquals(0xA, opcode.vx)
    }

    @Test
    fun shouldReturnTheOpcodeVy() {
        val opcode = Opcode(0x6A42)
        assertEquals(0x4, opcode.vy)
    }

    @Test
    fun shouldReturnTheOpcodeNData() {
        val opcode = Opcode(0x6A42)
        assertEquals(0x2, opcode.nData)
    }

    @Test
    fun shouldReturnTheOpcodeNnData() {
        val opcode = Opcode(0x6A42)
        assertEquals(0x42, opcode.nnData)
    }

    @Test
    fun shouldReturnTheOpcodeNnnData() {
        val opcode = Opcode(0x1A42)
        assertEquals(0xA42, opcode.nnnData)
    }
}
