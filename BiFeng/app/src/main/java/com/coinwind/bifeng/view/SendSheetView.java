package com.coinwind.bifeng.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.ui.task.bean.SendDaTiBean;

import java.util.List;

public class SendSheetView extends LinearLayout {
    private LayoutInflater inflater;
    private int viewTagIndex = 1; // 新生的view都会打一个tag，对每个view来说，这个tag是唯一的。

    private ItemChangeListener itemChangeListener;//布局改变时的监听

    public SendSheetView(Context context) {
        this(context, null);
    }

    public SendSheetView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SendSheetView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflater = LayoutInflater.from(context);

        createItemView();
        createItemView();
    }

    public void setItemChangeListener(ItemChangeListener itemChangeListener) {
        this.itemChangeListener = itemChangeListener;
    }

    /**
     * 添加item
     */
    private LinearLayout createItemView() {
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.send_sheet_item, null);

        TextView sendSheetOptionsTv = (TextView) linearLayout.findViewById(R.id.send_sheet_options_tv);

        linearLayout.setTag(viewTagIndex);
        sendSheetOptionsTv.setText(getSheetOptions(viewTagIndex));
        addView(linearLayout);
        if (getChildCount() == 4) {
            itemChangeListener.itemChangeListener();
            return null;
        }
        setAllOptions();
        viewTagIndex++;
        return linearLayout;
    }

    /**
     * 设置现象卡
     */
    private void setAllOptions() {
        for (int i = 0; i < getChildCount(); i++) {
            LinearLayout childAt = (LinearLayout) getChildAt(i);
            TextView sendSheetOptionsEt = childAt.findViewById(R.id.send_sheet_options_tv);
            sendSheetOptionsEt.setText(getSheetOptions((int) childAt.getTag()));
        }
    }

    /**
     * 获取选项卡的选项
     *
     * @param sheetOptions
     * @return
     */
    private String getSheetOptions(int sheetOptions) {
        String options = "";
        switch (sheetOptions) {
            case 1:
                options = "A";
                break;
            case 2:
                options = "B";
                break;
            case 3:
                options = "C";
                break;
            case 4:
                options = "D";
                break;
        }
        return options;
    }

    /**
     * 获取数据
     *
     * @param sendDaTiBean
     */
    public void getDate(SendDaTiBean sendDaTiBean) {
        for (int i = 0; i < getChildCount(); i++) {
            LinearLayout childAt = (LinearLayout) getChildAt(i);

            TextView sendSheetOptionsTv = childAt.findViewById(R.id.send_sheet_options_tv);
            TextView sendSheetOptionsEt = childAt.findViewById(R.id.send_sheet_options_et);

            String sheetOption = sendSheetOptionsTv.getText().toString();
            switch (sheetOption) {
                case "A":
                    String a = sendSheetOptionsEt.getText().toString();
                    if (a != null && !"".equals(a)) {
                        sendDaTiBean.setA(a);
                    } else {
                        sendDaTiBean.setA("");
                    }
                    break;
                case "B":
                    String b = sendSheetOptionsEt.getText().toString();
                    if (b != null && !"".equals(b)) {
                        sendDaTiBean.setB(b);
                    } else {
                        sendDaTiBean.setB("");
                    }
                    break;
                case "C":
                    String c = sendSheetOptionsEt.getText().toString();
                    if (c != null && !"".equals(c)) {
                        sendDaTiBean.setC(c);
                    } else {
                        sendDaTiBean.setC("");
                    }
                    break;
                case "D":
                    String d = sendSheetOptionsEt.getText().toString();
                    if (d != null && !"".equals(d)) {
                        sendDaTiBean.setD(d);
                    } else {
                        sendDaTiBean.setD("");
                    }
                    break;
            }
        }
    }

    /**
     * 对外暴露接口添加View
     */
    public void addView() {
        createItemView();
    }


    public interface ItemChangeListener {
        void itemChangeListener();
    }
}
