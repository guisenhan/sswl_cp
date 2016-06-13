package sswl.caipai.util;

import android.content.Context;
import android.widget.Toast;

import com.github.lazylibrary.util.DateUtil;
import com.github.lazylibrary.util.TimeUtils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import cz.msebera.android.httpclient.util.TextUtils;

/**
 * Created by Administrator on 2016/4/12 0012.
 */
public class CommenUtil {

    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


    public static boolean checkMobile(Context context, String mMobile) {
        if (TextUtils.isEmpty(mMobile)) {
            Toast.makeText(context,"请输入正确的手机号",Toast.LENGTH_LONG);
            return false;
        }
        if (!CommenUtil.checkMobileNum(mMobile)) {
            Toast.makeText(context, "请输入正确的手机号", Toast.LENGTH_LONG);
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

}
