package org.mini.common.utils;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理静态类
 * 
 * @category 字符串处理静态类
 * @author itachizhu
 * @version 1.0.0
 * @date 2012-12-03
 * 
 */
public class StringUtil {
	private StringUtil() {

	}

	/**
	 * 判断字符串是否为null或者为空
	 * 
	 * @param string
	 *            :需要判断的字符串对象
	 * @return 是否为空
	 */
	public static boolean isNullOrEmpty(String string) {
		return (string == null || string.length() == 0);
	}

	/**
	 * 判断字符串是否为整数格式
	 * 
	 * @param string
	 *            :需要判断的字符串对象
	 * @return 是否为整数
	 */
	public static boolean isNumber(String string) {
		return Pattern.matches("^(-?[1-9][0-9]*|0)$", string);
	}

	/**
	 * 判断字符串是否为邮件格式
	 * 
	 * @param string
	 *            :需要判断的字符串对象
	 * @return 是否为邮件格式
	 */
	public static boolean isMail(String string) {
		return Pattern
				.matches(
						"^(([A-Za-z0-9\\-]+_+)|([A-Za-z0-9\\-]+\\-+)|([A-Za-z0-9\\-]+\\.+)|([A-Za-z0-9\\-]+\\++))*[A-Za-z0-9_\\-]+@((\\w+\\-+)|(\\w+\\.))*\\w{1,63}\\.[a-zA-Z]{2,6}$",
						string);
	}

	/**
	 * 判断字符串是否为小数格式
	 * 
	 * @param string
	 *            :需要判断的字符串对象
	 * @return 是否为小数
	 */
	public static boolean isDecimal(String string) {
		return Pattern.matches("^-?[0-9]+.?[0-9]*$", string);
	}

	/**
	 * 将字符串进行md5加密
	 * 
	 * @param string
	 *            :要加密的字符串对象
	 * @param isHex
	 *            :是16位还是32位,true为16位,false为32位
	 * @return 加密后的字符串
	 */
	public static String toMD5(String string, boolean isHex) {
		if (isNullOrEmpty(string))
			return string;

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(string.getBytes());
			byte[] byteDigest = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < byteDigest.length; offset++) {
				i = byteDigest[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			if (isHex) {
				// 16位的加密
				return buf.toString().substring(8, 24);
			} else {
				// 32位加密
				return buf.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将字符串进行16位md5加密
	 * 
	 * @param string
	 *            :要加密的字符串对象
	 * @return 加密后的字符串
	 */
	public static String toMD5(String string) {
		return toMD5(string, true);
	}
	
	/**
	 * 将字符串首个字母转换为大写。
	 * @param string
	 * @return
	 * @throws Exception
	 */
	public static String firstLetterToUpper(String string) {
		byte[] items = string.getBytes();
		if(items[0] >= 'a' && items[0] <= 'z')
			items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return new String(items);
	}
	

	/**
	 * 将下划线+小写字母转换成大写字母，为的是数据表中的字段命和表命转换成对象域的名字：
	 * 如user_name转换成userName
	 * @param string
	 * @return
	 */
	public static String dbChartoFieldChar(String string) {
		Pattern pattern = Pattern.compile("_([a-z])");
        Matcher matcher = pattern.matcher(string);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
        	matcher.appendReplacement(buffer, matcher.group(1).toUpperCase()); 
        }
        matcher.appendTail(buffer);
        return buffer.toString();
	}

}
