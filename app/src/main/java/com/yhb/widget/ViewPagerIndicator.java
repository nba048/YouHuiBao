package com.yhb.widget;

import com.yhb.utils.DisplayUtil;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ViewPagerIndicator extends RadioGroup {
	private static final int A = 6;
	private static final int B = 3;
	private Context c;
	private int d = -1;
	private int e = 0;

	public ViewPagerIndicator(Context paramContext) {
		this(paramContext, null);
	}

	public ViewPagerIndicator(Context paramContext, int paramInt) {
		this(paramContext, null);
		e = paramInt;
		int i = e;
		a(i);
	}

	public ViewPagerIndicator(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
		setOrientation(0);
		c = paramContext;
	}

	public void a(int paramInt) {
		removeAllViews();
		if (paramInt > 0) {
			int j = 0;
			while (j < paramInt) {
				Context context = c;
				RadioButton radiobutton = new RadioButton(context);
				ColorDrawable colordrawable = new ColorDrawable(0x106000d);
				int k = colordrawable.getIntrinsicWidth();
				int l = colordrawable.getIntrinsicHeight();
				colordrawable.setBounds(0, 0, k, l);
				int i1 = DisplayUtil.dip2px(c, 6F);
				int j1 = DisplayUtil.dip2px(c, 6F);
				LayoutParams layoutparams = new LayoutParams(i1,
						j1);
				int k1 = DisplayUtil.dip2px(c, 3F);
				int l1 = DisplayUtil.dip2px(c, 3F);
				layoutparams.setMargins(k1, 0, l1, 0);
				radiobutton.setLayoutParams(layoutparams);
				radiobutton.setButtonDrawable(0x7f02008f);
				radiobutton.setClickable(false);
				addView(radiobutton);
				j++;
			}
		}
	}

	public int getCurrentPosition() {
		return d;
	}

	public int getMaxPosition() {
		return e;
	}

	public void setCurrentIndex(int i) {
		int j = getChildCount();
		if (i < j && i >= 0) {
			((RadioButton) getChildAt(i)).setChecked(true);
			d = i;
		}
	}
}
