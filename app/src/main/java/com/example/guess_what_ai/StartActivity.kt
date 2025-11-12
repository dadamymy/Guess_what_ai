package com.example.guess_what_ai

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.guess_what_ai.databinding.ActivityMainBinding

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // 假設您的 Binding 是 ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 點擊開始按鈕，跳轉到模式選擇頁
        binding.buttonStart.setOnClickListener {
            val intent = Intent(this, SelectModeActivity::class.java)
            startActivity(intent)
        }
    }
}