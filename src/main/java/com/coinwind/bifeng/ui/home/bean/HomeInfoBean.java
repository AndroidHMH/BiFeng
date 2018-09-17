package com.coinwind.bifeng.ui.home.bean;

/**
 * 首页基本信息的bean
 */
public class HomeInfoBean {

    /**
     * code : 1000
     * msg : 请求成功！
     * state : true
     * data : {"user_num":636,"process":70,"one_num":5000,"state":true}
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
         * user_num : 636       //当前用户数
         * process : 70         //当前进度
         * one_num : 5000       //第一矿池数
         * state : true         //请求状态
         */

        private int user_num;
        private int process;
        private int one_num;
        private boolean state;

        public int getUser_num() {
            return user_num;
        }

        public void setUser_num(int user_num) {
            this.user_num = user_num;
        }

        public int getProcess() {
            return process;
        }

        public void setProcess(int process) {
            this.process = process;
        }

        public int getOne_num() {
            return one_num;
        }

        public void setOne_num(int one_num) {
            this.one_num = one_num;
        }

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }
    }
}
