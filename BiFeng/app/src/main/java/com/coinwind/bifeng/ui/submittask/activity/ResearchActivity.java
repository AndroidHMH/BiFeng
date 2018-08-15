package com.coinwind.bifeng.ui.submittask.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.app.BFApplication;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.view.RichTextEditor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置调研任务的页面
 */
public class ResearchActivity extends AppCompatActivity {

    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.title_layout_return_btn)
    LinearLayout titleLayoutReturnBtn;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.title_bar_layout)
    LinearLayout titleBarLayout;
    @BindView(R.id.problem_first_tv)
    TextView problemFirstTv;
    @BindView(R.id.submit_research_problem_first_et)
    EditText submitResearchProblemFirstEt;
    @BindView(R.id.problem_second_tv)
    TextView problemSecondTv;
    @BindView(R.id.submit_research_problem_second_et)
    EditText submitResearchProblemSecondEt;
    @BindView(R.id.problem_third_tv)
    TextView problemThirdTv;
    @BindView(R.id.submit_research_problem_third_et)
    EditText submitResearchProblemThirdEt;
    @BindView(R.id.rich_text)
    RichTextEditor richText;
    @BindView(R.id.submit_research_submit_btn)
    TextView submitResearchSubmitBtn;
    @BindView(R.id.add_img_img_btn)
    Button addImgImgBtn;
    @BindView(R.id.add_content_btn)
    Button addContentBtn;

    public static void openActivity(Context context) {
        Intent intent = new Intent(context, ResearchActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_research);
        BFApplication.context = this;
        ButterKnife.bind(this);
    }

    @OnClick({R.id.add_img_img_btn, R.id.add_content_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_img_img_btn:
                richText.insertImage("http://coinwind.oss-cn-qingdao.aliyuncs.com/20180705105005_963.jpeg");
                break;
            case R.id.add_content_btn:
                LogHelp.e("content", richText.getHtmlContent());
                break;
        }
    }
}
