package sswl.caipai.ui.activity.user;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.github.lazylibrary.util.DateUtil;
import com.github.lazylibrary.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sswl.caipai.R;
import sswl.caipai.app.App;
import sswl.caipai.constant.Constant;
import sswl.caipai.constant.MessageConstant;
import sswl.caipai.http.BaseHttp;
import sswl.caipai.model.AreaModel;
import sswl.caipai.model.Result;
import sswl.caipai.model.UserModel;
import sswl.caipai.ui.activity.base.BaseActivity;
import sswl.caipai.widget.EmotionPopWindow;

@ContentView(R.layout.activity_user_info)
public class UserInfoActivity extends BaseActivity {
    @ViewInject(R.id.iv_back)
    private ImageView iv_back;

    @ViewInject(R.id.iv_user_photo)
    private RoundedImageView iv_user_photo;

    @ViewInject(R.id.tv_user_name)
    private TextView tv_user_name;

    @ViewInject(R.id.iv_sex)
    private ImageView iv_sex;

    @ViewInject(R.id.tv_user_sign)
    private TextView tv_user_sign;

    @ViewInject(R.id.tv_age)
    private TextView tv_age;

    @ViewInject(R.id.tv_emotion_state)
    private TextView tv_emotion_state;

    @ViewInject(R.id.tv_hometown)
    private TextView tv_hometown;

    @ViewInject(R.id.tv_career)
    private TextView tv_career;

    @ViewInject(R.id.tv_app_account)
    private TextView tv_app_account;

    @ViewInject(R.id.rv_age)
    private RelativeLayout rv_age;

    @ViewInject(R.id.rv_app_account)
    private RelativeLayout rv_app_account;

    @ViewInject(R.id.rv_authention)
    private RelativeLayout rv_authention;

    @ViewInject(R.id.rv_career)
    private RelativeLayout rv_career;

    @ViewInject(R.id.rv_emotion_state)
    private RelativeLayout rv_emotion_state;

    @ViewInject(R.id.rv_hometown)
    private RelativeLayout rv_homentown;

    @ViewInject(R.id.rv_user_indention)
    private RelativeLayout rv_user_idention;

    @ViewInject(R.id.rv_user_nickname)
    private RelativeLayout rv_user_nickname;

    @ViewInject(R.id.rv_user_photo)
    private RelativeLayout rv_user_photo;

    @ViewInject(R.id.rv_user_sex)
    private RelativeLayout rv_sex;

    @ViewInject(R.id.rv_user_sign)
    private RelativeLayout rv_user_sign;

    @ViewInject(R.id.content)
    private LinearLayout content;

    private boolean is_OnCreate = true; //用来判断是否调用OnResume里的setPages方法

    private TimePickerView timeView;
    private OptionsPickerView cityView;

