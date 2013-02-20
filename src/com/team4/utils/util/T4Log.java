package com.team4.utils.util;
/**
 * Log类.
 * 
 * @author Xiaohui Chen
 * @date 2012-7-3
 *
 */

import android.util.Log;

public class T4Log {
	private final static String TAG = "T4Log";
	
	private final static int VERBOSE = 0x01;
	private final static int DEBUG = 0x02;
	private final static int INFO = 0x04;
	private final static int WARN = 0x08;
	private final static int ERROR = 0x10;
	
	private static int mLogLevel = 0xFF;

	/**
	 * Set the level of the log.
	 * If you want to print all of log in this SDK, please setting 0xFF before invoking function.
	 * 
	 * @param level
	 */
	public static void setLogLevel(int level) {
		mLogLevel = level;
	}
	
	/**
	 * Print an {@link #INFO} log message.
	 * 
	 * @param msg
	 * 		The message you would like logged
	 */
	public static void i(String msg) {
		if ((mLogLevel & INFO) == INFO)
			Log.i(TAG, msg + getTraceInfo());
	}

	/**
	 * Print an {@link #DEBUG} log message.
	 * 
	 * @param msg
	 * 		The message you would like logged
	 */
	public static void d(String msg) {
		if ((mLogLevel & DEBUG) == DEBUG)
			Log.d(TAG, msg + getTraceInfo());
	}

	/**
	 * Print an {@link #VERBOSE} log message.
	 * 
	 * @param msg
	 * 		The message you would like logged
	 */
	public static void v(String msg) {
		if ((mLogLevel & VERBOSE) == VERBOSE)
			Log.v(TAG, msg + getTraceInfo());
	}

	/**
	 * Print an {@link #ERROR} log message.
	 * 
	 * @param msg
	 * 		The message you would like logged
	 */
	public static void e(String msg) {
		if ((mLogLevel & ERROR) == ERROR)
			Log.e(TAG, msg + getTraceInfo());
	}

	/**
	 * Print an {@link #WARN} log message.
	 * 
	 * @param msg
	 * 		The message you would like logged
	 */
	public static void w(String msg) {
		if ((mLogLevel & WARN) == WARN)
			Log.w(TAG, msg + getTraceInfo());
	}

	private static String getTraceInfo() {
		StringBuffer sb = new StringBuffer();

		StackTraceElement[] stacks = new Throwable().getStackTrace();
		int stacksLen = stacks.length;
		if (stacksLen <= 2)
			return "";
		sb.append("   {at ").append(stacks[2].getClassName()).append(".")
				.append(stacks[2].getMethodName()).append("(")
				.append(stacks[2].getFileName()).append(":")
				.append(stacks[2].getLineNumber()).append(")}");
		return sb.toString();
	}
}
