package com.fentury.testapp;

import android.content.Context;

import com.fentury.testapp.utils.StringFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by morozov on 12/1/16.
 */

@RunWith(JUnit4.class)
public class StringFormatterTest {
    Context context;

    @Test
    public void testFormatString() {
        StringFormatter stringFormatter = new StringFormatter(context);

        assertEquals("", stringFormatter.formatString(null, 0));
        assertEquals("", stringFormatter.formatString(null, R.string.name));
        assertEquals("Username", stringFormatter.formatString("Username", 0));
        assertEquals("Username Username:", stringFormatter.formatString("Username", R.string.creator));
    }
}
