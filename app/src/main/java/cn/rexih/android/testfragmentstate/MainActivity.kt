package cn.rexih.android.testfragmentstate

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val fragment by lazy { MainFragment() }
    val secondFragment by lazy { SecondFragment() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.fl_main, secondFragment)
        beginTransaction.hide(secondFragment)
        beginTransaction.add(R.id.fl_main, fragment)

        beginTransaction.commit()

        btn_main_2.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
        btn_main_3.setOnClickListener {
            startActivity(Intent(this, ThirdActivity::class.java))
        }
        btn_main_frg_1.setOnClickListener {
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.hide(secondFragment)
            beginTransaction.setMaxLifecycle(secondFragment, androidx.lifecycle.Lifecycle.State.STARTED)
            beginTransaction.setMaxLifecycle(fragment, androidx.lifecycle.Lifecycle.State.RESUMED)
            beginTransaction.show(fragment)
            beginTransaction.commitNow()
        }
        btn_main_frg_2.setOnClickListener {
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.hide(fragment)
            beginTransaction.setMaxLifecycle(fragment, androidx.lifecycle.Lifecycle.State.STARTED)
            beginTransaction.setMaxLifecycle(secondFragment, androidx.lifecycle.Lifecycle.State.RESUMED)
            beginTransaction.show(secondFragment)
            beginTransaction.commit()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        btn_main_frg_2.performClick()
    }

}
