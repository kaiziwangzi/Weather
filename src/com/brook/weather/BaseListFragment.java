package com.brook.weather;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.brook.weather.widgets.recyclerview.BaseViewHolder;
import com.brook.weather.widgets.recyclerview.DividerItemDecoration;
import com.brook.weather.widgets.recyclerview.WeatherRecyclerView;
import com.brook.weather.widgets.recyclerview.WeatherRecyclerView.OnRefreshListener;

/**
 * Created by Stay on 8/3/16. Powered by www.stay4it.com
 */
public abstract class BaseListFragment<T> extends BaseFragment implements
		OnRefreshListener,
		android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener {
	protected WeatherRecyclerView recyclerView;
	protected RecyclerView.Adapter adapter;
	protected ArrayList<T> mDataList;
	protected RecyclerView.LayoutManager layoutManager;
	protected SwipeRefreshLayout mSwipeRefreshLayout;
	protected static final int MODE_REFRESH = 100;
	private int mode;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.base_list_layout, container, false);
	}

	@Override
	public void setUpView(View view) {
		recyclerView = (WeatherRecyclerView) view
				.findViewById(R.id.recyclerview);
		if (mode == MODE_REFRESH) {
			mSwipeRefreshLayout = (SwipeRefreshLayout) view
					.findViewById(R.id.pulltorefreshlayout);
		}
		setUpAdapter();
		layoutManager = getRecyclerLayoutManager();
		recyclerView.setRecyclerLayoutManager(layoutManager);
		RecyclerView.ItemDecoration decoration = getItemDecoration();
		if (decoration != null) {
			recyclerView.addItemDecoration(decoration);
		}
		recyclerView.setAdapter(adapter);
		if (mode == MODE_REFRESH) {
			mSwipeRefreshLayout.setOnRefreshListener(this);
			mSwipeRefreshLayout.post(new Runnable() {
				@Override
				public void run() {
					mSwipeRefreshLayout.setRefreshing(true);
					onRefresh();
				}
			});
		}
	}

	@Override
	public void setUpData() {

	}

	protected void setUpAdapter() {
		adapter = new BaseListAdapter();
	}

	protected RecyclerView.LayoutManager getRecyclerLayoutManager() {
		return new LinearLayoutManager(getContext());
	}

	protected RecyclerView.ItemDecoration getItemDecoration() {
		return new DividerItemDecoration(getContext(), R.drawable.list_divider);
	}

	protected BaseViewHolder getDataHolder(ViewGroup parent, int viewType) {
		throw new IllegalArgumentException(
				"you should override getDataHolder() to customize item UI!");
	}

	protected int getDataViewType(int position) {
		return 0;
	}

	public class BaseListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

		@Override
		public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			return getDataHolder(parent, viewType);
		}

		@Override
		public int getItemViewType(int position) {
			return getDataViewType(position);
		}

		@Override
		public int getItemCount() {
			return mDataList != null ? mDataList.size() : 0;
		}

		@Override
		public void onBindViewHolder(BaseViewHolder holder, int position) {
			holder.onBind(mDataList.get(position));
		}
	}

	protected boolean isSectionHeader(int position) {
		return false;
	}

	protected int getItemType(int position) {
		return 0;
	}

	protected abstract BaseViewHolder getViewHolder(ViewGroup parent,
			int viewType);

}
