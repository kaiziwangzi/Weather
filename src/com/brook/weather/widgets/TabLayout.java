package com.brook.weather.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class TabLayout extends LinearLayout {

	public TabLayout(Context context) {
		this(context, null);
	}

	public TabLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public TabLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

}
