package com.team4.utils.parser;

import com.team4.utils.exceptions.T4Exception;
import com.team4.utils.type.IBaseType;

public interface IParser<T extends IBaseType> {
    public abstract T parse(String content) throws T4Exception;
}
