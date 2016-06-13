package sswl.caipai.http;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import sswl.caipai.constant.Constant;
import sswl.caipai.model.Result;

/**
 * Created by Administrator on 2016/6/12 0012.
 */
public interface UserInterface {
    @FormUrlEncoded
    @POST(Constant.GETCODE)
    Call<Result> getCode(@Field("mobile") String mobile,@Field("type") String type);
}
