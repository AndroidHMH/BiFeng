package com.coinwind.bifeng.ui.submittask.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 提交答题任务的bean
 */
public class SubmitDaTiBean {

    /**
     * passNum : 2
     * totalNum : 3
     * question : [{"title":"第一题","num":"一","right":"A","A":"1","B":"2"},{"title":"第2题","num":"二","right":"B","A":"2","B":"3","C":"4"},{"title":"第3题","num":"三","right":"A","A":"3","B":"4","C":"5","D":"6"}]
     */

    private String passNum;
    private int totalNum;
    private List<QuestionBean> question;

    public String getPassNum() {
        return passNum;
    }

    public void setPassNum(String passNum) {
        this.passNum = passNum;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public List<QuestionBean> getQuestion() {
        return question;
    }

    public void setQuestion(List<QuestionBean> question) {
        this.question = question;
    }

    public static class QuestionBean implements Serializable {
        /**
         * title : 第一题
         * num : 一
         * right : A
         * A : 1
         * B : 2
         * C : 4
         * D : 6
         */

        private String title;
        private String num;
        private String right;
        private String A;
        private String B;
        private String C;
        private String D;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getRight() {
            return right;
        }

        public void setRight(String right) {
            this.right = right;
        }

        public String getA() {
            return A;
        }

        public void setA(String A) {
            this.A = A;
        }

        public String getB() {
            return B;
        }

        public void setB(String B) {
            this.B = B;
        }

        public String getC() {
            return C;
        }

        public void setC(String C) {
            this.C = C;
        }

        public String getD() {
            return D;
        }

        public void setD(String D) {
            this.D = D;
        }
    }
}
