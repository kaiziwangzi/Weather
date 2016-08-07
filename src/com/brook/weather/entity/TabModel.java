package com.brook.weather.entity;

public class TabModel {
	public int iconResId;
    public int labelResId;
    public String balance;
    public Class<?> activityClass;

    public TabModel(int iconResId, int labelResId, Class<?> activityClass) {
        this.iconResId = iconResId;
        this.labelResId = labelResId;
        this.activityClass = activityClass;
    }
}
