package com.erill.simplebitcoinchecker.view.main;

import android.content.Context;

import com.erill.simplebitcoinchecker.R;
import com.erill.simplebitcoinchecker.manager.BitcoinManager;
import com.erill.simplebitcoinchecker.model.BitcoinInfo;
import com.erill.simplebitcoinchecker.utils.Utils;
import com.erill.simplebitcoinchecker.view.base.BasePresenter;

import javax.inject.Inject;

import rx.functions.Action1;

import static com.erill.simplebitcoinchecker.model.BitcoinInfo.ERROR_VALUE;

/**
 * Created by Roger on 5/12/17.
 */

public class MainPresenter extends BasePresenter<MainView> {

    public static final String TAG = "MainPresenter";

    @Inject
    BitcoinManager bitcoinManager;

    @Inject
    Context context;

    private Double coinValue;

    @Inject
    public MainPresenter() {
    }

    public void getBitcoinData() {
        getView().showLoading();

        bitcoinManager.getBitcoinInfo()
                .subscribe(new Action1<BitcoinInfo>() {
                    @Override
                    public void call(BitcoinInfo bitcoinInfo) {
                        getView().dismissLoading();
                        coinValue = bitcoinInfo.getPrice();
                        if (coinValue == ERROR_VALUE) {
                            getView().showError();
                        } else {
                            getView().showInfo(bitcoinInfo);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getView().dismissLoading();
                        getView().showError();
                    }
                });
    }

    public String getUserBalance(double userQuantity) {
        if (coinValue == null) return context.getString(R.string.error);
        double balance = coinValue * userQuantity;
        return Utils.formatPrice(balance);
    }

}
