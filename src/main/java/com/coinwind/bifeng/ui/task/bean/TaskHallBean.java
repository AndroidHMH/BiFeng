package com.coinwind.bifeng.ui.task.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 任务列表bean
 */
public class TaskHallBean {

    /**
     * code : 1000
     * msg : 请求成功！
     * state : true
     * data : {"super":0,"new":0,"is_visit":1,"high":0,"middle":0,"state":true}
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
         * super : 0    //超级任务
         * new : 0      //创世任务
         * is_visit : 1
         * high : 0     //高级任务
         * middle : 0   //中级任务
         * state : true
         */

        @SerializedName("super")
        private int superX;
        @SerializedName("new")
        private int newX;
        private int is_visit;
        private int high;
        private int middle;
        private boolean state;
        private int code;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getSuperX() {
            return superX;
        }

        public void setSuperX(int superX) {
            this.superX = superX;
        }

        public int getNewX() {
            return newX;
        }

        public void setNewX(int newX) {
            this.newX = newX;
        }

        public int getIs_visit() {
            return is_visit;
        }

        public void setIs_visit(int is_visit) {
            this.is_visit = is_visit;
        }

        public int getHigh() {
            return high;
        }

        public void setHigh(int high) {
            this.high = high;
        }

        public int getMiddle() {
            return middle;
        }

        public void setMiddle(int middle) {
            this.middle = middle;
        }

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }
    }
}
