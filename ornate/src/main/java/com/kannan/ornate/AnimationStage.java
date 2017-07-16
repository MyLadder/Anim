package com.kannan.ornate;

import android.icu.text.UnicodeSetSpanner;
import android.view.animation.Interpolator;

/**
 * Created by kannan on 15/7/17.
 */

abstract public class AnimationStage {

    private float FROM_PROGRESS;
    private float TO_PROGRESS;
    private boolean INITIALIZED;
    private Interpolator mImterpolator;

    AnimationStage(float fromProgress, float toProgress, Interpolator interpolator) {
        FROM_PROGRESS = fromProgress;
        TO_PROGRESS = toProgress;
        INITIALIZED = false;
        mImterpolator = interpolator;
    }


    abstract protected void init();

    public boolean needToUpdate(float progress) {
        return FROM_PROGRESS <= progress && progress < TO_PROGRESS;
    }

    public void update(float progress) {
        if (!INITIALIZED) {
            init();
            INITIALIZED = true;
        }
        updateAnimation(progress);
    }

    abstract protected void updateAnimation(float progress);

    protected float getNormalized(float progress) {
        return mImterpolator.getInterpolation(
            Utils.normalize(progress, FROM_PROGRESS, TO_PROGRESS)
        );
    }
}
