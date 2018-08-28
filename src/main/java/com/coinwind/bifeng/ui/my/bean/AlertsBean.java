package com.coinwind.bifeng.ui.my.bean;

import java.util.List;

/**
 * 消息通知的bean
 */
public class AlertsBean {

    /**
     * code : 1000
     * msg : 请求成功！
     * state : true
     * data : [{"read":"0","user_id":null,"b_id":null,"lrrq":"2018-07-23 11:23:37","logo":"http://coinwind.oss-cn-qingdao.aliyuncs.com/20180723112305_299.png","b_read":null,"id":"1021234347207819264","flrrq":"2018.07.23 11:23","type":"1","title":"打打打打沙袋哈师大的哈杀手","content":"打打打打沙袋哈师大的哈杀手"}]
     */

    private int code;
    private String msg;
    private boolean state;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * read : 0
         * user_id : null
         * b_id : null
         * lrrq : 2018-07-23 11:23:37
         * logo : http://coinwind.oss-cn-qingdao.aliyuncs.com/20180723112305_299.png
         * b_read : null
         * id : 1021234347207819264
         * flrrq : 2018.07.23 11:23
         * type : 1
         * title : 打打打打沙袋哈师大的哈杀手
         * content : 打打打打沙袋哈师大的哈杀手
         */

        private String read;
        private Object user_id;
        private Object b_id;
        private String lrrq;
        private String logo;
        private Object b_read;
        private String id;
        private String flrrq;
        private String type;
        private String title;
        private String content;

        public String getRead() {
            return read;
        }

        public void setRead(String read) {
            this.read = read;
        }

        public Object getUser_id() {
            return user_id;
        }

        public void setUser_id(Object user_id) {
            this.user_id = user_id;
        }

        public Object getB_id() {
            return b_id;
        }

        public void setB_id(Object b_id) {
            this.b_id = b_id;
        }

        public String getLrrq() {
            return lrrq;
        }

        public void setLrrq(String lrrq) {
            this.lrrq = lrrq;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public Object getB_read() {
            return b_read;
        }

        public void setB_read(Object b_read) {
            this.b_read = b_read;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFlrrq() {
            return flrrq;
        }

        public void setFlrrq(String flrrq) {
            this.flrrq = flrrq;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
