package com.brook.weather;

import java.util.ArrayList;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brook.weather.entity.TabModel;
import com.brook.weather.widgets.recyclerview.BaseViewHolder;

public class ObservationActivity extends BaseListActivity<TabModel> {

	@Override
	protected void setUpContentView() {
		setContentView(R.layout.activity_observation, R.string.tabmoel_zhgc,
				MODE_BACK, 0);
	}

	@Override
	protected void setUpView() {
		super.setUpView();
	}

	@Override
	protected void setUpData() {
		super.setUpData();
		mDataList = new ArrayList<>();
		mDataList
				.add(new TabModel(R.drawable.yl, R.string.observation_yl, null));
		mDataList.add(new TabModel(R.drawable.ldpt, R.string.observation_ldpt,
				null));
		mDataList.add(new TabModel(R.drawable.wxyt, R.string.observation_wxyt,
				null));
		mDataList.add(new TabModel(R.drawable.trsf, R.string.observation_trsf,
				null));
		mDataList.add(new TabModel(R.drawable.zgqw, R.string.observation_zgqw,
				null));
		mDataList.add(new TabModel(R.drawable.zdqw, R.string.observation_zdqw,
				null));
		adapter.notifyDataSetChanged();
	}

	@Override
	protected LayoutManager getRecyclerLayoutManager() {
		return new GridLayoutManager(getApplicationContext(), 3);
	}

	@Override
	protected RecyclerView.ItemDecoration getItemDecoration() {
		return null;
	}

	@Override
	public void onRefresh(int mode) {

	}

	@Override
	protected BaseViewHolder<TabModel> getDataHolder(ViewGroup parent,
			int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.tab, parent, false);
		return new ViewHolder(view);
	}

	class ViewHolder extends BaseViewHolder<TabModel> {
		private ImageView mPurseIcon;
		private TextView mPurseItemLabel;

		public ViewHolder(View itemView) {
			super(itemView);
			mPurseItemLabel = (TextView) itemView
					.findViewById(R.id.mTabTextView);
			mPurseIcon = (ImageView) itemView.findViewById(R.id.mTabIamge);
		}

		@Override
		public void onBind(TabModel tab) {
			mPurseItemLabel.setText(tab.labelResId);
			mPurseIcon.setImageResource(tab.iconResId);
		}

		@Override
		public void onItemClick(View view, int position) {
			TabModel tabModel = mDataList.get(position);
			if (null != tabModel && null != tabModel.activityClass) {
				Intent intent = new Intent(ObservationActivity.this,
						tabModel.activityClass);
				startActivity(intent);
			}
		}
	}

}
