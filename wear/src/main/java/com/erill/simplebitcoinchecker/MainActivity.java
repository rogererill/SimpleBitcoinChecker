package com.erill.simplebitcoinchecker;

import android.os.Bundle;
import android.support.wear.widget.BoxInsetLayout;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends WearableActivity {

    public static final int FAKE_PRICE_INTERVAL = 100;
    public static final int FAKE_INITIAL_PRICE = 9450;

    @BindView(R.id.container)
    BoxInsetLayout container;
    @BindView(R.id.bitcoin_value)
    TextView textValue;

    String priceValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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
        double newPrice = (Math.random() * FAKE_PRICE_INTERVAL) + FAKE_INITIAL_PRICE;
        priceValue = String.format("%.2f", newPrice);
        printValue();
    }

    private void initPrice() {
        priceValue = String.valueOf(FAKE_INITIAL_PRICE);
        printValue();
    }

    private void printValue() {
        textValue.setText(getString(R.string.bitcoin_value, priceValue));
    }
}
