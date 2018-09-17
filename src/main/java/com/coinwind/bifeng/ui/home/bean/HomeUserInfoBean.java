package com.coinwind.bifeng.ui.home.bean;

/**
 * 首页根据机型得到信息
 */
public class HomeUserInfoBean {

    /**
     * code : 1000
     * msg : 请求成功！
     * state : true
     * data : {"msg":"创建新游客","allTopCount":2,"code":1014,"done_num":0,"today_money":66,"c_power":10,"state":true}
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
         * msg : 创建新游客  //
         * allTopCount : 2  //今日推荐总数
         * code : 1014      //相应码  1014新游客  1013旧游客
         * done_num : 0     //做过的推荐任务数
         * today_money : 66 //估值多少人民币
         * c_power : 10     //当前风力
         * state : true     //
         */

        private String msg;
        private int allTopCount;
        private int code;
        private int done_num;
        private int today_money;
        private double c_power;
        private boolean state;

        public int getIs_visit() {
            return is_visit;
        }

        public void setIs_visit(int is_visit) {
            this.is_visit = is_visit;
        }

        private int is_visit;


        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getAllTopCount() {
            return allTopCount;
        }

        public void setAllTopCount(int allTopCount) {
            this.allTopCount = allTopCount;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getDone_num() {
            return done_num;
        }

        public void setDone_num(int done_num) {
            this.done_num = done_num;
        }

        public int getToday_money() {
            return today_money;
        }

        public void setToday_money(int today_money) {
            this.today_money = today_money;
        }

        public double getC_power() {
            return c_power;
        }

        public void setC_power(double c_power) {
            this.c_power = c_power;
        }

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }
    }
}
