package sswl.caipai.constant;

import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
public class Constant {
    public final static String SERVER_IP = "http://192.168.0.222:8081/caipaiapp/";
//    public final static String SERVER_IP ="http://120.55.75.155:8081/";

    public static final String GETCODE="mobileCode/send.json";
    public static String APP_DOCUMENT = "/sdcard/caipai/" ;
    public static final String CACHE_DIR = Environment.getExternalStorageDirectory() + File.separator + "caipai";
    public static final String CACHE_DIR_PHOTO = CACHE_DIR + File.separator + "photo"; // 拍照临时地址
    public static final String CACHE_DIR_SD =  CACHE_DIR + File.separator + "formats";
    public static final String UPLOAD_FILE = SERVER_IP +  "upload.json"; //上传图片


    public final static String GET_VALIDATE_CODE =SERVER_IP+"mobileCode/send.json"; //获取验证码
    public final static String USER_LOGIN=SERVER_IP+"user/login.json"; //用户登录
    public final static String USER_QUIT = SERVER_IP + "user/logout.json";

    // 用户相关
    public final static String GET_USER_INFO = SERVER_IP+"user/info.json"; //获取用户信息
    public final static String GET_AREA_INFO = SERVER_IP + "city/list.json" ; //获取地区信息
    public final static String EDIT_USER_INFO = SERVER_IP + "user/edit.json"; //编辑用户资料

    //直播相关
    public final static String CREATE_LIVING = SERVER_IP +"live/create.json" ;//创建直播
    public final static String CLOSE_LIVING = SERVER_IP + "live/close.json"; //关闭直播
    public final static String ENTER_LIVE_ROOM = SERVER_IP + "live/join.json";//进入直播频道
    public final static String EXIT_LIVE_ROOM = SERVER_IP + "live/exit.json";//退出直播间

    //用户动作相关
    public final static String DO_ATTENTION_ACTION = SERVER_IP + "relation/follow.json" ; // 关注用户接口
    public final static String DO_CANCEL_ATTENTION_ACTION = SERVER_IP + "relation/unfollow.json" ; //取消关注接口
    public final static String GET_USER_CARD_INFO = SERVER_IP + "user/detail.json" ; //获取某个用户的名片信息
    public final static String DO_SEARCH_USER = SERVER_IP + "search/search.json";//搜索用户接口
    //关注直播相关
    public final static String GET_NEWEST_LIVE = SERVER_IP + "live/list.json" ;//获取最新直播
    public final static String GET_HOT_LIVE = SERVER_IP + "live/hot.json" ; //获取热门直播
    public final static String GET_ATTENTION_LIVE = SERVER_IP + "live/attention.json" ; //获取关注的直播！
}
