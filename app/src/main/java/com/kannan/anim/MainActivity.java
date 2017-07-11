package com.kannan.anim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kannan.ornate.AnimTextView;
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

    List<AnimTextView.Mode> modes = Arrays.asList(
            AnimTextView.Mode.A,
            AnimTextView.Mode.B,
            AnimTextView.Mode.C,
            AnimTextView.Mode.D,
            AnimTextView.Mode.E,
            AnimTextView.Mode.F,
            AnimTextView.Mode.G,
            AnimTextView.Mode.H,
            AnimTextView.Mode.I,
            AnimTextView.Mode.J,
            AnimTextView.Mode.K,
            AnimTextView.Mode.L,
            AnimTextView.Mode.M
    );


    private void show() {
        AnimatorSet animSet = new AnimatorSet();
        List<Animator> al = new ArrayList<>();
        for (AnimTextView.Mode mode : modes) {
            AnimTextView v = new AnimTextView(getApplicationContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            lp.gravity = Gravity.CENTER;
            v.setLayoutParams(lp);
            v.setText("ORNATE");
            v.setTextAppearance(getApplicationContext(), R.style.main_title_left);
            v.setMode(mode);
            v.setRevealFactor(0.0f);
            al.add(
                    ObjectAnimator.ofFloat(v, "revealFactor", 0.0f, 1.0f).setDuration(2000)
            );
            root.addView(v);
        }
        animSet.playSequentially(al);
        animSet.start();
    }





}
