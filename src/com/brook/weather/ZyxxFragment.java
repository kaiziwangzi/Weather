package com.brook.weather;

import java.util.ArrayList;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brook.weather.api.WeatherRequest;
import com.brook.weather.utils.DateUtil;
import com.brook.weather.webservice.request.Request;
import com.brook.weather.webservice.request.RequestBody;
import com.brook.weather.webservice.request.RequestEnvelope;
import com.brook.weather.webservice.response.ResponseEnvelope;
import com.brook.weather.webservice.response.Return;
import com.brook.weather.widgets.recyclerview.BaseViewHolder;
import com.brook.weather.widgets.recyclerview.WeatherRecyclerView;

public class ZyxxFragment extends BaseListFragment<Return> {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_zsbg, container, false);
	}

	@Override
	public void setUpView(View view) {
		mode = MODE_REFRESH;
		super.setUpView(view);
	}

	@Override
	public void setUpData() {
		super.setUpData();
		Log.e("ssss", "isVisibleToUser:=" + isVisibleToUser);
		if (!isVisibleToUser && null == mDataList) {
			mDataList = new ArrayList<>();
			adapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onRefresh(int mode) {
		RequestEnvelope t = new RequestEnvelope();
		RequestBody b = new RequestBody();
		b.setJcfw(new Request("zytqyb"));// 重要预报：qxztfw 重要信息：zytqyb
		t.setBody(b);
		WeatherRequest.buildXml().sayHi(t).subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<Response<ResponseEnvelope>>() {

					@Override
					public void call(Response<ResponseEnvelope> response) {
						if (response.isSuccessful() && null != response.body()) {
							mDataList = response.body().responseBody.model1;
						}
						adapter.notifyDataSetChanged();
						mSwipeRefreshLayout.setRefreshing(false);
					}
				}, new Action1<Throwable>() {

					@Override
					public void call(Throwable arg0) {
						mSwipeRefreshLayout.setRefreshing(false);
					}
				});
	}

	@Override
	public boolean needLoadMore() {
		return false;
	}

	@Override
	public void onRefresh() {
		if (null != mSwipeRefreshLayout) {
			onRefresh(WeatherRecyclerView.MODE_PULL_REFRESH);
		}
	}

	protected RecyclerView.ItemDecoration getItemDecoration() {
		return null;
	}

	@Override
	protected BaseViewHolder getDataHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.item_warnning, parent, false);
		return new ViewHolder(view);
	}

	class ViewHolder extends BaseViewHolder<Return> {
		private TextView mDescription1;
		private TextView mTitles;
		private TextView mPubdate;

		public ViewHolder(View itemView) {
			super(itemView);
			mDescription1 = (TextView) itemView
					.findViewById(R.id.mDescription1);
			mTitles = (TextView) itemView.findViewById(R.id.mTitles);
			mPubdate = (TextView) itemView.findViewById(R.id.mPubdate);
		}

		@Override
		public void onBind(Return mReturn) {
			mTitles.setText(mReturn.filename);
			mDescription1.setText(mReturn.inserttime);
			mPubdate.setText(DateUtil.format(mReturn.path));
		}

		@Override
		public void onItemClick(View view, int position) {
			Return mrReturn = mDataList.get(position);
			if (null != mrReturn) {
				// Intent intent = new Intent(WeatherWarnningActivity.this,
				// WeatherWarnningDetailActivity.class);
				// intent.putExtra(Constants.ACTION_WARNNING_DETAIL, mrReturn);
				// startActivity(intent);
			}
		}
	}

	@Override
	protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
		return null;
	}
}
