package me.androidapp.yanx.dashboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * me.androidapp.yanx.dashboard
 * Created by @author YANx on 2019/4/9 11:40 PM.
 * Description ${TODO}
 */
public class DashBoard extends View {
    private static final float RADIUS = Utils.INSTANCE.dp2px(150);
    private static final float LENGTH = Utils.INSTANCE.dp2px(100);
    private static final float ANGLE = 120;
    private RectF boundRect;
    private Paint paint;
    private Path dash = new Path();
    private Path path = new Path();
    private PathMeasure pathMeasure;
    private PathDashPathEffect pathDashPathEffect;

    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.INSTANCE.dp2px(4));
        paint.setColor(Color.BLACK);

        dash.addRect(0, 0, Utils.INSTANCE.dp2px(2), Utils.INSTANCE.dp2px(10), Path.Direction.CCW);

    }

    public DashBoard(Context context) {
        super(context);
    }

    public DashBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public DashBoard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        boundRect = new RectF(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS,
                              getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS);
        path.addArc(boundRect, 90 + ANGLE / 2, 360 - ANGLE);
        pathMeasure = new PathMeasure(path, false);
        pathDashPathEffect = new PathDashPathEffect(dash, (pathMeasure.getLength() - Utils.INSTANCE.dp2px(2)) / 20, 0, PathDashPathEffect.Style.ROTATE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(boundRect, 90 + ANGLE / 2, 360 - ANGLE, false, paint);

        paint.setPathEffect(pathDashPathEffect);
        canvas.drawArc(boundRect, 90 + ANGLE / 2, 360 - ANGLE, false, paint);
        paint.setPathEffect(null);

        canvas.drawLine(getWidth() / 2, getHeight() / 2,
                        getWidth() / 2 + (float) Math.cos(Math.toRadians(getMarkAngle(5))) * LENGTH,
                        getHeight() / 2 + (float) Math.sin(Math.toRadians(getMarkAngle(5))) * LENGTH, paint);
    }

    private float getMarkAngle(int markIndex) {
        return 90 + ANGLE / 2 + (360 - ANGLE) / 20 * markIndex;
    }
}
