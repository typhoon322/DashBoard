package me.androidapp.yanx.dashboard

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import me.androidapp.yanx.dashboard.Utils.getBitmap

/**
 * me.androidapp.yanx.dashboard
 * Created by @author YANx on 2019/4/10 11:31 PM.
 * Description ${TODO}
 */
class AvatarView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null, 0)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val WIDTH = Utils.dp2px(300f)
    private val PADDING = Utils.dp2px(40f)
    private val BOADER_WIDTH = Utils.dp2px(5f)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        cut = RectF(PADDING, PADDING, PADDING + WIDTH, PADDING + WIDTH)
        border = RectF(PADDING - BOADER_WIDTH, PADDING - BOADER_WIDTH, PADDING + WIDTH + BOADER_WIDTH, PADDING + WIDTH + BOADER_WIDTH)
    }

    private var avatar: Bitmap? = null

    init {
        avatar = getBitmap(context!!.resources, R.drawable.demo, WIDTH.toInt())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawCake(canvas)
    }

    lateinit var border: RectF
    lateinit var cut: RectF
    private val Xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    private fun drawCake(canvas: Canvas?) {
        canvas?.drawOval(border, paint)
        val save = canvas?.saveLayer(border, paint)
        canvas?.drawOval(cut, paint)
        paint.xfermode = Xfermode
        canvas?.drawBitmap(avatar!!, PADDING, PADDING, paint)
        paint.xfermode = null
        canvas?.restoreToCount(save!!)
    }
}