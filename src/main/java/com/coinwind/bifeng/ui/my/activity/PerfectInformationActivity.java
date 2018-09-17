package com.coinwind.bifeng.ui.my.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coinwind.bifeng.R;
import com.coinwind.bifeng.app.BFApplication;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.ui.login.activity.LoginActivity;
import com.coinwind.bifeng.ui.my.config.InfoHelp;
import com.coinwind.bifeng.ui.my.contract.PerfectInformationContract;
import com.coinwind.bifeng.ui.my.presenter.PerfectInformationPresenter;
import com.coinwind.bifeng.ui.setting.activity.ChangePaswActivity;
import com.coinwind.bifeng.ui.setting.config.SetMessageHelp;
import com.coinwind.bifeng.ui.submittask.biz.SubmitService;
import com.coinwind.bifeng.ui.submittask.config.PhotoHelp;
import com.coinwind.bifeng.ui.submittask.config.UpdateFile;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 个人资料的页面
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class PerfectInformationActivity extends BaseActivity<PerfectInformationPresenter> implements View.OnClickListener, PerfectInformationContract.View {


    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.title_layout_return_btn)
    LinearLayout titleLayoutReturnBtn;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.perfect_information_name_tv)
    TextView perfectInformationNameTv;
    @BindView(R.id.perfect_information_name_btn)
    RelativeLayout perfectInformationNameBtn;
    @BindView(R.id.perfect_information_logo_img)
    RoundedImageView perfectInformationLogoImg;
    @BindView(R.id.perfect_information_logo_btn)
    RelativeLayout perfectInformationLogoBtn;
    @BindView(R.id.perfect_information_phone_tv)
    TextView perfectInformationPhoneTv;
    @BindView(R.id.perfect_information_phone_btn)
    RelativeLayout perfectInformationPhoneBtn;
    @BindView(R.id.perfect_information_my_tv)
    TextView perfectInformationMyTv;
    @BindView(R.id.perfect_information_my_btn)
    RelativeLayout perfectInformationMyBtn;

    private String filePath;
    private PopupWindow photoPopup;
    private TextView paiBtn;
    private TextView xiangCeBtn;
    private TextView returnBtn;

    public static final int REQUEST_CODE = 100;
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_perfect_information;
    }

    @Override
    protected void init() {
        popupInit();
        perfectInformationNameTv.setText(SpHelp.getUserInformation(SpHelp.NICK_NAME));
        perfectInformationPhoneTv.setText(SpHelp.getUserInformation(SpHelp.PHONE));
        String head_img_url = SpHelp.getUserInformation(SpHelp.HEAD_IMG);
        if (head_img_url != null && !"".equals(head_img_url)) {
            Glide.with(this).load(head_img_url).into(perfectInformationLogoImg);
        }
        InfoHelp.isShowQiYe(perfectInformationMyBtn, perfectInformationMyTv);
    }

    @Override
    protected void loadDate() {

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

    @OnClick({R.id.title_layout_return_btn, R.id.perfect_information_name_btn, R.id.perfect_information_logo_btn, R.id.perfect_information_phone_btn, R.id.perfect_information_my_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_layout_return_btn:
                finish();
                break;
            case R.id.perfect_information_name_btn:
                Intent nickNameIntent = InfoHelp.getPerfectInformationIntent(this, SpHelp.NICK_NAME);
                startActivityForResult(nickNameIntent, REQUEST_CODE);
//                startActivity(new Intent(this, ChangePaswActivity.class));
                break;
            case R.id.perfect_information_logo_btn:
                showPopup();
                break;
            case R.id.perfect_information_phone_btn:
                break;
            case R.id.perfect_information_my_btn:
//                Intent qiYeInfoIntent = InfoHelp.getPerfectInformationIntent(this, SpHelp.QIYE_INFO);
//                startActivityForResult(qiYeInfoIntent, REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case SetMessageHelp.NICK_NAME_REQUEST_CODE:
                BFApplication.context = this;
                perfectInformationNameTv.setText(InfoHelp.putUserInfo(data));
                break;
            case SetMessageHelp.QIYE_INFO_REQUEST_CODE:
                BFApplication.context = this;
                perfectInformationMyTv.setText(InfoHelp.putUserInfo(data));
                break;
        }
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CODE_CAMERA_REQUEST://拍照完成回调
                    Bitmap cameraBitmap = BitmapFactory.decodeFile(filePath);
                    setImg(cameraBitmap);
                    break;
                case CODE_GALLERY_REQUEST://访问相册完成回调
                    Bitmap xiangCeBitmap = PhotoHelp.xiangCeResult(this, data);
                    setImg(xiangCeBitmap);
                    break;
            }
        }
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

    @Override
    public void successUpdate(String imgUrl) {
        ToastHelp.showShort(this, "上传头像成功");
        SpHelp.putUserInformation(SpHelp.HEAD_IMG, imgUrl);
        presenter.changeHeadImg(SpHelp.getUserInformation(SpHelp.ID), imgUrl);
    }

    @Override
    public void errorUpdate(String errorMsg) {
        ToastHelp.showShort(this, errorMsg);
    }

    @Override
    public void changeSuccess(String message) {
        ToastHelp.showShort(this, message);
        Glide.with(this).load(SpHelp.getUserInformation(SpHelp.HEAD_IMG)).into(perfectInformationLogoImg);
    }

    @Override
    public void changeError(String errorMsg) {
        SpHelp.putUserInformation(SpHelp.HEAD_IMG, "");
    }

    @Override
    public void loginOut() {
        SpHelp.loginOut();
        LoginActivity.openLoginActivity(this);
    }

    public void setImg(Bitmap bitmap) {
        perfectInformationLogoImg.setImageBitmap(bitmap);
        File file = PhotoHelp.saveBitmapFile(bitmap, getCodeCacheDir().getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
        presenter.updateHeadImg(file);
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

    public void showPopup() {
        photoPopup.showAtLocation(getLayoutInflater().inflate(R.layout.activity_perfect_information, null), Gravity.BOTTOM, 0, 0);
    }

    public void dismissPopup() {
        photoPopup.dismiss();
    }


}
