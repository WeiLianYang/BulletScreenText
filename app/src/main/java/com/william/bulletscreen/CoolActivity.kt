package com.william.bulletscreen

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cool.*
import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt

/**
 * @author William
 * @date 11/18/20 4:48 PM
 * Class Comment：
 */
class CoolActivity : AppCompatActivity() {

    private var timer: Timer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cool)

        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                mClRootView.setBackgroundColor(
                    Color.rgb(
                        Random.nextInt(0..255),
                        Random.nextInt(0..255),
                        Random.nextInt(0..255)
                    )
                )
            }
        }, 0, 80)
    }

    override fun onDestroy() {
        timer?.cancel()
        super.onDestroy()
    }
}