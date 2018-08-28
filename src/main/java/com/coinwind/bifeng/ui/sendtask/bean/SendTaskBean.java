package com.coinwind.bifeng.ui.sendtask.bean;

/**
 * 发布任务的bean
 */
public class SendTaskBean {

    @Override
    public String toString() {
        return "SendTaskBean{" +
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
     * data : {"msg":"剩余css不足","state":false}
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
                    ", state=" + state +
                    '}';
        }

        /**
         * msg : 剩余css不足
         * state : false
         */

        private String msg;
        private boolean state;

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
    }
}
