package com.william.bulletscreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author William
 * @date 11/16/20 8:48 PM
 * Class Comment：
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mButtonBullet?.setOnClickListener {
            startActivity(Intent(this, BulletActivity::class.java))
        }
    }
}