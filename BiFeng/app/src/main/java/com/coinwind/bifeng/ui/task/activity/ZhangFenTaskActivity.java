package com.coinwind.bifeng.ui.task.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.submittask.activity.SubmitRegisitActivity;
import com.coinwind.bifeng.ui.task.config.SetViewHelp;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 操作步骤为4步的页面
 */
public class ZhangFenTaskActivity extends BaseActivity {

    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.title_layout_return_btn)
    LinearLayout titleLayoutReturnBtn;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.bu_zou_first_tv)
    TextView buZouFirstTv;
    @BindView(R.id.bu_zou_wei_xin_tv)
    TextView buZouWeiXinTv;
    @BindView(R.id.bu_zou_cope_tv)
    LinearLayout buZouCopeTv;
    @BindView(R.id.bu_zou_second_tv)
    TextView buZouSecondTv;
    @BindView(R.id.bu_zou_third_tv)
    TextView buZouThirdTv;
    @BindView(R.id.bu_zhou_img)
    ImageView buZhouImg;
    @BindView(R.id.bu_zou_forth_tv)
    TextView buZouForthTv;
    @BindView(R.id.bu_zou_next_btn)
    LinearLayout buZouNextBtn;
    private TaskBean bean;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_zhang_fen_task;
    }

    @Override
    protected void init() {
        bean = (TaskBean) getIntent().getSerializableExtra("bean");
        setViews();
    }

    private void setViews() {
        titleTitleTv.setText(SetViewHelp.getType(bean.getType()));
        SetViewHelp.setContent(bean.getType(), buZouFirstTv, buZouSecondTv, buZouThirdTv, buZouForthTv, buZhouImg);

    }

    @Override
    protected void loadDate() {

    }

    @OnClick({R.id.title_layout_return_btn, R.id.bu_zou_next_btn, R.id.bu_zou_cope_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_layout_return_btn:
                finish();
                break;
            case R.id.bu_zou_cope_tv:
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(buZouWeiXinTv.getText());
                ToastHelp.showShort(this, buZouWeiXinTv.getText() + "已经复制到剪切板");
                break;
            case R.id.bu_zou_next_btn:
                Intent intent = new Intent(this, SubmitRegisitActivity.class);
                intent.putExtra("bean", bean);
                startActivity(intent);
                break;
        }
    }
}
