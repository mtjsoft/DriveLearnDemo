package com.huahan.readerviewpager.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ReaderViewPager extends ViewPager {
	public ReaderViewPager(Context context) {
		this(context, null);
	}

	public ReaderViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		setReadEffect();
	}

	@SuppressLint("ClickableViewAccessibility")
	public void setReadEffect() {
		setPageTransformer(true, new PageTransformer() {
			// private static final float MIN_SCALE = 0.75f;
			@Override
			public void transformPage(View view, float position) {
				// TODO Auto-generated method stub
				int pageWidth = view.getWidth();
				if (position <= 0) {
					view.setTranslationX(0);
					view.setScaleX(1);
					view.setScaleY(1);
				} else if (position <= 1) { // (0,1]
					view.setAlpha(1);
					view.setTranslationX(pageWidth * -position);
				} else {
					view.setTranslationX(pageWidth);
					view.setScaleX(1);
					view.setScaleY(1);
				}
			}
		});
		setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN) {
					return true;
				} else {
					return false;
				}
			}
		});
	}
}
