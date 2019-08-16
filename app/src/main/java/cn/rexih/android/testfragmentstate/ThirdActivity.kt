package cn.rexih.android.testfragmentstate

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_third.*


/**
 *
 * @package cn.rexih.android.testfragmentstate
 * @file ThirdActivity
 * @date 2019/8/16
 * @author huangwr
 * @version %I%, %G%
 */
class ThirdActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        btn_third.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}