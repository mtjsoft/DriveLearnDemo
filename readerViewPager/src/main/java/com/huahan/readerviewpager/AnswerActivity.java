package com.huahan.readerviewpager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.litepal.crud.DataSupport;

import android.annotation.SuppressLint;
import android.os.Message;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.huahan.hhbaseutils.HHLog;
import com.huahan.hhbaseutils.model.HHLoadState;
import com.huahan.hhbaseutils.ui.HHBaseDataActivity;
import com.huahan.readerviewpager.adapter.StudyAdapter;
import com.huahan.readerviewpager.imp.AdapterViewClickListener;
import com.huahan.readerviewpager.model.studyModel;
import com.huahan.readerviewpager.popu.AnswerCountPopupWindow;
import com.huahan.readerviewpager.utils.UserInfoUtils;
import com.huahan.readerviewpager.view.ReaderViewPager;

public class AnswerActivity extends HHBaseDataActivity implements
		AdapterViewClickListener {

	private ReaderViewPager viewpager;
	private List<studyModel> list = new ArrayList<studyModel>();
	private TextView yinyingTextView;
	private AnswerCountPopupWindow popu;
	private TextView chooseTextView;
	private int chooseNum = 0;
	private StudyAdapter adapter;
	private String mark;

	@Override
	public boolean initOnCreate() {
		// TODO Auto-generated method stub
		setPageTitle("答题");
		return false;
	}

	@Override
	public void onPageLoad() {
		// TODO Auto-generated method stub
		mark = getIntent().getStringExtra("mark");
		HHLog.i("mtj", "mark== " + mark);
		getData();
	}

	@Override
	public View initView() {
		// TODO Auto-generated method stub
		View view = View
				.inflate(getPageContext(), R.layout.activity_main, null);
		viewpager = getViewByID(view, R.id.rvp);
		yinyingTextView = getViewByID(view, R.id.tv_yinying);
		chooseTextView = getViewByID(view, R.id.tv_choose_count);
		return view;
	}

	@Override
	public void initValues() {
		// TODO Auto-generated method stub
		adapter = new StudyAdapter(getSupportFragmentManager(), list);
		viewpager.setAdapter(adapter);
		if (mark.equals("0")) {
			viewpager.setCurrentItem(chooseNum);
		}
		viewpager.setOffscreenPageLimit(3);
		chooseTextView.setText(chooseNum + "/" + list.size());
	}

	@SuppressLint("ClickableViewAccessibility")
	@SuppressWarnings("deprecation")
	@Override
	public void initListeners() {
		// TODO Auto-generated method stub
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				chooseNum = arg0;
				chooseTextView.setText(arg0 + "/" + list.size());
				if (mark.equals("0")) {
					UserInfoUtils.saveUserInfo(getPageContext(),
							UserInfoUtils.STUDY_ID, chooseNum + "");
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				yinyingTextView.setTranslationX(viewpager.getWidth() - arg2);
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
			}
		});
		chooseTextView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popu = new AnswerCountPopupWindow(getPageContext(),
						list.size(), chooseNum);
				popu.showAsDropDown(getBaseBottomLayout(), 0, 0);
				//popu.showAtLocation(chooseTextView, Gravity.TOP, 0, 0);
			}
		});
	}

	/**
	 * 获取数据
	 */
	private void getData() {
		// TODO Auto-generated method stub
		if (list.size() > 0) {
			list.clear();
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				switch (mark) {
				case "0":
					String a = UserInfoUtils.getUserInfo(getPageContext(),
							UserInfoUtils.STUDY_ID);
					if (TextUtils.isEmpty(a)) {
						UserInfoUtils.saveUserInfo(getPageContext(),
								UserInfoUtils.STUDY_ID, chooseNum + "");
					} else {
						chooseNum = Integer.parseInt(a);
					}
					list = DataSupport.findAll(studyModel.class);
					break;
				case "1":
					break;
				case "2":
					List<studyModel> listtemp = DataSupport
							.findAll(studyModel.class);
					int[] x = new int[listtemp.size()];
					for (int i = 0; i < x.length; i++) {
						x[i] = i;
					}
					for (int i = 0; i < x.length; i++) {
						Random random = new Random();
						// 随机出 i 至 x.length 的数
						// 目的：不再随机出已置换出去 的数 的下标
						int t = random.nextInt(x.length - i) + i;
						int temp = x[i];
						x[i] = x[t];
						x[t] = temp;
						list.add(i, listtemp.get(x[i]));
					}
					break;
				case "3":
					List<studyModel> listtemp2 = DataSupport
							.findAll(studyModel.class);
					int[] y = new int[listtemp2.size()];
					for (int i = 0; i < y.length; i++) {
						y[i] = i;
					}
					for (int i = 0; i < 100; i++) {
						Random random = new Random();
						// 随机出 i 至 b 的数
						// 目的：不再随机出已置换出去 的数 的下标
						int t = random.nextInt(100 - i) + i;
						int temp = y[i];
						y[i] = y[t];
						y[t] = temp;
						list.add(i, listtemp2.get(y[i]));
					}
					break;
				default:
					break;
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
		}
	}

	@Override
	public void adapterViewClick(int position, View view) {
		// TODO Auto-generated method stub
		viewpager.setCurrentItem(position,false);
		viewpager.setOffscreenPageLimit(3);
	}
}
