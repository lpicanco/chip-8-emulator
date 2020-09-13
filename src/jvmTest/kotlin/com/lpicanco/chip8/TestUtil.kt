package com.lpicanco.chip8

actual fun runBlocking(block: suspend () -> Unit) = kotlinx.coroutines.runBlocking { block() }
