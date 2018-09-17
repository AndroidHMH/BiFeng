package com.coinwind.bifeng.ui.my.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.ui.my.bean.MyTaskBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的任务adapter
 */
public class NewMyTaskAdapter extends RecyclerView.Adapter<NewMyTaskAdapter.Holder> {
    private List<MyTaskBean.DataBean.ListBean> listBeans;

    public NewMyTaskAdapter(List<MyTaskBean.DataBean.ListBean> listBeans) {
        this.listBeans = listBeans;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_my_task_recycler_item, parent, false);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        MyTaskBean.DataBean.ListBean listBean = listBeans.get(position);
        holder.newMyTaskRecyclerItemTitleTv.setText(listBean.getTitle());
        holder.newMyTaskRecyclerItemTimeTv.setText(listBean.getSub_time());
    }

    @Override
    public int getItemCount() {
        return listBeans.isEmpty() ? 0 : listBeans.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.new_my_task_recycler_item_title_tv)
        TextView newMyTaskRecyclerItemTitleTv;
        @BindView(R.id.new_my_task_recycler_item_time_tv)
        TextView newMyTaskRecyclerItemTimeTv;
        @BindView(R.id.new_my_task_recycler_item_show_tv)
        TextView newMyTaskRecyclerItemShowTv;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
