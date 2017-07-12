package com.kannan.ornate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by kannan on 12/7/17.
 */

public class BoxView extends View {

    private Rect maskRect;
    private Path maskPath;
    private Path pathLeft, pathTop, pathRight, pathBottom;
    private float progress;
    private int widthTotal = -1;
    private int heightTotal = -1;
    private int left, top, right, bottom, width, height;

    private Paint mLinePaint;

    private AnimTextView mRectSource;

    public BoxView(Context context) {
        super(context);
        init();
    }

    public BoxView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BoxView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        maskRect = new Rect();
        maskPath = new Path();
        pathLeft = new Path();
        pathTop = new Path();
        pathRight = new Path();
        pathBottom = new Path();
        progress = 1.0f;

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setColor(Color.GREEN);
        mLinePaint.setStrokeWidth(3f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthTotal = getMeasuredWidth();
        heightTotal = getMeasuredHeight();
        left = getPaddingLeft();
        top = getPaddingTop();
        right = widthTotal - getPaddingRight();
        bottom = heightTotal - getPaddingBottom();
        width = right - left;
        height = bottom - top;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawPath(pathLeft, mLinePaint);
//        canvas.drawPath(pathTop, mLinePaint);
        RectF bound = mRectSource.getBoundingRect();
        bound.offset(5, 5);
        canvas.drawRect(bound, mLinePaint);
    }

    private void updatePaths() {
        maskPath.moveTo(left, top + ((height / 2) - (height * progress / 2)));
        maskPath.lineTo(right, top + ((height / 2) - (height * progress / 2)));
        maskPath.lineTo(right, top + ((height / 2) + (height * progress / 2)));
        maskPath.lineTo(left, top + ((height / 2) - (height * progress / 2)));
        maskPath.close();

        RectF bound = mRectSource.getBoundingRect();
//        bound.offset(2*-bound.left, -bound.top);
        pathLeft.moveTo(bound.left, bound.top);
        pathLeft.lineTo(bound.left, bound.bottom);
        Log.i("app", bound.toString());

        pathTop.moveTo(bound.left, bound.top);
        pathTop.lineTo(bound.right, bound.top);


    }


    public void setProgress(float progress) {
        this.progress = progress;
        updatePaths();
        invalidate();
    }

    public void setRectSource(AnimTextView source) {
        mRectSource = source;
    }

    public float getProgress() {
        return progress;
    }

    public void setText(String text) {

    }
}
