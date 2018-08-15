package com.coinwind.bifeng.ui.task.bean;

/**
 * 获取分享图片的bean
 */
public class ShareImgBean {

    /**
     * code : 1000
     * msg : 请求成功！
     * state : true
     * data : {"success":"1","width":"asd","fpath":"http://coinwind.oss-cn-qingdao.aliyuncs.com/shareBaseImg/20180803140326_114.png","error":0,"title":"shareBaseImg/20180803140326_114.png","url":"http://coinwind.oss-cn-qingdao.aliyuncs.com/shareBaseImg/20180803140326_114.png"}
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
         * success : 1
         * width : asd
         * fpath : http://coinwind.oss-cn-qingdao.aliyuncs.com/shareBaseImg/20180803140326_114.png
         * error : 0
         * title : shareBaseImg/20180803140326_114.png
         * url : http://coinwind.oss-cn-qingdao.aliyuncs.com/shareBaseImg/20180803140326_114.png
         */

        private String success;
        private String width;
        private String fpath;
        private int error;
        private String title;
        private String url;

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getFpath() {
            return fpath;
        }

        public void setFpath(String fpath) {
            this.fpath = fpath;
        }

        public int getError() {
            return error;
        }

        public void setError(int error) {
            this.error = error;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
