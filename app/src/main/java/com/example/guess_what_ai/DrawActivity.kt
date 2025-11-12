package com.example.guess_what_ai

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.guess_what_ai.databinding.ActivityDrawBinding

class DrawActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDrawBinding
    private lateinit var drawingView: DrawingView
    private var gameMode: Int = SelectModeActivity.MODE_AI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDrawBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 接收遊戲模式
        gameMode = intent.getIntExtra(SelectModeActivity.EXTRA_MODE, SelectModeActivity.MODE_AI)
        Log.d("DrawActivity", "當前遊戲模式: $gameMode")

        drawingView = binding.drawingView

        // 設定按鈕點擊監聽器
        binding.imageButton.setOnClickListener(this) // 反回箭頭

        // ***** 修正 1：還原清除按鈕的監聽器註冊 *****
        // 確保 activity_draw.xml 中 清除按鈕的 ID 是 buttonClear
        binding.buttonClear.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.imageButton.id -> {
                // 跳轉回 SelectModeActivity
                Log.d("DrawActivity", "點擊：返回模式選擇頁")
                val intent = Intent(this, SelectModeActivity::class.java)
                startActivity(intent)
                finish()
            }

            // ***** 修正 2：還原清除按鈕的處理邏輯 *****
            binding.buttonClear.id -> {
                Log.d("DrawActivity", "點擊：清除畫布")
                drawingView.clearCanvas() // 呼叫 DrawingView.kt 中的清除方法
            }

            else -> {}
        }
    }
}