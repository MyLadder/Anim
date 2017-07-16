package com.kannan.ornate;

import android.graphics.Color;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kannan on 16/7/17.
 */

public class Effect_2 extends AnimationEffect {

    public Effect_2(AnimTextView textView, BoxView boxView, float lineWidth) {
        super(textView, boxView, lineWidth);
    }

    @Override
    protected List<AnimationStage> getAnimationStages() {
        return Arrays.asList(

                new AnimationStage(0.0f, 0.025f, new LinearInterpolator()) {
                    @Override
                    protected void init() {
                        getTextView().setAnimationType(AnimTextView.AnimationType.REVEAL_CENTER_VERTICAL_ARROW_INSIDE);
                        getTextView().setProgress(0.0f);
                        getBoxView().getLineLeft().setScale(0.0f);
//                        getBoxView().getLineLeft().setPivotRatio();
                        getBoxView().getLineTop().setScale(0.0f);
                        getBoxView().getLineBottom().setScale(0.0f);
                        getBoxView().getLineRight().setScale(0.0f);
                        getBoxView().getLineRight().setPivotRatio(0.5f);
//                        Toast.makeText(getBoxView().getContext(), "jbfkkjfh", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    protected void updateAnimation(float progress) {
//                        Toast.makeText(getBoxView().getContext(), "jbfkkjfh", Toast.LENGTH_LONG).show();
                        getBoxView().getLineRight().setScale(getNormalized(progress));
                        getBoxView().getLineRight().setThickness(getLineWidth() * getNormalized(progress));
                    }
                },
//
                new AnimationStage(0.025f, 0.1f, new LinearInterpolator()) {
                    @Override
                    protected void init() {
                        getBoxView().getLineRight().setScale(1.0f);
                        getBoxView().getLineRight().setThickness(getLineWidth());
                        getBoxView().getLineLeft().setScale(1.0f);
                        getBoxView().getLineLeft().setThickness(getLineWidth());
//                        getTextView().setDoubleLayer(false);
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
                        getBoxView().getLineLeft().setScale(0.0f);
                        getTextView().setProgress(0.0f);
                    }

                    @Override
                    protected void updateAnimation(float progress) {
                        getBoxView().getLineRight().setScale(1 - getNormalized(progress));
                        getBoxView().getLineRight().setThickness(getLineWidth() * (1 - getNormalized(progress)));
                    }
                },

                new AnimationStage(1.0f, 1.1f, new LinearInterpolator()) {
                    @Override
                    protected void init() {
                        getBoxView().getLineRight().setScale(0.0f);
                    }

                    @Override
                    protected void updateAnimation(float progress) {
                    }
                }
        );
    }

}
