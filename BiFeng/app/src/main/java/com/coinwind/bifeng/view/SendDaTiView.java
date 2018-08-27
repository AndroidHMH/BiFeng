package com.coinwind.bifeng.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.ui.sendtask.bean.DiaoYanBean;
import com.coinwind.bifeng.ui.task.bean.SendDaTiBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 答题任务选项卡
 */
public class SendDaTiView extends ScrollView {

    private LayoutInflater inflater;
    private LinearLayout allLayout;// 这个是所有子view的容器，scrollView内部的唯一一个ViewGroup
    private OnClickListener btnListener; // 删除布局时的监听

    private int viewTagIndex = 1; // 新生的view都会打一个tag，对每个view来说，这个tag是唯一的。
    private Map<Integer, String> titleMap = new HashMap<>();

    {
        titleMap.put(1, "一");
        titleMap.put(2, "二");
        titleMap.put(3, "三");
        titleMap.put(4, "四");
        titleMap.put(5, "五");
        titleMap.put(6, "六");
        titleMap.put(7, "七");
        titleMap.put(8, "八");
        titleMap.put(9, "九");
        titleMap.put(10, "十");
    }

    public SendDaTiView(Context context) {
        this(context, null);
    }

    public SendDaTiView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SendDaTiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
                LinearLayout parentView = (LinearLayout) v.getParent();
                onItemViewCloseClick(parentView);
            }
        };


        addView();

    }

    /**
     * 点击删除事删除整个布局
     *
     * @param view
     */
    private void onItemViewCloseClick(View view) {
        allLayout.removeView(view);
        setDaTiCount();
//        }
    }

    /**
     * 添加View
     */
    private LinearLayout createItemView() {
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.send_da_ti_task_item, null);

        final RelativeLayout sendSheetAddItemBtn = (RelativeLayout) linearLayout.findViewById(R.id.send_sheet_add_item_btn);
        ImageView sendSheetDeleteBtn = (ImageView) linearLayout.findViewById(R.id.send_sheet_delete_btn);
        final SendSheetView sendDaTiOptionsView = (SendSheetView) linearLayout.findViewById(R.id.send_da_ti_options_view);

        linearLayout.setTag(viewTagIndex);
        sendDaTiOptionsView.setItemChangeListener(new SendSheetView.ItemChangeListener() {
            @Override
            public void itemChangeListener() {
                sendSheetAddItemBtn.setVisibility(GONE);
            }
        });
        sendSheetAddItemBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDaTiOptionsView.addView();
            }
        });

        if (viewTagIndex != 1) {
            sendSheetDeleteBtn.setVisibility(VISIBLE);
        }
        sendSheetDeleteBtn.setOnClickListener(btnListener);

        viewTagIndex++;
        return linearLayout;
    }

    /**
     * 设置题号
     */
    private void setDaTiCount() {
        for (int i = 0; i < allLayout.getChildCount(); i++) {
            LinearLayout linearLayout = (LinearLayout) allLayout.getChildAt(i);
            TextView sendDaTiTaskItemCountTv = (TextView) linearLayout.findViewById(R.id.send_da_ti_task_item_count_tv);

            sendDaTiTaskItemCountTv.setText(titleMap.get((i + 1)));
        }
    }

    /**
     * 添加View
     */
    public void addView() {
        LinearLayout.LayoutParams itemViewParam = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        LinearLayout itemView = createItemView();
        allLayout.addView(itemView, itemViewParam);
        setDaTiCount();
    }

    /**
     * 获取数据
     *
     * @return
     */
    public List<SendDaTiBean> getDate() {
        List<SendDaTiBean> dataList = new ArrayList<>();
        for (int index = 0; index < allLayout.getChildCount(); index++) {
            LinearLayout itemView = (LinearLayout) allLayout.getChildAt(index);
            TextView sendDaTiItemCountTv = (TextView) itemView.findViewById(R.id.send_da_ti_task_item_count_tv);
            EditText sendDaTiItemRightEt = (EditText) itemView.findViewById(R.id.send_da_ti_task_item_da_an_et);
            EditText sendDaTiItemEt = (EditText) itemView.findViewById(R.id.send_da_ti_task_item_title);
            SendSheetView sendDaTiOptionsView = (SendSheetView) itemView.findViewById(R.id.send_da_ti_options_view);

            int num = -1;
            for (Integer integer : titleMap.keySet()) {
                if (titleMap.get(integer).equals(sendDaTiItemCountTv.getText().toString())) {
                    num = integer;
                    break;
                }
            }
            SendDaTiBean itemData = new SendDaTiBean();
            itemData.setRight(sendDaTiItemRightEt.getText().toString().trim());
            itemData.setNum(num + "");
            itemData.setTitle(sendDaTiItemEt.getText().toString().trim());
            sendDaTiOptionsView.getDate(itemData);

            dataList.add(itemData);
        }
        return dataList;
    }

}
