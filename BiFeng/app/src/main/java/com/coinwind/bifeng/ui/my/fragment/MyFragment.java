package com.coinwind.bifeng.ui.my.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseFragment;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.login.activity.LoginActivity;
import com.coinwind.bifeng.ui.my.activity.PerfectInformationActivity;
import com.coinwind.bifeng.ui.my.contract.MyContract;
import com.coinwind.bifeng.ui.my.presenter.MyPresenter;
import com.coinwind.bifeng.ui.setting.activity.SettingActivity;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 个人页面
 */
public class MyFragment extends BaseFragment<MyPresenter> implements MyContract.View {

    @BindView(R.id.my_guzhu_btn)
    LinearLayout myGuzhuBtn;
    @BindView(R.id.my_fu_wu_btn)
    LinearLayout myFuWuBtn;
    @BindView(R.id.my_shen_fen_layout)
    LinearLayout myShenFenLayout;
    @BindView(R.id.my_user_type_tv)
    TextView myUserTypeTv;
    @BindView(R.id.my_change_user_type_btn)
    LinearLayout myChangeUserTypeBtn;
    @BindView(R.id.my_head_img_img)
    RoundedImageView myHeadImgImg;
    @BindView(R.id.my_user_name_tv)
    TextView myUserNameTv;
    @BindView(R.id.my_today_cc_tv)
    TextView myTodayCcTv;
    @BindView(R.id.my_all_cc_tv)
    TextView myAllCcTv;
    @BindView(R.id.my_wallet_btn)
    LinearLayout myWalletBtn;
    @BindView(R.id.my_msg_btn)
    LinearLayout myMsgBtn;
    @BindView(R.id.my_send_renw_btn)
    LinearLayout mySendRenwBtn;
    @BindView(R.id.my_running_renw_btn)
    LinearLayout myRunningRenwBtn;
    @BindView(R.id.my_setting_btn)
    LinearLayout mySettingBtn;
    @BindView(R.id.my_qian_dao_btn)
    LinearLayout myQianDaoBtn;
    @BindView(R.id.my_yao_qing_btn)
    LinearLayout myYaoQingBtn;
    @BindView(R.id.my_wan_shan_info_btn)
    TextView myWanShanInfoBtn;
    @BindView(R.id.my_ge_ren_btn)
    LinearLayout myGeRenBtn;
    @BindView(R.id.my_wan_tv)
    TextView myWanTv;
    @BindView(R.id.my_renw_tv)
    TextView myRenwTv;
    @BindView(R.id.my_wan_cheng_renw_btn)
    TextView myWanChengRenwBtn;
    @BindView(R.id.my_task_btn)
    LinearLayout myTaskBtn;
    @BindView(R.id.my_info_layout)
    ScrollView myInfoLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void init() {
        isShowShenFenLayout();
    }

