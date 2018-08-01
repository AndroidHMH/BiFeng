package com.coinwind.bifeng.config;

/**
 * 统一管理Url
 */
public class Urls {
    //请求地址
//    public static final String BASE_URL = "https://m.coinwind.com/";
    public static final String BASE_URL = "http://47.105.33.156:8080/";

    //首页轮播图
    public static final String HOME_BANNER = "bifeng/api/banners";

    //首页广播
    public static final String HOME_GUANG_BO = "bifeng/zc/indexBroadcast";

    //首页抢任务
    public static final String HOME_QINAG = "bifeng/api/snatchTask";

    //分页加载
    public static final String HOME_LIST = "bifeng/zc/indexFineTask";

    //完成任务id
    public static final String TASK_ID = "zc/broadcastByTaskId";

    //任务大厅列表
    public static final String TASK_CONTENT = "/bifeng/zc/queryData";

    //获取验证码
    public static final String LOGIN_GET_CODE = "bifeng/user/shortMsgCode";

    //上传图片
    public static final String UPDATE_IMGS = "bifeng/upload/imgOss";

    //提交任务
    public static final String SUBMIT_TASK = "bifeng/zc/subTask";

    //用户名密码登录接口
    public static final String PSW_LOGIN = "bifeng/user/userLogin";

    //手机号登录接口
    public static final String PHONE_LOGIN = "bifeng/user/loginByPhone";

    //更改用户信息借口
    public static final String CHANGE_MSG = "bifeng/api/updateUserInfo";

    //获取用户钱包
    public static final String SELECT_CC = "bifeng/zc/myPurse";
}
