package com.coinwind.bifeng.ui.home.bean;

/**
 * 签到的bean
 */
public class QianDaoBean {

    @Override
    public String toString() {
        return "QianDaoBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", state=" + state +
                ", data=" + data +
                '}';
    }

    /**
     * code : 1000
     * msg : 请求成功！
     * state : true
     * data : {"msg":"恭喜签到成功，+5css","checkType":1,"css":5,"state":true}
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
        @Override
        public String toString() {
            return "DataBean{" +
                    "msg='" + msg + '\'' +
                    ", checkType='" + checkType + '\'' +
                    ", css=" + css +
                    ", state=" + state +
                    '}';
        }

        /**
         * msg : 恭喜签到成功，+5css
         * checkType : 1
         * css : 5
         * state : true
         */

        private String msg;
        private String checkType;
        private int css;
        private boolean state;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getCheckType() {
            return checkType;
        }

        public void setCheckType(String checkType) {
            this.checkType = checkType;
        }

        public int getCss() {
            return css;
        }

        public void setCss(int css) {
            this.css = css;
        }

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }
    }
}
