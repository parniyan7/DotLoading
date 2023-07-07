package com.parniyan.parniyandotloading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val loadingView = findViewById<LoadingView>(R.id.loadingView)
        loadingView.startLoading()
    }
}