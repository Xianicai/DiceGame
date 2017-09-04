package com.android.xianicai.dicegame.gameroom.presenter.impl;

import com.android.xianicai.dicegame.base.basemvp.BasePresenterImpl;
import com.android.xianicai.dicegame.base.basemvp.ReqBase;
import com.android.xianicai.dicegame.gameroom.presenter.BetPresenter;
import com.android.xianicai.dicegame.gameroom.provider.impl.GameRoomProviderImpl;
import com.android.xianicai.dicegame.gameroom.view.BetView;
import com.android.xianicai.dicegame.utils.netutil.NetAsynTask;

/**
 * Created by Zhanglibin on 2017/9/4.
 */

public class BetpresenterImpl extends BasePresenterImpl<BetView> implements BetPresenter {

    private final GameRoomProviderImpl mGameRoomProvider;

    public BetpresenterImpl() {
        mGameRoomProvider = new GameRoomProviderImpl();
    }

    @Override
    public void setBet(String userId, String roomId, String dian) {
        final ReqBase reqBase = new ReqBase();
        mGameRoomProvider.setBet(userId, roomId, dian, reqBase, new NetAsynTask.CallBack() {
            @Override
            public void onGetSucc() {
                if (reqBase.code == 0) {
                    getView().setBet();
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
}
