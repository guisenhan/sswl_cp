 package sswl.caipai.ui.activity.play;

 import android.content.Intent;
 import android.os.Bundle;
 import android.os.Handler;
 import android.os.Message;
 import android.text.TextUtils;
 import android.util.Log;
 import android.view.Gravity;
 import android.view.View;
 import android.widget.AdapterView;
 import android.widget.ImageView;
 import android.widget.ListView;
 import android.widget.RelativeLayout;
 import android.widget.TextView;

 import com.github.lazylibrary.util.DateUtil;
 import com.github.lazylibrary.util.StringUtils;
 import com.google.gson.Gson;
 import com.google.gson.reflect.TypeToken;
 import com.makeramen.roundedimageview.RoundedImageView;
 import com.netease.nimlib.sdk.NIMClient;
 import com.netease.nimlib.sdk.Observer;
 import com.netease.nimlib.sdk.RequestCallback;
 import com.netease.nimlib.sdk.chatroom.ChatRoomMessageBuilder;
 import com.netease.nimlib.sdk.chatroom.ChatRoomService;
 import com.netease.nimlib.sdk.chatroom.ChatRoomServiceObserver;
 import com.netease.nimlib.sdk.chatroom.constant.MemberQueryType;

 import com.netease.nimlib.sdk.chatroom.model.ChatRoomMember;
 import com.netease.nimlib.sdk.chatroom.model.ChatRoomMessage;
 import com.netease.nimlib.sdk.chatroom.model.EnterChatRoomData;
 import com.nostra13.universalimageloader.core.ImageLoader;

 import org.xutils.view.annotation.ContentView;
 import org.xutils.view.annotation.ViewInject;

 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;

 import sswl.caipai.R;
 import sswl.caipai.app.App;

 import sswl.caipai.constant.Constant;

 import sswl.caipai.constant.MessageConstant;
 import sswl.caipai.http.BaseHttp;
 import sswl.caipai.model.MessageModel;
 import sswl.caipai.model.LiveModel;

 import sswl.caipai.model.Result;
 import sswl.caipai.model.UserModel;
 import sswl.caipai.ui.activity.base.BaseActivity;
 import sswl.caipai.ui.adapter.listview.AudienceAdapter;
 import sswl.caipai.ui.adapter.listview.MessageAdapter;
 import sswl.caipai.util.CommonUtil;
 import sswl.caipai.widget.HorizontalListView;
 import sswl.caipai.widget.UserCardPopwindow;

 @ContentView(R.layout.activity_player)
 public class NEVideoPlayerActivity extends BaseActivity {
     public final static String TAG = "NEVideoPlayerActivity";
     public NEVideoView mVideoView;  //用于画面显示
     private String roomid;
     private String cid;
     private LiveModel live;
     private AudienceAdapter audienceAdapter;
     private Result<UserModel> tempUser;
     @ViewInject(R.id.iv_liver_photo)
     private RoundedImageView iv_liver_photo;

     @ViewInject(R.id.tv_liver_name)
     private TextView tv_liver_name;

     @ViewInject(R.id.tv_account)
     private TextView tv_account;

     @ViewInject(R.id.tv_cp)
     private TextView tv_cp;

     @ViewInject(R.id.tv_date)
     private TextView tv_date;

     @ViewInject(R.id.rv_send_message)
     private RelativeLayout rv_send_message;

     @ViewInject(R.id.iv_send_message)
     private ImageView iv_send_message;

     @ViewInject(R.id.tv_send_message)
     private TextView tv_send_message;

     @ViewInject(R.id.ed_message_text)
     private TextView ed_message_text;

     @ViewInject(R.id.lv_message)
     private ListView lv_message;

     @ViewInject(R.id.hlv_play_audience)
     private HorizontalListView hlv_audience;

     @ViewInject(R.id.iv_finish)
     private ImageView iv_finish;

     @ViewInject(R.id.root_view)
     private RelativeLayout root_view;

     private List<MessageModel> message;
     private MessageAdapter adapter;
     private Result<LiveModel> liveinfo;
     public static final int NELP_LOG_UNKNOWN = 0; //!< log输出模式：输出详细
     public static final int NELP_LOG_DEFAULT = 1; //!< log输出模式：输出详细
     public static final int NELP_LOG_VERBOSE = 2; //!< log输出模式：输出详细
     public static final int NELP_LOG_DEBUG   = 3; //!< log输出模式：输出调试信息
     public static final int NELP_LOG_INFO    = 4; //!< log输出模式：输出标准信息
     public static final int NELP_LOG_WARN    = 5; //!< log输出模式：输出警告
     public static final int NELP_LOG_ERROR   = 6; //!< log输出模式：输出错误
     public static final int NELP_LOG_FATAL   = 7; //!< log输出模式：一些错误信息，如头文件找不到，非法参数使用
     public static final int NELP_LOG_SILENT  = 8; //!< log输出模式：不输出

     private String mVideoPath; //文件路径
     private String mDecodeType;//解码类型，硬解或软解
     private String mMediaType; //媒体类型
     private boolean mHardware = true;

     private boolean pauseInBackgroud = true;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         Log.e(TAG, "on create");
         super.onCreate(savedInstanceState);
         //接收MainActivity传过来的参数
         mMediaType   ="livestream";
         mDecodeType = "hardware";
         mVideoPath  =getIntent().getStringExtra("rtmp");

         Intent intent = getIntent();
         String intentAction = intent.getAction();
         if (!TextUtils.isEmpty(intentAction) && intentAction.equals(Intent.ACTION_VIEW)) {
             mVideoPath = intent.getDataString();
             Log.d(TAG, "videoPath = "+ mVideoPath);
         }

         if (mDecodeType.equals("hardware")) {
             mHardware = true;
         }
         else if (mDecodeType.equals("software")) {
             mHardware = false;
         }
         mVideoView = (NEVideoView) findViewById(R.id.video_view);

         if (mMediaType.equals("livestream")) {
             mVideoView.setBufferStrategy(0); //直播低延时
         }
         else {
             mVideoView.setBufferStrategy(1); //点播抗抖动
         }
         mVideoView.setMediaType(mMediaType);
         mVideoView.setHardwareDecoder(mHardware);
         mVideoView.setPauseInBackground(pauseInBackgroud);
         mVideoView.setVideoPath(mVideoPath);
         mVideoView.requestFocus();
         mVideoView.start();
         initChatRoom();
         getChatRoomMember();
     }

     @Override
     public void initData() {
         super.initData();
         iv_send_message.setOnClickListener(this);
         tv_send_message.setOnClickListener(this);
         message = new ArrayList<>();
         adapter = new MessageAdapter(this,message);
         iv_liver_photo.setOnClickListener(this);
         root_view.setOnClickListener(this);
         iv_finish.setOnClickListener(this);
         lv_message.setAdapter(adapter);
         liveinfo = new Result<LiveModel>();
         roomid = getIntent().getStringExtra("roomid");
         cid = getIntent().getStringExtra("cid");
         params.put("roomid",roomid);
         params.put("cid",cid);
         tempUser = new Result<>();
         BaseHttp.sendPost(Constant.ENTER_LIVE_ROOM, params,handler,this);
         getMessage();
     }

     @Override
     public void initViews() {
         super.initViews();

         hlv_audience.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 BaseHttp.sendPost(Constant.GET_USER_CARD_INFO,params,handler,NEVideoPlayerActivity.this,7);
             }
         });
     }

     @Override
     public void onClick(View v) {
         super.onClick(v);
         switch (v.getId()){
             case R.id.iv_send_message:
                 if(rv_send_message.getVisibility()==View.GONE){
                     rv_send_message.setVisibility(View.VISIBLE);
                 }
                 else
                     rv_send_message.setVisibility(View.GONE);
                 break;
             case R.id.tv_send_message:
                 sendMessage();
                 break;
             case R.id.iv_finish:
                 finish();
                 break;
             case R.id.iv_liver_photo:
                 tempUser = new Result<UserModel>();
                 tempUser.setData(live.getCreatuser());
                 params.put("followid",live.getCreatuser().getUserid());
                 UserCardPopwindow user = new UserCardPopwindow(this,tempUser.getData(),params);
                 user.showAtLocation(root_view, Gravity.CENTER,0 ,0);
                 break;
             case R.id.root_view:
                 if(rv_send_message.getVisibility()==View.VISIBLE){
                     rv_send_message.setVisibility(View.GONE);
                 }
                 break;
         }
     }
     private void sendMessage(){
         if(StringUtils.isEmpty(ed_message_text.getText().toString()))
             return;
         Map<String,Object> map = new HashMap<>();
         map.put("user_level", App.user.getData().getLv());
         final ChatRoomMessage Textmessage = ChatRoomMessageBuilder.createChatRoomTextMessage(roomid, ed_message_text.getText().toString());
         Textmessage.setRemoteExtension(map);
         NIMClient.getService(ChatRoomService.class).sendMessage(Textmessage,true).setCallback(new RequestCallback<Void>() {
             @Override
             public void onSuccess(Void aVoid) {
                 Log.e("send msg success","send message success");
                 MessageModel messageModel = new MessageModel();
                 messageModel.setContent(ed_message_text.getText().toString());
                 messageModel.setNickName(App.user.getData().getNickname());

                 message.add(messageModel);
                 ed_message_text.setText(" ");
                 adapter.notifyDataSetChanged();
                 lv_message.setSelection(message.size());
             }
             @Override
             public void onFailed(int i) {
                 Log.e("send msg onFailure","sendMessage failure "+i);
             }
             @Override
             public void onException(Throwable throwable)   {
                 Log.e("send msg onException","sendMessage failure "+throwable);
             }
         });
     }

     private void getMessage(){
         Observer<List<ChatRoomMessage>> messageObserve = new Observer<List<ChatRoomMessage>>() {
             @Override
             public void onEvent(List<ChatRoomMessage> chatRoomMessages) {
                 for (int i = 0;i<chatRoomMessages.size();i++){
                     MessageModel messageModel = new MessageModel();
                     messageModel.setNickName(chatRoomMessages.get(i).getChatRoomMessageExtension().getSenderNick());
                     messageModel.setContent(chatRoomMessages.get(i).getContent());
                     if(!StringUtils.isEmpty(messageModel.getContent())&&!StringUtils.isEmpty(messageModel.getNickName())){
                        message.add(messageModel);
                     }else{
                         getChatRoomMember();
                     }
                 }
                 adapter.notifyDataSetChanged();
                 lv_message.setSelection(message.size());
             }
         };
         NIMClient.getService(ChatRoomServiceObserver.class)
                 .observeReceiveMessage(messageObserve, true);
     }
     @Override
     protected void onStop() {
         Log.d(TAG, "NEVideoPlayerActivity onStop");
         NIMClient.getService(ChatRoomService.class).exitChatRoom(roomid);
         super.onStop();
     }

     @Override
     protected void onPause() {
         Log.d(TAG, "NEVideoPlayerActivity onPause");
         if (pauseInBackgroud)
             mVideoView.pause(); //锁屏时暂停
         super.onPause();
     }

     @Override
     protected void onDestroy() {
         Log.d(TAG, "NEVideoPlayerActivity onDestroy");
         mVideoView.release_resource();
         BaseHttp.doPost(Constant.EXIT_LIVE_ROOM,params,this);
         super.onDestroy();
     }

     @Override
     protected void onStart() {
         Log.d(TAG, "NEVideoPlayerActivity onStart");
         super.onStart();
     }

     @Override
     protected void onResume() {
         Log.d(TAG, "NEVideoPlayerActivity onResume");
         if (pauseInBackgroud && !mVideoView.isPaused()) {
             mVideoView.start(); //锁屏打开后恢复播放
         }
         super.onResume();
     }

     @Override
     protected void onRestart() {
         Log.d(TAG, "NEVideoPlayerActivity onRestart");
         super.onRestart();
     }

     private void initChatRoom(){
         EnterChatRoomData data = new EnterChatRoomData(roomid);
         NIMClient.getService(ChatRoomService.class).enterChatRoom(data).setCallback(new RequestCallback() {
             @Override
             public void onSuccess(Object o) {
                 Log.e("chatRoom success","enter chat room roomId"+roomid +"  "+o.toString());
             }
             @Override
             public void onFailed(int i) {
                 Log.e("chatRoom failure","enter chat room failure roomId"+roomid +"  "+i);
             }
             @Override
             public void onException(Throwable throwable) {
                 Log.e("chatRoom onException","enter chat room onException roomId"+roomid +"  "+throwable);
             }
         });
     }

     @Override
     public void onBackPressed() {
         if(rv_send_message.getVisibility()==View.VISIBLE){
             rv_send_message.setVisibility(View.GONE);
         }else{
             finish();
         }
     }
     Handler handler = new Handler(){
         @Override
         public void handleMessage(Message msg) {
             super.handleMessage(msg);
             switch (msg.what){
                 case MessageConstant.MESSAGE_WHAT_GET_POST_INFO:
                    liveinfo = new Gson().fromJson(String.valueOf(msg.obj),new TypeToken<Result<LiveModel>>(){}.getType());
                     if(!liveinfo.getCode().equals(1000))
                         CommonUtil.toast(NEVideoPlayerActivity.this,liveinfo.getMessage());
                     else
                        setPages();
                     break;
                 case 7:
                     tempUser = new Gson().fromJson(String.valueOf(msg.obj),new TypeToken<Result<UserModel>>(){}.getType());
                     params.put("followid",tempUser.getData().getUserid());
                    UserCardPopwindow car = new UserCardPopwindow(NEVideoPlayerActivity.this,tempUser.getData(),params);
                     car.showAtLocation(root_view, Gravity.CENTER,0 ,0);
                     break;
                 default:
                     break;
             }
         }
     };
     private void setPages(){
         ImageLoader.getInstance().displayImage(liveinfo.getData().getIcon(),iv_liver_photo);
            Log.e("info"," " +liveinfo.getData().getCreatuser().getAccount());
         tv_account.setText(liveinfo.getData().getCreatuser().getAccount());
         tv_liver_name.setText(liveinfo.getData().getCreatuser().getNickname());
         tv_date.setText(DateUtil.getCurDateStr(DateUtil.FORMAT_YMD));
     }
     private void getChatRoomMember(){
         NIMClient.getService(ChatRoomService.class).fetchRoomMembers(roomid, MemberQueryType.GUEST,0,15).setCallback(new RequestCallback<List<ChatRoomMember>>() {
             @Override
             public void onSuccess(List<ChatRoomMember> chatRoomMembers) {
                 for(int i = 0 ; i < chatRoomMembers.size();i++){
                     Log.e("chatRoomMember",new Gson().toJson(chatRoomMembers.get(i)));
                 }
                 Log.e("members size"," " +chatRoomMembers.size());
                 audienceAdapter = new AudienceAdapter(NEVideoPlayerActivity.this,chatRoomMembers);
                 hlv_audience.setAdapter(audienceAdapter);
                 audienceAdapter.notifyDataSetChanged();
             }

             @Override
             public void onFailed(int i) {
             }
             @Override
             public void onException(Throwable throwable) {
             }
         });
     }
 }