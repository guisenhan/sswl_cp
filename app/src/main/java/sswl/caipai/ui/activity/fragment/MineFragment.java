package sswl.caipai.ui.activity.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.lazylibrary.util.StringUtils;
import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import sswl.caipai.R;
import sswl.caipai.app.App;
import sswl.caipai.constant.Constant;
import sswl.caipai.http.BaseHttp;
import sswl.caipai.model.Result;
import sswl.caipai.model.UserModel;
import sswl.caipai.ui.activity.base.BaseFragment;
import sswl.caipai.ui.activity.user.SettingActivity;
import sswl.caipai.ui.activity.user.UserInfoActivity;

/**
 * Created by Administrator on 2016/5/20 0020.
 */
@ContentView(R.layout.fragment_mine)
public class MineFragment extends BaseFragment{
    @ViewInject(R.id.iv_user_photo)
    private RoundedImageView iv_user_photo;
    @ViewInject(R.id.tv_user_name)
    private TextView tv_user_name;
    @ViewInject(R.id.iv_sex)
    private ImageView iv_sex;
    @ViewInject(R.id.iv_level)
    private ImageView iv_level;
    @ViewInject(R.id.lv_user_info)
    private LinearLayout lv_user_info;
    @ViewInject(R.id.tv_user_account)
    private TextView tv_user_account;

    @ViewInject(R.id.rv_setting)
    private RelativeLayout rv_setting;

    private RequestParams params ;

    private boolean is_create=true; // 用来判断是否调用OnResume方法里的setpages
    @Override
    public void initData() {
        super.initData();

    }
    @Override
    public void initViews() {
        super.initViews();
        lv_user_info.setOnClickListener(this);
        rv_setting.setOnClickListener(this);
        setPages(App.user);

    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.lv_user_info:
                Intent intent = new Intent(getActivity(), UserInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.rv_setting:
                    Intent settting = new Intent(getActivity(),SettingActivity.class);
                    startActivity(settting);
                break;
            default:
                break;
        }
    }
    private void setPages(Result<UserModel> user){
        if(!StringUtils.isEmpty(user.getData().getIcon()))
            ImageLoader.getInstance().displayImage(user.getData().getIcon(),iv_user_photo);
        if(!StringUtils.isEmpty(user.getData().getNickname()))
            tv_user_name.setText(user.getData().getNickname());
        if (!StringUtils.isEmpty(user.getData().getAccount()))
            tv_user_account.setText("财拍号："+user.getData().getAccount());
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!is_create)
        setPages(App.user);
        is_create = false;
    }
}
