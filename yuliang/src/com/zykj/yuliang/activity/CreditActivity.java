package com.zykj.yuliang.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.R;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/** Version 1.0.1
 * @author duiba
 * 1������toolbar����titleΪ������ʾ��
 * 2������tile�����Ϊ200dp��
 * 3���޸�toolbar�߶�Ϊdip��λ��20dip��
 * 4���޸ķ���ͼ��Ϊ������У�margin-left=10dp��
 * 5�����dip2px()��λת��������
 */
/**
 * Version 1.0.2
 * @author duiba
 * 1���޸�δ��¼�û���¼��ص�ҳ�棬���˵�֮ǰ��ҳ��ʱ��ˢ��һ�Σ�ȥ��δ��¼״̬��
 * 2������ˢ�·����޸���
 * 3����ջ��Activity�����ΪCreditActivity��
 */
/**
 * Version 1.0.3
 * @author duiba fxt
 * 1����ӷ����ܣ�֧�ַ����ҳ��ĵ���������ʾ���������赽�Ұɹ����̨���ò�������
 * 2����Ӹ������ܵ�ע����Ϣ��
 * 3������ӿں��Զ���¼�ӿڸ�ΪAlertDialog��չʾ��ʽ��
 */
/**
 * Version 1.0.4
 * @author duiba fxt
 * ɾ��webview���ã� settings.setLoadWithOverviewMode(true); 
 * �������ÿ��ܵ���ҳ���޷������ҳ����������⡣
 */
/**
 * Version 1.0.5
 * 
 * @author duiba fxt
 *         ��onConsume�����м���ˢ�»��ֵ�js�������ҳ�溬��onDBNewOpenBack()����,����ø�js����(ˢ�»���)
 *         ����api�汾��4.4֮��ʹ��evaluateJavascript������
 */
public class CreditActivity extends Activity {
	private static String ua;
	private static Stack<CreditActivity> activityStack;
	public static final String VERSION = "1.0.5";
	public static CreditsListener creditsListener;

	public interface CreditsListener {
		/**
		 * ���������ť�����
		 * 
		 * @param shareUrl
		 *            ����ĵ�ַ
		 * @param shareThumbnail
		 *            ���������ͼ
		 * @param shareTitle
		 *            ����ı���
		 * @param shareSubtitle
		 *            ����ĸ�����
		 */
		public void onShareClick(WebView webView, String shareUrl,
				String shareThumbnail, String shareTitle, String shareSubtitle);

		/**
		 * �������¼
		 * 
		 * @param webView
		 *            ���ڵ�¼�ɹ��󷵻ص���ǰ��webview��ˢ�¡�
		 * @param currentUrl
		 *            ��ǰҳ���url
		 */
		public void onLoginClick(WebView webView, String currentUrl);
	}

	protected String url;

	protected String shareUrl; // �����url
	protected String shareThumbnail; // ���������ͼ
	protected String shareTitle; // ����ı���
	protected String shareSubtitle; // ����ĸ�����

	protected Boolean ifRefresh = false;
	protected Boolean delayRefresh = false;

	protected String navColor;
	protected String titleColor;
	protected Long shareColor;

	protected WebView mWebView;
	protected LinearLayout mLinearLayout;
	protected RelativeLayout mNavigationBar;
	protected TextView mTitle;
	protected ImageView mBackView;
	protected TextView mShare;

	private int RequestCode = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // ����������ʾ

		url = getIntent().getStringExtra("url");
		if (url == null) {
			throw new RuntimeException("url can't be blank");
		}

		if (activityStack == null) {
			activityStack = new Stack<CreditActivity>();
		}
		activityStack.push(this);

		// ���õ������ı���ɫ
		titleColor = getIntent().getStringExtra("titleColor");
		String titleColorTemp = "0xff"
				+ titleColor.substring(1, titleColor.length());
		Long titlel = Long.parseLong(titleColorTemp.substring(2), 16);
		// ���÷����İ���ɫ,ͬtaitle
		shareColor = titlel;
		// ���õ�����������ɫ
		navColor = getIntent().getStringExtra("navColor");
		String navColorTemp = "0xff" + navColor.substring(1, navColor.length());
		Long navl = Long.parseLong(navColorTemp.substring(2), 16);
		// ��ʼ��ҳ��
		initView();
		setContentView(mLinearLayout);
		// ����ϵͳĬ�ϵ�ActionBar
		ActionBar actionBar = getActionBar();
		if (actionBar != null) {
			actionBar.hide();
		}

