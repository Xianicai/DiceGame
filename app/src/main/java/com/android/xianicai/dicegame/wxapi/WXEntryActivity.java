package com.android.xianicai.dicegame.wxapi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.xianicai.dicegame.Constant;
import com.android.xianicai.dicegame.Urls;
import com.android.xianicai.dicegame.home.HomeActivity;
import com.android.xianicai.dicegame.home.provider.data.ReqUser;
import com.android.xianicai.dicegame.utils.RxBus;
import com.android.xianicai.dicegame.utils.netutil.NetAsynTask;
import com.android.xianicai.dicegame.widget.loading.ShapeLoadingDialog;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zhanglibin on 2017/8/22.
 */

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    private ShapeLoadingDialog  mShapeLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IWXAPI api = WXAPIFactory.createWXAPI(getApplicationContext(), Constant.APP_ID, false);
        api.handleIntent(getIntent(), this);
        Log.i("act", "initViews:     LoginActivity");
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    // 获得code的回调
    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:// 同意
                RxBus.getDefault().post(Constant.RXBUS_CLOSE_LOGIN);
                if (baseResp instanceof SendAuth.Resp) {
                    showLoading();
                    final String code = ((SendAuth.Resp) baseResp).code;
                    final ReqUser reqUser = new ReqUser();
                    final Map<String, String> map = new HashMap<>();
                    map.put("code", code);
                    map.put("phone", "1");
                    NetAsynTask.connectByPost(Urls.POST_WEIXIN_LOGIN, map, reqUser, new NetAsynTask.CallBack() {
                        @Override
                        public void onGetSucc() {
                            if (reqUser.code == 0) {
                                HomeActivity.start(WXEntryActivity.this, reqUser.getT().getResult().getUserId());
                                finish();
                            }
                        }

                        @Override
                        public void onGetFinished() {
                            cancelLoading();

                        }

                        @Override
                        public void onGetFaild() {

                        }

                        @Override
                        public void onGetError() {

                        }
                    });

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
