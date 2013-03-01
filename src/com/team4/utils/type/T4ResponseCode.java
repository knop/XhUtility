package com.team4.utils.type;

/**
*  @Project       : XhUtility
*  @Program Name  : com.team4.utils.type.T4ResponseCode.java
*  @Class Name    : T4ResponseCode
*  @Description   : Http请求响应Code基类
*  @Author        : Xiaohui Chen
*  @Creation Date : 2013-3-1 上午10:54:38 
*  @ModificationHistory  
*  Who            When          What 
*  ------------   -----------   ------------------------------------
*  Xiaohui Chen   2013-3-1       Create
*
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
