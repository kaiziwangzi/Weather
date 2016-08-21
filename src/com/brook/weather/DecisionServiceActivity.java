package com.brook.weather;

import java.util.ArrayList;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.brook.weather.entity.TabFragment;

public class DecisionServiceActivity extends BaseActivity {

	private ArrayList<TabFragment> tabs;
	private TabLayout tabLayout;
	private ViewPager viewPager;
	private HomePageAdapter adapter;

	@Override
	protected void setUpContentView() {
		setContentView(R.layout.activity_decision_service,
				R.string.tabmoel_jcfw, MODE_BACK);
	}

	@Override
	protected void setUpView() {
		tabLayout = (TabLayout) findViewById(R.id.mTablayout);
		viewPager = (ViewPager) findViewById(R.id.mViewpager);
	}

	@Override
	protected void setUpData() {
		createTab();
		viewPager.setOffscreenPageLimit(2);
		adapter = new HomePageAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);

		tabLayout.setupWithViewPager(viewPager);
		tabLayout.setTabMode(TabLayout.MODE_FIXED);
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

		buildTabs();
	}

	private void createTab() {
		if (tabs == null) {
			tabs = new ArrayList<>();
			tabs.add(new TabFragment(R.string.decisionservice_zsbg,
					ZsbgFragment.class));
			tabs.add(new TabFragment(R.string.decisionservice_zybg,
					ZybgFragment.class));
			tabs.add(new TabFragment(R.string.decisionservice_zyxx,
					ZyxxFragment.class));
		}
	}

	private void buildTabs() {
		for (int i = 0; i < tabs.size(); i++) {
			TabLayout.Tab tab = tabLayout.getTabAt(i);
			View view = LayoutInflater.from(getApplicationContext()).inflate(
					R.layout.activity_home_tab_item, null);
			TextView mTabLabel = (TextView) view.findViewById(R.id.mTabLabel);
			mTabLabel.setText(tabs.get(i).labelResId);
			tab.setCustomView(view);
		}
	}

	public class HomePageAdapter extends FragmentPagerAdapter {
		public HomePageAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int i) {
			try {
				return tabs.get(i).targetFragmentClz.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public int getCount() {
			return tabs.size();
		}

	}

}
