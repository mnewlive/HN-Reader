package com.fentury.testapp.utils;

import android.content.Context;

/**
 * Created by Vadim on 20.11.2016.
 */

public class StringFormatter {
    Context context;

    public StringFormatter(Context context) {
        this.context = context;
    }

    public String formatString(String str, int resId) {
        return String.format("%s %s", context.getResources().getString(resId), str);
    }
}
