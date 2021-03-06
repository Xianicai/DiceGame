package com.android.xianicai.dicegame.gameroom.provider;

import com.android.xianicai.dicegame.base.basemvp.ReqBase;
import com.android.xianicai.dicegame.gameroom.provider.data.ReqGameResult;
import com.android.xianicai.dicegame.gameroom.provider.data.ReqCheckRoom;
import com.android.xianicai.dicegame.gameroom.provider.data.ReqRoomDetail;
import com.android.xianicai.dicegame.utils.netutil.NetAsynTask;

import java.util.Map;

/**
 * Created by Zhanglibin on 2017/9/3.
 */

public interface GameRoomProvider {
    void getGameRoomDetail(String userId, String roomId,ReqRoomDetail reqRoomDetail, NetAsynTask.CallBack callBack );
    void dismissRoom(String userId, String roomId, ReqBase reqBase, NetAsynTask.CallBack callBack);
    void startGame(String userId, String roomId,int gameTimes,ReqGameResult reqGameResult, NetAsynTask.CallBack callBack);
    void quitRoom(String userId, String roomId, ReqBase reqBase, NetAsynTask.CallBack callBack);
    void setBet(String userId, String roomId, Map<String,String> map, ReqBase reqBase, NetAsynTask.CallBack callBack);
    void checkMemberCount(String userId,String roomId, ReqCheckRoom reqCheckRoom, NetAsynTask.CallBack callBack);
    void gameReady(String userId,String roomId, ReqBase reqBase, NetAsynTask.CallBack callBack);
}
