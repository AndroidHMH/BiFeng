package com.coinwind.bifeng.ui.submittask.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.ui.submittask.activity.SubmitDaTiActivity;
import com.coinwind.bifeng.ui.submittask.adapter.SubmitDaTiRecyclerAdapter;
import com.coinwind.bifeng.ui.submittask.bean.SubmitDaTiBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 答题页面的题
 */
public class SubmitDaTiFragment extends Fragment {


    @BindView(R.id.submit_da_ti_title)
    TextView submitDaTiTitle;
    @BindView(R.id.submit_da_ti_recycler)
    RecyclerView submitDaTiRecycler;
    Unbinder unbinder;
    private SubmitDaTiBean.QuestionBean item;
    private List<String> options;
    private ClickItem clickItem;

    public void setClickItem(ClickItem clickItem) {
        this.clickItem = clickItem;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_submit_da_ti, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        options = new ArrayList<>();
        item = (SubmitDaTiBean.QuestionBean) getArguments().getSerializable("item");
        submitDaTiTitle.setText(item.getTitle());
        nullOrEmpty("A", item.getA());
        nullOrEmpty("B", item.getB());
        nullOrEmpty("C", item.getC());
        nullOrEmpty("D", item.getD());

        SubmitDaTiRecyclerAdapter submitDaTiRecyclerAdapter = new SubmitDaTiRecyclerAdapter(options);
        submitDaTiRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        submitDaTiRecycler.setAdapter(submitDaTiRecyclerAdapter);
        submitDaTiRecyclerAdapter.setItemContent(new SubmitDaTiRecyclerAdapter.ItemContent() {
            @Override
            public void itemContent(String content) {
//                submit_da_ti_btn_shape
                LogHelp.e("SubmitDaTiFragment", "content = " + content);
//
                clickItem.clickItem(item.getTitle(), content, item.getRight(),item.getNum());
            }
        });
    }

    private void nullOrEmpty(String optionId, String option) {
        if (null != option && !"".equals(option)) {
            options.add(optionId + "、" + option);
        }

    }

    public interface ClickItem {
        void clickItem(String problem, String content, String right,String num);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
