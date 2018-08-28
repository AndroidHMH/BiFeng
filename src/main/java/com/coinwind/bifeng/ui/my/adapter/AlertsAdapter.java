package com.coinwind.bifeng.ui.my.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coinwind.bifeng.R;
import com.coinwind.bifeng.ui.my.bean.AlertsBean;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 消息通知的list适配器
 */
public class AlertsAdapter extends RecyclerView.Adapter<AlertsAdapter.Holder> {
    private List<AlertsBean.DataBean> dataBeanList;
    private Context context;

    public AlertsAdapter(List<AlertsBean.DataBean> dataBeanList) {
        this.dataBeanList = dataBeanList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.alerts_recycler_item, parent, false);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        AlertsBean.DataBean dataBean = dataBeanList.get(position);
        holder.alertsRecyclerLayoutContentTv.setText(dataBean.getContent());
        holder.alertsRecyclerLayoutTimeTv.setText(dataBean.getFlrrq());
        Glide.with(context).load(dataBean.getLogo()).into(holder.alertsRecyclerLayoutIconImg);
        holder.alertsRecyclerLayoutTypeTv.setText(dataBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return dataBeanList.isEmpty() ? 0 : dataBeanList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.alerts_recycler_layout_icon_img)
        RoundedImageView alertsRecyclerLayoutIconImg;
        @BindView(R.id.alerts_recycler_layout_type_tv)
        TextView alertsRecyclerLayoutTypeTv;
        @BindView(R.id.alerts_recycler_layout_time_tv)
        TextView alertsRecyclerLayoutTimeTv;
        @BindView(R.id.alerts_recycler_layout_content_tv)
        TextView alertsRecyclerLayoutContentTv;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
