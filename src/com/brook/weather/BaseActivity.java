package com.brook.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

	private boolean isStartActivity;

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
	
	protected boolean needPendingTransition() {
        return true;
    }
	
	@Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        isStartActivity = true;
        if (needPendingTransition()) {
            overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
        }
    }
	
	@Override
    public void finish() {
        super.finish();
        if (!isStartActivity) {
            overridePendingTransition(R.anim.push_left_in, R.anim.push_right_out);
        }
    }
}
