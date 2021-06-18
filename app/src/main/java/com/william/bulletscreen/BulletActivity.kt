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

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_bullet_text.*


/**
 * @author William
 * @date 11/16/20 8:29 PM
 * Class Comment：
 */
class BulletActivity : AppCompatActivity() {

    private var animator: ObjectAnimator? = null
    private var showControlView = true
    private var settingsBean: SettingsBean? = SettingsBean(5000, 600, Color.WHITE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bullet_text)

        initLayoutParams()

        initAnimator()

        observeEditTextChange()

        observeEvent()
    }

    private fun initLayoutParams() {
        textView?.apply {
            rotation = 90f
            translationX =
                -(resources.displayMetrics.heightPixels - resources.displayMetrics.widthPixels) / 2f
            layoutParams = ConstraintLayout.LayoutParams(
                resources.displayMetrics.heightPixels,
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
            ).apply {
                topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
            }
        }
    }

    private fun initAnimator() {
        animator = ObjectAnimator.ofFloat(
            textView, "dx",
            resources.displayMetrics.heightPixels * 1f,
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
        mEtInput?.addTextChangedListener(object : SimpleTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                textView?.text = s.toString()
                animator?.setFloatValues(
                    resources.displayMetrics.heightPixels * 1f,
                    -textView.getTextWidth()
                )
                animator?.start()
            }
        })
    }

    private fun observeEvent() {
        textView?.setOnClickListener {
            toggleControlViewVisibility()
        }

        mIvSettings?.setOnClickListener {
            toggleControlViewVisibility()
            val dialog = SettingsDialog.newInstance(settingsBean)
            dialog.listener = {
                settingsBean = it
                textView?.textColor = it?.color ?: Color.WHITE
                textView?.textSize = it?.size?.toFloat() ?: 600f
                animator?.duration = it?.duration?.toLong() ?: 5000
                animator?.setFloatValues(
                    resources.displayMetrics.heightPixels * 1f,
                    -textView.getTextWidth()
                )
                animator?.start()
            }
            dialog.show(supportFragmentManager)
        }
    }

    private fun toggleControlViewVisibility() {
        if (showControlView) {
            mEtInput.visibility = View.GONE
        } else {
            mEtInput.visibility = View.VISIBLE
        }
        showControlView = !showControlView
    }

    override fun onDestroy() {
        animator?.cancel()
        super.onDestroy()
    }
}