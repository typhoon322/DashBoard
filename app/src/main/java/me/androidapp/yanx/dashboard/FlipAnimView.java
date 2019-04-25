package me.androidapp.yanx.dashboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * me.androidapp.yanx.dashboard
 * Created by @author YANx on 2019/4/15 11:21 PM.
 * Description ${TODO}
 */
public class FlipAnimView extends View {
    private static final float IMAGE_WIDTH = Utils.dp2px(200);
    private static final float PADDING = Utils.dp2px(100);
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap image;
    private Camera camera = new Camera();

    float topFlip = 0;
    float bottomFlip = 0;
    float flipRotation = 0;

    {
        image = Utils.getBitmap(getResources(), R.drawable.avatar, (int) IMAGE_WIDTH);

        camera.setLocation(0, 0, Utils.getZ4Camera());
    }

    public FlipAnimView(Context context) {
        this(context, null);
    }

    public FlipAnimView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlipAnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public float getTopFlip() {
        return topFlip;
    }

    public void setTopFlip(float topFlip) {
        this.topFlip = topFlip;
        invalidate();
    }

    public float getBottomFlip() {
        return bottomFlip;
    }

    public void setBottomFlip(float bottomFlip) {
        this.bottomFlip = bottomFlip;
        invalidate();
    }

    public float getFlipRotation() {
        return flipRotation;
    }

    public void setFlipRotation(float flipRotation) {
        this.flipRotation = flipRotation;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.translate((PADDING + IMAGE_WIDTH / 2), (PADDING + IMAGE_WIDTH / 2));
        canvas.rotate(-flipRotation);
        camera.save();
        camera.rotateX(topFlip);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.clipRect(-IMAGE_WIDTH, -IMAGE_WIDTH, IMAGE_WIDTH, 0);
        canvas.rotate(flipRotation);
        canvas.translate(-(PADDING + IMAGE_WIDTH / 2), -(PADDING + IMAGE_WIDTH / 2));
        canvas.drawBitmap(image, PADDING, PADDING, paint);
        canvas.restore();

        canvas.save();
        canvas.translate((PADDING + IMAGE_WIDTH / 2), (PADDING + IMAGE_WIDTH / 2));
        canvas.rotate(-flipRotation);
        camera.save();
        camera.rotateX(bottomFlip);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.clipRect(-IMAGE_WIDTH, 0, IMAGE_WIDTH, IMAGE_WIDTH);
        canvas.rotate(flipRotation);
        canvas.translate(-(PADDING + IMAGE_WIDTH / 2), -(PADDING + IMAGE_WIDTH / 2));
        canvas.drawBitmap(image, PADDING, PADDING, paint);
        canvas.restore();
    }
}
