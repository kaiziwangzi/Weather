package com.brook.weather;

import java.util.ArrayList;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.brook.weather.webservice.response.Return;
import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 动画播放界面
 * @ClassName: AnimatorActivity 
 * @Description: TODO
 * @author yuanxw
 * @date 2016-8-22 上午9:19:48 
 * @copyright XLSTUDIO
 */
public class AnimatorActivity extends BaseActivity implements OnClickListener{

	private ArrayList<String> imgs;
	
	private ImageView playIv;
	
	private int playIndex;
	
	private ImageView playBtn;
	
	private boolean isPause = false;
	
	private int delayTime = 0;
	
	//用来处理UI动画
		private Handler handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what) {
				case 1:
					playIndex++;
					if(playIndex>=imgs.size()){
						delayTime = 300;
					}
					Glide.with(AnimatorActivity.this).load(imgs.get(playIndex%imgs.size())).asBitmap().dontAnimate().into(playIv);
//					ImageLoader.getInstance().displayImage(imgs.get(playIndex%imgs.size()), playIv);
					handler.sendEmptyMessageDelayed(1,delayTime);
					break;
				default:
					break;
				}
			}
		};
	@Override
	protected void setUpContentView() {
		setContentView(R.layout.activity_animator,
				R.string.animator, MODE_BACK);
		imgs = (ArrayList<String>) getIntent().getSerializableExtra("imgs");
	}

	@Override
	protected void setUpData() {
		
	}

	@Override
	protected void setUpView() {
		playIv = (ImageView) findViewById(R.id.iv_play);
		
		playBtn = (ImageView) findViewById(R.id.iv_play_btn);
		
		playBtn.setOnClickListener(this);
		
		findViewById(R.id.iv_next).setOnClickListener(this);
		
		if(imgs!=null&&imgs.size()>0){
			Glide.with(AnimatorActivity.this).load(imgs.get(0)).asBitmap().dontAnimate() .into(playIv);
//			ImageLoader.getInstance().displayImage(imgs.get(0), playIv);
			handler.sendEmptyMessageDelayed(1,delayTime);
		}
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.iv_play_btn:
			if(isPause){
				handler.sendEmptyMessageDelayed(1, delayTime);
			}else{
				handler.removeMessages(1);
			}
			isPause = !isPause;
			playBtn.setSelected(isPause);
			break;
		}
	}

	@Override
	public void finish() {
		super.finish();
		handler.removeMessages(1);
	}
	
	

}
