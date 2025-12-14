package com.example.guess_what_ai

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.guess_what_ai.databinding.ActivitySelectModeBinding

class SelectModeActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySelectModeBinding

    // 用於 Intent 的 Key
    companion object {
        const val EXTRA_MODE = "MODE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 假設您已設定 View Binding，對應 activity_select_mode.xml
        binding = ActivitySelectModeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAI.setOnClickListener(this)
        binding.buttonUser.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.buttonAI.id -> {
                // 跳轉到 DrawActivity，並傳遞選擇的模式
                val intent = Intent(this, DrawActivity::class.java).apply {
                    putExtra(EXTRA_MODE, "AI")
                }
                startActivity(intent)
                finish() // 模式選擇完成後，關閉此頁面
            }
            binding.buttonUser.id -> {
                showCustomTopicDialog()
            }
            else -> return
        }
    }

    fun showCustomTopicDialog(){
        CustomTopicDialog().show(supportFragmentManager, "CustomTopic_Dialog")
    }
}