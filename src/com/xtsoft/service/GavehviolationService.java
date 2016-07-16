package com.xtsoft.service;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xtsoft.GeMss.MssProxy;
import com.xtsoft.job.GavehviolationJob;
import com.xtsoft.model.GavehviolationEntity;
import com.xtsoft.persistence.GavehviolationPersistence;
import com.xtsoft.util.AppPropsWeb;
import com.xtsoft.util.CommUtils;
import com.xtsoft.util.DateUtil;
import com.xtsoft.util.WebKeys;

public class GavehviolationService {
	private static Log iLog = LogFactory.getLog(GavehviolationService.class.getName());
	private GavehviolationPersistence persistence;

	public GavehviolationPersistence getPersistence() {
		return persistence;
	}

	public void setPersistence(GavehviolationPersistence persistence) {
		this.persistence = persistence;
	}

	/**
	 * 获取违法记录
	 * 
	 * @return
	 */
	public List<GavehviolationEntity> getGavehviolationUpload(long startId, long endId) {
		return getPersistence().getGavehviolationUpload(startId, endId);

	}

	/**
	 * 下载，保存图片保存图片
	 * 
	 * @param url
	 * @return
	 */
	public boolean saveURLResource(String url, String d) {
		boolean ret = true;
		try {
			URL source = new URL(url);
			File destination = new File(d);
			FileUtils.copyURLToFile(source, destination);
		} catch (Exception ex) {
			ret = false;
		}
		return ret;
	}

	public GavehviolationEntity saveGavehviolation(GavehviolationEntity gavehviolation,String filePath) throws Exception {
		Date date2 = new Date();
		if(true)  {
			int sucessFile = 0;
			String date = DateUtil.getYestoday();
			String maindir = AppPropsWeb.mainDir();
			File s = new File(maindir + "/" + date);
			if (!s.exists()) {
				s.mkdirs();
			}
			List<String> file = new ArrayList();
			//String ip ="";
			//String serviceApp = CommUtils.getPhotoRsIp(ip, gavehviolation.getFdId().substring(0, 6));
			iLog.error("接受一违法记录");
			try {
				if (gavehviolation.getCenterStoreKey() != null && gavehviolation.getCenterStoreKey().toUpperCase().indexOf("HTTP") == -1
						&& gavehviolation.getCenterStoreKey().toUpperCase().indexOf("FTP") == -1) {
					String fdId = gavehviolation.getFdId();
					short channelType = gavehviolation.getChannelType().shortValue();
					short channelId = gavehviolation.getChannelId().shortValue();
					String[] urls2 = MssProxy.getInstance().queryAlarmPicUrls(fdId, channelType, channelId, gavehviolation.getCenterStoreKey(), gavehviolation.getStorageAreaId(),
							gavehviolation.getAlarmTime(), "");
					if (urls2 != null && urls2.length > 0) {
						for (int k = 0; k < urls2.length; k++) {
							//urls2[k] = serviceApp + "/showfile?fileType=picUrlProxy&xwPicUrl=" + urls2[k];
						}
					}

					gavehviolation.setImages(urls2);

				} else {
					if (gavehviolation.getCenterStoreKey() != null && !gavehviolation.getCenterStoreKey().equals("")) {
						String[] arr2 = gavehviolation.getCenterStoreKey().split(";");
						if (arr2 != null && arr2.length > 0) {
							for (int k = 0; k < arr2.length; k++) {
								//arr2[k] = serviceApp + "/showfile?fileType=picUrlProxy&xwPicUrl=" + arr2[k];
							}
						}
						gavehviolation.setImages(arr2);
					}

				}

				// 第一步，下载图片
				if (gavehviolation != null && gavehviolation.getImages() != null && gavehviolation.getImages().length > 0) {
					for (String f : gavehviolation.getImages()) {
						if (sucessFile < 3) {
							if (saveURLResource(f, maindir + "/" + date + "/" + gavehviolation.getAlarmId() + "-" + sucessFile + ".jpg")) {
								sucessFile = sucessFile + 1;
								file.add(f);
							}
						}

					}

				}
				gavehviolation.setImages(file.toArray(new String[0]));
				if (sucessFile < 1) {
					gavehviolation.setImages(null);
				}
				if (gavehviolation.getImages() != null && gavehviolation.getImages().length > 0) {
					File data = new File(maindir + "/" + date + "/data.dat");
					if (!data.exists()) {
						data.createNewFile();
					}
					FileUtils.writeStringToFile(data, gavehviolation.toUploadString(), true);
				}
				// saveURLResource()
			} catch (Exception ex) {
				gavehviolation.setImages(null);
			}
			if (gavehviolation.getImages() != null && gavehviolation.getImages().length > 0) {
				// deletegavehviolationUpload(gavehviolation.getAlarmId());
				// 更新状态
				getPersistence().updategavehviolation(gavehviolation.getAlarmId());
				getPersistence().deletegavehviolationUpload(gavehviolation.getAlarmId());
				
			}else{
				if((new Date().getTime()-gavehviolation.getAlarmTime().getTime())>86400*30){
				getPersistence().deletegavehviolationUpload(gavehviolation.getAlarmId());
				}
			}
		}

		return gavehviolation;

	}

	public Long getMaxAlarmIdGavehviolation() {
		return getPersistence().getMaxAlarmIdGavehviolation();

	}

	public Long getMinAlarmIdGavehviolation() {
		return getPersistence().getMinAlarmIdGavehviolation();
	}

	public void insertgavehviolationUploadFail(long alarmId) throws Exception {
		getPersistence().insertgavehviolationUploadFail(alarmId);
	}

	public void deletegavehviolationUpload(long alarmId) throws Exception {
		getPersistence().deletegavehviolationUpload(alarmId);
	}
}
