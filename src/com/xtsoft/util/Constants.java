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

	public static Map<String, String> DataSourceMap = new HashMap(); // ������Դ˵��
	public static Map<String, String> DataSourceMapCode = new HashMap(); // ������Դ˵������
	public static Map<String, String> plateTypeMap = new HashMap(); // ��������
	public static Map<String, String> plateTypeMapCode = new HashMap(); // ������������
	public static Map<String, String> vehiclecolorMap = new HashMap(); // ������ɫ
	public static Map<String, String> vehiclecolorMapCode = new HashMap(); // ������ɫ����
	public static Map<String, String> plateTypecolorMap = new HashMap(); // ������ɫ
	public static Map<String, String> plateTypecolorMapCode= new HashMap(); // ������ɫ����
	public static Map<String, String> vehicleTypeMap = new HashMap(); // ��������
	public static Map<String, String> vehicleTypeMapCode  = new HashMap(); // ��������
	public static Map<String, String> vehiclefromMap = new HashMap();// �������
	public static Map<String, String> vehiclefromMapCode = new HashMap();// �������
	public static final String SOA = "SOA";
	public static final String DefauatDataSource = "�������Ӽ���豸";
	public static final String DefauatDataSourceCode = "09";
	public static final String plateType = "����";
	public static final String plateTypeCode = "99";
	public static final String vehiclecolor = "����";
	public static final String vehiclecolorCode = "Z";
	public static final String plateTypecolor = "����";
	public static final String plateTypecolorCode = "4";
	public static final String vehicleType = "��������";
	public static final String vehicleTypeCode = "2";
	public static final String vehiclefrom="����";
	public static final String defaltvehiclefromCode="01";
	static {
		// ������Դ��ʼ��
		DataSourceMap.put("1", "���ܿ���");
		DataSourceMap.put("01", "���Ӿ���");
		DataSourceMap.put("2", "���Ӿ���");
		DataSourceMap.put("02", "���Ӿ���");
		DataSourceMap.put("3", "�̶�����");
		DataSourceMap.put("03", "�̶�����");
		DataSourceMap.put("4", "��Ƶ���");
		DataSourceMap.put("04", "�̶�����");
		DataSourceMap.put("5", "�ƶ����Ӿ���");
		DataSourceMap.put("05", "�ƶ����Ӿ���");
		DataSourceMap.put("6", "�г���¼��");
		DataSourceMap.put("06", "�ƶ����Ӿ���");
		DataSourceMap.put("7", "�������");
		DataSourceMap.put("07", "�������");
		DataSourceMap.put("8", "�յ���");
		DataSourceMap.put("08", "�յ���");
		DataSourceMap.put("9", "�������Ӽ���豸");
		DataSourceMap.put("09", "�������Ӽ���豸");
		//** ���� *****/
		DataSourceMapCode.put("���ܿ���", "01");
		DataSourceMapCode.put("���Ӿ���", "02");
		DataSourceMapCode.put("�̶�����", "03");
		DataSourceMapCode.put("��Ƶ���", "04");
		DataSourceMapCode.put("�ƶ����Ӿ���", "05");
		DataSourceMapCode.put("�г���¼��", "06");
		DataSourceMapCode.put("�������", "07");
		DataSourceMapCode.put("�յ���", "08");
		DataSourceMap.put("�������Ӽ���豸", "09");
		// �������ͳ�ʼ��
		plateTypeMap.put("01", "��������");
		plateTypeMapCode.put("��������", "01");
		plateTypeMap.put("02", "С������");
		plateTypeMapCode.put("С������", "02");
		plateTypeMap.put("03", "ʹ������");
		plateTypeMapCode.put("ʹ������", "03");
		plateTypeMap.put("04", "�������");
		plateTypeMapCode.put("�������", "04");
		plateTypeMap.put("05", "��������");
		plateTypeMapCode.put("��������", "05");
		plateTypeMap.put("06", "�⼮����");
		plateTypeMapCode.put("�⼮����", "06");
		plateTypeMap.put("07", "��������Ħ�г�");
		plateTypeMapCode.put("��������Ħ�г�", "07");
		plateTypeMap.put("08", "���Ħ�г�");
		plateTypeMapCode.put("���Ħ�г�", "08");
		plateTypeMap.put("09", "ʹ��Ħ�г�");
		plateTypeMapCode.put("ʹ��Ħ�г�", "09");
		plateTypeMap.put("10", "���Ħ�г�");
		plateTypeMapCode.put("���Ħ�г�", "10");
		plateTypeMap.put("11", "����Ħ�г�");
		plateTypeMapCode.put("����Ħ�г�", "11");
		plateTypeMap.put("12", "�⼮Ħ�г�");
		plateTypeMapCode.put("�⼮Ħ�г�", "12");
		plateTypeMap.put("13", "ũ�����䳵");
		plateTypeMapCode.put("ũ�����䳵", "13");
		plateTypeMap.put("14", "������");
		plateTypeMapCode.put("������", "14");
		plateTypeMap.put("15", "�ҳ�");
		plateTypeMapCode.put("�ҳ�", "15");
		plateTypeMap.put("16", "��������");
		plateTypeMapCode.put("��������", "16");
		plateTypeMap.put("17", "����Ħ�г�");
		plateTypeMapCode.put("����Ħ�г�", "17");
		plateTypeMap.put("18", "ʵ������");
		plateTypeMapCode.put("ʵ������", "18");
		plateTypeMap.put("19", "ʵ��Ħ�г�");
		plateTypeMapCode.put("ʵ��Ħ�г�", "19");
		plateTypeMap.put("20", "��ʱ�뾳����");
		plateTypeMapCode.put("��ʱ�뾳����", "20");
		plateTypeMap.put("21", "��ʱ�뾳Ħ�г�");
		plateTypeMapCode.put("��ʱ�뾳Ħ�г�", "21");
		plateTypeMap.put("22", "��ʱ��ʻ����");
		plateTypeMapCode.put("��ʱ��ʻ����", "22");
		plateTypeMap.put("24", "��������");
		plateTypeMapCode.put("��������", "24");
		plateTypeMap.put("99", "����");
		plateTypeMapCode.put("����", "99");
		
		

		// ������ɫ��ʼ��
		vehiclecolorMap.put("A", "��");
		vehiclecolorMapCode.put("��", "A");
		vehiclecolorMap.put("B", "��");
		vehiclecolorMapCode.put("��", "B");
		vehiclecolorMap.put("C", "��");
		vehiclecolorMapCode.put("��", "C");
		vehiclecolorMap.put("D", "��");
		vehiclecolorMapCode.put("��", "D");
		vehiclecolorMap.put("E", "��");
		vehiclecolorMapCode.put("��", "E");
		vehiclecolorMap.put("F", "��");
		vehiclecolorMapCode.put("��", "F");
		vehiclecolorMap.put("G", "��");
		vehiclecolorMapCode.put("��", "G");
		vehiclecolorMap.put("H", "��");
		vehiclecolorMapCode.put("��", "H");
		vehiclecolorMap.put("I", "��");
		vehiclecolorMapCode.put("��", "I");
		vehiclecolorMap.put("J", "��");
		vehiclecolorMapCode.put("��", "J");
		vehiclecolorMap.put("Z", "����");
		vehiclecolorMapCode.put("����", "Z");
		// ������ɫ��ʼ��
		plateTypecolorMap.put("0", "��ɫ");
		plateTypecolorMapCode.put("��ɫ", "0");
		plateTypecolorMap.put("1", "��ɫ");
		plateTypecolorMapCode.put("��ɫ", "1");
		plateTypecolorMap.put("2", "��ɫ");
		plateTypecolorMapCode.put("��ɫ", "2");
		plateTypecolorMap.put("3", "��ɫ");
		plateTypecolorMapCode.put("��ɫ", "3");
		plateTypecolorMap.put("4", "����");
		plateTypecolorMapCode.put("����", "4");
		// �������ͳ�ʼ��
		/*****************/
		vehicleTypeMap.put("0", "С������");
		vehicleTypeMapCode.put("С������", "0");
		vehicleTypeMap.put("2", "��������");
		vehicleTypeMapCode.put("��������", "2");
		vehicleTypeMap.put("1", "��������");
		vehicleTypeMapCode.put("��������", "1");
		vehicleTypeMap.put("M", "Ħ�г�");
		vehicleTypeMapCode.put("Ħ�г�", "M");
		vehicleTypeMap.put("K", "�ͳ�");
		vehicleTypeMapCode.put("�ͳ�", "K");
		vehicleTypeMap.put("H", "����");
		vehicleTypeMapCode.put("����", "H");
		vehicleTypeMap.put("K33", "�γ�");
		vehicleTypeMapCode.put("�γ�", "K33");

		// ��������ʼ��
		vehiclefromMap.put("01", "������");
		vehiclefromMapCode.put("������", "01");
		vehiclefromMap.put("02", "����");
		vehiclefromMapCode.put("����", "02");
		vehiclefromMap.put("03", "����");
		vehiclefromMapCode.put("����", "03");
		vehiclefromMap.put("04", "������");
		vehiclefromMapCode.put("������", "04");
		vehiclefromMap.put("05", "���ϵ�����");
		vehiclefromMapCode.put("���ϵ�����", "05");
		vehiclefromMap.put("06", "����������");
		vehiclefromMapCode.put("����������", "06");
		vehiclefromMap.put("07", "����������");
		vehiclefromMapCode.put("����������", "07");
		vehiclefromMap.put("08", "����������");
		vehiclefromMapCode.put("����������", "08");
		//vehiclefromMap.put("99", "����");
		//vehiclefromMapCode.put("����", "99");

	}

	/**
	 * ���ݱ����ȡ����
	 * ��Դ
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
	 * ����������ȡ����
	 * ��Դ
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
	 * ���ݱ����ȡ����
	 * ����
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
	 * ����������ȡ����
	 * ����
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
	 * ���ݱ����ȡ����
	 * ������ɫ
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
	 * ����������ȡ����
	 * ������ɫ
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
	 * ���ݱ����ȡ����
	 * ������ɫ
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
	 * ����������ȡ����
	 * ������ɫ
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
	 * ���ݱ����ȡ����
	 * ��������
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
	 * ����������ȡ����
	 * ��������
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
	 * ���ݱ����ȡ����
	 * ��������
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
	 * ����������ȡ����
	 * ��������
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
