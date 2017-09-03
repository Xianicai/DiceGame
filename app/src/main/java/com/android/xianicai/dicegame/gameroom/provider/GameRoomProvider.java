package com.android.xianicai.dicegame.gameroom.provider;

import com.android.xianicai.dicegame.gameroom.provider.data.ReqRoomDetail;
import com.android.xianicai.dicegame.utils.netutil.NetAsynTask;

/**
 * Created by Zhanglibin on 2017/9/3.
 */

public interface GameRoomProvider {
    void getGameRoomDetail(String userId, String roomId,ReqRoomDetail reqRoomDetail, NetAsynTask.CallBack callBack );
}
