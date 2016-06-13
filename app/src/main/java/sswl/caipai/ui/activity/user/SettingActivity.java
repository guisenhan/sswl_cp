package sswl.caipai.ui.activity.user;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import sswl.caipai.R;
import sswl.caipai.constant.Constant;
import sswl.caipai.http.BaseHttp;
import sswl.caipai.ui.activity.MainActivity;
import sswl.caipai.ui.activity.base.BaseActivity;


@ContentView(R.layout.activity_setting)
public class SettingActivity extends BaseActivity {
    @ViewInject(R.id.iv_back)
    private ImageView iv_back;

    @ViewInject(R.id.tv_quit)
    private TextView tv_quit;
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
        iv_back.setOnClickListener(this);
        tv_quit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_quit:
                BaseHttp.doPost(Constant.USER_QUIT, params,this);
                getSharedPreferences("user", Activity.MODE_PRIVATE).edit().remove("userid").commit();
                MainActivity.mainActivity.finish();
                finish();
                break;
        }
    }
}
