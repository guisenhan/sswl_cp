package sswl.caipai.ui.activity.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.shizhefei.view.viewpager.SViewPager;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import sswl.caipai.R;
import sswl.caipai.ui.activity.MainActivity;
import sswl.caipai.ui.activity.base.BaseFragment;
import sswl.caipai.ui.activity.search.SearchActivity;
import sswl.caipai.ui.adapter.fragment.LiveFragmentAdapter;

/**
 * Created by Administrator on 2016/5/12 0012.
 */
@ContentView(R.layout.fragment_live)
public class LiveFragment extends BaseFragment {
    @ViewInject(R.id.fiv_live_tab)
    private FixedIndicatorView fiv_live_tab;

    @ViewInject(R.id.svp_live)
    private SViewPager svp_live;

    @ViewInject(R.id.iv_search)
    private ImageView iv_search;

    private IndicatorViewPager indicatorvp;

    private String[] tab_name={"关注","热门","最新"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void initData() {
        indicatorvp = new IndicatorViewPager(fiv_live_tab,svp_live);
        indicatorvp.setAdapter(new LiveFragmentAdapter(getFragmentManager(),getActivity(),tab_name));
        indicatorvp.setIndicatorOnTransitionListener(new OnTransitionTextListener());
        indicatorvp.setCurrentItem(1,false);
        iv_search.setOnClickListener(this);
    }

    @Override
    public void initViews() {
        super.initViews();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.iv_search:
                Intent intent = new Intent(MainActivity.mainActivity, SearchActivity.class);

                startActivity(intent);
        }
    }
}
