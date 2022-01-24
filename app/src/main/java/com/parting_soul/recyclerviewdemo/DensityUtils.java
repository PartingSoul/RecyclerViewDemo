package com.parting_soul.recyclerviewdemo;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * @author parting_soul
 * @date 2022/1/23
 */
public class DensityUtils {
    public static int dpToPx(Context context, int dp) {
      DisplayMetrics metrics =  context.getResources().getDisplayMetrics();
      return (int) (metrics.density * dp + 0.5f);
    }
}
