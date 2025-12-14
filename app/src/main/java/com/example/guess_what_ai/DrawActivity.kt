package com.example.guess_what_ai

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.guess_what_ai.databinding.ActivityDrawBinding
class DrawActivity : AppCompatActivity() , View.OnClickListener{
    private lateinit var binding: ActivityDrawBinding

    private val viewModel : DrawViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }
    fun initView(){
        //接收資料
        val mode = intent.getIntExtra("MODE", 1)
        //設定按鈕
        val clearBtn : Button = binding.buttonClear
        val finishBtn : Button = binding.buttonFinish
        clearBtn.setOnClickListener(this)
        finishBtn.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if (p0?.id == R.id.buttonClear){
            binding.drawingView.clearCanvas()
        }
        else if (p0?.id == R.id.buttonFinish){
            showResultDialog()
        }
    }

    fun showResultDialog(){
        ResultDialog().show(supportFragmentManager, "RESULT_DIALOG")
    }
}