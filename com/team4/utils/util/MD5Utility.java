package com.team4.utils.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.team4.utils.exceptions.T4Exception;

/**
 * @Project : XhUtility
 * @Program Name  : com.team4.utils.util.MD5Utility.java
 * @Class Name    : MD5Utility
 * @Description : MD5编码工具类
 * @Author : Xiaohui Chen
 * @Creation Date : 2013-3-1 上午10:52:15
 * @ModificationHistory Who            When          What
 * ------------   -----------   ------------------------------------
 * Xiaohui Chen   2013-3-1       Create
 */
public class MD5Utility {
    public static final String CHARSET_NAME = "UTF-8";

    public static String getMD5Digest(String origin) throws T4Exception {
        try {
            byte[] messageDigest = MessageDigest.getInstance("MD5").digest(origin.getBytes(CHARSET_NAME));
            BigInteger number = new BigInteger(1, messageDigest);
            String md5 = number.toString(16);
            while (md5.length() < 32)
                md5 = "0" + md5;
            return md5;
        } catch (NoSuchAlgorithmException e) {
            throw new T4Exception("No such algorithm");
        } catch (UnsupportedEncodingException e) {
            throw new T4Exception("Unsupported encoding");
        }
    }

//	public static List<BasicNameValuePair> secret(String sigName, String secret,
//			List<BasicNameValuePair> params) throws T4Exception {
//		final StringBuilder result = new StringBuilder();
//		result.append(secret);
//		for (final BasicNameValuePair parameter : params) {
//			final String name = parameter.getName();
//			String encodedValue = parameter.getValue();
//			result.append("&");
//			result.append(name);
//			result.append("=");
//			result.append(encodedValue);
//		}
//		String origin = result.toString();
//		T4Log.v("origin = " + origin);
//		String md5 = MD5Utility.getMD5Digest(origin);
//		T4Log.v("md5 = " + md5);
//		T4Log.v("param = " + origin+"&"+sigName+"="+md5);
//		params.add(new BasicNameValuePair(sigName, md5));
//		return params;
//	}
}
