package com.zykj.yuliang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.R;
import com.zykj.yuliang.view.MyCommonTitle;

public class CommonQuestionActivity extends BaseActivity{
	
	private MyCommonTitle myCommonTitle;
	private TextView tv_content_0,tv_content_1,tv_content_2,tv_content_3,tv_content_4,tv_content_5,tv_content_6,tv_content_7,tv_content_8,tv_content_9,tv_content_10,tv_content_11,tv_content_12;
	private String []text_all={"联系我们","账户解冻、积分未到账请至客服中心自助提交处理，其他问题请联系余粮官方微信客服。\n工作时间：每天9:00 ~ 18:00（节假日不休息）\n关注余粮官方微信服务号(唯一方式)：余粮","1、长按以下二维码，点“存储图像”，将二维码保存到手机相册；\n2、打开微信，找到“扫一扫”，点击“相册”；\n3、选择二维码，手机会自动识别；\n4、关注余粮服务号完成！","作弊行为的判定","1、付费任务，如使用共享ID获得积分，“工作室”采用大量手机，流水作业都将视为作弊\n2、同一台设备，无论以什么方式刻意地重复做任务，刷机做任务，刷徒弟做任务都视为作弊\n3 、所有的兑换行为，均由纯人工审核，非常严格。请同学们切勿因小失大，上述行为一旦发现，立即冻结，且将永不在解封。请各位玩粮知晓。\n请大家珍惜自己的唯一ID，您的每一位真实徒弟所产生的收入，我们将额外给您最高20%的奖励，并终身受用，我们每天兑换出去的奖金超过数十万，绝不会对真正的用户和您辛苦招来的徒弟进行恶意冻结。","不给积分问题","首先我们深表歉意，目前除了限时推荐之外，联盟任务无法实现100%返还，您可以选择我们的智能列表，里面是根据实际用户的返还率来进行排列的。这样可以最大限度的保证到您的收益。我们正在研发更多好玩有趣的功能。定能给你们更多的惊喜","为什么能赚钱","用户完成广告联盟商指定的任务，即完成了广告推广行为，获得相应的报酬，此奖励为劳动所得，由广告联盟商支付相应报酬。","没有获得积分怎么办","请检查是否按要求完成了任务，如：\n1、必须首次安装，以前安装过，删除再安无效\n2、必须完成任务的要求，比如注册、体验等\n3、不同联盟商里的相同任务，只能做一次，重复无效\n如果确认没问题，请向我们投诉该广告联盟商，我们将定期更换可信的广告联盟商。","如何做到月入数千","坚持以下两个步骤：\n1、任务每天都会变化，每天先把每日任务做了，然后去联盟任务区优先选择高收益的任务，所有任务都是限时限量，下午任务最多，所以要经常看看。\n2、通过QQ、微信、论坛等推广自己的ID，成为师傅之后，永久享受徒弟的收入奖励，徒弟越多越好"};
	private TextView []tv_all={tv_content_0,tv_content_1,tv_content_2,tv_content_3,tv_content_4,tv_content_5,tv_content_6,tv_content_7,tv_content_8,tv_content_9,tv_content_10,tv_content_11,tv_content_12};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_common_question);
		initViews();
		setContent();

	}
	
	private void setContent(){
		for (int i = 0; i < tv_all.length; i++) {
			tv_all[i].setText(text_all[i]);
		}
	}

	private void initViews() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("常见问题");
	
		tv_content_0=(TextView)findViewById(R.id.tv_content_0);
		tv_all[0]=tv_content_0;
		tv_content_1=(TextView)findViewById(R.id.tv_content_1);
		tv_all[1]=tv_content_1;
		tv_content_2=(TextView)findViewById(R.id.tv_content_2);
		tv_all[2]=tv_content_2;
		tv_content_3=(TextView)findViewById(R.id.tv_content_3);
		tv_all[3]=tv_content_3;
		tv_content_4=(TextView)findViewById(R.id.tv_content_4);
		tv_all[4]=tv_content_4;
		tv_content_5=(TextView)findViewById(R.id.tv_content_5);
		tv_all[5]=tv_content_5;
		tv_content_6=(TextView)findViewById(R.id.tv_content_6);
		tv_all[6]=tv_content_6;
		tv_content_7=(TextView)findViewById(R.id.tv_content_7);
		tv_all[7]=tv_content_7;
		tv_content_8=(TextView)findViewById(R.id.tv_content_8);
		tv_all[8]=tv_content_8;
		tv_content_9=(TextView)findViewById(R.id.tv_content_9);
		tv_all[9]=tv_content_9;
		tv_content_10=(TextView)findViewById(R.id.tv_content_10);
		tv_all[10]=tv_content_10;
		tv_content_11=(TextView)findViewById(R.id.tv_content_11);
		tv_all[11]=tv_content_11;
		tv_content_12=(TextView)findViewById(R.id.tv_content_12);
		tv_all[12]=tv_content_12;
		
		
		
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);
	
	}
}
