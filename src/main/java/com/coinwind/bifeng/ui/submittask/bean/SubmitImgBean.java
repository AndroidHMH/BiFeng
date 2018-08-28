package com.coinwind.bifeng.ui.submittask.bean;
/**
 *提交任务配图的bean
 */
public class SubmitImgBean {

    @Override
    public String toString() {
        return "SubmitImgBean{" +
                "success='" + success + '\'' +
                ", width='" + width + '\'' +
                ", fpath='" + fpath + '\'' +
                ", error=" + error +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    /**
     * success : 1
     * width : asd
     * fpath : http://coinwind.oss-cn-qingdao.aliyuncs.com/20180730161541_520.jpg
     * error : 0
     * title : 20180730161541_520.jpg
     * url : http://coinwind.oss-cn-qingdao.aliyuncs.com/20180730161541_520.jpg
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
