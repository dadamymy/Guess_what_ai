package com.example.guess_what_ai

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DrawViewModel : ViewModel() {
    // ... 初始化 helper ...
    private val geminiHelper = GeminiHelper()

    // 儲存遊戲結果 (是否答對, 題目, AI評語)
    private val _gameResult = MutableLiveData<Triple<Boolean, String, String>>()
    val gameResult: LiveData<Triple<Boolean, String, String>> = _gameResult
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // 新增：觀察題目變化的 LiveData
    private val _currentTopic = MutableLiveData<String>()
    val currentTopic: LiveData<String> = _currentTopic

    // 初始化遊戲模式
    fun initGame(mode: String?) {
        if (mode == "AI") { // 假設 1 是 AI 出題模式 (MODE_AI)
            fetchAiTopic()
        } else {
            // 玩家自己出題模式 (MODE_USER)

            _currentTopic.value = mode
        }
    }

    // 動作：去跟 AI 要題目
    fun fetchAiTopic() {
        _isLoading.value = true // 開始轉圈圈
        viewModelScope.launch {
            val topic = geminiHelper.getAiTopic()
            _currentTopic.value = topic // 更新題目，View 會收到通知
            _isLoading.value = false // 關閉轉圈圈
        }
    }

    fun submitDrawing(bitmap: Bitmap) {
        val topic = _currentTopic.value ?: return // 如果沒題目就不送出

        _isLoading.value = true
        viewModelScope.launch {
            val (isCorrect, comment) = geminiHelper.verifyDrawing(bitmap, topic)
            // 收到結果，通知 UI
            //_gameResult.value = Triple(isCorrect, currentTopic, comment)
            _isLoading.value = false // 隱藏轉圈圈
        }
    }
}