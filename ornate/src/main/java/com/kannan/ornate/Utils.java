package com.kannan.ornate;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by kannan on 10/7/17.
 */

public class Utils {

    public static int dpToPixels(Context context, float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, context.getResources().getDisplayMetrics());
    }

}
