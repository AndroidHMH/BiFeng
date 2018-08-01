package com.coinwind.bifeng.ui.task.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.config.Codes;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.lujun.androidtagview.ColorFactory;
import co.lujun.androidtagview.TagContainerLayout;

public class TaskAdapter extends BaseAdapter {
    private List<TaskBean> mList;
    private Context context;

    public TaskAdapter(List<TaskBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mList.isEmpty() ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.recycler_item_layout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        TaskBean taskBean = mList.get(position);
        holder.recyclerItemLayoutTitleTv.setText(taskBean.getTitle());
        holder.recyclerItemLayoutShangYuCountTv.setText(taskBean.getShengyu_num() + "");
        holder.recyclerItemLayoutCountTv.setText(taskBean.getScore() + "");
        Glide.with(context).load(taskBean.getLogo()).into(holder.recyclerItemLayoutUserIconImg);
        holder.recyclerItemLayoutUserNameTv.setText(taskBean.getNick_name());
        Glide.with(context).load(taskBean.getHead_img()).into(holder.recyclerItemLayoutIconImg);
        holder.recyclerItemLayoutChangCuoTv.setText(taskBean.getGz_name());

        String is_top = taskBean.getIs_top();
        if ("".equals(is_top) || is_top == null) {
            is_top = "0";
        }
        if (Integer.parseInt(is_top) == Codes.IS_TOP) {
            holder.recyclerItemLayoutZhiDingImg.setVisibility(View.GONE);
        }
        String is_essence = taskBean.getIs_essence();
        if ("".equals(is_essence) || is_essence == null) {
            is_essence = "0";
        }
        if (Integer.parseInt(is_essence) == Codes.IS_ESSENCE) {
            holder.recyclerItemLayoutZhuanFaImg.setVisibility(View.GONE);
        }

        String label = taskBean.getLabel();
        String[] labels = label.split(",");
        holder.recyclerItemLayoutXcfTitleLayout.setTheme(ColorFactory.NONE);
        holder.recyclerItemLayoutXcfTitleLayout.setTagBackgroundColor(context.getResources().getColor(R.color.white));
        holder.recyclerItemLayoutXcfTitleLayout.setTagTextColor(context.getResources().getColor(R.color.red_f42));
        holder.recyclerItemLayoutXcfTitleLayout.setTagBorderColor(context.getResources().getColor(R.color.red_f42));
        holder.recyclerItemLayoutXcfTitleLayout.setTags(labels);


        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.recycler_item_layout_user_icon_img)
        RoundedImageView recyclerItemLayoutUserIconImg;
        @BindView(R.id.recycler_item_layout_user_name_tv)
        TextView recyclerItemLayoutUserNameTv;
        @BindView(R.id.recycler_item_layout_shang_yu_count_tv)
        TextView recyclerItemLayoutShangYuCountTv;
        @BindView(R.id.recycler_item_layout_icon_img)
        ImageView recyclerItemLayoutIconImg;
        @BindView(R.id.recycler_item_layout_zhi_ding_img)
        ImageView recyclerItemLayoutZhiDingImg;
        @BindView(R.id.recycler_item_layout_zhuan_fa_img)
        ImageView recyclerItemLayoutZhuanFaImg;
        @BindView(R.id.recycler_item_layout_title_tv)
        TextView recyclerItemLayoutTitleTv;
        @BindView(R.id.recycler_item_layout_xcf_title_layout)
        TagContainerLayout recyclerItemLayoutXcfTitleLayout;
        @BindView(R.id.recycler_item_layout_chang_cuo_tv)
        TextView recyclerItemLayoutChangCuoTv;
        @BindView(R.id.recycler_item_layout_count_tv)
        TextView recyclerItemLayoutCountTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
