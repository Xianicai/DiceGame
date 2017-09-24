package com.android.xianicai.dicegame.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.xianicai.dicegame.R;
import com.android.xianicai.dicegame.utils.Mobile;
import com.android.xianicai.dicegame.utils.StringUtil;

/**
 * Created by Zhanglibin on 2017/9/23.
 */

public class ShareDialog extends AlertDialog {

    TextView mTvMsg;
    ImageView mImageSure;
    ImageView mImageCancle;
    // 内容
    private String mMessage;
    private DialogInterface.OnDismissListener mOnDismissListener;
    /**
     * 按钮数量
     */
    private int mBtnNum = 1;
    /**
     * 是否已经初始化
     */
    private boolean mHasInit = false;

    private boolean mCancelable = true;

    private int mDialogWidth;
    private int mDialogHight;
    private View mView;
    private setOnTwoListener mViewListener;

    public ShareDialog setTwoListener(setOnTwoListener viewListener) {
        mViewListener = viewListener;
        return this;
    }

    public ShareDialog(Context context) {
        this(context, 0);
    }

    protected ShareDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected ShareDialog(Context context, @StyleRes int themeResId) {
        super(context, R.style.confirm_dialog);
        mView = View.inflate(context, R.layout.share_dialog, null);
        mTvMsg = (TextView) mView.findViewById(R.id.tv_msg);
        mImageSure = (ImageView) mView.findViewById(R.id.image_sure);
        mImageCancle = (ImageView) mView.findViewById(R.id.image_cancle);
        mImageSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewListener.onSureClicked(ShareDialog.this);

            }
        });
        mImageCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewListener.onCancleClicked(ShareDialog.this);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mView);
        mDialogHight = Mobile.SCREEN_HEIGHT * 8 / 10;
        mDialogWidth = Mobile.SCREEN_WIDTH * 6 / 10;
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.height = mDialogHight;
        lp.width = mDialogWidth;
        this.getWindow().setAttributes(lp);
        setOnDismissListener(mOnDismissListener);
        setCancelable(mCancelable);
        setCanceledOnTouchOutside(mCancelable);
    }

    /**
     * 设置内容
     */
    public ShareDialog setMsg(String message) {
        this.mMessage = message;
        if (StringUtil.isNotBlank(mMessage)) {
            mTvMsg.setText(mMessage);
        }
        return this;
    }

    /**
     * 显示Dialog
     */
    public ShareDialog showTwo() {
        mImageSure.setVisibility(View.VISIBLE);
        mImageCancle.setVisibility(View.VISIBLE);
        setOnDismissListener(mOnDismissListener);
        setCancelable(mCancelable);
        setCanceledOnTouchOutside(mCancelable);
        show();
        return this;
    }

    public interface setOnTwoListener {
        void onSureClicked(ShareDialog dialog);

        void onCancleClicked(ShareDialog dialog);
    }
}

