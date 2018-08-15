package com.coinwind.bifeng.ui.home.bean;

import java.util.List;

/**
 * 首页广播的bean
 */
public class GuangBoBean {

    /**
     * code : 1000
     * msg : 请求成功！
     * state : true
     * data : [{"user_id":"57","lrrq":"2018-07-23 15:17:13","logo":"http://coinwind.oss-cn-qingdao.aliyuncs.com/20180723103416_23.jpg","task_title":"DEx.top 将上线Maverick Chain(MVC)","task_id":"1021293133943537664","id":"1021293133956120576","type":"1","broadcast_title":"小白同学刚刚发布了 转发任务"},{"user_id":"58","lrrq":"2018-07-23 15:07:17","logo":"http://coinwind.oss-cn-qingdao.aliyuncs.com/20180723103452_966.jpg","task_title":"hubox送你新人大礼包，注册瓜分15亿HAB糖果","task_id":"1021290635753160704","id":"1021290635765743616","type":"1","broadcast_title":"阿星刚刚发布了 注册任务"},{"user_id":"57","lrrq":"2018-07-23 14:56:47","logo":"http://coinwind.oss-cn-qingdao.aliyuncs.com/20180723103416_23.jpg","task_title":"DEx.top于7月23日13时上线 PAI、EDR","task_id":"1021287995329740800","id":"1021287995342323712","type":"1","broadcast_title":"小白同学刚刚发布了 转发任务"},{"user_id":"56","lrrq":"2018-07-23 14:48:35","logo":"http://coinwind.oss-cn-qingdao.aliyuncs.com/20180723103345_651.jpg","task_title":" CoinTiger 2018年第二季度TCH回购公告","task_id":"1021285931631509504","id":"1021285931644092416","type":"1","broadcast_title":"潇洒哥刚刚发布了 转发任务"},{"user_id":"56","lrrq":"2018-07-23 14:44:37","logo":"http://coinwind.oss-cn-qingdao.aliyuncs.com/20180723103345_651.jpg","task_title":"CoinTiger携手百度度宇宙打造的【区块链星球】正式上线","task_id":"1021284930174320640","id":"1021284930186903552","type":"1","broadcast_title":"潇洒哥刚刚发布了 转发任务"},{"user_id":"55","lrrq":"2018-07-23 10:27:44","logo":"http://coinwind.oss-cn-qingdao.aliyuncs.com/20180723102522_47.jpg","task_title":"币团网今日12时正式开放交易 平台积分BT同步上线","task_id":"1021220284776054784","id":"1021220284788637696","type":"1","broadcast_title":"团子刚刚发布了 转发任务"},{"user_id":"1018798340353032192","lrrq":"2018-07-23 10:24:27","logo":null,"task_title":" Huobi Token 升级为火币全球生态通证","task_id":"1011215003916697600","id":"1021219459815178240","type":"2","broadcast_title":"15711453382刚刚执行了 转发任务"},{"user_id":"1018798340353032192","lrrq":"2018-07-23 10:24:27","logo":null,"task_title":" Huobi Token 升级为火币全球生态通证","task_id":"1011215003916697600","id":"1021219459995533312","type":"2","broadcast_title":"15711453382刚刚执行了 转发任务"},{"user_id":"1","lrrq":"2018-07-23 10:18:23","logo":"http://coinwind.oss-cn-qingdao.aliyuncs.com/20180720150059_625.jpg","task_title":"ZB.com关于开放第二期投票上币项目PDX充值及交易的公告","task_id":"1021217932320964608","id":"1021217932333547520","type":"1","broadcast_title":"一颗丸子头刚刚发布了 转发任务"},{"user_id":"1","lrrq":"2018-07-23 10:14:17","logo":"http://coinwind.oss-cn-qingdao.aliyuncs.com/20180720150059_625.jpg","task_title":"ZB.com关于开启\u201cSLT交易大赛\u201d的公告","task_id":"1021216899171614720","id":"1021216899184197632","type":"1","broadcast_title":"一颗丸子头刚刚发布了 转发任务"}]
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
         * user_id : 57
         * lrrq : 2018-07-23 15:17:13
         * logo : http://coinwind.oss-cn-qingdao.aliyuncs.com/20180723103416_23.jpg
         * task_title : DEx.top 将上线Maverick Chain(MVC)
         * task_id : 1021293133943537664
         * id : 1021293133956120576
         * type : 1
         * broadcast_title : 小白同学刚刚发布了 转发任务
         */

        private String user_id;
        private String lrrq;
        private String logo;
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

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
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
