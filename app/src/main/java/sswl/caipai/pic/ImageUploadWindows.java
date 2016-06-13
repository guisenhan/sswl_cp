/**
 * 
 */
package sswl.caipai.pic;

import android.app.Activity;
import android.widget.PopupWindow;

/**
 * @author caojinhua
 * 
 */
public abstract class ImageUploadWindows extends PopupWindow {

//	String path;
//	public ImageUploadWindows(Context mContext, View parent) {
//		View view = View
//				.inflate(mContext, R.layout.more_myinfo_photo_pop, null);
//		setWidth(LayoutParams.MATCH_PARENT);
//		setHeight(LayoutParams.MATCH_PARENT);
//		setBackgroundDrawable(new BitmapDrawable());
//		setFocusable(true);
//		setOutsideTouchable(true);
//		setContentView(view);
//		showAtLocation(parent, Gravity.BOTTOM, 0, 0);
//		update();
//		view.findViewById(R.id.other_text).setOnClickListener(
//				new OnClickListener() {
//					@Override
//					public void onClick(View arg0) {
//						dismiss();
//					}
//				});
//		Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_camera);
//		Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_photo);
//		Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_cancel);
//		bt1.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				dismiss();
//				openPhoto();
//			}
//		});
//		bt2.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				dismiss();
//				Intent intent = new Intent(GetCurActivity(),
//						MoreUserPicNewActivity.class);
//				intent.putExtra("type", "2");
//				GetCurActivity().startActivityForResult(intent, 10);
//			}
//		});
//		bt3.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				dismiss();
//			}
//		});
//	}
//
//	private void openPhoto() {
//		File tempFile = new File(Constant.CACHE_DIR);
//		if (!tempFile.exists()) {
//			tempFile.mkdir();
//		}
//		File tempFile2 = new File(Constant.CACHE_DIR_PHOTO);
//		if (!tempFile2.exists()) {
//			tempFile2.mkdir();
//		}
//		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//		String sdStatus = Environment.getExternalStorageState();
//		if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
//			Log.v("photo", "SD card is not avaiable/writeable right now.");
//			Toast.makeText(GetCurActivity().getApplicationContext(), "SD卡不可用",
//					Toast.LENGTH_SHORT).show();
//			return;
//		}
//		File file = new File(Constant.CACHE_DIR_PHOTO + File.separator
//				+ String.valueOf(System.currentTimeMillis()) + ".jpg");
//		path = file.getPath();
//		intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
//		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
//		intent.putExtra("return-data", true);
//		GetCurActivity().startActivityForResult(intent, 9);
//	}
//
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		GetCurActivity().onActivityResult(requestCode, resultCode, data);
//		if (requestCode == 9 && resultCode == Activity.RESULT_OK) { // 系统相机拍照成功
//			Bimp.sdrr = path;
//			titleImg();
//		} else if (requestCode == 10 && resultCode == 11) { // 选择照片并按确定键
//			titleImg();
//		}
//	}
//
//	public void titleImg() {
//		new Thread(new Runnable() {
//			public void run() {
//				try {
//					String path = Bimp.sdrr;
//					if (path != null && !"".equals(path)) {
//						Bimp.sbitmap = Bimp.revitionImageSize(path);
//
//						String newStr = path.substring(
//								path.lastIndexOf("/") + 1,
//								path.lastIndexOf("."));
//						CommonUtil.saveBitmap(Bimp.sbitmap, newStr);
//						handler.sendEmptyMessage(2);
//					}
//				} catch (IOException e) {
//				}
//			}
//		}).start();
//	}
//
//	/**
//	 * 上传图片
//	 */
//	public void uploadImgs() {
//		new Thread(new Runnable() {
//			public void run() {
//				try {
//					uploadUrl = "";
//					String path = Bimp.sdrr;
//					if (path != null && !"".equals(path)) { // 上传封面
//						String newStr = path.substring(
//								path.lastIndexOf("/") + 1,
//								path.lastIndexOf("."));
//						File tempFile = new File(Constant.CACHE_DIR_SD
//								+ File.separator + newStr + ".JPEG");
//						String info = CommonUtil.uploadFile(tempFile,
//								Constant.API_SERVER_UPLOAD_IMG, "filename");
//						JSONObject imgJson = new JSONObject(info);
//						String img_url = imgJson.getString("img_url");
//						uploadUrl = img_url;
//					}
//					handler.sendMessage(handler.obtainMessage(10));
//					return;
//				} catch (Exception e) {
//				}
//				handler.sendMessage(handler.obtainMessage(11));
//			}
//		}).start();
//	}
//
//	Handler handler = new Handler() {
//		@Override
//		public void handleMessage(Message msg) {
//			if (msg.what == 2) {
//				// 拍照显示
//				if (Bimp.sbitmap != null) {
//					account_sfz_pic.setImageBitmap(Bimp.sbitmap);
//				}
//			} else if (msg.what == 10) {
//				submitsfz();
//			} else if (msg.what == 0) {
//				Toast.makeText(AccountUploadSfz.this, "绑定成功",
//						Toast.LENGTH_SHORT).show();
//				// String msgs = (String) msg.obj;
//				// CommonUtil.toast(AccountUploadSfz.this, msgs);
//				finish();
//			} else if (msg.what == 1) {
//				account_sfz_save.setClickable(true);
//				Toast.makeText(AccountUploadSfz.this, "绑定失败",
//						Toast.LENGTH_SHORT).show();
//			}
//		}
//
//		private void submitsfz() {
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					String path = Constant.API_BINDSFZ;
//					Map<String, Object> params = new HashMap<String, Object>();
//					params.put("auserid", UserUtil.user.getId());
//					params.put("realname", name_et.getText().toString().trim());
//					params.put("idcard", "430000200808089988");
//					params.put("idcard_img1", uploadUrl);
//					BaseHttpResult re = BaseHttpResult.parseComm(CommonUtil
//							.sendPostRequest(path, params));
//					if (re.isSuccess()) {
//						handler.sendMessage(handler.obtainMessage(0,
//								re.getMsg()));
//					} else {
//						handler.sendEmptyMessage(1);
//					}
//				}
//			}).start();
//		}
//	};
//	
	abstract Activity GetCurActivity();
}
