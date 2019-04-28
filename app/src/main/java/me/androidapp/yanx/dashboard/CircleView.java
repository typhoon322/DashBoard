package me.androidapp.yanx.dashboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * me.androidapp.yanx.dashboard
 * Created by @author YANx on 2019-04-22 23:56.
 * Description ${TODO}
 */
public class CircleView extends View {

    final static float PADDING = Utils.dp2px(20);
    float radius = Utils.dp2px(80);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = (int) (radius + PADDING) * 2;
        int width = resolveSize(size, widthMeasureSpec);
        int height = resolveSize(size, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(PADDING + radius, PADDING + radius, radius, paint);
    }
}
