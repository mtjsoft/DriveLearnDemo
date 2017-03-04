package com.huahan.readerviewpager.fragment;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.huahan.hhbaseutils.frag.HHBaseFragment;
import com.huahan.readerviewpager.AnswerActivity;
import com.huahan.readerviewpager.R;

public class keMuFragment extends HHBaseFragment implements OnClickListener {

	private TextView type1TextView;
	private TextView type2TextView;
	private TextView type3TextView;
	private TextView type4TextView;

	@Override
	public View initView() {
		// TODO Auto-generated method stub
		View view = View
				.inflate(getPageContext(), R.layout.fragmnet_kemu, null);
		type1TextView = getViewByID(view, R.id.tv_type_1);
		type2TextView = getViewByID(view, R.id.tv_type_2);
		type3TextView = getViewByID(view, R.id.tv_type_3);
		type4TextView = getViewByID(view, R.id.tv_type_4);
		return view;
	}

	@Override
	public void initValues() {
		// TODO Auto-generated method stub
		getBaseTopLayout().removeAllViews();
	}

	@Override
	public void initListeners() {
		// TODO Auto-generated method stub
		type1TextView.setOnClickListener(this);
		type2TextView.setOnClickListener(this);
		type3TextView.setOnClickListener(this);
		type4TextView.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = null;
		switch (v.getId()) {
		case R.id.tv_type_2:
			//intent.putExtra("mark", "1");
			break;
		case R.id.tv_type_1:
			intent = new Intent(getPageContext(), AnswerActivity.class);
			intent.putExtra("mark", "0");
			break;
		case R.id.tv_type_3:
			intent = new Intent(getPageContext(), AnswerActivity.class);
			intent.putExtra("mark", "2");
			break;
		case R.id.tv_type_4:
			intent = new Intent(getPageContext(), AnswerActivity.class);
			intent.putExtra("mark", "3");
			break;
		default:
			break;
		}
		if (intent != null) {
			startActivity(intent);
		}
	}

	@Override
	public void processHandlerMsg(Message msg) {
		// TODO Auto-generated method stub

	}
}
