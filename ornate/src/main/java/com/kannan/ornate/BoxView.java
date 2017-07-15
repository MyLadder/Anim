package com.kannan.ornate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kannan on 12/7/17.
 */

public class BoxView extends View {


    private CompositeLine mCompositeLineLeft;
    private CompositeLine mCompositeLineTop;
    private CompositeLine mCompositeLineRight;
    private CompositeLine mCompositeLineBottom;

    private Shape mRectangle;

    private boolean mShowLineLeft;
    private boolean mShowLineTop;
    private boolean mShowLineRight;
    private boolean mShowLineBottom;

    public enum Mode {
        MODE_LINES,

        MODE_BOX;
    }

    private Mode mMode;

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

        mCompositeLineLeft = new CompositeLine(CompositeLine.ORIENT_VERTICAL);
        mCompositeLineTop = new CompositeLine(CompositeLine.ORIENT_HORIZONTAL);
        mCompositeLineRight = new CompositeLine(CompositeLine.ORIENT_VERTICAL);
        mCompositeLineBottom = new CompositeLine(CompositeLine.ORIENT_HORIZONTAL);

        mRectangle = new Shape(Shape.STYLE_FILL_STROKE);

//        mShowLineLeft = true;
//        mShowLineTop = true;
//        mShowLineRight = true;
//        mShow

