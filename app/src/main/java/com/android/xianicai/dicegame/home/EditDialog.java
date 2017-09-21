package com.android.xianicai.dicegame.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.xianicai.dicegame.R;
import com.android.xianicai.dicegame.utils.Mobile;
import com.android.xianicai.dicegame.utils.StringUtil;

/**
 * Created by Zhanglibin on 2017/9/21.
 */

public class EditDialog  extends AlertDialog {

    TextView mTvMsg;
    ImageView mImageSure;
    ImageView mImageCancle;
    ImageView mImageKnow;
    //    private Activity mContext;
//    private AlertDialog.Builder mBuilder;
//    private AlertDialog mDialog;
//    private View mView;
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
    private setOnEditListener editListener;
    private EditText mEdRoomNumber;

    public EditDialog setTwoListener(setOnTwoListener viewListener) {
        mViewListener = viewListener;
        return this;
    }

    public EditDialog setEditListener(setOnEditListener editListener) {
        this.editListener = editListener;
        return this;
    }

    public EditDialog(Context context) {
        this(context, 0);
    }

    protected EditDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected EditDialog(final Context context, @StyleRes int themeResId) {
        super(context, R.style.confirm_dialog);



        mView = View.inflate(context, R.layout.join_room_dialog, null);
        mTvMsg = (TextView) mView.findViewById(R.id.tv_msg);
        mImageSure = (ImageView) mView.findViewById(R.id.image_sure);
        mImageCancle = (ImageView) mView.findViewById(R.id.image_cancle);
        mImageKnow = (ImageView) mView.findViewById(R.id.image_know);
        mEdRoomNumber = (EditText) mView.findViewById(R.id.ed_room_number);
        mImageSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewListener.onSureClicked(EditDialog.this,mEdRoomNumber.getText().toString());

            }
        });
        mImageCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewListener.onCancleClicked(EditDialog.this);
            }
        });
        mImageKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editListener.onEditClicked(mEdRoomNumber);
            }
        });
        mEdRoomNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editListener.onEditClicked(mEdRoomNumber);
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
    public EditDialog setMsg(String message) {
        this.mMessage = message;
        if (StringUtil.isNotBlank(mMessage)) {
            mTvMsg.setText(mMessage);
        }
        return this;
    }

    /**
     * 显示Dialog
     */
    public EditDialog showTwo() {
        mImageSure.setVisibility(View.VISIBLE);
        mImageCancle.setVisibility(View.VISIBLE);
        mImageKnow.setVisibility(View.GONE);
        setOnDismissListener(mOnDismissListener);
        setCancelable(mCancelable);
        setCanceledOnTouchOutside(mCancelable);
        show();
        return this;
    }

    /**
     * 显示Dialog
     */
    public EditDialog showSingle() {
        mImageSure.setVisibility(View.GONE);
        mImageCancle.setVisibility(View.GONE);
        mImageKnow.setVisibility(View.VISIBLE);
        setOnDismissListener(mOnDismissListener);
        setCancelable(mCancelable);
        setCanceledOnTouchOutside(mCancelable);
        show();
        return this;
    }

    public interface setOnTwoListener {
        void onSureClicked(EditDialog dialog,String str);

        void onCancleClicked(EditDialog dialog);
    }

    public interface setOnEditListener {

        void onEditClicked(EditText editText);
    }

    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }
}

