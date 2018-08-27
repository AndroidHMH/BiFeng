package com.coinwind.bifeng.ui.submittask.bean;

/**
 * 提交答题任务
 */
public class SendDaTiBean {
    private String num;//题号
    private String sub_answer;//用户选择的答案
    private String timu_content;//题目
    private String right_answer;//正确答案
    private String is_right;//是否正确   0不正确    1正确

    public SendDaTiBean() {
    }

    public SendDaTiBean(String num, String sub_answer, String timu_content, String right_answer, String is_right) {
        this.num = num;
        this.sub_answer = sub_answer;
        this.timu_content = timu_content;
        this.right_answer = right_answer;
        this.is_right = is_right;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSub_answer() {
        return sub_answer;
    }

    public void setSub_answer(String sub_answer) {
        this.sub_answer = sub_answer;
    }

    public String getTimu_content() {
        return timu_content;
    }

    public void setTimu_content(String timu_content) {
        this.timu_content = timu_content;
    }

    public String getRight_answer() {
        return right_answer;
    }

    public void setRight_answer(String right_answer) {
        this.right_answer = right_answer;
    }

    public String getIs_right() {
        return is_right;
    }

    public void setIs_right(String is_right) {
        this.is_right = is_right;
    }

    @Override
    public String toString() {
        return "SendDaTiBean{" +
                "num='" + num + '\'' +
                ", sub_answer='" + sub_answer + '\'' +
                ", timu_content='" + timu_content + '\'' +
                ", right_answer='" + right_answer + '\'' +
                ", is_right='" + is_right + '\'' +
                '}';
    }
}
