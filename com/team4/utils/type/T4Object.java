package com.team4.utils.type;

import java.util.List;

/**
 *  @Project       : TPO
 *  @Program Name  : com.team4.type.T4Object.java
 *  @Class Name    : T4Object
 *  @Description   : 类描述
 *  @Author        : Xiaohui Chen
 *  @Creation Date : 2013-6-22 下午4:08:44 
 *  @ModificationHistory  
 *  Who        When          What 
 *  --------   ----------    -----------------------------------
 *  username   2013-6-22       TODO
 */
public class T4Object<T> {
	
	private TMeta meta;
	private List<T> objects;

	/**
	*  @BareFieldName : meta
	*  @return  the meta
	*/
	public TMeta getMeta() {
		return meta;
	}

	/**
	 * @param meta the meta to set
	 */
	public void setMeta(TMeta meta) {
		this.meta = meta;
	}

	/**
	*  @BareFieldName : object
	*  @return  the object
	*/
	public List<T> getObjects() {
		return objects;
	}

	/**
	 * @param object the object to set
	 */
	public void setObjects(List<T> objects) {
		this.objects = objects;
	}
}
