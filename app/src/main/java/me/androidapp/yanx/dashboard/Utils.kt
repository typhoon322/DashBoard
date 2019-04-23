package me.androidapp.yanx.dashboard

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.TypedValue

/**
 * me.androidapp.yanx.dashboard
 * Created by @author YANx on 2019/4/9 11:54 PM.
 * Description ${TODO}
 */
object Utils {
    @JvmStatic
    fun dp2px(dp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().displayMetrics)
    }

    @JvmStatic
    fun getBitmap(resources: Resources, resId: Int, width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, resId, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, resId, options)
    }

    @JvmStatic
    fun getZ4Camera(): Float {
        return -6 * Resources.getSystem().displayMetrics.density
    }
}
