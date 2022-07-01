package com.ergokit

object DataTableReba {

    // tableA
    object TableA {
        private val neck1 = mapOf(
            1 to listOf(1, 2, 3, 4),
            2 to listOf(2, 3, 4, 5),
            3 to listOf(2, 4, 5, 6),
            4 to listOf(3, 5, 6, 7),
            5 to listOf(4, 6, 7, 8)
        )

        private val neck2 = mapOf(
            1 to listOf(1, 2, 3, 4),
            2 to listOf(3, 4, 5, 6),
            3 to listOf(4, 5, 6, 7),
            4 to listOf(5, 6, 7, 8),
            5 to listOf(6, 7, 8, 9)
        )

        private val neck3 = mapOf(
            1 to listOf(3, 3, 5, 6),
            2 to listOf(4, 5, 6, 7),
            3 to listOf(5, 6, 7, 8),
            4 to listOf(6, 7, 8, 9),
            5 to listOf(7, 8, 9, 9)
        )

        val tableA = arrayListOf(neck1, neck2, neck3)
    }

    object TableB {
        private val lowerArm1 = mapOf(
            1 to listOf(1, 2, 2),
            2 to listOf(1, 2, 3),
            3 to listOf(3, 4, 5),
            4 to listOf(4, 5, 5),
            5 to listOf(6, 7, 8),
            6 to listOf(7, 8, 8)
        )

        private val lowerArm2 = mapOf(
            1 to listOf(1, 2, 3),
            2 to listOf(2, 3, 4),
            3 to listOf(4, 5, 5),
            4 to listOf(5, 6, 7),
            5 to listOf(7, 8, 8),
            6 to listOf(8, 9, 9)
        )

        val tableB = arrayListOf(lowerArm1, lowerArm2)
    }

    object TableC {
        val scoreC = mapOf(
            1 to listOf(1, 1, 1, 2, 3, 3, 4, 5, 6, 7, 7, 7),
            2 to listOf(1, 2 ,2, 3, 4, 4, 5, 6, 6, 7, 7, 8),
            3 to listOf(2, 3, 3, 3, 4, 5, 6, 7, 7, 8, 8, 8),
            4 to listOf(3, 4, 4, 4, 5, 6, 7, 8, 8, 9, 9, 9),
            5 to listOf(4, 4, 4, 5, 6, 7, 8, 8, 9, 9, 9, 9),
            6 to listOf(6, 6, 6, 7, 8, 8, 9, 9, 10, 10, 10, 10),
            7 to listOf(7, 7, 7, 8, 9, 9, 9, 10, 10, 11, 11, 11),
            8 to listOf(8, 8, 8, 9, 10, 10, 10, 10, 10, 11, 11, 11),
            9 to listOf(9, 9, 9, 10, 10 ,10, 11, 11, 11, 12, 12, 12),
            10 to listOf(10, 10, 10, 11, 11, 11, 11, 12, 12, 12, 12, 12),
            11 to listOf(11, 11, 11, 11, 12, 12, 12, 12, 12, 12, 12, 12),
            12 to listOf(12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12)
        )
    }
}