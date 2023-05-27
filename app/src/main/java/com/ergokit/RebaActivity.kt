package com.ergokit

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.DisplayMetrics
import android.util.Log
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import com.ergokit.databinding.ActivityRebaBinding
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class RebaActivity : AppCompatActivity() {
    private val activityRebaBinding by lazy { ActivityRebaBinding.inflate(layoutInflater) }
    var mLoadInters: InterstitialAd? = null

    private val adSize: AdSize
        get() {
            val display = windowManager.defaultDisplay
            val outMetrics = DisplayMetrics()
            display.getMetrics(outMetrics)

            val density = outMetrics.density

            var adWidthPixels = activityRebaBinding.flAdView.width.toFloat()
            if (adWidthPixels == 0f) {
                adWidthPixels = outMetrics.widthPixels.toFloat()
            }

            val adWidth = (adWidthPixels / density).toInt()
            return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityRebaBinding.root)

//        adMob()
        showBannerAdaptive(
            this,
            getString(R.string.ID_BANNER_REBA),
            adSize,
            activityRebaBinding.flAdView
        )
        loadInters(getString(R.string.ID_INTERS_REBA))

        supportActionBar?.title = "REBA"

        with(activityRebaBinding) {
            cbReba1a1.setOnClickListener {
                cbReba1a2.isChecked = false
            }
            cbReba1a2.setOnClickListener {
                cbReba1a1.isChecked = false
            }

            cbReba2a1.setOnClickListener {
                cbReba2a2.isChecked = false
            }
            cbReba2a2.setOnClickListener {
                cbReba2a1.isChecked = false
            }

            cbReba3a1.setOnClickListener {
                cbReba3a2.isChecked = false
            }
            cbReba3a2.setOnClickListener {
                cbReba3a1.isChecked = false
            }

            btnHitung.setOnClickListener {
                showIntersWithAction { calculate() }
            }
        }
    }

    private fun calculate() {
        try {
            // Area A
            //step 1
            with(activityRebaBinding) {
                val locateNeckPosition =
                    when (spLocateNeckPosition.selectedItemId.toInt() + 1) {
                        1 -> 1
                        2 -> 2
                        3 -> 2
                        else -> 0
                    }

                //step 1a
                var extension1 = 0
                if (cbReba1a1.isChecked) extension1++
                if (cbReba1a2.isChecked) extension1++

                val mLocateNeckPosition = locateNeckPosition + extension1

                //step 2
                val locateTrunkPosition =
                    when (spLocateTrunkPosition.selectedItemId.toInt() + 1) {
                        1 -> 1
                        2 -> 2
                        3 -> 2
                        4 -> 3
                        5 -> 4
                        else -> 0
                    }
                //step 2a
                var extension2 = 0
                if (cbReba2a1.isChecked) extension2++
                if (cbReba2a2.isChecked) extension2++

                val mLocateTrunkPosition = locateTrunkPosition + extension2

                //step 3
                val legs = when (spLegs.selectedItemId.toInt() + 1) {
                    1 -> 1
                    2 -> 2
                    else -> 0
                }
                var extension3 = 0
                if (cbReba3a1.isChecked) extension3++
                if (cbReba3a2.isChecked) extension3 += 2

                val mLegs = legs + extension3

                //step 4
                val postureTableA: Int =
                    DataTableReba.TableA.tableA[mLocateNeckPosition - 1][mLocateTrunkPosition]?.get(
                        mLegs - 1
                    )
                        ?: 0 //5

                //step 5
                val loadScore = when (spLoadScore.selectedItemId.toInt() + 1) {
                    1 -> 0
                    2 -> 1
                    3 -> 2
                    else -> 0
                }
                var extension5 = 0
                if (cbReba5a1.isChecked) extension5++

                val mLoadScore = loadScore + extension5

                //step 6
                val scoreA = postureTableA + mLoadScore //7

// Area B
                //step 7
                val upperArm = when (spUpperArm.selectedItemId.toInt() + 1) {
                    1 -> 1
                    2 -> 2
                    3 -> 2
                    4 -> 3
                    5 -> 4
                    else -> 0
                }
                var extension7 = 0
                if (cbReba7a1.isChecked) extension7++
                if (cbReba7a2.isChecked) extension7++
                if (cbReba7a3.isChecked) extension7--

                val mUpperArm = upperArm + extension7

                //step 8
                val lowerArm = when (spLowerArm.selectedItemId.toInt() + 1) {
                    1 -> 1
                    in 2..3 -> 2
                    else -> 0
                }

                //step 9
                val wristPosition = when (spWrist.selectedItemId.toInt() + 1) {
                    1 -> 1
                    2 -> 2
                    else -> 0
                }
                var extension9 = 0
                if (cbReba9a1.isChecked) extension9++

                val mWristPosition = wristPosition + extension9

                //step 10
                val postureTableB =
                    DataTableReba.TableB.tableB[lowerArm - 1][mUpperArm]?.get(mWristPosition - 1)
                        ?: 0 //4

                //step 11
                val couplingScore = when (spCouplingScore.selectedItemId.toInt() + 1) {
                    1 -> 0
                    2 -> 1
                    3 -> 2
                    4 -> 3
                    else -> 0
                }

                //step 12
                val scoreB = postureTableB + couplingScore //7

                //step 13
                val scoreC = DataTableReba.TableC.scoreC[scoreA]?.get(scoreB - 1) ?: 0 //9

                //step 14
                var activityScore = 0
                if (cbRebaActivityScore1.isChecked) activityScore++
                if (cbRebaActivityScore2.isChecked) activityScore++
                if (cbRebaActivityScore3.isChecked) activityScore++

                //step 15
                val scoreReba = scoreC + activityScore //10
                tvHasil.text = scoreReba.toString()
                tvKeterangan.text = when (scoreReba) {
                    1 -> getString(R.string.low_risk)
                    in 2..3 -> getString(R.string.low_risk)
                    in 4..7 -> getString(R.string.medium_risk)
                    in 8..10 -> getString(R.string.high_risk)
                    in 11..15 -> getString(R.string.very_high_risk)
                    else -> getString(R.string.error)
                }
            }
        } catch (e: IndexOutOfBoundsException) {
            Log.e(TAG, "errornya adalah: $e")
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