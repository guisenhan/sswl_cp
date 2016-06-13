package sswl.caipai.ui.adapter.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.lazylibrary.util.StringUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import sswl.caipai.R;
import sswl.caipai.model.LiveModel;

/**
 * Created by Administrator on 2016/6/3 0003.
 */
public class NewLiveAdapter extends BaseAdapter {
    private List<LiveModel> _dataList;
    private Context context;
    private LayoutInflater inflater;

    public NewLiveAdapter(Context context,List<LiveModel> _dataList) {
        this.context = context;
        this._dataList = _dataList;
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
            convertView = inflater.inflate(R.layout.item_new_live,null);
            view.iv_live_icon = (ImageView)convertView.findViewById(R.id.iv_live_new);
            view.tv_live_name = (TextView) convertView.findViewById(R.id.tv_live_name);
            view.iv_level_icon =(ImageView)convertView.findViewById(R.id.iv_icon_level);
            convertView.setTag(view);
        }else{
            view = (ViewHolder)convertView.getTag();
        }
       // view.tv_live_name.setText(_dataList.get(position).getName());
        if(!StringUtils.isEmpty(_dataList.get(position).getIcon()))
            ImageLoader.getInstance().displayImage(_dataList.get(position).getIcon(),view.iv_live_icon);
        return convertView;
    }
    static class ViewHolder{
        TextView tv_live_name;
        ImageView iv_live_icon;
        ImageView iv_level_icon;
    }
}
