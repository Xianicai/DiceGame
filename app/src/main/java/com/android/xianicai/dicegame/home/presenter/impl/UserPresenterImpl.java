package com.android.xianicai.dicegame.home.presenter.impl;

import com.android.xianicai.dicegame.base.basemvp.BasePresenterImpl;
import com.android.xianicai.dicegame.home.presenter.UserPresenter;
import com.android.xianicai.dicegame.home.provider.data.ReqCreatRoom;
import com.android.xianicai.dicegame.home.provider.data.ReqUser;
import com.android.xianicai.dicegame.home.provider.impl.UserProviderImpl;
import com.android.xianicai.dicegame.home.view.HomeView;
import com.android.xianicai.dicegame.utils.ToastUtil;
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
        mUserProvider.login(code, phone, reqUser, new NetAsynTask.CallBack() {
            @Override
            public void onGetSucc() {
                if (reqUser.code == 0) {
                    getView().login(reqUser.getT());
                } else {
                    ToastUtil.showMessage(reqUser.message);
                    getView().loginFaild();
                }
            }

            @Override
            public void onGetFinished() {

            }

            @Override
            public void onGetFaild() {
                ToastUtil.showMessage(reqUser.message);
                getView().loginFaild();
            }

            @Override
            public void onGetError() {
                ToastUtil.showMessage(reqUser.message);
                getView().loginFaild();
            }
        });
    }

    @Override
    public void creatRomm(String userId) {
        final ReqCreatRoom creatRoom = new ReqCreatRoom();
        mUserProvider.creatRoom(userId, creatRoom, new NetAsynTask.CallBack() {
            @Override
            public void onGetSucc() {
                if (creatRoom.code == 0) {
                    getView().creatRoom(creatRoom.getT());
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

    @Override
    public void joinRoom(String userId, String roomId) {
        final ReqCreatRoom reqCreatRoom = new ReqCreatRoom();
        mUserProvider.joinRoom(userId, roomId, reqCreatRoom, new NetAsynTask.CallBack() {
            @Override
            public void onGetSucc() {
                if (reqCreatRoom.code == 0) {
                    getView().joinRoomSuccess(reqCreatRoom.getT());
                } else {
                    getView().joinRoomFaild(reqCreatRoom.message);
                }
            }

            @Override
            public void onGetFinished() {

            }

            @Override
            public void onGetFaild() {
                getView().joinRoomFaild(reqCreatRoom.message);
            }

            @Override
            public void onGetError() {
                getView().joinRoomFaild(reqCreatRoom.message);
            }
        });
    }

    @Override
    public void refreshUser(String userId) {
        final ReqUser reqUser = new ReqUser();
        mUserProvider.refreshUser(userId, reqUser, new NetAsynTask.CallBack() {
            @Override
            public void onGetSucc() {
                if (reqUser.code == 0) {
                    getView().refreshUser(reqUser.getT());
                }
            }

            @Override
            public void onGetFinished() {

            }

            @Override
            public void onGetFaild() {
                ToastUtil.showMessage(reqUser.message);
            }

            @Override
            public void onGetError() {
                ToastUtil.showMessage(reqUser.message);
            }
        });
    }
}
