package sswl.caipai.ui.activity.fragment.live;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.paging.listview.PagingListView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import lib.homhomlib.design.SlidingLayout;
import sswl.caipai.R;
import sswl.caipai.constant.Constant;
import sswl.caipai.constant.MessageConstant;
import sswl.caipai.http.BaseHttp;
import sswl.caipai.model.LiveModel;
import sswl.caipai.model.Result;
import sswl.caipai.model.ResultList;
import sswl.caipai.ui.activity.MainActivity;
import sswl.caipai.ui.activity.base.BaseFragment;
import sswl.caipai.ui.activity.play.NEVideoPlayerActivity;
import sswl.caipai.ui.adapter.listview.AttentionLiveAdapter;

/**
 * Created by Administrator on 2016/6/4 0004.
 */
@ContentView(R.layout.fragment_attention)
public class AttentionFragment extends BaseFragment{
    @ViewInject(R.id.sl_attention)
    private SlidingLayout sl_attention;

    @ViewInject(R.id.plv_attention)
    private PagingListView lv_live_attention;

    @ViewInject(R.id.rv_no_attention)
    private RelativeLayout rv_no_data;

    @ViewInject(R.id.tv_see_new_live)
    private TextView tv_see_new_live;

    private AttentionLiveAdapter adapter;

    private Result<List<LiveModel>>  result;
    private boolean can_refresh = false; //判断向下滑动的距离是否达到刷新的要求

    private boolean first_load = true; //判断是否为第一次获取数据
    private boolean hasMoreData = true;
    private int pageIndex=0;
    private int pageSize=3;
    @Override
    public void initData() {
        super.initData();
        adapter = new AttentionLiveAdapter(params);
        lv_live_attention.setAdapter(adapter);
        result = new Result<List<LiveModel>>();
        lv_live_attention.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), NEVideoPlayerActivity.class);
                intent.putExtra("rtmp",result.getData().get(position).getRtmp_pull_url());
                intent.putExtra("roomid",result.getData().get(position).getRoomid());
                intent.putExtra("cid",result.getData().get(position).getCid());
                intent.putExtra("liveInfo",result.getData().get(position));
                startActivity(intent);
            }
        });
        lv_live_attention.setHasMoreItems(true);
        lv_live_attention.setPagingableListener(new PagingListView.Pagingable() {
            @Override
            public void onLoadMoreItems() {
                    params.put("pageIndex",pageIndex);
                    params.put("pageSize",pageSize);
                    BaseHttp.sendPost(Constant.GET_HOT_LIVE,params,handler,getActivity());
            }
        });
        sl_attention.setSlidingListener(new SlidingLayout.SlidingListener() {
            @Override
            public void onSlidingOffset(View view, float delta) {
                Log.e("onSlidingOffset"," " + delta);
                if(delta>100){
                    can_refresh = true;
                }else{
                    can_refresh =false;
                }
            }
            @Override
            public void onSlidingStateChange(View view, int state) {
                Log.e("onSlidingStateChange"," " + state);
                if(state==1&&can_refresh) {
                    adapter.removeAllItems();
                    params.put("pageIndex",0);
                    pageIndex = 0;
                    BaseHttp.sendPost(Constant.GET_HOT_LIVE, params, handler, getActivity());
                }
            }
            @Override
            public void onSlidingChangePointer(View view, int pointerId) {
                Log.e("onSlidingChangePointer"," " + pointerId);
            }
        });
        tv_see_new_live.setOnClickListener(this);
    }

    @Override
    public void initViews() {
        super.initViews();
        View view = LayoutInflater.from(MainActivity.mainActivity).inflate(R.layout.header_fragment_attention,null);
        lv_live_attention.addHeaderView(view);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MessageConstant.MESSAGE_WHAT_GET_POST_INFO:
                    result = new Gson().fromJson(String.valueOf(msg.obj),new TypeToken<Result<List<LiveModel>>>(){}.getType());
                    if(result.getData()!=null){
                        rv_no_data.setVisibility(View.GONE);
                        lv_live_attention.setVisibility(View.VISIBLE);
                        adapter.notifyDataSetChanged();
                        lv_live_attention.onFinishLoading(true,result.getData());
                        pageIndex=pageIndex+result.getData().size();
                        params.put("pageIndex",pageIndex);
                        first_load = false; //判断是否是第一次获取数据
                    }else{
                        lv_live_attention.setHasMoreItems(false);
                        lv_live_attention.onFinishLoading(false,null);
                        if(first_load) {  //如果是第一次获取数据 并且获取数据为空 则展示空界面
                            rv_no_data.setVisibility(View.VISIBLE);
                            lv_live_attention.setVisibility(View.GONE);
                        }
                    }
                    break;
            }
        }
    };
}
