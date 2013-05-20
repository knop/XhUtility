package com.team4.utils.parser;

import org.json.JSONObject;

import com.team4.utils.exceptions.T4Exception;
import com.team4.utils.type.IBaseType;

/**
*  @Project       : XhUtility
*  @Program Name  : com.team4.utils.parser.IJsonParser.java
*  @Class Name    : IJsonParser
*  @Description   : JSON解析接口类
*  @Author        : Xiaohui Chen
*  @Creation Date : 2013-3-1 上午10:51:07 
*  @ModificationHistory  
*  Who            When          What 
*  ------------   -----------   ------------------------------------
*  Xiaohui Chen   2013-3-1       Create
*/
public interface IJsonObjectParser extends IJsonParser {
	public IBaseType parse(JSONObject json) throws T4Exception;
}