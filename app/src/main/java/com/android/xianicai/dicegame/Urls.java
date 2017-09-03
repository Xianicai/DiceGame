package com.android.xianicai.dicegame;

/**
 * Created by Zhanglibin on 2017/8/30.
 */

public class Urls {
    public static final String BASE_URL = "http://119.29.1.15:8080/ssmdemo/";
    public static final String BASE_URL_b = "http://169.254.170.31:8080/web-ssm/";
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
    public static final String POST_ROOM_DETAIL = BASE_URL_b + "room/detail";
}
