package sswl.caipai.ui.activity.user;

import android.os.Handler;
import android.os.Message;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.lazylibrary.util.StringUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import sswl.caipai.R;
import sswl.caipai.app.App;
import sswl.caipai.constant.Constant;
import sswl.caipai.constant.MessageConstant;
import sswl.caipai.http.BaseHttp;
import sswl.caipai.ui.activity.base.BaseActivity;
import sswl.caipai.util.CommonUtil;

@ContentView(R.layout.activity_edit_sign)
public class EditSignActivity extends BaseActivity {

    @ViewInject(R.id.iv_back)
    private ImageView iv_back;

    @ViewInject(R.id.tv_save)
    private TextView tv_save;

    @ViewInject(R.id.tv_title)
    private TextView tv_title;

    @ViewInject(R.id.ed_nickname)
    private EditText ed_nickname;

    @ViewInject(R.id.tv_tips)
    private TextView tv_tips;

    @ViewInject(R.id.tv_numbers)
    private TextView tv_numbers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        super.initData();
        tv_title.setText("修改签名");
        tv_tips.setText("签名不能超过32个字");
        if(!StringUtils.isEmpty(App.user.getData().getMotto()))
            ed_nickname.setHint(App.user.getData().getMotto());

        ed_nickname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tv_numbers.setText(s.length()+"/32");
                if(s.length()>32)
                    ed_nickname.setTextColor(getResources().getColor(R.color.main_sub_color));
            }
        });
    }

    @Override
    public void initViews() {
        super.initViews();
        tv_save.setOnClickListener(this);
        iv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_save:
                validate();
                break;
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }
    private void validate(){
        if(StringUtils.isEmpty(ed_nickname.getText().toString())){
            CommonUtil.toast(this,"提交内容为空！");
            return;
        }
        params.put("motto",ed_nickname.getText().toString());
        BaseHttp.sendPost(Constant.EDIT_USER_INFO,params,handler,this);
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MessageConstant.MESSAGE_WHAT_GET_POST_INFO:
                    BaseHttp.getUserInfo(EditSignActivity.this,handler);
                    break;
                case MessageConstant.MESSAGE_WHAT_GET_USER_INFO:
                    finish();
                    break;
                default:
                    break;
            }
        }
    };
}
