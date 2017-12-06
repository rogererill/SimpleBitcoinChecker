package com.erill.simplebitcoinchecker.di;

import android.content.Context;

import com.erill.simplebitcoinchecker.BaseApplication;
import com.erill.simplebitcoinchecker.manager.BitcoinManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Roger on 5/12/17.
 */

@Module
public class AppModule {

    private BaseApplication application;

    public AppModule(BaseApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }


    @Provides
    @Singleton
    public BitcoinManager providesMatchManager() {
        return new BitcoinManager(ComponentFactory.getApplicationComponent(application));
    }

}
