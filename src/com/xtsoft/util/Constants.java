package com.xtsoft.util;

import java.util.HashMap;
import java.util.Map;

public class Constants {
	public static final long ALARMSESSIONID = 0;
	public static final String RULEID = "000000000000000000";
	public static final long ALARMSEQ = 0;
	public static final long ALARMTYPE = 39;
	public static final long ALARMTYPEMINOR = 0;
	public static final long SEVERITYLEVEL = 5;
	public static final String chinese = "[\u0391-\uFFE5]";

	public static final String VehicleInfosuceess = "0000";
	public static final String VehicleInfoerror = "0001";

	public static Map<String, String> DataSourceMap = new HashMap(); // 数据来源说明
	public static Map<String, String> DataSourceMapCode = new HashMap(); // 数据来源说明编码
	public static Map<String, String> plateTypeMap = new HashMap(); // 号牌类型
	public static Map<String, String> plateTypeMapCode = new HashMap(); // 号牌类型描述
	public static Map<String, String> vehiclecolorMap = new HashMap(); // 车身颜色
	public static Map<String, String> vehiclecolorMapCode = new HashMap(); // 车身颜色编码
	public static Map<String, String> plateTypecolorMap = new HashMap(); // 号牌颜色
	public static Map<String, String> plateTypecolorMapCode= new HashMap(); // 号牌颜色编码
	public static Map<String, String> vehicleTypeMap = new HashMap(); // 车辆类型
	public static Map<String, String> vehicleTypeMapCode  = new HashMap(); // 车辆类型
	public static Map<String, String> vehiclefromMap = new HashMap();// 方向编码
	public static Map<String, String> vehiclefromMapCode = new HashMap();// 方向编码
	public static final String SOA = "SOA";
	public static final String DefauatDataSource = "其它电子监控设备";
	public static final String DefauatDataSourceCode = "09";
	public static final String plateType = "其他";
	public static final String plateTypeCode = "99";
	public static final String vehiclecolor = "其他";
	public static final String vehiclecolorCode = "Z";
	public static final String plateTypecolor = "其他";
	public static final String plateTypecolorCode = "4";
	public static final String vehicleType = "其它车型";
	public static final String vehicleTypeCode = "2";
	public static final String vehiclefrom="其他";
	public static final String defaltvehiclefromCode="01";
	static {
		// 数据来源初始化
		DataSourceMap.put("1", "智能卡口");
		DataSourceMap.put("01", "电子警察");
		DataSourceMap.put("2", "电子警察");
		DataSourceMap.put("02", "电子警察");
		DataSourceMap.put("3", "固定测速");
		DataSourceMap.put("03", "固定测速");
		DataSourceMap.put("4", "视频监控");
		DataSourceMap.put("04", "固定测速");
		DataSourceMap.put("5", "移动电子警察");
		DataSourceMap.put("05", "移动电子警察");
		DataSourceMap.put("6", "行车记录仪");
		DataSourceMap.put("06", "移动电子警察");
		DataSourceMap.put("7", "数码相机");
		DataSourceMap.put("07", "数码相机");
		DataSourceMap.put("8", "诱导屏");
		DataSourceMap.put("08", "诱导屏");
		DataSourceMap.put("9", "其它电子监控设备");
		DataSourceMap.put("09", "其它电子监控设备");
		//** 编码 *****/
		DataSourceMapCode.put("智能卡口", "01");
		DataSourceMapCode.put("电子警察", "02");
		DataSourceMapCode.put("固定测速", "03");
		DataSourceMapCode.put("视频监控", "04");
		DataSourceMapCode.put("移动电子警察", "05");
		DataSourceMapCode.put("行车记录仪", "06");
		DataSourceMapCode.put("数码相机", "07");
		DataSourceMapCode.put("诱导屏", "08");
		DataSourceMap.put("其它电子监控设备", "09");
		// 号牌类型初始化
		plateTypeMap.put("01", "大型汽车");
		plateTypeMapCode.put("大型汽车", "01");
		plateTypeMap.put("02", "小型汽车");
		plateTypeMapCode.put("小型汽车", "02");
		plateTypeMap.put("03", "使馆汽车");
		plateTypeMapCode.put("使馆汽车", "03");
		plateTypeMap.put("04", "领馆汽车");
		plateTypeMapCode.put("领馆汽车", "04");
		plateTypeMap.put("05", "境外汽车");
		plateTypeMapCode.put("境外汽车", "05");
		plateTypeMap.put("06", "外籍汽车");
		plateTypeMapCode.put("外籍汽车", "06");
		plateTypeMap.put("07", "两、三轮摩托车");
		plateTypeMapCode.put("两、三轮摩托车", "07");
		plateTypeMap.put("08", "轻便摩托车");
		plateTypeMapCode.put("轻便摩托车", "08");
		plateTypeMap.put("09", "使馆摩托车");
		plateTypeMapCode.put("使馆摩托车", "09");
		plateTypeMap.put("10", "领馆摩托车");
		plateTypeMapCode.put("领馆摩托车", "10");
		plateTypeMap.put("11", "境外摩托车");
		plateTypeMapCode.put("境外摩托车", "11");
		plateTypeMap.put("12", "外籍摩托车");
		plateTypeMapCode.put("外籍摩托车", "12");
		plateTypeMap.put("13", "农用运输车");
		plateTypeMapCode.put("农用运输车", "13");
		plateTypeMap.put("14", "拖拉机");
		plateTypeMapCode.put("拖拉机", "14");
		plateTypeMap.put("15", "挂车");
		plateTypeMapCode.put("挂车", "15");
		plateTypeMap.put("16", "教练汽车");
		plateTypeMapCode.put("教练汽车", "16");
		plateTypeMap.put("17", "教练摩托车");
		plateTypeMapCode.put("教练摩托车", "17");
		plateTypeMap.put("18", "实验汽车");
		plateTypeMapCode.put("实验汽车", "18");
		plateTypeMap.put("19", "实验摩托车");
		plateTypeMapCode.put("实验摩托车", "19");
		plateTypeMap.put("20", "临时入境汽车");
		plateTypeMapCode.put("临时入境汽车", "20");
		plateTypeMap.put("21", "临时入境摩托车");
		plateTypeMapCode.put("临时入境摩托车", "21");
		plateTypeMap.put("22", "临时行驶汽车");
		plateTypeMapCode.put("临时行驶汽车", "22");
		plateTypeMap.put("24", "警用汽车");
		plateTypeMapCode.put("警用汽车", "24");
		plateTypeMap.put("99", "其它");
		plateTypeMapCode.put("其它", "99");
		
		

		// 车身颜色初始化
		vehiclecolorMap.put("A", "白");
		vehiclecolorMapCode.put("白", "A");
		vehiclecolorMap.put("B", "灰");
		vehiclecolorMapCode.put("灰", "B");
		vehiclecolorMap.put("C", "黄");
		vehiclecolorMapCode.put("黄", "C");
		vehiclecolorMap.put("D", "粉");
		vehiclecolorMapCode.put("粉", "D");
		vehiclecolorMap.put("E", "红");
		vehiclecolorMapCode.put("红", "E");
		vehiclecolorMap.put("F", "紫");
		vehiclecolorMapCode.put("紫", "F");
		vehiclecolorMap.put("G", "绿");
		vehiclecolorMapCode.put("绿", "G");
		vehiclecolorMap.put("H", "蓝");
		vehiclecolorMapCode.put("蓝", "H");
		vehiclecolorMap.put("I", "棕");
		vehiclecolorMapCode.put("棕", "I");
		vehiclecolorMap.put("J", "黑");
		vehiclecolorMapCode.put("黑", "J");
		vehiclecolorMap.put("Z", "其它");
		vehiclecolorMapCode.put("其它", "Z");
		// 号牌颜色初始化
		plateTypecolorMap.put("0", "白色");
		plateTypecolorMapCode.put("白色", "0");
		plateTypecolorMap.put("1", "黄色");
		plateTypecolorMapCode.put("黄色", "1");
		plateTypecolorMap.put("2", "蓝色");
		plateTypecolorMapCode.put("蓝色", "2");
		plateTypecolorMap.put("3", "黑色");
		plateTypecolorMapCode.put("黑色", "3");
		plateTypecolorMap.put("4", "其他");
		plateTypecolorMapCode.put("其他", "4");
		// 车辆类型初始化
		/*****************/
		vehicleTypeMap.put("0", "小型汽车");
		vehicleTypeMapCode.put("小型汽车", "0");
		vehicleTypeMap.put("2", "其它车型");
		vehicleTypeMapCode.put("其它车型", "2");
		vehicleTypeMap.put("1", "大型汽车");
		vehicleTypeMapCode.put("大型汽车", "1");
		vehicleTypeMap.put("M", "摩托车");
		vehicleTypeMapCode.put("摩托车", "M");
		vehicleTypeMap.put("K", "客车");
		vehicleTypeMapCode.put("客车", "K");
		vehicleTypeMap.put("H", "货车");
		vehicleTypeMapCode.put("货车", "H");
		vehicleTypeMap.put("K33", "轿车");
		vehicleTypeMapCode.put("轿车", "K33");

		// 方向编码初始化
		vehiclefromMap.put("01", "东向西");
		vehiclefromMapCode.put("东向西", "01");
		vehiclefromMap.put("02", "西向东");
		vehiclefromMapCode.put("西向东", "02");
		vehiclefromMap.put("03", "南向北");
		vehiclefromMapCode.put("南向北", "03");
		vehiclefromMap.put("04", "北向南");
		vehiclefromMapCode.put("北向南", "04");
		vehiclefromMap.put("05", "东南到西北");
		vehiclefromMapCode.put("东南到西北", "05");
		vehiclefromMap.put("06", "西北到东南");
		vehiclefromMapCode.put("西北到东南", "06");
		vehiclefromMap.put("07", "东北往西南");
		vehiclefromMapCode.put("东北往西南", "07");
		vehiclefromMap.put("08", "西南往东北");
		vehiclefromMapCode.put("西南往东北", "08");
		//vehiclefromMap.put("99", "其他");
		//vehiclefromMapCode.put("其他", "99");

	}

