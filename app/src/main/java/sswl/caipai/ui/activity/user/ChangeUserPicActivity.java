package sswl.caipai.ui.activity.user;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lazylibrary.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.io.IOException;

import sswl.caipai.R;
import sswl.caipai.app.App;
import sswl.caipai.constant.Constant;
import sswl.caipai.constant.MessageConstant;
import sswl.caipai.http.BaseHttp;
import sswl.caipai.model.Request;
import sswl.caipai.model.Result;
import sswl.caipai.pic.Bimp;
import sswl.caipai.pic.MoreUserPicNewActivity;
import sswl.caipai.ui.activity.base.BaseActivity;
import sswl.caipai.util.CommonUtil;

@ContentView(R.layout.activity_change_user_pic)
public class ChangeUserPicActivity extends BaseActivity {
    @ViewInject(R.id.iv_user_photo)
    private ImageView iv_user_photo;

    @ViewInject(R.id.tv_choose_from_album)
    private TextView tv_album;

    @ViewInject(R.id.tv_take_picture)
    private TextView tv_take_picture;

    @ViewInject(R.id.iv_cancel)
    private ImageView iv_cancel;

   private String photoPath;


    private Result<String> result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        super.initData();
        if(!StringUtils.isEmpty(App.user.getData().getIcon()))
        ImageLoader.getInstance().displayImage(getIntent().getStringExtra("user_photo"), iv_user_photo);
    }

    @Override
    public void initViews() {
        super.initViews();
        iv_cancel.setOnClickListener(this);
        tv_album.setOnClickListener(this);
        tv_take_picture.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_cancel:
                finish();
                break;
            case R.id.tv_choose_from_album:
                Intent intent = new Intent(this, MoreUserPicNewActivity.class);
                intent.putExtra("type", "2");
                startActivityForResult(intent, 10);
                break;
            case R.id.tv_take_picture:
                openPhoto();
                break;
            default:
                break;
        }
    }

    private void openPhoto() {
        File tempFile = new File(Constant.CACHE_DIR);
        if (!tempFile.exists()) {
            tempFile.mkdir();
        }
        File tempFile2 = new File(Constant.CACHE_DIR_PHOTO);
        if (!tempFile2.exists()) {
            tempFile2.mkdir();
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            Log.v("photo", "SD card is not avaiable/writeable right now.");
            Toast.makeText(ChangeUserPicActivity.this, "SD卡不可用", Toast.LENGTH_SHORT).show();
            return;
        }
        File file = new File(Constant.CACHE_DIR_PHOTO + File.separator + String.valueOf(System.currentTimeMillis()) + ".jpg");
        photoPath = file.getPath();
        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 9);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 9 && resultCode == Activity.RESULT_OK) { // 系统相机拍照成功
            Bimp.sdrr = photoPath;
            try {
                params.put("filename",new File(photoPath));
            }catch (IOException e) {
                e.printStackTrace();
            }
            BaseHttp.sendPost(Constant.UPLOAD_FILE,params,upFile,ChangeUserPicActivity.this);
        } else if (requestCode == 10 && resultCode == 11) { // 选择照片并按确定键
              //  titleImg();
            try {
                params.put("filename",new File(Bimp.sdrr));
            }catch (IOException e) {
                e.printStackTrace();
            }
            BaseHttp.sendPost(Constant.UPLOAD_FILE,params,upFile,ChangeUserPicActivity.this);
        }
    }
    Handler upFile = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MessageConstant.MESSAGE_WHAT_GET_POST_INFO:
                    result = new Gson().fromJson(String.valueOf(msg.obj),new TypeToken<Result>(){}.getType());
                    params.put("icon",result.getData());
                    BaseHttp.sendPost(Constant.EDIT_USER_INFO,params,UIHandler,ChangeUserPicActivity.this);
                    break;
                default:
                    break;
            }
        }
    };
    Handler UIHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MessageConstant.MESSAGE_WHAT_GET_POST_INFO:
                    ImageLoader.getInstance().displayImage("file:///"+Bimp.sdrr,iv_user_photo);
                    BaseHttp.getUserInfo(ChangeUserPicActivity.this);
                    break;
                default:
                    break;
            }
        }
    };
}
