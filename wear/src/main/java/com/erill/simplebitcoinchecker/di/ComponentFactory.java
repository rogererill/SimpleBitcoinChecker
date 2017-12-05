package com.erill.simplebitcoinchecker.di;

import android.content.Context;

import com.erill.simplebitcoinchecker.BaseApplication;

/**
 * Created by Roger on 5/12/17.
 */

public abstract class ComponentFactory {

    public static AppComponent getApplicationComponent(Context context) {
        return ((BaseApplication) context).getAppComponent();
    }
}
