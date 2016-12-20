package com.fentury.testapp.utils

import android.content.Context
import android.content.res.Resources

/**
 * Created by morozov on 12/5/16.
 */
class StringFormatter(val context: Context) {

    fun formatString(resId: Int, str: String?): String {
        var result1 = ""

        try {
            result1 = context.getString(resId)
        } catch (e: Resources.NotFoundException) {

        }
        return String.format("%s %s", result1, str ?: "" ).trim { it <= ' ' }
    }
}