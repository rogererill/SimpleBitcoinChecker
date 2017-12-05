package com.erill.simplebitcoinchecker.manager;

import com.erill.simplebitcoinchecker.api.BitcoinApi;
import com.erill.simplebitcoinchecker.di.AppComponent;
import com.erill.simplebitcoinchecker.model.BitcoinInfo;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Roger on 5/12/17.
 */

public class BitcoinManager {

    @Inject
    BitcoinApi bitcoinApi;

    public BitcoinManager(AppComponent applicationComponent) {
        applicationComponent.inject(this);
    }

    public Observable<BitcoinInfo> getBitcoinInfo() {
        return bitcoinApi.getPriceData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
