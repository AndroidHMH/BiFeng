package com.coinwind.bifeng.ui.home.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseFragment;
import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.TimeUtils;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.home.adapter.HomeAdapter;
import com.coinwind.bifeng.ui.home.bean.HomeBannerBean;
import com.coinwind.bifeng.ui.home.config.QianDaoHelp;
import com.coinwind.bifeng.ui.home.contract.HomeContract;
import com.coinwind.bifeng.ui.home.presenter.HomePresenter;
import com.coinwind.bifeng.ui.login.activity.LoginActivity;
import com.coinwind.bifeng.ui.share.activity.InvitationActivity;
import com.coinwind.bifeng.ui.task.activity.AnswerTaskActivity;
import com.makeramen.roundedimageview.RoundedImageView;
import com.recker.flybanner.FlyBanner;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import co.lujun.androidtagview.ColorFactory;
import co.lujun.androidtagview.TagContainerLayout;

/**
 * 首页的fragment
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View, AbsListView.OnScrollListener, View.OnClickListener, AdapterView.OnItemClickListener {
    @BindView(R.id.main_list)
    ListView mainList;
    public FlyBanner homeBanner;
    public LinearLayout homeZhaufnaBtn;
    public LinearLayout homeZhuceBtn;
    public LinearLayout homePinglunBtn;
    public LinearLayout homeZhangfenBtn;
    public LinearLayout homeJiaoyanBtn;
    public LinearLayout homeDatiBtn;
    public LinearLayout homePaizhaoBtn;
    public TextView homeGuangBoContentTv;
    public RoundedImageView homeQiangUserIconImg;
    public TextView homeQiangUserNameTv;
    public TextView homeQiangCountTv;
    public ImageView homeQiangIconImg;
    public TextView homeQiangTitleTv;
    public TextView homeQiangTimeTv;
    public TagContainerLayout homeQiangXfcLayout;
    public LinearLayout homeQiangRenWuBtn;
    @BindView(R.id.title_layout_return_btn)
    LinearLayout titleLayoutReturnBtn;
    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;


    private List<TaskBean> mList;
    private List<String> bannerUrls;
    private HomeAdapter homeAdapter;
    private boolean loadFinishFlag;
    private int page = 1;
    private TaskBean qiangRenWu;
    private ImageView homeQianDaoBtn;
    private ImageView homeFenXiangBtn;
    public ImageView qianDao1;
    public ImageView qianDao2;
    public ImageView qianDao3;
    public ImageView qianDao4;
    public ImageView qianDao5;
    public ImageView qianDao6;
    public ImageView qianDao7;
    public Button qianDaoBtn;
    private PopupWindow qianDaoPopupWindow;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {
        View headerView = getLayoutInflater().inflate(R.layout.main_head_layout, null);
        initHeadViews(headerView);
        mainList.addHeaderView(headerView);
        View footView = getLayoutInflater().inflate(R.layout.main_foot_layout, null);
        mainList.addFooterView(footView);

        loadFinishFlag = true;
        mList = new ArrayList<>();
        bannerUrls = new ArrayList<>();
        homeAdapter = new HomeAdapter(mList, getContext());
        mainList.setAdapter(homeAdapter);
        titleLayoutReturnBtn.setVisibility(View.GONE);

        mainList.setOnScrollListener(this);
        mainList.setOnItemClickListener(this);
    }

    public void initHeadViews(View rootView) {
        this.homeBanner = (FlyBanner) rootView.findViewById(R.id.home_banner);
        this.homeZhaufnaBtn = (LinearLayout) rootView.findViewById(R.id.home_zhaufna_btn);
        this.homeZhuceBtn = (LinearLayout) rootView.findViewById(R.id.home_zhuce_btn);
        this.homePinglunBtn = (LinearLayout) rootView.findViewById(R.id.home_pinglun_btn);
        this.homeZhangfenBtn = (LinearLayout) rootView.findViewById(R.id.home_zhangfen_btn);
        this.homeJiaoyanBtn = (LinearLayout) rootView.findViewById(R.id.home_jiaoyan_btn);
        this.homeDatiBtn = (LinearLayout) rootView.findViewById(R.id.home_dati_btn);
        this.homePaizhaoBtn = (LinearLayout) rootView.findViewById(R.id.home_paizhao_btn);
        this.homeGuangBoContentTv = (TextView) rootView.findViewById(R.id.home_guang_bo_content_tv);
        this.homeQiangUserIconImg = (RoundedImageView) rootView.findViewById(R.id.home_qiang_user_icon_img);
        this.homeQiangUserNameTv = (TextView) rootView.findViewById(R.id.home_qiang_user_name_tv);
        this.homeQiangCountTv = (TextView) rootView.findViewById(R.id.home_qiang_count_tv);
        this.homeQiangIconImg = (ImageView) rootView.findViewById(R.id.home_qiang_icon_img);
        this.homeQiangTitleTv = (TextView) rootView.findViewById(R.id.home_qiang_title_tv);
        this.homeQiangTimeTv = (TextView) rootView.findViewById(R.id.home_qiang_time_tv);
        this.homeQiangXfcLayout = (TagContainerLayout) rootView.findViewById(R.id.home_qiang_xfc_layout);
        this.homeQiangRenWuBtn = (LinearLayout) rootView.findViewById(R.id.home_qiang_ren_wu_btn);
        this.homeQianDaoBtn = rootView.findViewById(R.id.home_qian_dao_btn);
        this.homeFenXiangBtn = rootView.findViewById(R.id.home_fen_xiang_btn);

        homeQiangRenWuBtn.setOnClickListener(this);
        homeZhaufnaBtn.setOnClickListener(this);
        homeZhuceBtn.setOnClickListener(this);
        homePinglunBtn.setOnClickListener(this);
        homeZhangfenBtn.setOnClickListener(this);
        homeJiaoyanBtn.setOnClickListener(this);
        homeDatiBtn.setOnClickListener(this);
        homePaizhaoBtn.setOnClickListener(this);
        homeQianDaoBtn.setOnClickListener(this);
        homeFenXiangBtn.setOnClickListener(this);


        homeGuangBoContentTv.setSelected(true);

        homeQiangXfcLayout.setTheme(ColorFactory.NONE);
        homeQiangXfcLayout.setTagBackgroundColor(getContext().getResources().getColor(R.color.white_fff));
        homeQiangXfcLayout.setTagTextColor(getContext().getResources().getColor(R.color.red_f42));
        homeQiangXfcLayout.setTagBorderColor(getContext().getResources().getColor(R.color.red_f42));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_qiang_ren_wu_btn:
                Intent answerIntent = new Intent(getContext(), AnswerTaskActivity.class);
                answerIntent.putExtra("bean", qiangRenWu);
                startActivity(answerIntent);
                break;
            case R.id.home_zhaufna_btn:
                break;
            case R.id.home_zhuce_btn:
                break;
            case R.id.home_pinglun_btn:
                break;
            case R.id.home_zhangfen_btn:
                break;
            case R.id.home_jiaoyan_btn:
                break;
            case R.id.home_dati_btn:
                break;
            case R.id.home_paizhao_btn:
                break;
            case R.id.home_qian_dao_btn:
                if (SpHelp.getLoginStatus()) {
                    showQianDaoPopup();
                } else {
                    LoginActivity.openLoginActivity(getContext());
                }
                break;
            case R.id.home_fen_xiang_btn:
                if (SpHelp.getLoginStatus()) {
                    presenter.isLogin();
                } else {
                    LoginActivity.openLoginActivity(getContext());
                }
                break;
            case R.id.qian_dao_btn:
                presenter.sendQianDao();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int i = position - 1;
        if ((position + 1) != mainList.getCount()) {
            Intent answerIntent = new Intent(getContext(), AnswerTaskActivity.class);
            TaskBean taskBean = mList.get(i);
            answerIntent.putExtra("bean", taskBean);
            startActivity(answerIntent);
        } else {
            ToastHelp.showShort(getContext(), "已经是最后一条了");
        }
    }

    @Override
    protected void loadDate() {
        presenter.loadBanner();
        presenter.loadQiang();
        presenter.loadTuiJian(page);
        presenter.loadGuangBo();
    }

    @Override
    protected void hideErrorView() {
        mainList.setVisibility(View.GONE);
    }

    @Override
    protected void showSuccessView() {
        mainList.setVisibility(View.VISIBLE);
    }

    @Override
    public void showBanner(List<HomeBannerBean.DataBean> bannerBeans) {
        for (HomeBannerBean.DataBean bannerBean : bannerBeans) {
            bannerUrls.add(bannerBean.getUrl());
        }
        homeBanner.setImagesUrl(bannerUrls);
    }

    @Override
    public void showTuiJian(List<TaskBean> tuiJians) {
        loadFinishFlag = true;
        mList.addAll(tuiJians);
        if (tuiJians.size() == 0) {
            loadFinishFlag = false;
        }
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void showQiang(TaskBean taskBean) {
        qiangRenWu = taskBean;
        Glide.with(getContext()).load(taskBean.getImg()).into(homeQiangIconImg);
        Glide.with(getContext()).load(taskBean.getLogo()).into(homeQiangUserIconImg);
        homeQiangUserNameTv.setText(taskBean.getNick_name());
        homeQiangCountTv.setText(taskBean.getScore() + "");
        homeQiangTitleTv.setText(taskBean.getTitle());
        String[] labels = taskBean.getLabel().split(",");
        homeQiangXfcLayout.setTags(labels);
        String end_time = taskBean.getEnd_time();

        long endTime = TimeUtils.string2long(end_time);
        long nowTime = System.currentTimeMillis();
        long l = endTime - nowTime;

        /** 倒计时60秒，一次1秒 */
        CountDownTimer timer = new CountDownTimer(l, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String s = TimeUtils.long2hms(millisUntilFinished);
//                LogHelp.e("time", s);
                homeQiangTimeTv.setText(s);
            }

            @Override
            public void onFinish() {
                homeQiangTimeTv.setText("倒计时完毕了");
            }
        }.start();
    }

    @Override
    public void showGuangBo(String content) {
        LogHelp.e("HomeFragment", content);
        homeGuangBoContentTv.setText(content);
    }

    @Override
    public void showBannerError(List<Integer> defaultImgs) {
        homeBanner.setImages(defaultImgs);
    }


    @Override
    public void showGuangBoError(String errorContent) {
        homeGuangBoContentTv.setText(errorContent);
    }

    @Override
    public void qianDaoSuccess(String msg, int days) {
        ToastHelp.showShort(getContext(), msg);
        SpHelp.putUserInformation(SpHelp.LAST_CHECK_TYPE, days + "");
        QianDaoHelp.setDay(getImgs(), days);
    }

    @Override
    public void qianDaoError(String msg) {
        ToastHelp.showShort(getContext(), msg);
    }

    @Override
    public void loginOut() {
        SpHelp.loginOut();
        LoginActivity.openLoginActivity(getContext());
    }

    @Override
    public void login() {
        startActivity(new Intent(getContext(), InvitationActivity.class));
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        //获取屏幕最后Item的ID
        int lastVisibleItem = mainList.getLastVisiblePosition();
        if (lastVisibleItem + 1 == i2) {
            if (loadFinishFlag) {
                //标志位，防止多次加载
                loadFinishFlag = false;
                page++;
                presenter.loadTuiJian(page);
            }
        }
    }

    /**
     * 展示签到的popupWindow
     */
    private void showQianDaoPopup() {
        View inflate = getLayoutInflater().inflate(R.layout.qian_dao_popup_layout, null);
        initQianDao(inflate);
        qianDaoPopupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        qianDaoPopupWindow.setOutsideTouchable(true);
        qianDaoPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));

        String lastClickType = SpHelp.getUserInformation(SpHelp.LAST_CHECK_TYPE);
        int days = 0;
        if (!"".equals(lastClickType)) {
            days = Integer.parseInt(SpHelp.getUserInformation(SpHelp.LAST_CHECK_TYPE));
        }
        QianDaoHelp.setDay(getImgs(), days);
        qianDaoPopupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.fragment_task, null), Gravity.CENTER, 0, 0);
    }

    private void initQianDao(View rootView) {
        this.qianDao1 = (ImageView) rootView.findViewById(R.id.qian_dao1);
        this.qianDao2 = (ImageView) rootView.findViewById(R.id.qian_dao2);
        this.qianDao3 = (ImageView) rootView.findViewById(R.id.qian_dao3);
        this.qianDao4 = (ImageView) rootView.findViewById(R.id.qian_dao4);
        this.qianDao5 = (ImageView) rootView.findViewById(R.id.qian_dao5);
        this.qianDao6 = (ImageView) rootView.findViewById(R.id.qian_dao6);
        this.qianDao7 = (ImageView) rootView.findViewById(R.id.qian_dao7);
        this.qianDaoBtn = (Button) rootView.findViewById(R.id.qian_dao_btn);

        qianDaoBtn.setOnClickListener(this);
    }

    private List<ImageView> getImgs() {
        List<ImageView> imageViews = new ArrayList<>();
        imageViews.add(qianDao1);
        imageViews.add(qianDao2);
        imageViews.add(qianDao3);
        imageViews.add(qianDao4);
        imageViews.add(qianDao5);
        imageViews.add(qianDao6);
        imageViews.add(qianDao7);
        return imageViews;
    }
}
