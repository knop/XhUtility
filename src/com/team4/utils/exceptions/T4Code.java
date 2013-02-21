package com.team4.utils.exceptions;
/**
 * 定义程序中的通用错误提示码
 * 在使用中可扩展此类的内容
 * 
 * @author Xiaohui Chen
 * @date 2012-6-25
 *
 */

public class T4Code {
	//通用异常编码
	public static final int UNKNOWN = -1;
	public static final int OK = 0;
	public static final int NETWORK_ERROR = 99;
	
	//标识APP中抛出的各种异常编码
	public static final int APP_ERROR = 100;
	
	//标识解析时抛出的各种异常编码
	public static final int PARSE_ERROR = 1000;
}

