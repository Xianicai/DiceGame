package com.android.xianicai.dicegame.home.provider.data;

/**
 * Created by Zhanglibin on 2017/8/30.
 */

public class UserBean {
    private String token;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户名字
     */
    private String userName;
    /**
     * 用户头像
     */
    private String userLogo;
    /**
     * 钻石数量
     */
    private String diamondCount;
    /**
     * 金币数量
     */
    private String goldCount;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getDiamondCount() {
        return diamondCount;
    }

    public void setDiamondCount(String diamondCount) {
        this.diamondCount = diamondCount;
    }

    public String getGoldCount() {
        return goldCount;
    }

    public void setGoldCount(String goldCount) {
        this.goldCount = goldCount;
    }
}
