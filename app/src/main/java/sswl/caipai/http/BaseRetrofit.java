package sswl.caipai.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sswl.caipai.constant.Constant;

/**
 * Created by Administrator on 2016/6/12 0012.
 */
public class BaseRetrofit {

    public static Retrofit getRetrofit(){
     return new Retrofit.Builder()
                .baseUrl(Constant.SERVER_IP)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
