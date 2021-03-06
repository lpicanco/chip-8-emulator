# chip-8-emulator
A CHIP-8 Emulator in Kotlin Multiplatform targeting the JVM and JS

![JVM](https://github.com/lpicanco/chip-8-emulator/workflows/JVM/badge.svg)
![Kotlin/JS](https://github.com/lpicanco/chip-8-emulator/workflows/JS/badge.svg)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=lpicanco-chip-8-emulator&metric=alert_status)](https://sonarcloud.io/dashboard?id=lpicanco-chip-8-emulator)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=lpicanco-chip-8-emulator&metric=coverage)](https://sonarcloud.io/dashboard?id=lpicanco-chip-8-emulator)

[https://lpicanco.github.io/chip-8-emulator/](https://lpicanco.github.io/chip-8-emulator/)

![Demo](https://github.com/lpicanco/chip-8-emulator/raw/master/img/blinky_animated.gif)


## Implemented Opcodes
| Instruction | Description |
|:-----------:|:-----------:|
|   `00E0`    | Clears the screen | 
|   `00EE`    | Returns from a subroutine | 
|   `1NNN`    | Jumps to address `NNN` | 
|   `2NNN`    | Calls subroutine at `NNN` | 
|   `3XNN`    | Skips the next instruction if `VX` equals `NN` | 
|   `4XNN`    | Skips the next instruction if `VX` doesn't equal `NN` | 
|   `5XY0`    | Skips the next instruction if `VX` equals `VY` | 
|   `6XNN`    | Sets `VX` to `NN` | 
|   `7XNN`    | Adds `NN` to `VX` without changing the carry flag | 
|   `8XY0`    | Sets `VX` to the value of `VY` | 
|   `8XY1`    | Sets `VX` to `VX` OR `VY` | 
|   `8XY2`    | Sets `VX` to `VX` AND `VY` | 
|   `8XY3`    | Sets `VX` to `VX` XOR `VY` | 
|   `8XY4`    | Adds `VY` to `VX`. `VF` is set to 1 when there's a carry, and to 0 when there isn't | 
|   `8XY5`    | `VY` is subtracted from `VX`. `VF` is set to 0 when there's a borrow, and 1 when there isn't | 
|   `8XY6`    | Stores the least significant bit of `VX` in `VF` and then shifts `VX` to the right by 1 | 
|   `8XY7`    | Sets `VX` to `VY` minus `VX`. `VF` is set to 0 when there's a borrow, and 1 when there isn't | 
|   `8XYE`    | Stores the most significant bit of `VX` in `VF` and then shifts `VX` to the left by 1 | 
|   `9XY0`    | Skips the next instruction if `VX` doesn't equal `VY` | 
|   `ANNN`    | Sets `I` to the address `NNN` | 
|   `BNNN`    | Jumps to the address `NNN` plus `V0` | 
|   `CXNN`    | Sets `VX` to the result of a bitwise and operation on a random number (Typically: 0 to 255) and `NN` | 
|   `DXYN`    | Draws a sprite at coordinate (VX, `VY`) that has a width of 8 pixels and a height of `N`+1 pixels. Each row of 8 pixels is read as bit-coded starting from memory location `I`; `I` value doesn’t change after the execution of this instruction. As described above, `VF` is set to 1 if any screen pixels are flipped from set to unset when the sprite is drawn, and to 0 if that doesn't happen | 
|   `EX9E`    | Skips the next instruction if the key stored in `VX` is pressed | 
|   `EXA1`    | Skips the next instruction if the key stored in `VX` isn't pressed | 
|   `FX07`    | Sets `VX` to the value of the delay timer | 
|   `FX0A`    | A key press is awaited, and then stored in `VX`. (Blocking Operation. All instruction halted until next key event) | 
|   `FX15`    | Sets the delay timer to `VX` | 
|   `FX18`    | Sets the sound timer to `VX` | 
|   `FX1E`    | Adds `VX` to `I`. `VF` is not affected | 
|   `FX29`    | Sets `I` to the location of the sprite for the character in `VX`. Characters 0-F (in hexadecimal) are represented by a 4x5 font | 
|   `FX33`    | Stores the binary-coded decimal representation of `VX`, with the most significant of three digits at the address in `I`, the middle digit at `I` plus 1, and the least significant digit at `I` plus 2 | 
|   `FX55`    | Stores `V0` to `VX` (including `VX`) in memory starting at address `I`. The offset from `I` is increased by 1 for each value written, but `I` itself is left unmodified | 
|   `FX65`    | Fills `V0` to `VX` (including `VX`) with values from memory starting at address `I`. The offset from `I` is increased by 1 for each value written, but `I` itself is left unmodified | 
