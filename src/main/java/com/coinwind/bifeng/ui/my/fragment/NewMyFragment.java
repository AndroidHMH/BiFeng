package com.coinwind.bifeng.ui.my.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coinwind.bifeng.R;
import com.coinwind.bifeng.app.BFApplication;
import com.coinwind.bifeng.base.BaseFragment;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.bindphonenumber.activity.BindPhoneNumberActivity;
import com.coinwind.bifeng.ui.home.fragment.NewHomeFragment;
import com.coinwind.bifeng.ui.homepage.activity.MainActivity;
import com.coinwind.bifeng.ui.login.activity.LoginActivity;
import com.coinwind.bifeng.ui.my.activity.AlertsActivity;
import com.coinwind.bifeng.ui.my.activity.MyTaskActivity;
import com.coinwind.bifeng.ui.my.activity.WalletActivity;
import com.coinwind.bifeng.ui.my.bean.NewMyBean;
import com.coinwind.bifeng.ui.my.contract.NewMyContract;
import com.coinwind.bifeng.ui.my.presenter.NewMyPresenter;
import com.coinwind.bifeng.ui.setting.activity.ContactUsActivity;
import com.coinwind.bifeng.ui.setting.activity.GuanYuActivity;
import com.coinwind.bifeng.ui.setting.activity.SettingActivity;
import com.coinwind.bifeng.ui.share.activity.InvitationActivity;
import com.coinwind.bifeng.ui.task.activity.DoNewTaskActivity;
import com.coinwind.bifeng.ui.task.activity.NewTaskActivity;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.coinwind.bifeng.config.Codes.OVERDUE_CODE;
import static com.coinwind.bifeng.config.Codes.OVERDUE_RESULT_CODE;
import static com.coinwind.bifeng.ui.task.activity.DoNewTaskActivity.PHONE_SUCCESS_CODE;
import static com.coinwind.bifeng.ui.task.activity.NewTaskActivity.PHONE_REQUEST_CODE;

/**
 * 新的我的页面
 */
public class NewMyFragment extends BaseFragment<NewMyPresenter> implements NewMyContract.View {

