package com.brook.weather.adapter;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.brook.weather.webservice.response.Return;
import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;

public class DlybPicAdapter extends PagerAdapter{
	
	private List<Return> data;
	
	private Context context;
	
	public List<Return> getData() {
		return data;
	}

	public void setData(List<Return> data) {
		this.data = data;
	}

	public DlybPicAdapter(Context context,List<Return> data){
		this.context = context;
		this.data = data;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0==arg1;//官方提示这样写  
	}
	
	 @Override  
     public void destroyItem(ViewGroup container, int position, Object object)   {     
         container.removeView((View)object);//删除页卡  
     }  

	
	 @Override  
     public Object instantiateItem(ViewGroup container, int position) {  //这个方法用来实例化页卡  
		 ImageView iv = new ImageView(context);
		 LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		 iv.setLayoutParams(lp);
		 Glide.with(context).load(data.get(position).path).asBitmap().into(iv);
         container.addView(iv);//添加页卡  
         return iv;  
     }  

}
