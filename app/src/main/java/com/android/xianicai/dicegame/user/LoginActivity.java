package com.android.xianicai.dicegame.user;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.android.xianicai.dicegame.Constant;
import com.android.xianicai.dicegame.R;
import com.android.xianicai.dicegame.base.BaseActivity;
import com.android.xianicai.dicegame.base.BaseApplication;
import com.android.xianicai.dicegame.utils.RxBus;
import com.android.xianicai.dicegame.utils.ToastUtil;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.image_login)
    ImageView mImageLogin;

    @Override
    public int getlayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        finishLogin();
    }


    @OnClick(R.id.image_login)
    public void onViewClicked() {
//        HomeActivity.start(this,null);
        if (BaseApplication.api == null) {
            BaseApplication.api = WXAPIFactory.createWXAPI(this, Constant.APP_ID, true);
        }
        if (!BaseApplication.api.isWXAppInstalled()) {
            ToastUtil.showMessage("您手机尚未安装微信，请安装后再登录");
        } else {
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
                            finish();
                        }
                    }
                });
    }
}
