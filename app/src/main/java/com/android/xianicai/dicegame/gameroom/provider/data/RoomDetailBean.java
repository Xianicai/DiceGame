package com.android.xianicai.dicegame.gameroom.provider.data;

/**
 * Created by Zhanglibin on 2017/9/2.
 */

public class RoomDetailBean {


    /**
     * code : 0
     * message : 获取房间详情
     * result : {"gameTimes":0,"lastResult":"","memberCount":1,"ownerId":123660,"ownerLogo":"http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJoo6fHYEsGZSyFlXOewbr7vThJuFCRWs9DqLnW2QcNic7paHAwYeK66Mb88W3TJ4z6tabHr4ZHXSA/0","ownerName":"夏尼采","roomId":"1236606","roomStatus":1,"userDiamongCount":100,"userGoldCount":1000,"userId":"123660","userLogo":"http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJoo6fHYEsGZSyFlXOewbr7vThJuFCRWs9DqLnW2QcNic7paHAwYeK66Mb88W3TJ4z6tabHr4ZHXSA/0","userName":"夏尼采","userType":"0"}
     */

    private int code;
    private String message;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * gameTimes : 0
         * lastResult :
         * memberCount : 1
         * ownerId : 123660
         * ownerLogo : http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJoo6fHYEsGZSyFlXOewbr7vThJuFCRWs9DqLnW2QcNic7paHAwYeK66Mb88W3TJ4z6tabHr4ZHXSA/0
         * ownerName : 夏尼采
         * roomId : 1236606
         * roomStatus : 1
         * userDiamongCount : 100
         * userGoldCount : 1000
         * userId : 123660
         * userLogo : http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJoo6fHYEsGZSyFlXOewbr7vThJuFCRWs9DqLnW2QcNic7paHAwYeK66Mb88W3TJ4z6tabHr4ZHXSA/0
         * userName : 夏尼采
         * userType : 0
         */

        private int gameTimes;
        private String lastResult;
        private int memberCount;
        private int ownerId;
        private String ownerLogo;
        private String ownerName;
        private String roomId;
        private int roomStatus;
        private int userDiamongCount;
        private int userGoldCount;
        private String userId;
        private String userLogo;
        private String userName;
        private int userType;

        public int getGameTimes() {
            return gameTimes;
        }

        public void setGameTimes(int gameTimes) {
            this.gameTimes = gameTimes;
        }

        public String getLastResult() {
            return lastResult;
        }

        public void setLastResult(String lastResult) {
            this.lastResult = lastResult;
        }

        public int getMemberCount() {
            return memberCount;
        }

        public void setMemberCount(int memberCount) {
            this.memberCount = memberCount;
        }

        public int getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(int ownerId) {
            this.ownerId = ownerId;
        }

        public String getOwnerLogo() {
            return ownerLogo;
        }

        public void setOwnerLogo(String ownerLogo) {
            this.ownerLogo = ownerLogo;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public int getRoomStatus() {
            return roomStatus;
        }

        public void setRoomStatus(int roomStatus) {
            this.roomStatus = roomStatus;
        }

        public int getUserDiamongCount() {
            return userDiamongCount;
        }

        public void setUserDiamongCount(int userDiamongCount) {
            this.userDiamongCount = userDiamongCount;
        }

        public int getUserGoldCount() {
            return userGoldCount;
        }

        public void setUserGoldCount(int userGoldCount) {
            this.userGoldCount = userGoldCount;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserLogo() {
            return userLogo;
        }

        public void setUserLogo(String userLogo) {
            this.userLogo = userLogo;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }
    }
}
