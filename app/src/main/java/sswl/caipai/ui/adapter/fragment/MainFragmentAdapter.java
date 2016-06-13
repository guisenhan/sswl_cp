package sswl.caipai.ui.adapter.fragment;

import android.content.Context;
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

import org.xutils.common.util.DensityUtil;

import sswl.caipai.R;
import sswl.caipai.ui.activity.fragment.LiveFragment;
import sswl.caipai.ui.activity.fragment.MineFragment;
import sswl.caipai.ui.activity.fragment.RoomFragment;


/**
 * Created by Administrator on 2016/3/30 0030.
 */
public class MainFragmentAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
    private String[]  fragmentName ={};
    private int[] fragment_icon = {};
    private Context context;
    private LayoutInflater inflater;
    public MainFragmentAdapter(FragmentManager fragmentManager, Context context,String[] fragmentName, int[] fragment_icon) {
        super(fragmentManager);
        this.fragment_icon = fragment_icon;
        this.fragmentName = fragmentName;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    public MainFragmentAdapter(FragmentManager fragmentManager, Context context, int[] fragment_icon) {
        super(fragmentManager);
        this.fragment_icon = fragment_icon;
        this.fragmentName = new String[fragment_icon.length];
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
           view = (ImageView)inflater.inflate(R.layout.tab_bottom,viewGroup,false);
        }
        ImageView image = (ImageView)view;
        Drawable drawable = context.getResources().getDrawable(fragment_icon[i]);
        image.setImageDrawable(drawable);
        return image;
    }

    @Override
    public Fragment getFragmentForPage(int i) {
        Fragment fragment = new Fragment();
        switch (i){
            case 0:
                LiveFragment live = new LiveFragment();
                fragment = live;
                break;
            case 2:
                MineFragment mine = new MineFragment();
                fragment = mine;
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
