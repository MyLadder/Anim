package com.kannan.ornate;

import android.content.Context;

/**
 * Created by kannan on 10/7/17.
 */

public class MenuBuilder {

    private Context mContext;
    private MenuData mMenuData;
    private float mMarginLeftDP;
    private float mMarginTopDP;
    private float mMarginRightDP;
    private float mMarginBottomDP;

    private MenuBuilder() {}

    public MenuBuilder(Context context) {
        mContext = context;
        mMenuData = null;
    }

    public MenuBuilder withMenuData(MenuData data) {
        mMenuData = data;
        return this;
    }

    public MenuBuilder withMarginDP(float left, float top, float right, float bottom) {
        mMarginLeftDP = left;
        mMarginTopDP = top;
        mMarginRightDP = right;
        mMarginBottomDP = bottom;
        return this;
    }

    public MenuDialogFragment build() {

        MenuSystem menuSytem = new MenuSystem(mContext);
        menuSytem.setMenuData(mMenuData);

        MenuDialogFragment menuFragment = new MenuDialogFragment();
        menuFragment.setMenuSystem(menuSytem);
        menuFragment.setMargin(
                Utils.dpToPixels(mContext, mMarginLeftDP),
                Utils.dpToPixels(mContext, mMarginTopDP),
                Utils.dpToPixels(mContext, mMarginRightDP),
                Utils.dpToPixels(mContext, mMarginBottomDP)
        );

        return menuFragment;
    }
}
