package sswl.caipai.ui.activity.room;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.lazylibrary.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import sswl.caipai.R;
import sswl.caipai.app.App;
import sswl.caipai.constant.Constant;
import sswl.caipai.constant.MessageConstant;
import sswl.caipai.http.BaseHttp;
import sswl.caipai.model.CreateLivingModel;
import sswl.caipai.model.Result;
import sswl.caipai.ui.activity.base.BaseActivity;
import sswl.caipai.ui.activity.base.BaseFragment;

@ContentView(R.layout.activity_create_live)
public class CreateLiveActivity extends BaseActivity {
    @ViewInject(R.id.video_type)
    private Spinner video_type;

    @ViewInject(R.id.iv_cancel)
    private ImageView iv_cancel;

    @ViewInject(R.id.ed_live_title)
    private EditText ed_live_title;

    @ViewInject(R.id.tv_start_living)
    private TextView tv_start_living;

    private Result<CreateLivingModel> live;

    private String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        super.initData();
        tv_start_living.setOnClickListener(this);
        live  = new Result<CreateLivingModel>();
        iv_cancel.setOnClickListener(this);
        video_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        type="SD";
                        break;
                    case 1:
                        type="SD";
                        break;
                    case 2:
                        type="HD";
                    break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                    type=" ";
            }
        });
    }

    @Override
    public void initViews() {
        super.initViews();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_start_living:
                validate();
                break;
            case R.id.iv_cancel:
                finish();
                break;

        }
    }
    private void validate(){
        if(StringUtils.isEmpty(ed_live_title.getText().toString()))
            return;
        params.put("name",ed_live_title.getText().toString());
        params.put("type","1");
        params.put("creator", App.user.getData().getUserid());
        BaseHttp.sendPost(Constant.CREATE_LIVING,params,handler,this);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MessageConstant.MESSAGE_WHAT_GET_POST_INFO:
                    live = new Gson().fromJson(String.valueOf(msg.obj),new TypeToken<Result<CreateLivingModel>>(){}.getType());
                    Intent intent = new Intent(CreateLiveActivity.this,MediaPreviewActivity.class);
                    intent.putExtra("mediaPath", live.getData().getRtmp_pull_url().trim());
                    intent.putExtra("videoResolution", type);
                    intent.putExtra("filter", true);
                    intent.putExtra("alert1", false);
                    intent.putExtra("alert2", false);
                    intent.putExtra("roomId",live.getData().getRoomid());
                    intent.putExtra("cid",live.getData().getCid());
                    intent.putExtra("operator",live.getData().getCreator());
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    };
}
