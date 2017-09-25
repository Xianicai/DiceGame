package com.android.xianicai.dicegame;

/**
 * Created by Zhanglibin on 2017/8/30.
 */

public class Urls {
    public static final String BASE_URL = "http://127.0.0.1:8080/web-ssm/";
    /**
     * 登录
     */
    public static final String POST_WEIXIN_LOGIN = BASE_URL + "user/login";
    /**
     * 创建支付订单
     */
    public static final String POST_CREAT_WEIXIN_ODER = BASE_URL + "order/create";

    /**
     * 支付成功
     */
    public static final String POST_PAY_SUCCESS = BASE_URL + "pay/success";

    /**
     * 创建房间
     */
    public static final String POST_CREAT_ROOM = BASE_URL + "room/create";
    /**
     * 加入房间
     */
    public static final String POST_ADD_ROOM = BASE_URL + "user/addRoom";
    /**
     * 获取房间详情
     */
    public static final String POST_ROOM_DETAIL = BASE_URL + "room/detail";
    /**
     * 开始游戏
     */
    public static final String POST_STAR_GAME = BASE_URL + "game/start";
    /**
     * 金币下注
     */
    public static final String POST_BET_GAME = BASE_URL + "user/bet";

    /**
     * 退出房间
     */
    public static final String POST_QUITE_ROOM = BASE_URL + "user/exit";
    /**
     * 解散房间
     */
    public static final String POST_DISMISS_ROOM = BASE_URL + "user/dissolve";
    /**
     * 查询房间人数
     */
    public static final String POST_CHECK_MEMBER = BASE_URL + "room/check";

    /**
     * 查询用户详情
     */
    public static final String POST_CHECK_USER = BASE_URL + "user/detail";
    /**
     * 开始游戏倒计时
     */
    public static final String POST_GAME_READY = BASE_URL + "game/ready";
}
