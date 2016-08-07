package com.brook.weather.widgets.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder
		implements View.OnClickListener {

	public BaseViewHolder(View view) {
		super(view);
	}

	public abstract void onBind(T t);

	protected abstract void onItemClick(View view, int position);

	@Override
	public void onClick(View v) {
		onItemClick(v, getAdapterPosition());
	}

}
