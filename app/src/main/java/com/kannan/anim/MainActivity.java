package com.kannan.anim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.kannan.ornate.AnimCoord_1;
import com.kannan.ornate.AnimCoord_10;
import com.kannan.ornate.AnimCoord_100;
import com.kannan.ornate.AnimCoord_11;
import com.kannan.ornate.AnimCoord_12;
import com.kannan.ornate.AnimCoord_13;
import com.kannan.ornate.AnimCoord_14;
import com.kannan.ornate.AnimCoord_2;
import com.kannan.ornate.AnimCoord_2_1;
import com.kannan.ornate.AnimCoord_3;
import com.kannan.ornate.AnimCoord_4;
import com.kannan.ornate.AnimCoord_5;
import com.kannan.ornate.AnimCoord_6;
import com.kannan.ornate.AnimCoord_7;
import com.kannan.ornate.AnimCoord_8;
import com.kannan.ornate.AnimCoord_9;
import com.kannan.ornate.AnimTextView;
import com.kannan.ornate.BoxTextView;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LinearLayout root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        root = (LinearLayout) findViewById(R.id.container);
        show();
    }

    List<AnimTextView.AnimationType> animationTypes = Arrays.asList(
            AnimTextView.AnimationType.REVEAL_RIGHT,
            AnimTextView.AnimationType.REVEAL_LEFT,
            AnimTextView.AnimationType.REVEAL_DOWN,
            AnimTextView.AnimationType.REVEAL_UP,
            AnimTextView.AnimationType.E,
            AnimTextView.AnimationType.F,
            AnimTextView.AnimationType.G,
            AnimTextView.AnimationType.H,
            AnimTextView.AnimationType.REVEAL_CENTER_VERTICAL,
            AnimTextView.AnimationType.REVEAL_CENTER_HORIZONTAL,
            AnimTextView.AnimationType.K,
            AnimTextView.AnimationType.L,
            AnimTextView.AnimationType.M
    );



    private void show() {
        AnimatorSet animSet = new AnimatorSet();
        List<Animator> al = new ArrayList<>();
//        for (AnimTextView.AnimationType mode : animationTypes) {
//            AnimTextView v = new AnimTextView(getApplicationContext());
//            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT
//            );
//            lp.gravity = Gravity.CENTER;
//            v.setLayoutParams(lp);
//            v.setText("ORNATE\nMENU");
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//                v.setTextAlignment(TEXT_ALIGNMENT_CENTER);
//            }
//            v.setTextAppearance(getApplicationContext(), R.style.main_title_left);
//            v.setAnimationType(mode);
//            v.setProgress(0.0f);
//            al.add(
//                    ObjectAnimator.ofFloat(v, "progress", 0.0f, 1.0f).setDuration(1000)
//            );
//            root.addView(v);
        {
            BoxTextView atv = new BoxTextView(getApplicationContext());
            atv.setProgress(1.0f);
            atv.setAnimCooard(new AnimCoord_2_1(atv.getTextView(), atv.getBoxView(), atv.getLineWidth()));
            root.addView(atv);
            al.add(
                    ObjectAnimator.ofFloat(atv, "progress", 0.0f, 1.0f).setDuration(10000)
            );
        }
        {
            BoxTextView atv = new BoxTextView(getApplicationContext());
            atv.setProgress(1.0f);
            atv.setAnimCooard(new AnimCoord_2(atv.getTextView(), atv.getBoxView(), atv.getLineWidth()));
            root.addView(atv);
            al.add(
                    ObjectAnimator.ofFloat(atv, "progress", 0.0f, 1.0f).setDuration(5000)
            );
        }
        {
            BoxTextView atv = new BoxTextView(getApplicationContext());
            atv.setProgress(1.0f);
            atv.setAnimCooard(new AnimCoord_3(atv.getTextView(), atv.getBoxView(), atv.getLineWidth()));
            root.addView(atv);
            al.add(
                    ObjectAnimator.ofFloat(atv, "progress", 0.0f, 1.0f).setDuration(5000)
            );
        }
        {
            BoxTextView atv = new BoxTextView(getApplicationContext());
            atv.setProgress(1.0f);
            atv.setAnimCooard(new AnimCoord_4(atv.getTextView(), atv.getBoxView(), atv.getLineWidth()));
            root.addView(atv);
            al.add(
                    ObjectAnimator.ofFloat(atv, "progress", 0.0f, 1.0f).setDuration(5000)
            );
        }
        {
            BoxTextView atv = new BoxTextView(getApplicationContext());
            atv.setProgress(1.0f);
            atv.setAnimCooard(new AnimCoord_5(atv.getTextView(), atv.getBoxView(), atv.getLineWidth()));
            root.addView(atv);
            al.add(
                    ObjectAnimator.ofFloat(atv, "progress", 0.0f, 1.0f).setDuration(5000)
            );
        }
        {
            BoxTextView atv = new BoxTextView(getApplicationContext());
            atv.setProgress(1.0f);
            atv.setAnimCooard(new AnimCoord_6(atv.getTextView(), atv.getBoxView(), atv.getLineWidth()));
            root.addView(atv);
            al.add(
                    ObjectAnimator.ofFloat(atv, "progress", 0.0f, 1.0f).setDuration(5000)
            );
        }
        {
            BoxTextView atv = new BoxTextView(getApplicationContext());
            atv.setProgress(1.0f);
            atv.setAnimCooard(new AnimCoord_7(atv.getTextView(), atv.getBoxView(), atv.getLineWidth()));
            root.addView(atv);
            al.add(
                    ObjectAnimator.ofFloat(atv, "progress", 0.0f, 1.0f).setDuration(5000)
            );
        }
        {
            BoxTextView atv = new BoxTextView(getApplicationContext());
            atv.setProgress(1.0f);
            atv.setAnimCooard(new AnimCoord_8(atv.getTextView(), atv.getBoxView(), atv.getLineWidth()));
            root.addView(atv);
            al.add(
                    ObjectAnimator.ofFloat(atv, "progress", 0.0f, 1.0f).setDuration(5000)
            );
        }
        {
            BoxTextView atv = new BoxTextView(getApplicationContext());
            atv.setProgress(1.0f);
            atv.setAnimCooard(new AnimCoord_9(atv.getTextView(), atv.getBoxView(), atv.getLineWidth()));
            root.addView(atv);
            al.add(
                    ObjectAnimator.ofFloat(atv, "progress", 0.0f, 1.0f).setDuration(5000)
            );
        }
        {
            BoxTextView atv = new BoxTextView(getApplicationContext());
            atv.setProgress(1.0f);
            atv.setAnimCooard(new AnimCoord_10(atv.getTextView(), atv.getBoxView(), atv.getLineWidth()));
            root.addView(atv);
            al.add(
                    ObjectAnimator.ofFloat(atv, "progress", 0.0f, 1.0f).setDuration(5000)
            );
        }
        {
            BoxTextView atv = new BoxTextView(getApplicationContext());
            atv.setProgress(1.0f);
            atv.setAnimCooard(new AnimCoord_11(atv.getTextView(), atv.getBoxView(), atv.getLineWidth()));
            root.addView(atv);
            al.add(
                    ObjectAnimator.ofFloat(atv, "progress", 0.0f, 1.0f).setDuration(5000)
            );
        }
        {
            BoxTextView atv = new BoxTextView(getApplicationContext());
            atv.setProgress(1.0f);
            atv.setAnimCooard(new AnimCoord_12(atv.getTextView(), atv.getBoxView(), atv.getLineWidth()));
            root.addView(atv);
            al.add(
                    ObjectAnimator.ofFloat(atv, "progress", 0.0f, 1.0f).setDuration(5000)
            );
        }
        {
            BoxTextView atv = new BoxTextView(getApplicationContext());
            atv.setProgress(1.0f);
            atv.setAnimCooard(new AnimCoord_13(atv.getTextView(), atv.getBoxView(), atv.getLineWidth()));
            root.addView(atv);
            al.add(
                    ObjectAnimator.ofFloat(atv, "progress", 0.0f, 1.0f).setDuration(5000)
            );
        }
        {
            BoxTextView atv = new BoxTextView(getApplicationContext());
            atv.setProgress(1.0f);
            atv.setAnimCooard(new AnimCoord_14(atv.getTextView(), atv.getBoxView(), atv.getLineWidth()));
            root.addView(atv);
            al.add(
                    ObjectAnimator.ofFloat(atv, "progress", 0.0f, 1.0f).setDuration(5000)
            );
        }
        {
            BoxTextView atv = new BoxTextView(getApplicationContext());
            atv.setProgress(1.0f);
            atv.setAnimCooard(new AnimCoord_100(atv.getTextView(), atv.getBoxView(), atv.getLineWidth()));
            root.addView(atv);
            al.add(
                    ObjectAnimator.ofFloat(atv, "progress", 0.0f, 1.0f).setDuration(5000)
            );
        }
//        }
        animSet.playSequentially(al);
        animSet.start();
    }





}
