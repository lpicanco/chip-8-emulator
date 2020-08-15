package com.lpicanco.chip8

inline class Opcode(private val value: Int) {

    // 0x6B42 -> 0x6
    val instruction: Instruction
        get() = value and 0xF000

    // 0x6B42 -> 0xB
    val vx: Register
        get() = (value and 0x0F00).shr(8)

    // 0x6B42 -> 0x42
    val nnData: Int
        get() = value and 0x00FF

    override fun toString() = value.toString(16)
}
