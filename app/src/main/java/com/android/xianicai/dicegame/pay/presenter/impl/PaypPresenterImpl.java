package com.android.xianicai.dicegame.pay.presenter.impl;

import com.android.xianicai.dicegame.base.basemvp.BasePresenterImpl;
import com.android.xianicai.dicegame.pay.presenter.PaypPresenter;
import com.android.xianicai.dicegame.pay.provider.data.ReqPayOrder;
import com.android.xianicai.dicegame.pay.provider.impl.PayProviderImpl;
import com.android.xianicai.dicegame.pay.view.PayView;
import com.android.xianicai.dicegame.utils.netutil.NetAsynTask;

/**
 * Created by Zhanglibin on 2017/8/31.
 */

public class PaypPresenterImpl extends BasePresenterImpl<PayView> implements PaypPresenter {

    private final PayProviderImpl mPayProvider;

    public PaypPresenterImpl() {
        mPayProvider = new PayProviderImpl();
    }

    @Override
    public void getWeiXInOrder(String userId, String price) {
        final ReqPayOrder reqPayOrder = new ReqPayOrder();
        mPayProvider.getWeiXInOrder(userId, price, reqPayOrder, new NetAsynTask.CallBack() {
            @Override
            public void onGetSucc() {
                if (reqPayOrder.code == 0) {
                    getView().getWeiXInOrder(reqPayOrder.getT());
                }
            }

            @Override
            public void onGetFinished() {

            }

            @Override
            public void onGetFaild() {

            }

            @Override
            public void onGetError() {

            }
        });
    }
}
