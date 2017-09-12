package com.android.xianicai.dicegame.home.provider.data;

/**
 * Created by Zhanglibin on 2017/9/12.
 */

public class CreatRoomBean {

    /**
     * result : {"ownerName":"夏尼采","memberCount":"1","ownerLogo":"http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJoo6fHYEsGZSyFlXOewbr7vThJuFCRWs9DqLnW2QcNic7paHAwYeK66Mb88W3TJ4z6tabHr4ZHXSA/0","diamondCount":100,"goldCount":10055,"ownerId":"10020","roomId":"100201"}
     * code : 0
     * message : 创建房间成功
     */

    private ResultBean result;
    private int code;
    private String message;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

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

    public static class ResultBean {
        /**
         * ownerName : 夏尼采
         * memberCount : 1
         * ownerLogo : http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJoo6fHYEsGZSyFlXOewbr7vThJuFCRWs9DqLnW2QcNic7paHAwYeK66Mb88W3TJ4z6tabHr4ZHXSA/0
         * diamondCount : 100
         * goldCount : 10055
         * ownerId : 10020
         * roomId : 100201
         */

        private String ownerName;
        private int memberCount;
        private String ownerLogo;
        private int diamondCount;
        private int goldCount;
        private String ownerId;
        private String roomId;

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public int getMemberCount() {
            return memberCount;
        }

        public void setMemberCount(int memberCount) {
            this.memberCount = memberCount;
        }

        public String getOwnerLogo() {
            return ownerLogo;
        }

        public void setOwnerLogo(String ownerLogo) {
            this.ownerLogo = ownerLogo;
        }

        public int getDiamondCount() {
            return diamondCount;
        }

        public void setDiamondCount(int diamondCount) {
            this.diamondCount = diamondCount;
        }

        public int getGoldCount() {
            return goldCount;
        }

        public void setGoldCount(int goldCount) {
            this.goldCount = goldCount;
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
    }
}
