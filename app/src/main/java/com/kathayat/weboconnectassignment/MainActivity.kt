package com.kathayat.weboconnectassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kathayat.weboconnectassignment.databinding.ActivityMainBinding
import com.kathayat.weboconnectassignment.splash.SplashActivity
import com.kathayat.weboconnectassignment.util.SharedPrefDataStore

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logoutAccountButton.setOnClickListener {
            SharedPrefDataStore.init().clear()
            startActivity(Intent(this,SplashActivity::class.java))
            finish()
        }
    }
}