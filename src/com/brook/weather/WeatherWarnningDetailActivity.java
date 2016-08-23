package com.brook.weather;

import com.brook.weather.constants.Constants;
import com.brook.weather.utils.DateUtil;
import com.brook.weather.utils.StringUtil;
import com.brook.weather.webservice.response.Return;

import android.widget.ImageView;
import android.widget.TextView;

public class WeatherWarnningDetailActivity extends BaseActivity {

	private TextView mTitles;
	private TextView mDescription1;
	private TextView mTime_date;
	private TextView mTime_time;
	private ImageView mStatusImage;

	@Override
	protected void setUpContentView() {
		setContentView(R.layout.activity_weather_warnning_detail,
				R.string.tabmoel_tqyj, MODE_BACK);
	}

	@Override
	protected void setUpView() {
		mTitles = (TextView) findViewById(R.id.mTitles);
		mDescription1 = (TextView) findViewById(R.id.mDescription1);
		mTime_date = (TextView) findViewById(R.id.mTime_date);
		mTime_time = (TextView) findViewById(R.id.mTime_time);
		mStatusImage = (ImageView)findViewById(R.id.mStatusImage);
	}

	@Override
	protected void setUpData() {
		Return mReturn = (Return) getIntent().getExtras().get(
				Constants.ACTION_WARNNING_DETAIL);
		if (null != mReturn) {
			mTitles.setText(mReturn.level1 + "预警");
			mDescription1.setText(mReturn.description1);
			mTime_date.setText(getString(R.string.warnning_detail_date,
					DateUtil.formatDate(mReturn.pubdate)));
			mTime_time.setText(DateUtil.formatTime(mReturn.pubdate));
			int icon = StringUtil.getDisasterIcon(mReturn.titles);
			if(icon!=-1){
				mStatusImage.setImageDrawable(getResources().getDrawable(icon));
			}
		}
	}

}
