package com.lpicanco.chip8

import kotlin.random.Random

class CPU(val memory: Memory = Memory(MEMORY_SIZE)) {
    val screen: Screen = Screen(SCREEN_PIXELS)
    val registers: Registers = Registers(REGISTER_COUNT)
    val stack: Stack = Stack(STACK_SIZE)
    var i: Register = I_REGISTER_START
        private set
    var clock = DEFAULT_CLOCK
    private var pc: Register = PROGRAM_ROM_START
    private var sp: Register = STACK_POINTER_START
    private var delayTimer = 0
    private var tickCount = 0

    fun tick() {
        val opCode = fetchOpcode()
        incPC()
        executeOpCode(opCode)

        if (tickCount++ > (clock / 60)) {
            if (delayTimer > 0) {
                delayTimer--
            }
            tickCount = 0
        }
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
            OPCODE_VX_VY_OPERATION -> vxVyOperation(opcode)
            OPCODE_SKIP_NEXT_IF_VX_NOT_EQUALS_VY -> skipNextIfVxNotEqualsVy(opcode)
            OPCODE_SET_I_TO_NNN -> setIToNnn(opcode)
            OPCODE_JUMP_TO_ADDRESS_NNN_PLUS_V0 -> jumpToAddressNnnPlusV0(opcode)
            OPCODE_SET_VX_TO_RANDOM_AND_NN -> setVxToRandomAndNn(opcode)
            OPCODE_DRAW_N_AT_VX_VY -> drawNAtVxVy(opcode)
            OPCODE_KEY_OPERATION -> keyOperation(opcode)
            OPCODE_TIMER_KEY_MEM_OPERATION -> timerKeyMemOperation(opcode)
            else -> opcodeNotImplementedError(opcode)
        }
    }

    private fun clearScreenOrReturn(opcode: Opcode) = when (opcode.value) {
        OPCODE_CLEAR_SCREEN -> clearScreen()
        OPCODE_RETURN_FROM_SUBROUTINE -> returnFromSubRoutine()
        else -> opcodeNotImplementedError(opcode)
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

    // Jumps to the address NNN plus V0.
    private fun jumpToAddressNnnPlusV0(opcode: Opcode) {
        pc = opcode.nnnData + registers[0x0]
    }

    // Sets VX to the result of a bitwise and operation on a random number (Typically: 0 to 255) and NN.
    private fun setVxToRandomAndNn(opcode: Opcode) {
        registers[opcode.vx] = Random.nextInt(0xFF) and opcode.nnData
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

    private fun vxVyOperation(opcode: Opcode) = when (opcode.nData) {
        OPCODE_N_SET_VX_TO_VY -> setVxToVy(opcode)
        OPCODE_N_SET_VX_TO_VX_OR_VY -> setVxToVxOrVy(opcode)
        OPCODE_N_SET_VX_TO_VX_AND_VY -> setVxToVxAndVy(opcode)
        OPCODE_N_SET_VX_TO_VX_XOR_VY -> setVxToVxXorVy(opcode)
        OPCODE_N_ADD_VY_TO_VX -> addVyToVx(opcode)
        OPCODE_N_SUBTRACT_VY_FROM_VX -> subtractVyFromVx(opcode)
        OPCODE_N_SHIFT_RIGHT_VX_FROM_VY -> shiftRightVx(opcode)
        OPCODE_N_SUBTRACT_VX_FROM_VY -> subtractVxFromVy(opcode)
        OPCODE_N_SHIFT_LEFT_VX_FROM_VY -> shiftLeftVx(opcode)
        else -> opcodeNotImplementedError(opcode)
    }

    // Sets VX to the value of VY.
    private fun setVxToVy(opcode: Opcode) {
        registers[opcode.vx] = registers[opcode.vy]
    }

    // Sets VX to VX OR VY. (Bitwise OR operation)
    private fun setVxToVxOrVy(opcode: Opcode) {
        registers[opcode.vx] = registers[opcode.vx] or registers[opcode.vy]
    }

    // Sets VX to VX AND VY. (Bitwise OR operation)
    private fun setVxToVxAndVy(opcode: Opcode) {
        registers[opcode.vx] = registers[opcode.vx] and registers[opcode.vy]
    }

    // Sets VX to VX XOR VY. (Bitwise OR operation)
    private fun setVxToVxXorVy(opcode: Opcode) {
        registers[opcode.vx] = registers[opcode.vx] xor registers[opcode.vy]
    }

    // Adds VY to VX. VF is set to 1 when there's a carry, and to 0 when there isn't.
    private fun addVyToVx(opcode: Opcode) {
        val sum = registers[opcode.vx] + registers[opcode.vy]

        if (sum > 0xFF) {
            registers[VF_INDEX] = 1
        }

        registers[opcode.vx] = sum.toUByte().toInt()
    }

    // VY is subtracted from VX. VF is set to 0 when there's a borrow, and 1 when there isn't.
    private fun subtractVyFromVx(opcode: Opcode) {
        val x = registers[opcode.vx]
        val y = registers[opcode.vy]
        registers[VF_INDEX] = if (x > y) 1 else 0
        registers[opcode.vx] = (x - y).toUByte().toInt()
    }

    // Stores the least significant bit of VX in VF and then shifts VX to the right by 1.
    private fun shiftRightVx(opcode: Opcode) {
        registers[VF_INDEX] = registers[opcode.vx] and 0x1
        registers[opcode.vx] = registers[opcode.vx] shr 1
    }

    // Sets VX to VY minus VX. VF is set to 0 when there's a borrow, and 1 when there isn't.
    private fun subtractVxFromVy(opcode: Opcode) {
        val x = registers[opcode.vx]
        val y = registers[opcode.vy]
        registers[VF_INDEX] = if (y > x) 1 else 0
        registers[opcode.vx] = (y - x).toUByte().toInt()
    }

    // Stores the most significant bit of VX in VF and then shifts VX to the left by 1.
    private fun shiftLeftVx(opcode: Opcode) {
        registers[VF_INDEX] = registers[opcode.vx] shr 7
        registers[opcode.vx] = (registers[opcode.vx] shl 1).toUByte().toInt()
    }

    // Sets I to the address NNN.
    private fun setIToNnn(opcode: Opcode) {
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

    private fun keyOperation(opcode: Opcode) = when (opcode.nnData) {
        OPCODE_NN_SKIP_NEXT_IF_KEY_AT_VX_IS_PRESSED -> skipNextIfKeyAtVxIsPressed(opcode)
        OPCODE_NN_SKIP_NEXT_IF_KEY_AT_VX_IS_NOT_PRESSED -> skipNextIfKeyAtVxIsNotPressed(opcode)
        else -> opcodeNotImplementedError(opcode)
    }

    private fun skipNextIfKeyAtVxIsNotPressed(opcode: Opcode) {
        opcodeNotImplementedError(opcode)
    }

    private fun skipNextIfKeyAtVxIsPressed(opcode: Opcode) {
        opcodeNotImplementedError(opcode)
    }

    // FXNN Operations.
    private fun timerKeyMemOperation(opcode: Opcode) = when (opcode.nnData) {
        OPCODE_NN_SET_VX_TO_DELAY_TIMER -> setVxToDelayTimer(opcode)
        OPCODE_NN_SET_DELAY_TIMER_TO_VX -> setDelayTimerToVx(opcode)
        OPCODE_NN_ADD_VX_TO_I -> addVxToI(opcode)
        OPCODE_NN_SET_VX_TO_I_BCD -> setVxToIBcd(opcode)
        OPCODE_NN_SET_I_TO_SPRITE_AT_VX -> setIToSpriteAtVx(opcode)
        OPCODE_NN_SET_I_TO_V0_UNTIL_VX -> setIToV0UntilVx(opcode)
        OPCODE_NN_SET_V0_UNTIL_VX_TO_I -> setV0UntilVxToI(opcode)
        else -> opcodeNotImplementedError(opcode)
    }

    // Sets VX to the value of the delay timer.
    private fun setVxToDelayTimer(opcode: Opcode) {
        registers[opcode.vx] = delayTimer
    }

    // Sets the delay timer to VX.
    private fun setDelayTimerToVx(opcode: Opcode) {
        delayTimer = registers[opcode.vx]
    }

    private fun addVxToI(opcode: Opcode) {
        i = (i + registers[opcode.vx]).toUShort().toInt()
    }

    // Sets I to the location of the sprite for the character in VX. Characters 0-F (in hexadecimal) are represented by a 4x5 font.
    private fun setIToSpriteAtVx(opcode: Opcode) {
        val sprite = registers[opcode.vx]
        i = FONT_ROM_START + sprite
    }

    // Stores the binary-coded decimal representation of VX, with the most significant of three digits at the
    // address in I, the middle digit at I plus 1, and the least significant digit at I plus 2.
    private fun setVxToIBcd(opcode: Opcode) {
        opcodeNotImplementedError(opcode)
    }

    // Stores V0 to VX (including VX) in memory starting at address I. The offset from I is increased
    // by 1 for each value written, but I itself is left unmodified.
    private fun setIToV0UntilVx(opcode: Opcode) {
        var pointer = i
        for(n in 0..opcode.vx) {
            memory[pointer++] = registers[n]
        }
    }

    // Fills V0 to VX (including VX) with values from memory starting at address I. The offset from I is
    // increased by 1 for each value written, but I itself is left unmodified.[d
    private fun setV0UntilVxToI(opcode: Opcode) {
        var pointer = i
        for(n in 0..opcode.vx) {
            registers[n] = memory[pointer++]
        }
    }

    private fun incPC() {
        pc += 2
    }

    private fun opcodeNotImplementedError(opcode: Opcode) {
         TODO("Opcode ${opcode.value.toString(16)} not implemented.")
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
        const val FONT_ROM_START: Register = 0x000
        const val DEFAULT_CLOCK = 500

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
        private const val OPCODE_VX_VY_OPERATION: Instruction = 0x8000
        private const val OPCODE_N_SET_VX_TO_VY: Instruction = 0x0
        private const val OPCODE_N_SET_VX_TO_VX_OR_VY: Instruction = 0x1
        private const val OPCODE_N_SET_VX_TO_VX_AND_VY: Instruction = 0x2
        private const val OPCODE_N_SET_VX_TO_VX_XOR_VY: Instruction = 0x3
        private const val OPCODE_N_ADD_VY_TO_VX: Instruction = 0x4
        private const val OPCODE_N_SUBTRACT_VY_FROM_VX: Instruction = 0x5
        private const val OPCODE_N_SHIFT_RIGHT_VX_FROM_VY: Instruction = 0x6
        private const val OPCODE_N_SUBTRACT_VX_FROM_VY: Instruction = 0x7
        private const val OPCODE_N_SHIFT_LEFT_VX_FROM_VY: Instruction = 0xE
        private const val OPCODE_SKIP_NEXT_IF_VX_NOT_EQUALS_VY: Instruction = 0x9000
        private const val OPCODE_SET_I_TO_NNN: Instruction = 0xA000
        private const val OPCODE_JUMP_TO_ADDRESS_NNN_PLUS_V0: Instruction = 0xB000
        private const val OPCODE_SET_VX_TO_RANDOM_AND_NN: Instruction = 0xC000
        private const val OPCODE_DRAW_N_AT_VX_VY: Instruction = 0xD000
        private const val OPCODE_KEY_OPERATION: Instruction = 0xE000
        private const val OPCODE_TIMER_KEY_MEM_OPERATION: Instruction = 0xF000
        private const val OPCODE_NN_SKIP_NEXT_IF_KEY_AT_VX_IS_PRESSED: Instruction = 0x9E
        private const val OPCODE_NN_SKIP_NEXT_IF_KEY_AT_VX_IS_NOT_PRESSED: Instruction = 0xA1
        private const val OPCODE_NN_SET_VX_TO_DELAY_TIMER: Instruction = 0x07
        private const val OPCODE_NN_SET_VX_TO_KEY_PRESSED: Instruction = 0x0A
        private const val OPCODE_NN_SET_DELAY_TIMER_TO_VX: Instruction = 0x15
        private const val OPCODE_NN_ADD_VX_TO_I: Instruction = 0x1E
        private const val OPCODE_NN_SET_I_TO_SPRITE_AT_VX: Instruction = 0x29
        private const val OPCODE_NN_SET_VX_TO_I_BCD: Instruction = 0x33
        private const val OPCODE_NN_SET_I_TO_V0_UNTIL_VX: Instruction = 0x55
        private const val OPCODE_NN_SET_V0_UNTIL_VX_TO_I: Instruction = 0x65
    }
}
