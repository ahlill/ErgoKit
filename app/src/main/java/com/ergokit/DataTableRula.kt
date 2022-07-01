package com.ergokit

object DataTableRula {

    object TableA {
        private val wristScore1 = mapOf(
            1 to listOf(1, 2),
            2 to listOf(2, 2),
            3 to listOf(2, 3),
            4 to listOf(2, 3),
            5 to listOf(3, 3),
            6 to listOf(3, 4),
            7 to listOf(3, 3),
            8 to listOf(3, 4),
            9 to listOf(4, 4),
            10 to listOf(4, 4),
            11 to listOf(4, 4),
            12 to listOf(4, 4),
            13 to listOf(5, 5),
            14 to listOf(5, 6),
            15 to listOf(6, 6),
            16 to listOf(7, 7),
            17 to listOf(8, 8),
            18 to listOf(9, 9)
        )

        private val wristScore2 = mapOf(
            1 to listOf(2, 2),
            2 to listOf(2, 2),
            3 to listOf(3, 3),
            4 to listOf(3, 3),
            5 to listOf(3, 3),
            6 to listOf(4, 4),
            7 to listOf(4, 4),
            8 to listOf(4, 4),
            9 to listOf(4, 4),
            10 to listOf(4, 4),
            11 to listOf(4, 4),
            12 to listOf(4, 5),
            13 to listOf(5, 5),
            14 to listOf(6, 6),
            15 to listOf(6, 7),
            16 to listOf(7, 7),
            17 to listOf(8, 8),
            18 to listOf(9, 9)
        )

        private val wristScore3 = mapOf(
            1 to listOf(2, 3),
            2 to listOf(3, 3),
            3 to listOf(3, 3),
            4 to listOf(3, 4),
            5 to listOf(3, 4),
            6 to listOf(4, 4),
            7 to listOf(4, 4),
            8 to listOf(4, 4),
            9 to listOf(4, 5),
            10 to listOf(4, 5),
            11 to listOf(4, 5),
            12 to listOf(5, 5),
            13 to listOf(5, 6),
            14 to listOf(6, 7),
            15 to listOf(7, 7),
            16 to listOf(7, 8),
            17 to listOf(8, 9),
            18 to listOf(9, 9)
        )

        private val wristScore4 = mapOf(
            1 to listOf(3, 3),
            2 to listOf(3, 3),
            3 to listOf(4, 4),
            4 to listOf(4, 4),
            5 to listOf(4, 4),
            6 to listOf(5, 5),
            7 to listOf(5, 5),
            8 to listOf(5, 5),
            9 to listOf(5, 5),
            10 to listOf(5, 5),
            11 to listOf(5, 5),
            12 to listOf(6, 6),
            13 to listOf(6, 7),
            14 to listOf(7, 7),
            15 to listOf(7, 8),
            16 to listOf(8, 9),
            17 to listOf(9, 9),
            18 to listOf(9, 9)
        )

        val tableA = arrayOf(wristScore1, wristScore2, wristScore3, wristScore4)
    }

    object TableB {
        private val trunkPostureScore1 = mapOf(
            1 to listOf(1, 3),
            2 to listOf(2, 3),
            3 to listOf(3, 3),
            4 to listOf(5, 5),
            5 to listOf(7, 7),
            6 to listOf(8, 8)
        )

        private val trunkPostureScore2 = mapOf(
            1 to listOf(2, 3),
            2 to listOf(2, 3),
            3 to listOf(3, 4),
            4 to listOf(5, 6),
            5 to listOf(7, 7),
            6 to listOf(8, 8)
        )

        private val trunkPostureScore3 = mapOf(
            1 to listOf(3, 4),
            2 to listOf(4, 5),
            3 to listOf(4, 5),
            4 to listOf(6, 7),
            5 to listOf(7, 8),
            6 to listOf(8, 8)
        )

        private val trunkPostureScore4 = mapOf(
            1 to listOf(5, 5),
            2 to listOf(5, 5),
            3 to listOf(5, 6),
            4 to listOf(7, 7),
            5 to listOf(8, 8),
            6 to listOf(8, 9)
        )

        private val trunkPostureScore5 = mapOf(
            1 to listOf(6, 6),
            2 to listOf(6, 7),
            3 to listOf(6, 7),
            4 to listOf(7, 7),
            5 to listOf(8, 8),
            6 to listOf(9, 9)
        )
        private val trunkPostureScore6 = mapOf(
            1 to listOf(7, 7),
            2 to listOf(7, 7),
            3 to listOf(7, 7),
            4 to listOf(8, 8),
            5 to listOf(8, 8),
            6 to listOf(9, 9)
        )

        val tableB = arrayOf(trunkPostureScore1, trunkPostureScore2, trunkPostureScore3, trunkPostureScore4, trunkPostureScore5, trunkPostureScore6)
    }

    object TableC {
        val tableC = mapOf(
            1 to listOf(1, 2, 3, 3, 4, 5, 5),
            2 to listOf(2, 2, 3, 4, 4, 5, 5),
            3 to listOf(3, 3, 3, 4, 4 ,5, 6),
            4 to listOf(3, 3, 3, 4, 5, 6, 6),
            5 to listOf(4, 4, 4, 5, 6, 7, 7),
            6 to listOf(4, 4, 5, 6, 6, 7, 7),
            7 to listOf(5, 5, 6, 6, 7, 7, 7),
            8 to listOf(5, 5, 6, 7, 7, 7, 7)
        )
    }
}