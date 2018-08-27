package com.coinwind.bifeng.ui.submittask.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.ui.sendtask.bean.DiaoYanBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 调研任务的bean
 */
public class AnswerTheQuestionsAdapter extends RecyclerView.Adapter<AnswerTheQuestionsAdapter.Holder> {
    private List<DiaoYanBean> list;

    public AnswerTheQuestionsAdapter(List<DiaoYanBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_the_questions_recycler_item, parent, false);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        DiaoYanBean diaoYanBean = list.get(position);
        holder.answerTheQuestionsRecyclerItemQidTv.setText(diaoYanBean.getNum());
        holder.answerTheQuestionsRecyclerItemTitleTv.setText(diaoYanBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.isEmpty() ? 0 : list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.answer_the_questions_recycler_item_qid_tv)
        TextView answerTheQuestionsRecyclerItemQidTv;
        @BindView(R.id.answer_the_questions_recycler_item_title_tv)
        TextView answerTheQuestionsRecyclerItemTitleTv;
        @BindView(R.id.answer_the_questions_recycler_item_answer_et)
        EditText answerTheQuestionsRecyclerItemAnswerEt;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
