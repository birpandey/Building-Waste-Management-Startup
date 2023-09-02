/*
 * Copyright (c)
 * 1/5/21 10:47 AM
 * 2021
 * omprakashtiwari
 */

package com.example.waste.pinutill;

import android.content.res.Resources;

public class Util {
    public static float dpToPx(float dp) {
        return  (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
