package me.androidapp.yanx.dashboard;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;

/**
 * me.androidapp.yanx.dashboard
 * Created by @author YANx on 2019/4/21 3:30 PM.
 * Description ${TODO}
 */
public class MaterialEditText extends AppCompatEditText {
    private static final float TEXT_SIZE = Utils.dp2px(12);
    private static final float TEXT_MARGIN = Utils.dp2px(8);
    private static final float VERTICAL_OFFSET = Utils.dp2px(38);
    private static final float HORIZONTAL_OFFSET = Utils.dp2px(5);
    private static final float VERTICAL_OFFSET_EXTRA = Utils.dp2px(16);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    ObjectAnimator animator1;
    ObjectAnimator animator2;
    private float floatingLabelFraction;
    private boolean floatingLabelShown;

    {
        setPadding(getPaddingLeft(), (int) (getPaddingTop() + TEXT_SIZE + TEXT_MARGIN),
                getPaddingRight(), getPaddingBottom());
        paint.setTextSize(TEXT_SIZE);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (floatingLabelShown && TextUtils.isEmpty(s)) {
                    floatingLabelShown = !floatingLabelShown;
                    getAnimator2().reverse();
                } else if (!floatingLabelShown && !TextUtils.isEmpty(s)) {
                    floatingLabelShown = !floatingLabelShown;
                    getAnimator2().start();
                }
            }
        });
    }

    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private ObjectAnimator getAnimator2() {
        if (animator2 == null) {
            animator2 = ObjectAnimator.ofFloat(MaterialEditText.this, "floatingLabelFraction", 0, 1);
        }
        return animator2;
    }

    public float getFloatingLabelFraction() {
        return floatingLabelFraction;
    }

    public void setFloatingLabelFraction(float floatingLabelFraction) {
        this.floatingLabelFraction = floatingLabelFraction;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAlpha((int) (floatingLabelFraction * 0xff));
        paint.setTextSize(getTextSize() - floatingLabelFraction * (getTextSize() - TEXT_SIZE));
        canvas.drawText(getHint().toString(), HORIZONTAL_OFFSET, VERTICAL_OFFSET - VERTICAL_OFFSET_EXTRA * floatingLabelFraction, paint);
    }
}
