package com.coinwind.bifeng.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.ui.sendtask.bean.DiaoYanBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SendProblemView extends ScrollView {

    private LayoutInflater inflater;
    private LinearLayout allLayout;// 这个是所有子view的容器，scrollView内部的唯一一个ViewGroup

    private int viewTagIndex = 1; // 新生的view都会打一个tag，对每个view来说，这个tag是唯一的。
    private OnClickListener btnListener; // 删除布局时的监听
    private OnFocusChangeListener focusListener; // 所有EditText的焦点监听listener
    private HashMap<Integer, LinearLayout> items = new HashMap<>();


    public SendProblemView(Context context) {
        this(context, null);
    }

    public SendProblemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SendProblemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflater = LayoutInflater.from(context);

        // 1. 初始化allLayout
        allLayout = new LinearLayout(context);
        allLayout.setOrientation(LinearLayout.VERTICAL);
        allLayout.setBackgroundColor(Color.WHITE);

        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        addView(allLayout, layoutParams);
        // 删除布局时的监听
        btnListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取最外层的布局
                LinearLayout parentView = (LinearLayout) ((LinearLayout) ((RelativeLayout) v.getParent()).getParent()).getParent();
                onItemViewCloseClick(parentView);
            }
        };


        //创建第一个Item
        addItemView();
    }

    /**
     * 点击删除事删除整个布局
     *
     * @param view
     */
    private void onItemViewCloseClick(View view) {
        allLayout.removeView(view);
        setProblemCount();
//        }
    }

    /**
     * 添加Item
     */
    private LinearLayout createItemView() {
        final LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.send_diao_yan_item, null);

        TextView sendDiaoYanItemDeleteBtn = (TextView) linearLayout.findViewById(R.id.send_diao_yan_item_delete_btn);
        RelativeLayout sendDiaoYanItemDeleteBtnLayout = (RelativeLayout) linearLayout.findViewById(R.id.send_diao_yan_item_delete_btn_layout);
        EditText sendDiaoYanItemEt = (EditText) linearLayout.findViewById(R.id.send_diao_yan_item_et);

        linearLayout.setTag(viewTagIndex);
        sendDiaoYanItemEt.setOnFocusChangeListener(focusListener);
        sendDiaoYanItemDeleteBtn.setOnClickListener(btnListener);
        if (viewTagIndex != 1) {
            sendDiaoYanItemDeleteBtnLayout.setVisibility(VISIBLE);
        }
        items.put(viewTagIndex, linearLayout);

        viewTagIndex++;
        return linearLayout;
    }

    /**
     * 设置题号
     */
    private void setProblemCount() {
        for (int i = 0; i < allLayout.getChildCount(); i++) {
            LinearLayout linearLayout = (LinearLayout) allLayout.getChildAt(i);
            TextView sendDiaoYanItemCountTv = (TextView) linearLayout.findViewById(R.id.send_diao_yan_item_count_tv);
            sendDiaoYanItemCountTv.setText((i + 1) + "");
        }
    }

    /**
     * 对外暴露接口获得题目集合
     *
     * @return
     */
    public List<DiaoYanBean> getData() {
        List<DiaoYanBean> dataList = new ArrayList<>();
        int num = allLayout.getChildCount();
        for (int index = 0; index < num; index++) {
            LinearLayout itemView = (LinearLayout) allLayout.getChildAt(index);
            TextView sendDiaoYanItemCountTv = (TextView) itemView.findViewById(R.id.send_diao_yan_item_count_tv);
            EditText sendDiaoYanItemEt = (EditText) itemView.findViewById(R.id.send_diao_yan_item_et);
            DiaoYanBean itemData = new DiaoYanBean();
            itemData.setNum(sendDiaoYanItemCountTv.getText().toString());
            itemData.setTitle(sendDiaoYanItemEt.getText().toString().trim());

            dataList.add(itemData);
        }
        return dataList;
    }

    /**
     * 对外暴露接口添加Item
     */
    public void addItemView() {
        LinearLayout.LayoutParams itemViewParam = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        LinearLayout itemView = createItemView();
        allLayout.addView(itemView, itemViewParam);
        setProblemCount();
    }
}
