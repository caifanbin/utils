package com.binge.utils.util;

import java.security.MessageDigest;


/**
 * 
 * @ClassName:  MD5Utils   
 * @Description:
 * @author: Brand Wang 
 * @date:   2020年2月24日 上午11:57:39   
 *     
 */
public class MD5Utils {
	
	public static void main(String[] args) {
		System.out.println(encodeByMD5(null));
	}
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
		"e", "f" };
	/**
	 * <p>Title: encodeByMD5</p>
	 * <p>Description: Md5加密</p>
	 * @param originString
	 * @return
	 * @author donglei.hu  2012-7-18 下午05:55:52
	 */
	public static String encodeByMD5(String originString) {
		if (originString != null) {
			try {
				// 创建具有指定算法名称的信息摘要
				MessageDigest md = MessageDigest.getInstance("MD5");
				// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
				byte[] results = md.digest(originString.getBytes());
				// 将得到的字节数组变成字符串返回
				String resultString = byteArrayToHexString(results);
				return resultString.toUpperCase();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 
	 * @Title: encodeByMD5Low   
	 * @Description: Md5加密 转换为小写字母   
	 * @param: @param originString
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String encodeByMD5Low(String originString) {
		if (originString != null) {
			try {
				// 创建具有指定算法名称的信息摘要
				MessageDigest md = MessageDigest.getInstance("MD5");
				// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
				byte[] results = md.digest(originString.getBytes());
				// 将得到的字节数组变成字符串返回
				String resultString = byteArrayToHexString(results);
				return resultString.toLowerCase();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * <p>Title: byteArrayToHexString</p>
	 * <p>Description: 转换字节数组为十六进制字符串</p>
	 * @param b
	 * @return
	 * @author donglei.hu  2012-7-21 下午12:45:50
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/**
	 * <p>Title: byteToHexString</p>
	 * <p>Description: 将一个字节转化成十六进制形式的字符串</p>
	 * @param b
	 * @return
	 * @author donglei.hu  2012-7-21 下午12:45:33
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
}
