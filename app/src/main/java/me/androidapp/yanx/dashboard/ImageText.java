package me.androidapp.yanx.dashboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * me.androidapp.yanx.dashboard
 * Created by @author YANx on 2019/4/15 11:21 PM.
 * Description ${TODO}
 */
public class ImageText extends View {
    private static final float IMAGE_WIDTH = Utils.dp2px(150);
    private static final float PADDING = Utils.dp2px(100);
    private static final String text = "Interface to global information about an application environment.  This is" +
            " * an abstract class whose implementation is provided by" +
            " * the Android system.  It" +
            " * allows access to application-specific resources and classes, as well as" +
            " * up-calls for application-level operations such as launching activities," +
            " * broadcasting and receiving intents, etc." + "Interface to global information about an application environment.  This is" +
            " * an abstract class whose implementation is provided by" +
            " * the Android system.  It" +
            " * allows access to application-specific resources and classes, as well as" +
            " * up-calls for application-level operations such as launching activities," +
            " * broadcasting and receiving intents, etc.";
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap image;
    private Paint.FontMetrics fontMetrics;
    private float[] measureWidth = new float[0];

    {
        textPaint.setTextSize(Utils.dp2px(14));
        paint.setTextSize(Utils.dp2px(16));
        fontMetrics = paint.getFontMetrics();
    }

    public ImageText(Context context) {
        this(context, null);
    }

    public ImageText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        image = Utils.getBitmap(context.getResources(), R.drawable.avatar, (int) IMAGE_WIDTH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(image, getWidth() - IMAGE_WIDTH, PADDING, paint);

        int textLength = text.length();
        float yOffset = paint.getFontSpacing();
        float usableWidth;
        for (int start = 0, count; start < textLength; start += count, yOffset += paint.getFontSpacing()) {
            float textTop = yOffset + fontMetrics.ascent;
            float textBottom = yOffset + fontMetrics.descent;
            if (textTop > PADDING && textTop < IMAGE_WIDTH + PADDING ||
                    textBottom > PADDING && textBottom < IMAGE_WIDTH + PADDING) {
                usableWidth = getWidth() - IMAGE_WIDTH;
            } else {
                usableWidth = getWidth();
            }
            count = paint.breakText(text, start, textLength, true, usableWidth, measureWidth);
            canvas.drawText(text, start, start + count, 0, yOffset, paint);
        }
    }
}