	/**
	 * 根据编码获取描述
	 * 来源
	 * @param key
	 */
	public static String getDataSourceCode(String key) {
		if (DataSourceMap.get(key) != null) {
			return DataSourceMap.get(key);
		} else if (DataSourceMapCode.get(key) != null) {
			return key;
		} else {
			return DefauatDataSource;
		}

	}

	/**
	 * 根据描述获取编码
	 * 来源
	 * @param key
	 * @return
	 */
	public static String getDataSourceKey(String key) {
		if (DataSourceMapCode.get(key) != null) {
			return DataSourceMapCode.get(key);
		}else{
			return  DefauatDataSourceCode;
		}

	}
	/**
	 * 根据编码获取描述
	 * 号牌
	 * @param key
	 * @return
	 */
	public static String getPlateTypeKey(String key){
		if (plateTypeMap.get(key) != null) {
			return plateTypeMap.get(key);
		}else{
			return  plateType;
		}
	} 
	
	/**
	 * 根据描述获取编码
	 * 号牌
	 * @param key
	 * @return
	 */
	public static String getPlateTypeKeyCode(String key){
		if (plateTypeMapCode.get(key) != null) {
			return plateTypeMapCode.get(key);
		}else{
			return  plateTypeCode;
		}
	} 
	/**
	 * 根据编码获取描述
	 * 车身颜色
	 * @param key
	 * @return
	 */
	public static String getVehiclecolor(String key){
		if (vehiclecolorMap.get(key) != null) {
			return vehiclecolorMap.get(key);
		}else{
			return  vehiclecolor;
		}
	} 
	
