package org.cc.utils;




import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class Tools
{
	public static String md5(String str){

		MessageDigest md5=null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		BASE64Encoder encoder = new BASE64Encoder();

		String security_str = null;
		try {
			security_str = encoder.encode(md5.digest(str.getBytes("utf-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return security_str;
    }
}
