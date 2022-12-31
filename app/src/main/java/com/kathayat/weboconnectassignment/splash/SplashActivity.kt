package com.kathayat.weboconnectassignment.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.kathayat.weboconnectassignment.R
import com.kathayat.weboconnectassignment.databinding.ActivitySplashBinding
import com.kathayat.weboconnectassignment.signup.SignUpActivity
import com.kathayat.weboconnectassignment.util.SharedPrefDataStore

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val splash = SharedPrefDataStore.init().getValue(this,"Signed_up")

        Handler(Looper.getMainLooper()).postDelayed({
            if (splash.isNotEmpty()) {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            } else {
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.splashFragmentContainer) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_signUpHomeFragment_to_signUpGetStartedFragment)
            }
        },3000)
    }
}