    @BindView(R.id.new_my_tong_zhi_btn)
    ImageView newMyTongZhiBtn;
    @BindView(R.id.new_my_setting_btn)
    ImageView newMySettingBtn;
    @BindView(R.id.new_my_head_img)
    RoundedImageView newMyHeadImg;
    @BindView(R.id.new_my_user_name_tv)
    TextView newMyUserNameTv;
    @BindView(R.id.new_my_wallet_address_tv)
    TextView newMyWalletAddressTv;
    @BindView(R.id.new_my_wallet_er_wei_ba_btn)
    ImageView newMyWalletErWeiBaBtn;
    @BindView(R.id.new_my_yue_deng_yu_tv)
    TextView newMyYueDengYuTv;
    @BindView(R.id.new_my_hang_qing_img)
    ImageView newMyHangQingImg;
    @BindView(R.id.new_my_zi_chan_btn)
    LinearLayout newMyZiChanBtn;
    @BindView(R.id.new_my_my_all_task_btn)
    LinearLayout newMyMyAllTaskBtn;
    @BindView(R.id.new_my_jie_shou_task_tv)
    TextView newMyJieShouTaskTv;
    @BindView(R.id.new_my_jie_shou_task_btn)
    LinearLayout newMyJieShouTaskBtn;
    @BindView(R.id.new_my_jin_xing_task_tv)
    TextView newMyJinXingTaskTv;
    @BindView(R.id.new_my_jin_xing_task_btn)
    LinearLayout newMyJinXingTaskBtn;
    @BindView(R.id.new_my_shen_he_task_tv)
    TextView newMyShenHeTaskTv;
    @BindView(R.id.new_my_shen_he_task_btn)
    LinearLayout newMyShenHeTaskBtn;
    @BindView(R.id.new_my_jie_shu_task_tv)
    TextView newMyJieShuTaskTv;
    @BindView(R.id.new_my_jie_shu_task_btn)
    LinearLayout newMyJieShuTaskBtn;
    @BindView(R.id.new_my_guo_qi_task_tv)
    TextView newMyGuoQiTaskTv;
    @BindView(R.id.new_my_guo_qi_task_btn)
    LinearLayout newMyGuoQiTaskBtn;
    @BindView(R.id.new_my_share_btn)
    LinearLayout newMyShareBtn;
    @BindView(R.id.new_my_help_btn)
    LinearLayout newMyHelpBtn;
    @BindView(R.id.new_my_lian_xi_btn)
    LinearLayout newMyLianXiBtn;
    @BindView(R.id.new_my_guan_yu_btn)
    LinearLayout newMyGuanYuBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new_my;
    }

    @Override
    protected void init() {
    }

    @Override
    protected void loadDate() {
        presenter.loadMyInfo();
    }

    @Override
    public void onResume() {
        super.onResume();
        netWorkError();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            netWorkError();
        }
    }


    @OnClick({R.id.new_my_tong_zhi_btn, R.id.new_my_setting_btn, R.id.new_my_wallet_er_wei_ba_btn, R.id.new_my_zi_chan_btn,
            R.id.new_my_my_all_task_btn, R.id.new_my_jie_shou_task_btn, R.id.new_my_jin_xing_task_btn, R.id.new_my_shen_he_task_btn,
            R.id.new_my_jie_shu_task_btn, R.id.new_my_guo_qi_task_btn, R.id.new_my_share_btn, R.id.new_my_help_btn, R.id.new_my_lian_xi_btn,
            R.id.new_my_guan_yu_btn, R.id.new_my_user_info_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.new_my_tong_zhi_btn:
                //跳转信息通知
                AlertsActivity.openActivity(getContext());
                break;
            case R.id.new_my_setting_btn:
                //跳转设置页面
                startActivity(new Intent(getContext(), SettingActivity.class));
                break;
            case R.id.new_my_wallet_er_wei_ba_btn:
                //显示钱包二维码
                break;
            case R.id.new_my_zi_chan_btn:
                //跳转钱包
                startActivity(new Intent(getContext(), WalletActivity.class));
                break;
            case R.id.new_my_my_all_task_btn:
                if (SpHelp.getIsVisit() == 0) {
                    //falg  1已接受，2进行中，3审核中，4已结束，5已过期
                    MyTaskActivity.openMyTaskActivity(getContext(), -1);
                } else {
                    Intent bindIntent = new Intent(getActivity(), BindPhoneNumberActivity.class);
                    startActivityForResult(bindIntent, PHONE_REQUEST_CODE);
                    ToastHelp.showShort(getContext(), "您当前身份为游客，无法邀请好友");
                }

                break;
            case R.id.new_my_jie_shou_task_btn:
                if (SpHelp.getIsVisit() == 0) {
                    MyTaskActivity.openMyTaskActivity(getContext(), 0);
                } else {
                    Intent bindIntent = new Intent(getActivity(), BindPhoneNumberActivity.class);
                    startActivityForResult(bindIntent, PHONE_REQUEST_CODE);
                    ToastHelp.showShort(getContext(), "您当前身份为游客，无法邀请好友");
                }
                break;
            case R.id.new_my_jin_xing_task_btn:
                MyTaskActivity.openMyTaskActivity(getContext(), 0);
                if (SpHelp.getIsVisit() == 0) {
                    MyTaskActivity.openMyTaskActivity(getContext(), 1);
                } else {
                    Intent bindIntent = new Intent(getActivity(), BindPhoneNumberActivity.class);
                    startActivityForResult(bindIntent, PHONE_REQUEST_CODE);
                    ToastHelp.showShort(getContext(), "您当前身份为游客，无法邀请好友");
                }
                break;
            case R.id.new_my_shen_he_task_btn:
                if (SpHelp.getIsVisit() == 0) {
                    MyTaskActivity.openMyTaskActivity(getContext(), 2);
                } else {
                    Intent bindIntent = new Intent(getActivity(), BindPhoneNumberActivity.class);
                    startActivityForResult(bindIntent, PHONE_REQUEST_CODE);
                    ToastHelp.showShort(getContext(), "您当前身份为游客，无法邀请好友");
                }
                break;
            case R.id.new_my_jie_shu_task_btn:
                if (SpHelp.getIsVisit() == 0) {
                    MyTaskActivity.openMyTaskActivity(getContext(), 3);
                } else {
                    Intent bindIntent = new Intent(getActivity(), BindPhoneNumberActivity.class);
                    startActivityForResult(bindIntent, PHONE_REQUEST_CODE);
                    ToastHelp.showShort(getContext(), "您当前身份为游客，无法邀请好友");
                }
                break;
            case R.id.new_my_guo_qi_task_btn:
                if (SpHelp.getIsVisit() == 0) {
                    MyTaskActivity.openMyTaskActivity(getContext(), 4);
                } else {
                    Intent bindIntent = new Intent(getActivity(), BindPhoneNumberActivity.class);
                    startActivityForResult(bindIntent, PHONE_REQUEST_CODE);
                    ToastHelp.showShort(getContext(), "您当前身份为游客，无法邀请好友");
                }
                break;
            case R.id.new_my_share_btn:
                if (SpHelp.getIsVisit() == 0) {
                    startActivity(new Intent(getContext(), InvitationActivity.class));
                } else {
                    Intent bindIntent = new Intent(getActivity(), BindPhoneNumberActivity.class);
                    startActivityForResult(bindIntent, PHONE_REQUEST_CODE);
                    ToastHelp.showShort(getContext(), "您当前身份为游客，无法邀请好友");
                }
                break;
            case R.id.new_my_help_btn:
                //帮助中心
                break;
            case R.id.new_my_user_info_btn:
                if (SpHelp.getIsVisit() == 1) {
                    LoginActivity.openLoginActivity(getContext());
                }
                break;
            case R.id.new_my_lian_xi_btn:
                //联系我们
                ContactUsActivity.openActivity(getContext());
                break;
            case R.id.new_my_guan_yu_btn:
                GuanYuActivity.openActivity(getContext(), "https://m.coinwind.com/share/about.html");
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHONE_REQUEST_CODE) {
            if (resultCode == PHONE_SUCCESS_CODE) {//绑定手机号成功
                ToastHelp.showShort(getContext(), "绑定手机号成功");
                MainActivity activity = (MainActivity) getActivity();
                activity.reLoadFragView(R.id.main_layout, NewHomeFragment.class);
            }
        }
    }
    @Override
    public void showMyInfo(NewMyBean.DataBean dataBean) {
        SpHelp.putUserInformation(SpHelp.NICK_NAME, dataBean.getNick_name());
        SpHelp.putIsVisit(dataBean.getIs_visit());
        //设置头像
        String head_img = dataBean.getHead_img();
        if (!"".equals(head_img) && head_img != null) {
            Glide.with(getContext()).load(dataBean.getHead_img()).into(newMyHeadImg);
        }
        //设置昵称
        newMyUserNameTv.setText(dataBean.getNick_name());
        //设置钱包地址
        String purse_addr = dataBean.getPurse_addr();
        if (purse_addr != null && !"".equals(purse_addr)) {
            newMyWalletAddressTv.setText(getWallAddress(purse_addr));
        }
        //估值多少人民币
        newMyYueDengYuTv.setText(dataBean.getCurrent_cc() + "");
        //已接收
        newMyJieShouTaskTv.setText(dataBean.getReceiveNum() + "");
        //进行中
        newMyJinXingTaskTv.setText(dataBean.getNowNum() + "");
        //待审核
        newMyShenHeTaskTv.setText(dataBean.getCheckNum() + "");
        //已结束
        newMyJieShuTaskTv.setText(dataBean.getEndNum() + "");
        //已过期
        newMyGuoQiTaskTv.setText(dataBean.getExpireNum() + "");
    }

    private String getWallAddress(String wallAddressStr) {
        StringBuilder wallAddress = new StringBuilder();
        wallAddress.append(wallAddressStr.substring(0, 5));
        wallAddress.append("*****");
        wallAddress.append(wallAddressStr.substring((wallAddress.length() - 5), wallAddress.length()));
        return wallAddress.toString();
    }

    @Override
    public void showError(String errorMsg) {
        ToastHelp.showLong(getContext(), errorMsg);
    }

    @Override
    public void loginTimeOut() {

    }
}
