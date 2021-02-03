package com.demo.nerdeyesem.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demo.nerdeyesem.R
import com.demo.nerdeyesem.presentation.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}