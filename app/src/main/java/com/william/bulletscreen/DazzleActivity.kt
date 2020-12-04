package com.william.bulletscreen

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.view.animation.Animation
import android.widget.EditText
import android.widget.SeekBar
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_dazzle_text.*


/**
 * @author William
 * @date 11/16/20 8:29 PM
 * Class Comment：
 */
class DazzleActivity : AppCompatActivity() {

    private var animator: ObjectAnimator? = null
    private var showControlView = true
    private var editTextList: ArrayList<EditText?>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dazzle_text)

        initLayoutParams()

        initAnimator()

        observeEditTextChange()

        observeEvent()
    }

    private fun initLayoutParams() {
        mDazzleTextView?.apply {
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
            mDazzleTextView, "radius", 0f, 260f
        ).apply {
            duration = 3500
            repeatCount = Animation.INFINITE
            repeatMode = ValueAnimator.REVERSE
        }
    }

    private fun observeEditTextChange() {
        editTextList = arrayListOf(mEtInput1, mEtInput2, mEtInput3, mEtInput4, mEtInput5)
        editTextList?.forEachIndexed { index, editText ->
            editText?.addTextChangedListener(object : SimpleTextWatcher() {
                override fun afterTextChanged(s: Editable?) {
                    setText(index, s.toString())
                }
            })
        }
    }

    private fun setText(index: Int, text: String) {
        mDazzleTextView?.mTextList?.set(index, text)
        mDazzleTextView?.invalidate()
    }

    private fun setColor(index: Int, @ColorInt color: Int) {
        mDazzleTextView?.mColorList?.set(index, color)
        mDazzleTextView?.invalidate()
    }

    private fun setTextColor(@ColorInt color: Int) {
        editTextList?.forEachIndexed { index, editText ->
            if (editText?.hasFocus() == true) {
                editText.setTextColor(color)
                editText.setHintTextColor(color)
                setColor(index, color)
            }
        }
    }

    private fun observeEvent() {
        mDazzleTextView?.setOnClickListener {
            toggleControlViewVisibility()
        }

        mSizeSeekBar?.setOnSeekBarChangeListener(object : SimpleOnSeekBarChangeListener() {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mDazzleTextView.mTextSize = progress.toFloat()
            }
        })

        mRadioGroup?.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_white -> setTextColor(Color.WHITE)
                R.id.rb_red -> setTextColor(Color.RED)
                R.id.rb_orange -> setTextColor(Color.rgb(255, 136, 0))
                R.id.rb_yellow -> setTextColor(Color.YELLOW)
                R.id.rb_green -> setTextColor(Color.GREEN)
                R.id.rb_cyan -> setTextColor(Color.CYAN)
                R.id.rb_blue -> setTextColor(Color.BLUE)
                R.id.rb_purple -> setTextColor(Color.rgb(170, 102, 204))
            }
        }
    }

    private fun toggleControlViewVisibility() {
        if (showControlView) {
            mGroupControl.visibility = View.GONE
            animator?.start()
        } else {
            mGroupControl.visibility = View.VISIBLE
            animator?.cancel()
            mDazzleTextView?.radius = 0f
        }
        showControlView = !showControlView
    }

    override fun onDestroy() {
        animator?.cancel()
        super.onDestroy()
    }
}