package com.android.xianicai.dicegame.user.presenter.impl;

import com.android.xianicai.dicegame.base.basemvp.BasePresenterImpl;
import com.android.xianicai.dicegame.gameroom.provider.data.ReqRoomDetail;
import com.android.xianicai.dicegame.user.presenter.UserPresenter;
import com.android.xianicai.dicegame.user.provider.data.ReqCreatRoom;
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
        mUserProvider.login(code, phone, reqUser, new NetAsynTask.CallBack() {
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
        });
    }

    @Override
    public void creatRomm(String userId) {
        final ReqCreatRoom creatRoom = new ReqCreatRoom();
        mUserProvider.creatRoom(userId, creatRoom, new NetAsynTask.CallBack() {
            @Override
            public void onGetSucc() {
                getView().creatRoom(creatRoom.getT());
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
        final ReqRoomDetail reqRoomDetail = new ReqRoomDetail();
        mUserProvider.joinRoom(userId,roomId, reqRoomDetail, new NetAsynTask.CallBack() {
            @Override
            public void onGetSucc() {
                getView().joinRoomSuccess(reqRoomDetail.getT());
            }

            @Override
            public void onGetFinished() {

            }

            @Override
            public void onGetFaild() {
                getView().JoinRommFaild(reqRoomDetail.message);
            }

            @Override
            public void onGetError() {

            }
        });
    }
}
