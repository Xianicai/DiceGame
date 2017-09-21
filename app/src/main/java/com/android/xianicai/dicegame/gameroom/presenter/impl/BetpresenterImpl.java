package com.android.xianicai.dicegame.gameroom.presenter.impl;

import com.android.xianicai.dicegame.base.basemvp.BasePresenterImpl;
import com.android.xianicai.dicegame.base.basemvp.ReqBase;
import com.android.xianicai.dicegame.gameroom.presenter.BetPresenter;
import com.android.xianicai.dicegame.gameroom.provider.impl.GameRoomProviderImpl;
import com.android.xianicai.dicegame.gameroom.view.BetView;
import com.android.xianicai.dicegame.utils.netutil.NetAsynTask;

import java.util.Map;

/**
 * Created by Zhanglibin on 2017/9/4.
 */

public class BetpresenterImpl extends BasePresenterImpl<BetView> implements BetPresenter {

    private final GameRoomProviderImpl mGameRoomProvider;

    public BetpresenterImpl() {
        mGameRoomProvider = new GameRoomProviderImpl();
    }

    @Override
    public void setBet(String userId, String roomId, Map<String,String> map) {
        getView().showProgress();
        final ReqBase reqBase = new ReqBase();
        mGameRoomProvider.setBet(userId, roomId, map, reqBase, new NetAsynTask.CallBack() {
            @Override
            public void onGetSucc() {
                if (reqBase.code == 0) {
                    getView().setBet();
                }
            }

            @Override
            public void onGetFinished() {
                getView().hideProgress();
            }

            @Override
            public void onGetFaild() {
                getView().hideProgress();
            }

            @Override
            public void onGetError() {
                getView().hideProgress();
            }
        });
    }
}
