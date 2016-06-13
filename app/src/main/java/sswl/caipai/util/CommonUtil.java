package sswl.caipai.util;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.DecimalFormat;
import java.util.UUID;

import sswl.caipai.constant.Constant;

//import han.guisen.hanchengserver.constant.Constant;
//import han.guisen.hanchengserver.pic.Bimp;


public class CommonUtil {
	private static final String LOGTAG = "CommonUtil";
	public static final boolean errorClose = true;

	/**
	 * 获取渠道号
	 * 
	 * @param mContext
	 * @return
	 */
	public static String getChannelID(Context mContext) {
		String channelId = "";
		try {
			ApplicationInfo ai = mContext.getPackageManager().getApplicationInfo(mContext.getPackageName(), PackageManager.GET_META_DATA);
			Object value = ai.metaData.get("CHANNEL_ID"); // manifest里面的name值
			if (value != null) {
				channelId = value.toString();
			}
		} catch (Exception e) {
		}
		return channelId;
	}
	
	/**
	 * @author guisen.han
	 * @explain get the device UUID;
	 * @express DeviceID+simSerialNmber++androidId 
	 * */
	public static String getUUID(Context context){
		TelephonyManager tm = (TelephonyManager) context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
		   String tmDevice, tmSerial, androidId;   
		  tmDevice = "" + tm.getDeviceId();  
		  tmSerial = "" + tm.getSimSerialNumber();   
		  androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(),android.provider.Settings.Secure.ANDROID_ID);   
		  UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());   
		  String uniqueId = deviceUuid.toString();
		  Log.d("debug","uuid="+uniqueId);
		  return uniqueId;
	}

	/**
	 * 获取mac地址
	 * 
	 * @param context
	 * @return
	 */
	public static String getMac(Context context) {
		WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		if (wifi != null) {
			WifiInfo info = wifi.getConnectionInfo();
			if (info != null) {
				return info.getMacAddress();
			}
		}
		String macSerial = "";
		String str = "";
		try {
			Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ");
			InputStreamReader ir = new InputStreamReader(pp.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (; null != str;) {
				str = input.readLine();
				if (str != null) {
					macSerial = str.trim();// 去空格
					break;
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return macSerial;
	}

	/**
	 * 判断是否有安装某个应用
	 */
	public static boolean canUseApp(Context context, String packageName) {
		PackageInfo packageInfo = null;
		try {
			packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
		} catch (NameNotFoundException e) {
			packageInfo = null;
		}
		return packageInfo != null;
	}

	/**
	 * 获取AndroidManifest.xml里的version
	 * 
	 * @param context
	 * @return
	 */
	public static String getPackageVersionName(Context context) {
		PackageManager pm = context.getPackageManager();
		if (pm == null)
			return "1.0";
		try {
			PackageInfo packInfo = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
			return packInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return "1.0";
	}

	/**
	 * 获取屏幕宽度
	 * 
	 * @param context
	 * @return
	 */
	public static int getWidth(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		return dm.widthPixels;
	}

	/**
	 * 获取屏幕高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getHeight(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		return dm.heightPixels;
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static void saveBitmap(Bitmap bm, String picName) {
		Log.e("", "保存图片");
		try {
			File tempFile = new File(Constant.CACHE_DIR);
			if (!tempFile.exists()) {
				tempFile.mkdir();
			}
			File tempFile2 = new File(Constant.CACHE_DIR_SD);
			if (!tempFile2.exists()) {
				tempFile2.mkdir();
			}
			File f;
			if ("logo".equals(picName)) {
				f = new File(Constant.CACHE_DIR_SD, picName);
			} else {
				f = new File(Constant.CACHE_DIR_SD, picName + ".JPEG");
			}
			if (f.exists()) {
				f.delete();
			}
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
//			bm.getGenerationId()
			out.flush();
			out.close();
			Log.e("", "已经保存");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 图片缓存key
	 * 
	 * @param data
	 * @return
	 */
	public static String getCacheImgUrl(String data) {
		if (data == null || data.lastIndexOf(".") < (data.lastIndexOf("/") + 1)) {
			return "";
		}
		String cacheKey = data.substring(data.lastIndexOf("/") + 1, data.lastIndexOf("."));
		return cacheKey;
	}

	public static String getImgJson(int index, String img_url, String width, String height) {
		String url = "{\"img_url\":\"" + img_url + "\",\"width\":\"" + width + "\",\"height\":\"" + height + "\"},";
		return url;
	}

	public static String getBigPhotoUrl(String img_url) {
		int last = img_url.lastIndexOf(".");
		if (last < 0)
			last = 0;
		String urlHead = img_url.substring(0, last);
		String urlFoot = img_url.substring(last);
		String url = urlHead + "_org" + urlFoot;
		return url;
	}

	/**
	 * 转换图片成圆形
	 * 
	 * @param bitmap
	 *            传入Bitmap对象
	 * @return
	 */
	public static Bitmap toRoundBitmap(Bitmap bitmap) {
		if (bitmap == null) {
			return null;
		}
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float roundPx;
		float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
		if (width <= height) {
			roundPx = width / 2;
			top = 0;
			bottom = width;
			left = 0;
			right = width;
			height = width;
			dst_left = 0;
			dst_top = 0;
			dst_right = width;
			dst_bottom = width;
		} else {
			roundPx = height / 2;
			float clip = (width - height) / 2;
			left = clip;
			right = width - clip;
			top = 0;
			bottom = height;
			width = height;
			dst_left = 0;
			dst_top = 0;
			dst_right = height;
			dst_bottom = height;
		}
		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);
		final Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);
		final RectF rectF = new RectF(dst);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, src, dst, paint);
		return output;
	}

	public static void toast(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 删除指定目录下文件及目录
	 * 
	 * @param deleteThisPath
	 * @return
	 */
	public static void deleteFolderFile(String filePath, boolean deleteThisPath) {
		if (!TextUtils.isEmpty(filePath)) {
			File file = new File(filePath);
			if (file.isDirectory()) {// 处理目录
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					deleteFolderFile(files[i].getAbsolutePath(), true);
				}
			}
			if (deleteThisPath) {
				if (!file.isDirectory()) {// 如果是文件，删除
					file.delete();
				} else {// 目录
					if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
						file.delete();
					}
				}
			}
		}
	}

//	public static void clearCache() {
//		try {
//			Bimp.bmp.clear();
//			Bimp.drr.clear();
//			Bimp.max = 0;
//			Bimp.sdrr = "";
//			if (Bimp.sbitmap != null) {
//				Bimp.sbitmap.recycle();
//				Bimp.sbitmap = null;
//			}
//			new Thread(new Runnable() {
//				public void run() {
//					CommonUtil.deleteFolderFile(Constant.CACHE_DIR_PHOTO, false);
//					CommonUtil.deleteFolderFile(Constant.CACHE_DIR_SD, false);
//				}
//			}).start();
//		} catch (Exception e) {
//		}
//	}






	/**
	 * 检查手机号
	 * 
	 * @return
	 */
	public static boolean checkMobile(Context context, String mMobile) {
		if (TextUtils.isEmpty(mMobile)) {
			CommonUtil.toast(context, "请输入手机号");
			return false;
		}
		if (!CommonUtil.checkMobileNum(mMobile)) {
			CommonUtil.toast(context, "请输入正确的手机号");
			return false;
		}
		return true;
	}

	/**
	 * 判断是否是手机号
	 * 
	 * @param num
	 * @return 中国移动手机号码开头数字
	 *         134、135、136、137、138、139、150、151、152、157、158、159、182、183
	 *         、184、187、188、178(4G)、147(上网卡) 中国联通手机号码开头数字
	 *         130、131、132、155、156、185、186、176(4G)、145(上网卡) 中国电信手机号码开头数字
	 *         133、153、180、181、189 、177(4G) 另外还有177开头
	 */
	public static boolean checkMobileNum(String num) {
		if (TextUtils.isEmpty(num) || num.length() != 11)
			return false;
		// 中国移动
		String[] chinaMobile = new String[] { "134", "135", "136", "137", "138", "139", "150", "151", "152", "157", "158", "159", "182", "183", "184", "187", "188", "178", "147" };
		// 中国联通
		String[] chinaUnicom = new String[] { "130", "131", "132", "155", "156", "185", "186", "176", "145" };
		// 中国电信
		String[] chinaTelecom = new String[] { "133", "153", "180", "181", "189", "177" };
		// 其他
		String[] chinaOther = new String[] { "170" };

		for (String n : chinaMobile) {
			if (num.startsWith(n))
				return true;
		}
		for (String n : chinaUnicom) {
			if (num.startsWith(n))
				return true;
		}
		for (String n : chinaTelecom) {
			if (num.startsWith(n))
				return true;
		}
		for (String n : chinaOther) {
			if (num.startsWith(n))
				return true;
		}
		return false;
	}

	/**
	 * 获取jsonValue
	 * 
	 * @param json
	 *            josnObj
	 * @param key
	 *            key
	 * @param def
	 *            默认值
	 * @return
	 */
	public static String getJsonValue(JSONObject json, String key, String def) {
		if (json.has(key)) {
			try {
				return json.getString(key);
			} catch (JSONException e) {
				return def;
			}
		}
		return def;
	}

	/**
	 * 分转元
	 * 
	 * @param fen
	 * @return
	 */
	public static String parseMoney(String fen) {
		String yuan = "0.00";
		try {
			if (!TextUtils.isEmpty(fen)) {
				DecimalFormat format = new DecimalFormat("0.00");
				yuan = format.format(Double.parseDouble(fen) / 100);
			}
		} catch (Exception e) {
		}
		return yuan;
	}




	// public static LocationClient mLocationClient;
	//
	// public static void getPosion(final Context context) {
	// BDLocationListener myListener = new MyLocationListener(context);
	// mLocationClient = new LocationClient(context); // 声明LocationClient类
	// mLocationClient.registerLocationListener(myListener); // 注册监听函数
	// initLocation();
	// mLocationClient.start();
	// }
	//
	// private static void initLocation() {
	// LocationClientOption option = new LocationClientOption();
	// option.setLocationMode(LocationMode.Battery_Saving);// 设置定位模式
	// // option.setCoorType(tempcoor);//返回的定位结果是百度经纬度，默认值gcj02
	// option.setScanSpan(60 * 60 * 1000);// 设置发起定位请求的间隔时间为5000ms
	// option.setIsNeedAddress(true);
	// mLocationClient.setLocOption(option);
	// }
	//
	// public static class MyLocationListener implements BDLocationListener {
	// Context context;
	//
	// public MyLocationListener(Context context) {
	// this.context = context;
	// }
	//
	// @Override
	// public void onReceiveLocation(BDLocation location) {
	// Double latitude = 0.0d;
	// Double longitude = 0.0d;
	// latitude = location.getLatitude();
	// longitude = location.getLongitude();
	// Log.i(LOGTAG, "latitude=" + latitude + "---longitude=" + longitude);
	// if (mLocationClient != null) {
	// mLocationClient.stop();
	// mLocationClient = null;
	// }
	// if (latitude > 0.1) {
	// UserUtil.getCityByPosition(context, longitude, latitude);
	// }
	// }
	// }
	
	public static void copy(String content, Context context)  
	{  
	// 得到剪贴板管理器  
	ClipboardManager cmb = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);  
	cmb.setText(content.trim());  
	}  
}
