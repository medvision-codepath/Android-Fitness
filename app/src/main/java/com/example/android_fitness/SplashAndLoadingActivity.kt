package com.example.android_fitness
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashAndLoadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        // Simulate a delay before moving to the loading screen
        Handler().postDelayed({
            // Start the LoadingScreenActivity
            startActivity(Intent(this, LoadingScreenActivity::class.java))
            finish()
        }, SPLASH_SCREEN_DELAY)
    }

    companion object {
        private const val SPLASH_SCREEN_DELAY = 2000L // 2 seconds delay for splash screen
    }
}

class LoadingScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loading_screen)

        // Simulate a loading process
        Handler().postDelayed({
            // Proceed to the MainActivity or any other activity
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, LOADING_DELAY)
    }

    companion object {
        private const val LOADING_DELAY = 3000L // 3 seconds delay for loading screen
    }
}
