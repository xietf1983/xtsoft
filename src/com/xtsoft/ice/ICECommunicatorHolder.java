package com.xtsoft.ice;

import org.apache.log4j.Logger;

import com.xtsoft.exception.SystemException;

import Ice.InitializationData;
import Ice.LocalException;


public class ICECommunicatorHolder {
	private Ice.Communicator iCommunicator = null;
	private static ICECommunicatorHolder instance = new ICECommunicatorHolder();
	private static Logger iLog = Logger.getLogger(ICECommunicatorHolder.class);

	private ICECommunicatorHolder() {
	}

	public static ICECommunicatorHolder getInstance() {
		return instance;
	}

	/**
	 * 得到ICE平台Communicator，如果还没初始化，则先初始化 现在设计为客户端和服务端共用一个Communicator
	 * 
	 * @return Communicator
	 * @throws NanwangException
	 */
	public Ice.Communicator getICECommunicator() throws SystemException {
		if (iCommunicator == null) {
			synchronized (this) {
				if (iCommunicator == null) {
					try {
						InitializationData initData = new InitializationData();
						try {
							initData.properties = IcePropertiesUtil
									.getInstance().initProeperties("ice.xml");
						} catch (LocalException ex) {
							iLog.error("读ice配置失败" + ": " + ex);
						} catch (java.lang.Exception ex) {
							iLog.error("读ice配置失败" + ": unknown exception");
						}
						// 采用统一的log4j日志
						initData.logger = new ICELogger(Logger
								.getLogger("ICE.Communicator"));
						iCommunicator = Ice.Util.initialize(initData);
					} catch (Ice.LocalException ex) {
						// ex.printStackTrace();
						if (iCommunicator != null) {
							iCommunicator.destroy();
						}
						throw new SystemException("和提供服务的服务器的通讯失败，请重试或联系客服。",
								ex);
					}
				}
			}
		}
		return iCommunicator;
	}

	/**
	 * destroy释放ICE资源 注意在程序退出时，如SPICEServer的contextDestroyed事件中释放资源
	 */
	public synchronized void destroy() {
		if (iCommunicator != null) {
			iLog.info("关闭服务器的ice平台...");
			try {
				iCommunicator.destroy();
				iCommunicator = null;
				iLog.info("ice iCommunicator is stopped.");
			} catch (Exception ex) {
				iLog.error("关闭服务器的ice平台失败", ex);
			}
		}
	}

}
