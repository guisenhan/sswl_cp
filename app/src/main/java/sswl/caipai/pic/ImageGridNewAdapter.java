package sswl.caipai.pic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;

import sswl.caipai.R;
import sswl.caipai.pic.img.ImageFetcher;
import sswl.caipai.util.CommonUtil;


public class ImageGridNewAdapter extends BaseAdapter {

	Activity act;
	List<ImageItem> dataList;
	Map<String, String> map = new LinkedHashMap<String, String>();
	List<String> path = new ArrayList<String>();

	ImageFetcher mImageFetcher;
	int imgWidth;
	int selectTotal = 0;
	Handler mHandler;

	private int mMaxCount = Bimp.maxnum;

	public ImageGridNewAdapter(Activity act, List<ImageItem> list, Handler mHandler) {
		this.act = act;
		dataList = list;
		int w = CommonUtil.getWidth(act);
		mImageFetcher = new ImageFetcher(act, w / 4);
		imgWidth = (w - CommonUtil.dip2px(act, 20)) / 3;
		this.mHandler = mHandler;
	}

	public void setMaxCount(int c) {
		mMaxCount = c;
	}

	@Override
	public int getCount() {
		int count = 0;
		if (dataList != null) {
			count = dataList.size();
		}
		return count;

	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	class Holder {
		private ImageView iv;
		private CheckBox mCheckBox;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final Holder holder;
		if (convertView == null) {
			holder = new Holder();
			convertView = View.inflate(act, R.layout.sel_img_item, null);
			holder.iv = (ImageView) convertView.findViewById(R.id.image);
			holder.mCheckBox = (CheckBox) convertView.findViewById(R.id.child_checkbox);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		final ImageItem item = dataList.get(position);
		holder.mCheckBox.setChecked(item.isSelected);
		// holder.iv.setTag(item.imagePath);
		holder.iv.setLayoutParams(new LayoutParams(imgWidth, imgWidth));
		mImageFetcher.loadImage(item.imagePath, holder.iv, item.thumbnailPath);
		holder.mCheckBox.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (mMaxCount > 1) {
					if ((Bimp.drr.size() + selectTotal) < mMaxCount) {
						if (holder.mCheckBox.isChecked()) {
							item.isSelected = true;
							selectTotal++;
							map.put(item.imagePath, item.imagePath);
						} else {
							item.isSelected = false;
							selectTotal--;
							map.remove(item.imagePath);
						}
					} else if ((Bimp.drr.size() + selectTotal) >= mMaxCount) {
						if (item.isSelected) {
							item.isSelected = false;
							holder.mCheckBox.setChecked(false);
							selectTotal--;
							map.remove(item.imagePath);
						} else {
							item.isSelected = false;
							holder.mCheckBox.setChecked(false);
							mHandler.sendEmptyMessage(0);
						}
					}
				} else {
					if (selectTotal < 1) {
						if (holder.mCheckBox.isChecked()) {
							item.isSelected = true;
							Bimp.sdrr = item.imagePath;
							selectTotal++;
						}
					} else {
						if (item.isSelected) {
							item.isSelected = false;
							holder.mCheckBox.setChecked(false);
							selectTotal = 0;
							Bimp.sdrr = "";
						} else {
							item.isSelected = false;
							holder.mCheckBox.setChecked(false);
							mHandler.sendEmptyMessage(0);
						}
					}
				}
				mHandler.sendMessage(mHandler.obtainMessage(1, selectTotal));
			}
		});
		holder.iv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (mMaxCount > 1) {
					if ((Bimp.drr.size() + selectTotal) < mMaxCount) {
						if (holder.mCheckBox.isChecked()) {
							holder.mCheckBox.setChecked(false);
							selectTotal--;
							item.isSelected = false;
							map.remove(item.imagePath);
						} else {
							holder.mCheckBox.setChecked(true);
							selectTotal++;
							item.isSelected = true;
							map.put(item.imagePath, item.imagePath);
						}
					} else if ((Bimp.drr.size() + selectTotal) >= mMaxCount) {
						if (item.isSelected) {
							item.isSelected = false;
							holder.mCheckBox.setChecked(false);
							selectTotal--;
							map.remove(item.imagePath);
						} else {
							item.isSelected = false;
							holder.mCheckBox.setChecked(false);
							mHandler.sendEmptyMessage(0);
						}
					}
				} else {
					if (selectTotal < 1) {
						if (holder.mCheckBox.isChecked()) {
							holder.mCheckBox.setChecked(false);
							item.isSelected = false;
							selectTotal = 0;
							Bimp.sdrr = "";
						} else {
							holder.mCheckBox.setChecked(true);
							item.isSelected = true;
							Bimp.sdrr = item.imagePath;
							selectTotal++;
						}
					} else {
						if (item.isSelected) {
							item.isSelected = false;
							holder.mCheckBox.setChecked(false);
							selectTotal = 0;
							Bimp.sdrr = "";
						} else {
							item.isSelected = false;
							holder.mCheckBox.setChecked(false);
							mHandler.sendEmptyMessage(0);
						}
					}
				}
				mHandler.sendMessage(mHandler.obtainMessage(1, selectTotal));
			}
		});
		return convertView;
	}

}
