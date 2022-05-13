package ru.smak.databinding_mvvm_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.smak.databinding_mvvm_test.ui.MainFragment
import ru.smak.databinding_mvvm_test.ui.PaintFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainBlock, MainFragment.newInstance())
            .replace(R.id.paintBlock, PaintFragment.newInstance())
            .commitNow()
    }
}