package com.zykj.yuliang.activity;

import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.R;
import com.zykj.yuliang.utils.SharedPreferenceUtils;
import com.zykj.yuliang.utils.Tools;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class IntroActivity extends BaseActivity implements OnPageChangeListener {

	private ViewPager viewPager;

	/**
	 * ͼƬ��Դid
	 */
	private int[] imgIdArray;

	private ImageView[] mImageViews;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView(R.layout.ui_intro);

	}

	public void initView(int viewId) {
		super.initView(viewId);
		/**
		 * �ֲ�ͼͼƬ
		 */
		 imgIdArray = new int[] { R.drawable.pic_1,
		 R.drawable.pic_2, R.drawable.pic_3};

		// ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);
		viewPager = (ViewPager) findViewById(R.id.viewPager);

		// // �������뵽ViewGroup��
		// tips = new ImageView[imgIdArray.length];
		// for (int i = 0; i < tips.length; i++) {
		// ImageView imageView = new ImageView(this);
		// imageView.setLayoutParams(new LayoutParams(10, 10));
		// tips[i] = imageView;
		// if (i == 0) {
		// tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
		// } else {
		// tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
		// }
		//
		// LinearLayout.LayoutParams layoutParams = new
		// LinearLayout.LayoutParams(
		// new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,
		// LayoutParams.WRAP_CONTENT));
		// layoutParams.leftMargin = 5;
		// layoutParams.rightMargin = 5;
		// group.addView(imageView, layoutParams);
		// }

		// ��ͼƬװ�ص�������
		mImageViews = new ImageView[imgIdArray.length];
		for (int i = 0; i < mImageViews.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setTag(i);
			imageView.setScaleType(ScaleType.FIT_XY);
			imageView.setOnClickListener(this);

			mImageViews[i] = imageView;
			imageView.setImageBitmap(BitmapFactory.decodeResource(
					getResources(), imgIdArray[i]));
		}

		// ����Adapter
		viewPager.setAdapter(new MyAdapter());
		// ���ü�������Ҫ�����õ��ı���
		viewPager.setOnPageChangeListener(this);
	}

	public class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return mImageViews.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
		}

		/**
		 * ����ͼƬ��ȥ���õ�ǰ��position ���� ͼƬ���鳤��ȡ�����ǹؼ�
		 */
		@Override
		public Object instantiateItem(View container, int position) {
			try {
				((ViewPager) container).addView(mImageViews[position
						% mImageViews.length], 0);

			} catch (Exception e) {
			}

			return mImageViews[position % mImageViews.length];
		}

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int arg0) {
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		Tools.Log("����¼�");
		int position = (Integer) v.getTag();
		if (position == mImageViews.length - 1) {// ������һҳ
			// �洢�汾��
			putSharedPreferenceValue("version", Tools.getAppVersion(this) + "");
			// �洢�Ѿ����й������ı�ʶ
			putSharedPreferenceValue("is_intro", "1");
			if (SharedPreferenceUtils.init(IntroActivity.this).getIsNewFirst().equals("true")) {
				SharedPreferenceUtils.init(IntroActivity.this).setIsNewFirst("false");
				startActivity(new Intent(this,FirstLoginActivity.class));
			}else{
				Intent intent = new Intent(this, MainActivity.class);
				startActivity(intent);
				finish();
			}
			
		} else {
			viewPager.setCurrentItem(position + 1);
		}
	}
}
