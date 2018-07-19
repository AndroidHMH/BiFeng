package com.coinwind.bifeng.ui.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseFragment;
import com.coinwind.bifeng.ui.answertask.activity.AnswerTaskActivity;
import com.coinwind.bifeng.ui.home.adapter.HomeAdapter;
import com.coinwind.bifeng.ui.home.bean.HomeBannerBean;
import com.coinwind.bifeng.ui.home.bean.ListBean;
import com.coinwind.bifeng.ui.home.contract.HomeContract;
import com.coinwind.bifeng.ui.home.presenter.HomePresenter;
import com.coinwind.bifeng.view.XCFlowLayout;
import com.makeramen.roundedimageview.RoundedImageView;
import com.recker.flybanner.FlyBanner;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {


    @BindView(R.id.home_banner)
    FlyBanner homeBanner;
    @BindView(R.id.home_zhaufna_btn)
    LinearLayout homeZhaufnaBtn;
    @BindView(R.id.home_zhuce_btn)
    LinearLayout homeZhuceBtn;
    @BindView(R.id.home_pinglun_btn)
    LinearLayout homePinglunBtn;
    @BindView(R.id.home_zhangfen_btn)
    LinearLayout homeZhangfenBtn;
    @BindView(R.id.home_jiaoyan_btn)
    LinearLayout homeJiaoyanBtn;
    @BindView(R.id.home_dati_btn)
    LinearLayout homeDatiBtn;
    @BindView(R.id.home_paizhao_btn)
    LinearLayout homePaizhaoBtn;
    @BindView(R.id.home_guang_bo_content_tv)
    TextView homeGuangBoContentTv;
    @BindView(R.id.home_qiang_user_icon_img)
    RoundedImageView homeQiangUserIconImg;
    @BindView(R.id.home_qiang_user_name_tv)
    TextView homeQiangUserNameTv;
    @BindView(R.id.home_qiang_count_tv)
    TextView homeQiangCountTv;
    @BindView(R.id.home_qiang_icon_img)
    ImageView homeQiangIconImg;
    @BindView(R.id.home_qiang_title_tv)
    TextView homeQiangTitleTv;
    @BindView(R.id.home_xcf_lauout)
    XCFlowLayout homeXcfLauout;
    @BindView(R.id.home_tui_jian_list)
    RecyclerView homeTuiJianList;
    //    @BindView(R.id.home_refresh_layout)
//    SmartRefreshLayout homeRefreshLayout;
    private List<String> mList;
    private HomeAdapter homeAdapter;
    private int page = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {
        mList = new ArrayList<>();
        homeAdapter = new HomeAdapter(mList);
        homeTuiJianList.setLayoutManager(new LinearLayoutManager(getContext()));
        homeTuiJianList.setAdapter(homeAdapter);

        homeTuiJianList.setHasFixedSize(true);
        homeTuiJianList.setNestedScrollingEnabled(false);
    }

    @Override
    protected void loadDate() {
        presenter.loadBanner();
        presenter.loadList(page);
    }

    @OnClick({R.id.home_zhaufna_btn, R.id.home_qiang_ren_wu_btn, R.id.home_zhuce_btn, R.id.home_pinglun_btn, R.id.home_zhangfen_btn, R.id.home_jiaoyan_btn, R.id.home_dati_btn, R.id.home_paizhao_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
            case R.id.home_qiang_ren_wu_btn:
                Intent intent = new Intent(getContext(), AnswerTaskActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void showBanner(List<HomeBannerBean> bannerBeans) {
        List<String> urls = new ArrayList<>();
        for (HomeBannerBean bannerBean : bannerBeans) {
            urls.add(bannerBean.getUrl());
        }
        homeBanner.setImagesUrl(urls);
    }

    @Override
    public void showList(List<ListBean> listBeans) {
        for (ListBean listBean : listBeans) {
            mList.add(listBean.getImg());
        }
        homeAdapter.notifyDataSetChanged();
    }


    //添加云标签
//    String[] name = new String[]{"EOS", "柚子", "以太坊"};
//    ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
//            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//    lp.leftMargin = 15;
//    lp.rightMargin = 15;
//    lp.topMargin = 15;
//    lp.bottomMargin = 15;
//        for (String n : name) {
//        TextView view = new TextView(getContext());
//        view.setText(n);
//        view.setTextColor(getContext().getResources().getColor(R.color.red_f42));
//        view.setBackgroundDrawable(getResources().getDrawable(R.drawable.label_tv_shape));
//        homeXcfLauout.addView(view, lp);
//    }
}
