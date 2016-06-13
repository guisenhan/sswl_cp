package sswl.caipai.ui.adapter.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.lazylibrary.util.StringUtils;
import com.shizhefei.view.indicator.IndicatorViewPager;

import sswl.caipai.R;
import sswl.caipai.ui.activity.fragment.live.AttentionFragment;
import sswl.caipai.ui.activity.fragment.live.HotFragment;
import sswl.caipai.ui.activity.fragment.live.NewLiveFragment;
import sswl.caipai.ui.activity.room.RoomActivity;


/**
 * Created by Administrator on 2016/3/30 0030.
 */
public class LiveFragmentAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
    private String[]  fragmentName ={};
    private Context context;
    private LayoutInflater inflater;
    public LiveFragmentAdapter(FragmentManager fragmentManager, Context context, String[] fragmentName) {
        super(fragmentManager);
        this.fragmentName = fragmentName;
        this.context = context;
        inflater = LayoutInflater.from(context);
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
                AttentionFragment attentionFragment = new AttentionFragment();
                fragment = attentionFragment;
                break;
            case 1:
//                Intent intent = new Intent(context, RoomActivity.class);
                HotFragment hot = new HotFragment();
                fragment = hot;
                break;
            case 2:
                NewLiveFragment newest = new NewLiveFragment();
                fragment = newest;
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
