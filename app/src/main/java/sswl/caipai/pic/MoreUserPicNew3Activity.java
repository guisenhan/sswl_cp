package sswl.caipai.pic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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


public class MoreUserPicNew3Activity extends FragmentActivity {
	public static final String EXTRA_IMAGE_LIST = "imagelist";
	List<ImageItem> dataList;
	GridView gridView;
	ImageGridNewAdapter adapter;
//	AlbumHelper helper;
	String type;
	TextView submit_btn;
	private int mMaxCount = Bimp.maxnum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sel_img3);
		dataList = (List<ImageItem>) getIntent().getSerializableExtra(EXTRA_IMAGE_LIST);
		type = getIntent().getStringExtra("type");
		initView();
	}

	/**
	 * 初始化view视图
	 */
	private void initView() {
		gridView = (GridView) findViewById(R.id.gridview);
		adapter = new ImageGridNewAdapter(MoreUserPicNew3Activity.this, dataList, mHandler);
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
			public void onClick(View arg0) { // 点击完成
				submit_btn.setClickable(false);

				if ("1".equals(type)) { // 社区帖子发布，多选
					ArrayList<String> list = new ArrayList<String>();
					Collection<String> c = adapter.map.values();
					Iterator<String> it = c.iterator();
					for (; it.hasNext();) {
						list.add(it.next());
					}
					for (int i = 0; i < list.size(); i++) {
						if (Bimp.drr.size() < Bimp.maxnum) {
							Bimp.drr.add(list.get(i));
						}
					}
				} else if ("2".equals(type)) {

				}

				Intent intent = new Intent(MoreUserPicNew3Activity.this, MoreUserPicNew2Activity.class);
				setResult(15, intent);
				finish();
			}
		});
		findViewById(R.id.return_last).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				onBackPressed();
			}
		});
	}

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				Toast.makeText(MoreUserPicNew3Activity.this, "最多选择" + mMaxCount + "张图片", Toast.LENGTH_SHORT).show();
				break;
			case 1:
				Integer selectTotal = (Integer) msg.obj;
				submit_btn.setText("完成(" + selectTotal + ")");
				break;
			}
		}
	};
}
