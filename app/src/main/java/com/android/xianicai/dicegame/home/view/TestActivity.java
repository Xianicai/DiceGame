package com.android.xianicai.dicegame.home.view;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.android.xianicai.dicegame.R;
import com.android.xianicai.dicegame.home.EditDialog;

public class TestActivity extends AppCompatActivity {
    private AlertDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        EditDialog editDialog = new EditDialog(this).setTwoListener(new EditDialog.setOnTwoListener() {
            @Override
            public void onSureClicked(EditDialog dialog, String str) {
//                showSoftInput(TestActivity.this, view);

            }

            @Override
            public void onCancleClicked(EditDialog dialog) {
                dialog.dismiss();
            }
        }).showTwo();
        editDialog.setEditListener(new EditDialog.setOnEditListener() {
            @Override
            public void onEditClicked(EditText editText) {
//                showSoftInput(TestActivity.this, view);
            }
        });

//        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.confirm_dialog);
//        View view = View.inflate(this, R.layout.join_room_dialog, null);
//        ImageView imageSure = (ImageView) view.findViewById(R.id.image_sure);
//        ImageView imageCancle = (ImageView) view.findViewById(R.id.image_cancle);
//        final EditText edRoomNumber = (EditText) view.findViewById(R.id.ed_room_number);
//
//        // 设置视图
//        builder.setView(view);
//        mDialog = builder.create();
//        // 确定
//        imageSure.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String roomId = edRoomNumber.getText().toString();
//                if (StringUtil.isNotBlank(roomId) && roomId.length() == 6) {
////                    mUserPresenter.joinRoom(mUserId, roomId);
//                } else {
//                    ToastUtil.showMessage("房间号有误，请重新输入");
//                }
//            }
//        });
//        // 取消
//        imageCancle.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                mDialog.dismiss();
//            }
//        });


//        WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
//        lp.height = Mobile.SCREEN_HEIGHT * 8 / 10;
//        lp.width = Mobile.SCREEN_WIDTH * 6 / 10;
//        mDialog.getWindow().setAttributes(lp);
//        mDialog.setCanceledOnTouchOutside(true);
//        mDialog.setCancelable(true);
//        mDialog.show();
    }
}
