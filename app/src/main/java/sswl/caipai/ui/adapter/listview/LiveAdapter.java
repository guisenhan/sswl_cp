package sswl.caipai.ui.adapter.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.lazylibrary.util.StringUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.paging.listview.PagingBaseAdapter;

import java.util.List;

import sswl.caipai.R;
import sswl.caipai.model.LiveModel;

/**
 * Created by Administrator on 2016/5/31 0031.
 */
public class LiveAdapter extends PagingBaseAdapter<LiveModel>{


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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder view ;
        if(convertView==null){
            view = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_live,null);
            view.iv_live_icon = (ImageView)convertView.findViewById(R.id.iv_live_icon);
            view.tv_eye_number=(TextView)convertView.findViewById(R.id.tv_room_number);
            view.iv_live_photo = (RoundedImageView)convertView.findViewById(R.id.iv_live_photo);
            view.tv_live_name=(TextView)convertView.findViewById(R.id.tv_live_name);
            convertView.setTag(view);
        }else{
            view = (ViewHolder)convertView.getTag();
        }
        if(!StringUtils.isEmpty(items.get(position).getIcon())) {
            if(view.iv_live_photo.getTag()!=null){
               if(!view.iv_live_photo.getTag().equals(items.get(position).getIcon())){
                   ImageLoader.getInstance().displayImage(items.get(position).getIcon(), view.iv_live_icon);
                   ImageLoader.getInstance().displayImage(items.get(position).getIcon(), view.iv_live_photo);
                   view.iv_live_photo.setTag(items.get(position).getIcon());
               }
            }else{
                ImageLoader.getInstance().displayImage(items.get(position).getIcon(), view.iv_live_icon);
                ImageLoader.getInstance().displayImage(items.get(position).getIcon(), view.iv_live_photo);
                view.iv_live_photo.setTag(items.get(position).getIcon());
            }
        }
        if(!StringUtils.isEmpty(items.get(position).getName())){
            view.tv_live_name.setText(items.get(position).getName());
        }
        if(!StringUtils.isEmpty(items.get(position).getOnline_count()))
            view.tv_eye_number.setText(items.get(position).getOnline_count());
        return convertView;
    }
    static class ViewHolder{
        TextView tv_live_name;
        RoundedImageView iv_live_photo;
        ImageView iv_live_icon;
        TextView tv_eye_number;
    }
}
