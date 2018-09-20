package com.coinwind.bifeng.ui.bindphonenumber.entity;

/**
 * 绑定手机号的entity
 */
public class SendBindPhoneNumberEntity {

    /**
     * code : 1000
     * msg : 请求成功！
     * state : true
     * data : {"sign":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1Mzc0NDY5MzUyNjUsInBheWxvYWQiOiJcIjEwNDIzOTEwMjE5NTE5MDk4ODhcIiJ9.iZRnO2N2v0Wis6AGfF8ZZw-fRmWzrnG9L8ygH6sqJdk","state":true,"userId":"1042391021951909888"}
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
         * sign : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1Mzc0NDY5MzUyNjUsInBheWxvYWQiOiJcIjEwNDIzOTEwMjE5NTE5MDk4ODhcIiJ9.iZRnO2N2v0Wis6AGfF8ZZw-fRmWzrnG9L8ygH6sqJdk
         * state : true
         * userId : 1042391021951909888
         */

        private String sign;
        private boolean state;
        private String userId;
        private int code;
        private String msg;

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

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
