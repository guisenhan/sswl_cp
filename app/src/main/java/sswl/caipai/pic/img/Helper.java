package sswl.caipai.pic.img;


public class Helper {
	// 检测网络连接
//	public static boolean checkConnection(Context context) {
//		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
//		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//		if (networkInfo != null) {
//			return networkInfo.isAvailable();
//		}
//		return false;
//	}
//
//	public static boolean isWifi(Context mContext) {
//		ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
//		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
//		if (activeNetInfo != null && activeNetInfo.getTypeName().equals("WIFI")) {
//			return true;
//		}
//		return false;
//	}
//
//	/**
//	 * 从网上获取内容get方式
//	 * 
//	 * @param url
//	 * @return
//	 * @throws IOException
//	 * @throws ClientProtocolException
//	 */
//	public static String getStringFromUrl(String url) throws ClientProtocolException, IOException {
//		HttpGet get = new HttpGet(url);
//		HttpClient client = new DefaultHttpClient();
//		HttpResponse response = client.execute(get);
//		HttpEntity entity = response.getEntity();
//		return EntityUtils.toString(entity, "UTF-8");
//	}
}
