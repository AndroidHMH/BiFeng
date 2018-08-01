package com.coinwind.bifeng.ui.task.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseFragment;
import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.ui.searchfor.activity.SearchForActivity;
import com.coinwind.bifeng.ui.task.activity.AnswerTaskActivity;
import com.coinwind.bifeng.ui.task.activity.TaskTypeActivity;
import com.coinwind.bifeng.ui.task.adapter.TaskAdapter;
import com.coinwind.bifeng.ui.task.adapter.TaskTypeAdapter;
import com.coinwind.bifeng.ui.task.config.SetViewHelp;
import com.coinwind.bifeng.ui.task.contract.TaskContract;
import com.coinwind.bifeng.ui.task.presenter.TaskPresenter;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 任务大厅页面
 */
public class TaskFragment extends BaseFragment<TaskPresenter> implements TaskContract.View, AbsListView.OnScrollListener, TaskTypeAdapter.OnItemCLick, AdapterView.OnItemClickListener {


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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_task;
    }

    @Override
    protected void init() {
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


    @OnClick({R.id.title_layout_return_btn, R.id.task_zhi_xing_btn, R.id.task_my_fa_bo_btn, R.id.task_fa_bo_btn, R.id.task_sou_suo_btn, R.id.task_type_first_layout, R.id.task_type_second_layout, R.id.task_type_third_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.task_zhi_xing_btn:
                break;
            case R.id.task_my_fa_bo_btn:
                break;
            case R.id.task_fa_bo_btn:
                startActivity(new Intent(getContext(), TaskTypeActivity.class));
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getContext(), AnswerTaskActivity.class);
        TaskBean taskBean = mList.get(position);
        intent.putExtra("bean", taskBean);
        startActivity(intent);
    }

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

    private void initPopup() {
        View inflate = getLayoutInflater().inflate(R.layout.task_type_popup_layou, null);
        initPopupView(inflate);
        popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setOutsideTouchable(true);

    }

    private void initPopupView(View rootView) {
        this.taskTypePopupImg = (ImageView) rootView.findViewById(R.id.task_type_popup_img);
        this.taskTypePopupRecycler = (RecyclerView) rootView.findViewById(R.id.task_type_popup_recycler);

        taskTypePopupRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        taskTypePopupRecycler.setAdapter(taskTypeAdapter);
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
}
