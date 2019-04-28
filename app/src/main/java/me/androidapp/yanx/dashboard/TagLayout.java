package me.androidapp.yanx.dashboard;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TagLayout extends ViewGroup {
    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    List<Rect> bounds = new ArrayList<>();

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthUsed = 0;
        int heightUsed = 0;
        int lineHeight = 0;
        int lineWidthUsed = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);

            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            if (widthMode != MeasureSpec.UNSPECIFIED && lineWidthUsed + child.getMeasuredWidth() > widthSize) {
                lineWidthUsed = 0;
                heightUsed += lineHeight;
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            }

            Rect childBound;
            if (bounds.size() <= i) {
                childBound = new Rect();
                bounds.add(childBound);
            } else {
                childBound = bounds.get(i);
            }

            childBound.set(lineWidthUsed, heightUsed, lineWidthUsed + child.getMeasuredWidth(), heightUsed + child.getMeasuredHeight());
            widthUsed += child.getMeasuredWidth();
            lineHeight = Math.max(lineHeight, child.getMeasuredHeight());

            lineWidthUsed += child.getMeasuredWidth();
        }

        int measuredWidth = widthUsed;
        int measureHeight = heightUsed;
        setMeasuredDimension(measuredWidth, measureHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < bounds.size(); i++) {
            View view = getChildAt(i);
            view.layout(bounds.get(i).left, bounds.get(i).top, bounds.get(i).right, bounds.get(i).bottom);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
