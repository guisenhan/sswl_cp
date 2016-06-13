package sswl.caipai.ui.activity.search;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.github.lazylibrary.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import sswl.caipai.R;
import sswl.caipai.constant.Constant;
import sswl.caipai.constant.MessageConstant;
import sswl.caipai.http.BaseHttp;
import sswl.caipai.model.Result;
import sswl.caipai.model.UserModel;
import sswl.caipai.ui.activity.base.BaseActivity;
import sswl.caipai.ui.activity.otherUser.PersonalActivity;
import sswl.caipai.ui.adapter.listview.SearchAdapter;


@ContentView(R.layout.activity_search)
public class SearchActivity extends BaseActivity {

    @ViewInject(R.id.ed_search_content)
    private EditText ed_search_content;

    @ViewInject(R.id.tv_search)
    private TextView tv_search;

    @ViewInject(R.id.lv_search)
    private ListView lv_search;

    private Result<List<UserModel>> result;
    private SearchAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        super.initData();

        tv_search.setOnClickListener(this);
        lv_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchActivity.this, PersonalActivity.class);
                intent.putExtra("user",result.getData().get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_search:
                validate();
                break;
        }
    }

    @Override
    public void initViews() {
        super.initViews();
    }
    private void validate(){
        result = new Result<List<UserModel>>();
        if(StringUtils.isEmpty(ed_search_content.getText().toString()))
           return;
        params.put("queryString",ed_search_content.getText().toString());
        BaseHttp.sendPost(Constant.DO_SEARCH_USER,params,handler,this);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MessageConstant.MESSAGE_WHAT_GET_POST_INFO:
                    result = new Gson().fromJson(String.valueOf(msg.obj),new TypeToken<Result<List<UserModel>>>(){}.getType());
                    if(result.getData()!=null){
                        adapter = new SearchAdapter(SearchActivity.this,result.getData(),params);
                        lv_search.setAdapter(adapter);
                    }
                    break;
            }
        }
    };
}
