package com.team4.utils.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.widget.Toast;

/**
*  @Project       : XhUtility
*  @Program Name  : com.team4.utils.util.UncaughtCrashHandler.java
*  @Class Name    : UncaughtCrashHandler
*  @Description   : 捕获未处理的异常，主要是处理用户在使用过程中的crash，记录成文件
*  @Author        : Xiaohui Chen
*  @Creation Date : 2013-3-1 下午2:37:26 
*  @ModificationHistory  
*  Who            When          What 
*  ------------   -----------   ------------------------------------
*  Xiaohui Chen   2013-3-1       Create
*
*/
public class UncaughtCrashHandler implements UncaughtExceptionHandler {

	private final static String LOG_PATH = "/Uncaught_Crash_Log/";
	
	private static UncaughtCrashHandler mInstance;
	private Thread.UncaughtExceptionHandler mDefaultHandler;
	private Context mContext;
	private Map<String, String> mInfos = new HashMap<String, String>();
	private UncaughtCrashHandler() {

	}

	public static synchronized UncaughtCrashHandler getInstance() {
		if (mInstance == null)
			mInstance = new UncaughtCrashHandler();
		return mInstance;
	}

	public void init(Context context) {
		mContext = context;
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		if (!handleException(thread, ex) && mDefaultHandler != null) {
			mDefaultHandler.uncaughtException(thread, ex);
		} else {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.exit(1);
		}
	}

	private boolean handleException(Thread thread, Throwable ex) {
		if (ex == null) {
			return false;
		}
		new Thread() {
			@Override
			public void run() {
				Looper.prepare();
				Toast.makeText(mContext,
						"An error occured, the app will exit.",
						Toast.LENGTH_LONG).show();
				Looper.loop();
			}
		}.start();
		collectDeviceInfo(mContext);
		saveCrashInfo2File(ex);
		return true;
	}

	public void collectDeviceInfo(Context ctx) {
		try {
			PackageManager pm = ctx.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
					PackageManager.GET_ACTIVITIES);
			if (pi != null) {
				String versionName = pi.versionName == null ? "null"
						: pi.versionName;
				String versionCode = pi.versionCode + "";
				mInfos.put("versionName", versionName);
				mInfos.put("versionCode", versionCode);
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		Field[] fields = Build.class.getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				mInfos.put(field.getName(), field.get(null).toString());
				T4Log.d(field.getName() + " : " + field.get(null));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private String saveCrashInfo2File(Throwable ex) {

		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : mInfos.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			sb.append(key + "=" + value + "\n");
		}

		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		ex.printStackTrace(printWriter);
		Throwable cause = ex.getCause();
		while (cause != null) {
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}
		printWriter.close();
		String result = writer.toString();
		T4Log.e("crash: " + result);
		sb.append(result);
		try {
			String fileName = "log-" + FuncUtil.genRandomNum(10) + ".log";
			if (FuncUtil.isAvailableSDCard()) {
				String path = Environment.getExternalStorageDirectory() + LOG_PATH;
				File dir = new File(path);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				FileOutputStream fos = new FileOutputStream(path + fileName);
				fos.write(sb.toString().getBytes());
				fos.close();
			}
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
