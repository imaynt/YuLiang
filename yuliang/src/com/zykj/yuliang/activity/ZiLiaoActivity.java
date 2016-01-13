package com.zykj.yuliang.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.http.Header;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.BaseApp;
import com.zykj.yuliang.R;
import com.zykj.yuliang.http.AbstractHttpHandler;
import com.zykj.yuliang.http.HttpErrorHandler;
import com.zykj.yuliang.http.HttpUtils;
import com.zykj.yuliang.http.UrlContants;
import com.zykj.yuliang.utils.CommonUtils;
import com.zykj.yuliang.utils.StringUtil;
import com.zykj.yuliang.utils.Tools;
import com.zykj.yuliang.view.MyCommonTitle;
import com.zykj.yuliang.view.PickDialog;
import com.zykj.yuliang.view.RoundImageView;
import com.zykj.yuliang.view.UIDialog;
import com.zykj.yuliang.view.PickDialog.PickDialogListener;

public class ZiLiaoActivity extends BaseActivity {


	private MyCommonTitle myCommonTitle;
	private LinearLayout ll_nick, ll_avatar, ll_sex, ll_birth, ll_prefession,
	ll_weixin, ll_bind_mobile,ll_submit;
	private TextView tv_sex, tv_birthday, tv_profession, tv_mobile;
	private RoundImageView img_avatar;
	private EditText user_nick;
	private String timeString;// 上传头像字段
	private File file;
	private List<String> list;
	private Intent intent;
	private RequestParams params;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_geren);

		initViews();
		requstData();

	}

	protected void initViews() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("个人资料");

		ll_weixin = (LinearLayout) findViewById(R.id.ll_weixin_ziliao);
		ll_bind_mobile = (LinearLayout) findViewById(R.id.ll_bind_mobile_ziliao);

		user_nick = (EditText) findViewById(R.id.ed_user_nick);// 昵称
		img_avatar = (RoundImageView) findViewById(R.id.img_avatar);// 头像
		tv_sex = (TextView) findViewById(R.id.tv_sex);// 性别
		tv_birthday = (TextView) findViewById(R.id.tv_birthday);// 生日
		tv_profession = (TextView) findViewById(R.id.tv_profession);// 职业
		tv_mobile = (TextView) findViewById(R.id.tv_mobile);

		ll_avatar = (LinearLayout) findViewById(R.id.ll_avatar);// 头像
		ll_sex = (LinearLayout) findViewById(R.id.ll_sex);// 性别
		ll_birth = (LinearLayout) findViewById(R.id.ll_birthday);// 生日
		ll_prefession = (LinearLayout) findViewById(R.id.ll_profession);// 职业
		ll_weixin = (LinearLayout) findViewById(R.id.ll_weixin_ziliao);// 职业;
		ll_bind_mobile = (LinearLayout) findViewById(R.id.ll_bind_mobile_ziliao);// 职业;
		ll_submit = (LinearLayout) findViewById(R.id.ll_submit);// 提交

		setListener(ll_avatar, ll_sex, ll_birth, ll_prefession, ll_weixin,ll_bind_mobile,ll_submit);
	}

	private void requstData() {
		String nick = BaseApp.getModel().getUsername();
		user_nick.setText(StringUtil.isEmpty(nick) ? "" : nick);

		String avatar = BaseApp.getModel().getAvatar();
		ImageLoader.getInstance().displayImage(
				StringUtil.toString(UrlContants.IMAGE_URL+avatar, "http://"), img_avatar);

		String sex = BaseApp.getModel().getSex();
		tv_sex.setText(StringUtil.isEmpty(sex) ? "" : sex);

		String birth = BaseApp.getModel().getBirth();
		tv_birthday.setText(StringUtil.isEmpty(birth) ? "" : birth);

		String prefession = BaseApp.getModel().getPrefession();
		tv_profession.setText(StringUtil.isEmpty(prefession) ? "" : prefession);

		String mobile = BaseApp.getModel().getMobile();
		tv_mobile.setText(StringUtil.isEmpty(mobile) ? "" : mobile);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_weixin_ziliao:
			intent = new Intent(ZiLiaoActivity.this, WeiXinActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_bind_mobile_ziliao:
			intent = new Intent(ZiLiaoActivity.this, BindMobileActivity.class);
			intent.putExtra("mobile", tv_mobile.getText().toString().trim());
			startActivity(intent);
			break;

		case R.id.ll_avatar:
			UIDialog.ForThreeBtn(ZiLiaoActivity.this, new String[] { "拍照",
					"从相册中取", "取消" }, ZiLiaoActivity.this);
			break;
		case R.id.ll_sex:// 性别
			list = new ArrayList<String>();
			list.add("男");
			list.add("女");
			new PickDialog(ZiLiaoActivity.this, "请选择性别", list,
					new PickDialogListener() {

						@Override
						public void onListItemClick(int position, String string) {
							tv_sex.setText(list.get(position));
						}
					}).show();
			break;
		case R.id.ll_birthday:// 生日
			CommonUtils.showDateTimePicker(this, tv_birthday);
			// startView.findViewById(R.id.hour).setVisibility(View.GONE);
			// startView.findViewById(R.id.mins).setVisibility(View.GONE);
			break;
		case R.id.ll_profession:// 职业
			list = new ArrayList<String>();
			list.add("学生");
			list.add("教师");
			list.add("上班族");
			list.add("老板");
			list.add("公务员");
			list.add("自由职业者");
			list.add("退休");
			list.add("其他");
			new PickDialog(ZiLiaoActivity.this, "请选择职业", list,
					new PickDialogListener() {

						@Override
						public void onListItemClick(int position, String string) {
							tv_profession.setText(list.get(position));
						}
					}).show();
			break;
		case R.id.ll_submit:
			final String nick = user_nick.getText().toString().trim();
			final String sex = tv_sex.getText().toString().trim();
			final String birth = tv_birthday.getText().toString().trim();
			final String profession = tv_profession.getText().toString().trim();
			if (StringUtil.isEmpty(nick)) {
				Tools.toast(ZiLiaoActivity.this, "昵称不能为空");
				return;
			}
			if (StringUtil.isEmpty(sex)) {
				Tools.toast(ZiLiaoActivity.this, "性别不能为空");
				return;
			}
			if (StringUtil.isEmpty(birth)) {
				Tools.toast(ZiLiaoActivity.this, "生日不能为空");
				return;
			}
			if (StringUtil.isEmpty(profession)) {
				Tools.toast(ZiLiaoActivity.this, "职业不能为空");
				return;
			}if(file==null){
				Tools.toast(ZiLiaoActivity.this, "头像不能为空");
				return;
			}
			try {
				params = new RequestParams();
				params.put("deviceId", BaseApp.getModel().getDeviceId());// deviceId设备id
				params.put("username", nick);// username必须，新的会员昵称
				params.put("sex", sex);// sex必须, 性别
				params.put("birthday", birth);// birthday必须, 生日
				params.put("profession", profession);// profession必须, 职业
				params.put("id", BaseApp.getModel().getUserid());// birthday必须, 生日
				params.put("imgURL", file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}// birthday必须, 生日
				HttpUtils.updateUserInfo(new HttpErrorHandler() {

					@Override
					public void onRecevieSuccess(JSONObject json) {
						Tools.toast(ZiLiaoActivity.this, "资料更新成功!");
						String avatar=json.getJSONObject(UrlContants.jsonData).getString("avatar");
						BaseApp.getModel().setUsername(nick);
						BaseApp.getModel().setBirth(birth);
						BaseApp.getModel().setSex(sex);
						BaseApp.getModel().setPrefession(profession);
						BaseApp.getModel().setAvatar(avatar);
						setResult(RESULT_OK);
						finish();
					}
				}, params);
				
				getMoneyFromZiLiao();

			break;
		case R.id.dialog_modif_1:// 启动相机拍照
			/* 拍照 */
			UIDialog.closeDialog();
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"'IMG'_yyyyMMddHHmmss", new Locale("zh", "CN"));
			timeString = dateFormat.format(date);
			createSDCardDir();
			Intent shootIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			shootIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri
					.fromFile(new File(Environment
							.getExternalStorageDirectory() + "/DCIM/Camera",
							timeString + ".jpg")));
			startActivityForResult(shootIntent, 1);
			break;
		case R.id.dialog_modif_2:// 从相册中选取
			UIDialog.closeDialog();
			Intent photoIntent = new Intent(Intent.ACTION_PICK, null);
			photoIntent.setDataAndType(
					MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
			startActivityForResult(photoIntent, 2);
			break;
		case R.id.dialog_modif_3:// 取消
			UIDialog.closeDialog();
			break;
		default:
			break;
		}
	}

	
	/**
	 * 个人资料
	 */
	private void getMoneyFromZiLiao() {

		params = new RequestParams();
		params.put("deviceId", BaseApp.getModel().getDeviceId());// 设备ID
		params.put("part", "2");// 1或者2（1是新手教程，2是个人资料得分）
		HttpUtils.postNewAndPersonal(new AbstractHttpHandler() {

			@Override
			public void onJsonSuccess(JSONObject json) {
				if (json.getString("code").equals("200")) {// 个人资料已完成

				}
			}

			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {

			}
		}, params);
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 1:
			/* 如果是调用相机拍照，图片设置名字和路径 */
			File temp = new File(Environment.getExternalStorageDirectory()
					.getPath() + "/DCIM/Camera/" + timeString + ".jpg");
			startPhotoZoom(Uri.fromFile(temp));
			break;
		case 2:
			/* 如果是直接从相册获取 */
			try {
				startPhotoZoom(data.getData());
			} catch (Exception e) {
				Toast.makeText(this, "您没有选择任何照片", Toast.LENGTH_LONG).show();
			}
			break;
		case 3:
			/* 取得裁剪后的图片 */
			if (data != null) {
				setPicToView(data);
			}
			break;
		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void createSDCardDir() {
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			// 创建一个文件夹对象，赋值为外部存储器的目录
			File sdcardDir = Environment.getExternalStorageDirectory();
			// 得到一个路径，内容是sdcard的文件夹路径和名字
			String path = sdcardDir.getPath() + "/DCIM/Camera";
			File path1 = new File(path);
			if (!path1.exists()) {
				// 若不存在，创建目录，可以在应用启动的时候创建
				path1.mkdirs();

			}
		}
	}

	/**
	 * 裁剪图片方法实现
	 * 
	 * @param uri
	 */
	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 150);
		intent.putExtra("outputY", 150);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, 3);
	}

	/**
	 * 保存裁剪之后的图片数据
	 * 
	 * @param picdata
	 */
	private void setPicToView(Intent picdata) {
		Bundle extras = picdata.getExtras();
		if (extras != null) {
			Bitmap photo = extras.getParcelable("data");
			// Drawable drawable = new BitmapDrawable(photo);
			/* 下面注释的方法是将裁剪之后的图片以Base64Coder的字符方式上 传到服务器，QQ头像上传采用的方法跟这个类似 */
			savaBitmap(photo);
			// avatar_head_image.setBackgroundDrawable(drawable);
		}
	}

	/**
	 * 将剪切后的图片保存到本地图片上！
	 */
	public void savaBitmap(Bitmap bitmap) {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"'IMG'_yyyyMMddHHmmss", new Locale("zh", "CN"));
		String cutnameString = dateFormat.format(date);
		String filename = Environment.getExternalStorageDirectory().getPath()
				+ "/" + cutnameString + ".jpg";
		file = new File(filename);
		FileOutputStream fOut = null;
		try {
			file.createNewFile();
			fOut = new FileOutputStream(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);// 把Bitmap对象解析成流
		try {
			fOut.flush();
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		img_avatar.setImageBitmap(bitmap);// 头像
//		updateUserAvatar(file);
		// if (type == 1) {
		// img_avator.setImageBitmap(bitmap);//头像
		// file1=file;
		// } else {
		// images.add(bitmap);
		// files.add(file);
		// imgAdapter.notifyDataSetChanged();//详情
		// }
	}
	/**
	 * 更新服务器头像
	 */
	private void updateUserAvatar(File file) {
		try {
			RequestParams params = new RequestParams();
			params.put("id", BaseApp.getModel().getUserid());
			params.put("imgURL", file);
			HttpUtils.postUserAvatar(new HttpErrorHandler() {

				@Override
				public void onRecevieSuccess(JSONObject json) {
					Tools.toast(ZiLiaoActivity.this, "上传头像成功");
					String imgurl = json.getJSONObject(UrlContants.jsonData).getString("avatar");
					BaseApp.getModel().setAvatar(imgurl);
					setResult(RESULT_OK);
//					finish();
				}
			}, params);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
