package com.android.xianicai.dicegame.home.provider.data;

import android.support.annotation.NonNull;

import com.android.xianicai.dicegame.utils.netutil.ReqCommon;

import org.json.JSONObject;

/**
 * Created by Zhanglibin on 2017/9/2.
 */

public class ReqCreatRoom extends ReqCommon<CreatRoomBean> {
    @Override
    protected void parseResult(@NonNull JSONObject jsonObject) throws Exception {
        CreatRoomBean bean = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.toString(),CreatRoomBean.class);
        setT(bean);
    }
}
