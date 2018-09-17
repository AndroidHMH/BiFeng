package com.coinwind.bifeng.ui.task.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coinwind.bifeng.R;
import com.coinwind.bifeng.ui.task.bean.NewTaskBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewTaskAdapter extends RecyclerView.Adapter<NewTaskAdapter.Holder> implements View.OnClickListener {
    private List<NewTaskBean.DataBean.MustDataBean> list;
    private Context context;

    public NewTaskAdapter(List<NewTaskBean.DataBean.MustDataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.new_task_recycler_item, parent, false);
        Holder holder = new Holder(inflate);
        inflate.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        if (position == (list.size() - 1)) {
            holder.newTaskRecyclerItemLine.setVisibility(View.INVISIBLE);
        }
        NewTaskBean.DataBean.MustDataBean mustDataBean = list.get(position);

        holder.newTaskRecyclerItemTaskNameTv.setText(mustDataBean.getInfo());
        Glide.with(context).load(mustDataBean.getImg_url()).into(holder.newTaskRecyclerItemImg);

        int is_use = mustDataBean.getIs_use();
        if (is_use == 0) {
            holder.newTaskRecyclerItemReceiveBtn.setText("未开放");
            holder.newTaskRecyclerItemReceiveBtn.setBackgroundResource(0);
            holder.newTaskRecyclerItemReceiveBtn.setTextColor(context.getResources().getColor(R.color.gray_666));
        } else {
            int is_done = mustDataBean.getIs_done();
            if (is_done == 0) {
                int n_state = mustDataBean.getN_state();
                if (n_state == 1) {
                    holder.newTaskRecyclerItemReceiveBtn.setText("风力+" + mustDataBean.getC_power());
                } else {
                    holder.newTaskRecyclerItemReceiveBtn.setText("风速+" + mustDataBean.getC_speed());
                }
                //未做过
                holder.newTaskRecyclerItemReceiveBtn.setBackgroundResource(R.mipmap.wei_zuo);
                holder.newTaskRecyclerItemReceiveBtn.setTextColor(context.getResources().getColor(R.color.blue_095a));
            } else {
                holder.newTaskRecyclerItemReceiveBtn.setBackgroundResource(R.mipmap.new_task_btn_bg);
                holder.newTaskRecyclerItemReceiveBtn.setText("已完成");
                holder.newTaskRecyclerItemReceiveBtn.setTextColor(context.getResources().getColor(R.color.gray_666));
            }

        }
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.isEmpty() ? 0 : list.size();
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClickListener((int) v.getTag(), v);
        }
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(int position, View view);
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.new_task_recycler_item_img)
        ImageView newTaskRecyclerItemImg;
        @BindView(R.id.new_task_recycler_item_task_name_tv)
        TextView newTaskRecyclerItemTaskNameTv;
        @BindView(R.id.new_task_recycler_item_receive_btn)
        TextView newTaskRecyclerItemReceiveBtn;
        @BindView(R.id.new_task_recycler_item_line)
        View newTaskRecyclerItemLine;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