    @Override
    protected void loadDate() {
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!SpHelp.getLoginStatus()) {
            myShenFenLayout.setVisibility(View.VISIBLE);
            myInfoLayout.setVisibility(View.GONE);
        } else {
            isShowShenFenLayout();
            presenter.loadCC(SpHelp.getUserInformation(SpHelp.ID));
        }
    }

    @OnClick({R.id.my_guzhu_btn, R.id.my_fu_wu_btn, R.id.my_change_user_type_btn, R.id.my_wallet_btn, R.id.my_msg_btn, R.id.my_send_renw_btn,
            R.id.my_running_renw_btn, R.id.my_setting_btn, R.id.my_wan_shan_info_btn, R.id.my_ge_ren_btn,
            R.id.my_wan_cheng_renw_btn, R.id.my_task_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_guzhu_btn:
                if (SpHelp.getLoginStatus()) {
                    presenter.changeType(SpHelp.getUserInformation(SpHelp.ID), SpHelp.TYPE, "2");
                } else {
                    LoginActivity.openLoginActivity(getContext());
                }
                break;
            case R.id.my_fu_wu_btn:
                if (SpHelp.getLoginStatus()) {
                    presenter.changeType(SpHelp.getUserInformation(SpHelp.ID), SpHelp.TYPE, "1");
                } else {
                    LoginActivity.openLoginActivity(getContext());
                }
                break;
            case R.id.my_change_user_type_btn:
                changeType();
                break;
            case R.id.my_wallet_btn:
                //跳转钱包
                break;
            case R.id.my_msg_btn:
                //跳转信息通知
                break;
            case R.id.my_send_renw_btn:
                //跳转完成(发布)任务界面
                break;
            case R.id.my_running_renw_btn:
                //跳转执行任务的界面
                break;
            case R.id.my_setting_btn:
                //跳转设置页面
                startActivity(new Intent(getContext(), SettingActivity.class));
                break;
            case R.id.my_wan_shan_info_btn:
                //跳转完善个人资料界面
                startActivity(new Intent(getContext(), PerfectInformationActivity.class));
                break;
            case R.id.my_ge_ren_btn:
                //跳转完善个人资料界面
                break;
            case R.id.my_wan_cheng_renw_btn:
                //跳转完成任务界面
                break;
            case R.id.my_task_btn:
                //跳转完成任务界面
                break;
        }
    }


    @Override
    public void showSuccess(String msg, int type) {
        ToastHelp.showShort(getContext(), msg);
        if (type == 2) {
            SpHelp.putUserType(SpHelp.EMPLOYERS);
        } else {
            SpHelp.putUserType(SpHelp.SERVICE_PROVIDERS);
        }
        initLayout();
    }

    @Override
    public void showError(String errorMsg) {
        ToastHelp.showShort(getContext(), errorMsg);
    }

    @Override
    public void showCC(String todayCC, String allCC) {
        myTodayCcTv.setText(todayCC);
        myAllCcTv.setText(allCC);
    }

    /**
     * 更换用户类型
     */
    private void changeType() {
        String userType = SpHelp.getUserType();
        if (userType.equals(SpHelp.SERVICE_PROVIDERS)) {
            presenter.changeType(SpHelp.getUserInformation(SpHelp.ID), SpHelp.TYPE, "2");
        } else {
            presenter.changeType(SpHelp.getUserInformation(SpHelp.ID), SpHelp.TYPE, "1");
        }
    }

    public void initLayout() {
        if (SpHelp.SERVICE_PROVIDERS.equals(SpHelp.getUserType())) {
            myShenFenLayout.setVisibility(View.GONE);
            myInfoLayout.setVisibility(View.VISIBLE);
            myUserTypeTv.setText(getContext().getResources().getString(R.string.fuwu));
            myWanTv.setText(getContext().getResources().getString(R.string.my_task));
            myRenwTv.setText(getContext().getResources().getString(R.string.my_running_task));

        } else if (SpHelp.EMPLOYERS.equals(SpHelp.getUserType())) {
            myShenFenLayout.setVisibility(View.GONE);
            myInfoLayout.setVisibility(View.VISIBLE);
            myUserTypeTv.setText(getContext().getResources().getString(R.string.guzhu));
            myWanTv.setText(getContext().getResources().getString(R.string.my_send_task));
            myRenwTv.setText(getContext().getResources().getString(R.string.my_fa_bu_task));
        } else {
            myShenFenLayout.setVisibility(View.VISIBLE);
            myInfoLayout.setVisibility(View.GONE);
        }
    }

    public void isShowShenFenLayout() {
        String userType = SpHelp.getUserInformation(SpHelp.TYPE);
        if ("".equals(userType) || userType == null) {
            myShenFenLayout.setVisibility(View.VISIBLE);
            myInfoLayout.setVisibility(View.GONE);
        } else {
            initLayout();
        }

        String head_img = SpHelp.getUserInformation(SpHelp.HEAD_IMG);
        if (isNullRoEmpty(head_img)) {
            Glide.with(getContext()).load(head_img).into(myHeadImgImg);
        } else {
            //设置默认图片
        }

        String nick_name = SpHelp.getUserInformation(SpHelp.NICK_NAME);
        if (isNullRoEmpty(nick_name)) {
            myUserNameTv.setText(nick_name);
        } else {
            myUserNameTv.setText(SpHelp.getUserInformation(SpHelp.PHONE));
        }

    }

    private boolean isNullRoEmpty(String content) {
        if (null == content) {
            return false;
        }
        if ("".equals(content)) {
            return false;
        }
        return true;
    }
}
