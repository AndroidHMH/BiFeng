package com.coinwind.bifeng.ui.home.adapter;

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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.lujun.androidtagview.ColorFactory;
import co.lujun.androidtagview.TagContainerLayout;

/**
 * 首页list适配器
 */
public class HomeAdapter extends BaseAdapter {
    private List<TaskBean> mList;
    private Context context;

    public HomeAdapter(List<TaskBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mList.isEmpty() ? 0 : mList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.home_recycler_item_layout, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        TaskBean taskBean = mList.get(i);
        holder.homeRecyclerItemLayoutTitleTv.setText(taskBean.getTitle());
        holder.homeRecyclerItemLayoutCountTv.setText(taskBean.getShengyu_num() + "");
        holder.homeRecyclerItemLayoutScoreTv.setText(taskBean.getScore() + "");
        Glide.with(context).load(taskBean.getLogo()).into(holder.homeRecyclerItemLayoutImg);
        String is_top = taskBean.getIs_top();
        if (Integer.parseInt(is_top) == Codes.IS_TOP) {
            holder.homeRecyclerItemLayoutZhiDiongTv.setVisibility(View.GONE);
        }
        String is_essence = taskBean.getIs_essence();
        if (Integer.parseInt(is_essence) == Codes.IS_ESSENCE) {
            holder.homeRecyclerItemLayoutJingPinTv.setVisibility(View.GONE);
        }

        String label = taskBean.getLabel();
        String[] labels = label.split(",");
        holder.homeRecyclerItemLayoutXcfTitleLayout.setTheme(ColorFactory.NONE);
        holder.homeRecyclerItemLayoutXcfTitleLayout.setTagBackgroundColor(context.getResources().getColor(R.color.white_fff));
        holder.homeRecyclerItemLayoutXcfTitleLayout.setTagTextColor(context.getResources().getColor(R.color.red_f42));
        holder.homeRecyclerItemLayoutXcfTitleLayout.setTagBorderColor(context.getResources().getColor(R.color.red_f42));
        holder.homeRecyclerItemLayoutXcfTitleLayout.setTags(labels);

        String type = taskBean.getType();
        setType(type,holder);
        return view;
    }

    private void setType(String type,ViewHolder holder) {
        switch (Integer.parseInt(type)) {
            case 1:
                holder.homeRecyclerItemLayoutBiaoQianImg.setImageResource(R.mipmap.zhang_fen_biao_qian);
                break;
            case 2:
                holder.homeRecyclerItemLayoutBiaoQianImg.setImageResource(R.mipmap.ping_lun_biao_qian);
                break;
            case 3:
                holder.homeRecyclerItemLayoutBiaoQianImg.setImageResource(R.mipmap.zhu_ce_biao_qian);
                break;
            case 4:
                holder.homeRecyclerItemLayoutBiaoQianImg.setImageResource(R.mipmap.zhuan_fa_biao_qian);
                break;
            case 5:
                holder.homeRecyclerItemLayoutBiaoQianImg.setImageResource(R.mipmap.jiao_yan_biao_qian);
                break;
            case 6:
                holder.homeRecyclerItemLayoutBiaoQianImg.setImageResource(R.mipmap.da_ti_biao_qian);
                break;
            case 7:
                holder.homeRecyclerItemLayoutBiaoQianImg.setImageResource(R.mipmap.pai_zhao_biap_qian);
                break;
        }
    }


    class ViewHolder {
        @BindView(R.id.home_recycler_item_layout_img)
        ImageView homeRecyclerItemLayoutImg;
        @BindView(R.id.home_recycler_item_layout_title_tv)
        TextView homeRecyclerItemLayoutTitleTv;
        @BindView(R.id.home_recycler_item_layout_xcf_title_layout)
        TagContainerLayout homeRecyclerItemLayoutXcfTitleLayout;
        @BindView(R.id.home_recycler_item_layout_count_tv)
        TextView homeRecyclerItemLayoutCountTv;
        @BindView(R.id.home_recycler_item_layout_score_tv)
        TextView homeRecyclerItemLayoutScoreTv;
        @BindView(R.id.home_recycler_item_layout_zhi_diong_tv)
        ImageView homeRecyclerItemLayoutZhiDiongTv;
        @BindView(R.id.home_recycler_item_layout_jing_pin_tv)
        ImageView homeRecyclerItemLayoutJingPinTv;
        @BindView(R.id.home_recycler_item_layout_biao_qian_img)
        ImageView homeRecyclerItemLayoutBiaoQianImg;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
