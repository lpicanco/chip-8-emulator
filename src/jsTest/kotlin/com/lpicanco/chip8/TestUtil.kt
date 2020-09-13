package com.lpicanco.chip8

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise

actual fun runBlocking(block: suspend () -> Unit): dynamic = GlobalScope.promise { block() }