        mProgress = 1.0f;
        mMode = Mode.MODE_LINES;

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
        float offsetX = mViewToCover.getX();
        float offsetY = mViewToCover.getY();
        if (mMode == Mode.MODE_LINES) {
            canvas.drawPath(mCompositeLineLeft.getPathOffset(offsetX, offsetY), mCompositeLineLeft.getPaint());
//            Path p = mViewToCover.pathLeft;
//            p.offset(mViewToCover.getX(), mViewToCover.getY());
//            Path pp = mViewToCover.pathTop;
//            pp.offset(mViewToCover.getX(), mViewToCover.getY());
//            canvas.drawPath(p, mCompositeLineLeft.getPaint());
            canvas.drawPath(mCompositeLineTop.getPathOffset(offsetX, offsetY), mCompositeLineTop.getPaint());
//            canvas.drawPath(pp, mCompositeLineTop.getPaint());
            canvas.drawPath(mCompositeLineRight.getPathOffset(offsetX, offsetY), mCompositeLineRight.getPaint());
            canvas.drawPath(mCompositeLineBottom.getPathOffset(offsetX, offsetY), mCompositeLineBottom.getPaint());
        } else if (mMode == Mode.MODE_BOX) {
            canvas.drawPath(mRectangle.getPathOffset(offsetX, offsetY), mRectangle.getPaint());
        }
    }

    private void updatePaths() {
//        RectF bound = mViewToCover.getBoundingRect();
//        bound.offset(mViewToCover.getX(), mViewToCover.getY());
//        mCompositeLineLeft.setEndPoints(bound.left, bound.top, bound.left, bound.bottom);
////        mCompositeLineLeft = mViewToCover.pathLeft;
//        mCompositeLineTop.setEndPoints(bound.left, bound.top, bound.right, bound.top);
//        mCompositeLineRight.setEndPoints(bound.right, bound.top, bound.right, bound.bottom);
//        mCompositeLineBottom.setEndPoints(bound.left, bound.bottom, bound.right, bound.bottom);

        mCompositeLineLeft.setPoints(mViewToCover.boundaryLeft.getContolrPoints());
        mCompositeLineTop.setPoints(mViewToCover.boundaryTop.getContolrPoints());
        mCompositeLineRight.setPoints(mViewToCover.boundaryRight.getContolrPoints());
        mCompositeLineBottom.setPoints(mViewToCover.boundaryBottom.getContolrPoints());

        mRectangle.setPoints(mViewToCover.boundaryLeft.getContolrPoints());
        mRectangle.addPoints(mViewToCover.boundaryTop.getContolrPoints());
        mRectangle.addPoints(mViewToCover.boundaryRight.getContolrPoints());
        mRectangle.addPoints(mViewToCover.boundaryBottom.getContolrPoints());
    }


    public void setProgress(float progress) {
        mProgress = progress;
        updatePaths();
        invalidate();
    }

    public void setRectSource(AnimTextView source) {
        mViewToCover = source;
    }

    public void setMode(Mode mode) {
        mMode = mode;
    }

    public float getProgress() {
        return mProgress;
    }

    public CompositeLine getLineLeft() {
        return mCompositeLineLeft;
    }

    public CompositeLine getLineTop() {
        return mCompositeLineTop;
    }

    public CompositeLine getLineRight() {
        return mCompositeLineRight;
    }

    public CompositeLine getLineBottom() {
        return mCompositeLineBottom;
    }

    public Shape getRectangle() {
        return mRectangle;
    }



    class CompositeLine {

        static final int ORIENT_HORIZONTAL = 1;
        static final int ORIENT_VERTICAL = 2;

        Path mPath;
        PathMeasure mPathMeasure;
        Paint mPaint;
        float mScale;
        float mPivotRatio;
        int mOrientation;
//        float x1, x2, y1, y2;
        List<PointF> mPoints;

        private void init() {
            mPath = new Path();
            mPathMeasure = new PathMeasure();
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(Color.YELLOW);
            mPaint.setStrokeWidth(1f);
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            mOrientation = ORIENT_HORIZONTAL;
//            x1 = x2 = y1 = y2 = 0;
            mPoints = new ArrayList<>();
            mPivotRatio = 0.5f;
        }

        CompositeLine(int orient) {
            init();
            mOrientation = orient;
        }

//        void setEndPoints(float x1, float y1, float x2, float y2) {
//            this.x1 = x1;
//            this.x2 = x2;
//            this.y1 = y1;
//            this.y2 = y2;
//            preparePath();
//        }

        void setPoints(List<PointF> points) {
            mPoints = new ArrayList<>(points);
        }

        public void reset() {
            init();
        }

        private void preparePath() {
            mPath.reset();
            if (!mPoints.isEmpty()) {
                mPath.moveTo(mPoints.get(0).x, mPoints.get(0).y);
                for (PointF point : mPoints) {
                    mPath.lineTo(point.x, point.y);
                }
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
        }

        Path getPath() {
            preparePath();
            return mPath;
        }

        Path getPathOffset(float x, float y) {
            Path p = getPath();
            p.offset(x, y);
            return p;
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
//            preparePath();
        }

        void setPivotRatio(float ratio) {
            mPivotRatio = ratio;
//            preparePath();
        }

    }

    class Shape {

        static final int STYLE_STROKE = 1;
        static final int STYLE_FILL = 2;
        static final int STYLE_FILL_STROKE = 3;

        private List<PointF> mControlPoints;
        private Paint mPaint;
        private Path mPath;
        private int mStyle;
        private float mScaleX;
        private float mScaleY;
        private float mPivotRatioX;
        private float mPivotRatioY;

        private void init() {
            mControlPoints = new ArrayList<>();
            mStyle = STYLE_FILL_STROKE;
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            mPaint.setColor(Color.BLACK);
            mPaint.setStrokeJoin(Paint.Join.ROUND);
            mPath = new Path();
            mScaleX = 1.0f;
            mScaleY = 1.0f;
            mPivotRatioX = 0.5f;
            mPivotRatioY = 0.5f;
        }

        Shape(int style) {
            init();
            setStyle(style);
        }

        public void preparePath() {
            mPath.reset();
            if (!mControlPoints.isEmpty()) {
                mPath.moveTo(mControlPoints.get(0).x, mControlPoints.get(0).y);
                for (PointF point : mControlPoints) {
                    mPath.lineTo(point.x, point.y);
                }
                mPath.close();
                RectF bound = new RectF();
                mPath.computeBounds(bound, true);
                Matrix scaleMatrix = new Matrix();
                scaleMatrix.setScale(mScaleX, mScaleY,
                        bound.left + bound.width() * mPivotRatioX,
                        bound.top + bound.height() * mPivotRatioY);
                mPath.transform(scaleMatrix);
            }
        }

        public void setPoints(List<PointF> points) {
            mControlPoints = new ArrayList<>(points);
        }

        public void addPoints(List<PointF> points) {
            mControlPoints.addAll(points);
        }

        public void reset() {
            init();
        }

        public Path getPath() {
            preparePath();
            return mPath;
        }

        public Path getPathOffset(float dx, float dy) {
            Path p = getPath();
            p.offset(dx, dy);
            return p;
        }

        public void setColor(int color) {
            mPaint.setColor(color);
        }

        public void setStyle(int style) {
            mStyle = style;
            if (mStyle == STYLE_FILL) {
                mPaint.setStyle(Paint.Style.FILL);
            } else if (mStyle == STYLE_STROKE){
                mPaint.setStyle(Paint.Style.STROKE);
            } else {
                mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            }
        }

        public void setStrockWidth(float width) {
            mPaint.setStrokeWidth(width);
        }

        public Paint getPaint() {
            return mPaint;
        }
    }
}
