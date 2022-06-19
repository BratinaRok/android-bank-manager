package com.example.myapplication

import android.app.Activity
import android.view.View
import android.widget.Button
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.MainScreenBinding
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(::class)
class MainScreenTest

@Test
fun checkIfLogInButtonExists () {
    val result = MainScreenBinding.bind()

    }

private val fragment1Button: Button by lazy {
    activity.findViewByString("fragment1Button")
}



}