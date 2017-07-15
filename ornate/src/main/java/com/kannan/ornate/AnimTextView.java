package com.kannan.ornate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.kannan.ornate.AnimTextView.AnimationType.REVEAL_RIGHT;

/**
 * Created by kannan on 11/7/17.
 */

@SuppressLint("AppCompatCustomView")
public class AnimTextView extends TextView {

    public enum AnimationType {

        REVEAL_RIGHT,
        REVEAL_LEFT,
        REVEAL_DOWN,
        REVEAL_UP,
        E,
        F,
        G,
        H,
        REVEAL_CENTER_VERTICAL,
        REVEAL_CENTER_HORIZONTAL,
        K,
        L,
        M,
        N;

    }

    private AnimationType animationType = REVEAL_RIGHT;
    private Rect maskRect;
    public Path maskPath, pathLeft, pathTop, pathRight, pathBottom;
    public Boundary boundaryLeft, boundaryRight, boundaryTop, boundaryBottom;
    private float mProgress;
    private int width = -1;
    private int height = -1;

    public AnimTextView(Context context) {
        super(context);
        init();
    }

    public AnimTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AnimTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_NONE, null);
        maskRect = new Rect();
        maskPath = new Path();
        pathLeft = new Path();
        pathTop = new Path();
        pathRight = new Path();
        pathBottom = new Path();

        boundaryLeft = new Boundary();
        boundaryTop = new Boundary();
        boundaryRight = new Boundary();
        boundaryBottom = new Boundary();
        mProgress = 1.0f;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            width = getMeasuredWidth();
            height = getMeasuredHeight();

//        width += getPaint().getFontMetrics().descent;
//        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        try {
            maskPath.reset();
            maskPath.moveTo(boundaryLeft.getContolrPoints().get(0).x, boundaryLeft.getContolrPoints().get(0).y);
            boundaryLeft.drawOnPath(maskPath);
            boundaryTop.drawOnPath(maskPath);
            boundaryRight.drawOnPath(maskPath);
            boundaryBottom.drawOnPath(maskPath);
            maskPath.close();
            canvas.clipPath(maskPath);
        } catch (Exception e ){}
