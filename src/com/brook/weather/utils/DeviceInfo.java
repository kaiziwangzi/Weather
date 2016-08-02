package com.brook.weather.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;

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
	
	/**
	 * 是否有导航栏
	 * @param context
	 * @return
	 */
	 public static boolean checkDeviceHasNavigationBar(Context context) {  
		  
	        //通过判断设备是否有返回键、菜单键(不是虚拟键,是手机屏幕外的按键)来确定是否有navigation bar  
	        boolean hasMenuKey = ViewConfiguration.get(context)  
	                .hasPermanentMenuKey();  
	        boolean hasBackKey = KeyCharacterMap  
	                .deviceHasKey(KeyEvent.KEYCODE_BACK);  
	  
	        if (!hasMenuKey && !hasBackKey) {  
	            // 做任何你需要做的,这个设备有一个导航栏  
	            return true;  
	        }  
	        return false;  
	    }  
	 
	 
	 /**
	  * 获取导航栏高度
	  * @param context
	  * @return
	  */
	 public static int getNavigationBarHeight(Context context) {  
	        Resources resources = context.getResources();  
	        int resourceId = resources.getIdentifier("navigation_bar_height",  
	                "dimen", "android");  
	        //获取NavigationBar的高度  
	        int height = resources.getDimensionPixelSize(resourceId);  
	        return height;  
	    }  
	 
	 
	 /**
	  * 获取状态栏高度
	  * @param context
	  * @return
	  */
	 public static int getStatusBarHeight(Context context) {
		  int result = 0;
		  int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
		  if (resourceId > 0) {
		      result = context.getResources().getDimensionPixelSize(resourceId);
		  }
		  return result;
		}

}
