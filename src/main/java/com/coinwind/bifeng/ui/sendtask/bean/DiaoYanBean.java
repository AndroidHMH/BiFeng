package com.coinwind.bifeng.ui.sendtask.bean;

public class DiaoYanBean {
    private String title;
    private String num;
    private String timu_content;


    public DiaoYanBean() {
    }

    public DiaoYanBean(String title, String num) {
        this.title = title;
        this.num = num;
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

    public String getTimu_content() {
        return timu_content;
    }

    public void setTimu_content(String timu_content) {
        this.timu_content = timu_content;
    }

    @Override
    public String toString() {
        return "DiaoYanBean{" +
                "title='" + title + '\'' +
                ", num='" + num + '\'' +
                ", timu_content='" + timu_content + '\'' +
                '}';
    }
}
