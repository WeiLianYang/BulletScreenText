package com.william.bulletscreen

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.dialog_settings.*


/**
 * @author William
 * @date 11/17/20 5:36 PM
 * Class Comment：
 */
class SettingsDialog : DialogFragment() {

    private var bean: SettingsBean? = null

    companion object {
        @JvmStatic
        fun newInstance(bean: SettingsBean?): SettingsDialog {
            return SettingsDialog().apply {
                arguments = Bundle().apply {
                    putParcelable("bean", bean)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            attributes.apply {
                width = WindowManager.LayoutParams.MATCH_PARENT
                height = WindowManager.LayoutParams.WRAP_CONTENT
                gravity = Gravity.BOTTOM
                dimAmount = 0f
                alpha = 0.8f
            }
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bean = arguments?.getParcelable("bean") as? SettingsBean
        return inflater.inflate(R.layout.dialog_settings, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mDurationSeekBar?.progress = bean?.duration ?: 5000
        mSizeSeekBar?.progress = bean?.size ?: 600

        mTvConfirm?.setOnClickListener {
            bean?.duration = mDurationSeekBar?.progress ?: 5000
            bean?.size = mSizeSeekBar?.progress ?: 600
            listener?.invoke(bean)
            dismiss()
        }

        mRadioGroup?.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_white -> bean?.color = Color.WHITE
                R.id.rb_red -> bean?.color = Color.RED
                R.id.rb_orange -> bean?.color = Color.parseColor("#ffff8800")
                R.id.rb_yellow -> bean?.color = Color.YELLOW
                R.id.rb_green -> bean?.color = Color.GREEN
                R.id.rb_cyan -> bean?.color = Color.CYAN
                R.id.rb_blue -> bean?.color = Color.BLUE
                R.id.rb_purple -> bean?.color = Color.parseColor("#ffaa66cc")
            }
        }
    }

    fun show(manager: FragmentManager) {
        super.show(manager, javaClass.simpleName)
    }

    var listener: ((bean: SettingsBean?) -> Unit)? = null
}