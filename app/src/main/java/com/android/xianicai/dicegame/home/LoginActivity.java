package com.android.xianicai.dicegame.home;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.android.xianicai.dicegame.Constant;
import com.android.xianicai.dicegame.R;
import com.android.xianicai.dicegame.base.BaseActivity;
import com.android.xianicai.dicegame.base.BaseApplication;
import com.android.xianicai.dicegame.utils.RxBus;
import com.android.xianicai.dicegame.utils.ToastUtil;
import com.android.xianicai.dicegame.widget.loading.ShapeLoadingDialog;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class LoginActivity extends BaseActivity {
    private Handler mHandler;
    private ShapeLoadingDialog mShapeLoadingDialog;
    @BindView(R.id.image_login)
    ImageView mImageLogin;

    @Override
    public int getlayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Log.i("act", "initViews:     LoginActivity");
        finishLogin();
    }


    @OnClick(R.id.image_login)
    public void onViewClicked() {
        if (BaseApplication.api == null) {
            BaseApplication.api = WXAPIFactory.createWXAPI(this, Constant.APP_ID, true);
        }
        if (!BaseApplication.api.isWXAppInstalled()) {
            ToastUtil.showMessage("您手机尚未安装微信，请安装后再登录");
        } else {
            showLoading();
            BaseApplication.api.registerApp(Constant.APP_ID);
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            //官方说明：用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
            req.state = "wechat_sdk_xb_live_state";
            BaseApplication.api.sendReq(req);
        }
    }

    /**
     * 接收rxbus关闭当前页面
     */
    private void finishLogin() {
        RxBus.getDefault().toObservable(String.class)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        if (s.equals(Constant.RXBUS_CLOSE_LOGIN)) {
                            cancelLoading();
                            finish();
                        }
                    }
                });
    }

    /**
     * showloading
     */
    private void showLoading() {
        if (mShapeLoadingDialog == null) {
            mShapeLoadingDialog = new ShapeLoadingDialog(this);
        }
        mShapeLoadingDialog.setLoadingText("请稍后...");
        mShapeLoadingDialog.show();
    }

    /**
     * cancelLoading
     */
    private void cancelLoading() {
        if (mShapeLoadingDialog != null) {
            mShapeLoadingDialog.dismiss();
        }
    }

}
