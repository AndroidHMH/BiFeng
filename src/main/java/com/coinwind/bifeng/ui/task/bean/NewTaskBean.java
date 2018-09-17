package com.coinwind.bifeng.ui.task.bean;

import java.util.List;

public class NewTaskBean {

    /**
     * code : 1000
     * msg : 请求成功！
     * state : true
     * data : {"is_visit":1,"c_power":10,"mustData":[{"is_done":0,"n_state":1,"img_url":null,"c_power":10,"id":"100","type":1,"url":null,"info":"绑定手机号"},{"is_done":0,"n_state":1,"img_url":null,"c_power":50,"id":"101","type":2,"url":null,"info":"阅读新手任务教程"},{"is_done":0,"n_state":1,"img_url":null,"c_power":5,"id":"102","type":3,"url":null,"info":"填写昵称"},{"is_done":0,"n_state":1,"img_url":null,"c_power":3,"id":"103","type":4,"url":null,"info":"上传头像"},{"is_done":0,"n_state":1,"img_url":null,"c_power":10,"id":"104","type":5,"url":null,"info":"创建钱包"}],"choiceDate":[{"is_done":0,"n_state":2,"img_url":null,"c_power":10,"id":"105","type":6,"url":null,"info":"关注公众号"},{"is_done":0,"n_state":2,"img_url":null,"c_power":10,"id":"106","type":7,"url":null,"info":"绑定微信"}],"state":true}
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
         * is_visit : 1
         * c_power : 10
         * mustData : [{"is_done":0,"n_state":1,"img_url":null,"c_power":10,"id":"100","type":1,"url":null,"info":"绑定手机号"},{"is_done":0,"n_state":1,"img_url":null,"c_power":50,"id":"101","type":2,"url":null,"info":"阅读新手任务教程"},{"is_done":0,"n_state":1,"img_url":null,"c_power":5,"id":"102","type":3,"url":null,"info":"填写昵称"},{"is_done":0,"n_state":1,"img_url":null,"c_power":3,"id":"103","type":4,"url":null,"info":"上传头像"},{"is_done":0,"n_state":1,"img_url":null,"c_power":10,"id":"104","type":5,"url":null,"info":"创建钱包"}]
         * choiceDate : [{"is_done":0,"n_state":2,"img_url":null,"c_power":10,"id":"105","type":6,"url":null,"info":"关注公众号"},{"is_done":0,"n_state":2,"img_url":null,"c_power":10,"id":"106","type":7,"url":null,"info":"绑定微信"}]
         * state : true
         */

        private int is_visit;
        private double c_power;
        private boolean state;
        private List<MustDataBean> mustData;
        private List<MustDataBean> choiceDate;
        private int code;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getIs_visit() {
            return is_visit;
        }

        public void setIs_visit(int is_visit) {
            this.is_visit = is_visit;
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

        public List<MustDataBean> getMustData() {
            return mustData;
        }

        public void setMustData(List<MustDataBean> mustData) {
            this.mustData = mustData;
        }

        public List<MustDataBean> getChoiceDate() {
            return choiceDate;
        }

        public void setChoiceDate(List<MustDataBean> choiceDate) {
            this.choiceDate = choiceDate;
        }

        public static class MustDataBean {
            /**
             * is_done : 0                  //是否做过    0未做   1做过
             * n_state : 1                  //1是风力列表(必做任务)  2是风速列表(高风速)
             * img_url : null               //图片地址
             * c_power : 10                 //提升的风力
             * id : 100                     //任务id
             * type : 1                     //
             * url : null                   //2级页面的h5地址
             * info : 绑定手机号             //任务名称
             * c_speed:                     //风速值
             * is_use:                      //是否开放  0为开放  1开放
             */

            private int is_done;
            private int n_state;
            private String img_url;
            private double c_power;
            private String id;
            private int type;
            private String url;
            private String info;
            private int is_use;
            private double c_speed;

            public int getIs_use() {
                return is_use;
            }

            public void setIs_use(int is_use) {
                this.is_use = is_use;
            }

            public double getC_speed() {
                return c_speed;
            }

            public void setC_speed(double c_speed) {
                this.c_speed = c_speed;
            }

            public int getIs_done() {
                return is_done;
            }

            public void setIs_done(int is_done) {
                this.is_done = is_done;
            }

            public int getN_state() {
                return n_state;
            }

            public void setN_state(int n_state) {
                this.n_state = n_state;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public double getC_power() {
                return c_power;
            }

            public void setC_power(double c_power) {
                this.c_power = c_power;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }
        }

    }
}
