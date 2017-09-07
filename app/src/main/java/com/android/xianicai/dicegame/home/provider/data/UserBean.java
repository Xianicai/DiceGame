package com.android.xianicai.dicegame.home.provider.data;

/**
 * Created by Zhanglibin on 2017/8/30.
 */

public class UserBean {

    /**
     * result : {"accessToken":"aKgl8MiCZIoJQakW8TMEcGuUhVd8ofJ3ox_OoqqGofsVEihLh97MXkxkUzBgGE0KQKIoeXnZ98YDg8tWyxD8Eg","code":"0118PBpR03oVNa2jQwnR0b6LpR08PBpi","diamondCount":100,"goldCount":1000,"phone":1,"refreshToken":"3TzZa-nAq6xM8tdzvGCFWolcNmOtoBtGRZr15Svxlbrfw0N1q5T8GARQMtjwN7harKZsl7Pc6wCtUZXuHHRBRQ","userId":11050,"userLogo":"http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJoo6fHYEsGZSyFlXOewbr7vThJuFCRWs9DqLnW2QcNic7paHAwYeK66Mb88W3TJ4z6tabHr4ZHXSA/0","userName":"夏尼采"}
     * code : 0
     * message : 成功登录
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
         * accessToken : aKgl8MiCZIoJQakW8TMEcGuUhVd8ofJ3ox_OoqqGofsVEihLh97MXkxkUzBgGE0KQKIoeXnZ98YDg8tWyxD8Eg
         * code : 0118PBpR03oVNa2jQwnR0b6LpR08PBpi
         * diamondCount : 100
         * goldCount : 1000
         * phone : 1
         * refreshToken : 3TzZa-nAq6xM8tdzvGCFWolcNmOtoBtGRZr15Svxlbrfw0N1q5T8GARQMtjwN7harKZsl7Pc6wCtUZXuHHRBRQ
         * userId : 11050
         * userLogo : http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJoo6fHYEsGZSyFlXOewbr7vThJuFCRWs9DqLnW2QcNic7paHAwYeK66Mb88W3TJ4z6tabHr4ZHXSA/0
         * userName : 夏尼采
         */

        private String accessToken;
        private String code;
        private int diamondCount;
        private int goldCount;
        private int phone;
        private String refreshToken;
        private String userId;
        private String userLogo;
        private String userName;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
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

        public int getPhone() {
            return phone;
        }

        public void setPhone(int phone) {
            this.phone = phone;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
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
    }
}
