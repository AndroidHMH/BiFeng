package com.coinwind.bifeng.ui.submittask.adapter;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.coinwind.bifeng.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 提交任务配图的适配器
 */
public class AddImgAdapter extends RecyclerView.Adapter<AddImgAdapter.Holder> implements View.OnClickListener {
    private List<Bitmap> bitmapList;
    private OnItemClick onItemClick;
    private OnItemImgClick onItemImgClick;

    public AddImgAdapter(List<Bitmap> bitmapList) {
        this.bitmapList = bitmapList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_img_view, parent, false);
        Holder holder = new Holder(inflate);
        inflate.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        if ((position + 1) == bitmapList.size()) {
            holder.deleteImgBtn.setVisibility(View.GONE);
        } else {
            holder.deleteImgBtn.setVisibility(View.VISIBLE);
        }
        holder.addImgImg.setImageBitmap(bitmapList.get(position));
        holder.deleteImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onItemClick(position);
                }
            }
        });
        holder.itemView.setTag(position);
    }

    @Override
    public void onClick(View v) {
        if (onItemImgClick != null) {
            onItemImgClick.onItemImgClick((int) v.getTag());
        }
    }

    public interface OnItemClick {
        void onItemClick(int position);
    }

    public interface OnItemImgClick {
        void onItemImgClick(int position);
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setOnItemImgClick(OnItemImgClick onItemImgClick) {
        this.onItemImgClick = onItemImgClick;
    }

    @Override
    public int getItemCount() {
        return bitmapList.isEmpty() ? 0 : bitmapList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.add_img_img)
        ImageView addImgImg;
        @BindView(R.id.delete_img_btn)
        ImageView deleteImgBtn;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
