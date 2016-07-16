package com.xtsoft.service;


public class GavehviolationServiceUtil {
	private static GavehviolationService service;
	public static GavehviolationService getService() {
		return service;
	}

	public void setService(GavehviolationService eventService) {
		GavehviolationServiceUtil.service = eventService;
	}

}
