package com.xtsoft.util;

public class IpUtils {

	public static boolean isValidIp(String clientIp, String validIp) {

		if (clientIp.equals("127.0.0.1")) {
			return true;
		}
		String[] ips = validIp.split(",");
		for (int i = 0; i < ips.length; i++) {
			String ip = ips[i];
			int split = ip.indexOf("-");
			if (split == -1) {
				if (clientIp.equals(ip)) {
					return true;
				}
			} else {
				String startIp = ip.substring(0, split);
				String endIp = ip.substring(split + 1);
				long clientIpL = ipToLong(clientIp);
				long startIpL = ipToLong(startIp);
				long endIpL = ipToLong(endIp);
				if (startIpL > 0 && endIpL > 0 && startIpL <= clientIpL && clientIpL <= endIpL) {
					return true;
				}
			}
		}
		return false;
	}

	public static long ipToLong(String strIP) {
		try {
			long[] ip = new long[4];
			int position1 = strIP.indexOf(".");
			int position2 = strIP.indexOf(".", position1 + 1);
			int position3 = strIP.indexOf(".", position2 + 1);
			ip[0] = Long.parseLong(strIP.substring(0, position1));
			ip[1] = Long.parseLong(strIP.substring(position1 + 1, position2));
			ip[2] = Long.parseLong(strIP.substring(position2 + 1, position3));
			ip[3] = Long.parseLong(strIP.substring(position3 + 1));
			return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
		} catch (Exception ex) {
			return 0;
		}
	}

	public static boolean isValidSegIp(String clientIp, String validIp) {
		int index = validIp.indexOf("-");
		if (index == -1) {
			return isValidSingleIp(clientIp, validIp);
		} else {
			return IpUtils.isValidIp(clientIp, validIp);
		}
	}

	//单个IP及单个IP段验证
	public static boolean isValidSingleIp(String clientIp, String validIp) {
		if (clientIp.equals(validIp)) {
			return true;
		}
		String[] arr0 = validIp.split("\\.");
		String[] arr = clientIp.split("\\.");
		String clientIpPref="";
		for(int i=0;i<arr0.length;i++) {
			clientIpPref += arr[i]+".";
		}
		clientIpPref = clientIpPref.substring(0, clientIpPref.length()-1);
		if (validIp.equals(clientIpPref)) {
			return true;
		}
		return false;
	}
	
	public static long ipSegToLong(String strIP) {
		try {
			long ip = 0;
			long ipLong = 0;
			int position1 = strIP.indexOf(".");
			if (position1 != -1) {
				ip = Long.parseLong(strIP.substring(0, position1));
				ipLong = (ip << 24);

				int position2 = strIP.indexOf(".", position1 + 1);
				if (position2 != -1) {
					ip = Long.parseLong(strIP.substring(position1 + 1, position2));
					ipLong += (ip << 16);
				} else {
					ip = Long.parseLong(strIP.substring(position1 + 1, strIP.length()));
					ipLong += (ip << 16);
				}
			}
			return ipLong;
		} catch (Exception ex) {
			return 0;
		}
	}

	public static boolean isMacValid(String clientMac, String mac) {
		if (clientMac.equalsIgnoreCase(mac)) {
			return true;
		}
		String[] macs = mac.split(",");
		for (int i = 0; i < macs.length; i++) {
			String strMac = macs[i];
			if (strMac != null) {
				if (clientMac.equalsIgnoreCase(strMac)) {
					return true;
				}
			}
		}
		return false;
	}
}
