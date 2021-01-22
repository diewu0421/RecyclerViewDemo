package com.example.recyclerviewdemo

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import kotlin.jvm.internal.MagicApiIntrinsics

/**
 * 浙江集商优选电子商务有限公司
 * @author zenglw
 * @date   21-1-22 上午10:11
 */
class MyView @JvmOverloads constructor(context: Context, attrSets: AttributeSet? = null, defStyleAttrs: Int = 0)
    : View(context, attrSets, defStyleAttrs) {

    private  val mPaint  by lazy {
        Paint().apply {
            isDither = true
            isAntiAlias = true
            style = Paint.Style.FILL
            strokeWidth = 3f
            textSize = 70f
            shader = LinearGradient(
                0f,
                height.toFloat() / 2,
                width.toFloat(),
                height.toFloat()/ 2,
                intArrayOf(Color.RED, Color.BLUE),
                floatArrayOf(0f,1.0f),
                Shader.TileMode.CLAMP
            )

            ValueAnimator.ofFloat(0f, 1.0f).apply {
                interpolator = AccelerateDecelerateInterpolator()
                duration = 1500
                repeatCount = ValueAnimator.INFINITE
                repeatMode =ValueAnimator.REVERSE
                addUpdateListener {
                    val factor = it.animatedValue as Float
                    Log.e("MyView","factor = $factor")
                    mMatrix.setTranslate(measuredWidth.toFloat() * factor, 0f)
                    shader.setLocalMatrix(mMatrix)
                    invalidate()
                }
            }.start()
        }
    }
    private val mMatrix by lazy { Matrix() }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mPaint.color = Color.RED
        canvas.drawColor(Color.BLACK)
        canvas.drawText("Android绘图小糊涂",0f, measuredHeight /2f + getTextBound(PAINT_STR).height()/ 2,mPaint);
//        canvas.drawRoundRect(0f, 0f , width.toFloat(), height.toFloat(), 10f, 10f, mPaint)
    }

    fun getTextBound(drawText:String): Rect {
        val rect = Rect()
        mPaint.getTextBounds(drawText, 0, drawText.length - 1, rect)
        return rect
    }

    companion object {
        const val PAINT_STR = "Hello World"
    }
}