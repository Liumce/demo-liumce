package net.hw.application.util;

public class Constant {

    public static final int CALL_BACK_HANDLER_SUCCESS = 100;
    public static final int CALL_BACK_HANDLER_FAIL = 101;
    //appid
    //请同时修改  androidmanifest.xml里面，.PayActivityd里的属性<data android:scheme="wxb4ba3c02aa476ea1"/>为新设置的appid
  public static final String APP_ID = "wxf2f565574a968187";

 public static final String DOMAIN = "http://115.28.177.178:807";
//public static final String DOMAIN = "http://xiaxun.eicp.net:18080";
    //获取区域列表
    //http://139.196.102.210:807/LaoRenDangAn.aspx?action=get&parameter=Id&value=30e88464-5eb4-4193-93ab-56580cf4f06e
    public static final String GET_OLDER_DANGAN = DOMAIN +"/LaoRenDangAn.aspx";
    public static final String GET_HULi_DANGAN = DOMAIN +"/HuLiDengJi.aspx";
    public static final String GET_CAR_LIST_ACTION = DOMAIN +"/app/car/list";
    public static final String GET_LOGIN_ACTION = DOMAIN+"/FuWuRenYuan.aspx" ;
    public static final String GET_QINSHU_LOGIN_ACTION = DOMAIN+"/YL_User.aspx" ;
    public static final String GET_SERVICE_SUPLLY = DOMAIN+"/FuWuQingQiu.aspx" ;
    public static final String GET_LOGIN_ACTION_BY_QIN_SHU = DOMAIN+"/YL_User.aspx" ;
    public static final String GET_SERVICE_ITEM = DOMAIN+"/FuWuXiang.aspx" ;//获取服务项目
    public static final String GET_ORDER_INFO = DOMAIN+"/FuWuWanCheng.aspx" ;//获取服务项目
    public static final String ACEPT_ORDER = DOMAIN+"/FuWuFenPai.aspx" ;//接受订单
    public static final String FINISH_ORDER_ACTION = DOMAIN+"/FuWuWanCheng.aspx" ;//接受订单
    public static final String FUWU_QINGQIU = DOMAIN+"/FuWuQingQiu.aspx" ;//服务请求
    public static final String SUBMIT_ZHIWEN_ACTION = DOMAIN+"/LaoRenDangAn.aspx" ;//接受订单
    //http://139.196.102.210:807/FuWuWanCheng.aspx?action=complete&id=124&value=洗头;洗澡;洗头;洗澡;
//    http://139.196.102.210:807/FuWuUser.aspx?action=login&name=曾美强&pwd=123456
    //获取部门信息人员信息
    //返回部门用户（包括子部门用户）
    public static final String GET_Office_INFO_ACTION = DOMAIN +"/app/user/treeAndSubTreeData";
    //返回部门用户
    public static final String GET_Office_INFO_ACTION2 = DOMAIN +"/app/user/treeData";
 public static final String PACKAGENAME = "com.iet.fire";
 //出差单状态发生变化则修改
 public static final String FIRE_BUSINESS_TRIP_ACTION = DOMAIN+"/FIRE_BUSINESS_TRIP_MESSAGE";
 public static final String APK_DOWNLOAD_URL = DOMAIN+"apkUrl";
 public static int NEEDLOGIN = 1;//如果1则是需要登录，如果是2则代表已经进入登录页面了其他页面需要等待
 public static final String LOGIN_ACTION = "com.channelsoft.carshare.login";
 public static final String CHECK_VERSION_ACTION ="http://fire.weixunit.com:8080/app/apkVersion/newVersion";
 public static final String GET_MY_PROFILE_ACTION = DOMAIN +  "/user/getByPhone.do";
    public static final String SEND_SMS_FOR_CODE_ACTION = DOMAIN+"";//暂时还没用
    public static final String VERIFY_CODE_ACTION = DOMAIN +"";//暂时还没用
    //apkUpdate
    public static final String GET_APK_VERSION_ACTION = DOMAIN + "/app/apkVersion/newVersion";
    public static final String GET_APK_URL_ACTION = DOMAIN + "/app/option/apkUrl";
    //请假申请
    public static final String GET_APPLY_LEAVE_ACTION = DOMAIN + "/app/fireLeave/save";
    //待办流程
    public static final String GET_WAIT_CHECK_LEAVE_ACTION = DOMAIN + "/app/act/task/todo";
    //获取请假流程历史列表
    public static final String GET_PROC_LIST_ACTION = DOMAIN + "/app/act/task/histoicFlow";
    //获取请假待办流程
//    public static final String GET_WAIT_CHECK_LIST_ACTION = DOMAIN + "/app/act/task/todo";
    public static final String GET_WAIT_CHECK_LIST_ACTION = DOMAIN + "/app/fireLeave/list/task";
    public static final String GET_LEAVE_CHECK_ACTION = DOMAIN + "/app/act/task/complete";

    public static final String GET_WAIT_CHECK_TRIP_LIST_ACTION = DOMAIN + "/app/businesstrip/list/task";
    public static final String GET_WAIT_CHECK_USE_CAR_ACTION = DOMAIN + "/app/useCar/list/task";
    //申请出差接口
    public static final String APPLY_TRIP_ACTION = DOMAIN +"/app/businesstrip/save";

    //申请出差接口
    public static final String APPLY_USE_CAR_ACTION = DOMAIN +"/app/useCar/save";

    //今日值班
    public static final String GET_TODAY_DUTY = DOMAIN +"/app/workDuty";

    //我的出差、我的休假、我的用车
    public static final String GET_MY_LEAVE_ACTION = DOMAIN + "/app/fireLeave/list";
    public static final String GET_MY_TRIP_ACTION = DOMAIN + "/app/businesstrip/list";
    public static final String GET_MY_USE_CAR_ACTION = DOMAIN + "/app/useCar/list";
    //统计信息的网址
    public static final String GET_TRIP_PERSON_COUNT_ACTION = DOMAIN + "/app/businesstrip/list/user";
    public static final String GET_LEAVED_PERSON_COUNT_ACTION = DOMAIN + "/app/fireLeave/list/user";
    public static final String GET_USE_CAR_COUNT_ACTION = DOMAIN + "/app/useCar/list/user";

    //获取消息列表
    public static final String GET_MESSAGES_ACTION = DOMAIN +"/app/msg";
    public static final String DELETE_MESSAGE_BY_ID_ACTION = DOMAIN +"/app/msg/delete";

    public static final String GET_CHECKED_LEAVE_ACTION = DOMAIN +"/app/fireLeave/list/myApproval";
    public static final String GET_CHECKED_TRIP_ACTION = DOMAIN +"/app/businesstrip/list/myApproval";
    public static final String GET_CHECKED_USE_CAR_ACTION = DOMAIN +"/app/useCar/list/myApproval";
  //商户号
   public static final String MCH_ID = "1233848001";

    //执勤实力
    public static final String GET_DUTY_STENGHTH_ACTION = DOMAIN + "/app/duty";



    
}
