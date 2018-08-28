package com.coinwind.bifeng.ui.task.bean;

/**
 * 认证的bean
 */
public class RenZhengBean {

    /**
     * code : 1000
     * msg : 请求成功！
     * state : true
     * data : {"eMsg":"用户认证成功","state":true}
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
         * eMsg : 用户认证成功
         * state : true
         */

        private String eMsg;
        private boolean state;

        public String getEMsg() {
            return eMsg;
        }

        public void setEMsg(String eMsg) {
            this.eMsg = eMsg;
        }

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }
    }
}
