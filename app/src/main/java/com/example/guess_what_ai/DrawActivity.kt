package com.example.guess_what_ai

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.guess_what_ai.databinding.ActivityDrawBinding
class DrawActivity : AppCompatActivity() , View.OnClickListener {
    private lateinit var binding: ActivityDrawBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}