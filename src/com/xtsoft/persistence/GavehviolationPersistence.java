package com.xtsoft.persistence;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xtsoft.kernel.base.persistence.BasePersistence;
import com.xtsoft.kernel.base.persistence.QueryParameter;
import com.xtsoft.model.GavehviolationEntity;
import com.xtsoft.util.AppPropsWeb;
import com.xtsoft.util.DateUtil;

public class GavehviolationPersistence extends BasePersistence<GavehviolationEntity> {
	/**
	 * 
	 */
	public List<GavehviolationEntity> getGavehviolationUpload(long startId, long endId) {
		QueryParameter queryParameter = new QueryParameter();
		Map parameter = new HashMap();
		parameter.put("START", startId);
		parameter.put("END", endId);
		//parameter.put("startTime", DateUtil.getYestoday()+" 00:00:00");
		//parameter.put("endTime",  DateUtil.getYestoday()+" 23:59:59");
		queryParameter.setParameter(parameter);
		return (List<GavehviolationEntity>) selectList("queryGavehviolationList", queryParameter);

	}

	public void deletegavehviolationUpload(long alarmId)throws Exception {
		GavehviolationEntity g = new GavehviolationEntity();
		g.setAlarmId(alarmId);
		removeEntity("gavehviolation_delete", g);

	}
	
	public void insertgavehviolationUploadFail(long alarmId)throws Exception {
		GavehviolationEntity g = new GavehviolationEntity();
		g.setAlarmId(alarmId);
		insertEntity("gaVehViolationUploadFail_insert", g);

	}
	
	public void   updategavehviolation(long alarmId)throws Exception {
		GavehviolationEntity g = new GavehviolationEntity();
		g.setAlarmId(alarmId);
		getSqlSession().update("gaVehViolation_update", g);
		

	}

	/**
	 * 返回最大值
	 * 
	 * @return
	 */
	public Long getMaxAlarmIdGavehviolation() {
		QueryParameter queryParameter = new QueryParameter();
		Map parameter = new HashMap();
		Date nowDate = new Date();
		Calendar cal = java.util.Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 0 - Integer.parseInt(AppPropsWeb.getBeforeDay()));
		Date maxUpdateTime = cal.getTime();
		parameter.put("ENDTIME", DateUtil.toString(maxUpdateTime) + " 23:59:59");
		queryParameter.setParameter(parameter);
		Object obj= selectOne("queryMaxGavehviolationAlarmId", queryParameter);
		if(obj==null){
			return 0l;
		}else{
			return (Long)obj;
		}
	}

	public Long getMinAlarmIdGavehviolation() {
		QueryParameter queryParameter = new QueryParameter();
		Map parameter = new HashMap();
		Date nowDate = new Date();
		Calendar cal = java.util.Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 0 - Integer.parseInt(AppPropsWeb.getBeforeDay()) - Integer.parseInt(AppPropsWeb.gettryTimes()));
		Date maxUpdateTime = cal.getTime();
		parameter.put("ENDTIME", DateUtil.toString(maxUpdateTime) + " 23:59:59");
		queryParameter.setParameter(parameter);
		Object obj =selectOne("queryGavehviolationMinAlarmId", queryParameter);
		if(obj==null){
			return 0l;
		}else{
			return (Long)obj;
		}
	}
}
