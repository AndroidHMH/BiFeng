package com.coinwind.bifeng.ui.sendtask.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.sendtask.config.GetSendMsgHelp;
import com.coinwind.bifeng.ui.sendtask.config.PaiZhaoOrXiangCe;
import com.coinwind.bifeng.ui.sendtask.contract.SendZhuanFaTaskContract;
import com.coinwind.bifeng.ui.sendtask.presenter.SendZhuanFaTaskPresenter;
import com.coinwind.bifeng.ui.submittask.config.PhotoHelp;
import com.coinwind.bifeng.view.ClearEditText;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 发布注册任务
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class SendZhuCeTaskActivity extends BaseActivity<SendZhuanFaTaskPresenter> implements SendZhuanFaTaskContract.View {

    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.title_layout_return_btn)
    LinearLayout titleLayoutReturnBtn;
    @BindView(R.id.send_task_title_et)
    ClearEditText sendTaskTitleEt;
    @BindView(R.id.send_task_title_count_tv)
    TextView sendTaskTitleCountTv;
    @BindView(R.id.send_task_img_btn)
    LinearLayout sendTaskImgBtn;
    @BindView(R.id.send_task_img_img)
    ImageView sendTaskImgImg;
    @BindView(R.id.send_zhu_ce_url_et)
    EditText sendZhuCeUrlEt;
    @BindView(R.id.send_task_title_first_et)
    EditText sendTaskTitleFirstEt;
    @BindView(R.id.send_task_title_second_et)
    EditText sendTaskTitleSecondEt;
    @BindView(R.id.send_task_title_third_et)
    EditText sendTaskTitleThirdEt;
    @BindView(R.id.bu_zou_next_btn)
    LinearLayout buZouNextBtn;
    private PaiZhaoOrXiangCe paiZhaoOrXiangCe;
    private String filePath;
    private String imgUrl;
    private String type;

    public static void openSendZhuCeTaskActivity(Context context, String type) {
        Intent intent = new Intent(context, SendZhuCeTaskActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_send_zhu_ce_task;
    }

    @Override
    protected void init() {
        paiZhaoOrXiangCe = new PaiZhaoOrXiangCe(this);
        paiZhaoOrXiangCe.popupInit();
        type = getIntent().getStringExtra("type");
        initView();
    }

    private void initView() {
        if ("5".equals(type) || "6".equals(type) || "7".equals(type)) {
            //调研任务或答题任务
            sendZhuCeUrlEt.setVisibility(View.GONE);

        } else {
            sendZhuCeUrlEt.setVisibility(View.VISIBLE);

        }
    }

    @Override
    protected void loadDate() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PaiZhaoOrXiangCe.CODE_CAMERA_REQUEST://拍照完成回调
                    Bitmap cameraBitmap = BitmapFactory.decodeFile(filePath);
                    setImg(cameraBitmap);
                    break;
                case PaiZhaoOrXiangCe.CODE_GALLERY_REQUEST://访问相册完成回调
                    Bitmap xiangCeBitmap = PhotoHelp.xiangCeResult(this, data);
                    setImg(xiangCeBitmap);
                    break;
            }
        }
    }

    private void setImg(Bitmap bitmap) {
        if (bitmap != null) {
            sendTaskImgBtn.setVisibility(View.GONE);
            sendTaskImgImg.setVisibility(View.VISIBLE);
            sendTaskImgImg.setImageBitmap(bitmap);
            File file = PhotoHelp.saveBitmapFile(bitmap, getCodeCacheDir().getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
            presenter.updateImgs(file);
        }
    }

    @OnClick({R.id.send_task_img_btn, R.id.bu_zou_next_btn, R.id.title_layout_return_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_layout_return_btn:
                finish();
                break;
            case R.id.send_task_img_btn:
                paiZhaoOrXiangCe.showPopup(R.layout.activity_send_zhu_ce_task);
                break;
            case R.id.bu_zou_next_btn:
                String title = sendTaskTitleEt.getText().toString().trim();
                String label = GetSendMsgHelp.getLabel(sendTaskTitleFirstEt, sendTaskTitleSecondEt, sendTaskTitleThirdEt);
                if ("5".equals(type) || "6".equals(type)) {
                    //调研任务
                    GetSendMsgHelp.diaoYanOpenSendTaskActivity(this, title, imgUrl, label, type);
                } else {
                    String url = sendZhuCeUrlEt.getText().toString().trim();
                    GetSendMsgHelp.zhuCeOpenSendTaskActivity(this, title, imgUrl, url, label, type);
                }
                break;
        }
    }

    @Override
    public void successUpdate(String imgUrl) {
        ToastHelp.showShort(this, "上传任务配图成功");
        this.imgUrl = imgUrl;
    }

    @Override
    public void errorUpdate(String errorMsg) {
        ToastHelp.showShort(this, errorMsg);
    }
}
