package com.team4.utils.parser;

import com.team4.utils.exceptions.T4Exception;

/**
 * @Project : XhUtility
 * @Program Name  : com.team4.utils.parser.IParser.java
 * @Class Name    : IParser
 * @Description : 解析接口类，实现parse接口可实现对不同数据格式的解析工作
 * @Author : Xiaohui Chen
 * @Creation Date : 2013-3-1 上午10:51:49
 * @ModificationHistory Who            When          What
 * ------------   -----------   ------------------------------------
 * Xiaohui Chen   2013-3-1       Create
 */
public interface IParser {
    public <T> T parse(String content, Class<T> valueType) throws T4Exception;
}
