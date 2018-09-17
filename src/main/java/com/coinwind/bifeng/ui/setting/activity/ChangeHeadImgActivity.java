package com.coinwind.bifeng.ui.setting.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.login.activity.LoginActivity;
import com.coinwind.bifeng.ui.sendtask.config.PaiZhaoOrXiangCe;
import com.coinwind.bifeng.ui.setting.contract.ChangeHeadImgContract;
import com.coinwind.bifeng.ui.setting.presenter.ChangeHeadImgPresenter;
import com.coinwind.bifeng.ui.submittask.config.PhotoHelp;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 更换头像
 */
public class ChangeHeadImgActivity extends BaseActivity<ChangeHeadImgPresenter> implements ChangeHeadImgContract.View {

    @BindView(R.id.change_head_return_btn)
    LinearLayout changeHeadReturnBtn;
    @BindView(R.id.change_head_img_option_btn)
    LinearLayout changeHeadImgOptionBtn;
    @BindView(R.id.change_head_img)
    ImageView changeHeadImg;

    private PaiZhaoOrXiangCe paiZhaoOrXiangCe;
    private String filePath;
    public static final int REQUEST_CROP_PHOTO = 10010;
    public static final int HEAD_IMG_SUCCESS = 10020;
    public static final int HEAD_IMG_ERROR = 10030;
    private String type;
    private String where;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_head_img;
    }

    @Override
    protected void init() {
        where = getIntent().getStringExtra("where");
        paiZhaoOrXiangCe = new PaiZhaoOrXiangCe(this);
        paiZhaoOrXiangCe.popupInit();
    }

    @Override
    protected void loadDate() {

    }

    @OnClick({R.id.change_head_return_btn, R.id.change_head_img_option_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change_head_return_btn:
                finish();
                break;
            case R.id.change_head_img_option_btn:
                paiZhaoOrXiangCe.showPopup(R.layout.activity_change_head_img);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PaiZhaoOrXiangCe.CAMERA_PERMISSIONS_REQUEST_CODE:
                filePath = paiZhaoOrXiangCe.getFilePath();
                PhotoHelp.cameraPermissionResult(this, grantResults, PaiZhaoOrXiangCe.CAMERA_PERMISSIONS_REQUEST_CODE, filePath, PaiZhaoOrXiangCe.CODE_CAMERA_REQUEST);
                break;
            case PaiZhaoOrXiangCe.STORAGE_PERMISSIONS_REQUEST_CODE://调用系统相册申请Sdcard权限回调
                PhotoHelp.xiangCePermissionResult(this, grantResults, PaiZhaoOrXiangCe.CODE_GALLERY_REQUEST);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PaiZhaoOrXiangCe.CODE_CAMERA_REQUEST://拍照完成回调
                    type = "1";
                    gotoClipActivity(Uri.fromFile(new File(paiZhaoOrXiangCe.getImgFilePath())));
                    break;
                case PaiZhaoOrXiangCe.CODE_GALLERY_REQUEST://访问相册完成回调
                    type = "2";
                    Uri xiangCeUri = PhotoHelp.xiangCeResultUri(this, data);
                    gotoClipActivity(xiangCeUri);
                    break;
                case REQUEST_CROP_PHOTO://裁剪完成回调
                    final Uri clipUri = data.getData();
                    if (clipUri == null) {
                        return;
                    }
                    String cropImagePath = PhotoHelp.getRealFilePathFromUri(getApplicationContext(), clipUri);
                    Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
                    //上传服务器
                    File file = PhotoHelp.saveBitmapFile(bitMap, getCodeCacheDir().getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
                    presenter.updateImage(file);
                    break;


            }
        }
    }

    /**
     * 打开截图界面
     */
    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipActivity.class);
        intent.putExtra("type", type);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }

    @Override
    public void successUpdate(String imgUrl) {
        Glide.with(this).load(imgUrl).into(changeHeadImg);
        if ("NewTaskActivity".equals(where)) {
            presenter.doHeadImgTask(getIntent().getStringExtra("taskId"), imgUrl);
        }
//        changeHeadImg.setImageBitmap(bitMap);
    }

    @Override
    public void errorUpdate(String errorMsg) {
        if ("NewTaskActivity".equals(where)) {
//            finish();
            setResult(HEAD_IMG_ERROR);
        }
        ToastHelp.showShort(this, errorMsg);
    }

    @Override
    public void loginTimeOut() {
        SpHelp.loginOut();
        LoginActivity.openLoginActivity(this);
    }

    @Override
    public void doHeadImgTaskSuccess(String imgUrl) {
        if ("NewTaskActivity".equals(where)) {
//            finish();
            SpHelp.putUserInformation(SpHelp.HEAD_IMG, imgUrl);
            setResult(HEAD_IMG_SUCCESS);
        }
    }
}
