package com.coinwind.bifeng.ui.my.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.ui.my.bean.WalletBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的钱包list的适配器
 */
public class MyWalletRecyclerAdapter extends RecyclerView.Adapter<MyWalletRecyclerAdapter.Holder> {
    private List<WalletBean.DataBean.BfCssLogBean.ListBean> dataBeans;
    private Context context;

    public MyWalletRecyclerAdapter(List<WalletBean.DataBean.BfCssLogBean.ListBean> dataBeans, Context context) {
        this.dataBeans = dataBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.my_wallet_list_item, parent, false);
        Holder holder = new Holder(convertView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        WalletBean.DataBean.BfCssLogBean.ListBean listBean = dataBeans.get(position);
        holder.myWalletListCountTv.setText(listBean.getCss());
        holder.myWalletListTimeTv.setText(listBean.getLrrq());
        holder.myWalletListTypeTv.setText(listBean.getNote());
    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }


    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.my_wallet_list_type_tv)
        TextView myWalletListTypeTv;
        @BindView(R.id.my_wallet_list_time_tv)
        TextView myWalletListTimeTv;
        @BindView(R.id.my_wallet_list_count_tv)
        TextView myWalletListCountTv;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
