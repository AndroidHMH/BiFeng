package com.coinwind.bifeng.ui.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseFragment;
import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.config.TimeUtils;
import com.coinwind.bifeng.ui.home.adapter.HomeAdapter;
import com.coinwind.bifeng.ui.home.bean.HomeBannerBean;
import com.coinwind.bifeng.ui.home.contract.HomeContract;
import com.coinwind.bifeng.ui.home.presenter.HomePresenter;
import com.coinwind.bifeng.ui.task.activity.AnswerTaskActivity;
import com.makeramen.roundedimageview.RoundedImageView;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.lujun.androidtagview.ColorFactory;
import co.lujun.androidtagview.TagContainerLayout;

/**
 * 首页
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


    private List<TaskBean> mList;
    private List<String> bannerUrls;
    private HomeAdapter homeAdapter;
    private boolean loadFinishFlag;
    private int page = 1;
    private TaskBean qiangRenWu;

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
        homeQiangRenWuBtn.setOnClickListener(this);
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

        homeGuangBoContentTv.setSelected(true);

        homeQiangXfcLayout.setTheme(ColorFactory.NONE);
        homeQiangXfcLayout.setTagBackgroundColor(getContext().getResources().getColor(R.color.white));
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
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent answerIntent = new Intent(getContext(), AnswerTaskActivity.class);
        TaskBean taskBean = mList.get(position);
        answerIntent.putExtra("bean", taskBean);
        startActivity(answerIntent);
    }

    @Override
    protected void loadDate() {
        presenter.loadBanner();
        presenter.loadQiang();
        presenter.loadTuiJian(page);
        presenter.loadGuangBo();
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

        long endTime = TimeUtils.stringToLong(end_time);
        long nowTime = System.currentTimeMillis();
        long l = endTime - nowTime;
        /** 倒计时60秒，一次1秒 */
        CountDownTimer timer = new CountDownTimer(l, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                homeQiangTimeTv.setText(TimeUtils.longToString(millisUntilFinished));
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

}
