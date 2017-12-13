package com.erill.simplebitcoinchecker.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Roger on 9/12/17.
 */

public abstract class Utils {

    public static final String TAG = "Utils";

    private Utils() {}

    public static String getLastHourUpdate(Date originDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", new Locale("es", "ES"));
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
        return dateFormat.format(originDate);
    }

    public static double getDoubleFromString(String text) {
        double value = 0;
        if (!text.isEmpty()) {
            try {
                value = Double.valueOf(text);
            } catch (NumberFormatException e) {
                Log.e(TAG, "Error getting value of " + text);
                value = 0;
            }
        }
        return value;
    }

    public static Date getDateFromString(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
        try {
            final Date parsedDate = simpleDateFormat.parse(time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parsedDate);
            calendar.add(Calendar.SECOND, getOffsetFromUtc());
            return calendar.getTime();
        } catch (ParseException e) {
            Log.e(TAG, "error parsing: " + time);
        }
        return new Date();
    }

    private static int getOffsetFromUtc() {
        final TimeZone timezone = TimeZone.getDefault();
        Date now = new Date();
        return timezone.getOffset(now.getTime()) / (1000);
    }

    @NonNull
    public static String formatPrice(double price) {
        final String formattedPrice = String.format("%.2f", price);
        return String.valueOf(formattedPrice) + "€";
    }
}
