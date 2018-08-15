package com.coinwind.bifeng.ui.task.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.base.NoNetworkBaseActivity;
import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.ui.submittask.activity.SubmitCommentsActivity;
import com.coinwind.bifeng.ui.task.config.SetViewHelp;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * 操作步骤  有链接
 */
public class RegistTaskActivity extends NoNetworkBaseActivity {

    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.title_layout_return_btn)
    LinearLayout titleLayoutReturnBtn;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.bu_zou_lian_jie_first_tv)
    TextView buZouLianJieFirstTv;
    @BindView(R.id.bu_zou_lian_jie_url_tv)
    TextView buZouLianJieUrlTv;
    @BindView(R.id.bu_zou_lian_jie_second_tv)
    TextView buZouLianJieSecondTv;
    @BindView(R.id.bu_zou_lian_jie_img)
    ImageView buZouLianJieImg;
    @BindView(R.id.bu_zou_lian_jie_third_tv)
    TextView buZouLianJieThirdTv;
    @BindView(R.id.bu_zou_lian_jie_next_btn)
    LinearLayout buZouLianJieNextBtn;
    private TaskBean bean;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_regist;
    }

    @Override
    protected void init() {
        bean = (TaskBean) getIntent().getSerializableExtra("bean");
        setViews();
    }

    private void setViews() {
        titleTitleTv.setText(SetViewHelp.getType(bean.getType()));
        SetViewHelp.setContent(bean.getType(), buZouLianJieFirstTv, buZouLianJieSecondTv, buZouLianJieThirdTv, buZouLianJieImg);
        SetViewHelp.setUrl(bean.getUrl(), buZouLianJieUrlTv);
    }

    @OnClick({R.id.title_layout_return_btn, R.id.bu_zou_lian_jie_next_btn, R.id.bu_zou_lian_jie_url_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_layout_return_btn:
                finish();
                break;
            case R.id.bu_zou_lian_jie_url_tv:
                Intent intent = new Intent();
                intent.setData(Uri.parse(buZouLianJieUrlTv.getText().toString()));//Url 就是你要打开的网址
                intent.setAction(Intent.ACTION_VIEW);
                this.startActivity(intent); //启动浏览器
                break;
            case R.id.bu_zou_lian_jie_next_btn:
                Intent submitIntent = new Intent(this, SubmitCommentsActivity.class);
                submitIntent.putExtra("bean",bean);
                submitIntent.putExtra("type","phone");
                startActivity(submitIntent);
                break;
        }
    }
}