		mTitle.setTextColor(titlel.intValue());
		mNavigationBar.setBackgroundColor(navl.intValue());
		// ��Ӻ��˰�ť�����¼�
		mBackView.setImageResource(R.drawable.common_title_back);
		mBackView.setPadding(20, 20, 20, 20);
		mBackView.setClickable(true);
		mBackView.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				onBackClick();
			}
		});
		// ��ӷ���ť�ļ����¼�
		if (mShare != null) {
			mShare.setOnClickListener(new OnClickListener() {
				public void onClick(View view) {
					creditsListener.onShareClick(mWebView, shareUrl,
							shareThumbnail, shareTitle, shareSubtitle);
				}
			});
		}

		if (ua == null) {
			ua = mWebView.getSettings().getUserAgentString() + " Duiba/"
					+ VERSION;
		}
		mWebView.getSettings().setUserAgentString(ua);

		mWebView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onReceivedTitle(WebView view, String title) {
				CreditActivity.this.onReceivedTitle(view, title);
			}

		});

		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				return shouldOverrideUrlByDuiba(view, url);
			}

			// ҳ����ؽ���ʱ��ȡҳ�������Ϣ���纬������Ϣ���򵼺�������ʾ����ť
			@Override
			public void onPageFinished(WebView view, String url) {
				view.loadUrl("javascript:if(document.getElementById('duiba-share-url')){duiba_app.shareInfo(document.getElementById(\"duiba-share-url\").getAttribute(\"content\"));}");
				super.onPageFinished(view, url);
			}
		});

		// js��java����ӿڡ�
		mWebView.addJavascriptInterface(new Object() {

			// ���ڻش�����url��title��
			@JavascriptInterface
			public void shareInfo(String content) {
				if (content != null) {
					String[] dd = content.split("\\|");
					if (dd.length == 4) {
						setShareInfo(dd[0], dd[1], dd[2], dd[3]);
						mShare.setVisibility(View.VISIBLE);
					}
				}
			}

			// ������ת�û���¼ҳ���¼���
			@JavascriptInterface
			public void login() {
				if (creditsListener != null) {
					mWebView.post(new Runnable() {
						@Override
						public void run() {
							creditsListener.onLoginClick(mWebView,
									mWebView.getUrl());
						}
					});
				}
			}
		}, "duiba_app");

		mWebView.loadUrl(url);
	}

	// ���÷�����Ϣ.........................
	protected void setShareInfo(String shareUrl, String shareThumbnail,
			String shareTitle, String shareSubtitle) {
		this.shareUrl = shareUrl;
		this.shareThumbnail = shareThumbnail;
		this.shareSubtitle = shareSubtitle;
		this.shareTitle = shareTitle;
	}

	protected void onBackClick() {
		Intent intent = new Intent();
		setResult(99, intent);
		finishActivity(this);
	}

	// ��ʼ��ҳ��....................................
	protected void initView() {
		mLinearLayout = new LinearLayout(this);
		mLinearLayout.setBackgroundColor(Color.GRAY);
		mLinearLayout.setLayoutParams(new ViewGroup.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		mLinearLayout.setOrientation(LinearLayout.VERTICAL);

		int height50dp = dip2px(this, 50);
		// �Զ��嵼����
		initNavigationBar();

		LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, height50dp);

		mLinearLayout.addView(mNavigationBar, mLayoutParams);
		// ��ʼ��WebView����
		initWebView();

		mLinearLayout.addView(mWebView);

	}

	// �Զ��嵼���������� ���˰�ť��ҳ����⣬����ť��Ĭ�����أ�.........................
	protected void initNavigationBar() {
		int dp200 = dip2px(this, 200);
		int dp50 = dip2px(this, 50);
		int dp20 = dip2px(this, 20);
		int dp10 = dip2px(this, 10);

		mNavigationBar = new RelativeLayout(this);
		mNavigationBar.setLayoutParams(new ViewGroup.LayoutParams(
				LayoutParams.FILL_PARENT, dp20));

		mTitle = new TextView(this);
		mTitle.setMaxWidth(dp200);
		mTitle.setLines(1);
		mTitle.setTextSize(20.0f);
		mNavigationBar.addView(mTitle);
		android.widget.RelativeLayout.LayoutParams lp = (android.widget.RelativeLayout.LayoutParams) mTitle
				.getLayoutParams();
		lp.addRule(RelativeLayout.CENTER_IN_PARENT);

		mBackView = new ImageView(this);
		RelativeLayout.LayoutParams mBackLayout = new RelativeLayout.LayoutParams(
				dp50, dp50);
		mBackLayout
				.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		mBackLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		mBackLayout.setMargins(dp10, 0, 0, 0);
		mNavigationBar.addView(mBackView);

		// �ڵ��������Ҳ���ӷ���ť���޷�����Ϣ��ҳ�����أ�
		mShare = new TextView(this);
		mShare.setLines(1);
		mShare.setTextSize(20.0f);
		mShare.setText("����");
		mShare.setPadding(0, 0, dp10, 0);
		mShare.setTextColor(shareColor.intValue());
		mNavigationBar.addView(mShare);
		android.widget.RelativeLayout.LayoutParams shareLp = (android.widget.RelativeLayout.LayoutParams) mShare
				.getLayoutParams();
		shareLp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		shareLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		// ����ΪĬ�ϲ���ʾ
		mShare.setVisibility(View.INVISIBLE);
	}

	// ��ʼ��WebView����......................................
	protected void initWebView() {
		mWebView = new WebView(this);
		mWebView.setLayoutParams(new ViewGroup.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

		WebSettings settings = mWebView.getSettings();

		// User settings
		settings.setJavaScriptEnabled(true); // ����webview֧��javascript
		settings.setLoadsImagesAutomatically(true); // ֧���Զ�����ͼƬ
		settings.setUseWideViewPort(true); // ����webview�Ƽ�ʹ�õĴ��ڣ�ʹhtml��������Ӧ��Ļ
		settings.setSaveFormData(true); // ����webview���������
		settings.setSavePassword(true); // ����webview��������
		settings.setDefaultZoom(ZoomDensity.MEDIUM); // �����е������ܶȣ�medium=160dpi
		settings.setSupportZoom(true); // ֧������

		CookieManager.getInstance().setAcceptCookie(true);

		if (Build.VERSION.SDK_INT > 8) {
			settings.setPluginState(PluginState.ON_DEMAND);
		}

		// Technical settings
		settings.setSupportMultipleWindows(true);
		mWebView.setLongClickable(true);
		mWebView.setScrollbarFadingEnabled(true);
		mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		mWebView.setDrawingCacheEnabled(true);

		settings.setAppCacheEnabled(true);
		settings.setDatabaseEnabled(true);
		settings.setDomStorageEnabled(true);
	}
//.........................
	protected void onReceivedTitle(WebView view, String title) {
		mTitle.setText(title);
	}

	/**
	 * ����url���󣬸���url��βִ����Ӧ�Ķ����� ��;��ģ��ԭ��Ӧ�����飬����ҳ����ʷջ������Ҫ��................................
	 * 
	 * @param view
	 * @param url
	 * @return
	 */
	protected boolean shouldOverrideUrlByDuiba(WebView view, String url) {
		if (this.url.equals(url)) {
			view.loadUrl(url);
			return true;
		}
		if (!url.startsWith("http://") && !url.startsWith("https://")) {
			return false;
		}
		if (url.contains("dbnewopen")) { // �¿�ҳ��
			Intent intent = new Intent();
			intent.setClass(CreditActivity.this, CreditActivity.this.getClass());
			intent.putExtra("navColor", navColor);
			intent.putExtra("titleColor", titleColor);
			url = url.replace("dbnewopen", "none");
			intent.putExtra("url", url);
			startActivityForResult(intent, RequestCode);
		} else if (url.contains("dbbackrefresh")) { // ���˲�ˢ��
			url = url.replace("dbbackrefresh", "none");
			Intent intent = new Intent();
			intent.putExtra("url", url);
			intent.putExtra("navColor", navColor);
			intent.putExtra("titleColor", titleColor);
			setResult(RequestCode, intent);
			finishActivity(this);
		} else if (url.contains("dbbackrootrefresh")) { // �ص������̳���ҳ��ˢ��
			url = url.replace("dbbackrootrefresh", "none");
			if (activityStack.size() == 1) {
				finishActivity(this);
			} else {
				activityStack.get(0).ifRefresh = true;
				finishUpActivity();
			}
		} else if (url.contains("dbbackroot")) { // �ص������̳���ҳ
			url = url.replace("dbbackroot", "none");
			if (activityStack.size() == 1) {
				finishActivity(this);
			} else {
				finishUpActivity();
			}
		} else if (url.contains("dbback")) { // ����
			url = url.replace("dbback", "none");
			finishActivity(this);
		} else {
			if (url.endsWith(".apk") || url.contains(".apk?")) { // ֧��Ӧ����������
				Uri uri = Uri.parse(url);
				Intent viewIntent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(viewIntent);
				return true;
			}
			if (url.contains("autologin") && activityStack.size() > 0) { // δ��¼�û���¼�󷵻أ�������ʷҳ����Ϊ��ˢ��
				// �������ѿ�Activity����ΪonResumeʱˢ��ҳ�档
				setAllActivityDelayRefresh();
			}
			view.loadUrl(url);
		}
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		if (resultCode == 100) {
			if (intent.getStringExtra("url") != null) {
				this.url = intent.getStringExtra("url");
				mWebView.loadUrl(this.url);
				ifRefresh = false;
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (ifRefresh) {
			this.url = getIntent().getStringExtra("url");
			mWebView.loadUrl(this.url);
			ifRefresh = false;
		} else if (delayRefresh) {
			mWebView.reload();
			delayRefresh = false;
		} else {
			// ����ҳ��ʱ�����ҳ�溬��onDBNewOpenBack()����,����ø�js������
			mWebView.loadUrl("javascript:if(window.onDBNewOpenBack){onDBNewOpenBack()}");
			// if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			// mWebView.evaluateJavascript("if(window.onDBNewOpenBack){onDBNewOpenBack()}",
			// new ValueCallback<String>() {
			// @Override
			// public void onReceiveValue(String value) {
			// Log.e("credits", "ˢ�»���");
			// }
			// });
			// } else {
			// mWebView.loadUrl("javascript:if(window.onDBNewOpenBack){onDBNewOpenBack()}");
			// }
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			onBackClick();
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	// --------------------------------------------����Ϊ���߷���----------------------------------------------

	/**
	 * ����������ײ�һ�����������Activity....................
	 */
	public void finishUpActivity() {
		int size = activityStack.size();
		for (int i = 0; i < size - 1; i++) {
			activityStack.pop().finish();
		}
	}

	/**
	 * ����ָ����Activity............................
	 */
	public void finishActivity(Activity activity) {
		if (activity != null) {
			activityStack.remove(activity);
			activity.finish();
		}
	}

	/**
	 * ����ջ������ActivityΪ���ش�ˢ�¡� ���ã�δ��¼�û������¼�󣬽�����ջ�ڵ�Activity����ΪonResumeʱˢ��ҳ�档..................
	 */
	public void setAllActivityDelayRefresh() {
		int size = activityStack.size();
		for (int i = 0; i < size; i++) {
			if (activityStack.get(i) != this) {
				activityStack.get(i).delayRefresh = true;
			}
		}
	}

	/**
	 * �����ֻ��ķֱ��ʴ� dp �ĵ�λ ת��Ϊ px(����)..........................
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * ��ѯ�ֻ��ڷ�ϵͳӦ��
	 * 
	 * @param context
	 * @return
	 */
	public List<PackageInfo> getAllApps(Context context) {
		List<PackageInfo> apps = new ArrayList<PackageInfo>();
		PackageManager pManager = context.getPackageManager();
		// ��ȡ�ֻ�������Ӧ��
		List<PackageInfo> paklist = pManager.getInstalledPackages(0);
		for (int i = 0; i < paklist.size(); i++) {
			PackageInfo pak = (PackageInfo) paklist.get(i);
			// �ж��Ƿ�Ϊ��ϵͳԤװ��Ӧ�ó���
			if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM) <= 0) {
				// customs applications
				apps.add(pak);
			}
		}
		return apps;
	}

	/**
	 * ��ȡ�û����һ�εĵ���λ�ã���γ�ȡ�
	 * 
	 * @param context
	 * @return
	 */
	public static String getLocation(Context context) {
		android.location.Location location = null;

		String provider = null;
		double latitude = 0;
		double longitude = 0;
		double accuracy = 0;

		String userLocation = null;

		LocationManager lManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);

		if (lManager == null) {
			Log.e("location", "LocationManager is null");
			return null;
		}

		android.location.Location aLocation = null;

		// ������gps
		if (lManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			provider = LocationManager.GPS_PROVIDER;
			aLocation = lManager
					.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if (aLocation != null) {
				latitude = aLocation.getLatitude();
				longitude = aLocation.getLongitude();
				accuracy = aLocation.getAccuracy();
				userLocation = "location: latitude=" + latitude + ";longitude="
						+ longitude + ";accuracy=" + accuracy;
				return userLocation;
			}
		}

		if (lManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			provider = LocationManager.NETWORK_PROVIDER;
			aLocation = lManager
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		}

		// ���net��ȡ��λ�ã��򷵻�
		if (aLocation != null) {
			latitude = aLocation.getLatitude();
			longitude = aLocation.getLongitude();
			accuracy = aLocation.getAccuracy();
			userLocation = "location: latitude=" + latitude + ";longitude="
					+ longitude + ";accuracy=" + accuracy;
			return userLocation;
		}
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		// �����ж��Ƿ�cmda��λ
		if (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA) {
			CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) telephonyManager
					.getCellLocation();
			if (cdmaCellLocation != null) {
				provider = "cdma";
				latitude = (double) cdmaCellLocation.getBaseStationLatitude() / 14400;
				longitude = (double) cdmaCellLocation.getBaseStationLongitude() / 14400;
				userLocation = "location: latitude=" + latitude + ";longitude="
						+ longitude + ";accuracy=" + accuracy;
				return userLocation;
			}
		}

		return null;
	}
}
