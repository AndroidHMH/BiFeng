package com.coinwind.bifeng.ui.task.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseFragment;
import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.homepage.bean.MessageEvent;
import com.coinwind.bifeng.ui.searchfor.activity.SearchForActivity;
import com.coinwind.bifeng.ui.submittask.config.PhotoHelp;
import com.coinwind.bifeng.ui.task.activity.AnswerTaskActivity;
import com.coinwind.bifeng.ui.task.adapter.TaskAdapter;
import com.coinwind.bifeng.ui.task.adapter.TaskTypeAdapter;
import com.coinwind.bifeng.ui.task.config.SetViewHelp;
import com.coinwind.bifeng.ui.task.contract.TaskContract;
import com.coinwind.bifeng.ui.task.presenter.TaskPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * 任务大厅页面
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class TaskFragment extends BaseFragment<TaskPresenter> implements TaskContract.View, AbsListView.OnScrollListener, TaskTypeAdapter.OnItemCLick, AdapterView.OnItemClickListener, View.OnClickListener {


    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.title_layout_return_btn)
    LinearLayout titleLayoutReturnBtn;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.task_zhi_xing_btn)
    LinearLayout taskZhiXingBtn;
    @BindView(R.id.task_my_fa_bo_btn)
    LinearLayout taskMyFaBoBtn;
    @BindView(R.id.task_fa_bo_btn)
    LinearLayout taskFaBoBtn;
    @BindView(R.id.task_sou_suo_btn)
    LinearLayout taskSouSuoBtn;
    @BindView(R.id.task_recycler)
    ListView taskRecycler;
    @BindView(R.id.task_type_first_tv)
    TextView taskTypeFirstTv;
    @BindView(R.id.task_type_first_img)
    ImageView taskTypeFirstImg;
    @BindView(R.id.task_type_first_layout)
    LinearLayout taskTypeFirstLayout;
    @BindView(R.id.task_type_second_tv)
    TextView taskTypeSecondTv;
    @BindView(R.id.task_type_second_img)
    ImageView taskTypeSecondImg;
    @BindView(R.id.task_type_second_layout)
    LinearLayout taskTypeSecondLayout;
    @BindView(R.id.task_type_third_tv)
    TextView taskTypeThirdTv;
    @BindView(R.id.task_type_third_img)
    ImageView taskTypeThirdImg;
    @BindView(R.id.task_type_third_layout)
    LinearLayout taskTypeThirdLayout;
    @BindView(R.id.task_all_layout)
    LinearLayout taskAllLayout;
    @BindView(R.id.task_type_layout)
    LinearLayout taskTypeLayout;
    private PopupWindow popupWindow;
    public ImageView taskTypePopupImg;
    public RecyclerView taskTypePopupRecycler;
    private List<String> titles;
    private TaskTypeAdapter taskTypeAdapter;
    private int page = 1;
    private String type = "";
    private String taskType = "";
    private String orderFiel = "a.lrrq";
    private String orderSort = "DESC";
    private boolean loadFinishFlag;
    private List<TaskBean> mList;
    private TaskAdapter taskAdapter;
    private PopupWindow renZhwngPopupWindow;

    public ImageView certification_popup_qu_xiao_btn;
    public EditText certification_popup_chan_pin_et;
    public ImageView certification_popup_logo_img;
    public LinearLayout certification_popup_logo_btn;
    public ImageView certification_popup_ming_pian_img;
    public LinearLayout certification_popup_ming_pian_btn;
    public LinearLayout certification_popup_type_btn;
    public EditText certification_popup_jie_shao_et;
    public TextView certification_popup_submit_btn;
    public TextView certification_popup_type_tv;
    private PopupWindow xjpzPopup;

    private TextView paiBtn;
    private TextView xiangCeBtn;
    private TextView returnBtn;
    public TextView gz_popup_layout_xiang_mu_fang_btn;
    public TextView gz_popup_layout_jiao_yi_suo_btn;
    public TextView gz_popup_layout_return_btn;

    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;
    private String filePath;
    private Bitmap bitmap;
    private String logoImgUrl;
    private String mingPianImgUrl;
    private PopupWindow gzPopupWindow;
    private String gzType;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_task;
    }

    @Override
    protected void init() {
        EventBus.getDefault().register(this);
        popupInit();
        loadFinishFlag = true;
        titles = new ArrayList<>();
        taskTypeAdapter = new TaskTypeAdapter(titles);

        mList = new ArrayList<>();
        taskAdapter = new TaskAdapter(mList, getContext());
        taskRecycler.setAdapter(taskAdapter);

        titleLayoutReturnBtn.setVisibility(View.GONE);
        titleTitleTv.setText("任务大厅");

        taskRecycler.setOnScrollListener(this);
        taskTypeAdapter.setOnItemCLick(this);
        View footView = getLayoutInflater().inflate(R.layout.main_foot_layout, null);
        taskRecycler.addFooterView(footView);

        taskRecycler.setOnItemClickListener(this);
    }

    @Override
    protected void loadDate() {
        presenter.loadContentList(page, type, taskType, orderFiel, orderSort);
    }

    @Override
    protected void hideErrorView() {
        taskAllLayout.setVisibility(View.GONE);
    }

    @Override
    protected void showSuccessView() {
        taskAllLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 任务页面的点击事件
     *
     * @param view
     */
    @OnClick({R.id.title_layout_return_btn, R.id.task_zhi_xing_btn, R.id.task_my_fa_bo_btn, R.id.task_fa_bo_btn, R.id.task_sou_suo_btn, R.id.task_type_first_layout, R.id.task_type_second_layout, R.id.task_type_third_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.task_zhi_xing_btn:
                SetViewHelp.startZhiXing(getContext());
                break;
            case R.id.task_my_fa_bo_btn:
                SetViewHelp.startFaBo(getContext());
                break;
            case R.id.task_fa_bo_btn:
                //判断是否认证
//                if (SpHelp.getLoginStatus()) {
//                    String auth_flag = SpHelp.getUserInformation(SpHelp.AUTH_FLAG);
//                    if ("0".equals(auth_flag)) {
//                        //弹出认证窗口
//                        showRenZhengPopup();
//                    } else {
//                        startActivity(new Intent(getContext(), TaskTypeActivity.class));
//                    }
//                } else {
//                    LoginActivity.openLoginActivity(getContext());
//                }
                ToastHelp.showShort(getContext(), "程序小哥正在努力开发中，尽情期待。");

                break;
            case R.id.task_sou_suo_btn:
                startActivity(new Intent(getContext(), SearchForActivity.class));
                break;
            case R.id.task_type_first_layout:
                showTypePopup(R.mipmap.task_type_top_icon, SetViewHelp.TYPE_ONE);
                break;
            case R.id.task_type_second_layout:
                showTypePopup(R.mipmap.task_type_top_second_icon, SetViewHelp.TYPE_TWO);
                break;
            case R.id.task_type_third_layout:
                showTypePopup(R.mipmap.task_type_top_third_icon, SetViewHelp.TYPE_THREE);
                break;
        }
    }

    /**
     * 任务列表的item点击事件
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if ((position + 1) != taskRecycler.getCount()) {
            Intent intent = new Intent(getContext(), AnswerTaskActivity.class);
            TaskBean taskBean = mList.get(position);
            intent.putExtra("bean", taskBean);
            startActivity(intent);
        } else {
            ToastHelp.showShort(getContext(), "已经是最后一条了");
        }

    }

    private boolean isGoto = false;

    /**
     * 认证点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.certification_popup_qu_xiao_btn:
                renZhwngPopupWindow.dismiss();
                break;
            case R.id.certification_popup_logo_img:
                //企业logo
                isGoto = true;
                showPopup();
                break;
            case R.id.certification_popup_logo_btn:
                //企业logo
                isGoto = true;
                showPopup();
                break;
            case R.id.certification_popup_ming_pian_img:
                //名片
                isGoto = false;
                showPopup();
                break;
            case R.id.certification_popup_ming_pian_btn:
                //名片
                isGoto = false;
                showPopup();
                break;
            case R.id.certification_popup_type_btn:
                //雇主类型
                initGzType();
                break;
            case R.id.certification_popup_submit_btn:
                //提交认证
                String gzName = certification_popup_chan_pin_et.getText().toString().trim();
                String qiyiInfo = certification_popup_jie_shao_et.getText().toString().trim();
                if (presenter.isGzName(gzName) && presenter.isGzType(gzType) && presenter.isLogoUrl(logoImgUrl)
                        && presenter.isNameCardUrl(mingPianImgUrl) && presenter.isQiyeInfo(qiyiInfo)) {
                    presenter.loadRenZheng(logoImgUrl, mingPianImgUrl, qiyiInfo, gzName, gzType);
                }
                break;
            case R.id.photo_popup_pai_btn:
                //拍照
                filePath = PhotoHelp.getFilePath();
                PhotoHelp.applyForCameraPermission(this, CAMERA_PERMISSIONS_REQUEST_CODE, filePath, CODE_CAMERA_REQUEST);
                dismissPopup();
                break;
            case R.id.photo_popup_xiang_ce_btn:
                //相册
                PhotoHelp.autoObtainStoragePermission(this, STORAGE_PERMISSIONS_REQUEST_CODE, CODE_GALLERY_REQUEST);
                dismissPopup();
                break;
            case R.id.photo_popup_return_btn:
                //取消
                dismissPopup();
                break;
            case R.id.gz_popup_layout_xiang_mu_fang_btn:
                //项目方
                gzType = "项目方";
                certification_popup_type_tv.setText(gzType);
                gzPopupWindow.dismiss();
                break;
            case R.id.gz_popup_layout_jiao_yi_suo_btn:
                //交易所
                gzType = "交易所";
                certification_popup_type_tv.setText(gzType);
                gzPopupWindow.dismiss();
                break;
            case R.id.gz_popup_layout_return_btn:
                //取消
                gzPopupWindow.dismiss();
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CODE_CAMERA_REQUEST://拍照完成回调
                    bitmap = BitmapFactory.decodeFile(filePath);
                    setImg(bitmap);
                    break;
                case CODE_GALLERY_REQUEST://访问相册完成回调
                    bitmap = PhotoHelp.xiangCeResult(getContext(), data);
                    setImg(bitmap);
                    break;
            }
        }
    }

    public void setImg(Bitmap bitmap) {
        File file = PhotoHelp.saveBitmapFile(bitmap, getContext().getCodeCacheDir().getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
        presenter.updateImg(file);

    }

    @Override
    public void showContentList(List<TaskBean> taskBeans) {
        loadFinishFlag = true;
        mList.addAll(taskBeans);
        if (taskBeans.size() == 0) {
            loadFinishFlag = false;
        }
        taskAdapter.notifyDataSetChanged();
    }

    @Override
    public void successUpdate(String imgUrl) {
        if (isGoto) {
            certification_popup_logo_img.setImageBitmap(bitmap);
            logoImgUrl = imgUrl;
        } else {
            certification_popup_ming_pian_img.setImageBitmap(bitmap);
            mingPianImgUrl = imgUrl;
        }
        ToastHelp.showShort(getContext(), "上传图片成功");
    }

    @Override
    public void errorUpdate(String errorMsg) {
        ToastHelp.showShort(getContext(), errorMsg);
    }

    @Override
    public void renZhengSuccess() {
        //认证成功
        ToastHelp.showShort(getContext(), "认证成功");
        renZhwngPopupWindow.dismiss();
    }

    @Override
    public void renZhengError(String errorMsg) {
        ToastHelp.showShort(getContext(), errorMsg);
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //获取屏幕最后Item的ID
        int lastVisibleItem = taskRecycler.getLastVisiblePosition();
        if (lastVisibleItem + 1 == totalItemCount) {
            if (loadFinishFlag) {
                //标志位，防止多次加载
                loadFinishFlag = false;
                page++;
                presenter.loadContentList(page, type, taskType, orderFiel, orderSort);
            }
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        if (titles.size() == 8) {
            String type = titles.get(position);
            taskTypeFirstTv.setText(type);
            if (position == 0) {
                this.type = "";
            } else {
                this.type = String.valueOf(position);
            }
            if (!"转发任务".equals(type) && !"全部任务".equals(type)) {
                taskType = "";
                taskTypeSecondLayout.setVisibility(View.GONE);
                taskTypeSecondLayout.setEnabled(false);
            } else {
                taskTypeSecondLayout.setVisibility(View.VISIBLE);
                taskTypeSecondLayout.setEnabled(true);
            }
        } else if (titles.size() == 7) {
            taskType = String.valueOf(position);
            taskTypeSecondTv.setText(titles.get(position));
        } else {
            orderFiel = SetViewHelp.getOrderField(position);
            orderSort = SetViewHelp.getOrderSort(position);
            taskTypeThirdTv.setText(titles.get(position));
        }
        popupWindow.dismiss();
        page = 1;
        mList.clear();
        taskAdapter.notifyDataSetChanged();
        presenter.loadContentList(page, type, taskType, orderFiel, orderSort);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(MessageEvent event) {
        taskTypeFirstTv.setText(event.getType());
        page = 1;
        mList.clear();
        taskAdapter.notifyDataSetChanged();
        this.type = SetViewHelp.eventBusRes(event, taskTypeSecondLayout);
        presenter.loadContentList(page, type, "", orderFiel, orderSort);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 展示认证的popupWindow
     */
    private void showRenZhengPopup() {
        View inflate = getLayoutInflater().inflate(R.layout.certification_popup_layout, null);
        initRenZheng(inflate);
        renZhwngPopupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        renZhwngPopupWindow.setOutsideTouchable(true);
        renZhwngPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));

        renZhwngPopupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.fragment_task, null), Gravity.CENTER, 0, 0);
    }

    /**
     * 初始化认证popupWindow中的控件
     *
     * @param rootView
     */
    private void initRenZheng(View rootView) {
        this.certification_popup_qu_xiao_btn = (ImageView) rootView.findViewById(R.id.certification_popup_qu_xiao_btn);
        this.certification_popup_chan_pin_et = (EditText) rootView.findViewById(R.id.certification_popup_chan_pin_et);
        this.certification_popup_logo_img = (ImageView) rootView.findViewById(R.id.certification_popup_logo_img);
        this.certification_popup_logo_btn = (LinearLayout) rootView.findViewById(R.id.certification_popup_logo_btn);
        this.certification_popup_ming_pian_img = (ImageView) rootView.findViewById(R.id.certification_popup_ming_pian_img);
        this.certification_popup_ming_pian_btn = (LinearLayout) rootView.findViewById(R.id.certification_popup_ming_pian_btn);
        this.certification_popup_type_btn = (LinearLayout) rootView.findViewById(R.id.certification_popup_type_btn);
        this.certification_popup_jie_shao_et = (EditText) rootView.findViewById(R.id.certification_popup_jie_shao_et);
        this.certification_popup_submit_btn = (TextView) rootView.findViewById(R.id.certification_popup_submit_btn);
        this.certification_popup_type_tv = (TextView) rootView.findViewById(R.id.certification_popup_type_tv);

        certification_popup_qu_xiao_btn.setOnClickListener(this);
        certification_popup_logo_img.setOnClickListener(this);
        certification_popup_logo_btn.setOnClickListener(this);
        certification_popup_ming_pian_img.setOnClickListener(this);
        certification_popup_ming_pian_btn.setOnClickListener(this);
        certification_popup_type_btn.setOnClickListener(this);
        certification_popup_submit_btn.setOnClickListener(this);
    }


    /**
     * 展示筛选框的popupWindow
     *
     * @param imgId
     * @param type
     */
    private void showTypePopup(int imgId, int type) {
        initPopup();
        taskTypePopupImg.setImageResource(imgId);
        if (!titles.isEmpty()) {
            titles.clear();
        }
        titles.addAll(SetViewHelp.getList(type));
        taskTypeAdapter.notifyDataSetChanged();
        popupWindow.showAsDropDown(taskTypeLayout);
    }

    /**
     * 初始化筛选框的popupWindow
     */
    private void initPopup() {
        View inflate = getLayoutInflater().inflate(R.layout.task_type_popup_layou, null);
        initPopupView(inflate);
        popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setOutsideTouchable(true);

    }

    /**
     * 初始化筛选框的popupWindow中的控件
     *
     * @param rootView
     */
    private void initPopupView(View rootView) {
        this.taskTypePopupImg = (ImageView) rootView.findViewById(R.id.task_type_popup_img);
        this.taskTypePopupRecycler = (RecyclerView) rootView.findViewById(R.id.task_type_popup_recycler);

        taskTypePopupRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        taskTypePopupRecycler.setAdapter(taskTypeAdapter);
    }

    /**
     * 初始化拍照or相机的popupWindow
     */
    private void popupInit() {
        View inflate = getLayoutInflater().inflate(R.layout.photo_popup_layout, null);
        initPopupViews(inflate);

        xjpzPopup = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        xjpzPopup.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        xjpzPopup.setOutsideTouchable(true);
    }

    /**
     * 初始化拍照or相机的popupWindow中的控件
     *
     * @param inflate
     */
    private void initPopupViews(View inflate) {
        paiBtn = inflate.findViewById(R.id.photo_popup_pai_btn);
        xiangCeBtn = inflate.findViewById(R.id.photo_popup_xiang_ce_btn);
        returnBtn = inflate.findViewById(R.id.photo_popup_return_btn);

        paiBtn.setOnClickListener(this);
        xiangCeBtn.setOnClickListener(this);
        returnBtn.setOnClickListener(this);
    }

    /**
     * 展示拍照or相机的popupWindow
     */
    public void showPopup() {
        xjpzPopup.showAtLocation(getLayoutInflater().inflate(R.layout.fragment_task, null), Gravity.BOTTOM, 0, 0);
    }

    /**
     * 取消拍照or相机的popupWindow
     */
    public void dismissPopup() {
        xjpzPopup.dismiss();
    }

    /**
     * 初始化雇主类型的popupWindow
     */
    public void initGzType() {
        View inflate = getLayoutInflater().inflate(R.layout.gz_type_popup_layout, null);
        initGzTypeView(inflate);
        gzPopupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        gzPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        gzPopupWindow.setOutsideTouchable(true);
        gzPopupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.fragment_task, null), Gravity.BOTTOM, 0, 0);
    }

    /**
     * 初始化雇主类型的popupWindow中的控件
     *
     * @param rootView
     */
    private void initGzTypeView(View rootView) {
        this.gz_popup_layout_xiang_mu_fang_btn = (TextView) rootView.findViewById(R.id.gz_popup_layout_xiang_mu_fang_btn);
        this.gz_popup_layout_jiao_yi_suo_btn = (TextView) rootView.findViewById(R.id.gz_popup_layout_jiao_yi_suo_btn);
        this.gz_popup_layout_return_btn = (TextView) rootView.findViewById(R.id.gz_popup_layout_return_btn);

        gz_popup_layout_xiang_mu_fang_btn.setOnClickListener(this);
        gz_popup_layout_jiao_yi_suo_btn.setOnClickListener(this);
        gz_popup_layout_return_btn.setOnClickListener(this);
    }
}

