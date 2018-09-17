package com.coinwind.bifeng.ui.my.bean;

import java.util.List;

/**
 * 我的任务bean
 */
public class MyTaskBean {

    /**
     * code : 1000
     * msg : 请求成功！
     * state : true
     * data : {"flag":"3","state":true,"list":[{"explain":"哦哦哦","no_pass_msg":null,"css":27,"assess":null,"answer_content":null,"user_id":"1040999041795620864","pass":"0","lrrq":"2018-09-16 04:28:05","task_id":"1029249258286481408","id":"1041061107348799488","is_pass":null}]}
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
         * flag : 3
         * state : true
         * list : [{"explain":"哦哦哦","no_pass_msg":null,"css":27,"assess":null,"answer_content":null,"user_id":"1040999041795620864","pass":"0","lrrq":"2018-09-16 04:28:05","task_id":"1029249258286481408","id":"1041061107348799488","is_pass":null}]
         */

        private String flag;
        private boolean state;
        private List<ListBean> list;
        private int code;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String sub_time;
            private String title;

            public String getSub_time() {
                return sub_time;
            }

            public void setSub_time(String sub_time) {
                this.sub_time = sub_time;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
