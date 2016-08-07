package com.brook.weather.widgets.recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

public class WeatherRecyclerView extends RecyclerView {

	private static final int LINEAR = 1;
	private static final int GRID = 2;
	private static final int STAGGERED_GRID = 3;
	private static final int OTHER = 4;
	public static final int MODE_PULL_REFRESH = 101;
	public static final int MODE_LOAD_MORE = 102;
	private OnRefreshListener onRefreshListener;
	private boolean isRefreshing;
	private int layoutManagerType;
	private int[] lastScrollPositions;
	private RecyclerView.LayoutManager layoutManager;

	public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
		this.onRefreshListener = onRefreshListener;
	}

	public WeatherRecyclerView(Context context) {
		this(context, null);
	}

	public WeatherRecyclerView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public WeatherRecyclerView(Context arg0, AttributeSet arg1, int arg2) {
		super(arg0, arg1, arg2);
		init();
	}

	private void init() {
	}

	@Override
	public void onScrolled(int dx, int dy) {
		super.onScrolled(dx, dy);
		if (null != onRefreshListener && onRefreshListener.needLoadMore()) {
			checkIfNeedLoadMore();
		}
	}

	@Override
	public void onScrollStateChanged(int state) {
		super.onScrollStateChanged(state);
	}

	private void checkIfNeedLoadMore() {
		int lastVisibleItemPosition = findLastVisibleItemPosition(); // 当前最后一个可见的item
		int visibleItemCount = layoutManager.getChildCount(); // 可见item的个数
		int totalItemCount = layoutManager.getItemCount(); // 所有item的个数
		if (visibleItemCount > 0
				&& lastVisibleItemPosition == totalItemCount - 1
				&& !isRefreshing) {
			isRefreshing = true;
			onRefreshListener.onRefresh(MODE_LOAD_MORE);
		}
	}

	private int findLastVisibleItemPosition() {
		int lastVisibleItemPosition = -1;
		switch (layoutManagerType) {
		case LINEAR:
			lastVisibleItemPosition = ((LinearLayoutManager) layoutManager)
					.findLastCompletelyVisibleItemPosition();
			break;
		case GRID:
			lastVisibleItemPosition = ((GridLayoutManager) layoutManager)
					.findLastCompletelyVisibleItemPosition();
			break;
		case STAGGERED_GRID:
			StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
			if (lastScrollPositions == null)
				lastScrollPositions = new int[staggeredGridLayoutManager
						.getSpanCount()];

			staggeredGridLayoutManager
					.findLastCompletelyVisibleItemPositions(lastScrollPositions);
			lastVisibleItemPosition = findMax(lastScrollPositions);
			break;
		case OTHER:
			break;
		}
		return lastVisibleItemPosition;
	}

	private int findMax(int[] lastPositions) {
		int max = Integer.MIN_VALUE;
		for (int value : lastPositions) {
			if (value > max)
				max = value;
		}
		return max;
	}

	public void setRecyclerLayoutManager(
			RecyclerView.LayoutManager layoutManager) {
		this.layoutManager = layoutManager;
		setLayoutManager(layoutManager);
		if (layoutManager instanceof LinearLayoutManager) {
			layoutManagerType = LINEAR;
		} else if (layoutManager instanceof GridLayoutManager) {
			layoutManagerType = GRID;
		} else if (layoutManager instanceof StaggeredGridLayoutManager) {
			layoutManagerType = STAGGERED_GRID;
		} else {
			throw new RuntimeException(
					"Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
		}
	}

	public interface OnRefreshListener {
		void onRefresh(int mode);

		boolean needLoadMore();
	}

}
