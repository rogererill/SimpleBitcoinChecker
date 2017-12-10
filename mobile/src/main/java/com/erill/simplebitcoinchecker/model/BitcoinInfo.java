package com.erill.simplebitcoinchecker.model;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Roger on 5/12/17.
 */

public class BitcoinInfo {

    public static final String TAG = "BitcoinInfo";
    public static final int ERROR_VALUE = -1;

    private String price;
    private String time;

    public double getPrice() {
        try {
            return Double.parseDouble(price);
        } catch (NumberFormatException e) {
            if (price != null) {
                Log.e(TAG, "Error parsing price: " + price);
            }
            else {
                Log.e(TAG, "Cannot get price");
            }
            return ERROR_VALUE;
        }
    }

    public Date getTime() {
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.FRANCE);
        try {
            return df1.parse(time);
        } catch (ParseException e) {
            Log.e("BitcoinInfo", "error parsing: " + time);
        }
        return new Date();
    }
}
