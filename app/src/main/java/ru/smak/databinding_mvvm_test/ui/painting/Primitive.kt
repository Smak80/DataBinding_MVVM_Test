package ru.smak.databinding_mvvm_test.ui.painting

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

abstract class Primitive {
    protected val paint: Paint = Paint()
    var color: Int
        get() = paint.color
        set(value) {
            paint.color = value
        }

    init{
        paint.color = Color.BLACK
    }

    abstract fun paint(canvas: Canvas?)
}
