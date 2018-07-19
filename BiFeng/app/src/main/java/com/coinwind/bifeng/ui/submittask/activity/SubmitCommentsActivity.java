package com.coinwind.bifeng.ui.submittask.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.coinwind.bifeng.config.PhotoUtils;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.homepage.activity.MainActivity;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提交任务页面
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class SubmitCommentsActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.submit_comments_description_et)
    EditText submitCommentsDescriptionEt;
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
    private List<Bitmap> bitmapList;
    private SuperAdapter superAdapter;

    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");;
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    //    private Uri imageUri;
//    private Uri cropImageUri;
    private PopupWindow photoPopup;
    private TextView paiBtn;
    private TextView xiangCeBtn;
    private TextView returnBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_submit_comments;
    }

    @Override
    protected void init() {
        recyclerInit();
        popupInit();
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
                autoObtainCameraPermission();
                dismissPopup();
                break;
            case R.id.photo_popup_xiang_ce_btn:
                autoObtainStoragePermission();
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        submitCommentsImgRecycler.setLayoutManager(linearLayoutManager);
        bitmapList = new ArrayList<>();
        superAdapter = new SuperAdapter<Bitmap>(this, bitmapList, R.layout.add_img_view) {
            @Override
            public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, Bitmap item) {
                holder.setImageBitmap(R.id.add_img_img, item);
            }
        };
        submitCommentsImgRecycler.setAdapter(superAdapter);
    }

    @Override
    protected void loadDate() {

    }

    @OnClick({R.id.add_img_btn, R.id.submit_comments_submit_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_img_btn:
                showPopup();
                break;
            case R.id.submit_comments_submit_btn:
                break;
        }
    }

    /**
     * 自动获取相机权限
     */
    private void autoObtainCameraPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                ToastHelp.showShort(this, "您已经拒绝过一次");
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_PERMISSIONS_REQUEST_CODE);
        } else {//有权限直接调用系统相机拍照
            if (PhotoUtils.hasSdcard()) {
                Uri imageUri = Uri.fromFile(fileUri);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    imageUri = FileProvider.getUriForFile(this, "com.coinwind.bifeng.fileprovider", fileUri);//通过FileProvider创建一个content类型的Uri
//                fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
                PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
            } else {
                ToastHelp.showShort(this, "设备没有SD卡！");
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case CAMERA_PERMISSIONS_REQUEST_CODE: {//调用系统相机申请拍照权限回调
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (PhotoUtils.hasSdcard()) {
                        Uri imageUri = Uri.fromFile(fileUri);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            imageUri = FileProvider.getUriForFile(this, "com.coinwind.bifeng.fileprovider", fileUri);//通过FileProvider创建一个content类型的Uri
                        PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
                    } else {
                        ToastHelp.showShort(this, "设备没有SD卡！");
                    }
                } else {
                    ToastHelp.showShort(this, "请允许打开相机！！");
                }
                break;
            }
            case STORAGE_PERMISSIONS_REQUEST_CODE://调用系统相册申请Sdcard权限回调
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
                } else {

                    ToastHelp.showShort(this, "请允许打操作SDCard！！");
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int output_X = 480;
        int output_Y = 480;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CODE_CAMERA_REQUEST://拍照完成回调
                    Uri cropImageUri = Uri.fromFile(fileUri);
                    Uri newUri2 = Uri.parse(PhotoUtils.getPath(this, data.getData()));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                        newUri2 = FileProvider.getUriForFile(this, "com.coinwind.bifeng.fileprovider", new File(newUri2.getPath()));
//                    Uri paiUri = Uri.parse(PhotoUtils.getPath(this, data.getData()));
                    setBitmat(newUri2);
//                    PhotoUtils.cropImageUri(this, imageUri, cropImageUri, 1, 1, output_X, output_Y, CODE_RESULT_REQUEST);
                    break;
                case CODE_GALLERY_REQUEST://访问相册完成回调
                    if (PhotoUtils.hasSdcard()) {
                        Uri cropImageUri2 = Uri.fromFile(fileCropUri);
                        Uri newUri = Uri.parse(PhotoUtils.getPath(this, data.getData()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            newUri = FileProvider.getUriForFile(this, "com.coinwind.bifeng.fileprovider", new File(newUri.getPath()));
                        setBitmat(newUri);
//                        PhotoUtils.cropImageUri(this, newUri, cropImageUri, 1, 1, output_X, output_Y, CODE_RESULT_REQUEST);
                    } else {
                        ToastHelp.showShort(this, "设备没有SD卡！");
                    }
                    break;
                case CODE_RESULT_REQUEST:
//                    Bitmap bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, this);
//                    if (bitmap != null) {
//                        bitmapList.add(bitmap);
//                        superAdapter.notifyDataSetChanged();
////                        showImages(bitmap);
//                    }
                    break;
            }
        }
    }

    public void setBitmat(Uri cropImageUri) {
        Bitmap bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, this);
        if (bitmap != null) {
            bitmapList.add(bitmap);
            superAdapter.notifyDataSetChanged();
//                        showImages(bitmap);
        }
    }

    /**
     * 自动获取sdk权限
     */

    private void autoObtainStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSIONS_REQUEST_CODE);
        } else {
            PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
        }

    }


}
