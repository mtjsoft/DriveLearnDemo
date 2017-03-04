package com.huahan.readerviewpager.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.huahan.hhbaseutils.HHDensityUtils;
import com.huahan.hhbaseutils.HHScreenUtils;
import com.huahan.hhbaseutils.HHViewHelper;
import com.huahan.readerviewpager.R;

public class countAdapter extends BaseAdapter {

	private Context context;
	private int count;

	public countAdapter(Context context, int count) {
		this.context = context;
		this.count = count;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.item_tv, null);
			holder.tvTextView = HHViewHelper.getViewByID(convertView,
					R.id.tv_item);
			holder.layout = HHViewHelper.getViewByID(convertView, R.id.ll_item);
			int size = (HHScreenUtils.getScreenWidth(context) - HHDensityUtils
					.dip2px(context, 70)) / 6;
			LinearLayout.LayoutParams params = new LayoutParams(size, size);
			holder.tvTextView.setLayoutParams(params);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvTextView.setText(position + "");
		return convertView;
	}

	private class ViewHolder {
		LinearLayout layout;
		TextView tvTextView;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
}
