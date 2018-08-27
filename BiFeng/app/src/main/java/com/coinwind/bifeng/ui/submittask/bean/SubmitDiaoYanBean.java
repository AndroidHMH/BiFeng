package com.coinwind.bifeng.ui.submittask.bean;

/**
 * 调研任务的bean
 */
public class SubmitDiaoYanBean {

    /**
     * code : 403
     * msg : 403 登录失效
     * state : false
     * data : {"code":4031,"emsg":"无签名","state":false}
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
         * code : 4031
         * emsg : 无签名
         * state : false
         */

        private int code;
        private String emsg;
        private boolean state;
        private String isPass;

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

        public String getIsPass() {
            return isPass;
        }

        public void setIsPass(String isPass) {
            this.isPass = isPass;
        }
    }
}
