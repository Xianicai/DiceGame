package com.android.xianicai.dicegame.gameroom.provider;

import com.android.xianicai.dicegame.base.basemvp.ReqBase;
import com.android.xianicai.dicegame.gameroom.provider.data.ReqGameResult;
import com.android.xianicai.dicegame.gameroom.provider.data.ReqRoomDetail;
import com.android.xianicai.dicegame.utils.netutil.NetAsynTask;

/**
 * Created by Zhanglibin on 2017/9/3.
 */

public interface GameRoomProvider {
    void getGameRoomDetail(String userId, String roomId,ReqRoomDetail reqRoomDetail, NetAsynTask.CallBack callBack );
    void dismissRoom(String userId, String roomId, ReqBase reqBase, NetAsynTask.CallBack callBack);
    void startGame(String userId, String roomId, ReqGameResult reqGameResult, NetAsynTask.CallBack callBack);
    void quitRoom(String userId, String roomId, ReqBase reqBase, NetAsynTask.CallBack callBack);
    void setBet(String userId, String roomId,String dian,ReqBase reqBase, NetAsynTask.CallBack callBack);
}
