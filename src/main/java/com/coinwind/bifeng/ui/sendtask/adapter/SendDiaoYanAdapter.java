package com.coinwind.bifeng.ui.sendtask.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.ui.sendtask.bean.DiaoYanBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 发布调研任务的适配器
 */
public class SendDiaoYanAdapter extends RecyclerView.Adapter<SendDiaoYanAdapter.Holder> {
    private List<DiaoYanBean> diaoYanBeans;
    private DeleteItem deleteItem;

    public SendDiaoYanAdapter(List<DiaoYanBean> diaoYanBeans) {
        this.diaoYanBeans = diaoYanBeans;
    }

    public void setDeleteItem(DeleteItem deleteItem) {
        this.deleteItem = deleteItem;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.send_diao_yan_item, parent, false);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {
        if (position == 0) {
            holder.sendDiaoYanItemDeleteBtnLayout.setVisibility(View.GONE);
        } else {
            holder.sendDiaoYanItemDeleteBtnLayout.setVisibility(View.VISIBLE);
        }
        holder.sendDiaoYanItemCountTv.setText((position + 1) + "");
        diaoYanBeans.get(position).setNum((position + 1) + "");
        holder.sendDiaoYanItemDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem.deleteItem(position);
            }
        });
        holder.sendDiaoYanItemEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                diaoYanBeans.get(position).setTitle(holder.sendDiaoYanItemEt.getText().toString().trim());
            }
        });
    }

    @Override
    public int getItemCount() {
        return diaoYanBeans.isEmpty() ? 0 : diaoYanBeans.size();
    }

    public interface DeleteItem {
        void deleteItem(int position);
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.send_diao_yan_item_count_tv)
        TextView sendDiaoYanItemCountTv;
        @BindView(R.id.send_diao_yan_item_delete_btn)
        TextView sendDiaoYanItemDeleteBtn;
        @BindView(R.id.send_diao_yan_item_delete_btn_layout)
        RelativeLayout sendDiaoYanItemDeleteBtnLayout;
        @BindView(R.id.send_diao_yan_item_et)
        EditText sendDiaoYanItemEt;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
