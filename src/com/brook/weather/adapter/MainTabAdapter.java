package com.brook.weather.adapter;

import java.util.List;

import com.brook.weather.R;
import com.brook.weather.entity.Tab;
import com.brook.weather.utils.DeviceInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainTabAdapter extends BaseAdapter {

	private List<Tab> data;
	private LayoutInflater inflater = null;
	private int index = 0;
	private Context context;
	
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	class ViewHolder{
		TextView nameTv;
		ImageView tabIv;
		View tabLl;
	}
	
	public MainTabAdapter(Context context,List<Tab> data){
		this.data = data;
		inflater = LayoutInflater.from(context);
		this.context = context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		return data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if(convertView==null){
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.tab, null);
			holder.nameTv = (TextView)convertView.findViewById(R.id.mTabTextView);
			holder.tabIv = (ImageView)convertView.findViewById(R.id.mTabIamge);
			holder.tabLl = convertView.findViewById(R.id.ll_tab);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.nameTv.setText(data.get(position).getName());
		holder.tabIv.setImageDrawable(context.getResources().getDrawable(data.get(position).getDrawable()));
		return convertView;
	}

}
