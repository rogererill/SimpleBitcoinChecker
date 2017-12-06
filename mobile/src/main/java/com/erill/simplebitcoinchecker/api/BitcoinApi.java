package com.erill.simplebitcoinchecker.api;

import com.erill.simplebitcoinchecker.model.BitcoinInfo;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Roger on 5/12/17.
 */

public interface BitcoinApi {

    @GET("products/BTC-EUR/ticker")
    Observable<BitcoinInfo> getPriceData();
}
