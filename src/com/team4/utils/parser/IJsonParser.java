package com.team4.utils.parser;

import org.json.JSONArray;
import org.json.JSONObject;

import com.team4.utils.exceptions.T4Exception;

public interface IJsonParser<T> {    
    public T parse(JSONObject json) throws T4Exception;    
    public T parse(JSONArray array) throws T4Exception;
}