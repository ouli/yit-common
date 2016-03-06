package com.yit.net.ip;

import java.util.regex.Pattern;

/**
 * IP工具类
 * @author Administrator
 *
 */
public class IpUtils {
	
	private static final Pattern IP_PATTERN = Pattern.compile("((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)");
	
	/**
	 * 将字符串IP转换成长整形数值
	 * @param ipstr
	 * @return
	 */
	public static long ipStr2Long(String ipstr) {
		if (!validateIP(ipstr)) {
			throw new IllegalArgumentException(String.format("IP[%s]无效!", ipstr));
		}
		String[] ip4parts = ipstr.split("\\.");
		long iplong = 0L;
		for (int i=3; i>=0; i--) {
			iplong += (Long.parseLong(ip4parts[3-i]) << i * 8);
		}
		return iplong;
	}
	
	public static String ipLong2Str(long ip) {
		return String.format("%s.%s.%s.%s", ((ip >> 24) & 0xFF),((ip >> 16) & 0xFF),((ip >> 8) & 0xFF),((ip >> 0) & 0xFF));
	}
	
	
	public static byte[] ipStr2bytes(String ipstr) {
		if (!validateIP(ipstr)) {
			throw new IllegalArgumentException(String.format("IP[%s]无效!", ipstr));
		}
		String[] ip4parts = ipstr.split("\\.");
		byte[] bytes = new byte[4];
		for (int i=0;i<4;i++) {
			bytes[i] = (byte)(Integer.parseInt(ip4parts[i]) & 0xFF);
		}
		return bytes;
	}
	
	public static byte[] ipLong2bytes(long iplong) {

		byte[] bytes = new byte[4];
		for (int i=0;i<4;i++) {
			bytes[i] = (byte)((iplong >> 8 * (3-i)) & 0xFF);
		}
		return bytes;
	}
	
	public static String ipBytes2Str(byte[] ip) {
		return String.format("%s.%s.%s.%s", (ip[0] & 0xFF),(ip[1] & 0xFF),(ip[2] & 0xFF),(ip[3] & 0xFF));
	}
	
	public static long ipBytes2Long(byte[] ipbytes) {
		long iplong = 0L;
		for (int i = 0; i < ipbytes.length; i++) {
			iplong += ((ipbytes[i] & 0xFFL) << 8*(3-i));
		}
		return iplong;
	}
	
	/**
	 * 验证一个IP的合法性，合法返回true，否则返回false
	 * @param ipstr
	 * @return
	 */
	public static boolean validateIP(String ipstr) {
		return IP_PATTERN.matcher(ipstr).matches();
	}
}