	/**
	 * 根据描述获取编码
	 * 车身颜色
	 * @param key
	 * @return
	 */
	public static String getVehiclecolorCode(String key){
		if (vehiclecolorMapCode.get(key) != null) {
			return vehiclecolorMapCode.get(key);
		}else{
			return  vehiclecolorCode;
		}
	} 
	/**
	 * 根据编码获取描述
	 * 号牌颜色
	 * 
	 * @param key
	 * @return
	 */
	public static String getPlateTypecolor(String key){
		if (plateTypecolorMap.get(key) != null) {
			return plateTypecolorMap.get(key);
		}else{
			return  plateTypecolor;
		}
	}
	/**
	 * 根据描述获取编码
	 * 号牌颜色
	 * 
	 * @param key
	 * @return
	 */
	public static String getPlateTypecolorCode(String key){
		if (plateTypecolorMapCode.get(key) != null) {
			return plateTypecolorMapCode.get(key);
		}else{
			return  plateTypecolorCode;
		}
	}
	/**
	 * 根据编码获取描述
	 * 车辆类型
	 * @param key
	 * @return
	 */
	public static String getVehicleType(String key){
		if (vehicleTypeMap.get(key) != null) {
			return vehicleTypeMap.get(key);
		}else{
			return  vehicleType;
		}
	}
	/**
	 * 根据描述获取编码
	 * 车辆类型
	 * 
	 * @param key
	 * @return
	 */
	public static String getVehicleTypeCode(String key){
		if (vehicleTypeMapCode.get(key) != null) {
			return vehicleTypeMapCode.get(key);
		}else{
			return  vehicleTypeCode;
		}
	}
	/**
	 * 根据编码获取描述
	 * 车辆类型
	 * @param key
	 * @return
	 */
	public static String getVehiclefrom(String key){
		if (vehiclefromMap.get(key) != null) {
			return vehiclefromMap.get(key);
		}else{
			return  vehiclefrom;
		}
	}
	/**
	 * 根据描述获取编码
	 * 车辆类型
	 * 
	 * @param key
	 * @return
	 */
	public static String getVehiclefromCode(String key){
		if (vehiclefromMapCode.get(key) != null) {
			return vehiclefromMapCode.get(key);
		}else{
			return  defaltvehiclefromCode;
		}
	}

}
