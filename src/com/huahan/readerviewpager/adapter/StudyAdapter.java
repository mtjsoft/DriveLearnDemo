package com.huahan.readerviewpager.adapter;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.huahan.readerviewpager.fragment.answerFragment;
import com.huahan.readerviewpager.model.studyModel;

public class StudyAdapter extends FragmentPagerAdapter {

	private List<studyModel> list;

	public StudyAdapter(FragmentManager fm, List<studyModel> list) {
		super(fm);
		// TODO Auto-generated constructor stub
		this.list = list;
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		answerFragment fragment = new answerFragment();
		Bundle bundle = new Bundle();
		bundle.putSerializable("modelData", list.get(arg0));
		bundle.putString("posi", arg0+"");
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

}
