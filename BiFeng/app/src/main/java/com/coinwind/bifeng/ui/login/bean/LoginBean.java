package com.coinwind.bifeng.ui.login.bean;

import java.io.Serializable;

public class LoginBean {

    /**
     * code : 1000
     * msg : 请求成功！
     * state : true
     * data : {"msg":"登陆成功！","sign":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1MzMwODg3Mzc5MjcsInBheWxvYWQiOiJcIjEwMjE2Mzg2NzY2ODMxNjE2MDBcIiJ9.CoXiDCZLKWZHeMTCTv4st4Am8LnowrMifje9l5Z9xMw","state":true,"user":{"from_type":1,"namecard_url":null,"gz_type":null,"logo_url":null,"openid":null,"current_css":133,"head_img":"http://coinwind.oss-cn-qingdao.aliyuncs.com/20180730145242_90.png","gz_name":null,"type":"1","qiye_info":"二vrgrtgverf4e","auth_flag":null,"c2_fencheng":null,"c1_fencheng":null,"password":"C8C66B3590C8174E","last_check_type":"2","phone":"15711453382","nick_name":"157","lrrq":"2018-07-24 14:10:16","nickname":"15711453382","headimgurl":null,"id":"1021638676683161600","last_check_in":"2018-07-30 11:07:29"}}
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
                    ", sign='" + sign + '\'' +
                    ", state=" + state +
                    ", user=" + user +
                    '}';
        }

        /**
         * msg : 登陆成功！
         * sign : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1MzMwODg3Mzc5MjcsInBheWxvYWQiOiJcIjEwMjE2Mzg2NzY2ODMxNjE2MDBcIiJ9.CoXiDCZLKWZHeMTCTv4st4Am8LnowrMifje9l5Z9xMw
         * state : true
         * user : {"from_type":1,"namecard_url":null,"gz_type":null,"logo_url":null,"openid":null,"current_css":133,"head_img":"http://coinwind.oss-cn-qingdao.aliyuncs.com/20180730145242_90.png","gz_name":null,"type":"1","qiye_info":"二vrgrtgverf4e","auth_flag":null,"c2_fencheng":null,"c1_fencheng":null,"password":"C8C66B3590C8174E","last_check_type":"2","phone":"15711453382","nick_name":"157","lrrq":"2018-07-24 14:10:16","nickname":"15711453382","headimgurl":null,"id":"1021638676683161600","last_check_in":"2018-07-30 11:07:29"}
         */

        private String msg;
        private String sign;
        private boolean state;
        private UserBean user;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean implements Serializable {
            /**
             * from_type : 1
             * namecard_url : null
             * gz_type : null
             * logo_url : null
             * openid : null
             * current_css : 133
             * head_img : http://coinwind.oss-cn-qingdao.aliyuncs.com/20180730145242_90.png
             * gz_name : null
             * type : 1
             * qiye_info : 二vrgrtgverf4e
             * auth_flag : null
             * c2_fencheng : null
             * c1_fencheng : null
             * password : C8C66B3590C8174E
             * last_check_type : 2
             * phone : 15711453382
             * nick_name : 157
             * lrrq : 2018-07-24 14:10:16
             * nickname : 15711453382
             * headimgurl : null
             * id : 1021638676683161600
             * last_check_in : 2018-07-30 11:07:29
             */

            private int from_type;
            private Object namecard_url;
            private Object gz_type;
            private Object logo_url;
            private Object openid;
            private int current_css;
            private String head_img;
            private Object gz_name;
            private String type;
            private String qiye_info;
            private Object auth_flag;
            private Object c2_fencheng;
            private Object c1_fencheng;
            private String password;
            private String last_check_type;
            private String phone;
            private String nick_name;
            private String lrrq;
            private String nickname;
            private Object headimgurl;
            private String id;
            private String last_check_in;

            public int getFrom_type() {
                return from_type;
            }

            public void setFrom_type(int from_type) {
                this.from_type = from_type;
            }

            public Object getNamecard_url() {
                return namecard_url;
            }

            public void setNamecard_url(Object namecard_url) {
                this.namecard_url = namecard_url;
            }

            public Object getGz_type() {
                return gz_type;
            }

            public void setGz_type(Object gz_type) {
                this.gz_type = gz_type;
            }

            public Object getLogo_url() {
                return logo_url;
            }

            public void setLogo_url(Object logo_url) {
                this.logo_url = logo_url;
            }

            public Object getOpenid() {
                return openid;
            }

            public void setOpenid(Object openid) {
                this.openid = openid;
            }

            public int getCurrent_css() {
                return current_css;
            }

            public void setCurrent_css(int current_css) {
                this.current_css = current_css;
            }

            public String getHead_img() {
                return head_img;
            }

            public void setHead_img(String head_img) {
                this.head_img = head_img;
            }

            public Object getGz_name() {
                return gz_name;
            }

            public void setGz_name(Object gz_name) {
                this.gz_name = gz_name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getQiye_info() {
                return qiye_info;
            }

            public void setQiye_info(String qiye_info) {
                this.qiye_info = qiye_info;
            }

            public Object getAuth_flag() {
                return auth_flag;
            }

            public void setAuth_flag(Object auth_flag) {
                this.auth_flag = auth_flag;
            }

            public Object getC2_fencheng() {
                return c2_fencheng;
            }

            public void setC2_fencheng(Object c2_fencheng) {
                this.c2_fencheng = c2_fencheng;
            }

            public Object getC1_fencheng() {
                return c1_fencheng;
            }

            public void setC1_fencheng(Object c1_fencheng) {
                this.c1_fencheng = c1_fencheng;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getLast_check_type() {
                return last_check_type;
            }

            public void setLast_check_type(String last_check_type) {
                this.last_check_type = last_check_type;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public String getLrrq() {
                return lrrq;
            }

            public void setLrrq(String lrrq) {
                this.lrrq = lrrq;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public Object getHeadimgurl() {
                return headimgurl;
            }

            public void setHeadimgurl(Object headimgurl) {
                this.headimgurl = headimgurl;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLast_check_in() {
                return last_check_in;
            }

            public void setLast_check_in(String last_check_in) {
                this.last_check_in = last_check_in;
            }
        }
    }
}
