package ru.smak.databinding_mvvm_test.ui

import android.graphics.Color
import android.graphics.RectF
import android.util.Log
import android.util.SizeF
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import ru.smak.databinding_mvvm_test.ui.painting.Primitive
import ru.smak.databinding_mvvm_test.ui.painting.Rect
import java.util.*

class PaintViewModel : ViewModel(){

    val objects = ObservableField<List<Primitive>>(listOf())
    private val r = Random()

    init {
        objects.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                objects.get()?.forEach {
                    Log.d("OBJECT", it.toString())
                }
            }
        })
    }

    fun setRandomFigures(size: SizeF){
        val lst = List<Primitive>(5){
            Rect(
                RectF(
                    r.nextFloat()*size.width,
                    r.nextFloat()*size.height,
                    r.nextFloat()*size.width,
                    r.nextFloat()*size.height,
                ),
                Color.valueOf(
                    r.nextFloat(),
                    r.nextFloat(),
                    r.nextFloat(),
                    r.nextFloat()
                )
            )
        }
        objects.set(lst)
    }

}