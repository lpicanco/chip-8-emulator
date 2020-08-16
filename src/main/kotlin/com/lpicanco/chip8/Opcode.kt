package com.lpicanco.chip8

inline class Opcode(val value: Int) {

    // 0x6B42 -> 0x6
    val instruction: Instruction
        get() = value and 0xF000

    // 0x6B42 -> 0xB
    val vx: Register
        get() = (value and 0x0F00).shr(8)

    // 0x5AB0 -> 0xB
    val vy: Register
        get() = (value and 0x00F0).shr(4)

    // 0x6B42 -> 0x42
    val nnData: Int
        get() = value and 0x00FF

    // 0x1B42 -> 0xB42
    val nnnData: Int
        get() = value and 0x0FFF

    override fun toString() = value.toString(16)
}
