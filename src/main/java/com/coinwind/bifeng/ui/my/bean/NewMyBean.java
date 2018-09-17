package com.coinwind.bifeng.ui.my.bean;

/**
 * 我的页面的bean
 */
public class NewMyBean {

    /**
     * code : 1000
     * msg : 请求成功！
     * state : true
     * data : {"current_cc":null,"is_visit":0,"checkNum":0,"nick_name":"hahahahah","head_img":"hahahahah","nowNum":3,"receiveNum":0,"state":true,"purse_addr":"8be39627821272e63e4ec94e84317c2610dc44a36558011b25515c37518b54a2","endNum":0,"expireNum":0}
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
         * current_cc : null        //≈人民币
         * is_visit : 0             //0  正式用户      1  游客
         * checkNum : 0             //审核中
         * nick_name : hahahahah    //昵称
         * head_img : hahahahah     //头像
         * nowNum : 3               //进行中
         * receiveNum : 0           //已接收
         * state : true             //请求状态
         * purse_addr : 8be39627821272e63e4ec94e84317c2610dc44a36558011b25515c37518b54a2    //钱包
         * endNum : 0               //已结束
         * expireNum : 0            //已过期
         */

        private Float current_cc;
        private int is_visit;
        private int checkNum;
        private String nick_name;
        private String head_img;
        private int nowNum;
        private int receiveNum;
        private boolean state;
        private String purse_addr;
        private int endNum;
        private int expireNum;
        private int code;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public Float getCurrent_cc() {
            return current_cc;
        }

        public void setCurrent_cc(Float current_cc) {
            this.current_cc = current_cc;
        }

        public int getIs_visit() {
            return is_visit;
        }

        public void setIs_visit(int is_visit) {
            this.is_visit = is_visit;
        }

        public int getCheckNum() {
            return checkNum;
        }

        public void setCheckNum(int checkNum) {
            this.checkNum = checkNum;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public int getNowNum() {
            return nowNum;
        }

        public void setNowNum(int nowNum) {
            this.nowNum = nowNum;
        }

        public int getReceiveNum() {
            return receiveNum;
        }

        public void setReceiveNum(int receiveNum) {
            this.receiveNum = receiveNum;
        }

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }

        public String getPurse_addr() {
            return purse_addr;
        }

        public void setPurse_addr(String purse_addr) {
            this.purse_addr = purse_addr;
        }

        public int getEndNum() {
            return endNum;
        }

        public void setEndNum(int endNum) {
            this.endNum = endNum;
        }

        public int getExpireNum() {
            return expireNum;
        }

        public void setExpireNum(int expireNum) {
            this.expireNum = expireNum;
        }
    }
}
