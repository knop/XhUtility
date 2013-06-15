package com.team4.utils.exceptions;

/**
 * @Project : XhUtility
 * @Program Name  : com.team4.utils.exceptions.T4Code.java
 * @Class Name    : T4Code
 * @Description : 定义程序中的通用错误提示码，在使用中可扩展此类的内容
 * @Author : Xiaohui Chen
 * @Creation Date : 2013-3-1 上午10:55:13
 * @ModificationHistory Who            When          What
 * ------------   -----------   ------------------------------------
 * Xiaohui Chen   2013-3-1       Create
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

