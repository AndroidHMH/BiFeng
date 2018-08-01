package com.coinwind.bifeng.ui.task.bean;

import java.util.List;

public class TaskIdsBean {

    /**
     * code : 1000
     * msg : 请求成功！
     * state : true
     * data : [{"user_id":"1018800438658465792","lrrq":"2018-07-17 16:06:58","logo":null,"task_title":"SDChain项目进展周报（2018年6月第4周）","task_id":"1013722832258990080","id":"1019131329259765760","type":"2","broadcast_title":"null刚刚执行了 转发任务"},{"user_id":"1018800438658465792","lrrq":"2018-07-17 16:07:00","logo":null,"task_title":"SDChain项目进展周报（2018年6月第4周）","task_id":"1013722832258990080","id":"1019131335920320512","type":"2","broadcast_title":"null刚刚执行了 转发任务"},{"user_id":"1012237225552773120","lrrq":"2018-07-19 10:03:46","logo":null,"task_title":"SDChain项目进展周报（2018年6月第4周）","task_id":"1013722832258990080","id":"1019764703640944640","type":"2","broadcast_title":"春风吹不尽刚刚执行了 转发任务"},{"user_id":"1012237225552773120","lrrq":"2018-07-19 10:56:40","logo":null,"task_title":"SDChain项目进展周报（2018年6月第4周）","task_id":"1013722832258990080","id":"1019778014243717120","type":"2","broadcast_title":"春风吹不尽刚刚执行了 转发任务"}]
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
         * user_id : 1018800438658465792
         * lrrq : 2018-07-17 16:06:58
         * logo : null
         * task_title : SDChain项目进展周报（2018年6月第4周）
         * task_id : 1013722832258990080
         * id : 1019131329259765760
         * type : 2
         * broadcast_title : null刚刚执行了 转发任务
         */

        private String user_id;
        private String lrrq;
        private Object logo;
        private String task_title;
        private String task_id;
        private String id;
        private String type;
        private String broadcast_title;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getLrrq() {
            return lrrq;
        }

        public void setLrrq(String lrrq) {
            this.lrrq = lrrq;
        }

        public Object getLogo() {
            return logo;
        }

        public void setLogo(Object logo) {
            this.logo = logo;
        }

        public String getTask_title() {
            return task_title;
        }

        public void setTask_title(String task_title) {
            this.task_title = task_title;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getBroadcast_title() {
            return broadcast_title;
        }

        public void setBroadcast_title(String broadcast_title) {
            this.broadcast_title = broadcast_title;
        }
    }
}
