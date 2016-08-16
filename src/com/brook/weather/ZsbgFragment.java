package com.brook.weather;

import android.view.ViewGroup;

import com.brook.weather.widgets.recyclerview.BaseViewHolder;

public class ZsbgFragment extends BaseListFragment<String> {

	@Override
	public void onRefresh(int mode) {
		
	}

	@Override
	public boolean needLoadMore() {
		return false;
	}

	@Override
	public void onRefresh() {
		
	}

	@Override
	protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
		return null;
	}

}
