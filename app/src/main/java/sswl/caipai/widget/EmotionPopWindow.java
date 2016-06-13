package sswl.caipai.widget;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;

import sswl.caipai.R;
import sswl.caipai.constant.Constant;
import sswl.caipai.constant.MessageConstant;
import sswl.caipai.http.BaseHttp;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
public class EmotionPopWindow extends PopupWindow{
    private Activity mactivity;
    private setEmotional result;
    private String emotion = "";
    public interface setEmotional{
        public void setResult(String result);
    }
    private  RequestParams params1;
    public EmotionPopWindow( final Activity activity, String type, final RequestParams params,setEmotional result) {
        this.result = result;
        this.mactivity = activity;
        this.params1 = params;
        View view = View.inflate(activity.getApplicationContext(), R.layout.popwindow_emotion_state, null);
        setWidth(ActionBar.LayoutParams.MATCH_PARENT);
        setHeight(ActionBar.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new BitmapDrawable());
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);
        update();

        view.findViewById(R.id.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dismiss();
            }
        });
        final TextView tv_secret = (TextView) view.findViewById(R.id.tv_secret);
        final TextView tv_alone = (TextView) view.findViewById(R.id.tv_alone);
        final TextView tv_inlove = (TextView) view.findViewById(R.id.tv_inlove);
        final TextView tv_married =(TextView)view.findViewById(R.id.tv_married);
        final TextView tv_gay = (TextView)view.findViewById(R.id.tv_gay);
        if(type.equals("0")){
            emotion= "保密";
            tv_secret.setBackgroundResource(R.drawable.tv_login_yellow_back);}
        if(type.equals("1")){
            emotion= "单身";
            tv_alone.setBackgroundResource(R.drawable.tv_login_yellow_back);}
        if(type.equals("2")){
            emotion= "恋爱";
            tv_inlove.setBackgroundResource(R.drawable.tv_login_yellow_back);}
        if(type.equals("3")){
            emotion= "已婚";
            tv_married.setBackgroundResource(R.drawable.tv_login_yellow_back);}
        if(type.equals("4")) {
            emotion = "同性";
            tv_gay.setBackgroundResource(R.drawable.tv_login_yellow_back);
        }
        tv_married.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                params1.put("emotional","3");
                emotion = "已婚";
                BaseHttp.sendPost(Constant.EDIT_USER_INFO,params,handler,activity);
            }
        });
        tv_secret.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                params1.put("emotional","0");
                emotion = "保密";
                BaseHttp.sendPost(Constant.EDIT_USER_INFO,params,handler,activity);
            }
        });
        tv_alone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                params1.put("emotional","1");
                emotion = "单身";
                BaseHttp.sendPost(Constant.EDIT_USER_INFO,params,handler,activity);
            }
        });
        tv_inlove.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                params1.put("emotional","2");
                emotion = "恋爱中";
                BaseHttp.sendPost(Constant.EDIT_USER_INFO,params,handler,activity);
            }
        });
        tv_gay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                params1.put("emotional","4");
                emotion = "同性";
                BaseHttp.sendPost(Constant.EDIT_USER_INFO,params,handler,activity);
            }
        });
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MessageConstant.MESSAGE_WHAT_GET_POST_INFO:
                    BaseHttp.getUserInfo(mactivity,handler);
                    break;
                case MessageConstant.MESSAGE_WHAT_GET_USER_INFO:
                    dismiss();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void dismiss() {
        super.dismiss();
        result.setResult(emotion);
    }
}
