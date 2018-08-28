package com.coinwind.bifeng.ui.home.bean;

import java.util.List;

/**
 * 首页轮播图的bean
 */
public class HomeBannerBean {


    /**
     * code : 1000
     * msg : 请求成功！
     * state : true
     * data : [{"outurl":null,"note":null,"is_hidden":"0","task_id":"1010119102422843392","id":"1009042254951612416","sort":1,"type":null,"url":"http://coinwind.oss-cn-qingdao.aliyuncs.com/20180716135637_304.jpg"},{"outurl":null,"note":null,"is_hidden":"0","task_id":null,"id":"1020149728714686464","sort":2,"type":null,"url":"http://coinwind.oss-cn-qingdao.aliyuncs.com/20180720113330_257.jpg"},{"outurl":null,"note":null,"is_hidden":"0","task_id":null,"id":"1009043075349086208","sort":3,"type":null,"url":"http://coinwind.oss-cn-qingdao.aliyuncs.com/20180716135713_605.jpg"}]
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
         * outurl : null
         * note : null
         * is_hidden : 0
         * task_id : 1010119102422843392
         * id : 1009042254951612416
         * sort : 1
         * type : null
         * url : http://coinwind.oss-cn-qingdao.aliyuncs.com/20180716135637_304.jpg
         */

        private Object outurl;
        private Object note;
        private String is_hidden;
        private String task_id;
        private String id;
        private int sort;
        private Object type;
        private String url;

        public Object getOuturl() {
            return outurl;
        }

        public void setOuturl(Object outurl) {
            this.outurl = outurl;
        }

        public Object getNote() {
            return note;
        }

        public void setNote(Object note) {
            this.note = note;
        }

        public String getIs_hidden() {
            return is_hidden;
        }

        public void setIs_hidden(String is_hidden) {
            this.is_hidden = is_hidden;
        }

        public String getTask_id() {
            return task_id;
        }

        public void setTask_id(String task_id) {
            this.task_id = task_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
