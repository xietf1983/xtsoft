package com.xtsoft.model;

import java.util.Date;

import com.xtsoft.kernel.base.entity.BasePersistenceEntity;
import com.xtsoft.util.Constants;

public class GavehviolationEntity extends BasePersistenceEntity<GavehviolationEntity>{
	private Long alarmId;
	private String alarmTimeStr;// 违法时间
	private String monitorLocationName;// 违法地点
	private String plateType;// 号牌种类
	private String plateNo;// 号牌号码
	private String redlightbegintime;// 红灯亮起时间
	private String redlightlast;// 红灯持续时间
	private String vehicleFrom;// 行驶方向
	private Integer speedMax;// 最发速度
	private Integer recordedSpeed;// 实际速度
	private String violationCode;// 违法代码
	private String violationName;// 违法行为;
	private Date alarmTime;
	private String centerStoreKey;
	private String storageAreaId;
	private String fdId;
	private Integer channelType;
	private Integer channelId;
	private String[] images;
	private final String split = "/**/";
	private Date dealTime;
	public Long getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(Long alarmId) {
		this.alarmId = alarmId;
	}
	public String getAlarmTimeStr() {
		return alarmTimeStr;
	}

	public void setAlarmTimeStr(String alarmTimeStr) {
		this.alarmTimeStr = alarmTimeStr;
	}

	public String getMonitorLocationName() {
		return monitorLocationName;
	}

	public void setMonitorLocationName(String monitorLocationName) {
		this.monitorLocationName = monitorLocationName;
	}

	public String getPlateType() {
		return plateType;
	}

	public void setPlateType(String plateType) {
		this.plateType = plateType;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public String getRedlightbegintime() {
		return redlightbegintime;
	}

	public void setRedlightbegintime(String redlightbegintime) {
		this.redlightbegintime = redlightbegintime;
	}

	public String getRedlightlast() {
		return redlightlast;
	}

	public void setRedlightlast(String redlightlast) {
		this.redlightlast = redlightlast;
	}

	public String getVehicleFrom() {
		return vehicleFrom;
	}

	public void setVehicleFrom(String vehicleFrom) {
		this.vehicleFrom = vehicleFrom;
	}

	public Integer getSpeedMax() {
		return speedMax;
	}

	public void setSpeedMax(Integer speedMax) {
		this.speedMax = speedMax;
	}

	public Integer getRecordedSpeed() {
		return recordedSpeed;
	}

	public void setRecordedSpeed(Integer recordedSpeed) {
		this.recordedSpeed = recordedSpeed;
	}

	public String getViolationCode() {
		return violationCode;
	}

	public void setViolationCode(String violationCode) {
		this.violationCode = violationCode;
	}

	public String getViolationName() {
		return violationName;
	}

	public void setViolationName(String violationName) {
		this.violationName = violationName;
	}

	public Date getAlarmTime() {
		return alarmTime;
	}

	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}

	public String getCenterStoreKey() {
		return centerStoreKey;
	}

	public void setCenterStoreKey(String centerStoreKey) {
		this.centerStoreKey = centerStoreKey;
	}

	public String getStorageAreaId() {
		return storageAreaId;
	}

	public void setStorageAreaId(String storageAreaId) {
		this.storageAreaId = storageAreaId;
	}

	public String getFdId() {
		return fdId;
	}

	public void setFdId(String fdId) {
		this.fdId = fdId;
	}

	public Integer getChannelType() {
		return channelType;
	}

	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}

	/**
	 * 违法数据格式
	 * 
	 * @return
	 */
	public String toUploadString() {
		StringBuffer buffer = new StringBuffer();
		if (getPlateType() != null && !getPlateType().equals("") && Constants.getPlateTypeKeyCode(getPlateType()) != null) {
			buffer.append("hpzl=");
			buffer.append(Constants.getPlateTypeKeyCode(getPlateType()));
			buffer.append(split);
		}
		if (getPlateNo() != null && !getPlateNo().equals("")) {
			buffer.append("hphm=");
			buffer.append(getPlateNo());
			buffer.append(split);
		}
		if (getAlarmTimeStr() != null && !getAlarmTimeStr().equals("")) {
			buffer.append("wfsj=");
			buffer.append(getAlarmTimeStr());
			buffer.append(split);
		}
		if (getViolationName() != null && !getViolationName().equals("")) {
			buffer.append("wfxw=");
			buffer.append(getViolationName());
			buffer.append(split);
		}
		if (getMonitorLocationName() != null && !getMonitorLocationName().equals("")) {
			buffer.append("wfdd=");
			buffer.append(getMonitorLocationName());
			buffer.append(split);
		}
		if (getVehicleFrom() != null && !getVehicleFrom().equals("")) {
			buffer.append("xsfx=");
			buffer.append(getVehicleFrom());
			buffer.append(split);
		}
		
		if (getRecordedSpeed() != null && getRecordedSpeed() > 0) {
			buffer.append("sjcs=");
			buffer.append(getRecordedSpeed());
			buffer.append(split);
		}
		if (getSpeedMax() != null && getSpeedMax() > 0) {
			buffer.append("yxcs=");
			buffer.append(getSpeedMax());
			buffer.append(split);
		}
		

		if (getViolationCode() != null && !getViolationCode().equals("")) {
			buffer.append("wfdm=");
			buffer.append(getViolationCode());
			buffer.append(split);
		}
		
		
		if (images != null && images.length > 0) {
			for (int j = 0; j < images.length; j++) {
				if (j <= 2) {
					buffer.append("tp");
					buffer.append(j + 1);
					buffer.append("file=");
					buffer.append(getAlarmId()+"-"+j);
					buffer.append(".jpg");
					buffer.append(split);
				}

			}
		}
		buffer.append("\r\n");
		return buffer.toString();

	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

}
