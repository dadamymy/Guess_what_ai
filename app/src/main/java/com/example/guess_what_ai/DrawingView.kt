package com.example.guess_what_ai

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * 自訂的繪圖 View，處理觸控事件並繪製路徑。
 */
class DrawingView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    // 繪圖使用的路徑 (Path) 集合
    private var drawPath: Path = Path()
    // 繪圖的畫筆 (Paint)
    private var drawPaint: Paint = Paint()
    // 畫布 (Canvas)
    private lateinit var drawCanvas: Canvas
    // 儲存繪圖結果的位圖 (Bitmap)
    private lateinit var canvasBitmap: Bitmap

    // 筆刷顏色
    private var paintColor = Color.BLACK
    // 筆刷寬度
    private var brushSize = 10f

    init {
        setupDrawing()
    }

    // 初始化畫筆的設定
    private fun setupDrawing() {
        drawPaint.color = paintColor
        drawPaint.isAntiAlias = true // 抗鋸齒
        drawPaint.strokeWidth = brushSize // 筆刷寬度
        drawPaint.style = Paint.Style.STROKE // 僅描邊
        drawPaint.strokeJoin = Paint.Join.ROUND // 線段連接處圓角
        drawPaint.strokeCap = Paint.Cap.ROUND // 線段末端圓角
    }

    // 當 View 的大小改變時被呼叫，用於初始化畫布和位圖
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // 創建一個與 View 大小相同的位圖
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        // 創建一個畫布，使其在該位圖上繪圖
        drawCanvas = Canvas(canvasBitmap)
        // 畫布背景色為白色
        drawCanvas.drawColor(Color.WHITE)
    }

    // 繪製 View 內容
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 繪製儲存在位圖上的所有線條
        canvas.drawBitmap(canvasBitmap, 0f, 0f, drawPaint)
        // 繪製當前正在畫的路徑 (即手指還沒抬起時的路徑)
        canvas.drawPath(drawPath, drawPaint)
    }

    // 處理觸控事件
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val touchX = event.x
        val touchY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // 手指按下：開始一個新的繪圖路徑
                drawPath.moveTo(touchX, touchY)
            }
            MotionEvent.ACTION_MOVE -> {
                // 手指移動：將當前點連接到路徑中
                drawPath.lineTo(touchX, touchY)
            }
            MotionEvent.ACTION_UP -> {
                // 手指抬起：將完整的路徑繪製到主畫布上，並重設當前路徑
                drawCanvas.drawPath(drawPath, drawPaint)
                drawPath.reset()
            }
            else -> return false
        }
        // 重新繪製 View (觸發 onDraw 呼叫)
        invalidate()
        return true
    }

    /**
     * 清空畫布的方法。
     */
    fun clearCanvas() {
        // 清空儲存繪圖結果的位圖
        // 這裡直接使用 drawColor 搭配清除模式，或者使用 eraseColor
        drawCanvas.drawColor(Color.WHITE)
        // 重新繪製 View
        invalidate()
    }
}