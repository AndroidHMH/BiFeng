package com.coinwind.bifeng.ui.task.bean;

/**
 * 答题任务的bean
 */
public class SendDaTiBean {
    private String title;
    private String num;
    private String right;
    private String A;
    private String B;
    private String C;
    private String D;

    public SendDaTiBean() {
    }
    

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

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

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }

    @Override
    public String toString() {
        return "SendDaTiBean{" +
                "title='" + title + '\'' +
                ", num='" + num + '\'' +
                ", right='" + right + '\'' +
                ", A='" + A + '\'' +
                ", B='" + B + '\'' +
                ", C='" + C + '\'' +
                ", D='" + D + '\'' +
                '}';
    }
}
