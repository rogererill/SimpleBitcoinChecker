package com.erill.simplebitcoinchecker.view.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.erill.simplebitcoinchecker.BaseApplication;
import com.erill.simplebitcoinchecker.R;
import com.erill.simplebitcoinchecker.di.AppComponent;
import com.erill.simplebitcoinchecker.model.BitcoinInfo;
import com.erill.simplebitcoinchecker.utils.Utils;

import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    public static final String TAG = "MainActivity";

    @BindView(R.id.coordinator_main)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.et_your_quantity)
    EditText inputQuantity;

    @BindView(R.id.tv_price)
    TextView price;

    @BindView(R.id.tv_balance)
    TextView balance;

    @BindView(R.id.tv_last_update)
    TextView lastUpdateText;

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        AppComponent appComponent = ((BaseApplication) getApplication()).getAppComponent();
        appComponent.inject(this);
        presenter.bindView(this);
        initListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getBitcoinData();
    }

    private void initListeners() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getBitcoinData();
            }
        });
        inputQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                final String text = charSequence.toString();
                calculateValues(text);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void calculateValues(String input) {
        double quantity = Utils.getDoubleFromString(input);
        String userBalance = presenter.getUserBalance(quantity);
        balance.setText(userBalance);
    }

    @Override
    public void showInfo(BitcoinInfo bitcoinInfo) {
        final Date lastUpdate = bitcoinInfo.getTime();
        lastUpdateText.setVisibility(View.VISIBLE);
        lastUpdateText.setText(getString(R.string.last_update, Utils.getLastHourUpdate(lastUpdate)));
        price.setText(String.valueOf(bitcoinInfo.getPrice()) + "€");
        calculateValues(inputQuantity.getText().toString());
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void dismissLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError() {
        lastUpdateText.setVisibility(View.VISIBLE);
        lastUpdateText.setText(getString(R.string.last_update, Utils.getLastHourUpdate(new Date())));
        price.setText(getString(R.string.error));
        Log.e(TAG, "showError: ");
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, R.string.generic_error, Snackbar.LENGTH_LONG)
                .setAction(R.string.retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.getBitcoinData();
                    }
                });
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }
}
