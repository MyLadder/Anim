package com.kannan.ornate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by kannan on 12/7/17.
 */

public class BoxView extends View {


    private Line mLineLeft;
    private Line mLineTop;
    private Line mLineRight;
    private Line mLineBottom;

    private boolean mShowLineLeft;
    private boolean mShowLineTop;
    private boolean mShowLineRight;
    private boolean mShowLineBottom;


    private float mProgress;

    private AnimTextView mViewToCover;

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

        mLineLeft = new Line(Line.ORIENT_VERTICAL);
        mLineTop = new Line(Line.ORIENT_HORIZONTAL);
        mLineRight = new Line(Line.ORIENT_VERTICAL);
        mLineBottom = new Line(Line.ORIENT_HORIZONTAL);

//        mShowLineLeft = true;
//        mShowLineTop = true;
//        mShowLineRight = true;
//        mShow

        mProgress = 1.0f;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        widthTotal = getMeasuredWidth();
//        heightTotal = getMeasuredHeight();
//        left = getPaddingLeft();
//        top = getPaddingTop();
//        right = widthTotal - getPaddingRight();
//        bottom = heightTotal - getPaddingBottom();
//        width = right - left;
//        height = bottom - top;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mLineLeft.getPath(), mLineLeft.getPaint());
        canvas.drawPath(mLineTop.getPath(), mLineTop.getPaint());
        canvas.drawPath(mLineRight.getPath(), mLineRight.getPaint());
        canvas.drawPath(mLineBottom.getPath(), mLineBottom.getPaint());
    }

    private void updatePaths() {
        RectF bound = mViewToCover.getBoundingRect();
        bound.offset(mViewToCover.getX(), mViewToCover.getY());
        mLineLeft.setEndPoints(bound.left, bound.top, bound.left, bound.bottom);
        mLineTop.setEndPoints(bound.left, bound.top, bound.right, bound.top);
        mLineRight.setEndPoints(bound.right, bound.top, bound.right, bound.bottom);
        mLineBottom.setEndPoints(bound.left, bound.bottom, bound.right, bound.bottom);
    }


    public void setProgress(float progress) {
        mProgress = progress;
        updatePaths();
        invalidate();
    }

    public void setRectSource(AnimTextView source) {
        mViewToCover = source;
    }

    public float getProgress() {
        return mProgress;
    }

    public Line getLineLeft() {
        return mLineLeft;
    }

    public Line getLineTop() {
        return mLineTop;
    }

    public Line getLineRight() {
        return mLineRight;
    }

    public Line getLineBottom() {
        return mLineBottom;
    }



    class Line {

        static final int ORIENT_HORIZONTAL = 1;
        static final int ORIENT_VERTICAL = 2;

        Path mPath;
        PathMeasure mPathMeasure;
        Paint mPaint;
        float mScale;
        float mPivotRatio;
        int mOrientation;
        float x1, x2, y1, y2;

        {
            mPath = new Path();
            mPathMeasure = new PathMeasure();
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(Color.YELLOW);
            mPaint.setStrokeWidth(1f);
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            mOrientation = ORIENT_HORIZONTAL;
            x1 = x2 = y1 = y2 = 0;
        }

        Line(int orient) {
            mOrientation = orient;
        }

        void setEndPoints(float x1, float y1, float x2, float y2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
            preparePath();
        }

        private void preparePath() {
            mPath.reset();
            mPath.moveTo(x1, y1);
            mPath.lineTo(x2, y2);
            mPathMeasure = new PathMeasure(mPath, false);
            float[] pivotPoints = new float[2];
            mPathMeasure.getPosTan(mPathMeasure.getLength() * mPivotRatio, pivotPoints, null);
            Matrix scaleMatrix = new Matrix();
            if (mOrientation == ORIENT_HORIZONTAL) {
                scaleMatrix.setScale(mScale, 1f, pivotPoints[0], pivotPoints[1]);
            } else {
                scaleMatrix.setScale(1f, mScale, pivotPoints[0], pivotPoints[1]);
            }
            mPath.transform(scaleMatrix);
        }

        Path getPath() {
            return mPath;
        }

        Paint getPaint() {
            return mPaint;
        }

        void setColor(int color) {
            mPaint.setColor(color);
        }

        void setThickness(float thick) {
            mPaint.setStrokeWidth(thick);
        }

        public void setScale(float scale) {
            mScale = scale;
            preparePath();
        }

        void setPivotRatio(float ratio) {
            mPivotRatio = ratio;
            preparePath();
        }

    }
}
