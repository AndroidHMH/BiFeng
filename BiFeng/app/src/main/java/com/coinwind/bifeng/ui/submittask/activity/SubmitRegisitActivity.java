package com.coinwind.bifeng.ui.submittask.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.ui.submittask.adapter.AddImgAdapter;
import com.coinwind.bifeng.ui.submittask.config.PhotoHelp;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SubmitRegisitActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.title_layout_return_btn)
    LinearLayout titleLayoutReturnBtn;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.submit_regisit_task_phone_et)
    EditText submitRegisitTaskPhoneEt;
    @BindView(R.id.submit_comments_img_recycler)
    RecyclerView submitCommentsImgRecycler;
    @BindView(R.id.add_img_btn)
    ImageView addImgBtn;
    @BindView(R.id.submit_comments_photo_count_tv)
    TextView submitCommentsPhotoCountTv;
    @BindView(R.id.submit_regisit_task_submit_btn)
    TextView submitRegisitTaskSubmitBtn;
    private PopupWindow photoPopup;

    private TextView paiBtn;
    private TextView xiangCeBtn;
    private TextView returnBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_submit_regisit;
    }

    @Override
    protected void init() {
        recyclerInit();
        popupInit();
    }

    private void recyclerInit() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        submitCommentsImgRecycler.setLayoutManager(linearLayoutManager);
//        bitmapList = new ArrayList<>();
//        addImgAdapter = new AddImgAdapter(bitmapList);
//        addImgAdapter.setOnItemClick(this);
//        submitCommentsImgRecycler.setAdapter(addImgAdapter);
    }

    private void popupInit() {
        View inflate = getLayoutInflater().inflate(R.layout.photo_popup_layout, null);
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
    protected void loadDate() {

    }


    @OnClick({R.id.title_layout_return_btn, R.id.add_img_btn, R.id.submit_regisit_task_submit_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_layout_return_btn:
                break;
            case R.id.add_img_btn:
                break;
            case R.id.submit_regisit_task_submit_btn:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.photo_popup_pai_btn:
//                filePath = PhotoHelp.getFilePath();
//                PhotoHelp.applyForCameraPermission(this, CAMERA_PERMISSIONS_REQUEST_CODE, filePath, CODE_CAMERA_REQUEST);
//                dismissPopup();
//                break;
//            case R.id.photo_popup_xiang_ce_btn:
//                PhotoHelp.autoObtainStoragePermission(this, STORAGE_PERMISSIONS_REQUEST_CODE, CODE_GALLERY_REQUEST);
//                dismissPopup();
//                break;
//            case R.id.photo_popup_return_btn:
//                dismissPopup();
//                break;
        }
    }
}
