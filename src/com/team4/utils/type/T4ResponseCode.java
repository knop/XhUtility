package com.team4.utils.type;

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
