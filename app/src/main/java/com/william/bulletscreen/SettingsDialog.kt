package com.william.bulletscreen

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SeekBar
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
        mDurationSeekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                bean?.duration = seekBar?.progress ?: 5000
                Log.d("onStopTrackingTouch", "duration: ${seekBar?.progress}")
            }
        })

        mSizeSeekBar?.progress = bean?.size ?: 600
        mSizeSeekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                bean?.size = seekBar?.progress ?: 600
                Log.d("onStopTrackingTouch", "size: ${seekBar?.progress}")
            }
        })

        mTvConfirm?.setOnClickListener { dismiss() }
    }

    fun show(manager: FragmentManager) {
        super.show(manager, javaClass.simpleName)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        listener?.invoke(bean)
    }

    var listener: ((bean: SettingsBean?) -> Unit)? = null
}