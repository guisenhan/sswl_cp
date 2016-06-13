package sswl.caipai.ui.activity.otherUser;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.lazylibrary.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.shizhefei.view.viewpager.SViewPager;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import sswl.caipai.R;
import sswl.caipai.constant.Constant;
import sswl.caipai.constant.MessageConstant;
import sswl.caipai.http.BaseHttp;
import sswl.caipai.model.Result;
import sswl.caipai.model.UserModel;
import sswl.caipai.ui.activity.base.BaseActivity;
import sswl.caipai.ui.adapter.fragment.PersonalFragmentAdapter;

import sswl.caipai.util.CommonUtil;


@ContentView(R.layout.activity_personal)
public class PersonalActivity extends BaseActivity {


    @ViewInject(R.id.iv_back)
    private ImageView iv_back;

    @ViewInject(R.id.tv_title)
    private TextView tv_title;

    @ViewInject(R.id.iv_user_photo)
    private RoundedImageView iv_user_photo;

    @ViewInject(R.id.tv_user_name)
    private TextView tv_user_name;

    @ViewInject(R.id.iv_sex)
    private ImageView iv_sex;

    @ViewInject(R.id.iv_user_level)
    private ImageView iv_user_level;

    @ViewInject(R.id.tv_cancer)
    private TextView tv_cancer;

    @ViewInject(R.id.tv_fans)
    private TextView tv_fans;

    @ViewInject(R.id.tv_auth_info)
    private TextView tv_auth_info;

    @ViewInject(R.id.lv_cancer)
    private LinearLayout lv_cancer;

    @ViewInject(R.id.lv_defriend)
    private LinearLayout lv_defriend;

    @ViewInject(R.id.lv_private_letter)
    private LinearLayout lv_private_letter;

    @ViewInject(R.id.fiv_personal)
    private FixedIndicatorView fiv_personal;

    @ViewInject(R.id.svp_personal)
    private SViewPager svp_personal;

    private UserModel result;
    private IndicatorViewPager indicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        super.initData();
        result =(UserModel) getIntent().getSerializableExtra("user");
        setPage(result);
     //   BaseHttp.sendPost(Constant.GET_USER_CARD_INFO,params,handler,this);
        lv_cancer.setOnClickListener(this);
        iv_back.setOnClickListener(this);
    }

    @Override
    public void initViews() {
        super.initViews();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.lv_cancer:
                params.put("followid",result.getUserid());
                BaseHttp.doPost(Constant.DO_ATTENTION_ACTION,params,this);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MessageConstant.MESSAGE_WHAT_GET_POST_INFO:

                    break;
            }
        }
    };
    private void setPage(UserModel user){
        if(!StringUtils.isEmpty(user.getIcon()))
            ImageLoader.getInstance().displayImage(user.getIcon(),iv_user_photo);
        if(!StringUtils.isEmpty(user.getNickname()))
            tv_user_name.setText(user.getNickname());
        if(!StringUtils.isEmpty(user.getUserAuth().getRealname()));
            tv_auth_info.setText(user.getUserAuth().getRealname());
        indicator = new IndicatorViewPager(fiv_personal,svp_personal);
        indicator.setAdapter(new PersonalFragmentAdapter(getSupportFragmentManager(),this,user));
        indicator.setIndicatorScrollBar(new ColorBar(this,getResources().getColor(R.color.main_sub_color),10));
        indicator.setIndicatorOnTransitionListener(new OnTransitionTextListener(CommonUtil.px2dip(this,CommonUtil.dip2px(this,15)),CommonUtil.px2dip(this,CommonUtil.dip2px(this,15)),getResources().getColor(R.color.tv_yellow),getResources().getColor(R.color.tv_normal)));
    }
}
