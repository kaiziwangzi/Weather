package com.brook.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class BaseActivity extends AppCompatActivity {

	private boolean isStartActivity;
	protected TextView mTitleView;
	protected ImageView mBackView;
	protected FrameLayout mBackLayout;
	protected static final int MODE_BACK = 1;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setUpContentView();
		setUpView();
		setUpData();
	}

	protected abstract void setUpContentView();

	protected abstract void setUpData();

	/*
	 * 初始化View
	 */
	protected abstract void setUpView();

	public void setContentView(int layoutResID, int mode) {
		super.setContentView(layoutResID);
	}

	public void setContentView(int layoutResID, int titleResId, int mode) {
		super.setContentView(layoutResID);
		mTitleView = (TextView) findViewById(R.id.mTitle);
		mBackView = (ImageView) findViewById(R.id.mBackView);
		mBackLayout = (FrameLayout) findViewById(R.id.mBackLay);
		setUpMode(mode);
		setUpTitle(titleResId);
	}

	public void setUpTitle(int titleResId) {
		if (titleResId > 0) {
			mTitleView.setText(titleResId);
		}
	}

	public void setUpMode(int mode) {
		if (mode == MODE_BACK) {
			mTitleView.setVisibility(View.VISIBLE);
			mBackView.setImageResource(R.drawable.icon_title_back);
			mBackLayout.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					onNavigateClick();
				}

			});
		}
	}

	public void onNavigateClick() {
		finish();
	}

	protected boolean needPendingTransition() {
		return true;
	}

	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		super.startActivityForResult(intent, requestCode);
		isStartActivity = true;
		if (needPendingTransition()) {
			overridePendingTransition(R.anim.push_right_in,
					R.anim.push_left_out);
		}
	}

	@Override
	public void finish() {
		super.finish();
		if (!isStartActivity) {
			overridePendingTransition(R.anim.push_left_in,
					R.anim.push_right_out);
		}
	}
}
