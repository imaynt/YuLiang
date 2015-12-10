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
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zykj.yuliang.BaseActivity;
import com.zykj.yuliang.BaseApp;
import com.zykj.yuliang.R;
import com.zykj.yuliang.http.HttpErrorHandler;
import com.zykj.yuliang.http.HttpUtils;
import com.zykj.yuliang.utils.CommonUtils;
import com.zykj.yuliang.utils.StringUtil;
import com.zykj.yuliang.utils.Tools;
import com.zykj.yuliang.view.MyCommonTitle;
import com.zykj.yuliang.view.PickDialog;
import com.zykj.yuliang.view.PickDialog.PickDialogListener;
import com.zykj.yuliang.view.RoundImageView;
import com.zykj.yuliang.view.UIDialog;

public class UserInfoActivity extends BaseActivity {
	private MyCommonTitle myCommonTitle;
	private LinearLayout ll_nick, ll_avatar, ll_sex, ll_birth, ll_prefession,
			ll_submit;
	private TextView tv_sex, tv_birthday, tv_profession;
	private RoundImageView img_avatar;
	private EditText user_nick;
	private String timeString;// �ϴ�ͷ���ֶ�
	private File file;
	private List<String> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_more_user_info);

		initView();
	}

	private void initView() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle("����");

		user_nick = (EditText) findViewById(R.id.ed_user_nick);// �ǳ�
		img_avatar = (RoundImageView) findViewById(R.id.img_avatar);// ͷ��
		tv_sex = (TextView) findViewById(R.id.tv_sex);// �Ա�
		tv_birthday = (TextView) findViewById(R.id.tv_birthday);// ����
		tv_profession = (TextView) findViewById(R.id.tv_profession);// ְҵ

		ll_avatar = (LinearLayout) findViewById(R.id.ll_avatar);// ͷ��
		ll_sex = (LinearLayout) findViewById(R.id.ll_sex);// �Ա�
		ll_birth = (LinearLayout) findViewById(R.id.ll_birthday);// ����
		ll_prefession = (LinearLayout) findViewById(R.id.ll_profession);// ְҵ
		ll_submit = (LinearLayout) findViewById(R.id.ll_submit);// �ύ

		setListener(ll_avatar, ll_sex, ll_birth, ll_prefession, ll_submit);
	}

	@Override
	public void onClick(View view) {
		super.onClick(view);
		switch (view.getId()) {
		case R.id.ll_avatar:
			UIDialog.ForThreeBtn(UserInfoActivity.this, new String[] { "����",
					"�������ȡ", "ȡ��" }, UserInfoActivity.this);
			break;
		case R.id.ll_sex:// �Ա�
			list = new ArrayList<String>();
			list.add("��");
			list.add("Ů");
			new PickDialog(UserInfoActivity.this, "��ѡ���Ա�", list,
					new PickDialogListener() {

						@Override
						public void onListItemClick(int position, String string) {
							tv_sex.setText(list.get(position));
						}
					}).show();
			break;
		case R.id.ll_birthday:// ����
			CommonUtils.showDateTimePicker(this, tv_birthday);
			// startView.findViewById(R.id.hour).setVisibility(View.GONE);
			// startView.findViewById(R.id.mins).setVisibility(View.GONE);
			break;
		case R.id.ll_profession:// ְҵ
			list = new ArrayList<String>();
			list.add("ѧ��");
			list.add("��ʦ");
			list.add("�ϰ���");
			list.add("�ϰ�");
			list.add("����Ա");
			list.add("����ְҵ��");
			list.add("����");
			list.add("����");
			new PickDialog(UserInfoActivity.this, "��ѡ��ְҵ", list,
					new PickDialogListener() {

						@Override
						public void onListItemClick(int position, String string) {
							tv_profession.setText(list.get(position));
						}
					}).show();
			break;
		case R.id.ll_submit:
			String nick = user_nick.getText().toString().trim();
			String sex = tv_sex.getText().toString().trim();
			String birth = tv_birthday.getText().toString().trim();
			String profession = tv_profession.getText().toString().trim();
			if (StringUtil.isEmpty(nick)) {
				Tools.toast(UserInfoActivity.this, "�ǳƲ���Ϊ��");
				return;
			}
			if (StringUtil.isEmpty(sex)) {
				Tools.toast(UserInfoActivity.this, "�Ա���Ϊ��");
				return;
			}
			if (StringUtil.isEmpty(birth)) {
				Tools.toast(UserInfoActivity.this, "���ղ���Ϊ��");
				return;
			}
			if (StringUtil.isEmpty(profession)) {
				Tools.toast(UserInfoActivity.this, "ְҵ����Ϊ��");
				return;
			}
//			submitData();
			RequestParams params = new RequestParams();
			/**
			 * ��ΪdeviceIdΪ�������ύ���ݴ���,����deviceId.............................................
			 */
//			params.put("deviceId", BaseApp.getModel().getDeviceId());// deviceId�豸id
			params.put("username", nick);// username���룬�µĻ�Ա�ǳ�
			params.put("sex", sex);// sex����, �Ա�
			params.put("birthday", birth);// birthday����, ����
			params.put("profession", profession);// profession����, ְҵ
			HttpUtils.updateUserInfo(new HttpErrorHandler() {

				@Override
				public void onRecevieSuccess(JSONObject json) {
					Tools.toast(UserInfoActivity.this, "���ϸ��³ɹ�!");
					
					setResult(RESULT_OK);
					finish();
					
				}
			}, params);
			
			break;
		case R.id.dialog_modif_1:// �����������
			/* ���� */
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
		case R.id.dialog_modif_2:// �������ѡȡ
			UIDialog.closeDialog();
			Intent photoIntent = new Intent(Intent.ACTION_PICK, null);
			photoIntent.setDataAndType(
					MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
			startActivityForResult(photoIntent, 2);
			break;
		case R.id.dialog_modif_3:// ȡ��
			UIDialog.closeDialog();
			break;
		default:
			break;
		}
	}

	/**
	 * �ύ����............
	 */
	private void submitData() {

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 1:
			/* ����ǵ���������գ�ͼƬ�������ֺ�·�� */
			File temp = new File(Environment.getExternalStorageDirectory()
					.getPath() + "/DCIM/Camera/" + timeString + ".jpg");
			startPhotoZoom(Uri.fromFile(temp));
			break;
		case 2:
			/* �����ֱ�Ӵ�����ȡ */
			try {
				startPhotoZoom(data.getData());
			} catch (Exception e) {
				Toast.makeText(this, "��û��ѡ���κ���Ƭ", Toast.LENGTH_LONG).show();
			}
			break;
		case 3:
			/* ȡ�òü����ͼƬ */
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
			// ����һ���ļ��ж��󣬸�ֵΪ�ⲿ�洢����Ŀ¼
			File sdcardDir = Environment.getExternalStorageDirectory();
			// �õ�һ��·����������sdcard���ļ���·��������
			String path = sdcardDir.getPath() + "/DCIM/Camera";
			File path1 = new File(path);
			if (!path1.exists()) {
				// �������ڣ�����Ŀ¼��������Ӧ��������ʱ�򴴽�
				path1.mkdirs();

			}
		}
	}

	/**
	 * �ü�ͼƬ����ʵ��
	 * 
	 * @param uri
	 */
	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// �������crop=true�������ڿ�����Intent��������ʾ��VIEW�ɲü�
		intent.putExtra("crop", "true");
		// aspectX aspectY �ǿ�ߵı���
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY �ǲü�ͼƬ���
		intent.putExtra("outputX", 150);
		intent.putExtra("outputY", 150);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, 3);
	}

	/**
	 * ����ü�֮���ͼƬ����
	 * 
	 * @param picdata
	 */
	private void setPicToView(Intent picdata) {
		Bundle extras = picdata.getExtras();
		if (extras != null) {
			Bitmap photo = extras.getParcelable("data");
			// Drawable drawable = new BitmapDrawable(photo);
			/* ����ע�͵ķ����ǽ��ü�֮���ͼƬ��Base64Coder���ַ���ʽ�� ������������QQͷ���ϴ����õķ������������ */
			savaBitmap(photo);
			// avatar_head_image.setBackgroundDrawable(drawable);
		}
	}

	/**
	 * �����к��ͼƬ���浽����ͼƬ�ϣ�
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
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);// ��Bitmap�����������
		try {
			fOut.flush();
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		img_avatar.setImageBitmap(bitmap);// ͷ��
		// if (type == 1) {
		// img_avator.setImageBitmap(bitmap);//ͷ��
		// file1=file;
		// } else {
		// images.add(bitmap);
		// files.add(file);
		// imgAdapter.notifyDataSetChanged();//����
		// }
	}
}
