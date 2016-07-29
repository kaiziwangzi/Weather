package com.brook.weather.widgets;

import com.brook.weather.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TabView extends LinearLayout {
	private TextView mTabTextView;
	private ImageView mTabImage;

	public TabView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		inint(context);
	}

	public TabView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TabView(Context context) {
		this(context, null);
	}

	private void inint(Context context) {
		LayoutInflater.from(context).inflate(R.layout.tab, this, true);
		mTabTextView = (TextView) findViewById(R.id.mTabTextView);
		mTabImage = (ImageView) findViewById(R.id.mTabIamge);
	}

}
