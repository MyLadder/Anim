package com.kannan.ornate;

import java.util.List;

/**
 * Created by kannan on 16/7/17.
 */

abstract public class AnimationEffect {

    private AnimTextView mTextView;
    private BoxView mBoxView;
    private float mLineWidth;
    private List<AnimationStage> stages;


    public AnimationEffect(AnimTextView textView, BoxView boxView, float lineWidth) {
        mTextView = textView;
        mBoxView = boxView;
        mLineWidth = lineWidth;
        stages = getAnimationStages();
    }

    abstract protected List<AnimationStage> getAnimationStages();

    public void update(float progress) {
        for (AnimationStage stage : stages) {
            if (stage.needToUpdate(progress)) {
                stage.update(progress);
            }
        }
//        mTextView.setProgress(progress);
        mBoxView.update();
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
