package com.kannan.ornate;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by kannan on 12/7/17.
 */

public class BoxTextView extends FrameLayout {

    private Context mContext;

    private AnimTextView mTextView;
    private BoxView mBoxView;

    private int mTextMargin = 30;

    private int mLineWidth = 10;

    private int mLineColor = Color.RED;


    private float mProgress = 1.0f;

    private AnimationCoordinator mAnimCoord;

    private AnimationEffect effect;

    public BoxTextView(Context context) {
        super(context);
        init(context, null);
    }

    public BoxTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BoxTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;

        this.setLayoutParams(
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );
//        setBackgroundColor(Color.BLUE);
//        setOrientation();

        mBoxView = new BoxView(mContext);           // send attrs down ?
        LayoutParams boxLP = new LayoutParams(

                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.FILL_PARENT
//                200,200
        );
//        boxLP.addRule(ALIGN_PARENT_LEFT, TRUE);
//        boxLP.addRule(ALIGN_PARENT_TOP, TRUE);
//        boxLP.addRule(ALIGN_PARENT_RIGHT, TRUE);
//        boxLP.addRule(ALIGN_PARENT_BOTTOM, TRUE);
        mBoxView.setLayoutParams(boxLP);
//        mBoxView.setBackgroundColor(Color.YELLOW);

        mTextView = new AnimTextView(mContext);
        LayoutParams textLP = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
//        textLP.addRule(ALIGN_PARENT_LEFT, TRUE);
//        textLP.addRule(ALIGN_PARENT_TOP, TRUE);
//        textLP.addRule(ALIGN_PARENT_RIGHT, TRUE);
//        textLP.addRule(ALIGN_PARENT_BOTTOM, TRUE);
        textLP.setMargins(mTextMargin, mTextMargin, mTextMargin, mTextMargin);
        textLP.gravity = Gravity.CENTER;
        mTextView.setLayoutParams(textLP);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mTextView.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        }
        mTextView.setBackgroundColor(Color.TRANSPARENT);
        mTextView.setAnimationType(AnimTextView.AnimationType.L);
        mTextView.setText("ORNATE");    //.setText("aojkiljpgtyoe");
        mTextView.setTextAppearance(mContext, R.style.main_title_left);

        this.addView(mBoxView);
        this.addView(mTextView);
//        mBoxView.bringToFront();

        mBoxView.setRectSource(mTextView);
        mAnimCoord = new AnimCoord_14(mTextView, mBoxView, mLineWidth);
        effect = new Effect_2(mTextView, mBoxView, mLineWidth);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        int textWidthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSpecSize, MeasureSpec.UNSPECIFIED);
        int textHeightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSpecSize, MeasureSpec.UNSPECIFIED);
        measureChild(mTextView, textWidthMeasureSpec, textHeightMeasureSpec);
        measureChild(mTextView,
                // move to AnimTextView
                MeasureSpec.makeMeasureSpec(mTextView.getMeasuredWidth() + 80, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(mTextView.getMeasuredHeight(), MeasureSpec.EXACTLY));

        int requiredWidth = mTextView.getMeasuredWidth() + getPaddingLeft() + getPaddingRight() + 2 * mTextMargin;
        int requiredHeight = mTextView.getMeasuredHeight() + getPaddingTop() + getPaddingBottom() + 2 * mTextMargin;

        measureChild(mBoxView, MeasureSpec.makeMeasureSpec(requiredWidth, MeasureSpec.EXACTLY)
        ,MeasureSpec.makeMeasureSpec(requiredHeight, MeasureSpec.EXACTLY));

        setMeasuredDimension(requiredWidth, requiredHeight);

    }

    public void setAnimCooard(AnimationCoordinator coord){
        mAnimCoord = coord;
    }

    public void setProgress(float progress) {
        mProgress = progress;
//        mAnimCoord.animate(progress);
        effect.update(progress);

    }

    public AnimTextView getTextView() {
        return mTextView;
    }

    public BoxView getBoxView() {
        return mBoxView;
    }


    public float getLineWidth() {
        return mLineWidth;
    }


}
