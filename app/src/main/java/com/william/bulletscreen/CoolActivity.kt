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