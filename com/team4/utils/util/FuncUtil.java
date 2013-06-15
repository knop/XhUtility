package com.team4.utils.util;

import java.util.Random;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

/**
 * @Project : XhUtility
 * @Program Name  : com.team4.utils.util.FuncUtil.java
 * @Class Name    : FuncUtil
 * @Description : 通用函数集合
 * @Author : Xiaohui Chen
 * @Creation Date : 2013-3-1 上午10:07:42
 * @ModificationHistory Who            When          What
 * ------------   -----------   ------------------------------------
 * Xiaohui Chen   2013-3-1       Create
 */
public class FuncUtil {

    private final static int TYPE_NONE = -1;

    /**
     * @param context
     * @param dpValue
     * @return
     * @Description : 将dp转成px
     * @Creation Date  : 2013-3-1 上午10:10:18
     * @Author : Xiaohui Chen
     */

    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * @param context
     * @param pxValue
     * @return
     * @Description : 将px转成dp
     * @Creation Date  : 2013-3-1 上午10:11:06
     * @Author : Xiaohui Chen
     */

    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * @param cxt
     * @return
     * @Description : 判断网络是否可用
     * @Creation Date  : 2013-3-1 上午10:11:23
     * @Author : Xiaohui Chen
     */
    public static boolean networkIsConnected(Context cxt) {
        if (cxt == null)
            return false;

        return getActiveNetworkType(cxt) > TYPE_NONE;
    }

    /**
     * @param cxt
     * @return one of TYPE_MOBILE, TYPE_WIFI, TYPE_WIMAX, TYPE_ETHERNET, TYPE_BLUETOOTH, or other types defined by ConnectivityManager
     * @Description : 获取当前的网络类型
     * @Creation Date  : 2013-3-1 上午10:11:46
     * @Author : Xiaohui Chen
     */
    public static int getActiveNetworkType(Context cxt) {
        int type = TYPE_NONE;
        ConnectivityManager conn = (ConnectivityManager) cxt
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (conn != null) {
            NetworkInfo info = conn.getActiveNetworkInfo();
            if (info != null && info.isAvailable())
                type = info.getType();
        }

        return type;
    }

    /**
     * @return
     * @Description : 判断SD卡是否可用
     * @Creation Date  : 2013-3-1 下午2:35:08
     * @Author : Xiaohui Chen
     */
    public static boolean isAvailableSDCard() {
        return externalMemoryAvailable();
    }

    /**
     * @return
     * @Description : 判断额外的存储是否可用
     * @Creation Date  : 2013-3-1 下午2:35:36
     * @Author : Xiaohui Chen
     */
    public static boolean externalMemoryAvailable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * @param length 需要生成字符串的长度
     * @return
     * @Author Xiaohui Chen
     * @Creation 2013-3-1 下午4:20:59
     * @Description 生成随机数字符串
     */
    public static String genRandomNum(int length) {
        final int maxNum = 10;
        int i;
        int count = 0;
        /*
		 * char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
		 * 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
		 * 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		 */

        char[] str = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        StringBuffer strResult = new StringBuffer("");
        Random r = new Random();
        while (count < length) {
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                strResult.append(str[i]);
                count++;
            }
        }

        return strResult.toString();
    }
}
