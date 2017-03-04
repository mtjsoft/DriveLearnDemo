package com.huahan.readerviewpager.fragment;

import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.huahan.hhbaseutils.frag.HHBaseFragment;
import com.huahan.readerviewpager.R;
import com.huahan.readerviewpager.model.studyModel;

public class answerFragment extends HHBaseFragment {

	private studyModel model;
	private TextView tv;

	@Override
	public View initView() {
		// TODO Auto-generated method stub
		View view = View
				.inflate(getPageContext(), R.layout.fragment_view, null);
		tv = getViewByID(view, R.id.tv_show);
		return view;
	}

	@Override
	public void initValues() {
		// TODO Auto-generated method stub
		getBaseTopLayout().removeAllViews();
		model = (studyModel) getArguments().getSerializable("modelData");
		tv.setText(getArguments().getString("posi") + "\n" + model.getTitle()
				+ "\n" + model.getContent());
	}

	@Override
	public void initListeners() {
		// TODO Auto-generated method stub

	}

	@Override
	public void processHandlerMsg(Message msg) {
		// TODO Auto-generated method stub

	}

}
