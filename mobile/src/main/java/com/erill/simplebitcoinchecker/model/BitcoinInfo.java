package com.erill.simplebitcoinchecker.model;

import android.util.Log;

import com.erill.simplebitcoinchecker.utils.Utils;

import java.util.Date;

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
        return Utils.getDateFromString(time);
    }

}
