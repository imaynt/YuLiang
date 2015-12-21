package com.zykj.yuliang.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.zykj.yuliang.R;
import com.zykj.yuliang.fragment.IncomeDetailFragment;
import com.zykj.yuliang.view.MyCommonTitle;

public class IncomeDetailActivity extends FragmentActivity {
	private MyCommonTitle myCommonTitle;
	private RadioGroup tab_income;
	private IncomeDetailFragment allFragment, taskFragment, xuetuFragment,
			duihuanFragment;
	private int checkedId = R.id.rb_quanbu;// ��ǰFragment

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_income_detail);

		initView();
		requestData();
	}

	private void initView() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("��ϸ");
		tab_income = (RadioGroup) findViewById(R.id.tab_income);
		((RadioButton) findViewById(R.id.rb_quanbu)).setText("ȫ��");
		((RadioButton) findViewById(R.id.rb_renwu)).setText("����");
		((RadioButton) findViewById(R.id.rb_xuetu)).setText("ѧͽ");
		((RadioButton) findViewById(R.id.rb_duihuan)).setText("�һ�");

		allFragment = IncomeDetailFragment.getInstance(1);//ȫ��
		taskFragment = IncomeDetailFragment.getInstance(2);//����
		xuetuFragment = IncomeDetailFragment.getInstance(3);//ѧͽ
		duihuanFragment = IncomeDetailFragment.getInstance(4);//�һ�

		tab_income.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				IncomeDetailActivity.this.checkedId = checkedId;
				if (checkedId == R.id.rb_quanbu) {
					getSupportFragmentManager().beginTransaction()
							.show(allFragment).hide(taskFragment)
							.hide(xuetuFragment).hide(duihuanFragment).commit();
				} else if (checkedId == R.id.rb_renwu) {
					getSupportFragmentManager().beginTransaction()
							.show(taskFragment).hide(allFragment)
							.hide(xuetuFragment).hide(duihuanFragment).commit();
				} else if (checkedId == R.id.rb_xuetu) {
					getSupportFragmentManager().beginTransaction()
							.show(xuetuFragment).hide(taskFragment)
							.hide(allFragment).hide(duihuanFragment).commit();
				} else if (checkedId == R.id.rb_duihuan) {
					getSupportFragmentManager().beginTransaction()
							.show(duihuanFragment).hide(taskFragment)
							.hide(xuetuFragment).hide(allFragment).commit();
				}
			}
		});
	}

	/**
	 * ��������
	 */
	private void requestData() {
		getSupportFragmentManager().beginTransaction()
				.add(R.id.income_framelayout, allFragment)
				.add(R.id.income_framelayout, taskFragment)
				.add(R.id.income_framelayout, xuetuFragment)
				.add(R.id.income_framelayout, duihuanFragment)
				.show(allFragment).hide(taskFragment).hide(xuetuFragment)
				.hide(duihuanFragment).commit();
	}

}