//        canvas.clipPath(pathLeft, Region.Op.UNION);
//        canvas.clipPath(pathTop, Region.Op.UNION);
//        canvas.clipPath(pathRight, Region.Op.UNION);
//        canvas.clipPath(pathBottom, Region.Op.UNION);
//        canvas.drawText(getText().toString(),
//                getLeft(), getBaseline(), getPaint());
//        Layout layout = this.getLayout();
//        for (int i = 0; i < layout.getLineCount(); i += 1) {
//            int lineStart = layout.getLineStart(i);
//            int lineEnd = layout.getLineEnd(i);
//            float lineLeft = layout.getLineLeft(i);
//            float lineBaseline = layout.getLineBaseline(i);
//            String lineText = getText().subSequence(lineStart, lineEnd).toString();
//            canvas.drawText(String.valueOf(lineText), lineLeft, lineBaseline, getPaint());
//        }
        super.onDraw(canvas);

    }


    private void updateMask() {

        pathLeft.reset();
        pathTop.reset();
        pathRight.reset();
        pathBottom.reset();
        boundaryLeft.reset();
        boundaryTop.reset();
        boundaryRight.reset();
        boundaryBottom.reset();

        maskPath.reset();
        switch (animationType) {
            case REVEAL_RIGHT:
                maskPath.moveTo(0, 0);
                maskPath.lineTo(width * mProgress, 0);
                maskPath.lineTo(width * mProgress, height);
                maskPath.lineTo(0, height);
                maskPath.close();

                pathLeft.moveTo(0, 0);
                pathLeft.lineTo(0, height);

                pathTop.moveTo(0, 0);
                pathTop.lineTo(width * mProgress, 0);

                pathRight.moveTo(width * mProgress, 0);
                pathRight.lineTo(width * mProgress, height);

                pathBottom.moveTo(0, height);
                pathBottom.lineTo(width * mProgress, height);

                boundaryLeft.addControlPoint(0, height);
                boundaryLeft.addControlPoint(30, height / 2);
                boundaryLeft.addControlPoint(0, 0);
                boundaryTop.addControlPoint(0, 0);
                boundaryTop.addControlPoint(width * mProgress, 0);
                boundaryRight.addControlPoint(width * mProgress, 0);
                boundaryRight.addControlPoint(width * mProgress, height);
                boundaryBottom.addControlPoint(width * mProgress, height);
                boundaryBottom.addControlPoint(0, height);
                break;
            case REVEAL_LEFT:
                maskPath.moveTo(width - (width * mProgress), 0);
                maskPath.lineTo(width, 0);
                maskPath.lineTo(width, height);
                maskPath.lineTo(width - (width * mProgress), height);
                maskPath.close();

                pathLeft.moveTo(width - (width * mProgress), 0);
                pathLeft.lineTo(width - (width * mProgress), height);

                pathTop.moveTo(width - (width * mProgress), 0);
                pathTop.lineTo(width, 0);

                pathRight.moveTo(width, 0);
                pathRight.lineTo(width, height);

                pathBottom.moveTo(width - (width * mProgress), height);
                pathBottom.lineTo(width, height);


                boundaryLeft.addControlPoint(width - (width * mProgress), height);
                boundaryLeft.addControlPoint(width - (width * mProgress) + 30, height / 2);
                boundaryLeft.addControlPoint(width - (width * mProgress), 0);
                boundaryTop.addControlPoint(width - (width * mProgress), 0);
                boundaryTop.addControlPoint(width, 0);
                boundaryRight.addControlPoint(width, 0);
                boundaryRight.addControlPoint(width, height);
                boundaryBottom.addControlPoint(width, height);
                boundaryBottom.addControlPoint(width - (width * mProgress), height);
                break;
            case REVEAL_DOWN:
                maskPath.moveTo(0, 0);
                maskPath.lineTo(width, 0);
                maskPath.lineTo(width, height * mProgress);
                maskPath.lineTo(0, height * mProgress);
                maskPath.close();

                pathLeft.moveTo(0, 0);
                pathLeft.lineTo(0, height * mProgress);

                pathTop.moveTo(0, 0);
                pathTop.lineTo(width, 0);

                pathRight.moveTo(width, 0);
                pathRight.lineTo(width, height * mProgress);

                pathBottom.moveTo(0, height * mProgress);
                pathBottom.lineTo(width, height * mProgress);

                boundaryLeft.addControlPoint(0, height * mProgress);
                boundaryLeft.addControlPoint(0, 0);
                boundaryTop.addControlPoint(0, 0);
                boundaryTop.addControlPoint(width, 0);
                boundaryRight.addControlPoint(width, 0);
                boundaryRight.addControlPoint(width, height * mProgress);
                boundaryBottom.addControlPoint(width, height * mProgress);
                boundaryBottom.addControlPoint(0, height * mProgress);
                break;
            case REVEAL_UP:
                maskPath.moveTo(0, height - (height * mProgress));
                maskPath.lineTo(width, height - (height * mProgress));
                maskPath.lineTo(width, height);
                maskPath.lineTo(0, height);
                maskPath.close();

                pathLeft.moveTo(0, height - (height * mProgress));
                pathLeft.lineTo(0, height);

                pathTop.moveTo(0, height - (height * mProgress));
                pathTop.lineTo(width, height - (height * mProgress));

                pathRight.moveTo(width, height - (height * mProgress));
                pathRight.lineTo(width, height);

                pathBottom.moveTo(0, height);
                pathBottom.lineTo(width, height);


                boundaryLeft.addControlPoint(0, height);
                boundaryLeft.addControlPoint(0, height - (height * mProgress));
                boundaryTop.addControlPoint(0, height - (height * mProgress));
                boundaryTop.addControlPoint(width, height - (height * mProgress));
                boundaryRight.addControlPoint(width, height - (height * mProgress));
                boundaryRight.addControlPoint(width, height);
                boundaryBottom.addControlPoint(width, height);
                boundaryBottom.addControlPoint(0, height);
                break;
            case E:
                maskPath.moveTo(0, 0);
                maskPath.lineTo(width * mProgress * 2, 0);
                maskPath.lineTo(0, height * mProgress * 2);
                maskPath.close();
                break;
            case F:
                maskPath.moveTo(width - (width * mProgress * 2), 0);
                maskPath.lineTo(width, 0);
                maskPath.lineTo(width, height * mProgress * 2);
                maskPath.close();
                break;
            case G:
                maskPath.moveTo(width, height - (height * mProgress * 2));
                maskPath.lineTo(width, height);
                maskPath.lineTo(width - (width * mProgress * 2), height);
                maskPath.close();
                break;
            case H:
                maskPath.moveTo(0, height - (height * mProgress *2));
                maskPath.lineTo(width * mProgress * 2, height);
                maskPath.lineTo(0, height);
                maskPath.close();
                break;
            case REVEAL_CENTER_VERTICAL:
                maskPath.moveTo((width / 2) - (width * mProgress / 2), 0);
                maskPath.lineTo((width / 2) + (width * mProgress / 2), 0);
                maskPath.lineTo((width / 2) + (width * mProgress / 2), height);
                maskPath.lineTo((width / 2) - (width * mProgress / 2), height);
                maskPath.close();

                pathLeft.moveTo((width / 2) - (width * mProgress / 2), 0);
                pathLeft.lineTo((width / 2) - (width * mProgress / 2), height);

                pathTop.moveTo((width / 2) - (width * mProgress / 2), 0);
                pathTop.lineTo((width / 2) + (width * mProgress / 2), 0);

                pathRight.moveTo((width / 2) + (width * mProgress / 2), 0);
                pathRight.lineTo((width / 2) + (width * mProgress / 2), height);

                pathBottom.moveTo((width / 2) - (width * mProgress / 2), height);
                pathBottom.lineTo((width / 2) + (width * mProgress / 2), height);


                boundaryLeft.addControlPoint((width / 2) - (width * mProgress / 2), height);
                boundaryLeft.addControlPoint((width / 2) - (width * mProgress / 2), 0);
                boundaryTop.addControlPoint((width / 2) - (width * mProgress / 2), 0);
                boundaryTop.addControlPoint((width / 2) + (width * mProgress / 2), 0);
                boundaryRight.addControlPoint((width / 2) + (width * mProgress / 2), 0);
                boundaryRight.addControlPoint((width / 2) + (width * mProgress / 2), height);
                boundaryBottom.addControlPoint((width / 2) + (width * mProgress / 2), height);
                boundaryBottom.addControlPoint((width / 2) - (width * mProgress / 2), height);

                break;
            case REVEAL_CENTER_HORIZONTAL:
                maskPath.moveTo(0, (height / 2) - (height * mProgress / 2));
                maskPath.lineTo(width, (height / 2) - (height * mProgress / 2));
                maskPath.lineTo(width, (height / 2) + (height * mProgress / 2));
                maskPath.lineTo(0, (height / 2) + (height * mProgress / 2));
                maskPath.close();

                pathLeft.moveTo(0, (height / 2) - (height * mProgress / 2));
                pathLeft.lineTo(0, (height / 2) + (height * mProgress / 2));

                pathTop.moveTo(0, (height / 2) - (height * mProgress / 2));
                pathTop.lineTo(width, (height / 2) - (height * mProgress / 2));

                pathRight.moveTo(width, (height / 2) - (height * mProgress / 2));
                pathRight.lineTo(width, (height / 2) + (height * mProgress / 2));

                pathBottom.moveTo(0, (height / 2) + (height * mProgress / 2));
                pathBottom.lineTo(width, (height / 2) + (height * mProgress / 2));


                boundaryLeft.addControlPoint(0, (height / 2) + (height * mProgress / 2));
                boundaryLeft.addControlPoint(0, (height / 2) - (height * mProgress / 2));
                boundaryTop.addControlPoint(0, (height / 2) - (height * mProgress / 2));
                boundaryTop.addControlPoint(width, (height / 2) - (height * mProgress / 2));
                boundaryRight.addControlPoint(width, (height / 2) - (height * mProgress / 2));
                boundaryRight.addControlPoint(width, (height / 2) + (height * mProgress / 2));
                boundaryBottom.addControlPoint(width, (height / 2) + (height * mProgress / 2));
                boundaryBottom.addControlPoint(0, (height / 2) + (height * mProgress / 2));
                break;
            case K:
                maskPath.moveTo(0, 0);
                maskPath.lineTo(width * mProgress, 0);
                maskPath.lineTo(width, height - (height * mProgress));
                maskPath.lineTo(width, height);
                maskPath.lineTo(width - (width * mProgress), height);
                maskPath.lineTo(0, height * mProgress);
                maskPath.close();
                break;
            case L:
                maskPath.moveTo(width - (width * mProgress), 0);
                maskPath.lineTo(width, 0);
                maskPath.lineTo(width, height * mProgress);
                maskPath.lineTo(width * mProgress, height);
                maskPath.lineTo(0, height);
                maskPath.lineTo(0, height - (height * mProgress));
                maskPath.close();
                break;
            case M:
                maskPath.moveTo((width / 2) - (width * mProgress / 2), (height / 2) - (height * mProgress / 2));
                maskPath.lineTo((width / 2) + (width * mProgress / 2), (height / 2) - (height * mProgress / 2));
                maskPath.lineTo((width / 2) + (width * mProgress / 2), (height / 2) + (height * mProgress / 2));
                maskPath.lineTo((width / 2) - (width * mProgress / 2), (height / 2) + (height * mProgress / 2));
                maskPath.close();
                break;

        }

        maskPath.reset();
//        pathLeft.close();
//        pathTop.close();
//        pathRight.close();
//        pathBottom.close();
        maskPath.op(pathLeft, Path.Op.UNION);
        maskPath.op(pathTop, Path.Op.UNION);
        maskPath.op(pathRight, Path.Op.UNION);
        maskPath.op(pathTop, Path.Op.UNION);
        maskPath.close();
    }

    public RectF getBoundingRect() {
        RectF bound = new RectF();
        maskPath.computeBounds(bound, true);
        return bound;
    }

    public void setAnimationType(AnimationType animationType) {
        this.animationType = animationType;
        invalidate();
    }

    public void setProgress(float progress) {
        mProgress = progress;
        updateMask();
        invalidate();
    }

    public float getProgress() {
        return mProgress;
    }

    class Boundary {

        List<PointF> mControlPoints;

        Boundary() {
            mControlPoints = new ArrayList<>();
        }

        public void addControlPoint(PointF point) {
            mControlPoints.add(point);
        }

        public void addControlPoint(float x, float y) {
            addControlPoint(new PointF(x, y));
        }

        public List<PointF> getContolrPoints() {
            return mControlPoints;
        }

        public void drawOnPath(Path path) {
            for (PointF point : mControlPoints) {
                path.lineTo(point.x, point.y);
            }
        }

        public void reset() {
            mControlPoints.clear();
        }
    }
}
