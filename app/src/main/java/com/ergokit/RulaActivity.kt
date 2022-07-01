package com.ergokit

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ergokit.databinding.ActivityRulaBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds

class RulaActivity : AppCompatActivity() {

    private val activityRulaBinding by lazy { ActivityRulaBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityRulaBinding.root)
        supportActionBar?.title = "RULA"

        adMob()

        with(activityRulaBinding) {

            btnHitung.setOnClickListener {
                try {

                    //step 1
                    val upperArm = when (spUpperArm.selectedItemId.toInt() + 1) {
                        1 -> 1
                        2 -> 2
                        3 -> 2
                        4 -> 3
                        5 -> 4
                        else -> 0
                    }

                    var extension1 = 0
                    if (cbRula1a1.isChecked) extension1++
                    if (cbRula1a2.isChecked) extension1++
                    if (cbRula1a3.isChecked) extension1--

                    val mUpperArm = upperArm + extension1

                    //step 2
                    val lowerArm = when (spLowerArm.selectedItemId.toInt() + 1) {
                        1 -> 1
                        2 -> 2
                        3 -> 2
                        else -> 0
                    }

                    var extension2 = 0
                    if (cbRula2a1.isChecked) extension2++

                    val mLowerArm = lowerArm + extension2

                    val mLowerArm1 = mLowerArm + (3 * (mUpperArm - 1))

                    //step 3
                    val wristPosition = when (spWrist.selectedItemId.toInt() + 1) {
                        1 -> 1
                        2 -> 2
                        3 -> 3
                        else -> 0
                    }
                    var extension3 = 0
                    if (cbRula3a1.isChecked) extension3++

                    val mWristPosition = wristPosition + extension3

                    //step 4
                    val wristTwistPosition = when (spWristTwist.selectedItemId.toInt() + 1) {
                        1 -> 1
                        2 -> 2
                        else -> 0
                    }

                    //step 5
                    val scoreA = DataTableRula.TableA.tableA[mWristPosition - 1][mLowerArm1]?.get(wristTwistPosition - 1)
                        ?: 0

                    //step 6
                    var addMuscleScore = 0
                    if (cbRulaAddMuscleUseScore.isChecked) addMuscleScore++

                    //step 7
                    val loadScore = when (spLoadScore.selectedItemId.toInt() + 1) {
                        1 -> 0
                        2 -> 1
                        3 -> 2
                        4 -> 3
                        else -> 0
                    }

                    //step 8
                    var wristAndArmScore = scoreA + addMuscleScore + loadScore

                    //step 9
                    val locateNeckPosition = when (spLocateNeckPosition.selectedItemId.toInt() + 1) {
                        1 -> 1
                        2 -> 2
                        3 -> 3
                        4 -> 4
                        else -> 0
                    }
                    var extension9 = 0
                    if (cbRula9a1.isChecked) extension9++
                    if (cbRula9a2.isChecked) extension9++

                    val mLocateNeckPosition = locateNeckPosition + extension9

                    //step 10
                    val locateTrunkPosition = when (spLocateTrunkPosition.selectedItemId.toInt() + 1) {
                        1 -> 1
                        2 -> 2
                        3 -> 3
                        4 -> 4
                        else -> 0
                    }
                    var extension10 = 0
                    if (cbRula10a1.isChecked) extension10++
                    if (cbRula10a2.isChecked) extension10++

                    val mLocateTrunkPosition = locateTrunkPosition + extension10

                    //step 11
                    val legs = when (spLegs.selectedItemId.toInt() + 1) {
                        1 -> 1
                        2 -> 2
                        else -> 0
                    }

                    //step 12
                    val scoreB = DataTableRula.TableB.tableB[locateTrunkPosition - 1][locateNeckPosition]?.get(legs - 1)
                        ?: 0

                    //step 13
                    //same as step 6

                    //step 14
                    //same as step 7

                    //step 15
                    var neckTrunkLegScore = scoreB + addMuscleScore + loadScore

                    //step 16
                    if (neckTrunkLegScore > 7) neckTrunkLegScore = 7
                    if (wristAndArmScore > 8) wristAndArmScore = 8

                    val scoreC = DataTableRula.TableC.tableC[wristAndArmScore]?.get(neckTrunkLegScore - 1)

                    tvHasil.text = scoreC.toString() //scoreC == score RULA
                    tvKeterangan.text = when (scoreC) {
                        in 1..2 -> "Acceptable Posture"
                        in 3..4 -> "Further Investigation, Change May Be Needed"
                        in 5..6 -> "Further investigation, Change Soon"
                        7 -> "Investigate and Implement Change"
                        else -> "Error"
                    }
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "error: $e")
                }
            }
        }
    }

    private fun adMob() {
        MobileAds.initialize(
            this
        ) { }

        val adRequest: AdRequest = AdRequest.Builder().build()
        activityRulaBinding.adView.loadAd(adRequest)
    }
}