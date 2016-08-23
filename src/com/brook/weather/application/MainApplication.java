package com.brook.weather.application;

import java.io.File;

import android.app.Application;
import android.content.Context;

import com.brook.weather.constants.Constants;
import com.brook.weather.utils.FileUtil;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class MainApplication extends Application{

	@Override
	public void onCreate() {
		
		super.onCreate();
		
		initImageLoader(getApplicationContext());
	}
	
	
	public static void initImageLoader(Context context) {
		File cacheFile = new File(FileUtil.getPath(context)+Constants.CACHEPATH);

		@SuppressWarnings("deprecation")
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.diskCache(new UnlimitedDiskCache(cacheFile))
				.diskCacheFileCount(50)
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).build();

		ImageLoader.getInstance().init(config);
	}
}
