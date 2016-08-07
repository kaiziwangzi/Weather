package com.brook.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

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
}
