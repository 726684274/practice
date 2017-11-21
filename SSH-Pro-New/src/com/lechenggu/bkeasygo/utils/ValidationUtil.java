package com.lechenggu.bkeasygo.utils;

public class ValidationUtil {
	// 用户名必须包含字母,且长度大于0
	public static final String CHKNAME = "^(?![0-9]+)[a-zA-Z0-9]+$";
	// 用户真实姓名必须为汉字,且长度最少两位
	public static final String CHKTRUENAME = "^[\u4e00-\u9fa5]{2,}$";
	// 密码必须包含字母和数字,长度最少6位
	public static final String CHKPWD = "^(?![0-9]+$)(?![a-zA-Z]+$)[a-zA-Z0-9]{6,}$";
	// 出生日期格式:yyyy-mm-dd -表示连接
	public static final String CHKBIRTHDAY = "^([0-9]{4})-(0?[1-9]|1[0-2])-(0?[1-9]|1[0-9]|2[0-9]|3[0-1])$";
	// 邮箱格式
	public static final String CHKEMAIL = "^([a-zA-Z0-9]{1,})@([a-z0-9]{2,3})(\\.[a-z]{2,3})$";
	// 电话
	public static final String CHKPHONE = "^[1][3,5,7,8][0-9]{9}$";
	// 身份证验证
	public static final String CHKIDCARD = "^\\d{18}$|^\\d{17}(\\d|X|x)$";

	/**
	 * 判断字符串是否匹配正则表达式
	 * 
	 * @param str
	 *            字符串
	 * @param regex
	 *            正则表达式
	 * @return 匹配返回true
	 */
	public static boolean ifMatch(String str, String regex) {
		if (str != null) {
			// 正则表达式的判定方法
			return str.matches(regex);
		}
		return false;
	}

	public static boolean ifNotNull(String str) {
		return (str == null || str == "") ? false : true;
	}

}
