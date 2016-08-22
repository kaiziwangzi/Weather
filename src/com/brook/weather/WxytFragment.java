package com.brook.weather;

import java.util.ArrayList;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.brook.weather.ZyxxFragment.ViewHolder;
import com.brook.weather.api.WeatherRequest;
import com.brook.weather.constants.Constants;
import com.brook.weather.utils.DateUtil;
import com.brook.weather.utils.StringUtil;
import com.brook.weather.webservice.request.Request;
import com.brook.weather.webservice.request.RequestBody;
import com.brook.weather.webservice.request.RequestEnvelope;
import com.brook.weather.webservice.response.ResponseEnvelope;
import com.brook.weather.webservice.response.Return;
import com.brook.weather.widgets.recyclerview.BaseViewHolder;
import com.brook.weather.widgets.recyclerview.WeatherRecyclerView;
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
	public void setUpView(View view) {
		mode = MODE_REFRESH;
		super.setUpView(view);
	}

	@Override
	public void setUpData() {
		super.setUpData();
		if (!isVisibleToUser && null == mDataList) {
			mDataList = new ArrayList<>();
			adapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onRefresh(int mode) {
		RequestEnvelope t = new RequestEnvelope();
		RequestBody b = new RequestBody();
		b.setJcfw(new Request("fy2e"));//fy2e卫星云图
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
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected BaseViewHolder getDataHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.observation_item, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onRefresh() {
		if (null != mSwipeRefreshLayout) {
			onRefresh(WeatherRecyclerView.MODE_PULL_REFRESH);
		}
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
			mTitles.setText(StringUtil.getWxytTitle(mReturn.filename));
			mDescription1.setText(mReturn.inserttime);
			mPubdate.setText(DateUtil.format(mReturn.path));
		}

		@Override
		public void onItemClick(View view, int position) {
			Return mrReturn = mDataList.get(position);
			if (null != mrReturn) {
				 Intent intent = new Intent(getActivity(),
				 PicViewerActivity.class);
				 intent.putExtra("file", mrReturn.path);
				 startActivity(intent);
			}
		}
	}


	@Override
	protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
		// TODO Auto-generated method stub
		return null;
	}

}
