package sswl.caipai.ui.activity.fragment.live;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.paging.listview.PagingListView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import lib.homhomlib.design.SlidingLayout;
import sswl.caipai.R;
import sswl.caipai.constant.Constant;
import sswl.caipai.constant.MessageConstant;
import sswl.caipai.http.BaseHttp;
import sswl.caipai.model.LiveModel;
import sswl.caipai.model.ResultList;
import sswl.caipai.ui.activity.MainActivity;
import sswl.caipai.ui.activity.base.BaseFragment;
import sswl.caipai.ui.activity.play.NEVideoPlayerActivity;
import sswl.caipai.ui.adapter.listview.LiveAdapter;
import sswl.caipai.util.CommenUtil;

/**
 * Created by Administrator on 2016/5/12 0012.
 */

@ContentView(R.layout.fragment_live_hot)
public class HotFragment extends BaseFragment {
    private ConvenientBanner<Integer> live_banner;


    @ViewInject(R.id.lv_live_hot)
    private PagingListView lv_live_hot;

    @ViewInject(R.id.rv_no_data)
    private RelativeLayout rv_no_data;

    @ViewInject(R.id.sl_hot)
    private SlidingLayout sl_hot;

    private List<Integer> localImages = new ArrayList<>();

    private ResultList<LiveModel> result;
    private boolean can_refresh = false; //判断向下滑动的距离是否达到刷新的要求

    private boolean first_load = true; //判断是否为第一次获取数据

    private int pageIndex=0;
    private int pageSize=3;
    private LiveAdapter liveAdapter;
    @Override
    public void initData() {
        super.initData();
        result = new ResultList<LiveModel>();
        liveAdapter = new LiveAdapter();
        lv_live_hot.setAdapter(liveAdapter);

        lv_live_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        lv_live_hot.setHasMoreItems(true);
        lv_live_hot.setPagingableListener(new PagingListView.Pagingable() {
            @Override
            public void onLoadMoreItems() {
                    params.put("pageIndex",pageIndex);
                    params.put("pageSize",pageSize);
                    BaseHttp.sendPost(Constant.GET_HOT_LIVE,params,handler,getActivity());
            }
        });

        sl_hot.setSlidingListener(new SlidingLayout.SlidingListener() {
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
                    liveAdapter.removeAllItems();
                    pageIndex = 0;
                    params.put("pageIndex",0);
                    BaseHttp.sendPost(Constant.GET_HOT_LIVE, params, handler, getActivity(),5);
                }
            }
            @Override
            public void onSlidingChangePointer(View view, int pointerId) {
                Log.e("onSlidingChangePointer"," " + pointerId);
            }
        });
    }

    @Override
    public void initViews() {
        super.initViews();
        loadData();
        View view = LayoutInflater.from(MainActivity.mainActivity).inflate(R.layout.header_fragment_hot,null);
        live_banner = (ConvenientBanner<Integer>)view.findViewById(R.id.banner_hot);
        lv_live_hot.addHeaderView(view);
        live_banner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, localImages);
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
    }

    private void loadData(){
        for(int i =0;i < 3; i++){
            localImages.add(CommenUtil.getResId("banner_"+i,R.mipmap.class));
        }
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MessageConstant.MESSAGE_WHAT_GET_POST_INFO:
                    Log.e("msg obj",String.valueOf(msg.obj));
                    result = new Gson().fromJson(String.valueOf(msg.obj),new TypeToken<ResultList<LiveModel>>(){}.getType());
                    if(result.getData()!=null){
                        rv_no_data.setVisibility(View.GONE);
                        lv_live_hot.setVisibility(View.VISIBLE);
                        lv_live_hot.onFinishLoading(true,result.getData());
                        pageIndex=pageIndex+result.getData().size();
                        params.put("pageIndex",pageIndex);
                        first_load = false; //判断是否是第一次获取数据
                    }else{
                        lv_live_hot.setHasMoreItems(false);
                        lv_live_hot.onFinishLoading(false,null);
                        if(first_load) {  //如果是第一次获取数据 并且获取数据为空 则展示空界面
                            rv_no_data.setVisibility(View.VISIBLE);
                            lv_live_hot.setVisibility(View.GONE);
                        }
                    }
                    break;
            }
        }
    };

}
