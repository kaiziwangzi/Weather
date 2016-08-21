package com.brook.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brook.weather.webservice.response.Return;
import com.brook.weather.widgets.recyclerview.BaseViewHolder;
/**
 * 卫星云图
 * @ClassName: WxytFragment 
 * @Description: TODO
 * @author yuanxw
 * @date 2016-8-21 下午12:59:20 
 * @copyright DPX
 */
public class WxytFragment extends BaseListFragment<Return>{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_wxyt, container, false);
	}

	@Override
	public void onRefresh(int mode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean needLoadMore() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
		// TODO Auto-generated method stub
		return null;
	}

}
