package com.brook.weather;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brook.weather.api.WeatherRequest;
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
 * 雷达拼图
 * @ClassName: LdptFragment 
 * @Description: TODO
 * @author yuanxw
 * @date 2016-8-21 下午1:01:53 
 * @copyright DPX
 */
public class LdptFragment extends BaseListFragment<Return>{
	private TabLayout tabLayout;
	private String[] typeStr={"组合反射率","小时降水","基本反射率","液态水含量"};
	private String[] type={"radar_cref","radar_ohp","radar_qref","radar_vil"};
	private int tabIndex = 0;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_ldpt, container, false);
	}

	@Override
	public void setUpView(View view) {
		mode = MODE_REFRESH;
		super.setUpView(view);
		tabLayout = (TabLayout)view.findViewById(R.id.mTablayout);
	}



	@Override
	public void setUpData() {
		super.setUpData();
		if (!isVisibleToUser && null == mDataList) {
			mDataList = new ArrayList<>();
			adapter.notifyDataSetChanged();
		}
		tabLayout.setTabMode(TabLayout.MODE_FIXED);
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
		buildTabs();
	}

	private void buildTabs() {
		for(int i=0;i<typeStr.length;i++){
			tabLayout.addTab(tabLayout.newTab().setText(typeStr[i])); 
		}
		
		tabLayout.setOnTabSelectedListener(new OnTabSelectedListener() {
			
			@Override
			public void onTabUnselected(Tab arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onTabSelected(Tab arg0) {
				tabIndex = arg0.getPosition();
				onRefresh(mode);
			}
			
			@Override
			public void onTabReselected(Tab arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public void onRefresh(int mode) {
		RequestEnvelope t = new RequestEnvelope();
		RequestBody b = new RequestBody();
		b.setJcfw(new Request(type[tabIndex]));// 重要预报：qxztfw 重要信息：zytqyb
		t.setBody(b);
		WeatherRequest.buildXml().sayHi(t).subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<Response<ResponseEnvelope>>() {

					@Override
					public void call(Response<ResponseEnvelope> response) {
						if (response.isSuccessful() && null != response.body()) {
							mDataList = response.body().responseBody.model1;
							mDataList.add(0,new Return());
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
	public void onRefresh() {
		if (null != mSwipeRefreshLayout) {
			onRefresh(WeatherRecyclerView.MODE_PULL_REFRESH);
		}
	}
	
	@Override
	protected BaseViewHolder getDataHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.observation_item, parent, false);
		return new ViewHolder(view);
	}
	
	class ViewHolder extends BaseViewHolder<Return> {
		private TextView mDescription1;
		private TextView mTitles;
		private TextView mPubdate;
		private ImageView mStatusImage;

		public ViewHolder(View itemView) {
			super(itemView);
			mDescription1 = (TextView) itemView
					.findViewById(R.id.mDescription1);
			mTitles = (TextView) itemView.findViewById(R.id.mTitles);
			mPubdate = (TextView) itemView.findViewById(R.id.mPubdate);
			mStatusImage = (ImageView) itemView.findViewById(R.id.mStatusImage);
		}

		@Override
		public void onBind(Return mReturn) {
			if(StringUtil.isEmpty(mReturn.filename)){
				mTitles.setText(typeStr[tabIndex]);
				mDescription1.setText("动态显示");
				mStatusImage.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.play));
			}else{
				mTitles.setText(mReturn.filename);
				mDescription1.setText(mReturn.inserttime);
				mStatusImage.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ldpt));
			}
		}

		@Override
		public void onItemClick(View view, int position) {
			Return mrReturn = mDataList.get(position);
			if (null != mrReturn) {
				if(!StringUtil.isEmpty(mrReturn.filename)){
					 Intent intent = new Intent(getActivity(),
							 PicViewerActivity.class);
							 intent.putExtra("file", mrReturn.path);
							 startActivity(intent);
				}else{
					 Intent intent = new Intent(getActivity(),
							 AnimatorActivity.class);
					 		 ArrayList<String> temp = new ArrayList<String>();
					 		 for(int i=1;i<mDataList.size();i++){
					 			 temp.add(mDataList.get(i).path);
					 		 }
					 		 intent.putExtra("imgs",(Serializable)temp);
							 startActivity(intent);
				}
			}
		}
	}

	@Override
	protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
		// TODO Auto-generated method stub
		return null;
	}

}
