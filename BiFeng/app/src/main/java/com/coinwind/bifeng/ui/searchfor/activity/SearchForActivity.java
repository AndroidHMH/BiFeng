package com.coinwind.bifeng.ui.searchfor.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.searchfor.contract.SearchForContract;
import com.coinwind.bifeng.ui.searchfor.presenter.SearchForPresenter;
import com.coinwind.bifeng.ui.task.activity.AnswerTaskActivity;
import com.coinwind.bifeng.ui.task.adapter.TaskAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 搜索界面
 */
public class SearchForActivity extends BaseActivity<SearchForPresenter> implements SearchForContract.View, View.OnKeyListener, AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.title_layout_return_btn)
    LinearLayout titleLayoutReturnBtn;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.search_for_list)
    ListView searchForList;
    @BindView(R.id.title_bar_layout)
    LinearLayout titleBarLayout;
    @BindView(R.id.search_for_et)
    EditText searchForEt;
    @BindView(R.id.search_for_qu_xiao_btn)
    TextView searchForQuXiaoBtn;

    private List<TaskBean> taskBeans;
    private TaskAdapter taskAdapter;
    private int page = 1;
    private String searchForContent;
    private boolean loadFinishFlag = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_for;
    }

    @Override
    protected void init() {
        taskBeans = new ArrayList<>();
        searchForEt.setOnKeyListener(this);
        taskAdapter = new TaskAdapter(taskBeans, this);
        searchForList.setAdapter(taskAdapter);
        searchForList.setOnItemClickListener(this);
        searchForList.setOnScrollListener(this);
    }

    @Override
    protected void loadDate() {

    }

    @OnClick({R.id.title_layout_return_btn, R.id.search_for_qu_xiao_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_layout_return_btn:
                finish();
                finish();
                break;
            case R.id.search_for_qu_xiao_btn:
                searchForEt.setText("");
                break;
        }
    }

    @Override
    public void showSearchFor(List<TaskBean> taskBeans) {
        loadFinishFlag = true;
        this.taskBeans.addAll(taskBeans);
        if (taskBeans.size() == 0) {
            loadFinishFlag = false;
        }
        taskAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        //是否是回车键
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            //隐藏键盘
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(SearchForActivity.this.getCurrentFocus()
                            .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            if (page != 1) {
                page = 1;
            }
            loadFinishFlag = true;
            searchForContent = searchForEt.getText().toString().trim();
            if (!"".equals(searchForContent)) {
                presenter.showSearchForList(searchForContent, page);
            }
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, AnswerTaskActivity.class);
        intent.putExtra("bean", taskBeans.get(position));
        startActivity(intent);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //获取屏幕最后Item的ID
        int lastVisibleItem = searchForList.getLastVisiblePosition();
        if (lastVisibleItem + 1 == totalItemCount) {
            if (loadFinishFlag) {
                //标志位，防止多次加载
                loadFinishFlag = false;
                page++;
                presenter.showSearchForList(searchForContent, page);
            }
        }
    }
}
