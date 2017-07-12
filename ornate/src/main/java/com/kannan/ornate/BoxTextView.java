package com.kannan.ornate;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by kannan on 12/7/17.
 */

public class BoxTextView extends RelativeLayout {

    private Context mContext;

    private AnimTextView mTextView;
    private BoxView mBoxView;

    private int mTextMargin = 30;

    private int mLineWidth = 10;

    private int mLineColor = Color.RED;


    private float mProgress = 1.0f;


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
        setBackgroundColor(Color.BLUE);

        mBoxView = new BoxView(mContext);           // send attrs down ?
        RelativeLayout.LayoutParams boxLP = new RelativeLayout.LayoutParams(

                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
//                200,200
        );
        boxLP.addRule(ALIGN_PARENT_LEFT, TRUE);
        boxLP.addRule(ALIGN_PARENT_TOP, TRUE);
        boxLP.addRule(ALIGN_PARENT_RIGHT, TRUE);
        boxLP.addRule(ALIGN_PARENT_BOTTOM, TRUE);
        mBoxView.setLayoutParams(boxLP);
        mBoxView.setBackgroundColor(Color.YELLOW);

        mTextView = new AnimTextView(mContext);
        RelativeLayout.LayoutParams textLP = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        textLP.addRule(ALIGN_PARENT_LEFT, TRUE);
        textLP.addRule(ALIGN_PARENT_TOP, TRUE);
//        textLP.addRule(ALIGN_PARENT_RIGHT, TRUE);
//        textLP.addRule(ALIGN_PARENT_BOTTOM, TRUE);
        textLP.setMargins(mTextMargin, mTextMargin, mTextMargin, mTextMargin);
        mTextView.setLayoutParams(textLP);
        mTextView.setBackgroundColor(Color.TRANSPARENT);
        mTextView.setMode(AnimTextView.Mode.J);
        mTextView.setText("ORNATE");
        mTextView.setTextAppearance(mContext, R.style.main_title_left);

        this.addView(mBoxView);
        this.addView(mTextView);

        mBoxView.setRectSource(mTextView);

    }


    public void setProgress(float progress) {
        mProgress = progress;

        mTextView.setProgress(progress);
        mBoxView.setProgress(progress);
    }

}
