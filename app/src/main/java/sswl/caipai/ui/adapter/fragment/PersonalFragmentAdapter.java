package sswl.caipai.ui.adapter.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.lazylibrary.util.StringUtils;
import com.shizhefei.view.indicator.IndicatorViewPager;

import sswl.caipai.R;
import sswl.caipai.model.UserModel;
import sswl.caipai.ui.activity.fragment.live.AttentionFragment;
import sswl.caipai.ui.activity.fragment.live.HotFragment;
import sswl.caipai.ui.activity.fragment.live.NewLiveFragment;
import sswl.caipai.ui.activity.fragment.personal.UserHomePageFragment;
import sswl.caipai.ui.activity.fragment.personal.UserLiveFragment;


/**
 * Created by Administrator on 2016/3/30 0030.
 */
public class PersonalFragmentAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
    private String[]  fragmentName ={"主页","直播"};
    private Context context;
    private LayoutInflater inflater;
    private UserModel userModel;
    public PersonalFragmentAdapter(FragmentManager fragmentManager, Context context, UserModel userModel) {
        super(fragmentManager);
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.userModel = userModel;
    }

    @Override
    public int getCount() {
        return fragmentName.length;
    }

    @Override
    public Fragment getCurrentFragment() {
        return super.getCurrentFragment();
    }
    @Override
    public View getViewForTab(int i, View view, ViewGroup viewGroup) {
        if(view==null){
           view = inflater.inflate(R.layout.tab_top,viewGroup,false);
        }
        TextView text = (TextView)view;
        if(!StringUtils.isEmpty(fragmentName[i]))
            text.setText(fragmentName[i]);
        return text;
    }

    @Override
    public Fragment getFragmentForPage(int i) {
        Fragment fragment = new Fragment();
        switch (i){
            case 0:
                UserHomePageFragment homepage = new UserHomePageFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("user",userModel);
                homepage.setArguments(bundle);
                fragment = homepage;
                break;
            case 1:
                UserLiveFragment live = new UserLiveFragment();
                fragment = live;
                break;

            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public Fragment getExitFragment(int position) {
        return super.getExitFragment(position);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

}
