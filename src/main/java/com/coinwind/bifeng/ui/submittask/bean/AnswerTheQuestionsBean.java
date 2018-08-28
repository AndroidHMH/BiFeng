package com.coinwind.bifeng.ui.submittask.bean;

import com.coinwind.bifeng.ui.sendtask.bean.DiaoYanBean;

import java.util.ArrayList;

/**
 * 调研任务的bean
 */
public class AnswerTheQuestionsBean {
    private String totalNum;
    private ArrayList<DiaoYanBean> question;

    public AnswerTheQuestionsBean() {
    }

    public AnswerTheQuestionsBean(String totalNum, ArrayList<DiaoYanBean> question) {
        this.totalNum = totalNum;
        this.question = question;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public ArrayList<DiaoYanBean> getQuestion() {
        return question;
    }

    public void setQuestion(ArrayList<DiaoYanBean> question) {
        this.question = question;
    }
}
