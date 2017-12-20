package com.erill.simplebitcoinchecker.view.main;

import com.erill.simplebitcoinchecker.manager.BitcoinManager;
import com.erill.simplebitcoinchecker.model.BitcoinInfo;
import com.erill.simplebitcoinchecker.view.base.BasePresenter;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Roger on 5/12/17.
 */

public class MainPresenter extends BasePresenter<MainView> {

    @Inject
    BitcoinManager bitcoinManager;

    @Inject
    public MainPresenter() {
    }

    public void getBitcoinData() {
        getView().showLoading();

        bitcoinManager.getBitcoinInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BitcoinInfo>() {
                    @Override
                    public void call(BitcoinInfo bitcoinInfo) {
                        getView().dismissLoading();
                        getView().showInfo(bitcoinInfo);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getView().dismissLoading();
                        getView().showError(throwable.getMessage());
                    }
                });
    }
}
