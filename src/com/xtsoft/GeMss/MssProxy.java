package com.xtsoft.GeMss;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.log4j.Logger;

import com.xtsoft.ice.ICECommunicatorHolder;
import com.xtsoft.util.AppPropsWeb;
import com.xtsoft.util.DateUtil;
import com.xtsoft.util.WebKeys;

public class MssProxy {
	static private Logger iLog = Logger.getLogger(MssProxy.class);
	private static MssProxy inst = new MssProxy();

	private ConcurrentHashMap<String, MssWebPrx> prxContainer = new ConcurrentHashMap<String, MssWebPrx>();

	public static MssProxy getInstance() {
		return inst;
	};

	private MssWebPrx getMssWebPrx(String domainId) {
		MssWebPrx prx = null;
		prx = (MssWebPrx) prxContainer.get(domainId);
		if (prx == null) {
			try {
				String proxyProperty = "MssWeb" + domainId + ".Proxy";
				Ice.Properties properties = ICECommunicatorHolder.getInstance().getICECommunicator().getProperties();
				String proxyString = properties.getProperty(proxyProperty);
				if (proxyString.length() == 0) {
					proxyProperty = "MssWeb.Proxy";
					proxyString = properties.getProperty(proxyProperty);
				}
				if (proxyString.length() == 0) {
					iLog.error("创建ice代理失败,property '" + proxyProperty + "' not set");
				}
				Ice.ObjectPrx base = ICECommunicatorHolder.getInstance().getICECommunicator().stringToProxy(proxyString);
				if (base == null) {
					iLog.error(proxyString + " 创建ice代理失败");
				}
				// 5秒超时
				prx = MssWebPrxHelper.checkedCast(base.ice_twoway().ice_timeout(5000));
				if (prx == null) {
					iLog.error(proxyString + " 创建ice代理失败");
				}
			} catch (Exception e) {
				iLog.error("mss服务器连接失败", e);
			}
		}
		return prx;
	}

	public String[] queryAlarmPicUrls(String fdId, short channelType, short channelId, String strAlarmGuid, String strStorageAreaId, Date alarmTime, String clientIp) {
		if (iLog.isInfoEnabled()) {
			iLog.info(" fdId:" + fdId + " channelType:" + channelType + " channelId:" + channelId + " strAlarmGuid:" + strAlarmGuid + ",strStorageAreaId:" + strStorageAreaId + " alarmTime:"
					+ DateUtil.dateToString(alarmTime) + ",clientIp=" + clientIp);
		}
		String[] urls;
		boolean bCenter = true;
		TUrlAddressSeqHolder seqUrlAddress = new TUrlAddressSeqHolder();
		TFdInfoKey tFdKey = new TFdInfoKey();
		tFdKey.strFdId = fdId;
		if (fdId.length() > 6) {
			tFdKey.strWorkDomainId = fdId.substring(0, 6);
		} else {
			tFdKey.strWorkDomainId = "";
		}
		tFdKey.nFdChannelId = (channelType << 16) + channelId;
		tFdKey.byQoS = 1;
		String netWorkDouble = AppPropsWeb.getProp(WebKeys.NETWORKDOUBLE);// SystemConfigService.getInstance().getSystemConfig().getNetWorkDouble();
		String storeType = AppPropsWeb.getProp(WebKeys.STORETYPE);
		if (storeType.equals("SSU")) {
			if (strStorageAreaId != null && strStorageAreaId.length() > 0) {
				getMssWebPrx(tFdKey.strWorkDomainId).QueryAlarmStorageV3(bCenter, tFdKey, strAlarmGuid, alarmTime.getTime() / 1000, clientIp, strStorageAreaId, seqUrlAddress);
			}
		} else {
			if (netWorkDouble.equals("true")) {
				getMssWebPrx(tFdKey.strWorkDomainId).QueryAlarmStorageV2(bCenter, tFdKey, strAlarmGuid, alarmTime.getTime() / 1000, clientIp, seqUrlAddress);
			} else {
				getMssWebPrx(tFdKey.strWorkDomainId).QueryAlarmStorage(bCenter, tFdKey, strAlarmGuid, alarmTime.getTime() / 1000, seqUrlAddress);
			}
		}
		if (seqUrlAddress.value == null) {
			urls = new String[0];
		} else {
			urls = new String[seqUrlAddress.value.length];
			for (int i = 0; i < urls.length; i++) {
				urls[i] = seqUrlAddress.value[i].strUrl;
			}
		}
		return urls;
	}
}
