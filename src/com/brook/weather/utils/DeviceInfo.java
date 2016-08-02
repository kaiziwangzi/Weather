package com.brook.weather.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

public class DeviceInfo {

	/** 
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素) 
     */  
    public static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
    
    /**
	 * @Description：获取屏幕的宽度
	 * @param context
	 * @return
	 */
	public static int getScreentWidth(Context context) {

		return getDisplayMetrics(context).widthPixels;
	}
	
	/**
	 * @Description：获取屏幕的宽度
	 * @param context
	 * @return
	 */
	public static int getScreentHeight(Context context) {

		return getDisplayMetrics(context).heightPixels;
	}
	
	public static DisplayMetrics getDisplayMetrics(Context context) {
		DisplayMetrics metric = new DisplayMetrics();

		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);

		return metric;
	}
}
