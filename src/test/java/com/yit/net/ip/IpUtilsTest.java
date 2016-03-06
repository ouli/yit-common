package com.yit.net.ip;

import java.util.Arrays;

import junit.framework.TestCase;

public class IpUtilsTest extends TestCase {

	public void testIpStr2Long() {
		
		String ipstr = "255.255.255.255";
		ipstr = "1.0.0.255";
		ipstr = "172.16.24.35";
		ipstr = "202.106.0.20";
		long ip = IpUtils.ipStr2Long(ipstr);
		assertEquals(ipstr, IpUtils.ipLong2Str(ip));
	}

	public void testValidateIP() {
		String ipstr = "0.0.0.0";
		assertTrue(IpUtils.validateIP(ipstr));
		ipstr = "00.00.00.00";
		assertTrue(IpUtils.validateIP(ipstr));
		ipstr = "000.000.000.000";
		
		assertTrue(IpUtils.validateIP(ipstr));
		ipstr = "255.255.255.255";
		assertTrue(IpUtils.validateIP(ipstr));
		ipstr = "255.255.0.0";
		assertTrue(IpUtils.validateIP(ipstr));
		
		ipstr = "192.168.0.1";
		assertTrue(IpUtils.validateIP(ipstr));
		ipstr = "192.168.00.01";
		assertTrue(IpUtils.validateIP(ipstr));
		ipstr = "192.168.000.001";
		assertTrue(IpUtils.validateIP(ipstr));
		
		ipstr = "255.255.254.255";
		assertTrue(IpUtils.validateIP(ipstr));
	}
	
	public void testIpStr2bytes() {
		
		String ipstr = "192.168.0.1";
		byte[] ipbytes = IpUtils.ipStr2bytes(ipstr);
		System.out.println(Arrays.toString(ipbytes));
		String ipstr2 = IpUtils.ipBytes2Str(ipbytes);
		System.out.println(ipstr2);
		assertEquals(ipstr, ipstr2);
	}
	
	public void testIpLong2bytes() {
		String ipstr = "192.168.1.1";
		long iplong = IpUtils.ipStr2Long(ipstr);
		byte[] ipbytes = IpUtils.ipLong2bytes(iplong);
		
		String ipstr2 = IpUtils.ipBytes2Str(ipbytes);
		long iplong2 = IpUtils.ipBytes2Long(ipbytes);
		assertEquals(ipstr, ipstr2);
		assertEquals(iplong, iplong2);
	}

}
