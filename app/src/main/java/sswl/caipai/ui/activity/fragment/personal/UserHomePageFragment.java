package sswl.caipai.ui.activity.fragment.personal;

import android.view.View;
import android.widget.TextView;

import com.github.lazylibrary.util.StringUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import sswl.caipai.R;
import sswl.caipai.constant.MapConstant;
import sswl.caipai.model.UserModel;
import sswl.caipai.ui.activity.base.BaseFragment;

/**
 * Created by Administrator on 2016/6/6 0006.
 */
@ContentView(R.layout.fragment_user_homepage)
public class UserHomePageFragment extends BaseFragment {
    @ViewInject(R.id.tv_age)
    private TextView tv_gae;

    @ViewInject(R.id.tv_emotion_state)
    private TextView tv_emotion_state;

    @ViewInject(R.id.tv_hometown)
    private TextView tv_hometown;

    @ViewInject(R.id.tv_career)
    private TextView tv_career;

    @ViewInject(R.id.tv_account)
    private  TextView tv_account;

    @ViewInject(R.id.tv_user_sign)
    private TextView tv_user_sign;

    private UserModel userModel;

    @Override
    public void initViews() {
        super.initViews();
        userModel =(UserModel)getArguments().getSerializable("user");
        if(!StringUtils.isEmpty(userModel.getAccount()))
            tv_account.setText(userModel.getAccount());
        if(!StringUtils.isEmpty(userModel.getMotto()))
            tv_user_sign.setText(userModel.getMotto());
        if(!StringUtils.isEmpty(userModel.getJob()))
            tv_career.setText(userModel.getJob());
        if(!StringUtils.isEmpty(userModel.getArea()))
            tv_hometown.setText(userModel.getArea());
        if(!StringUtils.isEmpty(userModel.getAge()))
            tv_gae.setText(userModel.getAge());
        if(!StringUtils.isEmpty(userModel.getEmotional()))
            tv_emotion_state.setText(MapConstant.getEmotion().get(userModel.getEmotional()));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
