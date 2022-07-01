package com.ergokit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ergokit.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private val homeActivityBinding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(homeActivityBinding.root)

        homeActivityBinding.cardReba.setOnClickListener{
            val i = Intent(this, RebaActivity::class.java)
            startActivity(i)
        }
        homeActivityBinding.cardRula.setOnClickListener{
            val i = Intent(this, RulaActivity::class.java)
            startActivity(i)
        }
    }
}