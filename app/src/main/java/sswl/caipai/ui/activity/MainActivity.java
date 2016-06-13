package sswl.caipai.ui.activity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.viewpager.SViewPager;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import sswl.caipai.R;
import sswl.caipai.ui.activity.base.BaseActivity;
import sswl.caipai.ui.activity.room.CreateLiveActivity;
import sswl.caipai.ui.activity.room.MediaPreviewActivity;
import sswl.caipai.ui.adapter.fragment.MainFragmentAdapter;

/**
 * @explaination the main Activity for this application ,contain some fragments
 * @info created by guisen.han on 2016.5.12
 *
* */
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewInject(R.id.fiv_home_tab)
    private FixedIndicatorView fiv_home_tab;

    @ViewInject(R.id.svp_main_view)
    private SViewPager viewPager;

    @ViewInject(R.id.iv_room)
    private ImageView iv_room;

    private IndicatorViewPager ivp_main;

    public static MainActivity mainActivity;
    private int[] home_icon = {R.drawable.tab_home_live,R.drawable.tab_home_room,R.drawable.tab_home_me};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = this;
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initViews() {
        super.initViews();
        ivp_main = new IndicatorViewPager(fiv_home_tab,viewPager);
        ivp_main.setAdapter(new MainFragmentAdapter(getSupportFragmentManager(),this,home_icon));
        ivp_main.setPageOffscreenLimit(0);
        ivp_main.setCurrentItem(0,false);
        viewPager.setOffscreenPageLimit(4);
        iv_room.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.iv_room:
                Intent intent = new Intent(this, CreateLiveActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onRestart(){
        super.onRestart();
        ivp_main.setCurrentItem(0,false);
    }

}
