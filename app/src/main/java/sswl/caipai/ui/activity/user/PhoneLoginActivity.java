package sswl.caipai.ui.activity.user;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.CursorJoiner;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lazylibrary.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;

import org.json.JSONException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.IOException;

import retrofit2.Call;
import sswl.caipai.R;
import sswl.caipai.app.App;
import sswl.caipai.constant.Constant;
import sswl.caipai.constant.MessageConstant;
import sswl.caipai.http.BaseHttp;

import sswl.caipai.http.BaseRetrofit;
import sswl.caipai.http.UserInterface;
import sswl.caipai.model.Result;
import sswl.caipai.model.UserModel;
import sswl.caipai.ui.activity.MainActivity;
import sswl.caipai.ui.activity.base.BaseActivity;
import sswl.caipai.util.CommonUtil;


@ContentView(R.layout.activity_phone_login)
public class PhoneLoginActivity extends BaseActivity {
    private CountDownTimer timer;

    @ViewInject(R.id.ed_phone_number)
    private EditText ed_phone_number;

    @ViewInject(R.id.ed_validate_code)
    private EditText ed_validate_code;

    @ViewInject(R.id.tv_validate)
    private TextView tv_validate;

    @ViewInject(R.id.tv_login)
    private TextView tv_login;

    @ViewInject(R.id.iv_back)
    private ImageView iv_back;

    private String phone_number;

    private RequestParams params;

    private Result<UserModel> result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        super.initData();
        tv_validate.setOnClickListener(this);
        tv_login.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        timer =new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv_validate.setText(millisUntilFinished/1000+"秒后重新获取");
                tv_validate.setEnabled(false);
            }

            @Override
            public void onFinish() {
                tv_validate.setText("验证");
                tv_validate.setEnabled(true);
            }
        };

    }

    @Override
    public void initViews() {
        super.initViews();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_validate:
                timer.start();
                validate_mobile();
                break;
            case R.id.tv_login:
                validate_code();
                break;
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }
    /*
    * 验证手机号，并且发送验证码
    * */
    private void validate_mobile(){
        phone_number = ed_phone_number.getText().toString();
        if(!CommonUtil.checkMobile(this,phone_number)){
            return;
        }
        params = new RequestParams();
        params.put("mobile",phone_number);
        params.put("type","1");
        BaseHttp.doPost(Constant.GET_VALIDATE_CODE,params,this);
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                UserInterface user = BaseRetrofit.getRetrofit().create(UserInterface.class);
//                Call<Result> call = user.getCode("17764575632","1");
//                try {
//                    Log.e("call", call.execute().body().getCode());
//                }catch (IOException e){
//                    e.printStackTrace();
//                }
//            }
//        }.start();
        }
    private void validate_code(){
        String code = ed_validate_code.getText().toString();
        if(!StringUtils.isEmpty(code)){
            params = new RequestParams();
            params.put("account",phone_number);
            params.put("code",code);
            BaseHttp.sendPost(Constant.USER_LOGIN,params,handler,this);
        }
    }
    /*
    * 线程接收服务端返回的东西
    * */
    Handler handler  = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MessageConstant.MESSAGE_WHAT_GET_POST_INFO:
                    try{
                        setUserInfo(String.valueOf(msg.obj));
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                    break;
                case MessageConstant.MESSAGE_WHAT_GET_USER_INFO:
                    LoginInfo info = new LoginInfo(App.user.getData().getUserYunXin().getAccid(),App.user.getData().getUserYunXin().getToken());
                //    loginRequest = NIMClient.getService(AuthService.class).login(new LoginInfo());
                    RequestCallback<LoginInfo> callback = new RequestCallback<LoginInfo>() {
                        @Override
                        public void onSuccess(LoginInfo loginInfo) {
                            Log.e("login info","account"+loginInfo.getAccount()+"appkey"+loginInfo.getAppKey()+"token"+loginInfo.getToken());
                            Intent intent = new Intent(PhoneLoginActivity.this,MainActivity.class);
                            Toast.makeText(PhoneLoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            finish();
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

                    break;
                default:
                    break;
            }
        }
    };
    /*
    *  登录成功把用户id
    * */
    private void setUserInfo(String json) throws JSONException{

        result = new Gson().fromJson(json,new TypeToken<Result<UserModel>>(){}.getType());
        SharedPreferences sharedPreferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userid",result.getData().getUserid());
        String appSign = result.getData().getUserid() + result.getData().getSessionKey();
        editor.putString("appsign",appSign.toUpperCase());
        editor.putString("accid",result.getData().getUserYunXin().getAccid());
        editor.commit();
        BaseHttp.getUserInfo(this,handler);
    }
}
