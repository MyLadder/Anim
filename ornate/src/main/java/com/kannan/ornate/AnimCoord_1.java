package com.kannan.ornate;

import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kannan on 13/7/17.
 */

public class AnimCoord_1 extends AnimationEffect {

    public AnimCoord_1(AnimTextView textView, BoxView boxView, float lineWidth) {
        super(textView,boxView, lineWidth);

    }

    @Override
    protected List<AnimationStage> getAnimationStages() {
        return Arrays.asList(
                new AnimationStage(0.0f, 0.025f, new BounceInterpolator()) {
                    @Override
                    protected void init() {
                        getBoxView().getLineLeft().setScale(0.0f);
                        getBoxView().getLineRight().setScale(0.0f);
                        getBoxView().getLineTop().setScale(0.0f);
                        getBoxView().getLineBottom().setPivotRatio(0.5f);
                        getTextView().setAnimationType(AnimTextView.AnimationType.REVEAL_DOWN);
                        getTextView().setProgress(0.0f);
                    }

                    @Override
                    protected void updateAnimation(float progress) {
                        getBoxView().getLineBottom().setScale(getNormalized(progress));
                        getBoxView().getLineBottom().setThickness(getLineWidth() * getNormalized(progress));
                    }
                },

                new AnimationStage(0.025f, 0.1f, new LinearInterpolator()) {
                    @Override
                    protected void init() {
                        getBoxView().getLineBottom().setScale(1.0f);
                        getBoxView().getLineBottom().setThickness(getLineWidth());
                    }

                    @Override
                    protected void updateAnimation(float progress) {
                        getTextView().setProgress(getNormalized(progress));
                    }
                },

                new AnimationStage(0.1f, 0.9f, new LinearInterpolator()) {
                    @Override
                    protected void init() {
                        getTextView().setProgress(1.0f);
                    }

                    @Override
                    protected void updateAnimation(float progress) {
                    }
                },

                new AnimationStage(0.9f, 0.975f, new LinearInterpolator()) {
                    @Override
                    protected void init() {
                    }

                    @Override
                    protected void updateAnimation(float progress) {
                        getTextView().setProgress(1 - getNormalized(progress));
                    }
                },

                new AnimationStage(0.975f, 1.0f, new LinearInterpolator()) {
                    @Override
                    protected void init() {
                        getTextView().setProgress(0.0f);
                    }

                    @Override
                    protected void updateAnimation(float progress) {
                        getBoxView().getLineBottom().setScale(1 - getNormalized(progress));
                        getBoxView().getLineBottom().setThickness(getLineWidth() * (1 - getNormalized(progress)));
                    }
                }
        );
    }


//
//        if (progress <= 0.025) {
//            mBoxView.getLineLeft().setScale(0.0f);
//            mBoxView.getLineRight().setScale(0.0f);
//            mBoxView.getLineTop().setScale(0.0f);
//            mBoxView.getLineBottom().setScale(normalize(progress, 0.0f, 0.025f));
//            mBoxView.getLineBottom().setThickness(mLineWidth * normalize(progress, 0.0f, 0.025f));
//            mBoxView.getLineBottom().setPivotRatio(0.5f);
//            mTextView.setAnimationType(AnimTextView.AnimationType.REVEAL_DOWN);
//            mTextView.setProgress(0.0f);
//        } else if (progress <= 0.1f) {
//            mBoxView.getLineBottom().setScale(1.0f);
//            mBoxView.getLineBottom().setThickness(mLineWidth);
//            mTextView.setProgress(normalize(progress, 0.025f, 0.1f));
//        } else if (progress <= 0.9f) {
//            mTextView.setProgress(1.0f);
//        } else if (progress <= 0.975) {
//            mTextView.setProgress(1 - normalize(progress, 0.9f, 0.975f));
//        } else if (progress <= 1.0f) {
//            mBoxView.getLineBottom().setScale(1 - normalize(progress, 0.975f, 1.0f));
//            mBoxView.getLineBottom().setThickness(mLineWidth * (1 - normalize(progress, 0.975f, 1.0f)));
//            mTextView.setProgress(0.0f);
//        }
//        mBoxView.setProgress(progress);
//    }
}
