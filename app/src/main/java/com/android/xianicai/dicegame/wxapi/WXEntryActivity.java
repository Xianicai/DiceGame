package com.android.xianicai.dicegame.wxapi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.xianicai.dicegame.Constant;
import com.android.xianicai.dicegame.user.HomeActivity;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by Zhanglibin on 2017/8/22.
 */

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IWXAPI api = WXAPIFactory.createWXAPI(getApplicationContext(), Constant.APP_ID, false);
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    // 获得code的回调
    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:// 同意
                if (baseResp instanceof SendAuth.Resp) {
                    String code = ((SendAuth.Resp) baseResp).code;
                    HomeActivity.start(this, code);
                    finish();
                }
                // 分享到微信后，点击返回掌门的回调
                else if (baseResp instanceof SendMessageToWX.Resp) {
                    finish();
                }
                break;

            case BaseResp.ErrCode.ERR_USER_CANCEL:
                // 当用户取消授权时，关闭当前Activity
                finish();
                break;

            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                // 当认证被否决时，关闭当前Activity
                finish();
                break;
        }
    }

}
