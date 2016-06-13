package sswl.caipai.ui.activity.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.loopj.android.http.RequestParams;
import org.xutils.x;



/**
 * @explaination this class is design for the application
 * @info Created by guisen.han on 2016/5/12 0012.
 */
public class BaseActivity extends FragmentActivity implements View.OnClickListener{

    public String userid;
    public String appsign;
    public String accid;
    public RequestParams params;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        userid =getSharedPreferences("user", Activity.MODE_PRIVATE).getString("userid"," ");
        appsign = getSharedPreferences("user",Activity.MODE_PRIVATE).getString("appsign","");
        accid = getSharedPreferences("user",Activity.MODE_PRIVATE).getString("accid","");
        params = new RequestParams();
        params.put("userid",userid);
        params.put("appsign",appsign);
        params.put("accid", accid);
        initViews();
        initData();
    }
    public void initViews(){

    }
    public void initData(){
    }

    @Override
    public void onClick(View v) {

    }

}
