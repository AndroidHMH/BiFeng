package com.coinwind.bifeng.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import com.coinwind.bifeng.R;

/**
 * textview倾斜45度
 */
public class TiltTextView extends TextView {
    public int getmDegrees() {
        return mDegrees;
    }

    public void setmDegrees(int mDegrees) {
        this.mDegrees = mDegrees;
        invalidate();
    }

    private int mDegrees;

    public TiltTextView(Context context) {
        super(context, null);
    }

    public TiltTextView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        this.setGravity(Gravity.CENTER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(getCompoundPaddingLeft(), getExtendedPaddingTop());
        canvas.rotate(321, getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        super.onDraw(canvas);
        canvas.restore();
    }

}
