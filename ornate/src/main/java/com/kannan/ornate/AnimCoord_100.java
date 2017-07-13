package com.kannan.ornate;

import static com.kannan.ornate.Utils.normalize;

/**
 * Created by kannan on 13/7/17.
 */



public class AnimCoord_100 implements AnimationCoordinator {

    private AnimTextView mTextView;

    private BoxView mBoxView;
    private float mLineWidth;

    public AnimCoord_100(AnimTextView textView, BoxView boxView, float lineWidth) {
        mTextView = textView;
        mBoxView = boxView;
        mLineWidth = lineWidth;
    }

    @Override
    public void animate(float progress) {
        if (progress <= 0.025f) {
            mBoxView.getLineLeft().setScale(0.0f);
            mBoxView.getLineRight().setScale(0.0f);
            mBoxView.getLineTop().setScale(0.0f);
//            mBoxView.getLineTop().setScale(normalize(progress, 0.0f, 0.05f));
//            mBoxView.getLineTop().setThickness(mLineWidth * normalize(progress, 0.0f, 0.05f));
//            mBoxView.getLineTop().setPivotRatio(0.5f);
            mBoxView.getLineBottom().setScale(normalize(progress, 0.0f, 0.025f));
//            mBoxView.getLineBottom().setThickness(mLineWidth);
            mBoxView.getLineBottom().setThickness(mLineWidth * normalize(progress, 0.0f, 0.025f));
            mBoxView.getLineBottom().setPivotRatio(0.5f);
            mTextView.setMode(AnimTextView.Mode.J);
            mTextView.setProgress(0.0f);
        } else if (progress <= 0.1f) {
            mBoxView.getLineLeft().setScale(0.0f);
            mBoxView.getLineRight().setScale(0.0f);
            mBoxView.getLineTop().setScale(1.0f);
            mBoxView.getLineTop().setThickness(mLineWidth);
            mBoxView.getLineBottom().setScale(1.0f);
            mBoxView.getLineBottom().setThickness(mLineWidth);
            mTextView.setProgress(normalize(progress, 0.025f, 0.1f));
        } else if (progress <= 0.9f) {
            mBoxView.getLineLeft().setScale(normalize(progress, 0.1f, 0.9f));
            mBoxView.getLineLeft().setPivotRatio(0.0f);
            mBoxView.getLineLeft().setThickness(mLineWidth);
            mBoxView.getLineRight().setScale(normalize(progress, 0.1f, 0.9f));
            mBoxView.getLineRight().setPivotRatio(1.0f);
            mBoxView.getLineRight().setThickness(mLineWidth);
            mBoxView.getLineTop().setScale(1 - normalize(progress, 0.1f, 0.9f));
            mBoxView.getLineTop().setPivotRatio(0.0f);
            mBoxView.getLineBottom().setScale(1 - normalize(progress, 0.1f, 0.9f));
            mBoxView.getLineBottom().setPivotRatio(1.0f);
            mTextView.setProgress(1.0f);
        } else if (progress <= 0.975f) {
            mBoxView.getLineLeft().setScale(1.0f);
            mBoxView.getLineLeft().setThickness(mLineWidth);
            mBoxView.getLineRight().setScale(1.0f);
            mBoxView.getLineRight().setThickness(mLineWidth);
            mBoxView.getLineTop().setScale(0.0f);
            mBoxView.getLineBottom().setScale(0.0f);
            mTextView.setMode(AnimTextView.Mode.I);
            mTextView.setProgress(1 - normalize(progress, 0.9f, 0.975f));
        } else if(progress <= 1.0f) {
//            mBoxView.getLineLeft().setScale(0.0f);
            mBoxView.getLineLeft().setScale(1 - normalize(progress, 0.975f, 1.0f));
            mBoxView.getLineLeft().setThickness(mLineWidth * (1 - normalize(progress, 0.95f, 1.0f)));
            mBoxView.getLineLeft().setPivotRatio(0.5f);
            mBoxView.getLineRight().setScale(0.0f);
//            mBoxView.getLineRight().setThickness(mLineWidth);
//            mBoxView.getLineRight().setThickness(mLineWidth * (1 - normalize(progress, 0.95f, 1.0f)));
//            mBoxView.getLineRight().setPivotRatio(0.5f);
            mBoxView.getLineTop().setScale(0.0f);
            mBoxView.getLineBottom().setScale(0.0f);
            mTextView.setProgress(0.0f);
        }
        mBoxView.setProgress(progress);

    }
}
