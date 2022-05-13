package ru.smak.databinding_mvvm_test.ui.painting

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.RectF

class Rect(var rect: RectF, color: Color) : Primitive() {

    init{
        this.color = color.toArgb()
    }

    override fun paint(canvas: Canvas?){
        canvas?.drawRect(rect, paint)
    }

    override fun toString() =
        "left=${rect.left} top=${rect.top} right=${rect.right} bottom=${rect.bottom} color=${color}"

}