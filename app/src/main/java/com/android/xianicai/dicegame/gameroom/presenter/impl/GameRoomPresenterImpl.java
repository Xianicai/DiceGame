package com.android.xianicai.dicegame.gameroom.presenter.impl;

import com.android.xianicai.dicegame.base.basemvp.BasePresenterImpl;
import com.android.xianicai.dicegame.base.basemvp.ReqBase;
import com.android.xianicai.dicegame.gameroom.presenter.GameRoomPresenter;
import com.android.xianicai.dicegame.gameroom.provider.data.ReqGameResult;
import com.android.xianicai.dicegame.gameroom.provider.data.ReqCheckRoom;
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
        getView().showProgress();
        final ReqRoomDetail reqRoomDetail = new ReqRoomDetail();
        mGameRoomProvider.getGameRoomDetail(userId, roomId, reqRoomDetail, new NetAsynTask.CallBack() {
            @Override
            public void onGetSucc() {
                getView().hideProgress();
                if (reqRoomDetail.code == 0) {
                    getView().getGameRoomDetail(reqRoomDetail.getT());
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

    @Override
    public void dismissRoom(String userId, String roomId) {
        getView().showProgress();
        final ReqBase reqBase = new ReqBase();
        mGameRoomProvider.dismissRoom(userId, roomId, reqBase, new NetAsynTask.CallBack() {
            @Override
            public void onGetSucc() {
                getView().hideProgress();
                if (reqBase.code == 0) {
                    getView().dismissRoom();
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

    @Override
    public void startGame(String userId, String roomId,int gameTimes) {
        getView().showProgress();
        final ReqGameResult reqGameResult = new ReqGameResult();
        mGameRoomProvider.startGame(userId, roomId,gameTimes, reqGameResult, new NetAsynTask.CallBack() {
            @Override
            public void onGetSucc() {
                getView().hideProgress();
                if (reqGameResult.code == 0) {
                    getView().startGame(reqGameResult.getT());
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

    @Override
    public void quitRoom(String userId, String roomId) {
        getView().showProgress();
        final ReqBase reqBase = new ReqBase();
        mGameRoomProvider.quitRoom(userId, roomId, reqBase, new NetAsynTask.CallBack() {
            @Override
            public void onGetSucc() {
                getView().hideProgress();
                if (reqBase.code == 0) {
                    getView().quitRoom();
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

    @Override
    public void checkedRoom(String userId,String roomId) {
        final ReqCheckRoom reqMemberCount = new ReqCheckRoom();
        mGameRoomProvider.checkMemberCount(userId,roomId, reqMemberCount, new NetAsynTask.CallBack() {
            @Override
            public void onGetSucc() {
                if (reqMemberCount.code == 0) {
                    getView().checkedRoom(reqMemberCount.getT());
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
