package sswl.caipai.widget;

import android.content.Context;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import sswl.caipai.R;

/**
 * Created by Administrator on 2016/5/31 0031.
 */
public class CamraPopWindow extends PopupWindow{
    View.OnClickListener share_click;
    View.OnClickListener flash_light_click;
    View.OnClickListener turn_click;

    public CamraPopWindow(Context context) {
        super(context);
       View view = View.inflate(context, R.layout.popwindow_camera,null);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);
        view.findViewById(R.id.content_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        view.findViewById(R.id.tv_share).setOnClickListener(this.share_click);
        view.findViewById(R.id.tv_flash_light).setOnClickListener(this.flash_light_click);
        view.findViewById(R.id.tv_turn).setOnClickListener(this.turn_click);
    }

    public void setOnclickListener(View.OnClickListener turn_click,View.OnClickListener flash_light_click,View.OnClickListener share_clcik){

    }
    public void setTurn_click(View.OnClickListener turn_click){
        this.turn_click = turn_click;
    }
    public void setShare_click(View.OnClickListener share_click){
        this.share_click = share_click;
    }
    public void setFlash_light_click(View.OnClickListener flash_light_click){
        this.flash_light_click = flash_light_click;
    }
    @Override
    public void dismiss() {
        super.dismiss();
    }
}
