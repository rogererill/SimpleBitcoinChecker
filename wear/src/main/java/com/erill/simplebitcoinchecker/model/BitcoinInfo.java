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

    private String price;
    private String time;

    public double getPrice() {
        return Double.parseDouble(price);
    }

    public Date getTime() {
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.FRANCE);
        try {
            return df1.parse(time);
        } catch (ParseException e) {
            Log.e("BitcoinInfo", "error parsing: " + time);
        }
        return new Date();
    }
}
