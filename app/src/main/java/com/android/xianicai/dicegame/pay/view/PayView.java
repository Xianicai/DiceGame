package com.android.xianicai.dicegame.pay.view;

import com.android.xianicai.dicegame.base.basemvp.BaseView;
import com.android.xianicai.dicegame.pay.provider.data.OrderBean;

/**
 * Created by Zhanglibin on 2017/8/31.
 */

public interface PayView extends BaseView {
   void getWeiXInOrder(OrderBean orderBean);
}
