package com.ergokit

import android.content.ContentValues.TAG
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.FrameLayout
import com.ergokit.databinding.ActivityRulaBinding
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class RulaActivity : AppCompatActivity() {

    private val activityRulaBinding by lazy { ActivityRulaBinding.inflate(layoutInflater) }
    var mLoadInters: InterstitialAd? = null

    private val adSize: AdSize
        get() {
            val display = windowManager.defaultDisplay
            val outMetrics = DisplayMetrics()
            display.getMetrics(outMetrics)

            val density = outMetrics.density

            var adWidthPixels = activityRulaBinding.flAdView.width.toFloat()
            if (adWidthPixels == 0f) {
                adWidthPixels = outMetrics.widthPixels.toFloat()
            }

            val adWidth = (adWidthPixels / density).toInt()
            return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityRulaBinding.root)
        supportActionBar?.title = "RULA"

        showBannerAdaptive(
            this,
            getString(R.string.ID_BANNER_RULA),
            adSize,
            activityRulaBinding.flAdView
        )
        loadInters(getString(R.string.ID_INTERS_RULA))


        activityRulaBinding.btnHitung.setOnClickListener {
            showIntersWithAction { calculate() }
        }
    }

    private fun calculate() {
        with(activityRulaBinding) {
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
                val scoreA =
                    DataTableRula.TableA.tableA[mWristPosition - 1][mLowerArm1]?.get(
                        wristTwistPosition - 1
                    )
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
                val scoreB =
                    DataTableRula.TableB.tableB[locateTrunkPosition - 1][locateNeckPosition]?.get(
                        legs - 1
                    )
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

                val scoreC =
                    DataTableRula.TableC.tableC[wristAndArmScore]?.get(neckTrunkLegScore - 1)

                tvHasil.text = scoreC.toString() //scoreC == score RULA
                tvKeterangan.text = when (scoreC) {
                    in 1..2 -> getString(R.string.acceptable_posture)
                    in 3..4 -> getString(R.string.further_investigation_change_mya_be_needed)
                    in 5..6 -> getString(R.string.further_investigation_change_soon)
                    7 -> getString(R.string.investigate_and_implement_change)
                    else -> getString(R.string.error)
                }
            } catch (e: IndexOutOfBoundsException) {
                Log.e(TAG, "error: $e")
            }
        }
    }

    private fun showBannerAdaptive(ctx: Context, id: String, size: AdSize, layout: FrameLayout) {
        val adRequest = AdRequest.Builder()
            .build()
        val adView = AdView(ctx)
        adView.adUnitId = id
        adView.setAdSize(size)
        adView.loadAd(adRequest)
        layout.addView(adView)
    }

    private fun loadInters(idAd: String) {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            this,
            idAd,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    mLoadInters = null
                }

                override fun onAdLoaded(instertitialAd: InterstitialAd) {
                    mLoadInters = instertitialAd
                }
            }
        )
    }

    private fun showIntersWithAction(action: () -> Unit) {
        if (mLoadInters != null) {
            mLoadInters?.fullScreenContentCallback =
                object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        mLoadInters = null
                        action()
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                        mLoadInters = null
                        action()
                    }
                }
            mLoadInters?.show(this)
        } else {
            action()
        }
    }
}