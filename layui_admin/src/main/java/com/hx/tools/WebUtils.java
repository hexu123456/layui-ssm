package com.hx.tools;

import java.util.HashMap;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class WebUtils {
	//md5加密
	public static String Md5(String pwd,String account)throws Exception{
		String algorithmName = "MD5";
		Object source = pwd;
		Object salt = ByteSource.Util.bytes(account);
		int hashIterations = 1024;
		SimpleHash simpleHash = new SimpleHash(algorithmName, source, salt,
				hashIterations);
		return simpleHash.toString();
	}
	//实例化map
	public static HashMap<String,Object> getMap()throws Exception
	{
		return new HashMap<String,Object>();
	}
}
