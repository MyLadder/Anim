package com.kannan.ornate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.AttributeSet;
import android.widget.TextView;

import static com.kannan.ornate.AnimTextView.Mode.A;

/**
 * Created by kannan on 11/7/17.
 */

@SuppressLint("AppCompatCustomView")
public class AnimTextView extends TextView {

    public enum Mode {

        A,
        B,
        C,
        D,
        E,
        F,
        G,
        H,
        I,
        J,
        K,
        L,
        M,
        N;

    }

    private Mode mode = A;
    private Rect maskRect;
    private Path maskPath;
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
        maskRect = new Rect();
        maskPath = new Path();
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
        canvas.clipPath(maskPath);
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

        maskPath.reset();
        switch (mode) {
            case A:
                maskPath.moveTo(0, 0);
                maskPath.lineTo(width * mProgress, 0);
                maskPath.lineTo(width * mProgress, height);
                maskPath.lineTo(0, height);
                maskPath.close();
                break;
            case B:
                maskPath.moveTo(width - (width * mProgress), 0);
                maskPath.lineTo(width, 0);
                maskPath.lineTo(width, height);
                maskPath.lineTo(width - (width * mProgress), height);
                maskPath.close();
                break;
            case C:
                maskPath.moveTo(0, 0);
                maskPath.lineTo(width, 0);
                maskPath.lineTo(width, height * mProgress);
                maskPath.lineTo(0, height * mProgress);
                maskPath.close();
                break;
            case D:
                maskPath.moveTo(0, height - (height * mProgress));
                maskPath.lineTo(width, height - (height * mProgress));
                maskPath.lineTo(width, height);
                maskPath.lineTo(0, height);
                maskPath.close();
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
            case I:
                maskPath.moveTo((width / 2) - (width * mProgress / 2), 0);
                maskPath.lineTo((width / 2) + (width * mProgress / 2), 0);
                maskPath.lineTo((width / 2) + (width * mProgress / 2), height);
                maskPath.lineTo((width / 2) - (width * mProgress / 2), height);
                maskPath.close();
                break;
            case J:
                maskPath.moveTo(0, (height / 2) - (height * mProgress / 2));
                maskPath.lineTo(width, (height / 2) - (height * mProgress / 2));
                maskPath.lineTo(width, (height / 2) + (height * mProgress / 2));
                maskPath.lineTo(0, (height / 2) + (height * mProgress / 2));
                maskPath.close();
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
    }

    public RectF getBoundingRect() {
        RectF bound = new RectF();
        maskPath.computeBounds(bound, true);
        return bound;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
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
}
