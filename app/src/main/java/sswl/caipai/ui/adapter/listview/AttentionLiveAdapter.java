package sswl.caipai.ui.adapter.listview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.lazylibrary.util.StringUtils;
import com.loopj.android.http.RequestParams;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.paging.listview.PagingBaseAdapter;

import java.util.List;

import sswl.caipai.R;
import sswl.caipai.constant.Constant;
import sswl.caipai.constant.MessageConstant;
import sswl.caipai.http.BaseHttp;
import sswl.caipai.model.LiveModel;

/**
 * Created by Administrator on 2016/5/31 0031.
 */
public class AttentionLiveAdapter extends PagingBaseAdapter<LiveModel>{

    private  RequestParams params;
    public AttentionLiveAdapter( RequestParams params) {

        this.params = params;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder view ;
        if(convertView==null){
            view = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attention_liver,null);
            view.iv_live_icon = (ImageView)convertView.findViewById(R.id.iv_live_photo);
            view.tv_theme=(TextView)convertView.findViewById(R.id.tv_liver_theme);
            view.iv_live_photo = (RoundedImageView)convertView.findViewById(R.id.iv_liver_photo);
            view.tv_live_name=(TextView)convertView.findViewById(R.id.tv_liver_name);
            view.tv_attention =(TextView)convertView.findViewById(R.id.tv_attention);
            convertView.setTag(view);
        }else{
            view = (ViewHolder)convertView.getTag();
        }
        if(!StringUtils.isEmpty(items.get(position).getIcon())) {
            ImageLoader.getInstance().displayImage(items.get(position).getIcon(), view.iv_live_icon);
            ImageLoader.getInstance().displayImage(items.get(position).getIcon(), view.iv_live_photo);
        }
        if(!StringUtils.isEmpty(items.get(position).getName())){
            view.tv_live_name.setText(items.get(position).getName());
        }
        view.tv_attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseHttp.sendPost(Constant.DO_CANCEL_ATTENTION_ACTION,params,handler,parent.getContext());
                items.remove(position);
            }
        });
        return convertView;
    }
    static class ViewHolder{
        TextView tv_live_name;
        RoundedImageView iv_live_photo;
        ImageView iv_live_icon;
        TextView tv_attention;
        TextView tv_theme;
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MessageConstant.MESSAGE_WHAT_GET_POST_INFO:
                    notifyDataSetChanged();
                    break;
            }
        }
    };
}
