package com.example.nailshop.ui.splash

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.nailshop.R
import com.example.nailshop.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash_nail.*

class SplashNailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_nail)
        (imageSplash.background as AnimationDrawable).start()
        Handler().postDelayed({
            startActivity(MainActivity.getIntent(this))
        }, 2000)
    }
}