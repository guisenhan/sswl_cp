package sswl.caipai.ui.adapter.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.github.lazylibrary.util.StringUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMember;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import sswl.caipai.R;
import sswl.caipai.constant.Constant;
import sswl.caipai.model.UserModel;

/**
 * Created by Administrator on 2016/6/2 0002.
 */
public class AudienceAdapter extends BaseAdapter{

    private List<ChatRoomMember> _dataList;
    private Context context;
    private LayoutInflater inflater;

    public AudienceAdapter(Context context,List<ChatRoomMember> _dataList) {
        this._dataList = _dataList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return super.areAllItemsEnabled();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder view ;
        if(convertView == null){
            view = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_audience,null);
            view.audience_photo = (RoundedImageView)convertView.findViewById(R.id.iv_audience_photo);
            convertView.setTag(view);
        }else{
            view = (ViewHolder)convertView.getTag();
        }
        if(!StringUtils.isEmpty(_dataList.get(position).getAvatar()))
            ImageLoader.getInstance().displayImage(_dataList.get(position).getAvatar(),view.audience_photo);

        return convertView;
    }
    static class ViewHolder{
        RoundedImageView audience_photo;
    }
}
