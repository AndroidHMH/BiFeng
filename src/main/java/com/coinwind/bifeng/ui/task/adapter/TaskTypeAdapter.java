package com.coinwind.bifeng.ui.task.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 任务大厅筛选框的适配器
 */
public class TaskTypeAdapter extends RecyclerView.Adapter<TaskTypeAdapter.Holder> {
    private List<String> titles;
    private OnItemCLick onItemCLick;
    private Context context;
    private TaskTypeAdapter.Holder lastHolder;

    public TaskTypeAdapter(List<String> titles) {
        this.titles = titles;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.task_type_popup_recycler_item, parent, false);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {
        holder.taskTypePopupRecyclerItemTypeTv.setText(titles.get(position));
        holder.taskTypePopupRecyclerItemTypeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.taskTypePopupRecyclerItemTypeTv.setTextColor(context.getResources().getColor(R.color.blue_095a));
                holder.taskTypePopupRecyclerItemLineView.setBackgroundColor(context.getResources().getColor(R.color.blue_095a));
                if (lastHolder != null) {
                    lastHolder.taskTypePopupRecyclerItemTypeTv.setTextColor(context.getResources().getColor(R.color.gray_666));
                    lastHolder.taskTypePopupRecyclerItemLineView.setBackgroundColor(context.getResources().getColor(R.color.gray_e9));
                }
                lastHolder = holder;
                if (onItemCLick != null) {
                    onItemCLick.onItemClick(v, position);
                }
            }
        });
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return titles.isEmpty() ? 0 : titles.size();
    }

    public void setOnItemCLick(OnItemCLick onItemCLick) {
        this.onItemCLick = onItemCLick;
    }


    public interface OnItemCLick {
        void onItemClick(View view, int position);
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.task_type_popup_recycler_item_type_tv)
        public TextView taskTypePopupRecyclerItemTypeTv;
        @BindView(R.id.task_type_popup_recycler_item_type_layout)
        public LinearLayout taskTypePopupRecyclerItemTypeLayout;
        @BindView(R.id.task_type_popup_recycler_item_line_view)
        public View taskTypePopupRecyclerItemLineView;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
