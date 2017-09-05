package com.android.xianicai.dicegame.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.android.xianicai.dicegame.Constant;
import com.android.xianicai.dicegame.Urls;
import com.android.xianicai.dicegame.base.basemvp.ReqBase;
import com.android.xianicai.dicegame.home.HomeActivity;
import com.android.xianicai.dicegame.utils.ToastUtil;
import com.android.xianicai.dicegame.utils.netutil.NetAsynTask;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zhanglibin on 2017/8/31.
 */

public class WXPayActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(getApplicationContext(), Constant.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }
    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        String userId = ((PayResp) baseResp).extData;
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK://-成功

                //-当用户支付成功，跳转到指定页面
                if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
                    final ReqBase req = new ReqBase();
                    Map<String, String> map = new HashMap<>();
                    map.put("usrId", userId);
                    map.put("price", "");
                    NetAsynTask.connectByPost(Urls.BASE_URL, map, req, new NetAsynTask.CallBack() {
                        @Override
                        public void onGetSucc() {
                            if (req.code == 200) {
                                HomeActivity.start(WXPayActivity.this,null);
                                finish();
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
                break;

            case BaseResp.ErrCode.ERR_USER_CANCEL://-用户取消
                //-当用户取消支付时，关闭当前Activity
                ToastUtil.showMessage("用户取消");
                finish();
                break;

            case BaseResp.ErrCode.ERR_COMM://-授权失败
                //-当认证被否决时，关闭当前Activity、
                ToastUtil.showMessage("授权失败");
                finish();
                break;
        }
    }
}
