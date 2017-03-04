package com.huahan.readerviewpager;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huahan.hhbaseutils.HHScreenUtils;
import com.huahan.hhbaseutils.HHViewHelper;
import com.huahan.readerviewpager.adapter.countAdapter;

/**
 * 购物车设置
 * 
 * @author android.mtj
 *
 */
public class AnswerCountPopupWindow extends PopupWindow {

	// private int count;
	// private Context context;
	private GridView mostGridView;
	private AdapterViewClickListener clickListener;

	public AnswerCountPopupWindow(Context context, int count, int choose) {
		clickListener = (AdapterViewClickListener) context;
		// this.count = count;
		// this.context = context;
		int width = HHScreenUtils.getScreenWidth(context);
		int height = HHScreenUtils.getScreenHeight(context);
		final View view = View.inflate(context, R.layout.popu_answer_count, null);
		mostGridView = HHViewHelper.getViewByID(view, R.id.most_gv_answer_count);
		final RelativeLayout rl_layout = HHViewHelper.getViewByID(view, R.id.rl_popu);
		TextView tv = HHViewHelper.getViewByID(view, R.id.tv_count);
		tv.setText(choose + "/" + count);
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
		// // 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(context.getResources().getColor(R.color.half_transparent));
		// // 设置SelectPicPopupWindow弹出窗体的背景
		this.setBackgroundDrawable(dw);
		// mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
		final int top = view.getTop();
		final int bottom = view.getBottom();
		final int height_tv = tv.getHeight();
		view.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int y = 0;
				int lastY = 0;
				switch (event.getActionMasked()) {
				case MotionEvent.ACTION_POINTER_DOWN:
					return true;
				case MotionEvent.ACTION_DOWN:
					lastY = (int) event.getY();
					dismiss();
					return true;
				case MotionEvent.ACTION_MOVE:
					y = (int) event.getY();
					if (y < top) {
					}else if (y > bottom - height_tv) {
						int offY = y - lastY;
						RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) rl_layout
								.getLayoutParams();
						params.topMargin = offY;
						rl_layout.setLayoutParams(params);
					}else {
						dismiss();
					}
					return true;
				default:
					return false;
				}
			}
		});
		// view.setOnTouchListener(new OnTouchListener() {
		// @Override
		// public boolean onTouch(View v, MotionEvent event) {
		// // TODO Auto-generated method stub
		// float di = 0;
		// switch (event.getActionMasked()) {
		// case MotionEvent.ACTION_POINTER_DOWN:
		// return true;
		// case MotionEvent.ACTION_DOWN:
		// di = event.getRawX();
		// return true;
		// case MotionEvent.ACTION_MOVE:
		// float move_di = event.getRawX() - di;
		// RelativeLayout.LayoutParams params =
		// (android.widget.RelativeLayout.LayoutParams) rl_layout
		// .getLayoutParams();
		// params.topMargin = (int) move_di;
		// rl_layout.setLayoutParams(params);
		// return true;
		// default:
		// return false;
		// }
		// }
		// });

		// view.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// dismiss();
		// }
		// });
	}

	private class MyOnItemClick implements OnItemClickListener {

		private MyOnItemClick() {
		};

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			clickListener.adapterViewClick(position, null);
			dismiss();
		}

	}
}
