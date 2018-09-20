package com.coinwind.bifeng.ui.record.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.ui.my.bean.NewWalletBean;
import com.coinwind.bifeng.ui.record.bean.RecordBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的钱包list的适配器
 */
public class RecordAdapter extends BaseAdapter {
    private List<RecordBean.DataBean.CcLogBean.ListBean> loginBeans;
    private Context context;

    public RecordAdapter(List<RecordBean.DataBean.CcLogBean.ListBean> loginBeans, Context context) {
        this.loginBeans = loginBeans;
        this.context = context;
    }


    @Override
    public int getCount() {
        return loginBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return loginBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.my_wallet_list_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        RecordBean.DataBean.CcLogBean.ListBean listBean = loginBeans.get(position);
        holder.myWalletListCountTv.setText("+" + listBean.getCc());
        holder.myWalletListTimeTv.setText(listBean.getCreate_date());
        holder.myWalletListTypeTv.setText(listBean.getNote());
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.my_wallet_list_type_tv)
        TextView myWalletListTypeTv;
        @BindView(R.id.my_wallet_list_time_tv)
        TextView myWalletListTimeTv;
        @BindView(R.id.my_wallet_list_count_tv)
        TextView myWalletListCountTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
