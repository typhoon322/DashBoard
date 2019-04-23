package me.androidapp.yanx.dashboard

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * me.androidapp.yanx.dashboard
 * Created by @author YANx on 2019/4/10 11:31 PM.
 * Description ${TODO}
 */
class CakeGraph(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null, 0)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val RADIUS = Utils.dp2px(150f)

    private val ANGLES = floatArrayOf(60f, 45f, 90f, 45f, 120f)
    private val COLORS = intArrayOf(Color.BLUE, Color.GRAY, Color.GREEN, Color.YELLOW, Color.RED)

    init {

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawCake(canvas)
    }

    private val highLightIndex = 2

    private val PULLUP_LENGTH = Utils.dp2px(20f)

    private fun drawCake(canvas: Canvas?) {
        val x = width / 2
        val y = height / 2
        paint.color = Color.BLACK
        canvas?.drawCircle(x.toFloat(), y.toFloat(), Utils.dp2px(1f), paint)
        var currentAngle = 0f
        ANGLES.forEachIndexed { index, fl ->
            paint.color = COLORS[index]
            if (index == highLightIndex) {
                canvas?.save()
                canvas?.translate(
                        (Math.cos(Math.toRadians((currentAngle + fl / 2).toDouble())) * PULLUP_LENGTH).toFloat(),
                        (Math.sin(Math.toRadians((currentAngle + fl / 2).toDouble())) * PULLUP_LENGTH).toFloat()
                )
            }
            canvas?.drawArc(x - RADIUS, y - RADIUS, x + RADIUS, y + RADIUS,
                    currentAngle, fl, true, paint)
            if (index == highLightIndex) {
                canvas?.restore()
            }
            currentAngle += fl
        }
    }
}