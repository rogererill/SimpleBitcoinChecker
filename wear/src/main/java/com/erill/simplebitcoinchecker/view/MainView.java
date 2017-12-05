package com.erill.simplebitcoinchecker.view;

import com.erill.simplebitcoinchecker.model.BitcoinInfo;
import com.erill.simplebitcoinchecker.view.base.BaseView;

/**
 * Created by Roger on 5/12/17.
 */

public interface MainView extends BaseView{

    void showInfo(BitcoinInfo bitcoinInfo);

}
