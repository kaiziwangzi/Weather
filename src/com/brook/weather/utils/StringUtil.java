package com.brook.weather.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringUtil {

	// 获取请求body
	public static String getRequestBody(String method, String... names) {
		String name = "";
		for (int i = 1; i <= names.length; i++) {
			name = name + "<name" + i + " i:type='d:string'>" + names[i - 1]
					+ "</name" + i + ">";
		}
		String body = "<v:Envelope xmlns:i='http://www.w3.org/2001/XMLSchema-instance' xmlns:d='http://www.w3.org/2001/XMLSchema' xmlns:c='http://schemas.xmlsoap.org/soap/encoding/' xmlns:v='http://schemas.xmlsoap.org/soap/envelope/'><v:Header /><v:Body><n0:"
				+ method
				+ " id='o0' c:root='1' xmlns:n0='http://find/'>"
				+ name + "</n0:" + method + "></v:Body></v:Envelope>";
		return body;
	}
	
	public static String getWxytTitle(String filename){
		filename=filename.substring(0, 21).replaceAll("_", "");
		String biaozhi,year,month,date,hour,minute;
		biaozhi=filename.substring(0, 4);
		year=filename.substring(4, 8);
		month=filename.substring(8, 10);
		date=filename.substring(10, 12);
		hour=filename.substring(12, 14);
		minute=filename.substring(14, 16);
								String shijian_convert=year
										+"-"
										+month
										+"-"
										+date
										+" "
										+hour
										+":"
										+minute;
								
								SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
								
								Date dt = null;
								try {
									dt = sdf.parse(shijian_convert);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								Calendar rightNow = Calendar.getInstance();
								rightNow.setTime(dt);
								rightNow.add(Calendar.HOUR_OF_DAY,+8);//日期减1年
								
								Date dt1=rightNow.getTime();
								String now = biaozhi+sdf.format(dt1);
		return now;
	}
	
	public static String getYMD(){
		 Date d = new Date();  
	     System.out.println(d);  
	     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
	     String dateNowStr = sdf.format(d);  
	     return dateNowStr;
	}
	
	public static String getY_M_D(){
		 Date d = new Date();  
	     System.out.println(d);  
	     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	     String dateNowStr = sdf.format(d);  
	     return dateNowStr;
	}
	
	public static boolean isEmpty(String str) {
		if (null == str || "".equals(str.trim()) || "null".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}
	
	public static int getDisasterIcon(String yj){
		int start = 0;
		int end = 0;
		for(int i=0;i<yj.length();i++){
			if(yj.charAt(i)=='布'){
				start = i+1;
			}
			if(yj.charAt(i)=='['){
				end = i;
			}
		}
		return EmotionUtils.getImgByName(yj.substring(start,end));
	}
}

