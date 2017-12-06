package com.erill.simplebitcoinchecker;

import android.app.Application;

import com.erill.simplebitcoinchecker.di.AppComponent;
import com.erill.simplebitcoinchecker.di.AppModule;
import com.erill.simplebitcoinchecker.di.DaggerAppComponent;

/**
 * Created by Roger on 5/12/17.
 */

public class BaseApplication extends Application {

    private AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = initDagger(this);
    }

    protected AppComponent initDagger(BaseApplication application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }
}
