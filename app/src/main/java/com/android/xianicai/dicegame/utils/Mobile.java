package com.android.xianicai.dicegame.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class Mobile {

    /** 屏幕宽度 */
    public static int SCREEN_WIDTH;

    /** 屏幕高度 */
    public static int SCREEN_HEIGHT;

    /** 屏幕density */
    public static float DENSITY;

    /** 屏幕density */
    public static float SCALED_DENSITY;

    public static void init(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        SCREEN_WIDTH = metrics.widthPixels;
        SCREEN_HEIGHT = metrics.heightPixels;
        DENSITY = metrics.density;
        SCALED_DENSITY = metrics.scaledDensity;
        metrics = null;
    }

}
