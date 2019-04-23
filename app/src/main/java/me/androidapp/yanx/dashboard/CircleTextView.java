package me.androidapp.yanx.dashboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * me.androidapp.yanx.dashboard
 * Created by @author YANx on 2019/4/16 12:07 AM.
 * Description ${TODO}
 */
public class CircleTextView extends View {

    private static final float RADIUS = Utils.dp2px(150);
    private static final float PADDING = Utils.dp2px(100);
    private static final int CIRCLE_COLOR = Color.GRAY;
    private static final int HIGHLIGHT_COLOR = Color.YELLOW;
    private static final int TEXT_COLOR = Color.RED;
    private static final String text = "doge";

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint.FontMetrics fontMetrics = new Paint.FontMetrics();
    private RectF rect = new RectF();

    {
        paint.setTextSize(Utils.dp2px(100));
        paint.setTextAlign(Paint.Align.CENTER);
    }

    public CircleTextView(Context context) {
        this(context, null);
    }

    public CircleTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rect.set(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(CIRCLE_COLOR);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.dp2px(20));
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, RADIUS, paint);

        paint.setColor(HIGHLIGHT_COLOR);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(rect, -90, 270, false, paint);
        paint.setStrokeCap(Paint.Cap.BUTT);

        paint.setColor(TEXT_COLOR);
        paint.setStyle(Paint.Style.FILL);
        paint.getFontMetrics(fontMetrics);
        float offset = (fontMetrics.ascent + fontMetrics.descent) / 2f;
        canvas.drawText(text, getWidth() / 2, getHeight() / 2 - offset, paint);
    }
}
