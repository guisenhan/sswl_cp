package sswl.caipai.ui.adapter.listview;

import android.content.Context;
import android.media.Image;
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

import java.util.List;

import sswl.caipai.R;
import sswl.caipai.constant.Constant;
import sswl.caipai.constant.MessageConstant;
import sswl.caipai.http.BaseHttp;
import sswl.caipai.model.LiveModel;
import sswl.caipai.model.UserModel;

/**
 * Created by Administrator on 2016/5/31 0031.
 */
public class SearchAdapter extends BaseAdapter{
    private List<UserModel> _dataList;
    private Context context;
    private LayoutInflater inflater;
    private  RequestParams params;
    public SearchAdapter(Context context, List<UserModel> _dataList, RequestParams params) {
        this._dataList = _dataList;
        this.context=context;
        inflater = LayoutInflater.from(context);
        this.params = params;
    }

    @Override
    public int getCount() {
        return _dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return _dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder view ;
        if(convertView==null){
            view = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_search,null);
            view.iv_do_attention = (ImageView)convertView.findViewById(R.id.iv_do_attention);
            view.tv_user_name=(TextView)convertView.findViewById(R.id.tv_user_name);
            view.iv_user_photo = (RoundedImageView)convertView.findViewById(R.id.iv_user_photo);
            view.tv_sign=(TextView)convertView.findViewById(R.id.tv_user_sign);
            view.iv_sex =(ImageView) convertView.findViewById(R.id.iv_sex);
            view.iv_level =(ImageView)convertView.findViewById(R.id.iv_level);
            convertView.setTag(view);
        }else{
            view = (ViewHolder)convertView.getTag();
        }
        if(!StringUtils.isEmpty(_dataList.get(position).getIcon())) {
            ImageLoader.getInstance().displayImage(_dataList.get(position).getIcon(), view.iv_user_photo);
        }
        if(!StringUtils.isEmpty(_dataList.get(position).getNickname())){
            view.tv_user_name.setText(_dataList.get(position).getNickname());
        }
        view.iv_do_attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseHttp.sendPost(Constant.DO_ATTENTION_ACTION,params,handler,context);
            }
        });
        return convertView;
    }
    static class ViewHolder{
        TextView tv_user_name;
        RoundedImageView iv_user_photo;
        ImageView iv_do_attention;
        TextView tv_sign;
        ImageView iv_sex;
        ImageView iv_level;
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
