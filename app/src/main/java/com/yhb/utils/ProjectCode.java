package com.yhb.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.TextUtils;

import java.util.Random;

import com.yhb.base.ProjectUtil;

//生成验证码
public class ProjectCode {

	private static ProjectCode bmpCode;
	private static String mCode = "";

	public static ProjectCode getInstance(String code) {
		if (bmpCode == null) {
			bmpCode = new ProjectCode();
			mCode = code;
		}
		return bmpCode;
	}

	private static int DEFAULT_CODE_LENGTH = mCode.length();// 随机字符的个数

	private String code;
	private Random random = new Random();

	/**
	 * 生成验证码图片
	 *
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @return
	 */
	public Bitmap createBitmap(int width, int height) {

		int textSize = ProjectUtil.dip2px(26);

		Bitmap codeBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(codeBitmap);
		// canvas.drawColor(getResources().getColor(R.color.color_security_code));

		TextPaint textPaint = new TextPaint();
		textPaint.setAntiAlias(true);
		textPaint.setTextSize(textSize);
		textPaint.setStrokeWidth(3);
		textPaint.setStyle(Paint.Style.STROKE);
		textPaint.setTextAlign(Paint.Align.CENTER);

		code = createCode();
		int x = (width - textSize * 3) / 2;
		int y = (height + textSize) / 2;
		for (int index = 0; index < code.length(); index++) {
			textPaint.setColor(randomColor(1));
			canvas.drawText(code.charAt(index) + "", (x + textSize * index), y, textPaint);
		}
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			textPaint.setStrokeWidth(2);
			textPaint.setColor(randomColor(1));
			canvas.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width),
					random.nextInt(height), textPaint);
		}
		canvas.save(Canvas.ALL_SAVE_FLAG);
		canvas.restore();
		return codeBitmap;
	}

	public String getCode() {
		return code;
	}

	/**
	 * 确认结果是否匹配
	 *
	 * @param input
	 * @return
	 */
	public boolean Verification(String input) {
		if (TextUtils.isEmpty(code))
			return false;

		if (TextUtils.isEmpty(input))
			return false;

		return code.equalsIgnoreCase(input);
	}

	// 验证码
	private String createCode() {
		return mCode;
	}

	// 随机颜色
	private int randomColor(int rate) {
		int red = random.nextInt(256) / rate;
		int green = random.nextInt(256) / rate;
		int blue = random.nextInt(256) / rate;
		return Color.rgb(red, green, blue);
	}

}
