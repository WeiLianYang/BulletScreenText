package com.william.bulletscreen

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bullet_text.*


/**
 * @author William
 * @date 11/16/20 8:29 PM
 * Class Comment：
 */
class BulletActivity : AppCompatActivity() {

    private var animator: ObjectAnimator? = null
    private var showControlView = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bullet_text)

        initAnimator()

        observeEditTextChange()

        observeEvent()
    }

    private fun initAnimator() {
        animator = ObjectAnimator.ofFloat(
            textView, "dx",
            resources.displayMetrics.widthPixels * 1f,
            -textView.getTextWidth()
        ).apply {
            interpolator = LinearInterpolator()
            duration = 5000
            repeatCount = Animation.INFINITE
            repeatMode = ValueAnimator.RESTART
        }
        animator?.start()
    }

    private fun observeEditTextChange() {
        mEtInput?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                textView?.text = s.toString()
                animator?.setFloatValues(
                    resources.displayMetrics.widthPixels * 1f,
                    -textView.getTextWidth()
                )
                val inputTextLength = s.toString().length
                when {
                    inputTextLength < 3 -> {
                        animator?.duration = 2000
                    }
                    inputTextLength < 5 -> {
                        animator?.duration = 3000
                    }
                    else -> {
                        animator?.duration = 5000
                    }
                }
                animator?.start()
            }
        })
    }

    private fun observeEvent() {
        textView?.setOnClickListener {
            if (showControlView) {
                mEtInput.visibility = View.GONE
            } else {
                mEtInput.visibility = View.VISIBLE
            }
            showControlView = !showControlView
        }
    }
}