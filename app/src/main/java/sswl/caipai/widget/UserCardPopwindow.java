package sswl.caipai.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.w3c.dom.Text;
import org.xutils.view.annotation.ContentView;

import sswl.caipai.R;
import sswl.caipai.constant.Constant;
import sswl.caipai.constant.MessageConstant;
import sswl.caipai.http.BaseHttp;
import sswl.caipai.model.UserModel;
import sswl.caipai.ui.activity.base.BaseActivity;
import sswl.caipai.ui.activity.otherUser.PersonalActivity;
import sswl.caipai.util.CommonUtil;

/**
 * Created by Administrator on 2016/6/3 0003.
 */
@ContentView(R.layout.pop_audience_card)
public class UserCardPopwindow extends PopupWindow implements View.OnClickListener{
    private  Context context;
    private RequestParams params;
    private UserModel userModel;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_report:
                CommonUtil.toast(context,"暂未开放！");
                break;
            case R.id.iv_close:
                this.dismiss();
                break;
            case R.id.tv_attention:
                BaseHttp.sendPost(Constant.DO_ATTENTION_ACTION,params,handler,context);
                break;
            case R.id.tv_homepage:
                Intent intent = new Intent(context, PersonalActivity.class);
                intent.putExtra("user",userModel);
                context.startActivity(intent);
                break;
        }
    }

    private TextView tv_report;
    private ImageView iv_close;
    private RoundedImageView iv_user_photo;
    private TextView tv_user_name;
    private TextView tv_account;
    private TextView tv_sign;
    private TextView tv_attention;
    private TextView tv_homepage;
    public UserCardPopwindow(Context context, UserModel userModel, RequestParams params) {
        super(context);
        this.userModel = userModel;
        this.context = context;
        this.params = new RequestParams();
        this.params = params;
        View view = View.inflate(context, R.layout.pop_audience_card,null);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);
        view.findViewById(R.id.lv_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
         tv_report = (TextView)view.findViewById(R.id.tv_report);
        iv_close = (ImageView)view.findViewById(R.id.iv_close);
        iv_user_photo =(RoundedImageView)view.findViewById(R.id.iv_user_photo);
        tv_account = (TextView)view.findViewById(R.id.tv_account);
        tv_user_name = (TextView)view.findViewById(R.id.tv_user_name);
        tv_attention =(TextView)view.findViewById(R.id.tv_attention);
        tv_sign = (TextView)view.findViewById(R.id.tv_user_sign);
        tv_homepage = (TextView)view.findViewById(R.id.tv_homepage);

        tv_homepage.setOnClickListener(this);
        tv_attention.setOnClickListener(this);
        iv_close.setOnClickListener(this);
        iv_user_photo.setOnClickListener(this);
        tv_report.setOnClickListener(this);
        tv_user_name.setText(userModel.getNickname());
        tv_account.setText("财拍号："+userModel.getAccount());
        tv_sign.setText(userModel.getMotto());
        ImageLoader.getInstance().displayImage(userModel.getIcon(),iv_user_photo);

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MessageConstant.MESSAGE_WHAT_GET_POST_INFO:
                    tv_attention.setText("已关注");
                    tv_attention.setTextColor(context.getResources().getColor(R.color.tv_gray));
                    break;
            }
        }
    };
}
