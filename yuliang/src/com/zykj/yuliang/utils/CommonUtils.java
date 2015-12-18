package com.zykj.yuliang.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zykj.yuliang.BaseApp;
import com.zykj.yuliang.R;
import com.zykj.yuliang.adapter.NumericWheelAdapter;
import com.zykj.yuliang.adapter.OnWheelChangedListener;
import com.zykj.yuliang.view.WheelView;

public class CommonUtils {

	private static final double EARTH_RADIUS = 6378137;//����뾶
	/**
	* ��̬����ListView�ĸ߶�
	* @param listView
	*/
	public static void setListViewHeightBasedOnChildren(ListView listView) { 
	    if(listView == null) return;

	    ListAdapter listAdapter = listView.getAdapter(); 
	    if (listAdapter == null) { 
	        // pre-condition 
	        return; 
	    } 

	    int totalHeight = 0; 
	    for (int i = 0; i < listAdapter.getCount(); i++) { 
	        View listItem = listAdapter.getView(i, null, listView); 
	        listItem.measure(0, 0); 
	        totalHeight += listItem.getMeasuredHeight(); 
	    } 

	    ViewGroup.LayoutParams params = listView.getLayoutParams(); 
	    params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1)); 
	    listView.setLayoutParams(params); 
	}
	
	private static double rad(double d){
		return d * Math.PI / 180.0;
	}
	
	/**
	 * ����������γ�ȼ������
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	public static double GetDistance(double lat1, double lng1, double lat2, double lng2){
	   double radLat1 = rad(lat1);
	   double radLat2 = rad(lat2);
	   double a = radLat1 - radLat2;
	   double b = rad(lng1) - rad(lng2);

	   double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
	   Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
	   s = s * EARTH_RADIUS;
	   s = Math.round(s * 10000) / 10000;
	   return s;
	}
	
	public static boolean CheckLogin(){
		return !StringUtil.isEmpty(BaseApp.getModel().getUserid());
	}
	
	public static void showPic(String name,ImageView imageview){
		if(!StringUtil.isEmpty(name)){
			ImageLoader.getInstance().displayImage(name, imageview);
		}
	}
	
	//�˳�����
	public static void exitkey(int keyCode, Context context) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {// ���ذ�ť
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setTitle("��ʾ").setMessage("��ȷ���˳���ǰӦ��").setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			}).setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					try {
						// �ж��Ƿ������ʱ�������ļ�
						File temp_file = new File(Environment.getExternalStorageDirectory() + File.separator + "heyi_dir");
						Tools.Log("�ļ��Ƿ���ڣ�" + temp_file.exists());
						if (temp_file.exists()) {
							File[] file_detail = temp_file.listFiles();
							for (File file_del : file_detail) {
								file_del.delete();
							}
							temp_file.delete();
						}
					} catch (Exception e) {
					}
					System.exit(0);
				}
			}).setOnCancelListener(new DialogInterface.OnCancelListener() {
				public void onCancel(DialogInterface dialog) {
					dialog.dismiss();
				}

			}).show();
		}
	}
	

	public static void showShare(final Context context, String title, String content, String url) {

		ShareSDK.initSDK(context);
		 OnekeyShare oks = new OnekeyShare();
		 //�ر�sso��Ȩ
		 oks.disableSSOWhenAuthorize(); 
			// ����ʱNotification��ͼ�������  2.5.9�Ժ�İ汾�����ô˷���
			oks.setCallback(new PlatformActionListener() {
				@Override
				public void onError(Platform arg0, int arg1, Throwable arg2) {
					Tools.toast(context, "����ʧ��");
				}
				@Override
				public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
					Tools.toast(context, "����ɹ�");
				}
				@Override
				public void onCancel(Platform arg0, int arg1) {
					Tools.toast(context, "ȡ������");
				}
			});
		 // title���⣬ӡ��ʼǡ����䡢��Ϣ��΢�š���������QQ�ռ�ʹ��
		 oks.setTitle(title);
		 // titleUrl�Ǳ�����������ӣ�������������QQ�ռ�ʹ��
		 oks.setTitleUrl(url);
		 // text�Ƿ����ı�������ƽ̨����Ҫ����ֶ�
		 oks.setText(content);
		 // imagePath��ͼƬ�ı���·����Linked-In�����ƽ̨��֧�ִ˲����ǵ��޸�Ŷ        
		 //oks.setImagePathΪsdkͼƬ·��
		 oks.setImageUrl("http://dashboard.mob.com/Uploads/db95c30283c2aa827e6831170d70808d.png");//ȷ��SDcard������ڴ���ͼƬ
		 // url����΢�ţ��������Ѻ�����Ȧ����ʹ��
		 oks.setUrl(url);
		 // comment���Ҷ�������������ۣ�������������QQ�ռ�ʹ��
		 oks.setComment("content");
		 // site�Ƿ�������ݵ���վ���ƣ�����QQ�ռ�ʹ��
		 oks.setSite(title);
		 // siteUrl�Ƿ�������ݵ���վ��ַ������QQ�ռ�ʹ��
		 oks.setSiteUrl(url);
		// ��������GUI
		 oks.show(context);
		 
//		 OnekeyShare oks = new OnekeyShare();
//		 //�ر�sso��Ȩ
//		 oks.disableSSOWhenAuthorize(); 
//		// ����ʱNotification��ͼ�������  2.5.9�Ժ�İ汾�����ô˷���
//		 //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
//		 // title���⣬ӡ��ʼǡ����䡢��Ϣ��΢�š���������QQ�ռ�ʹ��
//		 oks.setTitle(getString(R.string.share));
//		 // titleUrl�Ǳ�����������ӣ�������������QQ�ռ�ʹ��
//		 oks.setTitleUrl("http://sharesdk.cn");
//		 // text�Ƿ����ı�������ƽ̨����Ҫ����ֶ�
//		 oks.setText("���Ƿ����ı�");
//		 // imagePath��ͼƬ�ı���·����Linked-In�����ƽ̨��֧�ִ˲���
//		 oks.setImagePath("/sdcard/test.jpg");//ȷ��SDcard������ڴ���ͼƬ
//		 // url����΢�ţ��������Ѻ�����Ȧ����ʹ��
//		 oks.setUrl("http://sharesdk.cn");
//		 // comment���Ҷ�������������ۣ�������������QQ�ռ�ʹ��
//		 oks.setComment("���ǲ��������ı�");
//		 // site�Ƿ�������ݵ���վ���ƣ�����QQ�ռ�ʹ��
//		 oks.setSite(getString(R.string.app_name));
//		 // siteUrl�Ƿ�������ݵ���վ��ַ������QQ�ռ�ʹ��
//		 oks.setSiteUrl("http://sharesdk.cn");
//		// ��������GUI
//		 oks.show(this);
	}
	
	/**
	 * @param context
	 * @param tv_time
	 * ����ʱ��ؼ�,��ʽ:yyyy-MM-dd HH:mm
	 */
	public static void showDateTimePicker(Context context, final TextView tv_time){
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);

		// ��Ӵ�С���·ݲ�����ת��Ϊlist,����֮����ж�
		String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
		String[] months_little = { "4", "6", "9", "11" };

		final List<String> list_big = Arrays.asList(months_big);
		final List<String> list_little = Arrays.asList(months_little);

		final Dialog dialog = new Dialog(context);
		dialog.setTitle("��ѡ��������ʱ��");
		// �ҵ�dialog�Ĳ����ļ�
		LayoutInflater inflater = (LayoutInflater)context.getSystemService("layout_inflater");
		View view = inflater.inflate(R.layout.ui_time_layout, null);

		// ��
		final WheelView wv_year = (WheelView) view.findViewById(R.id.year);
		wv_year.setAdapter(new NumericWheelAdapter(1900, 2100));// ����"��"����ʾ����
		wv_year.setCyclic(true);// ��ѭ������
		wv_year.setLabel("��");// �������
		wv_year.setCurrentItem(year - 1900);// ��ʼ��ʱ��ʾ������

		// ��
		final WheelView wv_month = (WheelView) view.findViewById(R.id.month);
		wv_month.setAdapter(new NumericWheelAdapter(1, 12));
		wv_month.setCyclic(true);
		wv_month.setLabel("��");
		wv_month.setCurrentItem(month);

		// ��
		final WheelView wv_day = (WheelView) view.findViewById(R.id.day);
		wv_day.setCyclic(true);
		// �жϴ�С�¼��Ƿ�����,����ȷ��"��"������
		if (list_big.contains(String.valueOf(month + 1))) {
			wv_day.setAdapter(new NumericWheelAdapter(1, 31));
		} else if (list_little.contains(String.valueOf(month + 1))) {
			wv_day.setAdapter(new NumericWheelAdapter(1, 30));
		} else {
			// ����
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
				wv_day.setAdapter(new NumericWheelAdapter(1, 29));
			else
				wv_day.setAdapter(new NumericWheelAdapter(1, 28));
		}
		wv_day.setLabel("��");
		wv_day.setCurrentItem(day - 1);

