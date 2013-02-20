package com.team4.utils.parser;
/**
 * 解析接口类，实现parse接口可实现对不同数据格式的解析工作
 * 
 * @author Xiaohui Chen
 * @date 2013-02-20
 *
 */

import com.team4.utils.exceptions.T4Exception;
import com.team4.utils.type.IBaseType;

public interface IParser<T extends IBaseType> {
    public abstract T parse(String content) throws T4Exception;
}
