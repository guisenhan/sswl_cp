package sswl.caipai.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lazylibrary.util.StringUtils;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;

import java.util.Timer;
import java.util.TimerTask;

import sswl.caipai.R;
import sswl.caipai.app.App;
import sswl.caipai.constant.MessageConstant;
import sswl.caipai.http.BaseHttp;
import sswl.caipai.ui.activity.user.LoginActivity;

/**
* @explaination the first activity when enter application
* @info created by guisen.han on 2016.5.12
* */
public class LoadingActivity extends Activity {
    private String userid;
    private TextView testinterface;
    private Timer timer = new Timer();
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            if(!StringUtils.isEmpty(userid)) {
                LoginInfo info = new LoginInfo(App.user.getData().getUserYunXin().getAccid(),App.user.getData().getUserYunXin().getToken());
                //    loginRequest = NIMClient.getService(AuthService.class).login(new LoginInfo());
                RequestCallback<LoginInfo> callback = new RequestCallback<LoginInfo>() {
                    @Override
                    public void onSuccess(LoginInfo loginInfo) {
                        Log.e("login info","account"+loginInfo.getAccount()+"appkey"+loginInfo.getAppKey()+"token"+loginInfo.getToken());
                        Intent intent = new Intent(LoadingActivity.this,MainActivity.class);
                        Toast.makeText(LoadingActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }

                    @Override
                    public void onFailed(int i) {
                        Log.e("failure"," " +i);
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        Log.e("exception"," " +throwable);
                    }
                };
                NIMClient.getService(AuthService.class).login(info).setCallback(callback);
            }else{
                Intent intent = new Intent(LoadingActivity.this, LoginActivity.class);
                startActivity(intent);
            }
            finish();
            timer.cancel();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        userid= getSharedPreferences("user", Activity.MODE_PRIVATE).getString("userid","");
        Log.e("userid",userid);
        if(!StringUtils.isEmpty(userid)){
            BaseHttp.getUserInfo(this,handler);
        }else{
            timer.schedule(task,3000);
        }
        testinterface =(TextView)findViewById(R.id.test_interface);

    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MessageConstant.MESSAGE_WHAT_GET_USER_INFO:
                    timer.schedule(task,3000);
                    break;
            }
        }
    };
}
