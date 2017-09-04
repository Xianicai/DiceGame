package com.android.xianicai.dicegame;

/**
 * Created by Zhanglibin on 2017/8/30.
 */

public class Urls {
    public static final String BASE_URL = "http://47.94.225.154:8080/web-ssm/";
    /**
     * 登录
     */
    public static final String POST_WEIXIN_LOGIN = BASE_URL + "user/login";
    /**
     * 创建支付订单
     */
    public static final String POST_CREAT_WEIXIN_ODER = BASE_URL + "pay/create/order";
    /**
     * 创建房间
     */
    public static final String POST_CREAT_ROOM = BASE_URL + "room/create";
    /**
     * 获取房间详情
     */
    public static final String POST_ROOM_DETAIL = BASE_URL + "room/detail";
    /**
     * 开始游戏
     */
    public static final String POST_STAR_GAME = BASE_URL + "room/detail";
    /**
     * 金币下注
     * */
    public static final String POST_BET_GAME = BASE_URL + "room/detail";

    /**
     * 退出房间
     */
    public static final String POST_QUITE_ROOM = BASE_URL + "room/detail";
    /**
     * 解散房间
     */
    public static final String POST_DISMISS_ROOM = BASE_URL + "room/detail";
}
