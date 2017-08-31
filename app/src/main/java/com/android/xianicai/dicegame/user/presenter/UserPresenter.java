package com.android.xianicai.dicegame.user.presenter;

import com.android.xianicai.dicegame.utils.netutil.NetAsynTask;

/**
 * Created by Zhanglibin on 2017/8/30.
 */

public interface UserPresenter  {
    void login(String code, String phone);
}
