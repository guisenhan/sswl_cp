package sswl.caipai.ui.activity.user;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import sswl.caipai.R;

import sswl.caipai.app.App;
import sswl.caipai.constant.Constant;
import sswl.caipai.constant.MessageConstant;
import sswl.caipai.http.BaseHttp;
import sswl.caipai.ui.activity.base.BaseActivity;

@ContentView(R.layout.activity_edit_sex)
public class EditSexActivity extends BaseActivity {
    @ViewInject(R.id.iv_woman)
    private ImageView iv_man;

    @ViewInject(R.id.iv_woman)
    private ImageView iv_woman;

    @ViewInject(R.id.iv_back)
    private ImageView iv_back;

    @ViewInject(R.id.tv_save)
    private TextView tv_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        super.initData();
        iv_back.setOnClickListener(this);
        iv_man.setOnClickListener(this);
        iv_woman.setOnClickListener(this);
        tv_save.setOnClickListener(this);
    }

    @Override
    public void initViews() {
        super.initViews();
        if(App.user.getData().getSex().equals("0"))
            iv_woman.setImageDrawable(getResources().getDrawable(R.mipmap.woman_nor));
        if(App.user.getData().getSex().equals("1"))
            iv_man.setImageDrawable(getResources().getDrawable(R.mipmap.man_nor));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_save:
                BaseHttp.sendPost(Constant.EDIT_USER_INFO,params,handler,this);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_man:
                iv_woman.setImageDrawable(getResources().getDrawable(R.mipmap.woman_gray));
                iv_man.setImageDrawable(getResources().getDrawable(R.mipmap.man_nor));
                params.remove("sex");
                params.put("sex","1");
                break;
            case R.id.iv_woman:
                iv_woman.setImageDrawable(getResources().getDrawable(R.mipmap.woman_nor));
                iv_man.setImageDrawable(getResources().getDrawable(R.mipmap.man_gray));
                params.remove("sex");
                params.put("sex","0");
                break;
            default:
                break;
        }

    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MessageConstant.MESSAGE_WHAT_GET_POST_INFO:
                    BaseHttp.getUserInfo(EditSexActivity.this,handler);
                    break;
                case MessageConstant.MESSAGE_WHAT_GET_USER_INFO:
                    finish();
                    break;
            }
        }
    };
}
