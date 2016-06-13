package sswl.caipai.ui.activity.fragment.live;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;

import sswl.caipai.ui.activity.play.NEVideoPlayerActivity;

/**
 * Created by Administrator on 2016/5/20 0020.
 */
public class LocalImageHolderView implements Holder<Integer> {



    private ImageView imageView;
    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(final Context context, int position, Integer data) {
        imageView.setImageResource(data);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NEVideoPlayerActivity.class);
                context.startActivity(intent);
            }
        });
    }


}

