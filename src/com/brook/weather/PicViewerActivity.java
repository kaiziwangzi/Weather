package com.brook.weather;

import com.brook.weather.widgets.ZoomImageView;
import com.bumptech.glide.Glide;

/**
 * 图片
 * @ClassName: AnimatorActivity 
 * @Description: TODO
 * @author yuanxw
 * @date 2016-8-22 上午9:19:48 
 * @copyright XLSTUDIO
 */
public class PicViewerActivity extends BaseActivity{
	private ZoomImageView iv;

	@Override
	protected void setUpContentView() {
		setContentView(R.layout.activity_picviewer,
				R.string.picviewer, MODE_BACK);
	}

	@Override
	protected void setUpData() {
		Glide.with(this).load(getIntent().getStringExtra("file")).asBitmap().into(iv);
	}

	@Override
	protected void setUpView() {
		iv = (ZoomImageView) findViewById(R.id.iv);
	}

}
