package com.example.guess_what_ai

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.guess_what_ai.databinding.ActivityDrawBinding
class DrawActivity : AppCompatActivity() , View.OnClickListener{
    private lateinit var binding: ActivityDrawBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val clearBtn : Button = binding.buttonClear
        clearBtn.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        if (p0?.id == R.id.buttonClear){
            binding.drawingView.clearCanvas()
        }
        else if (p0?.id == R.id.buttonFinish){

        }
    }
}