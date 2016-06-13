package sswl.caipai.pic;

import java.io.Serializable;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import sswl.caipai.R;
import sswl.caipai.pic.img.ImageFetcher;
import sswl.caipai.util.CommonUtil;

public class MoreUserPicNew2Activity extends FragmentActivity {

	public static final String EXTRA_IMAGE_LIST = "imagelist";
	private List<ImageBucket> dataList;
	private GridView gridView;
	private ImageBucketNewAdapter adapter;
	private AlbumHelper helper;
	private ImageFetcher mImageFetcher;
	private String type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sel_img2);
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
		dataList = helper.getImagesBucketList(false);
	}

	/**
	 * 初始化view视图
	 */
	private void initView() {
		gridView = (GridView) findViewById(R.id.gridview);
		mImageFetcher = new ImageFetcher(this, CommonUtil.getWidth(this) / 4);
		adapter = new ImageBucketNewAdapter(MoreUserPicNew2Activity.this, dataList, mImageFetcher);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(MoreUserPicNew2Activity.this, MoreUserPicNew3Activity.class);
				intent.putExtra("type", type);
				intent.putExtra(MoreUserPicNew2Activity.EXTRA_IMAGE_LIST,
						(Serializable) dataList.get(position).imageList);
				startActivityForResult(intent, 14);
			}
		});
		findViewById(R.id.return_last).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				onBackPressed();
			}
		});
//		findViewById(R.id.return_last_close).setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				if (!TextUtils.isEmpty(mFromType) && ChooseSinglePicActivity.FROM_TAG.equals(mFromType)) {
//					setResult(RESULT_OK);
//					finish();
//				} else {
//					Intent intent = new Intent(MoreUserPicNew2Activity.this, MoreUserPicNewActivity.class);
//					setResult(13, intent);
//					finish();
//				}
//			}
//		});
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 14 && resultCode == 15) {
			Intent intent = new Intent(MoreUserPicNew2Activity.this, MoreUserPicNewActivity.class);
			setResult(13, intent);
			finish();
		}
	}
}
