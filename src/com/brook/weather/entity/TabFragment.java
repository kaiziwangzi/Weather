package com.brook.weather.entity;

import android.support.v4.app.Fragment;

public class TabFragment {
	public int iconResId;
    public int labelResId;
    public Class<? extends Fragment> targetFragmentClz;


    public TabFragment(int labelResId, Class<? extends Fragment> targetFragmentClz) {
        this.labelResId = labelResId;
        this.targetFragmentClz = targetFragmentClz;
    }

    public TabFragment(int labelResId, int iconResId, Class<? extends Fragment> targetFragmentClz) {
        this.labelResId = labelResId;
        this.iconResId = iconResId;
        this.targetFragmentClz = targetFragmentClz;
    }
}
