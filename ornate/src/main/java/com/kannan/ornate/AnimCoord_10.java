package com.kannan.ornate;

import static com.kannan.ornate.Utils.normalize;

/**
 * Created by kannan on 14/7/17.
 */

public class AnimCoord_10 implements AnimationCoordinator {


    private AnimTextView mTextView;

    private BoxView mBoxView;
    private float mLineWidth;

    public AnimCoord_10(AnimTextView textView, BoxView boxView, float lineWidth) {
        mTextView = textView;
        mBoxView = boxView;
        mLineWidth = lineWidth;
    }


    @Override
    public void animate(float progress) {

        if (progress <= 0.025) {
            mBoxView.getLineBottom().setScale(0.0f);
            mBoxView.getLineRight().setScale(0.0f);
            mBoxView.getLineTop().setScale(0.0f);
            mBoxView.getLineLeft().setScale(normalize(progress, 0.0f, 0.025f));
            mBoxView.getLineLeft().setThickness(mLineWidth * normalize(progress, 0.0f, 0.025f));
            mBoxView.getLineLeft().setPivotRatio(0.5f);
            mTextView.setAnimationType(AnimTextView.AnimationType.REVEAL_CENTER_VERTICAL);
            mTextView.setProgress(0.0f);
        } else if (progress <= 0.1f) {
            mBoxView.getLineLeft().setScale(1.0f);
            mBoxView.getLineLeft().setThickness(mLineWidth);
            mBoxView.getLineRight().setScale(1.0f);
            mBoxView.getLineRight().setThickness(mLineWidth);
            mTextView.setProgress(normalize(progress, 0.025f, 0.1f));
        } else if (progress <= 0.9f) {
            mTextView.setProgress(1.0f);
        } else if (progress <= 0.975) {
            mTextView.setProgress(1 - normalize(progress, 0.9f, 0.975f));
        } else if (progress <= 1.0f) {
            mBoxView.getLineRight().setScale(0.0f);
            mBoxView.getLineLeft().setScale(1 - normalize(progress, 0.975f, 1.0f));
            mBoxView.getLineLeft().setThickness(mLineWidth * (1 - normalize(progress, 0.975f, 1.0f)));
            mTextView.setProgress(0.0f);
        }
        mBoxView.setProgress(progress);
    }

}
