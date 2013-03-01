package com.team4.utils.exceptions;

/**
*  @Project       : XhUtility
*  @Program Name  : com.team4.utils.exceptions.T4Exception.java
*  @Class Name    : T4Exception
*  @Description   : 通用异常类，定义了异常Code和异常Message两个信息.
*  @Author        : Xiaohui Chen
*  @Creation Date : 2013-3-1 上午10:54:58 
*  @ModificationHistory  
*  Who            When          What 
*  ------------   -----------   ------------------------------------
*  Xiaohui Chen   2013-3-1       Create
*
*/
public class T4Exception extends Exception {
	
	private static final long serialVersionUID = 7043110700738563425L;
	private int mExceptionCode;
    
    public T4Exception(String message) {
        super(message);
        mExceptionCode = T4Code.UNKNOWN;
    }

    public T4Exception(int code, String message) {
        super(message);
        mExceptionCode = code;
    }
    
    /**
     * Return a code that indicates a error type.
     * 
     * @return
     * 		{@link T4Code}.
     */
    public int getExceptionCode(){
    	return mExceptionCode;
    }
}
