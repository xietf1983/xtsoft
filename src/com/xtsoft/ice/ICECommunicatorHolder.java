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
	 * �õ�ICEƽ̨Communicator�������û��ʼ�������ȳ�ʼ�� �������Ϊ�ͻ��˺ͷ���˹���һ��Communicator
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
							iLog.error("��ice����ʧ��" + ": " + ex);
						} catch (java.lang.Exception ex) {
							iLog.error("��ice����ʧ��" + ": unknown exception");
						}
						// ����ͳһ��log4j��־
						initData.logger = new ICELogger(Logger
								.getLogger("ICE.Communicator"));
						iCommunicator = Ice.Util.initialize(initData);
					} catch (Ice.LocalException ex) {
						// ex.printStackTrace();
						if (iCommunicator != null) {
							iCommunicator.destroy();
						}
						throw new SystemException("���ṩ����ķ�������ͨѶʧ�ܣ������Ի���ϵ�ͷ���",
								ex);
					}
				}
			}
		}
		return iCommunicator;
	}

	/**
	 * destroy�ͷ�ICE��Դ ע���ڳ����˳�ʱ����SPICEServer��contextDestroyed�¼����ͷ���Դ
	 */
	public synchronized void destroy() {
		if (iCommunicator != null) {
			iLog.info("�رշ�������iceƽ̨...");
			try {
				iCommunicator.destroy();
				iCommunicator = null;
				iLog.info("ice iCommunicator is stopped.");
			} catch (Exception ex) {
				iLog.error("�رշ�������iceƽ̨ʧ��", ex);
			}
		}
	}

}
