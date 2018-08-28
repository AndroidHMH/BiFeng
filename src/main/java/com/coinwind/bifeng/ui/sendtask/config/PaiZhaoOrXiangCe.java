package com.coinwind.bifeng.ui.sendtask.config;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.ui.submittask.config.PhotoHelp;

public class PaiZhaoOrXiangCe implements View.OnClickListener {
    private Activity context;

    private PopupWindow photoPopup;
    private TextView paiBtn;
    private TextView xiangCeBtn;
    private TextView returnBtn;

    public static final int CODE_GALLERY_REQUEST = 0xa0;
    public static final int CODE_CAMERA_REQUEST = 0xa1;
    public static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    public static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;
    private String filePath;


    public PaiZhaoOrXiangCe(Activity context) {
        this.context = context;
    }

    public void popupInit() {
        View inflate = LayoutInflater.from(context).inflate(R.layout.photo_popup_layout, null);
        initPopupView(inflate);

        photoPopup = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        photoPopup.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        photoPopup.setOutsideTouchable(true);
    }

    private void initPopupView(View inflate) {
        paiBtn = inflate.findViewById(R.id.photo_popup_pai_btn);
        xiangCeBtn = inflate.findViewById(R.id.photo_popup_xiang_ce_btn);
        returnBtn = inflate.findViewById(R.id.photo_popup_return_btn);
        paiBtn.setOnClickListener(this);
        xiangCeBtn.setOnClickListener(this);
        returnBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.photo_popup_pai_btn:
                filePath = getFilePath();
                PhotoHelp.applyForCameraPermission(context, CAMERA_PERMISSIONS_REQUEST_CODE, filePath, CODE_CAMERA_REQUEST);
                dismissPopup();
                break;
            case R.id.photo_popup_xiang_ce_btn:
                PhotoHelp.autoObtainStoragePermission(context, STORAGE_PERMISSIONS_REQUEST_CODE, CODE_GALLERY_REQUEST);
                dismissPopup();
                break;
            case R.id.photo_popup_return_btn:
                dismissPopup();
                break;
        }

    }

    public void showPopup(int layoutId) {
        photoPopup.showAtLocation(context.getLayoutInflater().inflate(layoutId, null), Gravity.BOTTOM, 0, 0);
    }

    public void dismissPopup() {
        photoPopup.dismiss();
    }

    public String getFilePath() {
        return PhotoHelp.getFilePath();
    }

}
