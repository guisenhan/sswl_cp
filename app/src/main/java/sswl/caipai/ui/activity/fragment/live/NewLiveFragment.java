package sswl.caipai.ui.activity.fragment.live;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import sswl.caipai.R;
import sswl.caipai.constant.Constant;
import sswl.caipai.constant.MessageConstant;
import sswl.caipai.http.BaseHttp;
import sswl.caipai.model.LiveModel;
import sswl.caipai.model.Result;
import sswl.caipai.ui.activity.MainActivity;
import sswl.caipai.ui.activity.base.BaseFragment;
import sswl.caipai.ui.activity.play.NEVideoPlayerActivity;
import sswl.caipai.ui.adapter.listview.NewLiveAdapter;

/**
 * Created by Administrator on 2016/6/3 0003.
 */
@ContentView(R.layout.fragment_live_new)
public class NewLiveFragment extends BaseFragment{

    private Result<List<LiveModel>> result;
    private NewLiveAdapter newLiveAdapter;
    @ViewInject(R.id.gv_theme)
    private GridView gv_theme;

    @ViewInject(R.id.gv_new_live)
    private GridView gv_new_live;

    @Override
    public void initData() {
        super.initData();
        result = new Result<List<LiveModel>>();
        BaseHttp.sendPost(Constant.GET_NEWEST_LIVE,params,handler,getActivity());
    }

    @Override
    public void initViews() {
        super.initViews();
        gv_new_live.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent live = new Intent(getActivity(), NEVideoPlayerActivity.class);
                live.putExtra("liveInfo",result.getData().get(position));
                live.putExtra("rtmp",result.getData().get(position).getRtmp_pull_url());
                live.putExtra("roomid",result.getData().get(position).getRoomid());
                live.putExtra("cid",result.getData().get(position).getCid());
                live.putExtra("liveInfo",result.getData().get(position));
                startActivity(live);
            }
        });
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
                    newLiveAdapter = new NewLiveAdapter(MainActivity.mainActivity,result.getData());
                    gv_new_live.setAdapter(newLiveAdapter);
                    }
                    break;
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        result = new Result<List<LiveModel>>();
        BaseHttp.sendPost(Constant.GET_NEWEST_LIVE,params,handler,getActivity());
    }
}
