package com.coinwind.bifeng.config;

/**
 * 统一管理Url
 */
public class Urls {
    //请求地址
    public static final String BASE_URL = "https://m.coinwind.com/";
//    public static final String BASE_URL = "http://47.105.33.156:8080/";

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

    //获取分享图片
    public static final String SHARE_IMG = "bifeng/zc/shareTaskImg";

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

    //获取我的任务列表
    public static final String MY_TASK_LIST = "bifeng/zc/myTaskData";

    //获取分享的图片
    public static final String SHARE_IMG_HTML = "https://m.coinwind.com/share/receive.html";

    //分享任务详情链接
    public static final String SHARE_LINK_URL = "https://m.coinwind.com/share/details.html";

    //获取信息列表
    public static final String ALERTS_LIST = "bifeng/msg/list";

    //搜索
    public static final String SEARCH_FOR = "zc/searchData";

    //认证
    public static final String REN_ZHENG = "bifeng/zc/authUserInfo";

    //修改密码
    public static final String CHANGE_PSW = "bifeng/user/getBackPassword";

    //签到
    public static final String QIAN_DAO = "bifeng/zc/newCheckIn";

    //是否登录
    public static final String IS_LOGIN = "bifeng/user/isLogin";

    //邀请链接
    public static final String YAO_QING_URL = "https://m.coinwind.com/share/haoyou.html";

    //发布任务
    public static final String SEND_TASK = "bifeng/zc/publishTask";

    //提交答题调研任务
    public static final String SUBMIT_DA_TI = "bifeng/zc/subTaskByType";

    //个人详情信息
    public static final String MY_INFO = "bifeng/frame/myBaseInfo";

    //首页基本信息
    public static final String HOME_INFO = "bifeng/frame/indexBaseInfo";

    //首页根据机型获取信息
    public static final String HOME_USER_INFO = "bifeng/frame/indexInfoByMTab";

    //获取创世任务列表
    public static final String NEW_TASK = "bifeng/frame/taskNewList";

    //首页cc集合
    public static final String HOME_CC_LIST = "bifeng/frame/ccList";

    //任务进度
    public static final String TASK_PROGRESS = "bifeng/frame/taskList";

    //领取cc
    public static final String RECEIVE_CC = "bifeng/frame/addFreeCC";

    //上传头像
    public static final String HEAD_IMG_TASK = "bifeng/frame/subTaskNew";

    //我的任务
    public static final String MY_TASK = "bifeng/frame/myTaskList";

    //得到邀请码
    public static final String GET_CODE = "bifeng/user/getInviteCode";

    //新版资产管理
    public static final String LOAD_WALLET = "bifeng/frame/purseManager";

    //矿产记录
    public static final String RECORD = "bifeng/frame/freeCCLog";

    //绑定手机号
    public static final String BIND_PHONE = "bifeng/frame/subTaskNew";


}
