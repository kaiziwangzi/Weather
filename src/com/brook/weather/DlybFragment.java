package com.brook.weather;

import java.util.ArrayList;

import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.widget.Toast;

import com.brook.weather.adapter.DlybPicAdapter;
import com.brook.weather.api.WeatherRequest;
import com.brook.weather.constants.Constants;
import com.brook.weather.utils.StringUtil;
import com.brook.weather.webservice.request.Request;
import com.brook.weather.webservice.request.RequestBody;
import com.brook.weather.webservice.request.RequestEnvelope;
import com.brook.weather.webservice.response.ResponseEnvelope;
import com.brook.weather.webservice.response.Return;
import com.brook.weather.widgets.MyViewPager;
/**
 * 短临预报
 * @ClassName: DlybFragment 
 * @Description: TODO
 * @author yuanxw
 * @date 2016-8-21 下午12:59:44 
 * @copyright XLSTUDIO
 */
public class DlybFragment extends BaseFragment{
	
	private TabLayout tabLayout;
	private String[] hour={"006","012"};
	private int tabIndex = 0;
	private MyViewPager mPager;
	private float xDistance, yDistance;
	/** 记录按下的X坐标  **/
	private float mLastMotionX,mLastMotionY;
	/** 是否是左右滑动   **/
	private boolean mIsBeingDragged = true;
	
	private DlybPicAdapter adapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_dlyb, container, false);
	}

	@Override
	public void setUpView(View view) {
		tabLayout = (TabLayout)view.findViewById(R.id.mTablayout);
		mPager = (MyViewPager) view.findViewById(R.id.iv);
		mPager.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				mPager.getGestureDetector().onTouchEvent(event);
				// TODO Auto-generated method stub
				final float x = event.getRawX();
				final float y = event.getRawY();
				
                switch (event.getAction()) {  
                case MotionEvent.ACTION_DOWN:  
                    xDistance = yDistance = 0f;
                	mLastMotionX = x;
                	mLastMotionY = y;
                case MotionEvent.ACTION_MOVE:  
                    final float xDiff = Math.abs(x - mLastMotionX);
                    final float yDiff = Math.abs(y - mLastMotionY);
                    xDistance += xDiff;
                    yDistance += yDiff;
                    
                    float dx = xDistance - yDistance;
                    /** 左右滑动避免和下拉刷新冲突   **/
                    if (xDistance > yDistance || Math.abs(xDistance - yDistance) < 0.00001f) {
                        mIsBeingDragged = true;
                        mLastMotionX =  x;
                        mLastMotionY = y;
                        ((ViewParent) v.getParent()).requestDisallowInterceptTouchEvent(true);
                    } else {
                        mIsBeingDragged = false;
                        ((ViewParent) v.getParent()).requestDisallowInterceptTouchEvent(false);
                    }
                    break;  
                case MotionEvent.ACTION_UP:  
                 	break;  
                case MotionEvent.ACTION_CANCEL:
                	if(mIsBeingDragged) {
                		((ViewParent) v.getParent()).requestDisallowInterceptTouchEvent(false);
					}
                	break;
                default:  
                    break;  
                }  
                return false;  
			}
		});
		((TextView)view.findViewById(R.id.tv_date)).setText(StringUtil.getY_M_D()+"制作");
	}

	@Override
	public void setUpData() {
		tabLayout.setTabMode(TabLayout.MODE_FIXED);
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
		buildTabs();
		getData();
	}

	private void getData() {
		RequestEnvelope t = new RequestEnvelope();
		RequestBody b = new RequestBody();
		b.setTqybc(new Request(Constants.ACTION_DLYB+hour[tabIndex], StringUtil.getYMD()));//006---6小时 012---12小时 第二个参数时间戳，返回的是文件形式的三个返回，用viewpager做展示
		t.setBody(b);
		WeatherRequest.buildXml().sayHi(t).subscribeOn(Schedulers.io())
		.observeOn(AndroidSchedulers.mainThread())
		.subscribe(new Action1<Response<ResponseEnvelope>>() {

			@Override
			public void call(Response<ResponseEnvelope> response) {
				ArrayList<Return> data = response.body().responseBody.model2;
				adapter = new DlybPicAdapter(getActivity(), data);
				mPager.setAdapter(adapter);
			}
		}, new Action1<Throwable>() {

			@Override
			public void call(Throwable arg0) {
				Toast.makeText(getActivity(),arg0.getMessage().toString(), 2000).show();
			}
		});
	}

	private void buildTabs() {
		for(int i=0;i<hour.length;i++){
			tabLayout.addTab(tabLayout.newTab().setText(hour[i].replace("0", "")+"小时")); 
		}
		
		tabLayout.setOnTabSelectedListener(new OnTabSelectedListener() {
			
			@Override
			public void onTabUnselected(Tab arg0) {
				
			}
			
			@Override
			public void onTabSelected(Tab arg0) {
				tabIndex=arg0.getPosition();
				getData();
			}
			
			@Override
			public void onTabReselected(Tab arg0) {
				
			}
		});
	}

}
