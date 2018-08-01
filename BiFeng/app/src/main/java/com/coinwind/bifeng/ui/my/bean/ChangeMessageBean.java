package com.coinwind.bifeng.ui.my.bean;

public class ChangeMessageBean {

    /**
     * code : 1000
     * msg : 请求成功！
     * state : true
     * data : {"state":true,"user":{"from_type":null,"namecard_url":null,"gz_type":null,"logo_url":null,"openid":"oQY-o5Z_8iofVvtqJre-ShRAU7ec","current_css":98,"head_img":"https://wx.qlogo.cn/mmopen/vi_32/nhcgNDK7hHVqfibB1IcD1sPKZU7Hxz7gWPBcx7ALdfSTSUsibJ3Pt313cTtGOUMJdTIYljp5A6JiafaiaY1n9N8Xzw/132","gz_name":null,"type":"1","qiye_info":null,"auth_flag":null,"c2_fencheng":null,"c1_fencheng":null,"password":null,"last_check_type":null,"phone":null,"nick_name":"文文","lrrq":null,"nickname":"区块链之火","headimgurl":"https://wx.qlogo.cn/mmopen/vi_32/nhcgNDK7hHVqfibB1IcD1sPKZU7Hxz7gWPBcx7ALdfSTSUsibJ3Pt313cTtGOUMJdTIYljp5A6JiafaiaY1n9N8Xzw/132","id":"1013989360678207488","last_check_in":"2018-07-03 11:35:32"}}
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
         * state : true
         * user : {"from_type":null,"namecard_url":null,"gz_type":null,"logo_url":null,"openid":"oQY-o5Z_8iofVvtqJre-ShRAU7ec","current_css":98,"head_img":"https://wx.qlogo.cn/mmopen/vi_32/nhcgNDK7hHVqfibB1IcD1sPKZU7Hxz7gWPBcx7ALdfSTSUsibJ3Pt313cTtGOUMJdTIYljp5A6JiafaiaY1n9N8Xzw/132","gz_name":null,"type":"1","qiye_info":null,"auth_flag":null,"c2_fencheng":null,"c1_fencheng":null,"password":null,"last_check_type":null,"phone":null,"nick_name":"文文","lrrq":null,"nickname":"区块链之火","headimgurl":"https://wx.qlogo.cn/mmopen/vi_32/nhcgNDK7hHVqfibB1IcD1sPKZU7Hxz7gWPBcx7ALdfSTSUsibJ3Pt313cTtGOUMJdTIYljp5A6JiafaiaY1n9N8Xzw/132","id":"1013989360678207488","last_check_in":"2018-07-03 11:35:32"}
         */

        private boolean state;
        private UserBean user;

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

        public static class UserBean {
            /**
             * from_type : null
             * namecard_url : null
             * gz_type : null
             * logo_url : null
             * openid : oQY-o5Z_8iofVvtqJre-ShRAU7ec
             * current_css : 98
             * head_img : https://wx.qlogo.cn/mmopen/vi_32/nhcgNDK7hHVqfibB1IcD1sPKZU7Hxz7gWPBcx7ALdfSTSUsibJ3Pt313cTtGOUMJdTIYljp5A6JiafaiaY1n9N8Xzw/132
             * gz_name : null
             * type : 1
             * qiye_info : null
             * auth_flag : null
             * c2_fencheng : null
             * c1_fencheng : null
             * password : null
             * last_check_type : null
             * phone : null
             * nick_name : 文文
             * lrrq : null
             * nickname : 区块链之火
             * headimgurl : https://wx.qlogo.cn/mmopen/vi_32/nhcgNDK7hHVqfibB1IcD1sPKZU7Hxz7gWPBcx7ALdfSTSUsibJ3Pt313cTtGOUMJdTIYljp5A6JiafaiaY1n9N8Xzw/132
             * id : 1013989360678207488
             * last_check_in : 2018-07-03 11:35:32
             */

            private Object from_type;
            private Object namecard_url;
            private Object gz_type;
            private Object logo_url;
            private String openid;
            private int current_css;
            private String head_img;
            private Object gz_name;
            private String type;
            private Object qiye_info;
            private Object auth_flag;
            private Object c2_fencheng;
            private Object c1_fencheng;
            private Object password;
            private Object last_check_type;
            private Object phone;
            private String nick_name;
            private Object lrrq;
            private String nickname;
            private String headimgurl;
            private String id;
            private String last_check_in;

            public Object getFrom_type() {
                return from_type;
            }

            public void setFrom_type(Object from_type) {
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

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
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

            public Object getQiye_info() {
                return qiye_info;
            }

            public void setQiye_info(Object qiye_info) {
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

            public Object getPassword() {
                return password;
            }

            public void setPassword(Object password) {
                this.password = password;
            }

            public Object getLast_check_type() {
                return last_check_type;
            }

            public void setLast_check_type(Object last_check_type) {
                this.last_check_type = last_check_type;
            }

            public Object getPhone() {
                return phone;
            }

            public void setPhone(Object phone) {
                this.phone = phone;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public Object getLrrq() {
                return lrrq;
            }

            public void setLrrq(Object lrrq) {
                this.lrrq = lrrq;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getHeadimgurl() {
                return headimgurl;
            }

            public void setHeadimgurl(String headimgurl) {
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
