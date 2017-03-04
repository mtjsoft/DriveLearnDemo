package com.huahan.readerviewpager;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;

import com.huahan.hhbaseutils.HHDensityUtils;
import com.huahan.hhbaseutils.HHViewHelper;
import com.huahan.hhbaseutils.model.HHLoadState;
import com.huahan.hhbaseutils.ui.HHBaseDataActivity;
import com.huahan.hhbaseutils.view.PagerSlidingTabStrip;
import com.huahan.readerviewpager.adapter.CommonPSTAdapter;
import com.huahan.readerviewpager.fragment.keMuFragment;
import com.huahan.readerviewpager.model.studyModel;
import com.huahan.readerviewpager.utils.UserInfoUtils;

/**
 * @author mtj
 *
 */
public class ChooseKeMuActivity extends HHBaseDataActivity {
	private ViewPager fragementViewPager;
	private List<Fragment> fragments;
	private PagerSlidingTabStrip pstTabStrip;
	private int position = 0;

	@Override
	public void onPageLoad() {
		// TODO Auto-generated method stub
		if (UserInfoUtils.isLogin(getPageContext())) {
			changeLoadState(HHLoadState.SUCCESS);
		} else {
			addData();
		}
	}

	@Override
	public boolean initOnCreate() {
		// TODO Auto-generated method stub
		setPageTitle("科目");
		return false;
	}

	@Override
	public View initView() {
		// TODO Auto-generated method stub
		View view = View.inflate(getPageContext(), R.layout.activity_my_ad,
				null);
		pstTabStrip = HHViewHelper.getViewByID(view, R.id.pt_my_order);
		fragementViewPager = HHViewHelper
				.getViewByID(view, R.id.pt_vp_my_order);
		return view;
	}

	@Override
	public void initValues() {
		// TODO Auto-generated method stub
		position = getIntent().getIntExtra("posi", 0);
		addDataToView();
	}

	@Override
	public void initListeners() {
		// TODO Auto-generated method stub
	}

	private void addData() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 250; j++) {
						studyModel model = new studyModel();
						model.setType(i + "");
						model.setId(j + i * 250);
						model.setAnswer_id((j + i * 250) + "");
						model.setTitle("我是题目== " + (j + i * 250));
						model.setContent("我是内容== " + (j + i * 250));
						model.save();
					}
				}
				sendHandlerMessage(13);
			}
		}).start();
	}

	@Override
	public void processHandlerMsg(Message msg) {
		// TODO Auto-generated method stub
		if (msg.what == 13) {
			changeLoadState(HHLoadState.SUCCESS);
			//已保存所有题
			UserInfoUtils.saveUserInfo(getPageContext(), UserInfoUtils.USER_ID,
					"1");
		}
	}

	/**
	 * 添加分类
	 */
	private void addDataToView() {
		fragments = new ArrayList<Fragment>();
		String[] typeArray;
		typeArray = getResources().getStringArray(R.array.my_kemu);
		keMuFragment fragement0 = new keMuFragment();
		keMuFragment fragement1 = new keMuFragment();
		keMuFragment fragement2 = new keMuFragment();
		keMuFragment fragement3 = new keMuFragment();
		Bundle bundle0 = new Bundle();
		Bundle bundle1 = new Bundle();
		Bundle bundle2 = new Bundle();
		Bundle bundle3 = new Bundle();
		bundle0.putString("mark", "0");
		bundle1.putString("mark", "1");
		bundle2.putString("mark", "2");
		bundle3.putString("mark", "3");
		fragement0.setArguments(bundle0);
		fragement1.setArguments(bundle1);
		fragement2.setArguments(bundle2);
		fragement3.setArguments(bundle3);
		fragments.add(fragement0);
		fragments.add(fragement1);
		fragments.add(fragement2);
		fragments.add(fragement3);
		CommonPSTAdapter adapter = new CommonPSTAdapter(
				getSupportFragmentManager(), getPageContext(), fragments,
				typeArray);
		fragementViewPager.setOffscreenPageLimit(typeArray.length);
		fragementViewPager.setAdapter(adapter);
		final int pageMargin = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 10, getResources()
						.getDisplayMetrics());
		fragementViewPager.setPageMargin(pageMargin);
		pstTabStrip.setViewPager(fragementViewPager);
		pstTabStrip.setUnderlineColorResource(R.color.main_base_color);
		pstTabStrip.setIndicatorHeight(HHDensityUtils.dip2px(getPageContext(),
				2));
		pstTabStrip.setUnderlineHeight(0);
		pstTabStrip.setIndicatorColorResource(R.color.main_base_color);
		pstTabStrip.setTextColorResource(R.color.gray_text);
		pstTabStrip.setSelectedTextColorResource(R.color.black_text);
		pstTabStrip.setShouldExpand(true);
		fragementViewPager.setCurrentItem(position);
	}
}
