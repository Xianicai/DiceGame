package com.android.xianicai.dicegame.gameroom.presenter.impl;

import com.android.xianicai.dicegame.base.basemvp.BasePresenterImpl;
import com.android.xianicai.dicegame.gameroom.presenter.GameRoomPresenter;
import com.android.xianicai.dicegame.gameroom.provider.data.ReqRoomDetail;
import com.android.xianicai.dicegame.gameroom.provider.impl.GameRoomProviderImpl;
import com.android.xianicai.dicegame.gameroom.view.GameRoomView;
import com.android.xianicai.dicegame.utils.netutil.NetAsynTask;

/**
 * Created by Zhanglibin on 2017/9/3.
 */

public class GameRoomPresenterImpl extends BasePresenterImpl<GameRoomView> implements GameRoomPresenter {


    private final GameRoomProviderImpl mGameRoomProvider;

    public GameRoomPresenterImpl() {
        mGameRoomProvider = new GameRoomProviderImpl();
    }

    @Override
    public void getGameRoomDetail(String userId, String roomId) {
        final ReqRoomDetail reqRoomDetail = new ReqRoomDetail();
        mGameRoomProvider.getGameRoomDetail(userId, roomId, reqRoomDetail, new NetAsynTask.CallBack() {
            @Override
            public void onGetSucc() {
                getView().getGameRoomDetail(reqRoomDetail.getT());
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
}
