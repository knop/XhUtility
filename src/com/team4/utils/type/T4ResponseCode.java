package com.team4.utils.type;
/**
 * Http请求响应Code基类
 * 
 * 
 * @author Xiaohui Chen
 * 2013-02-30
 */

public class T4ResponseCode implements IBaseType{

	private int mResponseCode;
	
	public T4ResponseCode() {
	}
	
	public int getResponseCode(){
		return mResponseCode;
	}
	
	public void setResponseCode(int rc){
		mResponseCode = rc;
	}
}
