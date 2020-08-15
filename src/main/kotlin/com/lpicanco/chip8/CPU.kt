package com.lpicanco.chip8

class CPU(val memory: Memory = Memory(MEMORY_SIZE)) {
    val screen: Screen = Screen(SCREEN_PIXELS)
    val registers: Registers = Registers(REGISTER_COUNT)
    val stack: Stack = Stack(STACK_SIZE)
    private var i: Register = I_REGISTER_START
    private var pc: Register = PROGRAM_ROM_START
    private var sp: Pointer = STACK_POINTER_START

    fun reset() {
        memory.fill(0)
        registers.fill(0)
        stack.fill(0)
        pc = PROGRAM_ROM_START
        sp = STACK_POINTER_START
        i = I_REGISTER_START
    }

    fun tick() {
        val opCode = fetchOpcode()
        executeOpCode(opCode)
    }

    // One opcode have 2 bytes.
    // We need to shift the first byte 8 bits do the left and make a OR operation with the second byte.
    // e.g.: 0xA2 << 8 = 0xA200 -> 0xA200 | 0xF5 = 0xA2F5
    private fun fetchOpcode() = Opcode(
        memory[pc].shl(8) or memory[pc + 1]
    )

    private fun executeOpCode(opcode: Opcode) {
        // Instruction is in the first 4 bits.
        when (opcode.instruction) {
            OPCODE_CLEAR_SCREEN -> clearScreen()
            OPCODE_SET_VX_TO_NN -> setVxToNn(opcode)
            else -> TODO("Instruction ${opcode.instruction.toString(16)} not implemented.")
        }

        // The PC should be incremented by 2.
        pc += 2
    }

    private fun setVxToNn(opcode: Opcode) {
        registers[opcode.vx] = opcode.nnData
    }

    private fun clearScreen() {
        screen.fill(false)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CPU

        if (!memory.contentEquals(other.memory)) return false
        if (!screen.contentEquals(other.screen)) return false
        if (!registers.contentEquals(other.registers)) return false
        if (!stack.contentEquals(other.stack)) return false
        if (i != other.i) return false
        if (pc != other.pc) return false
        if (sp != other.sp) return false

        return true
    }

    override fun hashCode(): Int {
        var result = memory.contentHashCode()
        result = 31 * result + screen.contentHashCode()
        result = 31 * result + registers.contentHashCode()
        result = 31 * result + stack.contentHashCode()
        result = 31 * result + i
        result = 31 * result + pc
        result = 31 * result + sp
        return result
    }

    companion object {
        const val MEMORY_SIZE = 4096
        const val SCREEN_PIXELS = 2048
        const val STACK_SIZE = 16
        const val REGISTER_COUNT = 16
        const val PROGRAM_ROM_START: Register = 0x200
        const val STACK_POINTER_START: Register = 0x0
        const val I_REGISTER_START: Register = 0x0
        private const val OPCODE_CLEAR_SCREEN: Instruction = 0xA000
        private const val OPCODE_SET_VX_TO_NN: Instruction = 0x6000
    }
}
