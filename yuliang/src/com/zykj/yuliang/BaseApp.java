package com.zykj.yuliang;

import java.util.Stack;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.zykj.yuliang.model.AppModel;
import com.zykj.yuliang.utils.StringUtil;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;


public class BaseApp extends Application {
	
	/**
	 * �洢��sharePerfence
	 */
	public static final String config = "config";//�洢��sharePerfence
	public static final String IS_INTRO = "is_intro";//��ǰ���Ƿ��Ѿ����й�ָ��
	public static final String VERSION = "version";//��ǰӦ���д洢�İ汾��
	
	// ===========����==========
	private static final String TAG = "BaseApp";
	public static final String FILE_DIR = "heer_dir";

	private static Context context;
	private static Stack<Activity> activityStack;
	private static BaseApp instance;
    private static AppModel model;
	public BaseApp() {
	}

	public synchronized static BaseApp getInstance() {
		if (null == instance) {
			instance = new BaseApp();
		}
		return instance;
	}

    private void initModel() {
    	/*��ʼ���û�Model*/
        model=AppModel.init(this);
    }

	/**
	 * ��ȡ�û���Ϣ
	 */
    public static AppModel getModel(){
        if(model == null){
            Log.e("application","appmodel is null");
        }
        return model;
    }

	/**
	 * ��֤�û��Ƿ��¼
	 */
    public static boolean validateUserLogin(){
        if(StringUtil.isEmpty(model.getUserid())){
            return false;
        }else{
            return true;
        }
    }

	/**
	 * ��Activity
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);

		Log.d(TAG, "-----------------------------------");
		for (Activity temp : activityStack) {
			Log.d(TAG, "����:" + temp.toString() + "��ַ��" + temp);
		}
		Log.d(TAG, "===================================");
	}

	/**
	 * ��ȡ��ǰActivity
	 */
	public Activity currentActivity() {
		Activity activity = activityStack.lastElement();
		return activity;
	}

	/**
	 * �ر�Activity
	 */
	public void finishActivity() {
		Activity activity = activityStack.lastElement();
		finishActivity(activity);
	}

	/**
	 * �ر�ָ��Activity
	 */
	public void finishActivity(Activity activity) {
		if (activity != null) {
			activityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}

	/**
	 * �ر����е�Activity
	 */
	public void finishAllActivity() {
		for (int i = 0, size = activityStack.size(); i < size; i++) {
			if (null != activityStack.get(i)) {
				activityStack.get(i).finish();
			}
		}
		activityStack.clear();
	}

	/**
	 * ��ǰ����ָ�ʱ�Ĳ���
	 */
	public void resumeActivity(Activity activity) {
		if (activityStack.lastElement() == activity) {
			return;
		}
		activityStack.remove(activity);
		activityStack.push(activity);

		Log.d(TAG, "���һ������:" + activityStack.lastElement());
	}

	public void exit() {
		finishAllActivity();
		System.exit(0);
	}

	public void onLowMemory() {
		super.onLowMemory();
		System.gc();
	}

	@Override
	public void onCreate() {
		initImageLoader();
		context = getApplicationContext();
		Log.d(TAG, "[ExampleApplication] onCreate");
		super.onCreate();
		
        initModel();//��ʼ�� ����
	}
	
	/**
	 * ��ȡȫ��Context
	 */
	public static Context getContext() {
		return context;
	}

	/**
	 * ��ʼ��ImageLoader
	 */
	protected void initImageLoader() {
		//��ʼ��ImageLoader
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(this);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024);
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs();

        ImageLoader.getInstance().init(config.build());
	}
}