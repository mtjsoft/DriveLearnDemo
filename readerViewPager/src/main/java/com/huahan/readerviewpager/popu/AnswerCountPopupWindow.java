package com.huahan.readerviewpager.popu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huahan.hhbaseutils.HHDensityUtils;
import com.huahan.hhbaseutils.HHScreenUtils;
import com.huahan.hhbaseutils.HHTipUtils;
import com.huahan.hhbaseutils.HHViewHelper;
import com.huahan.readerviewpager.R;
import com.huahan.readerviewpager.adapter.countAdapter;
import com.huahan.readerviewpager.imp.AdapterViewClickListener;

/**
 * 选择题号
 * 
 * @author android.mtj
 *
 */
public class AnswerCountPopupWindow extends PopupWindow implements
		OnClickListener {

	private boolean is_move = false;
	private int count;
	private Context context;
	private GridView mostGridView;
	private AdapterViewClickListener clickListener;
	private TextView tv;

	@SuppressLint("ClickableViewAccessibility")
	@SuppressWarnings("deprecation")
	public AnswerCountPopupWindow(Context context, int count, int choose) {
		clickListener = (AdapterViewClickListener) context;
		this.count = count;
		this.context = context;
		int width = HHScreenUtils.getScreenWidth(context);
		final int height = HHScreenUtils.getScreenHeight(context);
		final View view = View.inflate(context, R.layout.popu_answer_count,
				null);
		mostGridView = HHViewHelper
				.getViewByID(view, R.id.most_gv_answer_count);
		final RelativeLayout rl_layout = HHViewHelper.getViewByID(view,
				R.id.rl_popu);
		tv = HHViewHelper.getViewByID(view, R.id.tv_count);
		tv.setText(choose + "/" + count);
		TextView cang = HHViewHelper.getViewByID(view, R.id.tv_cang);
		cang.setOnClickListener(this);
		countAdapter adapter = new countAdapter(context, count);
		mostGridView.setAdapter(adapter);
		mostGridView.smoothScrollToPositionFromTop(choose, 0, 300);
		mostGridView.setSelection(choose);
		mostGridView.setOnItemClickListener(new MyOnItemClick());
		// 设置SelectPicPopupWindow的View
		this.setContentView(view);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(width);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(height * 7 / 9);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		// 设置SelectPicPopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.style.my_popwindow_anim_style);
		// // 实例化一个ColorDrawable颜色为透明
		ColorDrawable dw = new ColorDrawable(context.getResources().getColor(
				R.color.transparent));
		// // 设置弹出窗体的背景
		this.setBackgroundDrawable(dw);
		// mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
		final int height_ll = HHDensityUtils.dip2px(context, 48);
		view.setOnClickListener(this);
		view.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int y = 0;
				int lastY = 0;
				switch (event.getActionMasked()) {
				case MotionEvent.ACTION_POINTER_DOWN:
					break;
				case MotionEvent.ACTION_DOWN:
					lastY = (int) event.getY();
					break;
				case MotionEvent.ACTION_UP:
					int up = (int) event.getY();
					if (!is_move) {
						dismiss();
					} else {
						if (up < height / 3) {
							RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) rl_layout
									.getLayoutParams();
							params.topMargin = 0;
							rl_layout.setLayoutParams(params);
						} else {
							dismiss();
						}
					}
					is_move = false;
					break;
				case MotionEvent.ACTION_MOVE:
					y = (int) event.getY();
					int offY = y - lastY;
					if (offY < 50) {
						// 防止手指点上去轻微移动，造成颤抖
						RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) rl_layout
								.getLayoutParams();
						params.topMargin = 0;
						rl_layout.setLayoutParams(params);
					} else if (offY >= 50
							&& offY <= (height * 7 / 9 - height_ll * 2)) {
						RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) rl_layout
								.getLayoutParams();
						params.topMargin = offY;
						rl_layout.setLayoutParams(params);
						is_move = true;
					} else {
						dismiss();
					}
					break;
				}
				return true;
			}
		});
	}

	private class MyOnItemClick implements OnItemClickListener {

		private MyOnItemClick() {
		};

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			tv.setText(position + "/" + count);
			clickListener.adapterViewClick(position, null);
			dismiss();
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_cang:
			HHTipUtils.getInstance().showToast(context, "已收藏");
			break;
		default:
			dismiss();
			break;
		}
	}
}