//		// ʱ
//		final WheelView wv_hours = (WheelView) view.findViewById(R.id.hour);
//		wv_hours.setAdapter(new NumericWheelAdapter(0, 23));
//		wv_hours.setCyclic(true);
//		wv_hours.setCurrentItem(hour);
//
//		// ��
//		final WheelView wv_mins = (WheelView) view.findViewById(R.id.mins);
//		wv_mins.setAdapter(new NumericWheelAdapter(0, 59, "%02d"));
//		wv_mins.setCyclic(true);
//		wv_mins.setCurrentItem(minute);

		// ���"��"����
		OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int year_num = newValue + 1900;
				// �жϴ�С�¼��Ƿ�����,����ȷ��"��"������
				if (list_big.contains(String
						.valueOf(wv_month.getCurrentItem() + 1))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
				} else if (list_little.contains(String.valueOf(wv_month
						.getCurrentItem() + 1))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
				} else {
					if ((year_num % 4 == 0 && year_num % 100 != 0)
							|| year_num % 400 == 0)
						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
					else
						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
				}
			}
		};
		// ���"��"����
		OnWheelChangedListener wheelListener_month = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int month_num = newValue + 1;
				// �жϴ�С�¼��Ƿ�����,����ȷ��"��"������
				if (list_big.contains(String.valueOf(month_num))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
				} else if (list_little.contains(String.valueOf(month_num))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
				} else {
					if (((wv_year.getCurrentItem() + 1900) % 4 == 0 && (wv_year
							.getCurrentItem() + 1900) % 100 != 0)
							|| (wv_year.getCurrentItem() + 1900) % 400 == 0)
						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
					else
						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
				}
			}
		};
		wv_year.addChangingListener(wheelListener_year);
		wv_month.addChangingListener(wheelListener_month);

		// ������Ļ�ܶ���ָ��ѡ��������Ĵ�С
		int textSize = 0;

		textSize = 28;

		wv_day.TEXT_SIZE = textSize;
//		wv_hours.TEXT_SIZE = textSize;
//		wv_mins.TEXT_SIZE = textSize;
		wv_month.TEXT_SIZE = textSize;
		wv_year.TEXT_SIZE = textSize;

		Button btn_sure = (Button) view.findViewById(R.id.btn_datetime_sure);
		Button btn_cancel = (Button) view
				.findViewById(R.id.btn_datetime_cancel);
		// ȷ��
		btn_sure.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// ����Ǹ���,����ʾΪ"02"����ʽ
				String parten = "00";
				DecimalFormat decimal = new DecimalFormat(parten);
				// �������ڵ���ʾ
				 tv_time.setText((wv_year.getCurrentItem() + 1900) + "-"
				 + decimal.format((wv_month.getCurrentItem() + 1)) + "-"
				 + decimal.format((wv_day.getCurrentItem() + 1))) ;
//				 + " "
//				 + decimal.format(wv_hours.getCurrentItem()) + ":"
//				 + decimal.format(wv_mins.getCurrentItem()));

				dialog.dismiss();
			}
		});
		// ȡ��
		btn_cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dialog.dismiss();
			}
		});
		// ����dialog�Ĳ���,����ʾ
		dialog.setContentView(view);
		dialog.show();
	}
}
