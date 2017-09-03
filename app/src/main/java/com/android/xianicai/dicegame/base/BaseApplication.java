package com.android.xianicai.dicegame.base;

import android.app.Application;

import com.android.xianicai.dicegame.Constant;
import com.android.xianicai.dicegame.utils.Mobile;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;


/**
 * ZY:
 * Created by zhanglibin on 2016/9/2.
 */
public class BaseApplication extends Application {

    public static IWXAPI api;
    public static BaseApplication app;

    public static BaseApplication getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        //注册微信
        api = WXAPIFactory.createWXAPI(this, Constant.APP_ID, true);
        api.registerApp( Constant.APP_ID);
        // 初始化
        x.Ext.init(this);
        // 是否输出debug日志
        x.Ext.setDebug(true);
        //极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        //测量屏幕
        Mobile.init(getApplicationContext());
    }

}