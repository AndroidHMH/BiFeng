package com.coinwind.bifeng.ui.submittask.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.homepage.activity.MainActivity;
import com.coinwind.bifeng.ui.login.activity.LoginActivity;
import com.coinwind.bifeng.ui.submittask.adapter.AddImgAdapter;
import com.coinwind.bifeng.ui.submittask.config.PhotoHelp;
import com.coinwind.bifeng.ui.submittask.config.UpdateFile;
import com.coinwind.bifeng.ui.submittask.contract.SubmitContract;
import com.coinwind.bifeng.ui.submittask.presenter.SubmitPresenter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提交任务页面
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class SubmitCommentsActivity extends BaseActivity<SubmitPresenter> implements SubmitContract.View, View.OnClickListener, AddImgAdapter.OnItemClick, AddImgAdapter.OnItemImgClick {


    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.submit_comments_description_et)
    EditText submitCommentsDescriptionEt;
    @BindView(R.id.submit_comments_phone_et)
    EditText submitCommentsPhoneEt;
    @BindView(R.id.submit_comments_text_count_tv)
    TextView submitCommentsTextCountTv;
    @BindView(R.id.add_img_btn)
    ImageView addImgBtn;
    @BindView(R.id.submit_comments_photo_count_tv)
    TextView submitCommentsPhotoCountTv;
    @BindView(R.id.submit_comments_submit_btn)
    TextView submitCommentsSubmitBtn;
    @BindView(R.id.submit_comments_img_recycler)
    RecyclerView submitCommentsImgRecycler;
    @BindView(R.id.title_layout_return_btn)
    LinearLayout titleLayoutReturnBtn;
    @BindView(R.id.title_bar_layout)
    LinearLayout titleBarLayout;
    @BindView(R.id.submit_comments_all_layout)
    LinearLayout submitCommentsAllLayout;
    @BindView(R.id.submit_comments_shuo_ming_tv)
    TextView submitCommentsShuoMingTv;
    @BindView(R.id.submit_comments_shu_cu_layout)
    LinearLayout submitCommentsShuCuLayout;
    private List<Bitmap> bitmapList;
    private AddImgAdapter addImgAdapter;

    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;

    private PopupWindow photoPopup;
    private TextView paiBtn;
    private TextView xiangCeBtn;
    private TextView returnBtn;
    private String filePath;
    private UpdateFile updateFile;
    private List<String> imgsUrl;
    private TaskBean bean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_submit_comments;
    }

    @Override
    protected void init() {
        titleTitleTv.setText("提交任务");
        bean = (TaskBean) getIntent().getSerializableExtra("bean");
        initShowUi();
        imgsUrl = new ArrayList<>();
        updateFile = new UpdateFile();
        recyclerInit();
        popupInit();
    }

    private void initShowUi() {
        titleTitleTv.setText("提交任务");
        String type = getIntent().getStringExtra("type");
        if ("phone".equals(type)) {
            submitCommentsShuoMingTv.setText(getResources().getString(R.string.phone_num));
            submitCommentsDescriptionEt.setVisibility(View.GONE);
            submitCommentsPhoneEt.setVisibility(View.VISIBLE);
            submitCommentsShuCuLayout.setVisibility(View.INVISIBLE);
        } else {
            submitCommentsShuoMingTv.setText(getResources().getString(R.string.task_description));
            submitCommentsShuCuLayout.setVisibility(View.VISIBLE);
            submitCommentsDescriptionEt.setVisibility(View.VISIBLE);
            submitCommentsPhoneEt.setVisibility(View.GONE);
        }
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.photo_popup_pai_btn:
                filePath = PhotoHelp.getFilePath();
                PhotoHelp.applyForCameraPermission(this, CAMERA_PERMISSIONS_REQUEST_CODE, filePath, CODE_CAMERA_REQUEST);
                dismissPopup();
                break;
            case R.id.photo_popup_xiang_ce_btn:
                PhotoHelp.autoObtainStoragePermission(this, STORAGE_PERMISSIONS_REQUEST_CODE, CODE_GALLERY_REQUEST);
                dismissPopup();
                break;
            case R.id.photo_popup_return_btn:
                dismissPopup();
                break;
        }
    }


    public void showPopup() {
        photoPopup.showAtLocation(getLayoutInflater().inflate(R.layout.activity_submit_comments, null), Gravity.BOTTOM, 0, 0);
    }

    public void dismissPopup() {
        photoPopup.dismiss();
    }

    private void recyclerInit() {
        submitCommentsImgRecycler.setLayoutManager(new GridLayoutManager(this, 3));
        bitmapList = new ArrayList<>();
        bitmapList.add(BitmapFactory.decodeResource(getResources(), R.mipmap.photo_icon));
        addImgAdapter = new AddImgAdapter(bitmapList);
        addImgAdapter.setOnItemClick(this);
        addImgAdapter.setOnItemImgClick(this);
        submitCommentsImgRecycler.setAdapter(addImgAdapter);
    }

    @Override
    public void onItemImgClick(int position) {
        if ((position + 1) == bitmapList.size()) {
            showPopup();
        }
    }

    /**
     * 删除图片
     *
     * @param position
     */
    @Override
    public void onItemClick(int position) {
        bitmapList.remove(position);
        updateFile.removeFile(position);
        imgsUrl.remove(position);
        submitCommentsPhotoCountTv.setText(imgsUrl.size() + "");
        if (bitmapList.size() != 3) {
            addImgBtn.setVisibility(View.VISIBLE);
        }
        addImgAdapter.notifyDataSetChanged();
    }

    @Override
    protected void loadDate() {

    }

    @Override
    protected void hideErrorView() {
        submitCommentsAllLayout.setVisibility(View.GONE);
    }

    @Override
    protected void showSuccessView() {
        submitCommentsAllLayout.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.title_layout_return_btn, R.id.add_img_btn, R.id.submit_comments_submit_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_layout_return_btn:
                finish();
                break;
            case R.id.add_img_btn:
//                showPopup();
                break;
            case R.id.submit_comments_submit_btn:
                if (SpHelp.getLoginStatus()) {
                    String content = "";
                    if (submitCommentsShuoMingTv.getText().toString().equals(getResources().getString(R.string.phone_num))) {
                        String phone = submitCommentsPhoneEt.getText().toString().trim();
                        if (!presenter.checkPhone(phone)) {
                            submitCommentsDescriptionEt.setText("");
                            break;
                        } else {
                            content = phone;
                        }
                    } else {
                        content = submitCommentsDescriptionEt.getText().toString().trim();
                    }
                    presenter.submitTask(bean.getId(), SpHelp.getUserInformation(SpHelp.ID), imgsUrl, content);
                } else {
                    LoginActivity.openLoginActivity(this);
                }
                break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case CAMERA_PERMISSIONS_REQUEST_CODE:
                filePath = PhotoHelp.getFilePath();
                PhotoHelp.cameraPermissionResult(this, grantResults, CAMERA_PERMISSIONS_REQUEST_CODE, filePath, CODE_CAMERA_REQUEST);
                break;
            case STORAGE_PERMISSIONS_REQUEST_CODE://调用系统相册申请Sdcard权限回调
                PhotoHelp.xiangCePermissionResult(this, grantResults, CODE_GALLERY_REQUEST);
                break;


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CODE_CAMERA_REQUEST://拍照完成回调
                    Bitmap cameraBitmap = BitmapFactory.decodeFile(filePath);
                    addList(cameraBitmap);
                    break;
                case CODE_GALLERY_REQUEST://访问相册完成回调
                    Bitmap xiangCeBitmap = PhotoHelp.xiangCeResult(this, data);
                    addList(xiangCeBitmap);
                    break;
            }
        }
    }

    public void addList(Bitmap bitmap) {
        if (bitmap != null) {
            bitmapList.remove(bitmapList.size() - 1);
            bitmapList.add(bitmap);
            bitmapList.add(BitmapFactory.decodeResource(getResources(), R.mipmap.photo_icon));
            File file = PhotoHelp.saveBitmapFile(bitmap, getCodeCacheDir().getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
            updateFile.addFile(file);
            if (bitmapList.size() == 3) {
                addImgBtn.setVisibility(View.GONE);
            }
            addImgAdapter.notifyDataSetChanged();
            presenter.updateImgs(file);
        }
    }

    @Override
    public void successUpdate(String imgUrl) {
        ToastHelp.showShort(this, "上传任务配图成功");
        imgsUrl.add(imgUrl);
        submitCommentsPhotoCountTv.setText(imgsUrl.size() + "");
    }

    @Override
    public void errorUpdate(String errorMsg) {
        ToastHelp.showShort(this, errorMsg);
    }

    @Override
    public void showSubmitSuccess() {
        ToastHelp.showShort(this, "提交成功");
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void showSubmitFailure(String error) {
        ToastHelp.showShort(this, error);
    }


}
