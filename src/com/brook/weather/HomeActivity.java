package com.brook.weather;

import java.util.ArrayList;
import com.brook.weather.entity.TabModel;
import com.brook.weather.widgets.recyclerview.BaseViewHolder;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends BaseListActivity<TabModel> {

	@Override
	protected void setUpContentView() {
		setContentView(R.layout.activity_main, 0);
	}

	// @Override
	// protected void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.activity_main);

	// findViewById(R.id.layout).setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	//
	// RequestEnvelope t = new RequestEnvelope();
	// RequestBody b = new RequestBody();
	// b.setTqyj(new Request("10", "0"));
	// t.setBody(b);
	//
	// WeatherRequest.buildXml().sayHi(t).subscribeOn(Schedulers.io())
	// .observeOn(AndroidSchedulers.mainThread())
	// .subscribe(new Action1<Response<ResponseEnvelope>>() {
	//
	// @Override
	// public void call(Response<ResponseEnvelope> reponse) {
	// ArrayList<Return> list = reponse.body().responseBody.model;
	// Log.e(TAG, list.toString());
	// }
	// }, new Action1<Throwable>() {
	//
	// @Override
	// public void call(Throwable throwable) {
	//
	// Log.e(TAG, throwable.getMessage().toString());
	// }
	// });
	// }
	// });

	// }

	@Override
	protected void setUpView() {
		super.setUpView();
	}

	@Override
	protected void setUpData() {
		super.setUpData();
		mDataList = new ArrayList<>();
		mDataList.add(new TabModel(R.drawable.tqyb, R.string.tabmoel_tqyb,
				WeatherForecastActivity.class));
		mDataList
				.add(new TabModel(R.drawable.dlyb, R.string.tabmoel_dlyb, null));
		mDataList
				.add(new TabModel(R.drawable.dqyb, R.string.tabmoel_dqyb, null));
		mDataList.add(new TabModel(R.drawable.tqyj, R.string.tabmoel_tqyj,
				WeatherWarningActivity.class));
		mDataList
				.add(new TabModel(R.drawable.zhgc, R.string.tabmoel_zhgc, null));
		mDataList
				.add(new TabModel(R.drawable.jcfw, R.string.tabmoel_jcfw, null));
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
			Intent intent = new Intent(HomeActivity.this,
					tabModel.activityClass);
			startActivity(intent);
		}
	}
}
