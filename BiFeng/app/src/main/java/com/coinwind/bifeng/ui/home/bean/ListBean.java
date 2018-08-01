package com.coinwind.bifeng.ui.home.bean;

import com.coinwind.bifeng.base.TaskBean;

import java.util.List;

public class ListBean {

    private int code;
    private String msg;
    private boolean state;
    private List<TaskBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public List<TaskBean> getData() {
        return data;
    }

    public void setData(List<TaskBean> data) {
        this.data = data;
    }


}
