package me.androidapp.yanx.dashboard

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import me.androidapp.yanx.dashboard.Utils.dp2px
import me.androidapp.yanx.dashboard.Utils.getBitmap

/**
 * me.androidapp.yanx.dashboard
 * Created by @author YANx on 2019/4/14 4:02 PM.
 * Description ${TODO}
 */
class ImageTextView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {

    private val IMAGE_WIDTH = dp2px(100f)
    private val PADDING = dp2px(200f)

    private var paint = Paint()
    private var textPaint = Paint()

    private lateinit var image: Bitmap

    private lateinit var fontMetrics: Paint.FontMetrics

    private val floatArray = FloatArray(1)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0) {
        image = getBitmap(getContext().resources, R.drawable.avatar, IMAGE_WIDTH.toInt())
        textPaint.textSize = dp2px(14f)
        paint.textSize = dp2px(16f)
        paint.getFontMetrics(fontMetrics)
    }

    constructor(context: Context?) : this(context, null, 0) {
        image = getBitmap(getContext().resources, R.drawable.avatar, IMAGE_WIDTH.toInt())
        textPaint.textSize = dp2px(14f)
        paint.textSize = dp2px(16f)
        paint.getFontMetrics(fontMetrics)
    }


    private val text = "Interface to global information about an application environment.  This is" +
            " * an abstract class whose implementation is provided by" +
            " * the Android system.  It" +
            " * allows access to application-specific resources and classes, as well as" +
            " * up-calls for application-level operations such as launching activities," +
            " * broadcasting and receiving intents, etc." +
            "Interface to global information about an application environment.  This is" +
            " * an abstract class whose implementation is provided by" +
            " * the Android system.  It" +
            " * allows access to application-specific resources and classes, as well as" +
            " * up-calls for application-level operations such as launching activities," +
            " * broadcasting and receiving intents, etc." +
            "Interface to global information about an application environment.  This is" +
            " * an abstract class whose implementation is provided by" +
            " * the Android system.  It" +
            " * allows access to application-specific resources and classes, as well as" +
            " * up-calls for application-level operations such as launching activities," +
            " * broadcasting and receiving intents, etc."

    init {

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val textLength = text.length


        var offsetY = paint.fontSpacing
        var start = 0
        var usableWidth: Float
        var count: Int
        var i = 0
        while (start < textLength) {
            val textTop = offsetY + fontMetrics.top
            val textBottom = offsetY + fontMetrics.descent
            usableWidth = if (textTop > PADDING && textTop < IMAGE_WIDTH + PADDING ||
                    textBottom > PADDING && textTop < IMAGE_WIDTH + PADDING) {
                width - PADDING
            } else {
                width.toFloat()
            }
            count = paint.breakText(text, start, textLength, true, usableWidth, floatArray) // measure
            canvas?.drawText(text, start, start + count, 0f, offsetY, paint) //draw
            start += count
            offsetY += paint.fontSpacing
            i++
        }


        canvas?.drawBitmap(image, PADDING, PADDING, paint)
    }

}