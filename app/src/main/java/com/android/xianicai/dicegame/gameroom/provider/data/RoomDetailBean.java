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

        private int diamondcount;
        private int goldcount;
        private String id;
        private String logo;
        private String name;
        private String roomId;

        public int getDiamondcount() {
            return diamondcount;
        }

        public void setDiamondcount(int diamondcount) {
            this.diamondcount = diamondcount;
        }

        public int getGoldcount() {
            return goldcount;
        }

        public void setGoldcount(int goldcount) {
            this.goldcount = goldcount;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }
    }
}
