package com.team4.utils.util;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
    
/**
*  @Project       : XhUtility
*  @Program Name  : com.team4.utils.util.FuncUtil.java
*  @Class Name    : FuncUtil
*  @Description   : 通用函数集合
*  @Author        : Xiaohui Chen
*  @Creation Date : 2013-3-1 上午10:07:42 
*  @ModificationHistory  
*  Who            When          What 
*  ------------   -----------   ------------------------------------
*  Xiaohui Chen   2013-3-1       Create
*/
public class FuncUtil {
	
	private final static int TYPE_NONE = -1;
	
	/** 
	*  @Description    : 将dp转成px
	*  @param context
	*  @param dpValue
	*  @return
	*  @Creation Date  : 2013-3-1 上午10:10:18 
	*  @Author         : Xiaohui Chen
	*/
	    
	public static int dp2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	
	/** 
	*  @Description    : 将px转成dp
	*  @param context
	*  @param pxValue
	*  @return
	*  @Creation Date  : 2013-3-1 上午10:11:06 
	*  @Author         : Xiaohui Chen
	*/
	    
	public static int px2dp(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	
	/** 
	*  @Description    : 判断网络是否可用
	*  @param cxt
	*  @return
	*  @Creation Date  : 2013-3-1 上午10:11:23 
	*  @Author         : Xiaohui Chen
	*/	    
	public static boolean networkIsConnected(Context cxt) {
		if (cxt == null)
			return false;

		return getActiveNetworkType(cxt) > TYPE_NONE;
	}

	/** 
	*  @Description    : 获取当前的网络类型
	*  @param cxt
	*  @return one of TYPE_MOBILE, TYPE_WIFI, TYPE_WIMAX, TYPE_ETHERNET, TYPE_BLUETOOTH, or other types defined by ConnectivityManager
	*  @Creation Date  : 2013-3-1 上午10:11:46 
	*  @Author         : Xiaohui Chen
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
}
