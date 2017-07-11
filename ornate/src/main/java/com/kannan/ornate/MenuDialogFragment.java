package com.kannan.ornate;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by kannan on 28/6/17.
 */

public class MenuDialogFragment extends DialogFragment {

    private static final String BUNDLE_MENU_OPTIONS = "bundle_menu_options";

//    private MenuDialogFragmentOptions mMenuOptions;
    private MenuSystem mMenuSystem;
    private RelativeLayout mRootContainer;

    private int mMarginLeft = 0;
    private int mMarginTop = 0;
    private int mMarginRight = 0;
    private int mMarginBottom = 0;


//    public static MenuDialogFragment newInstance(MenuDialogFragmentOptions options) {
//        MenuDialogFragment fragment = new MenuDialogFragment();
//        Bundle args = new Bundle();
//        args.putSerializable(BUNDLE_MENU_OPTIONS, options);
//        fragment.setArguments(args);
//
//        return fragment;
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.MenuDialogFragmentStyle);
//        if (getArguments() != null) {
//            mMenuOptions = (MenuDialogFragmentOptions) getArguments().getSerializable(BUNDLE_MENU_OPTIONS);
//        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.temp, container, false);

        mRootContainer = (RelativeLayout) root.findViewById(R.id.root_container);
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT
//        );
//        lp.setMargins(mMarginLeft, mMarginTop, mMarginRight, mMarginBottom);
//        mRootContainer.setLayoutParams(lp);

        //remove
        mRootContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMenuSystem.toggleMenu();
            }
        });

        initMenuSystem();

        return root;
    }

    public void setMenuSystem(MenuSystem ms) {
        mMenuSystem = ms;
    }

    public void setMargin(int left, int top, int right, int bottom) {
        mMarginLeft = left;
        mMarginTop = top;
        mMarginRight = right;
        mMarginBottom = bottom;
    }

    private void initMenuSystem() {
        if (mMenuSystem != null) {
            mMenuSystem.buildUpon(mRootContainer);
            final Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(isAdded()) {
                        mMenuSystem.toggleMenu();
                    } else {
                        h.postDelayed(this, 100);
                    }
                }
            }, 100);
//            mMenuSystem.toggleMenu();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


}
