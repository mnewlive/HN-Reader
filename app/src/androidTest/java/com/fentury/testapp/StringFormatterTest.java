package com.fentury.testapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.fentury.testapp.utils.StringFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Created by morozov on 12/1/16.
 */

@RunWith(AndroidJUnit4.class)
public class StringFormatterTest {
    private Context targetContext = InstrumentationRegistry.getTargetContext();

    @Test
    public void testFormatString() {
        StringFormatter stringFormatter = new StringFormatter(targetContext);

        assertEquals("", stringFormatter.formatString(0, null));
        assertEquals("Title:", stringFormatter.formatString(R.string.name, null));
        assertEquals("Username", stringFormatter.formatString(0, "Username"));
        assertEquals("Username: Username", stringFormatter.formatString(R.string.creator, "Username"));
        assertEquals("Username", stringFormatter.formatString(Integer.MAX_VALUE, "Username"));
        assertEquals("Username", stringFormatter.formatString(Integer.MIN_VALUE, "Username"));
    }
}
