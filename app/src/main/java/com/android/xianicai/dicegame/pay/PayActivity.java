package com.android.xianicai.dicegame.pay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.xianicai.dicegame.Constant;
import com.android.xianicai.dicegame.R;
import com.android.xianicai.dicegame.base.BaseActivity;
import com.android.xianicai.dicegame.pay.presenter.impl.PaypPresenterImpl;
import com.android.xianicai.dicegame.pay.provider.data.OrderBean;
import com.android.xianicai.dicegame.pay.view.PayView;
import com.android.xianicai.dicegame.utils.ToastUtil;
import com.android.xianicai.dicegame.widget.NumberChangeLayout;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.BindView;
import butterknife.OnClick;

public class PayActivity extends BaseActivity implements PayView {
    @BindView(R.id.numberchangelayout)
    NumberChangeLayout mNumberchangelayout;
    private PaypPresenterImpl mPresenter;
    private String mUserId;
    private IWXAPI mWxapi;

    @Override
    public int getlayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mWxapi = WXAPIFactory.createWXAPI(getApplicationContext(), Constant.APP_ID);
        mUserId = getIntent().getStringExtra("userId");
        mNumberchangelayout.setGoldCount(1000);
        mPresenter = new PaypPresenterImpl();
        mPresenter.bindView(this);

    }

    @Override
    public void getWeiXInOrder(OrderBean orderBean) {
        PayReq req = new PayReq();
        req.appId = orderBean.getAppId();
        req.partnerId = orderBean.getPartnerId();
        req.prepayId = orderBean.getPrepayId();
        req.packageValue = orderBean.getPackageId();
        req.nonceStr = orderBean.getNoncestr();
        req.timeStamp = orderBean.getTimeStamp();
        req.sign = orderBean.getSign();
        mWxapi.registerApp(orderBean.getAppId());
        req.extData = mUserId;
        mWxapi.sendReq(req);//调起微信
    }


    public static void start(Context context, String userId) {
        context.startActivity(new Intent(context, PayActivity.class).putExtra("userId", userId));
    }

    @OnClick({R.id.image_back, R.id.image_go_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                onBackPressed();
                break;
            case R.id.image_go_pay:
                int goldCount = mNumberchangelayout.getGoldPrice();
                if (mWxapi.isWXAppInstalled()) {
                    mPresenter.getWeiXInOrder(mUserId, goldCount + "");
                } else {
                    ToastUtil.showMessage("您手机尚未安装微信，请安装后再进行支付。");
                }


                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
