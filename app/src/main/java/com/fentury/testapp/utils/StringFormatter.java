package com.fentury.testapp.utils;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by Vadim on 20.11.2016.
 */

public class StringFormatter {
    Context context;

    public StringFormatter(Context context) {
        this.context = context;
    }

    public String formatString(int resId, String str) {
        String result1 = "";
        try {
            result1 = context.getString(resId);
        } catch (Resources.NotFoundException e) {

        }
        return String.format("%s %s", result1, (str == null) ? "" : str).trim();
    }
}
