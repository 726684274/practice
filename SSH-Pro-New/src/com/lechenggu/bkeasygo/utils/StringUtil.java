package com.lechenggu.bkeasygo.utils;

import java.util.Date;

//正则表达式验证方法
public class StringUtil {
	/**
	 * 将str转换成double型
	 * @param str
	 * @return double
	 */
	public static double strToDou(String str){
		if(ifNull(str)) return 0.0;
		return Double.parseDouble(str);
	}
	/**
	 * 将str转换成int
	 * @param str
	 * @return
	 * @throws AppException 
	 */
	public static int strToInt(String str){
		if(ifNull(str)) return 0;
		return Integer.parseInt(str);
	}
	/**
	 * 判断字符串是否为空 
	 * @param str
	 * @return 为空返回true
	 */
	public static boolean ifNull(String str){
		return (str==null ? true:false);
	}
	//获取时间
	public  static Date getDate(){
		Date date= new java.sql.Date(new Date().getTime());
		
		return date;
		
	}
}
