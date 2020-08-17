package com.lpicanco.chip8

class CPU(val memory: Memory = Memory(MEMORY_SIZE)) {
    val screen: Screen = Screen(SCREEN_PIXELS)
    val registers: Registers = Registers(REGISTER_COUNT)
    val stack: Stack = Stack(STACK_SIZE)
    var i: Register = I_REGISTER_START
        private set
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
        incPC()
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
            OPCODE_CLEAR_SCREEN_OR_RETURN -> clearScreenOrReturn(opcode)
            OPCODE_JUMP_TO_ADDRESS_NNN -> jumpToAddressNnn(opcode)
            OPCODE_CALL_SUBROUTINE_AT_NNN -> callSubroutineAtNnn(opcode)
            OPCODE_SKIP_NEXT_IF_VX_EQUALS_NN -> skipNextIfVxEqualsNn(opcode)
            OPCODE_SKIP_NEXT_IF_VX_NOT_EQUALS_NN -> skipNextIfVxNotEqualsNn(opcode)
            OPCODE_SKIP_NEXT_IF_VX_EQUALS_VY -> skipNextIfVxEqualsVy(opcode)
            OPCODE_SET_VX_TO_NN -> setVxToNn(opcode)
            OPCODE_ADD_NN_TO_VX -> addNnToVx(opcode)
            OPCODE_SET_VX_TO_VY -> setVxToVy(opcode)
            OPCODE_SKIP_NEXT_IF_VX_NOT_EQUALS_VY -> skipNextIfVxNotEqualsVy(opcode)
            OPCODE_SET_I_TO_NNN -> setIToNnn(opcode)
            OPCODE_DRAW_N_AT_VX_VY -> drawNAtVxVy(opcode)
            else -> TODO("Instruction ${opcode.instruction.toString(16)} not implemented.")
        }
    }

    private fun clearScreenOrReturn(opcode: Opcode) = when (opcode.value) {
        OPCODE_CLEAR_SCREEN -> clearScreen()
        OPCODE_RETURN_FROM_SUBROUTINE -> returnFromSubRoutine()
        else -> TODO("Opcode ${opcode.value.toString(16)} not implemented.")
    }

    private fun clearScreen() {
        screen.fill(false)
    }

    private fun returnFromSubRoutine() {
        pc = stack[--sp]
    }

    // Jumps to address NNN.
    private fun jumpToAddressNnn(opcode: Opcode) {
        pc = opcode.nnnData
    }

    // Calls subroutine at NNN.
    private fun callSubroutineAtNnn(opcode: Opcode) {
        stack[sp++] = pc
        pc = opcode.nnnData
    }

    // Skips the next instruction if VX equals NN.
    private fun skipNextIfVxEqualsNn(opcode: Opcode) {
        if (registers[opcode.vx] == opcode.nnData) {
            incPC()
        }
    }

    // Skips the next instruction if VX doesn't equal NN.
    private fun skipNextIfVxNotEqualsNn(opcode: Opcode) {
        if (registers[opcode.vx] != opcode.nnData) {
            incPC()
        }
    }

    // Skips the next instruction if VX equals VY.
    private fun skipNextIfVxEqualsVy(opcode: Opcode) {
        if (registers[opcode.vx] == registers[opcode.vy]) {
            incPC()
        }
    }

    // Skips the next instruction if VX not equals VY.
    private fun skipNextIfVxNotEqualsVy(opcode: Opcode) {
        if (registers[opcode.vx] != registers[opcode.vy]) {
            incPC()
        }
    }

    // Sets VX to NN.
    private fun setVxToNn(opcode: Opcode) {
        registers[opcode.vx] = opcode.nnData
    }

    // Adds NN to VX. (Carry flag is not changed)
    private fun addNnToVx(opcode: Opcode) {
        registers[opcode.vx] = (registers[opcode.vx] + opcode.nnData).toUByte().toInt()
    }

    // Sets VX to the value of VY.
    private fun setVxToVy(opcode: Opcode) {
        registers[opcode.vx] = registers[opcode.vy]
    }

    // Sets I to the address NNN.
    private fun setIToNnn(opcode: Opcode) {
        println("Setting I to [${opcode.nnnData.toString(16)}]($opcode)")
        i = opcode.nnnData
    }

    // Draws a sprite at coordinate (VX, VY) that has a width of 8 pixels and a height of N pixels.
    // Each row of 8 pixels is read as bit-coded starting from memory location I;
    // I value doesn't change after the execution of this instruction.
    // VF is set to 1 if any screen pixels are flipped from set to unset when the sprite is drawn, and to 0 if that doesn't happen
    private fun drawNAtVxVy(opcode: Opcode) {
        // X = VX % SCREEN_WIDTH and Y = VY % SCREEN_HEIGHT
        val x = registers[opcode.vx] % SCREEN_WIDTH
        val y = registers[opcode.vy] % SCREEN_HEIGHT

        // Sets VF to 0
        registers[VF_INDEX] = 0

        (0 until opcode.nData).forEach { row ->
            val currentSpriteByte = memory[i + row]

            (0 until 8).forEach { col ->
                val screenCoordinates = ((y + row) * SCREEN_WIDTH) + x + col
                if (screenCoordinates >= SCREEN_PIXELS) {
                    return
                }

                val currentPixel = screen[screenCoordinates]

                if (currentSpriteByte and (0x80 shr col) > 0) {
                    if (currentPixel) {
                        registers[VF_INDEX] = 1
                    }
                    screen[screenCoordinates] = !screen[screenCoordinates]
                }
            }
        }
    }

    private fun incPC() {
        pc += 2
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
        const val SCREEN_HEIGHT = 32
        const val SCREEN_WIDTH = 64
        const val SCREEN_PIXELS = SCREEN_HEIGHT * SCREEN_WIDTH
        const val STACK_SIZE = 16
        const val REGISTER_COUNT = 16
        const val VF_INDEX = 0xF
        const val PROGRAM_ROM_START: Register = 0x200
        const val STACK_POINTER_START: Register = 0x0
        const val I_REGISTER_START: Register = 0x0

        private const val OPCODE_CLEAR_SCREEN_OR_RETURN: Instruction = 0x0000
        private const val OPCODE_CLEAR_SCREEN: Instruction = 0x00E0
        private const val OPCODE_RETURN_FROM_SUBROUTINE: Instruction = 0x00EE
        private const val OPCODE_JUMP_TO_ADDRESS_NNN: Instruction = 0x1000
        private const val OPCODE_CALL_SUBROUTINE_AT_NNN: Instruction = 0x2000
        private const val OPCODE_SKIP_NEXT_IF_VX_EQUALS_NN: Instruction = 0x3000
        private const val OPCODE_SKIP_NEXT_IF_VX_NOT_EQUALS_NN: Instruction = 0x4000
        private const val OPCODE_SKIP_NEXT_IF_VX_EQUALS_VY: Instruction = 0x5000
        private const val OPCODE_SET_VX_TO_NN: Instruction = 0x6000
        private const val OPCODE_ADD_NN_TO_VX: Instruction = 0x7000
        private const val OPCODE_SET_VX_TO_VY: Instruction = 0x8000
        private const val OPCODE_SKIP_NEXT_IF_VX_NOT_EQUALS_VY: Instruction = 0x9000
        private const val OPCODE_SET_I_TO_NNN: Instruction = 0xA000
        private const val OPCODE_DRAW_N_AT_VX_VY: Instruction = 0xD000
    }
}
