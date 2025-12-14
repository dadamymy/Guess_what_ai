package com.example.guess_what_ai

import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import com.google.firebase.ai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GeminiHelper() {
    val model = Firebase.ai(backend = GenerativeBackend.googleAI()).generativeModel("gemini-2.5-flash")

    // 新增：請 AI 出題
    suspend fun getAiTopic(): String = withContext(Dispatchers.IO) {
        try {
            // 為了讓題目多樣化，可以稍微調整 Prompt
            val prompt = "請給我一個適合畫畫遊戲的具體的動物名詞，" +
                    "只要回傳那個名詞就好，不要有任何其他文字或標點符號，請用繁體中文。"
            val response = model.generateContent(prompt)
            return@withContext response.text?.trim() ?: "狗" // 如果失敗預設回傳狗
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext "錯誤" // 或是處理錯誤
        }
    }
    suspend fun verifyDrawing(bitmap: Bitmap, topic: String): Pair<Boolean, String> = withContext(Dispatchers.IO) {
        try {
            val inputContent = content {
                image(bitmap)
                // 這裡的 Prompt 設計很重要，讓 AI 告訴我們結果
                text("這是一個畫畫遊戲。" +
                        "請判斷這張圖畫是甚麼？" +
                        "請嚴格一點。回傳格式必須是一行：" +
                        "一個單字猜這是甚麼（例如：蘋果、狗、雞...）。")
            }

            val response = model.generateContent(inputContent)
            val text = response.text?.trim() ?: "否\n無法辨識"
            Log.d("GeminiDebug", "AI 回覆原始內容: $text")

            // 解析 AI 的回傳
            val lines = text.lines()
            val isCorrect = lines.firstOrNull()?.contains(topic) == true
            val aiComment = lines.getOrNull(0) ?: "AI 正在思考這像什麼..."

            return@withContext Pair(isCorrect, aiComment)
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext Pair(false, "連線發生錯誤")
        }
    }

}