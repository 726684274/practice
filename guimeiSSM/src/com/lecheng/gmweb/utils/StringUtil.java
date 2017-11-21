package com.lecheng.gmweb.utils;

/**
 * 字符串工具类
 * 
 * @author gavin
 *
 */
public final class StringUtil {
	
	/**
	 * 字符串转整型
	 * @param s
	 * @return
	 */
	public static int strToint(Object s){
		
		return s==null?0:Integer.parseInt(s.toString());
		
	}
	
	
	/**
	 * 判断传入的字符串是否为空
	 * @param s
	 * @return 是    否
	 */
	public static boolean isNull(String s){
		//三元运算符
		return s==null?true:false;
	}
	
	/**
	 * 传入的字符串去空
	 * @param s
	 * @return
	 */
	public static String nvl(String s){
		return s==null?"":s;
	}
	
	/**
	 * 判断输入字符是否符合输入的规则
	 * @param s 输入字符
	 * @param regex 输入的规则
	 * @return
	 */
	public static boolean isRegex(String s,String regex){
		if(isNull(s)) return false;
		//判读是否符合规则
		return (s.matches(regex));
	}
	
	public static void main(String[] args) {
		System.out.println(isRegex("a1","^(?![0-9]+$)[0-9A-Za-z)]{0,16}$"));
	}

}
