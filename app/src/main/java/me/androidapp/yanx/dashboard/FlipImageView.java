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
public class FlipImageView extends View {
    private static final float IMAGE_WIDTH = Utils.dp2px(200);
    private static final float PADDING = Utils.dp2px(100);
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap image;
    private Camera camera = new Camera();

    {
        image = Utils.getBitmap(getResources(), R.drawable.avatar, (int) IMAGE_WIDTH);
        camera.rotateX(45);
        camera.setLocation(0, 0, Utils.getZ4Camera());
    }

    public FlipImageView(Context context) {
        this(context, null);
    }

    public FlipImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlipImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.translate((PADDING + IMAGE_WIDTH / 2), (PADDING + IMAGE_WIDTH / 2));
        canvas.rotate(-30);
        canvas.clipRect(-IMAGE_WIDTH, -IMAGE_WIDTH, IMAGE_WIDTH, 0);
        canvas.rotate(30);
        canvas.translate(-(PADDING + IMAGE_WIDTH / 2), -(PADDING + IMAGE_WIDTH / 2));
        canvas.drawBitmap(image, PADDING, PADDING, paint);
        canvas.restore();

        canvas.save();
        canvas.translate((PADDING + IMAGE_WIDTH / 2), (PADDING + IMAGE_WIDTH / 2));
        canvas.rotate(-30);
        camera.applyToCanvas(canvas);
        canvas.clipRect(-IMAGE_WIDTH, 0, IMAGE_WIDTH, IMAGE_WIDTH);
        canvas.rotate(30);
        canvas.translate(-(PADDING + IMAGE_WIDTH / 2), -(PADDING + IMAGE_WIDTH / 2));
        canvas.drawBitmap(image, PADDING, PADDING, paint);
        canvas.restore();
    }
}
