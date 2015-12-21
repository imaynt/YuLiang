package com.zykj.yuliang.utils;

import android.view.View;
import android.view.animation.RotateAnimation;

public class RoatAnimeUtil {
	//�����Ķ��������ӳ�ʱ��
	public static void startAnimationOut(View view) {
		startAnimationOut(view, 0);

	}
	//����Ķ��������ӳ�ʱ��
	public static void startAnimationIn(View view) {
		startAnimationIn(view, 0);
	}
	//�����Ķ���
	//delayΪ�����ӳٵ�ʱ�䣬��λ�Ǻ���
	public static void startAnimationOut(View view, long delay) {
		RotateAnimation animation = new RotateAnimation(240, 180,
				view.getWidth() / 2, view.getHeight()/2);
		animation.setDuration(500);
		animation.setStartOffset(delay);
		animation.setFillAfter(true);
		view.startAnimation(animation);

	}
	//����Ķ���
	//delayΪ�����ӳٵ�ʱ�䣬��λ�Ǻ���
	public static void startAnimationIn(View view, long delay) {
		RotateAnimation animation = new RotateAnimation(180, 240,
				view.getWidth() / 2, view.getHeight()/2);
		animation.setDuration(500);
		animation.setStartOffset(delay);
		animation.setFillAfter(true);
		view.startAnimation(animation);
	}

}