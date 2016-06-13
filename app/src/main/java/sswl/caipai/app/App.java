package sswl.caipai.app;

import android.app.Activity;
import android.app.Application;
import android.support.multidex.MultiDexApplication;
import android.util.DisplayMetrics;

import com.github.lazylibrary.util.StringUtils;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import org.xutils.x;

import java.util.prefs.Preferences;

import sswl.caipai.constant.Constant;
import sswl.caipai.http.BaseHttp;
import sswl.caipai.model.Result;
import sswl.caipai.model.UserModel;

/**
 * @explaination  application
 * @info Created by guisen.han on 2016/5/12 0012.
 */
public class App extends MultiDexApplication {
    protected static App mInstance;
    private DisplayMetrics displayMetrics = null;

    public App(){
        mInstance = this;
    }
    public static App getApp() {
        if (mInstance != null && mInstance instanceof App) {
            return mInstance;
        } else {
            mInstance = new App();
            mInstance.onCreate();
            return mInstance;
        }
    }
    public static Result<UserModel> user;
    @Override
    public void onCreate() {
        super.onCreate();
        user = new Result<UserModel>();
        BaseHttp.getUserInfo(this);
        x.Ext.init(this);
        NIMClient.init(this, null, null);
        initImageLoader();
        loginInfo();
    }

    private LoginInfo loginInfo() {
        // 从本地读取上次登录成功时保存的用户登录信息
        String account = getSharedPreferences("user",Activity.MODE_PRIVATE).getString("account","");
        String token =getSharedPreferences("token",Activity.MODE_PRIVATE).getString("account","");

        if (!StringUtils.isEmpty(account) && !StringUtils.isEmpty(token)) {
         //   DemoCache.setAccount(account.toLowerCase());
            return new LoginInfo(account, token);
        } else {
            return null;
        }
    }

    private void initImageLoader() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(false)
                .imageScaleType(ImageScaleType.EXACTLY)
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .defaultDisplayImageOptions(defaultOptions)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCache(new UnlimitedDiskCache(StorageUtils.getOwnCacheDirectory(this, Constant.APP_DOCUMENT)))
                .diskCacheSize(100 * 1024 * 1024).tasksProcessingOrder(QueueProcessingType.LIFO)
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024)).memoryCacheSize(2 * 1024 * 1024)
                .threadPoolSize(3)
                .build();
        ImageLoader.getInstance().init(config);
    }
    public float getScreenDensity() {
        if (this.displayMetrics == null) {
            setDisplayMetrics(mInstance.getResources().getDisplayMetrics());
        }
        return this.displayMetrics.density;
    }
    public int getScreenHeight() {
        if (this.displayMetrics == null) {
            setDisplayMetrics(mInstance.getResources().getDisplayMetrics());
        }
        return this.displayMetrics.heightPixels;
    }
    public int getScreenWidth() {
        if (this.displayMetrics == null) {
            setDisplayMetrics(getResources().getDisplayMetrics());
        }
        return this.displayMetrics.widthPixels;
    }
    public void setDisplayMetrics(DisplayMetrics DisplayMetrics) {
        this.displayMetrics = DisplayMetrics;
    }
    public int dp2px(float f) {
        return (int)(0.5F + f * getScreenDensity());
    }
    public int px2dp(float pxValue) {
        return (int) (pxValue / getScreenDensity() + 0.5f);
    }
    //获取应用的data/data/....File目录
    public String getFilesDirPath() {
        return getFilesDir().getAbsolutePath();
    }
    //获取应用的data/data/....Cache目录
    public String getCacheDirPath() {
        return getCacheDir().getAbsolutePath();
    }
}
