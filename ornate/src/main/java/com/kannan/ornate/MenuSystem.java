package com.kannan.ornate;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorInflater;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.List;

/**
 * Created by kannan on 28/6/17.
 */

public class MenuSystem {

    private static final int SCROLL_CONTAINER_MAX_WIDTH = 600;      // to dp
    private static final int SCROLL_CONTAINER_MAX_HEIGHT = 1000;      // to dp

    private static final int DEF_ANIMATION_DURATION = 300; // milli seconds
    private static final int MIN_ANIMATION_DURATION = 0; // milli seconds
    private static final int MAX_ANIMATION_DURATION = 1000;             // change max range


    private Context mContext;

    private RelativeLayout mRootContainer;
    private ImageView mMenuIcon;
    private AnimTextView mMainTitleLeft;
    private TextSwitcher mMainTitleRight;
    private TextSwitcher mSubTitle;
    private View mMarginLineView;
    private LinearLayout mOptionsContainer;

    View cover;

    private MenuData mMenuData;

    private int mAnimationDuration;

    private AnimatorSet mOpenAnimatorSet;
    private AnimatorSet mCloseAnimatorSet;

    protected boolean mIsMenuOpen;
    protected boolean mIsAnimating;


    private MenuSystem() {}

    public MenuSystem(Context context) {
        mContext = context;
        mOpenAnimatorSet = null;
        mCloseAnimatorSet = null;
        mIsMenuOpen = false;
        mIsAnimating = false;


    }

    public void buildUpon(RelativeLayout rootContainer) {
        mRootContainer = rootContainer;
        mMenuIcon = (ImageView) mRootContainer.findViewById(R.id.menu_icon);
        mMainTitleLeft = (AnimTextView) mRootContainer.findViewById(R.id.main_title_left);
        mMainTitleRight = (TextSwitcher) mRootContainer.findViewById(R.id.main_title_right);
        mSubTitle = (TextSwitcher) mRootContainer.findViewById(R.id.sub_title);
        mMarginLineView = mRootContainer.findViewById(R.id.margin_line);
        mOptionsContainer = (LinearLayout) mRootContainer.findViewById(R.id.options_container);
        cover = mRootContainer.findViewById(R.id.cover);


        createMenuViews();
        initialiseViewProperties();
        buildAnimatorSet();
    }

    private class TextViewFactory implements ViewSwitcher.ViewFactory {

        private int mStyle;

        TextViewFactory(int style) {
            mStyle = style;
        }

        @Override
        public View makeView() {
            TextView tv = new AnimTextView(mContext);
            tv.setTextAppearance(mContext, mStyle);
            return tv;
        }
    };

