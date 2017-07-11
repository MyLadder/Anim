package com.kannan.ornate;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;
import com.kannan.ornate.R;

/**
 * Created by kannan on 9/7/17.
 */

public class MenuData {

    private String mMainTitleLeft;
    private String mMainTitleRight;
    private String mSubtitle;

    private int mMainTitleLeftStyle;
    private int mMainTitleRightStyle;
    private int mSubTitleStyle;
    private int mOptionLabelStyle;
    private int mOptionDescriptionStyle;

    private int mImageRes;
    private Drawable mImageDrawable;
    private Bitmap mImageBitmap;
    private int mImageColor;

    private int mMarginLineColor;
    private int mMarginLineWidthDP;
    private int mBackgroundColor;

    private List<MenuOption> mMenuOptions;

    public MenuData() {
        mMainTitleLeft = "";
        mMainTitleRight = "";
        mSubtitle = "";

        mMainTitleLeftStyle = R.style.main_title_left;
        mMainTitleRightStyle = R.style.main_title_right;
        mSubTitleStyle = R.style.sub_title;
        mOptionLabelStyle = R.style.option_label;
        mOptionDescriptionStyle = R.style.option_description;


//        mMainTitleLeftStyle = R.style.main_title_left;
//        mMainTitleRightStyle = R.style.main_title_left;
//        mSubTitleStyle = R.style.main_title_left;
//        mOptionLabelStyle = R.style.main_title_left;
//        mOptionDescriptionStyle = R.style.main_title_left;

        mImageRes = -1;
        mImageDrawable = null;
        mImageBitmap = null;
        mImageColor = Color.TRANSPARENT;

        mMarginLineColor = Color.RED;
        mMarginLineWidthDP = 3;
        mBackgroundColor = Color.TRANSPARENT;

        mMenuOptions = new ArrayList<>();
    }

    /**
     * Setters
     */

    public void setMainTitleLeft(String str) {
        mMainTitleLeft = str;
    }

    public void setMainTitleRight(String str) {
        mMainTitleRight = str;
    }

    public void setSubTitle(String str) {
        mSubtitle = str;
    }

    public void setMainTitleLeftStyle(int style) {
        mMainTitleLeftStyle = style;
    }

    public void setMainTitleRightStyle(int style) {
        mMainTitleRightStyle = style;
    }

    public void setSubTitleStyle(int style) {
        mSubTitleStyle = style;
    }

    public void setOptionLabelStyle(int style) {
        mOptionLabelStyle = style;
    }

    public void setOptionDescriptionStyle(int style) {
        mOptionDescriptionStyle = style;
    }

    public void setImageRes(int resId) {
        mImageRes = resId;
        mImageDrawable = null;
        mImageBitmap = null;
        mImageColor = Color.TRANSPARENT;
    }

    public void setImageDrawable(Drawable drawable) {
        mImageRes = -1;
        mImageDrawable = drawable;
        mImageBitmap = null;
        mImageColor = Color.TRANSPARENT;
    }

    public void setImageBitmap(Bitmap bitmap) {
        mImageRes = -1;
        mImageDrawable = null;
        mImageBitmap = bitmap;
        mImageColor = Color.TRANSPARENT;
    }

    public void setImageColor(int color) {
        mImageRes = -1;
        mImageDrawable = null;
        mImageBitmap = null;
        mImageColor = color;
    }

    public void setMarginLineColor(int color) {
        mMarginLineColor = color;
    }

    public void setMarginLineWidthDP(int width) {
        mMarginLineWidthDP = width;
    }

    public void setmBackgroundColor(int color) {
        mBackgroundColor = color;
    }

    public void setMenuOptions(List<MenuOption> options) {
        mMenuOptions = options;
    }


    /**
     * Getters
     */

    public String getMainTitleLeft() {
        return mMainTitleLeft;
    }

    public String getMainTitleRight() {
        return mMainTitleRight;
    }

    public String getSubTitle() {
        return mSubtitle;
    }

    public int getMainTitleLeftStyle() {
        return mMainTitleLeftStyle;
    }

    public int getMainTitleRightStyle() {
        return mMainTitleRightStyle;
    }

    public int getSubTitleStyle() {
        return mSubTitleStyle;
    }

    public int getOptionLabelStyle() {
        return mOptionLabelStyle;
    }

    public int getOptionDescriptionStyel() {
        return mOptionDescriptionStyle;
    }

    public int getImageRes() {
        return mImageRes;
    }

    public Drawable getImageDrawable() {
        return mImageDrawable;
    }

    public Bitmap getImageBitmap() {
        return mImageBitmap;
    }

    public int getImageColor() {
        return mImageColor;
    }

    public int getMarginLineColor() {
        return mMarginLineColor;
    }

    public int getMarginLineWidthDP() {
        return mMarginLineWidthDP;
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    public List<MenuOption> getMenuOptions() {
        return mMenuOptions;
    }

}
