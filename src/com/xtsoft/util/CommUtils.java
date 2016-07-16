package com.xtsoft.util;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 通用工具类
 * 
 * @author caogj
 * @date 2011-02-23
 */
public class CommUtils {

	/**
	 * 获取毫秒数
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return 毫秒数
	 */
	private static Log iLog = LogFactory.getLog(CommUtils.class.getName());

	public static long getMillisecond(Date startTime, Date endTime) {
		long millisecond = endTime.getTime() - startTime.getTime();
		return millisecond;
	}

	// 字符串转换Date
	public static Date stringToDate(String str) {
		SimpleDateFormat parseTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date startTime = null;
		try {
			startTime = parseTime.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return startTime;
	}

	// Date转String,type：转换格式:yyyy-MM-dd HH:mm:ss,yyyy-MM-dd
	public static String dateToString(Date date, String type) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(type);
			return dateFormat.format(date);
		} catch (Exception e) {
			System.out.println("日期转换错误！");
		}
		return null;
	}

	//根据客户端IP得到图片显示服务器的IP,domain可以为null、""
		public static String getPhotoRsIp(String clientIp,String domain) {
			String ipUrl = null;
			String defaurUrl = null;
			iLog.info("客户端IP:"+clientIp+" 设备域号："+domain);
			List<Map<String, String>> list = AppPropsWeb.getProphotoRsObject();
			for (Map<String, String> map : list) {
				String ipSeg = map.get("name");
				String domainId = map.get("domain");
				if(domain==null || domain.equals("") || domainId==null || domainId.equals("") || domainId.indexOf(domain)!=-1) {
					boolean isValid = IpUtils.isValidSegIp(clientIp, ipSeg);
					if (isValid == true) {
						ipUrl = map.get("value");
						break;
					}
					if(domainId==null || domainId.equals("")){
						defaurUrl = map.get("value");
					}
				}
			}
			iLog.info("图片显示服务器的IP:"+ipUrl);
			if(ipUrl==null){
				ipUrl =defaurUrl;
			}
			return ipUrl;
		}
	public static boolean haveMoreMemory() {
		Random random = new Random();
		Runtime runtime = Runtime.getRuntime();
		long maxM = runtime.maxMemory() / 1024 / 1024;
		long freeM = runtime.freeMemory() / 1024 / 1024;
		long totalM = runtime.totalMemory() / 1024 / 1024;
		if (random.nextInt(1000) == 50) {
			iLog.error("系统总内存:" + maxM + "MB 当前申请资源:" + totalM + "MB 当前空闲资源" + freeM + "MB");
		}
		if (maxM > (totalM + 50) || freeM > 50) {
			return true;
		} else {
			iLog.error("系统总内存:" + maxM + "MB 当前申请资源:" + totalM + "MB 当前空闲资源" + freeM + "MB");
			return false;
		}
	}

	// 将127.0.0.1 形式的IP地址转换成10进制整数，这里没有进行任何错误处理
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
			iLog.error("ip地址范围格式错误：" + strIP, ex);
			return 0;
		}
	}
}