    private void createMenuViews() {
        mRootContainer.setBackgroundColor(mMenuData.getBackgroundColor());

        cover.setBackgroundColor(mMenuData.getBackgroundColor());

        if (mMenuData.getImageDrawable() != null) {
            mMenuIcon.setImageDrawable(mMenuData.getImageDrawable());
        } else if (mMenuData.getImageBitmap() != null) {
            mMenuIcon.setImageBitmap(mMenuData.getImageBitmap());
        } else if (mMenuData.getImageRes() != -1) {
            mMenuIcon.setImageResource(mMenuData.getImageRes());
        } else {
            mMenuIcon.setBackgroundColor(mMenuData.getImageColor());
        }

//        mMainTitleLeft.setFactory(new TextViewFactory(R.style.main_title_left));
//        mMainTitleLeft.setInAnimation(mContext, R.anim.slide_in_left);
//        mMainTitleLeft.setOutAnimation(mContext, R.anim.slide_out_left);
        mMainTitleLeft.setText("ORNATE");
        mMainTitleLeft.setTextAppearance(mContext, mMenuData.getMainTitleLeftStyle());
        mMainTitleRight.setFactory(new TextViewFactory(R.style.main_title_right));
        mMainTitleRight.setInAnimation(mContext, R.anim.slide_in_left);
        mMainTitleRight.setOutAnimation(mContext, R.anim.slide_out_left);
        mMainTitleRight.setText("");

//        mMainTitleRight.setTextAppearance(mContext, mMenuData.getMainTitleRightStyle());
//        mSubTitle.setFactory(new TextViewFactory(R.style.sub_title));
//        mSubTitle.setText(mMenuData.getSubTitle());
//        mSubTitle.setTextAppearance(mContext, mMenuData.getSubTitleStyle());

        mMarginLineView.setBackgroundColor(mMenuData.getMarginLineColor());
        // dp to px
        mMarginLineView.getLayoutParams().width = mMenuData.getMarginLineWidthDP();

        List<MenuOption> options = mMenuData.getMenuOptions();
        for (int i = 0; i < options.size(); i += 1) {
            MenuOption option = options.get(i);

            LinearLayout optionWrapper = new LinearLayout(mContext);
            LinearLayout.LayoutParams wrapperLP = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            optionWrapper.setOrientation(LinearLayout.VERTICAL);
            optionWrapper.setLayoutParams(wrapperLP);

            AnimTextView label = new AnimTextView(mContext);
            LinearLayout.LayoutParams labelLP = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
//                    20
            );
            labelLP.gravity = Gravity.LEFT;
            label.setLayoutParams(labelLP);
            label.setText(option.getLabel());
            label.setTextAppearance(mContext, mMenuData.getOptionLabelStyle());

            AnimTextView description = new AnimTextView(mContext);
            LinearLayout.LayoutParams descriptionLP = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            descriptionLP.gravity = Gravity.LEFT;
            description.setLayoutParams(descriptionLP);
            description.setText(option.getDescriptio());
            description.setTextAppearance(mContext, mMenuData.getOptionDescriptionStyel());

            optionWrapper.addView(label);
            optionWrapper.addView(description);
            mOptionsContainer.addView(optionWrapper);

            if (i < options.size() - 1) {
                Space space = new Space(mContext);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        Utils.dpToPixels(mContext, 10f)
                );
                space.setLayoutParams(lp);
                mOptionsContainer.addView(space);
            }
        }


    }

    private void initialiseViewProperties() {

    }

    private void buildAnimatorSet() {
//        Animation a = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_bottom);
//        AnimatorSet ar = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.anim.slide_in_bottom);
//        mMainTitleLeft.setAlpha(0f);
//        cover.setPivotX(cover.getX() + cover.getWidth());
//        Animator ar = AnimatorSpawner.forTranslationX(cover,0, -700);
        cover.setVisibility(View.GONE);
        Animator ar = ObjectAnimator.ofFloat(mMainTitleLeft, "revealFactor", 0.0f, 1.0f);
        AnimatorSet atr = new AnimatorSet();
        atr.playSequentially(ar);
//        atr.setStartDelay(2000);
        atr.setDuration(1000);
        mOpenAnimatorSet = atr; new AnimatorSet();

        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
//                mMainTitleLeft.setText("ORNATE");
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mMainTitleRight.setText("MENU");
//                        h.postDelayed(this, 1000);
                    }
                }, 0);
            }
        }, 0);

    }

    public void toggleMenu() {
        if (!mIsAnimating) {
            // control variables will be changed accordingly in AnimationListeners
            if (mIsMenuOpen) {
//                initialiseViewProperties(mAnimationModel);  // need this ?
//                mCloseAnimatorSet.start();
            } else {
                mOpenAnimatorSet.start();
            }
        }
    }

    private Animator.AnimatorListener mOpenAnimatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
            mIsAnimating = true;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            mIsAnimating = false;
            mIsMenuOpen = true;
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            mIsAnimating = false;
            mIsMenuOpen = true;
        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };

    private Animator.AnimatorListener mCloseAnimatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
            mIsAnimating = true;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            mIsAnimating = false;
            mIsMenuOpen = false;
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            mIsAnimating = false;
            mIsMenuOpen = false;
        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };


    public void setMenuData(MenuData data) {
        mMenuData = data;
    }
}
