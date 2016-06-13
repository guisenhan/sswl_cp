package sswl.caipai.http;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import cz.msebera.android.httpclient.Header;
import sswl.caipai.app.App;
import sswl.caipai.constant.Constant;
import sswl.caipai.constant.MessageConstant;
import sswl.caipai.model.Result;
import sswl.caipai.model.UserModel;
import sswl.caipai.ui.activity.base.BaseActivity;
import sswl.caipai.ui.activity.base.BaseFragment;
import sswl.caipai.util.CommonUtil;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
public class BaseHttp {

    public static void sendPost(String url, RequestParams params, final Handler handler, final Context context, final AlertDialog dialog){
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.post(url,params,new JsonHttpResponseHandler(){
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                CommonUtil.toast(context, "提交失败，请检查网络！");
                Log.e("on failure", responseString + throwable);
                dialog.dismiss();
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                CommonUtil.toast(context, "提交成功！");
                Message message = new Message();
                message.what=1;
                handler.sendMessage(message);
                dialog.dismiss();
            }
        });
    }
    public static void sendPost(String url,RequestParams params, final Handler handler, final Context context){
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.setTimeout(100000);
        httpClient.post(url,params,new JsonHttpResponseHandler(){
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                CommonUtil.toast(context, "提交失败，请检查网络！");
                Log.e("on failure", responseString + throwable);
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                    Log.d("response",response.toString());
                    Message message = new Message() ;
                    message.what=MessageConstant.MESSAGE_WHAT_GET_POST_INFO;
                    message.obj = response;
                    handler.sendMessage(message);
            }
        });
    }
    public static void sendPost(String url, RequestParams params, final Handler handler, final Context context, final int msgWhat){
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.setTimeout(100000);
        httpClient.post(url,params,new JsonHttpResponseHandler(){
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                CommonUtil.toast(context, "提交失败，请检查网络！");
                Log.e("on failure", responseString + throwable);
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("response",response.toString());
                Message message = new Message() ;
                message.what= msgWhat;
                message.obj = response;
                handler.sendMessage(message);
            }
        });
    }
    public static void getListPost(String url,RequestParams params, final Handler handler, final Context context){
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.setTimeout(100000);
        httpClient.post(url,params,new JsonHttpResponseHandler(){
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                CommonUtil.toast(context, "提交失败，请检查网络！");
                Log.e("on failure", responseString + throwable);
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e("response",response.toString());
                Message message = new Message() ;
                message.what=MessageConstant.MESSAGE_WHAT_GET_POST_INFO;
                try {
                    message.obj = response.get("data");
                }catch (JSONException E){
                    E.printStackTrace();
                }
                handler.sendMessage(message);
            }
        });
    }




    public static void doPost(String url,RequestParams params, final Context context){
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.post(url,params,new JsonHttpResponseHandler(){
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                CommonUtil.toast(context, "网络！");
                Log.e("on failure", responseString + throwable);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                CommonUtil.toast(context, ""+errorResponse);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    CommonUtil.toast(context, response.getString("message"));
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }
    public static void getUserInfo(final Context context){
        RequestParams params = new RequestParams();
        params.put("userid",context.getSharedPreferences("user", Activity.MODE_PRIVATE).getString("userid",""));
        params.put("appsign",context.getSharedPreferences("user", Activity.MODE_PRIVATE).getString("userid",""));
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.post(Constant.GET_USER_INFO,params,new JsonHttpResponseHandler(){
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                CommonUtil.toast(context, "网络！");
                Log.d("on failure", responseString + throwable);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                CommonUtil.toast(context, ""+errorResponse);
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                    Log.d("userinfo",response.toString());
                    App.user = new Gson().fromJson(String.valueOf(response),new TypeToken<Result<UserModel>>(){}.getType());
                 //   Log.e("user_info", response.getString("message")+ App.user.getData().getAccount());

            }
        });
    }

    public static void getUserInfo(final Context context, final Handler handler){
        RequestParams params = new RequestParams();
        params.put("userid",context.getSharedPreferences("user", Activity.MODE_PRIVATE).getString("userid",""));
        params.put("appsign",context.getSharedPreferences("user", Activity.MODE_PRIVATE).getString("userid",""));
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.post(Constant.GET_USER_INFO,params,new JsonHttpResponseHandler(){
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                CommonUtil.toast(context, "网络！");
                Log.d("on failure", responseString + throwable);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                CommonUtil.toast(context, ""+errorResponse);
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("userinfo",response.toString());
                App.user = new Gson().fromJson(String.valueOf(response),new TypeToken<Result<UserModel>>(){}.getType());
                //   Log.e("user_info", response.getString("message")+ App.user.getData().getAccount());
                Message message = new Message();
                message.what= MessageConstant.MESSAGE_WHAT_GET_USER_INFO;
                handler.sendMessage(message);
            }
        });
    }
    public static void getArea(String url,RequestParams params, final Handler handler, final Context context){
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.setTimeout(100000);
        httpClient.post(url,params,new JsonHttpResponseHandler(){
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                CommonUtil.toast(context, "提交失败，请检查网络！");
                Log.e("on failure", responseString + throwable);
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("response",response.toString());
                Message message = new Message() ;
                message.what= MessageConstant.MESSAGE_WHAT_GET_CITY;
                message.obj = response;
                handler.sendMessage(message);
            }
        });
    }

    public static void getPost(){

    }
}
