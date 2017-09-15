package com.android.xianicai.dicegame.gameroom.provider.data;

/**
 * Created by Zhanglibin on 2017/9/14.
 */

public class CheckRoomBean {

    /**
     * result : {"memberCount":"","memberType":""}
     * code : 1
     * message : 人数未发生变化
     */

    private ResultBean result;
    private String code;
    private String message;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class ResultBean {
        /**
         * memberCount :
         * memberType :
         */

        private int memberCount;
        private String gameResult;
        private int roomState;
        private int resultGain;
        private int goldCount;
        private int gameTimes;

        public int getMemberCount() {
            return memberCount;
        }

        public void setMemberCount(int memberCount) {
            this.memberCount = memberCount;
        }

        public String getGameResult() {
            return gameResult;
        }

        public void setGameResult(String gameResult) {
            this.gameResult = gameResult;
        }

        public int getRoomState() {
            return roomState;
        }

        public void setRoomState(int roomState) {
            this.roomState = roomState;
        }

        public int getResultGain() {
            return resultGain;
        }

        public void setResultGain(int resultGain) {
            this.resultGain = resultGain;
        }

        public int getGoldCount() {
            return goldCount;
        }

        public void setGoldCount(int goldCount) {
            this.goldCount = goldCount;
        }

        public int getGameTimes() {
            return gameTimes;
        }

        public void setGameTimes(int gameTimes) {
            this.gameTimes = gameTimes;
        }
    }
}
