package com.coinwind.bifeng.ui.sendtask.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.base.NoNetworkBaseActivity;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.sendtask.config.GetSendMsgHelp;
import com.coinwind.bifeng.ui.sendtask.config.PaiZhaoOrXiangCe;
import com.coinwind.bifeng.ui.sendtask.contract.SendZhuanFaTaskContract;
import com.coinwind.bifeng.ui.sendtask.presenter.SendZhuanFaTaskPresenter;
import com.coinwind.bifeng.ui.submittask.config.PhotoHelp;
import com.coinwind.bifeng.view.ClearEditText;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 发布任务的页面
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class SendZhuanFaTaskActivity extends BaseActivity<SendZhuanFaTaskPresenter> implements View.OnClickListener, SendZhuanFaTaskContract.View {

    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.title_layout_return_btn)
    LinearLayout titleLayoutReturnBtn;
    @BindView(R.id.send_task_title_et)
    ClearEditText sendTaskTitleEt;
    @BindView(R.id.send_task_title_count_tv)
    TextView sendTaskTitleCountTv;
    @BindView(R.id.send_task_content_et)
    EditText sendTaskContentEt;
    @BindView(R.id.send_task_content_count_tv)
    TextView sendTaskContentCountTv;
    @BindView(R.id.send_task_img_btn)
    LinearLayout sendTaskImgBtn;
    @BindView(R.id.send_task_img_img)
    ImageView sendTaskImgImg;
    @BindView(R.id.send_task_type_tv)
    TextView sendTaskTypeTv;
    @BindView(R.id.send_task_type_layout_btn)
    LinearLayout sendTaskTypeLayoutBtn;
    @BindView(R.id.send_task_title_first_et)
    EditText sendTaskTitleFirstEt;
    @BindView(R.id.send_task_title_second_et)
    EditText sendTaskTitleSecondEt;
    @BindView(R.id.send_task_title_third_et)
    EditText sendTaskTitleThirdEt;
    @BindView(R.id.bu_zou_next_btn)
    LinearLayout buZouNextBtn;

    private String filePath;

    public TextView sendTaskPopupDongTaiTv;
    public TextView sendTaskPopupXinBiTv;
    public TextView sendTaskPopupZhouBaoTv;
    public TextView sendTaskPopupGongGaoTv;
    public TextView sendTaskPopupHuoDongTv;
    public TextView sendTaskPopupJieShaoTv;
    public TextView sendTaskPopupQuXiaoTv;
    private PopupWindow typePhotoPopup;
    private String imgUrl;
    private PaiZhaoOrXiangCe paiZhaoOrXiangCe;

    public static void openSendZhuanFaTaskActivity(Context context, String type) {
        Intent intent = new Intent(context, SendZhuanFaTaskActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zhuan_fa_send_task;
    }

    @Override
    protected void init() {
        paiZhaoOrXiangCe = new PaiZhaoOrXiangCe(this);
        paiZhaoOrXiangCe.popupInit();
        popupTypeInit();
    }

    @Override
    protected void loadDate() {

    }

    @OnClick({R.id.title_layout_return_btn, R.id.send_task_img_btn, R.id.send_task_type_layout_btn, R.id.bu_zou_next_btn, R.id.send_task_content_et})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_layout_return_btn:
                finish();
                break;
            case R.id.send_task_img_btn:
                paiZhaoOrXiangCe.showPopup(R.layout.activity_zhuan_fa_send_task);
                break;
            case R.id.send_task_content_et:

                break;
            case R.id.send_task_type_layout_btn:
                showTypePopup();
                break;
            case R.id.bu_zou_next_btn:
                String taskTitle = sendTaskTitleEt.getText().toString().trim();
                String taskContent = "";
//                        sendTaskContentEt.getText().toString().trim();
                String taskType = sendTaskTypeTv.getText().toString().trim();
                String label = GetSendMsgHelp.getLabel(sendTaskTitleFirstEt, sendTaskTitleSecondEt, sendTaskTitleThirdEt);
                String type = getIntent().getStringExtra("type");
                GetSendMsgHelp.zhuanFaOpenSendTaskActivity(this, taskTitle, taskContent, taskType, label, imgUrl, type);
                break;
        }
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_task_popup_dong_tai_tv:
                dismissTypePopup(sendTaskPopupDongTaiTv.getText().toString());
                break;
            case R.id.send_task_popup_xin_bi_tv:
                dismissTypePopup(sendTaskPopupXinBiTv.getText().toString());
                break;
            case R.id.send_task_popup_zhou_bao_tv:
                dismissTypePopup(sendTaskPopupZhouBaoTv.getText().toString());
                break;
            case R.id.send_task_popup_gong_gao_tv:
                dismissTypePopup(sendTaskPopupGongGaoTv.getText().toString());
                break;
            case R.id.send_task_popup_huo_dong_tv:
                dismissTypePopup(sendTaskPopupHuoDongTv.getText().toString());
                break;
            case R.id.send_task_popup_jie_shao_tv:
                dismissTypePopup(sendTaskPopupJieShaoTv.getText().toString());
                break;
            case R.id.send_task_popup_qu_xiao_tv:
                dismissTypePopup("");
                break;
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

    private void popupTypeInit() {
        View inflate = getLayoutInflater().inflate(R.layout.send_task_type_popup, null);
        initTypePopupView(inflate);

        typePhotoPopup = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        typePhotoPopup.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        typePhotoPopup.setOutsideTouchable(true);
    }

    private void initTypePopupView(View rootView) {
        this.sendTaskPopupDongTaiTv = (TextView) rootView.findViewById(R.id.send_task_popup_dong_tai_tv);
        this.sendTaskPopupXinBiTv = (TextView) rootView.findViewById(R.id.send_task_popup_xin_bi_tv);
        this.sendTaskPopupZhouBaoTv = (TextView) rootView.findViewById(R.id.send_task_popup_zhou_bao_tv);
        this.sendTaskPopupGongGaoTv = (TextView) rootView.findViewById(R.id.send_task_popup_gong_gao_tv);
        this.sendTaskPopupHuoDongTv = (TextView) rootView.findViewById(R.id.send_task_popup_huo_dong_tv);
        this.sendTaskPopupJieShaoTv = (TextView) rootView.findViewById(R.id.send_task_popup_jie_shao_tv);
        this.sendTaskPopupQuXiaoTv = (TextView) rootView.findViewById(R.id.send_task_popup_qu_xiao_tv);

        sendTaskPopupDongTaiTv.setOnClickListener(this);
        sendTaskPopupXinBiTv.setOnClickListener(this);
        sendTaskPopupZhouBaoTv.setOnClickListener(this);
        sendTaskPopupGongGaoTv.setOnClickListener(this);
        sendTaskPopupHuoDongTv.setOnClickListener(this);
        sendTaskPopupJieShaoTv.setOnClickListener(this);
        sendTaskPopupQuXiaoTv.setOnClickListener(this);
    }

    public void showTypePopup() {
        typePhotoPopup.showAtLocation(getLayoutInflater().inflate(R.layout.activity_submit_comments, null), Gravity.BOTTOM, 0, 0);
    }

    public void dismissTypePopup(String count) {
        sendTaskTypeTv.setText(count);
        typePhotoPopup.dismiss();
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
