package com.example.android.bakingapp.userInterface;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class ScreenUtils {
    /* Return the width, height of the screen in pixels and the screen density (i.e. {720.0, 1280.0, 2.0})
     * @param context is used to create a windowManager, so we can get the screen metrics
     */
    public static float[] getScreenSize(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        return new float[]{
                (float) displayMetrics.widthPixels,
                (float) displayMetrics.heightPixels,
                displayMetrics.density};
    }
}
