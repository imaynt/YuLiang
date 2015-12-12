package com.zykj.yuliang.activity;

import net.youmi.android.AdManager;
import net.youmi.android.offers.OffersManager;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.dc.wall.DianCai;
import cn.dow.android.DOW;
import cn.waps.AppConnect;

import com.yql.dr.sdk.DRSdk;
import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.R;
import com.zykj.yuliang.view.MyCommonTitle;
import com.zykjyulia.DevInit;

public class LianMengActivity extends BaseActivity {

	private MyCommonTitle myCommonTitle;
	private RelativeLayout ll_yiyuan;// 一元夺宝
	private RelativeLayout ll_duomeng;// 多盟
	private RelativeLayout ll_dianle;// 点乐
	private RelativeLayout ll_youmi;// 有米
	private RelativeLayout ll_dianru;// 点入
	private RelativeLayout ll_wanpu;// 万普
	private RelativeLayout ll_diancai;// 点财

	private static String DIANJOY_APP_ID = "918b87f4980776e44fa8158f04fbddf1";// 点乐的APPID
	private static String DIANCAIAPPID = "13340";// 点财的APPID
	private static String DIANCAIAPPKEY = "98c64e5a965946da889707d54a45f2c7";// 点财的APPKEY
	private static String YOUMIAPPID = "1cfa6a7dac554134";// 有米APPID
	private static String YOUMIAPPSECRET = "df6e12bf7f49b837";// 有米APPSECRET
	private static String WAPAPPID = "87f24e0526b1de3c7775c9ac1b93ca4e";// 万普APPID

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lianmeng);

		initViews();
	}

	protected void initViews() {

		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("联盟任务");

		ll_yiyuan = (RelativeLayout) findViewById(R.id.ll_yiyuan);// 一元夺宝
		ll_duomeng = (RelativeLayout) findViewById(R.id.ll_duomeng);// 多盟
		ll_dianle = (RelativeLayout) findViewById(R.id.ll_dianle);// 点乐
		ll_youmi = (RelativeLayout) findViewById(R.id.ll_youmi);// 有米
		ll_dianru = (RelativeLayout) findViewById(R.id.ll_dianru);// 点入
		ll_wanpu = (RelativeLayout) findViewById(R.id.ll_wanpu);// 万普
		ll_diancai = (RelativeLayout) findViewById(R.id.ll_diancai);// 点财

		setListener(ll_yiyuan, ll_duomeng, ll_dianle, ll_youmi, ll_dianru,
				ll_wanpu, ll_diancai);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.ll_yiyuan:// 一元夺宝

			break;
		case R.id.ll_duomeng:// 多盟
			// openOfferWall();
			DOW.getInstance(this).init();// 初始化积分墙
			DOW.getInstance(this).show(this);
			break;
		case R.id.ll_dianle:// 点乐
			DevInit.initGoogleContext(this, DIANJOY_APP_ID);
			DevInit.setCurrentUserID(this, "123456789");// 此处待修改,为设备ID................................
			DevInit.showOffers(this);
			break;
		case R.id.ll_youmi:// 有米
			/**
			 * appId 和 appSecret 分别为应用的发布 ID 和密钥，由有米后台自动生成，通过在有米后台 > 应用详细信息
			 * 可以获得。 isTestModel : 是否开启测试模式，true
			 * 为是，false为否。==============================================
			 * （上传有米审核及发布到市场版本，请设置为 false）
			 */
			// AdManager.getInstance(Context context).init(String appId, String
			// appSecret, boolean isTestModel);
			AdManager.getInstance(this).init(YOUMIAPPID, YOUMIAPPSECRET, true);
			OffersManager.getInstance(this).onAppLaunch();// 使用积分墙功能之前进行初始化
			OffersManager.getInstance(this).showOffersWall();

			break;
		case R.id.ll_dianru:// 点入
			/**
			 * 如果是 Activity,在第一个启动的 Acitivty 里面的 onCreate 的开始写上下面的代码 context
			 * :上下文 isLoc :是否开启定位 appuserid:用户 id 一般游戏的角色 id ，如果没有 id 的话传””
			 */
			DRSdk.initialize(this, false, "");// 初始化
			/**
			 * DRSdk.showOfferWall (context, SDK显示类型); DRSdk.DR_FREE 免费墙 DRSdk.
			 * DR_OFFER 积分墙
			 */
			DRSdk.showOfferWall(this, DRSdk.DR_OFFER);
			break;
		case R.id.ll_wanpu:// 万普
			/**
			 * APP_ID 为应用标识，该值由万普后台添加应用后自劢生成，点击“应用详情”获取； APP _PID
			 * 为分发渠道标识，使用规则请参见本文档附表《常用渠道编码表》。hiapk 安卓市场
			 */
			//AppConnect.getInstance("APP_ID","APP_PID",this);
			AppConnect.getInstance(WAPAPPID, "360", this);
			AppConnect.getInstance(this).showOffers(this);
			break;
		case R.id.ll_diancai:// 点财
			DianCai.initApp(LianMengActivity.this, DIANCAIAPPID, DIANCAIAPPKEY);
			DianCai.showOfferWall();// 展示有虚拟金币的推荐墙
			// DianCai.showGoodApps();//展示无虚拟金币的推荐墙
			break;
		default:
			break;
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		/**
		 * 在应用退出的地方（如：Activity的 onDestroy 方法中）调用以下代码进行资源回收：
		 */
		OffersManager.getInstance(this).onAppExit();
		// 万普退出
		AppConnect.getInstance(this).close();
	}

	/**
	 * 打开积分墙
	 */
	private void openOfferWall() {
		DOW.getInstance(this).show(this);
	}

}
