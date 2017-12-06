package com.erill.simplebitcoinchecker.di;

import com.erill.simplebitcoinchecker.manager.BitcoinManager;
import com.erill.simplebitcoinchecker.view.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Roger on 5/12/17.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(BitcoinManager bitcoinManager);

    void inject(MainActivity mainActivity);
}
