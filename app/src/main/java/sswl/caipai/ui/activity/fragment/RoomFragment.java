package sswl.caipai.ui.activity.fragment;


import android.content.Intent;
import android.os.Bundle;

import com.shizhefei.fragment.LazyFragment;

import sswl.caipai.ui.activity.room.MediaPreviewActivity;

/**
 * Created by Administrator on 2016/5/12 0012.
 */
public class RoomFragment extends LazyFragment {

    private boolean i= true;
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
    }


    @Override
    protected void onResumeLazy() {
        super.onResumeLazy();
        if(i) {
            Intent intent = new Intent(getActivity(), MediaPreviewActivity.class);
            startActivity(intent);
            i = false;
        }
        }
}
