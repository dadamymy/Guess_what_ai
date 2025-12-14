package com.example.guess_what_ai

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.drawToBitmap
import com.example.guess_what_ai.databinding.ActivityDrawBinding
class DrawActivity : AppCompatActivity() , View.OnClickListener{
    private lateinit var binding: ActivityDrawBinding

    private val viewModel : DrawViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        observeViewMode()
    }
    fun initView(){
        //接收資料
        val mode = intent.getStringExtra("MODE")
        viewModel.initGame(mode)
        //設定按鈕
        val clearBtn : Button = binding.buttonClear
        val finishBtn : Button = binding.buttonFinish
        clearBtn.setOnClickListener(this)
        finishBtn.setOnClickListener(this)
    }

    private fun observeViewMode(){
        viewModel.currentTopic.observe(this){topic->
            binding.TextTopic.text = "題目是:$topic"
        }
        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                binding.TextTopic.text = "AI 出題中..." // 也可以改文字提示
                binding.buttonFinish.isEnabled = false
            } else {
                binding.buttonFinish.isEnabled = true
            }
        }
        viewModel.gameResult.observe(this) { (isCorrect, topic, comment) ->
            showResultDialog(isCorrect, topic, comment)
        }
    }

    override fun onClick(p0: View?) {
        if (p0?.id == binding.buttonClear.id){
            binding.drawingView.clearCanvas()
        }
        else if (p0?.id == binding.buttonFinish.id){
            val bitmap = binding.drawingView.drawToBitmap()
            viewModel.submitDrawing(bitmap)
        }
    }

    fun showResultDialog(isCorrect: Boolean, topic: String, aiComment: String){
        val dialog = ResultDialog()
        val args = Bundle().apply {
            putBoolean("IS_CORRECT", isCorrect)
            putString("TOPIC", topic)
            putString("AI_COMMENT", aiComment)
        }
        dialog.arguments = args
        dialog.show(supportFragmentManager, "RESULT_DIALOG")
    }
}