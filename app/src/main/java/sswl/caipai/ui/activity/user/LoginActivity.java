package sswl.caipai.ui.activity.user;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import sswl.caipai.R;
import sswl.caipai.ui.activity.base.BaseActivity;



@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    @ViewInject(R.id.iv_def_phone)
    private ImageView iv_def_phone;

    @ViewInject(R.id.iv_def_qq)
    private ImageView iv_def_qq;

    @ViewInject(R.id.iv_def_wx)
    private ImageView iv_def_wx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initViews() {
        super.initViews();
        iv_def_phone.setOnClickListener(this);
        iv_def_qq.setOnClickListener(this);
        iv_def_wx.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.iv_def_phone:
                Intent intent = new Intent(this,PhoneLoginActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_def_qq:
                break;
            case R.id.iv_def_wx:
                break;
            default:
                break;
        }
    }
}
