package com.lpicanco.chip8.gui

object SampleRoms {

    val list = mapOf(
        "Tetris" to intArrayOf(
            0xa2, 0xb4, 0x23, 0xe6, 0x22, 0xb6, 0x70, 0x01, 0xd0, 0x11, 0x30, 0x25, 0x12, 0x06, 0x71, 0xff,
            0xd0, 0x11, 0x60, 0x1a, 0xd0, 0x11, 0x60, 0x25, 0x31, 0x00, 0x12, 0x0e, 0xc4, 0x70, 0x44, 0x70,
            0x12, 0x1c, 0xc3, 0x03, 0x60, 0x1e, 0x61, 0x03, 0x22, 0x5c, 0xf5, 0x15, 0xd0, 0x14, 0x3f, 0x01,
            0x12, 0x3c, 0xd0, 0x14, 0x71, 0xff, 0xd0, 0x14, 0x23, 0x40, 0x12, 0x1c, 0xe7, 0xa1, 0x22, 0x72,
            0xe8, 0xa1, 0x22, 0x84, 0xe9, 0xa1, 0x22, 0x96, 0xe2, 0x9e, 0x12, 0x50, 0x66, 0x00, 0xf6, 0x15,
            0xf6, 0x07, 0x36, 0x00, 0x12, 0x3c, 0xd0, 0x14, 0x71, 0x01, 0x12, 0x2a, 0xa2, 0xc4, 0xf4, 0x1e,
            0x66, 0x00, 0x43, 0x01, 0x66, 0x04, 0x43, 0x02, 0x66, 0x08, 0x43, 0x03, 0x66, 0x0c, 0xf6, 0x1e,
            0x00, 0xee, 0xd0, 0x14, 0x70, 0xff, 0x23, 0x34, 0x3f, 0x01, 0x00, 0xee, 0xd0, 0x14, 0x70, 0x01,
            0x23, 0x34, 0x00, 0xee, 0xd0, 0x14, 0x70, 0x01, 0x23, 0x34, 0x3f, 0x01, 0x00, 0xee, 0xd0, 0x14,
            0x70, 0xff, 0x23, 0x34, 0x00, 0xee, 0xd0, 0x14, 0x73, 0x01, 0x43, 0x04, 0x63, 0x00, 0x22, 0x5c,
            0x23, 0x34, 0x3f, 0x01, 0x00, 0xee, 0xd0, 0x14, 0x73, 0xff, 0x43, 0xff, 0x63, 0x03, 0x22, 0x5c,
            0x23, 0x34, 0x00, 0xee, 0x80, 0x00, 0x67, 0x05, 0x68, 0x06, 0x69, 0x04, 0x61, 0x1f, 0x65, 0x10,
            0x62, 0x07, 0x00, 0xee, 0x40, 0xe0, 0x00, 0x00, 0x40, 0xc0, 0x40, 0x00, 0x00, 0xe0, 0x40, 0x00,
            0x40, 0x60, 0x40, 0x00, 0x40, 0x40, 0x60, 0x00, 0x20, 0xe0, 0x00, 0x00, 0xc0, 0x40, 0x40, 0x00,
            0x00, 0xe0, 0x80, 0x00, 0x40, 0x40, 0xc0, 0x00, 0x00, 0xe0, 0x20, 0x00, 0x60, 0x40, 0x40, 0x00,
            0x80, 0xe0, 0x00, 0x00, 0x40, 0xc0, 0x80, 0x00, 0xc0, 0x60, 0x00, 0x00, 0x40, 0xc0, 0x80, 0x00,
            0xc0, 0x60, 0x00, 0x00, 0x80, 0xc0, 0x40, 0x00, 0x00, 0x60, 0xc0, 0x00, 0x80, 0xc0, 0x40, 0x00,
            0x00, 0x60, 0xc0, 0x00, 0xc0, 0xc0, 0x00, 0x00, 0xc0, 0xc0, 0x00, 0x00, 0xc0, 0xc0, 0x00, 0x00,
            0xc0, 0xc0, 0x00, 0x00, 0x40, 0x40, 0x40, 0x40, 0x00, 0xf0, 0x00, 0x00, 0x40, 0x40, 0x40, 0x40,
            0x00, 0xf0, 0x00, 0x00, 0xd0, 0x14, 0x66, 0x35, 0x76, 0xff, 0x36, 0x00, 0x13, 0x38, 0x00, 0xee,
            0xa2, 0xb4, 0x8c, 0x10, 0x3c, 0x1e, 0x7c, 0x01, 0x3c, 0x1e, 0x7c, 0x01, 0x3c, 0x1e, 0x7c, 0x01,
            0x23, 0x5e, 0x4b, 0x0a, 0x23, 0x72, 0x91, 0xc0, 0x00, 0xee, 0x71, 0x01, 0x13, 0x50, 0x60, 0x1b,
            0x6b, 0x00, 0xd0, 0x11, 0x3f, 0x00, 0x7b, 0x01, 0xd0, 0x11, 0x70, 0x01, 0x30, 0x25, 0x13, 0x62,
            0x00, 0xee, 0x60, 0x1b, 0xd0, 0x11, 0x70, 0x01, 0x30, 0x25, 0x13, 0x74, 0x8e, 0x10, 0x8d, 0xe0,
            0x7e, 0xff, 0x60, 0x1b, 0x6b, 0x00, 0xd0, 0xe1, 0x3f, 0x00, 0x13, 0x90, 0xd0, 0xe1, 0x13, 0x94,
            0xd0, 0xd1, 0x7b, 0x01, 0x70, 0x01, 0x30, 0x25, 0x13, 0x86, 0x4b, 0x00, 0x13, 0xa6, 0x7d, 0xff,
            0x7e, 0xff, 0x3d, 0x01, 0x13, 0x82, 0x23, 0xc0, 0x3f, 0x01, 0x23, 0xc0, 0x7a, 0x01, 0x23, 0xc0,
            0x80, 0xa0, 0x6d, 0x07, 0x80, 0xd2, 0x40, 0x04, 0x75, 0xfe, 0x45, 0x02, 0x65, 0x04, 0x00, 0xee,
            0xa7, 0x00, 0xf2, 0x55, 0xa8, 0x04, 0xfa, 0x33, 0xf2, 0x65, 0xf0, 0x29, 0x6d, 0x32, 0x6e, 0x00,
            0xdd, 0xe5, 0x7d, 0x05, 0xf1, 0x29, 0xdd, 0xe5, 0x7d, 0x05, 0xf2, 0x29, 0xdd, 0xe5, 0xa7, 0x00,
            0xf2, 0x65, 0xa2, 0xb4, 0x00, 0xee, 0x6a, 0x00, 0x60, 0x19, 0x00, 0xee, 0x37, 0x23
        ),
        "Pong" to intArrayOf(
            0x6a, 0x02, 0x6b, 0x0c, 0x6c, 0x3f, 0x6d, 0x0c, 0xa2, 0xea, 0xda, 0xb6, 0xdc, 0xd6, 0x6e, 0x00,
            0x22, 0xd4, 0x66, 0x03, 0x68, 0x02, 0x60, 0x60, 0xf0, 0x15, 0xf0, 0x07, 0x30, 0x00, 0x12, 0x1a,
            0xc7, 0x17, 0x77, 0x08, 0x69, 0xff, 0xa2, 0xf0, 0xd6, 0x71, 0xa2, 0xea, 0xda, 0xb6, 0xdc, 0xd6,
            0x60, 0x01, 0xe0, 0xa1, 0x7b, 0xfe, 0x60, 0x04, 0xe0, 0xa1, 0x7b, 0x02, 0x60, 0x1f, 0x8b, 0x02,
            0xda, 0xb6, 0x60, 0x0c, 0xe0, 0xa1, 0x7d, 0xfe, 0x60, 0x0d, 0xe0, 0xa1, 0x7d, 0x02, 0x60, 0x1f,
            0x8d, 0x02, 0xdc, 0xd6, 0xa2, 0xf0, 0xd6, 0x71, 0x86, 0x84, 0x87, 0x94, 0x60, 0x3f, 0x86, 0x02,
            0x61, 0x1f, 0x87, 0x12, 0x46, 0x02, 0x12, 0x78, 0x46, 0x3f, 0x12, 0x82, 0x47, 0x1f, 0x69, 0xff,
            0x47, 0x00, 0x69, 0x01, 0xd6, 0x71, 0x12, 0x2a, 0x68, 0x02, 0x63, 0x01, 0x80, 0x70, 0x80, 0xb5,
            0x12, 0x8a, 0x68, 0xfe, 0x63, 0x0a, 0x80, 0x70, 0x80, 0xd5, 0x3f, 0x01, 0x12, 0xa2, 0x61, 0x02,
            0x80, 0x15, 0x3f, 0x01, 0x12, 0xba, 0x80, 0x15, 0x3f, 0x01, 0x12, 0xc8, 0x80, 0x15, 0x3f, 0x01,
            0x12, 0xc2, 0x60, 0x20, 0xf0, 0x18, 0x22, 0xd4, 0x8e, 0x34, 0x22, 0xd4, 0x66, 0x3e, 0x33, 0x01,
            0x66, 0x03, 0x68, 0xfe, 0x33, 0x01, 0x68, 0x02, 0x12, 0x16, 0x79, 0xff, 0x49, 0xfe, 0x69, 0xff,
            0x12, 0xc8, 0x79, 0x01, 0x49, 0x02, 0x69, 0x01, 0x60, 0x04, 0xf0, 0x18, 0x76, 0x01, 0x46, 0x40,
            0x76, 0xfe, 0x12, 0x6c, 0xa2, 0xf2, 0xfe, 0x33, 0xf2, 0x65, 0xf1, 0x29, 0x64, 0x14, 0x65, 0x00,
            0xd4, 0x55, 0x74, 0x15, 0xf2, 0x29, 0xd4, 0x55, 0x00, 0xee, 0x80, 0x80, 0x80, 0x80, 0x80, 0x80,
            0x80, 0x00, 0x00, 0x00, 0x00, 0x00
        ),
        "Blinky" to intArrayOf(
            0x12, 0x1a, 0x32, 0x2e, 0x30, 0x30, 0x20, 0x43, 0x2e, 0x20, 0x45, 0x67, 0x65, 0x62, 0x65, 0x72,
            0x67, 0x20, 0x31, 0x38, 0x2f, 0x38, 0x2d, 0x27, 0x39, 0x31, 0x80, 0x03, 0x81, 0x13, 0xa8, 0xc8,
            0xf1, 0x55, 0x60, 0x05, 0xa8, 0xcc, 0xf0, 0x55, 0x87, 0x73, 0x86, 0x63, 0x27, 0x72, 0x00, 0xe0,
            0x27, 0x94, 0x6e, 0x40, 0x87, 0xe2, 0x6e, 0x27, 0x87, 0xe1, 0x68, 0x1a, 0x69, 0x0c, 0x6a, 0x38,
            0x6b, 0x00, 0x6c, 0x02, 0x6d, 0x1a, 0x27, 0x50, 0xa8, 0xed, 0xda, 0xb4, 0xdc, 0xd4, 0x23, 0xd0,
            0x3e, 0x00, 0x12, 0x7c, 0xa8, 0xcc, 0xf0, 0x65, 0x85, 0x00, 0xc4, 0xff, 0x84, 0x52, 0x24, 0xf6,
            0xc4, 0xff, 0x84, 0x52, 0x26, 0x1e, 0x60, 0x01, 0xe0, 0xa1, 0x27, 0xd6, 0x36, 0xf7, 0x12, 0x4e,
            0x8e, 0x60, 0x28, 0x7a, 0x6e, 0x64, 0x28, 0x7a, 0x27, 0xd6, 0x12, 0x2a, 0xf0, 0x07, 0x40, 0x00,
            0x13, 0x10, 0x80, 0x80, 0x80, 0x06, 0x81, 0xa0, 0x81, 0x06, 0x80, 0x15, 0x40, 0x00, 0x12, 0x9a,
            0x40, 0x01, 0x12, 0x9a, 0x40, 0xff, 0x12, 0x9a, 0x12, 0xc8, 0x80, 0x90, 0x80, 0x06, 0x81, 0xb0,
            0x81, 0x06, 0x80, 0x15, 0x40, 0x00, 0x12, 0xb2, 0x40, 0x01, 0x12, 0xb2, 0x40, 0xff, 0x12, 0xb2,
            0x12, 0xc8, 0xa8, 0xed, 0xda, 0xb4, 0x6a, 0x38, 0x6b, 0x00, 0xda, 0xb4, 0x6e, 0xf3, 0x87, 0xe2,
            0x6e, 0x04, 0x87, 0xe1, 0x6e, 0x32, 0x28, 0x7a, 0x80, 0x80, 0x80, 0x06, 0x81, 0xc0, 0x81, 0x06,
            0x80, 0x15, 0x40, 0x00, 0x12, 0xe0, 0x40, 0x01, 0x12, 0xe0, 0x40, 0xff, 0x12, 0xe0, 0x12, 0x54,
            0x80, 0x90, 0x80, 0x06, 0x81, 0xd0, 0x81, 0x06, 0x80, 0x15, 0x40, 0x00, 0x12, 0xf8, 0x40, 0x01,
            0x12, 0xf8, 0x40, 0xff, 0x12, 0xf8, 0x12, 0x54, 0xa8, 0xed, 0xdc, 0xd4, 0x6c, 0x02, 0x6d, 0x1a,
            0xdc, 0xd4, 0x6e, 0xcf, 0x87, 0xe2, 0x6e, 0x20, 0x87, 0xe1, 0x6e, 0x19, 0x28, 0x7a, 0x12, 0x54,
            0x60, 0x3f, 0x28, 0xa8, 0x27, 0x50, 0xa8, 0xed, 0xda, 0xb4, 0xdc, 0xd4, 0x6e, 0x40, 0x87, 0xe3,
            0x80, 0x70, 0x80, 0xe2, 0x30, 0x00, 0x12, 0x32, 0x8e, 0x60, 0x28, 0x7a, 0x28, 0x8a, 0x00, 0xe0,
            0x66, 0x11, 0x67, 0x0a, 0xa8, 0xca, 0x27, 0xe6, 0x66, 0x11, 0x67, 0x10, 0xa8, 0xc8, 0x27, 0xe6,
            0x64, 0x00, 0x65, 0x08, 0x66, 0x00, 0x67, 0x0f, 0xab, 0x19, 0xd4, 0x69, 0xab, 0x22, 0xd5, 0x69,
            0x60, 0x03, 0x28, 0xa8, 0x3e, 0x00, 0x13, 0xc6, 0xab, 0x19, 0xd4, 0x69, 0xab, 0x22, 0xd5, 0x69,
            0x74, 0x02, 0x75, 0x02, 0x34, 0x30, 0x13, 0x48, 0xab, 0x19, 0xd4, 0x69, 0xab, 0x22, 0xd5, 0x69,
            0x60, 0x03, 0x28, 0xa8, 0x3e, 0x00, 0x13, 0xc6, 0xab, 0x19, 0xd4, 0x69, 0xab, 0x22, 0xd5, 0x69,
            0x76, 0x02, 0x36, 0x16, 0x13, 0x68, 0xab, 0x19, 0xd4, 0x69, 0xab, 0x22, 0xd5, 0x69, 0x60, 0x03,
            0x28, 0xa8, 0x3e, 0x00, 0x13, 0xc6, 0xab, 0x19, 0xd4, 0x69, 0xab, 0x22, 0xd5, 0x69, 0x74, 0xfe,
            0x75, 0xfe, 0x34, 0x00, 0x13, 0x86, 0xab, 0x19, 0xd4, 0x69, 0xab, 0x22, 0xd5, 0x69, 0x60, 0x03,
            0x28, 0xa8, 0x3e, 0x00, 0x13, 0xc6, 0xab, 0x19, 0xd4, 0x69, 0xab, 0x22, 0xd5, 0x69, 0x76, 0xfe,
            0x36, 0x00, 0x13, 0xa6, 0x13, 0x48, 0xab, 0x22, 0xd5, 0x69, 0xab, 0x2b, 0xd5, 0x69, 0x12, 0x1a,
            0x83, 0x70, 0x6e, 0x03, 0x83, 0xe2, 0x84, 0x80, 0x85, 0x90, 0x6e, 0x06, 0xee, 0xa1, 0x14, 0x32,
            0x6e, 0x03, 0xee, 0xa1, 0x14, 0x4a, 0x6e, 0x08, 0xee, 0xa1, 0x14, 0x62, 0x6e, 0x07, 0xee, 0xa1,
            0x14, 0x7a, 0x43, 0x03, 0x75, 0x02, 0x43, 0x00, 0x75, 0xfe, 0x43, 0x02, 0x74, 0x02, 0x43, 0x01,
            0x74, 0xfe, 0x80, 0x40, 0x81, 0x50, 0x27, 0xba, 0x82, 0x00, 0x6e, 0x08, 0x80, 0xe2, 0x30, 0x00,
            0x14, 0x92, 0x6e, 0x07, 0x80, 0x20, 0x82, 0xe2, 0x42, 0x05, 0x14, 0x9a, 0x42, 0x06, 0x14, 0xb2,
            0x42, 0x07, 0x14, 0xec, 0x27, 0x50, 0x6e, 0xfc, 0x87, 0xe2, 0x87, 0x31, 0x88, 0x40, 0x89, 0x50,
            0x17, 0x50, 0x80, 0x40, 0x81, 0x50, 0x71, 0x02, 0x27, 0xba, 0x82, 0x00, 0x6e, 0x08, 0x80, 0xe2,
            0x30, 0x00, 0x13, 0xf2, 0x63, 0x03, 0x75, 0x02, 0x14, 0x0e, 0x80, 0x40, 0x81, 0x50, 0x71, 0xfe,
            0x27, 0xba, 0x82, 0x00, 0x6e, 0x08, 0x80, 0xe2, 0x30, 0x00, 0x13, 0xf2, 0x63, 0x00, 0x75, 0xfe,
            0x14, 0x0e, 0x80, 0x40, 0x81, 0x50, 0x70, 0x02, 0x27, 0xba, 0x82, 0x00, 0x6e, 0x08, 0x80, 0xe2,
            0x30, 0x00, 0x13, 0xf2, 0x63, 0x02, 0x74, 0x02, 0x14, 0x0e, 0x80, 0x40, 0x81, 0x50, 0x70, 0xfe,
            0x27, 0xba, 0x82, 0x00, 0x6e, 0x08, 0x80, 0xe2, 0x30, 0x00, 0x13, 0xf2, 0x63, 0x01, 0x74, 0xfe,
            0x14, 0x0e, 0x27, 0x50, 0xd8, 0x94, 0x8e, 0xf0, 0x00, 0xee, 0x6e, 0xf0, 0x80, 0xe2, 0x80, 0x31,
            0xf0, 0x55, 0xa8, 0xf1, 0xd4, 0x54, 0x76, 0x01, 0x61, 0x05, 0xf0, 0x07, 0x40, 0x00, 0xf1, 0x18,
            0x14, 0x24, 0x6e, 0xf0, 0x80, 0xe2, 0x80, 0x31, 0xf0, 0x55, 0xa8, 0xf5, 0xd4, 0x54, 0x76, 0x04,
            0x80, 0xa0, 0x81, 0xb0, 0x27, 0xba, 0x6e, 0xf0, 0x80, 0xe2, 0x30, 0x00, 0x14, 0xd2, 0x6e, 0x0c,
            0x87, 0xe3, 0x80, 0xc0, 0x81, 0xd0, 0x27, 0xba, 0x6e, 0xf0, 0x80, 0xe2, 0x30, 0x00, 0x14, 0xe4,
            0x6e, 0x30, 0x87, 0xe3, 0x60, 0xff, 0xf0, 0x18, 0xf0, 0x15, 0x14, 0x24, 0x43, 0x01, 0x64, 0x3a,
            0x43, 0x02, 0x64, 0x00, 0x14, 0x24, 0x82, 0x70, 0x83, 0x70, 0x6e, 0x0c, 0x82, 0xe2, 0x80, 0xa0,
            0x81, 0xb0, 0x27, 0xba, 0xa8, 0xed, 0x6e, 0xf0, 0x80, 0xe2, 0x30, 0x00, 0x15, 0x24, 0xda, 0xb4,
            0x42, 0x0c, 0x7b, 0x02, 0x42, 0x00, 0x7b, 0xfe, 0x42, 0x08, 0x7a, 0x02, 0x42, 0x04, 0x7a, 0xfe,
            0xda, 0xb4, 0x00, 0xee, 0x6e, 0x80, 0xf1, 0x07, 0x31, 0x00, 0x15, 0xd4, 0x34, 0x00, 0x15, 0xd4,
            0x81, 0x00, 0x83, 0x0e, 0x3f, 0x00, 0x15, 0x56, 0x83, 0x90, 0x83, 0xb5, 0x4f, 0x00, 0x15, 0x8c,
            0x33, 0x00, 0x15, 0x74, 0x87, 0xe3, 0x83, 0x80, 0x83, 0xa5, 0x4f, 0x00, 0x15, 0xbc, 0x33, 0x00,
            0x15, 0xa4, 0x87, 0xe3, 0x15, 0xd4, 0x83, 0x80, 0x83, 0xa5, 0x4f, 0x00, 0x15, 0xbc, 0x33, 0x00,
            0x15, 0xa4, 0x87, 0xe3, 0x83, 0x90, 0x83, 0xb5, 0x4f, 0x00, 0x15, 0x8c, 0x33, 0x00, 0x15, 0x74,
            0x87, 0xe3, 0x15, 0xd4, 0x63, 0x40, 0x81, 0x32, 0x41, 0x00, 0x15, 0xd4, 0xda, 0xb4, 0x7b, 0x02,
            0xda, 0xb4, 0x6e, 0xf3, 0x87, 0xe2, 0x62, 0x0c, 0x87, 0x21, 0x00, 0xee, 0x63, 0x10, 0x81, 0x32,
            0x41, 0x00, 0x15, 0xd4, 0xda, 0xb4, 0x7b, 0xfe, 0xda, 0xb4, 0x6e, 0xf3, 0x87, 0xe2, 0x62, 0x00,
            0x87, 0x21, 0x00, 0xee, 0x63, 0x20, 0x81, 0x32, 0x41, 0x00, 0x15, 0xd4, 0xda, 0xb4, 0x7a, 0x02,
            0xda, 0xb4, 0x6e, 0xf3, 0x87, 0xe2, 0x62, 0x08, 0x87, 0x21, 0x00, 0xee, 0x63, 0x80, 0x81, 0x32,
            0x41, 0x00, 0x15, 0xd4, 0xda, 0xb4, 0x7a, 0xfe, 0xda, 0xb4, 0x6e, 0xf3, 0x87, 0xe2, 0x62, 0x04,
            0x87, 0x21, 0x00, 0xee, 0xc1, 0xf0, 0x80, 0x12, 0x30, 0x00, 0x15, 0xe4, 0x6e, 0x0c, 0x87, 0xe3,
            0x82, 0xe3, 0x15, 0x0e, 0xda, 0xb4, 0x80, 0x0e, 0x4f, 0x00, 0x15, 0xf2, 0x62, 0x04, 0x7a, 0xfe,
            0x16, 0x14, 0x80, 0x0e, 0x4f, 0x00, 0x15, 0xfe, 0x62, 0x0c, 0x7b, 0x02, 0x16, 0x14, 0x80, 0x0e,
            0x4f, 0x00, 0x16, 0x0a, 0x62, 0x08, 0x7a, 0x02, 0x16, 0x14, 0x80, 0x0e, 0x4f, 0x00, 0x15, 0xdc,
            0x62, 0x00, 0x7b, 0xfe, 0xda, 0xb4, 0x6e, 0xf3, 0x87, 0xe2, 0x87, 0x21, 0x00, 0xee, 0x82, 0x70,
            0x83, 0x70, 0x6e, 0x30, 0x82, 0xe2, 0x80, 0xc0, 0x81, 0xd0, 0x27, 0xba, 0xa8, 0xed, 0x6e, 0xf0,
            0x80, 0xe2, 0x30, 0x00, 0x16, 0x4c, 0xdc, 0xd4, 0x42, 0x30, 0x7d, 0x02, 0x42, 0x00, 0x7d, 0xfe,
            0x42, 0x20, 0x7c, 0x02, 0x42, 0x10, 0x7c, 0xfe, 0xdc, 0xd4, 0x00, 0xee, 0x6e, 0x80, 0xf1, 0x07,
            0x31, 0x00, 0x17, 0x04, 0x34, 0x00, 0x17, 0x04, 0x81, 0x00, 0x83, 0x0e, 0x4f, 0x00, 0x16, 0x7e,
            0x83, 0x90, 0x83, 0xd5, 0x4f, 0x00, 0x16, 0xb6, 0x33, 0x00, 0x16, 0x9c, 0x87, 0xe3, 0x83, 0x80,
            0x83, 0xc5, 0x4f, 0x00, 0x16, 0xea, 0x33, 0x00, 0x16, 0xd0, 0x87, 0xe3, 0x17, 0x04, 0x83, 0x80,
            0x83, 0xc5, 0x4f, 0x00, 0x16, 0xea, 0x33, 0x00, 0x16, 0xd0, 0x87, 0xe3, 0x83, 0x90, 0x83, 0xd5,
            0x4f, 0x00, 0x16, 0xb6, 0x33, 0x00, 0x16, 0x9c, 0x87, 0xe3, 0x17, 0x04, 0x63, 0x40, 0x81, 0x32,
            0x41, 0x00, 0x17, 0x04, 0xdc, 0xd4, 0x7d, 0x02, 0xdc, 0xd4, 0x87, 0xe3, 0x6e, 0xcf, 0x87, 0xe2,
            0x62, 0x30, 0x87, 0x21, 0x00, 0xee, 0x63, 0x10, 0x81, 0x32, 0x41, 0x00, 0x17, 0x04, 0xdc, 0xd4,
            0x7d, 0xfe, 0xdc, 0xd4, 0x87, 0xe3, 0x6e, 0xcf, 0x87, 0xe2, 0x62, 0x00, 0x87, 0x21, 0x00, 0xee,
            0x63, 0x20, 0x81, 0x32, 0x41, 0x00, 0x17, 0x04, 0xdc, 0xd4, 0x7c, 0x02, 0xdc, 0xd4, 0x87, 0xe3,
            0x6e, 0xcf, 0x87, 0xe2, 0x62, 0x20, 0x87, 0x21, 0x00, 0xee, 0x63, 0x80, 0x81, 0x32, 0x41, 0x00,
            0x17, 0x04, 0xdc, 0xd4, 0x7c, 0xfe, 0xdc, 0xd4, 0x87, 0xe3, 0x6e, 0xcf, 0x87, 0xe2, 0x62, 0x10,
            0x87, 0x21, 0x00, 0xee, 0xc1, 0xf0, 0x80, 0x12, 0x30, 0x00, 0x17, 0x16, 0x87, 0xe3, 0x6e, 0x30,
            0x87, 0xe3, 0x82, 0xe3, 0x16, 0x36, 0xdc, 0xd4, 0x80, 0x0e, 0x4f, 0x00, 0x17, 0x24, 0x62, 0x90,
            0x7c, 0xfe, 0x17, 0x46, 0x80, 0x0e, 0x4f, 0x00, 0x17, 0x30, 0x62, 0x30, 0x7d, 0x02, 0x17, 0x46,
            0x80, 0x0e, 0x4f, 0x00, 0x17, 0x3c, 0x62, 0xa0, 0x7c, 0x02, 0x17, 0x46, 0x80, 0x0e, 0x4f, 0x00,
            0x17, 0x0c, 0x62, 0x00, 0x7d, 0xfe, 0xdc, 0xd4, 0x6e, 0x4f, 0x87, 0xe2, 0x87, 0x21, 0x00, 0xee,
            0x80, 0x70, 0x6e, 0x03, 0x80, 0xe2, 0x80, 0x0e, 0x81, 0x80, 0x81, 0x94, 0x6e, 0x02, 0x81, 0xe2,
            0x41, 0x00, 0x70, 0x01, 0x80, 0x0e, 0x80, 0x0e, 0xa8, 0xcd, 0xf0, 0x1e, 0xd8, 0x94, 0x8e, 0xf0,
            0x00, 0xee, 0x6e, 0x00, 0xa9, 0x19, 0xfe, 0x1e, 0xfe, 0x1e, 0xfe, 0x1e, 0xfe, 0x1e, 0xf3, 0x65,
            0xab, 0x34, 0xfe, 0x1e, 0xfe, 0x1e, 0xfe, 0x1e, 0xfe, 0x1e, 0xf3, 0x55, 0x7e, 0x01, 0x3e, 0x80,
            0x17, 0x74, 0x00, 0xee, 0x82, 0x23, 0x83, 0x33, 0x6e, 0x0f, 0x80, 0x20, 0x81, 0x30, 0x27, 0xbe,
            0x80, 0xe2, 0x80, 0x0e, 0xa8, 0xf9, 0xf0, 0x1e, 0xd2, 0x32, 0x72, 0x02, 0x32, 0x40, 0x17, 0x9a,
            0x82, 0x23, 0x73, 0x02, 0x43, 0x20, 0x00, 0xee, 0x17, 0x9a, 0x70, 0x02, 0x71, 0x02, 0x80, 0x06,
            0x81, 0x06, 0x81, 0x0e, 0x81, 0x0e, 0x81, 0x0e, 0x81, 0x0e, 0xab, 0x34, 0xf1, 0x1e, 0xf1, 0x1e,
            0xf0, 0x1e, 0xf0, 0x65, 0x00, 0xee, 0xa8, 0xcc, 0xf0, 0x65, 0x80, 0x06, 0xf0, 0x55, 0x60, 0x01,
            0xe0, 0xa1, 0x17, 0xe0, 0x00, 0xee, 0xf1, 0x65, 0x6e, 0x01, 0x84, 0x43, 0x82, 0x00, 0x83, 0x10,
            0x65, 0x10, 0x83, 0x55, 0x4f, 0x00, 0x82, 0xe5, 0x4f, 0x00, 0x18, 0x0c, 0x65, 0x27, 0x82, 0x55,
            0x4f, 0x00, 0x18, 0x0c, 0x80, 0x20, 0x81, 0x30, 0x84, 0xe4, 0x17, 0xf0, 0xf4, 0x29, 0xd6, 0x75,
            0x76, 0x06, 0x84, 0x43, 0x82, 0x00, 0x83, 0x10, 0x65, 0xe8, 0x83, 0x55, 0x4f, 0x00, 0x82, 0xe5,
            0x4f, 0x00, 0x18, 0x34, 0x65, 0x03, 0x82, 0x55, 0x4f, 0x00, 0x18, 0x34, 0x80, 0x20, 0x81, 0x30,
            0x84, 0xe4, 0x18, 0x18, 0xf4, 0x29, 0xd6, 0x75, 0x76, 0x06, 0x84, 0x43, 0x82, 0x00, 0x83, 0x10,
            0x65, 0x64, 0x83, 0x55, 0x4f, 0x00, 0x82, 0xe5, 0x4f, 0x00, 0x18, 0x54, 0x80, 0x20, 0x81, 0x30,
            0x84, 0xe4, 0x18, 0x40, 0xf4, 0x29, 0xd6, 0x75, 0x76, 0x06, 0x84, 0x43, 0x82, 0x00, 0x83, 0x10,
            0x65, 0x0a, 0x83, 0x55, 0x4f, 0x00, 0x18, 0x6e, 0x81, 0x30, 0x84, 0xe4, 0x18, 0x60, 0xf4, 0x29,
            0xd6, 0x75, 0x76, 0x06, 0xf1, 0x29, 0xd6, 0x75, 0x00, 0xee, 0xa8, 0xc8, 0xf1, 0x65, 0x81, 0xe4,
            0x3f, 0x00, 0x70, 0x01, 0xa8, 0xc8, 0xf1, 0x55, 0x00, 0xee, 0xa8, 0xc8, 0xf3, 0x65, 0x8e, 0x00,
            0x8e, 0x25, 0x4f, 0x00, 0x00, 0xee, 0x3e, 0x00, 0x18, 0xa2, 0x8e, 0x10, 0x8e, 0x35, 0x4f, 0x00,
            0x00, 0xee, 0xa8, 0xca, 0xf1, 0x55, 0x00, 0xee, 0x8e, 0xe3, 0x62, 0x0f, 0x63, 0xff, 0x61, 0x10,
            0xe2, 0xa1, 0x18, 0xc4, 0x81, 0x34, 0x31, 0x00, 0x18, 0xb0, 0x61, 0x10, 0x80, 0x34, 0x30, 0x00,
            0x18, 0xb0, 0x00, 0xee, 0x6e, 0x01, 0x00, 0xee, 0x00, 0x00, 0x00, 0x00, 0x05, 0x00, 0x50, 0x70,
            0x20, 0x00, 0x50, 0x70, 0x20, 0x00, 0x60, 0x30, 0x60, 0x00, 0x60, 0x30, 0x60, 0x00, 0x30, 0x60,
            0x30, 0x00, 0x30, 0x60, 0x30, 0x00, 0x20, 0x70, 0x50, 0x00, 0x20, 0x70, 0x50, 0x00, 0x20, 0x70,
            0x70, 0x00, 0x00, 0x20, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x80, 0x00, 0x00, 0x00, 0x00, 0x00, 0xc0, 0x00, 0x00, 0x00, 0x80, 0x80, 0x00,
            0x00, 0xc0, 0x80, 0x80, 0x80, 0xc0, 0x00, 0x80, 0x00, 0x0c, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08,
            0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x0d, 0x0c, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08,
            0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x0d, 0x0a, 0x65, 0x05, 0x05, 0x05, 0x05, 0xe5,
            0x05, 0x05, 0xe5, 0x05, 0x05, 0x05, 0x05, 0xc5, 0x0a, 0x0a, 0x65, 0x05, 0x05, 0x05, 0x05, 0xe5,
            0x05, 0x05, 0xe5, 0x05, 0x05, 0x05, 0x05, 0xc5, 0x0a, 0x0a, 0x05, 0x0c, 0x08, 0x08, 0x0f, 0x05,
            0x0c, 0x0d, 0x05, 0x08, 0x08, 0x08, 0x0d, 0x05, 0x0e, 0x0f, 0x05, 0x0c, 0x08, 0x08, 0x0f, 0x05,
            0x0c, 0x0d, 0x05, 0x08, 0x08, 0x08, 0x0d, 0x05, 0x0a, 0x0a, 0x05, 0x0a, 0x65, 0x06, 0x05, 0x95,
            0x0a, 0x0a, 0x35, 0x05, 0x05, 0xc5, 0x0a, 0x35, 0x05, 0x05, 0x95, 0x0a, 0x65, 0x05, 0x05, 0x95,
            0x0a, 0x0a, 0x35, 0x05, 0x06, 0xc5, 0x0a, 0x05, 0x0a, 0x0a, 0x05, 0x0f, 0x05, 0x08, 0x08, 0x08,
            0x08, 0x08, 0x0c, 0x08, 0x0f, 0x05, 0x08, 0x08, 0x08, 0x08, 0x08, 0x0f, 0x05, 0x08, 0x08, 0x0c,
            0x08, 0x08, 0x08, 0x08, 0x0f, 0x05, 0x0f, 0x05, 0x0a, 0x0a, 0x75, 0x05, 0xb5, 0x05, 0x05, 0x05,
            0x05, 0xc5, 0x0a, 0x65, 0x05, 0xb5, 0x05, 0xe5, 0x05, 0x05, 0xe5, 0x05, 0xb5, 0x05, 0xc5, 0x0a,
            0x65, 0x05, 0x05, 0x05, 0x05, 0xb5, 0x05, 0xd5, 0x0a, 0x0a, 0x05, 0x0c, 0x08, 0x08, 0x08, 0x08,
            0x0d, 0x05, 0x0f, 0x05, 0x0c, 0x08, 0x0f, 0x05, 0x08, 0x0f, 0x05, 0x08, 0x08, 0x0d, 0x05, 0x0f,
            0x05, 0x0c, 0x08, 0x08, 0x08, 0x08, 0x0d, 0x05, 0x0a, 0x0f, 0x05, 0x0f, 0x65, 0x05, 0x05, 0xc5,
            0x0a, 0x35, 0xe5, 0x95, 0x0a, 0x65, 0x05, 0xb0, 0x05, 0x05, 0xb5, 0x05, 0xc5, 0x0a, 0x35, 0xe5,
            0x95, 0x0a, 0x65, 0x05, 0x05, 0xc5, 0x0f, 0x05, 0x0f, 0x07, 0x74, 0x05, 0xd5, 0x08, 0x0f, 0x05,
            0x0e, 0x0f, 0x05, 0x08, 0x0f, 0x05, 0x0c, 0x08, 0x08, 0x08, 0x08, 0x0d, 0x05, 0x08, 0x0f, 0x05,
            0x08, 0x0f, 0x05, 0x08, 0x0f, 0x75, 0x05, 0xd4, 0x07, 0x0a, 0x05, 0x0a, 0x35, 0x05, 0x05, 0xf5,
            0x05, 0x05, 0xb5, 0x05, 0x05, 0xd5, 0x08, 0x08, 0x0d, 0x0c, 0x08, 0x0f, 0x75, 0x05, 0x05, 0xb5,
            0x05, 0x05, 0xf5, 0x05, 0x05, 0x95, 0x0a, 0x05, 0x0a, 0x0a, 0x05, 0x08, 0x08, 0x08, 0x0d, 0x05,
            0x0c, 0x08, 0x08, 0x08, 0x0d, 0x35, 0x05, 0xc5, 0x0a, 0x0a, 0x65, 0x05, 0x95, 0x0c, 0x08, 0x08,
            0x08, 0x0d, 0x05, 0x0c, 0x08, 0x08, 0x0f, 0x05, 0x0a, 0x0a, 0x75, 0x05, 0x06, 0xc5, 0x0a, 0x05,
            0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x0f, 0x05, 0x08, 0x0f, 0x05, 0x08, 0x08, 0x08, 0x08, 0x08,
            0x08, 0x0f, 0x05, 0x0a, 0x65, 0x06, 0x05, 0xd5, 0x0a, 0x0a, 0x05, 0x0c, 0x0d, 0x05, 0x0a, 0x35,
            0x05, 0x05, 0x05, 0x05, 0xe5, 0x05, 0x05, 0xf5, 0x05, 0x05, 0xf5, 0x05, 0x05, 0xe5, 0x05, 0x05,
            0x05, 0x05, 0x95, 0x0a, 0x05, 0x0c, 0x0d, 0x05, 0x0a, 0x0a, 0x05, 0x08, 0x0f, 0x05, 0x08, 0x08,
            0x08, 0x08, 0x08, 0x0f, 0x05, 0x0c, 0x0d, 0x05, 0x08, 0x0f, 0x05, 0x0c, 0x0d, 0x05, 0x08, 0x08,
            0x08, 0x08, 0x08, 0x0f, 0x05, 0x08, 0x0f, 0x05, 0x0a, 0x0a, 0x35, 0x05, 0x05, 0xb5, 0x05, 0x05,
            0x05, 0x05, 0x05, 0x05, 0x95, 0x0a, 0x0a, 0x35, 0x05, 0x05, 0x95, 0x0a, 0x0a, 0x35, 0x05, 0x05,
            0x05, 0x05, 0x05, 0x05, 0xb5, 0x05, 0x05, 0x95, 0x0a, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08,
            0x08, 0x08, 0x08, 0x08, 0x08, 0x0f, 0x08, 0x08, 0x08, 0x08, 0x08, 0x0f, 0x08, 0x08, 0x08, 0x08,
            0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x0f, 0x3c, 0x42, 0x99, 0x99, 0x42, 0x3c, 0x01,
            0x10, 0x0f, 0x78, 0x84, 0x32, 0x32, 0x84, 0x78, 0x00, 0x10, 0xe0, 0x78, 0xfc, 0xfe, 0xfe, 0x84,
            0x78, 0x00, 0x10, 0xe0
        )
    )
}
