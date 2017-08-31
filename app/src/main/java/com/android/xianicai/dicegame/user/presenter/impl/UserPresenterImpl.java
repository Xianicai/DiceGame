package com.android.xianicai.dicegame.user.presenter.impl;

import com.android.xianicai.dicegame.base.basemvp.BasePresenterImpl;
import com.android.xianicai.dicegame.user.presenter.UserPresenter;
import com.android.xianicai.dicegame.user.provider.data.ReqUser;
import com.android.xianicai.dicegame.user.provider.impl.UserProviderImpl;
import com.android.xianicai.dicegame.user.view.HomeView;
import com.android.xianicai.dicegame.utils.netutil.NetAsynTask;

/**
 * Created by Zhanglibin on 2017/8/30.
 */

public class UserPresenterImpl extends BasePresenterImpl<HomeView> implements UserPresenter {

    private final UserProviderImpl mUserProvider;

    public UserPresenterImpl() {
        mUserProvider = new UserProviderImpl();
    }

    @Override
    public void login(String code, String phone) {
        final ReqUser reqUser = new ReqUser();
        mUserProvider.login(code, phone, new NetAsynTask.CallBack() {
            @Override
            public void onGetSucc() {
                getView().login(reqUser.getT());
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
        },reqUser);
    }
}
