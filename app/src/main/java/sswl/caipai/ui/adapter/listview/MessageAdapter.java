package sswl.caipai.ui.adapter.listview;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.lazylibrary.util.StringUtils;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMessage;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

import sswl.caipai.R;
import sswl.caipai.app.App;
import sswl.caipai.model.MessageModel;

/**
 * Created by Administrator on 2016/5/31 0031.
 */
public class MessageAdapter extends BaseAdapter{
    private Context context;
    private List<MessageModel> message;
    LayoutInflater inflater;
    public MessageAdapter(Context context, List<MessageModel> messages) {
        this.context = context;
        this.message = messages;
    }

    @Override
    public int getCount() {
        return message.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return message.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder view;
        if(convertView ==null){
            view = new ViewHolder();
            convertView = inflater.from(context).inflate(R.layout.item_im_message,null);
            view.message=(TextView) convertView.findViewById(R.id.tv_message);
            view.nickname=(TextView)convertView.findViewById(R.id.tv_nickname);
            convertView.setTag(view);
        }else{
            view = (ViewHolder)convertView.getTag();
        }
        view.nickname.setText(message.get(position).getNickName()+":");
        view.message.setText(message.get(position).getContent());

        return convertView;
    }
    static class ViewHolder{
        TextView message;
        TextView nickname;
        ImageView level;
    }
}
