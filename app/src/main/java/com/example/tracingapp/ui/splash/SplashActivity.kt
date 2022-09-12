package com.example.tracingapp.ui.splash

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.tracingapp.R
import com.example.tracingapp.ui.WelcomeActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.on_primary_dark)))

        val welcome_back = findViewById<TextView>(R.id.tv_welcome_back)
        val app_name = findViewById<TextView>(R.id.tv_app_name)
        val app_logo = findViewById<ImageView>(R.id.iv_app_logo)

        // Animation
        val animation = AnimationUtils
            .loadAnimation(
                this@SplashActivity,  // getApplicationContext(),
                // slide file is an anim folder
                R.anim.fade_in
            )
        app_logo.startAnimation(animation)
        welcome_back.startAnimation(animation)
        app_name.startAnimation(animation)

        // Splash screen starts the MainActivity with delay
        Handler().postDelayed({ // auto-redirection from splash to main
            val i = Intent(this@SplashActivity, WelcomeActivity::class.java)
            startActivity(i)
            overridePendingTransition(R.anim.slide_in_left, R.anim.fade_out)
            finish()
        }, 3000) // delay for 3000 milliseconds or 3 seconds
    }
}