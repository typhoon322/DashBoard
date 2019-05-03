package me.androidapp.yanx.dashboard;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;

/**
 * me.androidapp.yanx.dashboard
 * Created by @author YANx on 2019-05-03 17:16.
 * Description ${TODO}
 */
public class ScalableImageView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, Runnable {

    private final static float IMAGE_WIDTH = Utils.dp2px(200);
    private final static float OVER_SCALE_FACTOR = 1.5f;

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bitmap;

    private float originOffsetX;
    private float originOffsetY;
    private float offsetX;
    private float offsetY;
    private float bigScale;
    private float smallScale;

    private GestureDetectorCompat gestureDetector;
    private boolean big;
    private float scaleFraction;
    private ObjectAnimator scaleAnimator;
    private OverScroller scroller;

    public ScalableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        bitmap = Utils.getBitmap(getResources(), R.drawable.avatar, (int) IMAGE_WIDTH);
        gestureDetector = new GestureDetectorCompat(context, this);
        scroller = new OverScroller(context);
    }

    public float getScaleFraction() {
        return scaleFraction;
    }

    public void setScaleFraction(float scaleFraction) {
        this.scaleFraction = scaleFraction;
        invalidate();
    }

    private ObjectAnimator getScaleAnimator() {
        if (scaleAnimator == null) {
            scaleAnimator = ObjectAnimator.ofFloat(this, "scaleFraction", 0, 1);
        }
        return scaleAnimator;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        originOffsetX = (getWidth() - bitmap.getWidth()) / 2f;
        originOffsetY = (getHeight() - bitmap.getHeight()) / 2f;

        if ((float) bitmap.getWidth() / bitmap.getHeight() > (float) getWidth() / getHeight()) {
            smallScale = (float) getWidth() / bitmap.getWidth();
            bigScale = (float) getHeight() / bitmap.getHeight() * OVER_SCALE_FACTOR;
        } else {
            bigScale = (float) getWidth() / bitmap.getWidth() * OVER_SCALE_FACTOR;
            smallScale = (float) getHeight() / bitmap.getHeight();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction);
        float scale = smallScale + scaleFraction * (bigScale - smallScale);
        canvas.scale(scale, scale, getWidth() / 2f, getHeight() / 2f);
        canvas.drawBitmap(bitmap, originOffsetX, originOffsetY, paint);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (big) {
            offsetX -= distanceX;
            offsetY -= distanceY;
            fixOffset();
            invalidate();
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (big) {
            scroller.fling((int) offsetX, (int) offsetY, (int) velocityX, (int) velocityY,
                           - (int) ((bitmap.getWidth() * bigScale - getWidth()) / 2),
                           (int) ((bitmap.getWidth() * bigScale - getWidth()) / 2),
                           - (int) ((bitmap.getHeight() * bigScale - getHeight()) / 2),
                           (int) ((bitmap.getHeight() * bigScale - getHeight()) / 2));
            postOnAnimation(this);
        }
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        big = !big;
        if (big) {
            offsetX = (e.getX() - getWidth() / 2f) * (1 - bigScale / smallScale);
            offsetY = (e.getY() - getHeight() / 2f) * (1 - bigScale / smallScale);
            fixOffset();
            getScaleAnimator().start();
        } else {
            getScaleAnimator().reverse();
        }
        return false;
    }

    private void fixOffset() {
        offsetX = Math.min(offsetX, bitmap.getWidth() * bigScale - getWidth() / 2f);
        offsetX = Math.max(offsetX, -bitmap.getWidth() * bigScale - getWidth() / 2f);

        offsetY = Math.min(offsetY, bitmap.getHeight() * bigScale - getHeight() / 2f);
        offsetY = Math.max(offsetY, -bitmap.getHeight() * bigScale - getHeight() / 2f);
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public void run() {
        if (scroller.computeScrollOffset()) {
            offsetX = scroller.getCurrX();
            offsetY = scroller.getCurrY();
            invalidate();
            postOnAnimation(this);
        }
    }
}
