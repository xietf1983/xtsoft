package com.xtsoft.job;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xtsoft.model.GavehviolationEntity;
import com.xtsoft.service.GavehviolationServiceUtil;
import com.xtsoft.util.AppPropsWeb;
import com.xtsoft.util.CommUtils;
import com.xtsoft.util.DateUtil;
import com.xtsoft.util.FtpTest;

public class GavehviolationJob {
	private boolean isruning = false;
	private static Log iLog = LogFactory.getLog(GavehviolationJob.class.getName());
	public void run() {
		if (!isruning) {
			isruning = true;
			String date = DateUtil.getYestoday();
			try {
				long end = GavehviolationServiceUtil.getService().getMaxAlarmIdGavehviolation();
				long start = GavehviolationServiceUtil.getService().getMinAlarmIdGavehviolation();
				long rowspan = 300;
				iLog.error("作业开始:start"+start+"end:"+end);
				while(start <= end) {
                  List<GavehviolationEntity> list = GavehviolationServiceUtil.getService().getGavehviolationUpload(start, start + 1000);
                  start=start+1000;
					if(list.size()>0){
						iLog.error("发生数据size:"+list.size());
					}
                  for (GavehviolationEntity entity : list) { 
						try {
							GavehviolationServiceUtil.getService().saveGavehviolation(entity,date);
						} catch (Exception exx) {
							System.out.print(exx.toString());
						}
					}
				}
				//上传到FTP文件
				iLog.error("作业结束:");
				if(AppPropsWeb.getInstance().getProp("SENGDFTP")!= null && AppPropsWeb.getInstance().getProp("SENGDFTP").equals(1)){
					String maindir = AppPropsWeb.mainDir();
					File s = new File(maindir + "/" + date);
					FtpTest.uploadFile(s, date, AppPropsWeb.getInstance().getProp("ftpDir"));
					
					
				}


			} catch (Exception ex) {
				System.out.print(ex.toString());
				iLog.error(ex.toString());
			}
			isruning = false;
		}

	}
}
