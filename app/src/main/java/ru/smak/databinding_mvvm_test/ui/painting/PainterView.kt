package ru.smak.databinding_mvvm_test.ui.painting

import android.annotation.SuppressLint
import android.app.Notification
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.databinding.*
import java.util.*

class PainterView(context: Context, attr: AttributeSet?):
    View(context, attr) {
    constructor(context: Context) : this(context, null)

    private val measureListener = mutableListOf<(Float, Float)->Unit>()
    fun addMeasureListener(l: (Float, Float)->Unit) = measureListener.add(l)
    fun removeMeasureListener(l: (Float, Float)->Unit) = measureListener.remove(l)

    private val r = Random()
    private val bg = Paint()
    private val tempFg = Paint()
    private var point1: PointF? = null
    private var point2: PointF? = null
        set(value) {
            field = value
            point1?.let{ p1->
                field?.let { p2->
                    rect = RectF(p1.x, p1.y, p2.x, p2.y)
                }
                if (field == null) rect = null
            }
            invalidate()
        }

    private var rect: RectF? = null
    private var color: Color

    private var _objects = ObservableField<MutableList<Primitive>>(mutableListOf())
        set(value) {
            field = value
            invalidate()
        }

    init{
        color = Color()
        bg.color = Color.rgb(255, 255, 225)
        changeColor()
        tempFg.color = color.toArgb()
    }

    fun changeColor(){
        color = Color.valueOf(
            r.nextFloat(),
            r.nextFloat(),
            r.nextFloat(),
            r.nextFloat(),
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPaint(bg)
        _objects.get()?.let { obj ->
            obj.forEach {
                it.paint(canvas)
            }
        }
        rect?.let{
            tempFg.color = color.toArgb()
            canvas?.drawRect(it, tempFg)
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        measureListener.forEach { it((right-left).toFloat(), (bottom-top).toFloat()) }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action){
            MotionEvent.ACTION_DOWN -> {
                point1 = PointF(event.x, event.y)
            }
            MotionEvent.ACTION_MOVE ->{
                point2 = PointF(event.x, event.y)
            }
            MotionEvent.ACTION_UP ->{
                point2 = null
                changeColor()
                invalidate()
            }
        }
        return true
    }

    companion object{
        @BindingAdapter("objects")
        @JvmStatic fun setObjects(view: PainterView, newValue: List<Primitive>) {
            if (view._objects != newValue) {
                view._objects.set(newValue.toMutableList())
                view.invalidate()
            }
        }

        @InverseBindingAdapter(attribute = "objects")
        @JvmStatic fun getObjects(view: PainterView) : List<Primitive> {
            return view._objects.get()?.toList() ?: listOf()
        }

        @SuppressLint("ClickableViewAccessibility")
        @BindingAdapter("app:objectsAttrChanged")
        @JvmStatic fun setListeners(
            view: PainterView,
            attrChange: InverseBindingListener
        ) {
            // Set a listener for click, focus, touch, etc.
            view.setOnTouchListener { v, motionEvent ->
                if (motionEvent.action == MotionEvent.ACTION_UP){
                    val o = view._objects.get() ?: mutableListOf()
                    view.rect?.let{ o.add(Rect(it, view.color)) }
                    view._objects.set(o)
                    attrChange.onChange()
                }
                false
            }
        }
    }
}
