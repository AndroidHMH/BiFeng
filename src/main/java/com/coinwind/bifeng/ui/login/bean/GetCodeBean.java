package com.coinwind.bifeng.ui.login.bean;

import java.util.List;

/**
 * 获取验证码的bean
 */
public class GetCodeBean {

    /**
     * code : 1000
     * msg : 请求成功！
     * state : true
     * data : {"inviteDataList":[],"inviteCode":"530079","state":true}
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
         * inviteDataList : []
         * inviteCode : 530079
         * state : true
         */

        private String inviteCode;
        private boolean state;
        private List<?> inviteDataList;
        private int code;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getInviteCode() {
            return inviteCode;
        }

        public void setInviteCode(String inviteCode) {
            this.inviteCode = inviteCode;
        }

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }

        public List<?> getInviteDataList() {
            return inviteDataList;
        }

        public void setInviteDataList(List<?> inviteDataList) {
            this.inviteDataList = inviteDataList;
        }
    }
}
