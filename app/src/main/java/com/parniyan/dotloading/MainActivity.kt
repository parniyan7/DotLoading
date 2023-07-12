package com.parniyan.dotloading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.parniyan.parniyandotloading.LoadingView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val loadingView = findViewById<LoadingView>(R.id.loadingView)
        loadingView.startLoading()
    }
}