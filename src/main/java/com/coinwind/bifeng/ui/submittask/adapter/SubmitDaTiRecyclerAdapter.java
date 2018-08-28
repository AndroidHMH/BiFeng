package com.coinwind.bifeng.ui.submittask.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubmitDaTiRecyclerAdapter extends RecyclerView.Adapter<SubmitDaTiRecyclerAdapter.Holder> {
    private List<String> options;
    private Holder lastHolder;
    private ItemContent itemContent;

    public SubmitDaTiRecyclerAdapter(List<String> options) {
        this.options = options;
    }

    public void setItemContent(ItemContent itemContent) {
        this.itemContent = itemContent;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.submit_da_ti_recycler_item, parent, false);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {
        holder.submitDaTiRecyclerItemTitleTv.setText(options.get(position));
        holder.submitDaTiRecyclerItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastHolder != null) {
                    lastHolder.submitDaTiRecyclerItemClickImg.setVisibility(View.GONE);
                }
                holder.submitDaTiRecyclerItemClickImg.setVisibility(View.VISIBLE);
                itemContent.itemContent(options.get(position));
                lastHolder = holder;
            }
        });
    }

    public interface ItemContent {
        void itemContent(String content);
    }

    @Override
    public int getItemCount() {
        return options.isEmpty() ? 0 : options.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.submit_da_ti_recycler_item_title_tv)
        TextView submitDaTiRecyclerItemTitleTv;
        @BindView(R.id.submit_da_ti_recycler_item_click_img)
        ImageView submitDaTiRecyclerItemClickImg;
        @BindView(R.id.submit_da_ti_recycler_item_btn)
        LinearLayout submitDaTiRecyclerItemBtn;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
