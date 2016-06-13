package sswl.caipai.pic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import sswl.caipai.R;
import sswl.caipai.util.CommonUtil;


public class MoreUserPicNewActivity extends FragmentActivity {
	public static final String TAG = "MoreUserPicNewActivity";

	private List<ImageItem> dataList2;
	private GridView gridView;
	private ImageGridNewAdapter adapter;
	private AlbumHelper helper;
	private String type;
	private TextView submit_btn;
	private int mMaxCount = Bimp.maxnum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sel_img);
		helper = AlbumHelper.getHelper();
		helper.init(getApplicationContext());
		initData();
		initView();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		type = getIntent().getStringExtra("type");
		dataList2 = helper.getNewList();
	}

	/**
	 * 初始化view视图
	 */
	private void initView() {
		gridView = (GridView) findViewById(R.id.gridview);
		adapter = new ImageGridNewAdapter(MoreUserPicNewActivity.this, dataList2, mHandler);
		gridView.setAdapter(adapter);
		if ("1".equals(type)) {
			mMaxCount = Bimp.maxnum;
		} else if ("2".equals(type)) {
			mMaxCount = 1;
		}
		adapter.setMaxCount(mMaxCount);

		submit_btn = (TextView) findViewById(R.id.submit_btn);
		submit_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if ("1".equals(type)) { 
//					ArrayList<String> list = new ArrayList<String>();
//					Collection<String> c = adapter.map.values();
//					Iterator<String> it = c.iterator();
					if(adapter.map.size()!= Bimp.maxnum){
						CommonUtil.toast(MoreUserPicNewActivity.this, "请选择"+Bimp.maxnum+"张图片！");
						return;
					}else{
						submit_btn.setClickable(false);
						for (Map.Entry<String, String> entry : adapter.map.entrySet()){
						 if (Bimp.drr.size() < Bimp.maxnum) {
								Bimp.drr.add(entry.getValue());
							}
					 }}
				} else if ("2".equals(type)) {
					submit_btn.setClickable(false);
				}
				setResult(11);
				finish();
			}
		});
		findViewById(R.id.return_last).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				onBackPressed();
			}
		});
		findViewById(R.id.more_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MoreUserPicNewActivity.this, MoreUserPicNew2Activity.class);
				intent.putExtra("type", type);
				startActivityForResult(intent, 12);
			}
		});
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 12 && resultCode == 13) {

			setResult(11);
			finish();
		}
	}

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				Toast.makeText(MoreUserPicNewActivity.this, "最多选择" + mMaxCount + "张图片", Toast.LENGTH_SHORT).show();
				break;
			case 1:
				Integer selectTotal = (Integer) msg.obj;
				submit_btn.setText("完成(" + selectTotal + ")");
				break;
			}
		}
	};
}
