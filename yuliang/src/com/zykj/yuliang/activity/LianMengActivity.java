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
	private RelativeLayout ll_yiyuan;// һԪ�ᱦ
	private RelativeLayout ll_duomeng;// ����
	private RelativeLayout ll_dianle;// ����
	private RelativeLayout ll_youmi;// ����
	private RelativeLayout ll_dianru;// ����
	private RelativeLayout ll_wanpu;// ����
	private RelativeLayout ll_diancai;// ���

	private static String DIANJOY_APP_ID = "918b87f4980776e44fa8158f04fbddf1";// ���ֵ�APPID
	private static String DIANCAIAPPID = "13340";// ��Ƶ�APPID
	private static String DIANCAIAPPKEY = "98c64e5a965946da889707d54a45f2c7";// ��Ƶ�APPKEY
	private static String YOUMIAPPID = "1cfa6a7dac554134";// ����APPID
	private static String YOUMIAPPSECRET = "df6e12bf7f49b837";// ����APPSECRET
	private static String WAPAPPID = "87f24e0526b1de3c7775c9ac1b93ca4e";// ����APPID

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lianmeng);

		initViews();
	}

	protected void initViews() {

		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("��������");

		ll_yiyuan = (RelativeLayout) findViewById(R.id.ll_yiyuan);// һԪ�ᱦ
		ll_duomeng = (RelativeLayout) findViewById(R.id.ll_duomeng);// ����
		ll_dianle = (RelativeLayout) findViewById(R.id.ll_dianle);// ����
		ll_youmi = (RelativeLayout) findViewById(R.id.ll_youmi);// ����
		ll_dianru = (RelativeLayout) findViewById(R.id.ll_dianru);// ����
		ll_wanpu = (RelativeLayout) findViewById(R.id.ll_wanpu);// ����
		ll_diancai = (RelativeLayout) findViewById(R.id.ll_diancai);// ���

		setListener(ll_yiyuan, ll_duomeng, ll_dianle, ll_youmi, ll_dianru,
				ll_wanpu, ll_diancai);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.ll_yiyuan:// һԪ�ᱦ

			break;
		case R.id.ll_duomeng:// ����
			// openOfferWall();
			DOW.getInstance(this).init();// ��ʼ������ǽ
			DOW.getInstance(this).show(this);
			break;
		case R.id.ll_dianle:// ����
			DevInit.initGoogleContext(this, DIANJOY_APP_ID);
			DevInit.setCurrentUserID(this, "123456789");// �˴����޸�,Ϊ�豸ID................................
			DevInit.showOffers(this);
			break;
		case R.id.ll_youmi:// ����
			/**
			 * appId �� appSecret �ֱ�ΪӦ�õķ��� ID ����Կ�������׺�̨�Զ����ɣ�ͨ�������׺�̨ > Ӧ����ϸ��Ϣ
			 * ���Ի�á� isTestModel : �Ƿ�������ģʽ��true
			 * Ϊ�ǣ�falseΪ��==============================================
			 * ���ϴ�������˼��������г��汾��������Ϊ false��
			 */
			// AdManager.getInstance(Context context).init(String appId, String
			// appSecret, boolean isTestModel);
			AdManager.getInstance(this).init(YOUMIAPPID, YOUMIAPPSECRET, true);
			OffersManager.getInstance(this).onAppLaunch();// ʹ�û���ǽ����֮ǰ���г�ʼ��
			OffersManager.getInstance(this).showOffersWall();

			break;
		case R.id.ll_dianru:// ����
			/**
			 * ����� Activity,�ڵ�һ�������� Acitivty ����� onCreate �Ŀ�ʼд������Ĵ��� context
			 * :������ isLoc :�Ƿ�����λ appuserid:�û� id һ����Ϸ�Ľ�ɫ id �����û�� id �Ļ�������
			 */
			DRSdk.initialize(this, false, "");// ��ʼ��
			/**
			 * DRSdk.showOfferWall (context, SDK��ʾ����); DRSdk.DR_FREE ���ǽ DRSdk.
			 * DR_OFFER ����ǽ
			 */
			DRSdk.showOfferWall(this, DRSdk.DR_OFFER);
			break;
		case R.id.ll_wanpu:// ����
			/**
			 * APP_ID ΪӦ�ñ�ʶ����ֵ�����պ�̨���Ӧ�ú���۽���ɣ������Ӧ�����顱��ȡ�� APP _PID
			 * Ϊ�ַ�������ʶ��ʹ�ù�����μ����ĵ��������������������hiapk ��׿�г�
			 */
			//AppConnect.getInstance("APP_ID","APP_PID",this);
			AppConnect.getInstance(WAPAPPID, "360", this);
			AppConnect.getInstance(this).showOffers(this);
			break;
		case R.id.ll_diancai:// ���
			DianCai.initApp(LianMengActivity.this, DIANCAIAPPID, DIANCAIAPPKEY);
			DianCai.showOfferWall();// չʾ�������ҵ��Ƽ�ǽ
			// DianCai.showGoodApps();//չʾ�������ҵ��Ƽ�ǽ
			break;
		default:
			break;
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		/**
		 * ��Ӧ���˳��ĵط����磺Activity�� onDestroy �����У��������´��������Դ���գ�
		 */
		OffersManager.getInstance(this).onAppExit();
		// �����˳�
		AppConnect.getInstance(this).close();
	}

	/**
	 * �򿪻���ǽ
	 */
	private void openOfferWall() {
		DOW.getInstance(this).show(this);
	}

}
