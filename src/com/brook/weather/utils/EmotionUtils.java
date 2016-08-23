package com.brook.weather.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.brook.weather.R;

public class EmotionUtils implements Serializable {
	
	/**
	 * key-表情文字;value-表情图片资源
	 */
	public static Map<String, Integer> emojiMap;
	
	static {
		emojiMap = new HashMap<String, Integer>();
		emojiMap.put("暴雪橙色预警", R.drawable.bxcsb);
		emojiMap.put("暴雪红色预警", R.drawable.bxhsb);
		emojiMap.put("暴雪黄色预警", R.drawable.bxhusb);
		emojiMap.put("暴雪蓝色预警", R.drawable.bxlsb);
		emojiMap.put("暴雨橙色预警", R.drawable.bycsb);
		emojiMap.put("暴雨红色预警", R.drawable.byhsb);
		emojiMap.put("暴雨黄色预警", R.drawable.byhusb);
		emojiMap.put("暴雨蓝色预警", R.drawable.bylsb);
		emojiMap.put("冰雹橙色预警", R.drawable.bbcsb);
		emojiMap.put("冰雹红色预警", R.drawable.bbhsb);
		emojiMap.put("冰雹黄色预警", R.drawable.bbhusb);
		emojiMap.put("冰雹蓝色预警", R.drawable.bblsb);
		emojiMap.put("大风橙色预警", R.drawable.dfcsb);
		emojiMap.put("大风红色预警", R.drawable.dfhsb);
		emojiMap.put("大风黄色预警", R.drawable.dfhusb);
		emojiMap.put("大风蓝色预警", R.drawable.dflsb);
		emojiMap.put("大雾橙色预警", R.drawable.dwcsb);
		emojiMap.put("大雾红色预警", R.drawable.dwhsb);
		emojiMap.put("大雾黄色预警", R.drawable.dwhusb);
		emojiMap.put("大雾蓝色预警", R.drawable.dwlsb);
		emojiMap.put("道路结冰橙色预警", R.drawable.dljbcsb);
		emojiMap.put("道路结冰红色预警", R.drawable.dljbhsb);
		emojiMap.put("道路结冰黄色预警", R.drawable.dljbhusb);
		emojiMap.put("道路结冰蓝色预警", R.drawable.dljblsb);
		emojiMap.put("干旱橙色预警", R.drawable.ghcsb);
		emojiMap.put("干旱红色预警", R.drawable.ghhsb);
		emojiMap.put("干旱黄色预警", R.drawable.ghhusb);
		emojiMap.put("干旱蓝色预警", R.drawable.ghlsb);
		emojiMap.put("高温橙色预警", R.drawable.gwcsb);
		emojiMap.put("高温红色预警", R.drawable.gwhsb);
		emojiMap.put("高温黄色预警", R.drawable.gwhusb);
		emojiMap.put("高温蓝色预警", R.drawable.gwlsb);
		emojiMap.put("寒潮橙色预警", R.drawable.hccsb);
		emojiMap.put("寒潮红色预警", R.drawable.hchsb);
		emojiMap.put("寒潮黄色预警", R.drawable.hchusb);
		emojiMap.put("寒潮蓝色预警", R.drawable.hclsb);
		emojiMap.put("雷电橙色预警", R.drawable.ldcsb);
		emojiMap.put("雷电红色预警", R.drawable.ldhsb);
		emojiMap.put("雷电黄色预警", R.drawable.ldhusb);
		emojiMap.put("雷电蓝色预警", R.drawable.ldlsb);
		emojiMap.put("霾橙色预警", R.drawable.mcsb);
		emojiMap.put("霾红色预警", R.drawable.mhsb);
		emojiMap.put("霾黄色预警", R.drawable.mhusb);
		emojiMap.put("霾蓝色预警", R.drawable.mlsb);
		emojiMap.put("沙尘暴橙色预警", R.drawable.scbcsb);
		emojiMap.put("沙尘暴红色预警", R.drawable.scbhsb);
		emojiMap.put("沙尘暴黄色预警", R.drawable.scbhusb);
		emojiMap.put("沙尘暴蓝色预警", R.drawable.scblsb);
		emojiMap.put("霜冻橙色预警", R.drawable.xdcsb);
		emojiMap.put("霜冻红色预警", R.drawable.xdhsb);
		emojiMap.put("霜冻黄色预警", R.drawable.xdhusb);
		emojiMap.put("霜冻蓝色预警", R.drawable.xdlsb);
		emojiMap.put("台风橙色预警", R.drawable.tfcsb);
		emojiMap.put("台风红色预警", R.drawable.tfhsb);
		emojiMap.put("台风黄色预警", R.drawable.tfhusb);
		emojiMap.put("台风蓝色预警", R.drawable.tflsb);
		
	}
	
	public static int getImgByName(String imgName) {
		Integer integer = emojiMap.get(imgName);
		return integer == null ? -1 : integer;
	}
}
