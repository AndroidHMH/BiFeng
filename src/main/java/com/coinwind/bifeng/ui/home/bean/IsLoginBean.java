package com.coinwind.bifeng.ui.home.bean;

public class IsLoginBean {


    /**
     * code : 403
     * msg : 403 登录失效
     * state : false
     * data : {"code":4033,"emsg":"登录时间过期","state":false}
     */

    private int code;
    private String msg;
    private boolean state;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * code : 4033
         * emsg : 登录时间过期
         * state : false
         */

        private int code;
        private String emsg;
        private boolean state;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getEmsg() {
            return emsg;
        }

        public void setEmsg(String emsg) {
            this.emsg = emsg;
        }

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }
    }
}