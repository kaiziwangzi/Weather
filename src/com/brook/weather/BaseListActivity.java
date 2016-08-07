package com.brook.weather;

import java.util.ArrayList;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.brook.weather.widgets.recyclerview.BaseViewHolder;
import com.brook.weather.widgets.recyclerview.DividerItemDecoration;
import com.brook.weather.widgets.recyclerview.WeatherRecyclerView;
import com.brook.weather.widgets.recyclerview.WeatherRecyclerView.OnRefreshListener;

public abstract class BaseListActivity<T> extends BaseActivity implements
		OnRefreshListener {
	protected ArrayList<T> mDataList = new ArrayList<>();
	protected WeatherRecyclerView recyclerView;
	protected RecyclerView.Adapter adapter;
	protected RecyclerView.LayoutManager layoutManager;

	@Override
	protected void setUpContentView() {
		setContentView(R.layout.base_list_layout,0);
	}

	@Override
	protected void setUpView() {
		recyclerView = (WeatherRecyclerView) findViewById(R.id.recyclerview);
	}

	@Override
	protected void setUpData() {
		setUpAdapter();
		layoutManager = getRecyclerLayoutManager();
		recyclerView.setRecyclerLayoutManager(layoutManager);
		RecyclerView.ItemDecoration decoration = getItemDecoration();
		if (decoration != null) {
			recyclerView.addItemDecoration(decoration);
		}
		recyclerView.setAdapter(adapter);
		recyclerView.setOnRefreshListener(this);
	}

	protected void setUpAdapter() {
		adapter = new BaseListAdapter();
	}

	protected RecyclerView.ItemDecoration getItemDecoration() {
		return new DividerItemDecoration(getApplicationContext(),
				R.drawable.list_divider);
	}

	protected RecyclerView.LayoutManager getRecyclerLayoutManager() {
		return new LinearLayoutManager(getApplicationContext());
	}

	protected BaseViewHolder getDataHolder(ViewGroup parent, int viewType) {
		throw new IllegalArgumentException(
				"you should override getDataHolder() to customize item UI!");
	}

	protected int getDataViewType(int position) {
		return 0;
	}

	@Override
	public boolean needLoadMore() {
		return false;
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

}
