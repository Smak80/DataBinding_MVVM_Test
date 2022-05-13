package ru.smak.databinding_mvvm_test.ui

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var userText = ObservableField("")
    var enabled = ObservableField(true)

    fun btnClick(){
        enabled.set(!enabled.get()!!)
    }
}