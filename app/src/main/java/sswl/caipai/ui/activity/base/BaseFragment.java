package sswl.caipai.ui.activity.base;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.RequestParams;
import com.shizhefei.fragment.LazyFragment;

import org.xutils.x;

/**
 * @explaination base fragment
 * @info Created by guisen.han on 2016/5/12 0012.
 */
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.Field;

import sswl.caipai.http.BaseHttp;
import sswl.caipai.model.Result;
import sswl.caipai.model.UserModel;

public class BaseFragment extends Fragment implements View.OnClickListener{
    private boolean injected = false;
    public String userid;
    public String appsign;
    public RequestParams params;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        injected = true;
        return x.view().inject(this, inflater, container);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!injected) {
            x.view().inject(this, this.getView());
        }
        userid =getActivity().getSharedPreferences("user", Activity.MODE_PRIVATE).getString("userid"," ");
        appsign = getActivity().getSharedPreferences("user",Activity.MODE_PRIVATE).getString("appsign","");
        params = new RequestParams();
        params.put("userid",userid);
        params.put("appsign",appsign);
       // BaseHttp.getUserInfo(getActivity());
        initViews();
        initData();
    }
    public void initData(){

    }
    public void initViews(){

    }

    @Override
    public void onClick(View v) {

    }
}
