package com.erill.simplebitcoinchecker.utils;

import android.util.Log;

import com.erill.simplebitcoinchecker.view.main.MainActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Roger on 9/12/17.
 */

public abstract class Utils {

    private Utils() {}

    public static String getLastHourUpdate(Date originDate) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(originDate);
    }

    public static double getDoubleFromString(String text) {
        double value = 0;
        if (!text.isEmpty()) {
            try {
                value = Double.valueOf(text);
            } catch (NumberFormatException e) {
                Log.e(MainActivity.TAG, "Error getting value of " + text);
                value = 0;
            }
        }
        return value;
    }
}
