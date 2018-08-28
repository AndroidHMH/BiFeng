package com.coinwind.bifeng.ui.setting.bean;

/**
 * 修改密码的bean
 */
public class ChangePswBean {

    /**
     * code : 1000
     * msg : 请求成功！
     * state : true
     * data : {"emsg":"验证码错误！","state":false}
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
         * emsg : 验证码错误！
         * state : false
         */

        private String emsg;
        private boolean state;

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
