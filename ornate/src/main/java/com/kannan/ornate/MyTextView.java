package com.kannan.ornate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by kannan on 8/7/17.
 */


//https://stackoverflow.com/questions/6593885/how-to-remove-the-top-and-bottom-space-on-textview-of-android


public class MyTextView extends AppCompatTextView {

    private final Paint mPaint = new Paint();

    private final Rect mBounds = new Rect();

    private int paddingVertical = 0;
    private int paddingHorizontal = 0;




    public MyTextView(Context context) {
        super(context);
        init();
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        super.setPadding(left, top, right, bottom);
        Log.i("app", " " + left + top + right + bottom);
    }

    private void init() {
        setPadding(0,5,0,5);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        final String text = calculateTextParams();

        final int left = mBounds.left;
        final int bottom = mBounds.bottom;
        mBounds.offset(-mBounds.left, -mBounds.top);
        mPaint.setAntiAlias(true);
        mPaint.setColor(getCurrentTextColor());
        canvas.clipRect(-left + (paddingHorizontal / 2), mBounds.bottom - bottom + (paddingVertical / 2), mBounds.width(), mBounds.height());
        canvas.drawText(text, -left + (paddingHorizontal / 2), mBounds.bottom - bottom + (paddingVertical / 2), mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        init();
        calculateTextParams();
        setMeasuredDimension(mBounds.width() + 1 + paddingHorizontal, -mBounds.top + 1 + paddingVertical);
    }

    private String calculateTextParams() {
        paddingVertical = getPaddingBottom() + getPaddingTop();
        paddingHorizontal = getPaddingLeft() + getPaddingRight();

        final String text = getText().toString();
        final int textLength = text.length();
        mPaint.setTypeface(getTypeface());
        mPaint.setTextSize(getTextSize());
        mPaint.getTextBounds(text, 0, textLength, mBounds);
        if (textLength == 0) {
            mBounds.right = mBounds.left;
        }
        return text;
    }
//
//    @Override
//    protected void onDraw(@NonNull Canvas canvas) {
//        final String text = calculateTextParams();
//
//        final int left = mBounds.left;
//        final int bottom = mBounds.bottom;
////        mBounds.offset(-mBounds.left, -mBounds.top);
//        mPaint.setAntiAlias(true);
//        mPaint.setColor(getCurrentTextColor());
//        canvas.drawText(text, mBounds.left-1, mBounds.bottom+(mBounds.height()+1), mPaint);
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        calculateTextParams();
////        setMeasuredDimension(mBounds.width() + 1, -mBounds.top + 1);
////        setMeasuredDimension(mBounds.width() + 1, -mBounds.top + mBounds.bottom+10);
//        setMeasuredDimension(mBounds.width(), mBounds.height());
//    }
//
//    private String calculateTextParams() {
//        final String text = getText().toString();
//        final int textLength = text.length();
////        mPaint.setTypeface(getTypeface());
//        mPaint.setTextSize(getTextSize());
//        mPaint.getTextBounds(text, 0, textLength, mBounds);
////        Toast.makeText(getContext(), mBounds.toString(), Toast.LENGTH_LONG).show();
//        Log.i("app",  " " + mBounds.left + " " + mBounds.top + " " + mBounds.right + " " + mBounds.bottom + "\n" + mBounds.width() + " " + mBounds.height());
//        Log.i("app",  " " + getMeasuredWidth() + " " + getMeasuredHeight());
//        if (textLength == 0) {
//            mBounds.right = mBounds.left;
//        }
//        return text;
//    }
}