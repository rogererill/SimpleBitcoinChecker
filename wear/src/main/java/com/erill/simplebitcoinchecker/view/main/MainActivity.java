package com.erill.simplebitcoinchecker.view.main;

import android.os.Bundle;
import android.support.wear.widget.BoxInsetLayout;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.TextView;

import com.erill.simplebitcoinchecker.BaseApplication;
import com.erill.simplebitcoinchecker.R;
import com.erill.simplebitcoinchecker.di.AppComponent;
import com.erill.simplebitcoinchecker.model.BitcoinInfo;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends WearableActivity implements MainView {

    public static final int FAKE_PRICE_INTERVAL = 100;
    public static final int FAKE_INITIAL_PRICE = 9450;

    @BindView(R.id.container)
    BoxInsetLayout container;
    @BindView(R.id.bitcoin_value)
    TextView textValue;

    @Inject
    MainPresenter mainPresenter;

    String priceValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        AppComponent appComponent = ((BaseApplication) getApplication()).getAppComponent();
        appComponent.inject(this);
        mainPresenter.bindView(this);

        initPrice();
        initListeners();
        // Enables Always-on
        setAmbientEnabled();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        getNewPrice();

    }

    @Override
    public void onExitAmbient() {
        super.onExitAmbient();
        // We want to force getting a new price
        getNewPrice();
    }

    private void initListeners() {
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNewPrice();
            }
        });
    }

    private void getNewPrice() {
        // We randomly create a new value
        /*double newPrice = (Math.random() * FAKE_PRICE_INTERVAL) + FAKE_INITIAL_PRICE;
        priceValue = String.format("%.2f", newPrice);
        printValue();*/
        mainPresenter.getBitcoinData();
    }

    private void initPrice() {
        priceValue = String.valueOf(FAKE_INITIAL_PRICE);
        printValue();
    }

    private void printValue() {
        textValue.setText(getString(R.string.bitcoin_value, priceValue));
    }

    @Override
    public void showLoading() {
        //TODO
    }

    @Override
    public void dismissLoading() {
        //TODO
    }

    @Override
    public void showInfo(BitcoinInfo bitcoinInfo) {
        double price = bitcoinInfo.getPrice();
        priceValue = String.format("%.2f", price);
        printValue();
    }

    @Override
    public void showError(String error) {
        //TODO
    }
}
