/*
 * Copyright WeiLianYang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.william.bulletscreen

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View


/**
 * @author William
 * @date 12/2/20 4:17 PM
 * Class Comment：炫彩文本
 */
class DazzleColourTextView : View {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private val mPaint1: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mPaint2: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mPaint3: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mPaint4: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mPaint5: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var mPaintList = listOf(mPaint1, mPaint2, mPaint3, mPaint4, mPaint5)

    var mColorList = arrayListOf(
        Color.WHITE,
        Color.RED,
        Color.rgb(255, 136, 0),
        Color.GREEN,
        Color.BLUE
    )
    var mTextList = arrayListOf("我", "好", "😍️", "你", "!")

    var mTextSize = 400f
        set(value) {
            field = value
            init()
            invalidate()
        }

    var radius = 0f
        set(value) {
            field = value
            init()
            invalidate()
        }

    private fun init() {
        mPaintList.forEachIndexed { index, paint ->
            paint.apply {
                textSize = mTextSize
                color = mColorList[index]
                setShadowLayer(radius, 0f, 0f, color)
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var totalText = ""
        mTextList.forEachIndexed { _, s ->
            totalText += s
        }
        var x = (measuredWidth - getTextWidth(totalText)) / 2
        val y = measuredHeight / 2f + getBaseLineDistance()
        mPaintList.forEachIndexed { index, paint ->
            val text = mTextList[index]
            if (EmojiUtils.isContainEmoji(text)) {
                var emojiRadius = radius
                if (emojiRadius > 50) {
                    emojiRadius = 50f
                }
                paint.setShadowLayer(emojiRadius, 0f, 0f, mColorList[index])
            }
            canvas?.drawText(text, x, y, paint)
            x += getTextWidth(text)
        }
    }

    private fun getTextWidth(text: String): Float = mPaint1.measureText(text)

    private fun getBaseLineDistance(): Float {
        val fontMetrics = mPaint1.fontMetrics
        return (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent
    }

}