    private Map<String,String> emotional = new HashMap<>();
    private Result<ArrayList<AreaModel>> allCity;
    private ArrayList<String> province;
    private ArrayList<String> province_id;
    private ArrayList<ArrayList<String>> city;
    private ArrayList<ArrayList<String>> city_id;
    private ArrayList<String> temp;
    private ArrayList<String> temp_city_id;
    private RequestParams areaParams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void initData() {
        super.initData();
        province_id = new ArrayList<>();
        areaParams = new RequestParams();
        province =new ArrayList<>();
        city = new ArrayList<ArrayList<String>>();
        city_id = new ArrayList<>();
        temp_city_id = new ArrayList<>();
        emotional.put("0","保密");
        emotional.put("1","单身");
        emotional.put("2","恋爱中");
        emotional.put("3","已婚");
        emotional.put("4","同性");
        setPages(App.user.getData());
        timeView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        timeView.setCyclic(true);
        timeView.setTitle("选择出生日期");
        timeView.setRange(1960,2016);
        timeView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                Log.e("String date",DateUtil.date2Str(date));
               tv_age.setText((DateUtil.countDays(DateUtil.date2Str(date)))/365 + "岁");
            }
        });
        cityView = new OptionsPickerView<>(this);
        cityView.setTitle("选择家乡");

        cityView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                Log.e("select",province.get(options1) +" "+province_id.get(options1));
                Log.e("select",city.get(options1).get(option2) +" "+city_id.get(options1).get(option2));
                tv_hometown.setText(province.get(options1)+city.get(options1).get(option2));
                params.put("province_id",province_id.get(options1));
                params.put("city_id",city_id.get(options1).get(option2));
                params.put("province",province.get(options1));
                params.put("city",city.get(options1).get(option2));
                BaseHttp.sendPost(Constant.EDIT_USER_INFO,params,handler,UserInfoActivity.this);
            }
        });
    }

    @Override
    public void initViews() {
        super.initViews();
        rv_age.setOnClickListener(this);
        rv_app_account.setOnClickListener(this);
        rv_authention.setOnClickListener(this);
        rv_career.setOnClickListener(this);
        rv_emotion_state.setOnClickListener(this);
        rv_homentown.setOnClickListener(this);
        rv_sex.setOnClickListener(this);
        rv_user_idention.setOnClickListener(this);
        rv_user_nickname.setOnClickListener(this);
        rv_user_photo.setOnClickListener(this);
        rv_user_sign.setOnClickListener(this);
        iv_back.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.rv_age:
                timeView.show();
                break;
            case R.id.rv_user_photo:
                Intent user_photo = new Intent(this,ChangeUserPicActivity.class);
                startActivity(user_photo);
                break;
            case R.id.rv_user_nickname:
                Intent usernick_name = new Intent(this,EditNicknameActivity.class);
                startActivity(usernick_name);
                break;
            case R.id.rv_career:
                Intent user_career = new Intent(this,EditCareerActivity.class);
                startActivity(user_career);
                break;
            case R.id.rv_emotion_state:
                if(StringUtils.isEmpty(App.user.getData().getEmotional()))
                    new EmotionPopWindow(this, "0", params, new EmotionPopWindow.setEmotional() {
                        @Override
                        public void setResult(String result) {
                            tv_emotion_state.setText(result);
                        }
                    }).showAtLocation(content, Gravity.BOTTOM,0,0);
                else
                    new EmotionPopWindow(this, App.user.getData().getEmotional(), params, new EmotionPopWindow.setEmotional() {
                        @Override
                        public void setResult(String result) {
                            tv_emotion_state.setText(result);
                        }
                    }).showAtLocation(content, Gravity.BOTTOM,0,0);
                break;
            case R.id.rv_hometown:
                BaseHttp.getArea(Constant.GET_AREA_INFO,areaParams,handler,this);
                break;
            case R.id.rv_user_sex:
                Intent edid_sex = new Intent(this,EditSexActivity.class);
                startActivity(edid_sex);
                break;
            case R.id.rv_user_sign:
                Intent user_sign =new Intent(this,EditSignActivity.class);
                startActivity(user_sign);
                break;
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }
    private void setPages(UserModel userModel){
        if(!StringUtils.isEmpty(userModel.getAge()))
            tv_age.setText(userModel.getAge());
        if(!StringUtils.isEmpty(userModel.getJob()))
            tv_career.setText(userModel.getJob());
        if(!StringUtils.isEmpty(userModel.getNickname()))
            tv_user_name.setText(userModel.getNickname());
        if (!StringUtils.isEmpty(userModel.getIcon()))
            ImageLoader.getInstance().displayImage(userModel.getIcon(),iv_user_photo);
        if (!StringUtils.isEmpty(userModel.getArea()))
            tv_hometown.setText(userModel.getArea());
        if (!StringUtils.isEmpty(userModel.getAccount()))
            tv_app_account.setText(userModel.getAccount());
        if(!StringUtils.isEmpty(userModel.getEmotional()))
            tv_emotion_state.setText(emotional.get(userModel.getEmotional()));
        if(!StringUtils.isEmpty(userModel.getMotto()))
            tv_user_sign.setText(userModel.getMotto());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!is_OnCreate)
            setPages(App.user.getData());
        is_OnCreate = false;
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MessageConstant.MESSAGE_WHAT_GET_CITY:
                    allCity = new Gson().fromJson(String.valueOf(msg.obj),new TypeToken<Result<List<AreaModel>>>(){}.getType());
                    for(int i = 1 ; i < allCity.getData().size();i++){
                        if(allCity.getData().get(i).getParent_id().equals("1")){
                            province.add(allCity.getData().get(i).getName());
                            province_id.add(allCity.getData().get(i).getId());
                        }
                    }
                    for(int i =0; i<province_id.size();i++){
                        temp = new ArrayList<>();
                        for(int j = 0 ; j <allCity.getData().size();){
                            AreaModel _area = allCity.getData().get(j);
                            if(province_id.get(i).equals(_area.getParent_id())){
                                temp.add(_area.getName());
                                temp_city_id.add(_area.getId());
                                allCity.getData().remove(_area);
                            }else{
                                j++;
                            }
                        }
                        city.add(i,temp);
                        city_id.add(i,temp_city_id);
                    }
                    cityView.setPicker(province,city,true);
                    cityView.setCyclic(false,false,true);
                    cityView.show();
                    break;
                default:
                    break;
            }
        }
    };

}
