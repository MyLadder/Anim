package com.kannan.ornate;

import android.graphics.Color;
import android.view.animation.LinearInterpolator;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kannan on 16/7/17.
 */

public class Effect_1 extends AnimationEffect {

    public Effect_1(AnimTextView textView, BoxView boxView, float lineWidth) {
        super(textView, boxView, lineWidth);
    }

    @Override
    protected List<AnimationStage> getAnimationStages() {
        return Arrays.asList(

                new AnimationStage(0.0f, 0.025f, new LinearInterpolator()) {
                    @Override
                    protected void init() {
                        getTextView().setProgress(0.00001f);
                        getTextView().setAnimationType(AnimTextView.AnimationType.REVEAL_RIGHT);
                        getBoxView().getLineLeft().setScale(0.0f);
                        getBoxView().getLineTop().setScale(0.0f);
                        getBoxView().getLineBottom().setScale(0.0f);
                        getBoxView().getLineRight().setPivotRatio(0.5f);
                    }

                    @Override
                    protected void updateAnimation(float progress) {
                        getBoxView().getLineRight().setScale(getNormalized(progress));
                        getBoxView().getLineRight().setThickness(getLineWidth() * getNormalized(progress));
                    }
                },

                new AnimationStage(0.025f, 0.1f, new LinearInterpolator()) {
                    @Override
                    protected void init() {
                        getBoxView().getLineRight().setScale(1.0f);
                        getBoxView().getLineRight().setThickness(getLineWidth());
                        getTextView().setDoubleLayer(false);
                    }

                    @Override
                    protected void updateAnimation(float progress) {
                        getTextView().setProgress(getNormalized(progress));
                    }
                },

                new AnimationStage(0.1f, 0.175f, new LinearInterpolator()) {
                    @Override
                    protected void init() {
                        getBoxView().getLineRight().setScale(0.0f);
                        getBoxView().setMode(BoxView.Mode.MODE_BOX);
                        getBoxView().getRectangle().setColor(Color.YELLOW);
                        getBoxView().getRectangle().setStyle(BoxView.Shape.STYLE_FILL_STROKE);
                        getBoxView().getRectangle().setStrockWidth(getLineWidth());
                        getTextView().setDoubleLayer(true);
                        getTextView().setAnimationType(AnimTextView.AnimationType.REVEAL_LEFT);

                    }

                    @Override
                    protected void updateAnimation(float progress) {
                        getTextView().setProgress(getNormalized(progress));
                    }
                },

                new AnimationStage(0.175f, 0.825f, new LinearInterpolator()) {
                    @Override
                    protected void init() {
                        getTextView().setProgress(1.0f);
                    }

                    @Override
                    protected void updateAnimation(float progress) {
                    }
                },

                new AnimationStage(0.825f, 0.9f, new LinearInterpolator()) {
                    @Override
                    protected void init() {

                    }

                    @Override
                    protected void updateAnimation(float progress) {
                        getTextView().setProgress(1 - getNormalized(progress));
                    }
                },

                new AnimationStage(0.9f, 0.975f, new LinearInterpolator()) {
                    @Override
                    protected void init() {
                        getTextView().setDoubleLayer(false);
                        getTextView().setAnimationType(AnimTextView.AnimationType.REVEAL_RIGHT);
                        getTextView().setProgress(1.0f);
                        getBoxView().setMode(BoxView.Mode.MODE_LINES);
                        getBoxView().getLineRight().setScale(1.0f);
                        getBoxView().getLineRight().setThickness(getLineWidth());
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
                        getBoxView().getLineRight().setScale(1 - getNormalized(progress));
                        getBoxView().getLineRight().setThickness(getLineWidth() * (1 - getNormalized(progress)));
                    }
                }
        );
    }
}
