# chip-8-emulator
A CHIP-8 Emulator in Kotlin

![Java8](https://github.com/lpicanco/chip-8-emulator/workflows/Java8/badge.svg?branch=master)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=lpicanco-chip-8-emulator&metric=alert_status)](https://sonarcloud.io/dashboard?id=lpicanco-chip-8-emulator)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=lpicanco-chip-8-emulator&metric=coverage)](https://sonarcloud.io/dashboard?id=lpicanco-chip-8-emulator)

## Implemented Opcodes
| Instruction | Description |Implemented|
|:-----------:|:-----------:|:---------:|
|   `00E0`    | Clears the screen | :heavy_check_mark: |
|   `00EE`    | Returns from a subroutine | :heavy_check_mark: |
|   `1NNN`    | Jumps to address `NNN` | :heavy_check_mark: |
|   `2NNN`    | Calls subroutine at `NNN` | :heavy_check_mark: |
|   `3XNN`    | Skips the next instruction if `VX` equals `NN` | :heavy_check_mark: |
|   `4XNN`    | Skips the next instruction if `VX` doesn't equal `NN` | :heavy_check_mark: |
|   `5XY0`    | Skips the next instruction if `VX` equals `VY` | :heavy_check_mark: |
|   `6XNN`    | Sets `VX` to `NN` | :heavy_check_mark: |
|   `7XNN`    | Adds `NN` to `VX` without changing the carry flag | :heavy_check_mark: |
|   `8XY0`    | Sets `VX` to the value of `VY` | :heavy_check_mark: |
|   `8XY1`    | Sets `VX` to `VX` OR `VY` | :heavy_check_mark: |
|   `8XY2`    | Sets `VX` to `VX` AND `VY` | :heavy_check_mark: |
|   `8XY3`    | Sets `VX` to `VX` XOR `VY` | :heavy_check_mark: |
|   `8XY4`    | Adds `VY` to `VX`. `VF` is set to 1 when there's a carry, and to 0 when there isn't | :heavy_check_mark: |
|   `8XY5`    | `VY` is subtracted from `VX`. `VF` is set to 0 when there's a borrow, and 1 when there isn't | :heavy_check_mark: |
|   `8XY6`    | Stores the least significant bit of `VX` in `VF` and then shifts `VX` to the right by 1 | :heavy_check_mark: |
|   `8XY7`    | Sets `VX` to `VY` minus `VX`. `VF` is set to 0 when there's a borrow, and 1 when there isn't | :heavy_check_mark: |
|   `8XYE`    | Stores the most significant bit of `VX` in `VF` and then shifts `VX` to the left by 1 | :heavy_check_mark: |
|   `9XY0`    | Skips the next instruction if `VX` doesn't equal `VY` | :heavy_check_mark: |
|   `ANNN`    | Sets `I` to the address `NNN` | :heavy_check_mark: |
|   `BNNN`    | Jumps to the address `NNN` plus `V0` | :heavy_check_mark: |
|   `CXNN`    | Sets `VX` to the result of a bitwise and operation on a random number (Typically: 0 to 255) and `NN` | :heavy_check_mark: |
|   `DXYN`    | Draws a sprite at coordinate (VX, `VY`) that has a width of 8 pixels and a height of `N`+1 pixels. Each row of 8 pixels is read as bit-coded starting from memory location `I`; `I` value doesn’t change after the execution of this instruction. As described above, `VF` is set to 1 if any screen pixels are flipped from set to unset when the sprite is drawn, and to 0 if that doesn't happen | :heavy_check_mark: |
|   `EX9E`    | Skips the next instruction if the key stored in `VX` is pressed | :heavy_check_mark: |
|   `EXA1`    | Skips the next instruction if the key stored in `VX` isn't pressed | :heavy_check_mark: |
|   `FX07`    | Sets `VX` to the value of the delay timer | :heavy_check_mark: |
|   `FX0A`    | A key press is awaited, and then stored in `VX`. (Blocking Operation. All instruction halted until next key event) | :heavy_check_mark: |
|   `FX15`    | Sets the delay timer to `VX` | :heavy_check_mark: |
|   `FX18`    | Sets the sound timer to `VX` |
|   `FX1E`    | Adds `VX` to `I`. `VF` is not affected | :heavy_check_mark: |
|   `FX29`    | Sets `I` to the location of the sprite for the character in `VX`. Characters 0-F (in hexadecimal) are represented by a 4x5 font | :heavy_check_mark: |
|   `FX33`    | Stores the binary-coded decimal representation of `VX`, with the most significant of three digits at the address in `I`, the middle digit at `I` plus 1, and the least significant digit at `I` plus 2 | :heavy_check_mark: |
|   `FX55`    | Stores `V0` to `VX` (including `VX`) in memory starting at address `I`. The offset from `I` is increased by 1 for each value written, but `I` itself is left unmodified | :heavy_check_mark: |
|   `FX65`    | Fills `V0` to `VX` (including `VX`) with values from memory starting at address `I`. The offset from `I` is increased by 1 for each value written, but `I` itself is left unmodified | :heavy_check_mark: |
