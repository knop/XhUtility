package com.team4.utils.type;

import java.io.Serializable;

/**
 * @Project : XhUtility
 * @Program Name  : com.team4.utils.type.T4ResponseCode.java
 * @Class Name    : T4ResponseCode
 * @Description : Http请求响应Code基类
 * @Author : Xiaohui Chen
 * @Creation Date : 2013-3-1 上午10:54:38
 * @ModificationHistory Who            When          What
 * ------------   -----------   ------------------------------------
 * Xiaohui Chen   2013-3-1       Create
 */
public class T4ResponseCode<T> implements Serializable {

	private static final long serialVersionUID = -5796441190185810079L;
	
	private int code;
	private String description;
	private boolean success;
	private T data;

	/**
	*  @BareFieldName : code
	*  @return  the code
	*/
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	*  @BareFieldName : description
	*  @return  the description
	*/
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	*  @BareFieldName : success
	*  @return  the success
	*/
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	*  @BareFieldName : data
	*  @return  the data
	*/
	public T getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}
}
