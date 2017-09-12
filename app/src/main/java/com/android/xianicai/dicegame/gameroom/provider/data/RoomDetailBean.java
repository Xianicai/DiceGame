package com.android.xianicai.dicegame.gameroom.provider.data;

/**
 * Created by Zhanglibin on 2017/9/2.
 */

public class RoomDetailBean {

    /**
     * result : {"diamondcount":12,"goldcount":12,"id":26549,"logo":"logo url","name":"测试中文名字","roomId":10021}
     * code : ok
     * message : 创建成功
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
         * diamondcount : 12
         * goldcount : 12
         * id : 26549
         * logo : logo url
         * name : 测试中文名字
         * roomId : 10021
         */

        private int memberCount;
        private String ownerId;
        private String roomId;
        private String ownerName;
        private String ownerLogo;
        private String userId;
        private String userName;
        private String userLogo;
        private int userType;
        private int userGoldCount;
        private int userDiamongCount;
        private int gameTimes;

        public int getMemberCount() {
            return memberCount;
        }

        public void setMemberCount(int memberCount) {
            this.memberCount = memberCount;
        }

        public String getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(String ownerId) {
            this.ownerId = ownerId;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public String getOwnerLogo() {
            return ownerLogo;
        }

        public void setOwnerLogo(String ownerLogo) {
            this.ownerLogo = ownerLogo;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserLogo() {
            return userLogo;
        }

        public void setUserLogo(String userLogo) {
            this.userLogo = userLogo;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public int getUserGoldCount() {
            return userGoldCount;
        }

        public void setUserGoldCount(int userGoldCount) {
            this.userGoldCount = userGoldCount;
        }

        public int getUserDiamongCount() {
            return userDiamongCount;
        }

        public void setUserDiamongCount(int userDiamongCount) {
            this.userDiamongCount = userDiamongCount;
        }

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

        private String lastResult;

    }
}
