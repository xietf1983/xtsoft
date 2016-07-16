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
 * ͨ�ù�����
 * 
 * @author caogj
 * @date 2011-02-23
 */
public class CommUtils {

	/**
	 * ��ȡ������
	 * 
	 * @param startTime
	 *            ��ʼʱ��
	 * @param endTime
	 *            ����ʱ��
	 * @return ������
	 */
	private static Log iLog = LogFactory.getLog(CommUtils.class.getName());

	public static long getMillisecond(Date startTime, Date endTime) {
		long millisecond = endTime.getTime() - startTime.getTime();
		return millisecond;
	}

	// �ַ���ת��Date
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

	// DateתString,type��ת����ʽ:yyyy-MM-dd HH:mm:ss,yyyy-MM-dd
	public static String dateToString(Date date, String type) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(type);
			return dateFormat.format(date);
		} catch (Exception e) {
			System.out.println("����ת������");
		}
		return null;
	}

	//���ݿͻ���IP�õ�ͼƬ��ʾ��������IP,domain����Ϊnull��""
		public static String getPhotoRsIp(String clientIp,String domain) {
			String ipUrl = null;
			String defaurUrl = null;
			iLog.info("�ͻ���IP:"+clientIp+" �豸��ţ�"+domain);
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
			iLog.info("ͼƬ��ʾ��������IP:"+ipUrl);
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
			iLog.error("ϵͳ���ڴ�:" + maxM + "MB ��ǰ������Դ:" + totalM + "MB ��ǰ������Դ" + freeM + "MB");
		}
		if (maxM > (totalM + 50) || freeM > 50) {
			return true;
		} else {
			iLog.error("ϵͳ���ڴ�:" + maxM + "MB ��ǰ������Դ:" + totalM + "MB ��ǰ������Դ" + freeM + "MB");
			return false;
		}
	}

	// ��127.0.0.1 ��ʽ��IP��ַת����10��������������û�н����κδ�����
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
			iLog.error("ip��ַ��Χ��ʽ����" + strIP, ex);
			return 0;
		}
	}
}
