package com.william.bulletscreen

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View


/**
 * @author William
 * @date 11/15/20 7:16 PM
 * Class Comment：
 */
class BulletTextView : View {

    private val defaultText = "喜欢就给个star吧！"

    var text = defaultText
        set(value) {
            field = if (value.isBlank()) {
                defaultText
            } else {
                value
            }
        }

    var textSize = 600f
        set(value) {
            field = value
            mPaint.textSize = value
        }

    var textColor = Color.WHITE
        set(value) {
            field = value
            mPaint.color = value
        }

    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var dx = 0f
        set(value) {
            field = value
            invalidate()
        }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        mPaint.color = textColor
        mPaint.textSize = textSize
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val x = dx
        val y = measuredHeight / 2f + getBaseLineDistance()
        canvas?.drawText(text, x, y, mPaint)
    }

    /**
     * 获取文本宽度
     */
    fun getTextWidth(): Float = mPaint.measureText(text)

    /**
     * 获取文本中轴线到基线的距离
     */
    private fun getBaseLineDistance(): Float {
        val fontMetrics = mPaint.fontMetrics
        return (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent
    }

}