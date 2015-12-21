package com.zykj.yuliang.utils;

import com.zykj.yuliang.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 
 * @author Joker_Ya
 * 
 */
public class ShapeImageView extends ImageView {

	private Context mContext;

	private int border_size = 0;// �߿���
	private int in_border_color = 0;// ��Բ�߿���ɫ
	private int out_border_color = 0;// ��Բ�߿���ɫ
	private int defColor = 0xFFFFFFFF;// Ĭ����ɫ

	private int width = 0;// �ؼ��Ŀ��
	private int height = 0;// �ؼ��ĸ߶�

	private String shape_type = "";// ��״������

	public ShapeImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.mContext = context;
	}

	public ShapeImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.mContext = context;
		setAttributes(attrs);
	}

	public ShapeImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		this.mContext = context;
		setAttributes(attrs);
	}

	/**
	 * ����Զ�������
	 * 
	 * @param attrs
	 */
	private void setAttributes(AttributeSet attrs) {
		// TODO Auto-generated method stub
		TypedArray mArray = mContext.obtainStyledAttributes(attrs,
				R.styleable.shapeimageview);
		// �õ��߿��ȣ����򷵻�0
		border_size = mArray.getDimensionPixelSize(
				R.styleable.shapeimageview_border_size, 0);
		// �õ��ڱ߿���ɫ�����򷵻�Ĭ����ɫ
		in_border_color = mArray.getColor(
				R.styleable.shapeimageview_in_border_color, defColor);
		// �õ���߿���ɫ�����򷵻�Ĭ����ɫ
		out_border_color = mArray.getColor(
				R.styleable.shapeimageview_out_border_color, defColor);
		// �õ���״������
		shape_type = mArray.getString(R.styleable.shapeimageview_shape_type);

		mArray.recycle();// ����mArray
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		// super.onDraw(canvas); ����ȥ�����л�ע�͵���������������ͼƬ
		// �õ������ͼƬ
		Drawable drawable = getDrawable();
		if (drawable == null) {
			return;
		}
		if (getWidth() == 0 || getHeight() == 0) {
			return;
		}
		this.measure(0, 0);
		if (drawable.getClass() == NinePatchDrawable.class) {// ����ô���ͼƬ��.9��ʽ��ͼƬ
			return;
		}

		// ��ͼƬתΪλͼ
		Bitmap mBitmap = ((BitmapDrawable) drawable).getBitmap();

		Bitmap cpBitmap = mBitmap.copy(Bitmap.Config.ARGB_8888, true);
		// �õ��������
		width = getWidth();
		height = getHeight();

		int radius = 0;//
		// �ж��Ƿ���Բ��
		if ("round".equals(shape_type)) {
			// �����Բ�߿����Բ�߿����ɫ������Ĭ����ɫ����˵����Բ�������߿�
			if (in_border_color != defColor && out_border_color != defColor) {
				// ������뾶
				radius = (width < height ? width : height) / 2 - 2
						* border_size;
				// ����Բ�߿�
				drawCircleBorder(canvas, radius + border_size / 2,
						in_border_color);
				// ����Բ�߿�
				drawCircleBorder(canvas,
						radius + border_size + border_size / 2,
						out_border_color);
			}// �����Բ�߿���ɫ������Ĭ����ɫ����˵����Բ��һ���߿�
			else if (in_border_color != defColor
					&& out_border_color == defColor) {
				radius = (width < height ? width : height) / 2 - border_size;

				drawCircleBorder(canvas, radius + border_size / 2,
						in_border_color);
			}// �����Բ�߿���ɫ������Ĭ����ɫ����˵����Բ��һ���߿�
			else if (in_border_color == defColor
					&& out_border_color != defColor) {
				radius = (width < height ? width : height) / 2 - border_size;

				drawCircleBorder(canvas, radius + border_size / 2,
						out_border_color);
			} else {// û�б߿�
				radius = (width < height ? width : height) / 2;
			}
		} else {
			radius = (width < height ? width : height) / 2;
		}

		Bitmap shapeBitmap = drawShapeBitmap(cpBitmap, radius);
		canvas.drawBitmap(shapeBitmap, width / 2 - radius, height / 2 - radius,
				null);
	}

	/**
	 * ����ָ����״��ͼƬ
	 * 
	 * @param cpBitmap
	 * @param radius
	 * @return
	 */
	private Bitmap drawShapeBitmap(Bitmap bmp, int radius) {
		// TODO Auto-generated method stub
		Bitmap squareBitmap;// ���ݴ����λͼ��ȡ���ʵ�������λͼ
		Bitmap scaledBitmap;// ����diameter�Խ�ȡ��������λͼ��������

		int diameter = radius * 2;
		// ����λͼ�Ŀ��
		int w = bmp.getWidth();
		int h = bmp.getHeight();
		// Ϊ�˷�ֹ��߲���ȣ����Բ��ͼƬ���Σ���˽�ȡ�������д����м�λ������������ͼƬ
		int squarewidth = 0, squareheight = 0;// ���εĿ��
		int x = 0, y = 0;
		if (h > w) {// �����>��
			squarewidth = squareheight = w;
			x = 0;
			y = (h - w) / 2;
			// ��ȡ������ͼƬ
			squareBitmap = Bitmap.createBitmap(bmp, x, y, squarewidth,
					squareheight);
		} else if (h < w) {// �����>��
			squarewidth = squareheight = h;
			x = (w - h) / 2;
			y = 0;
			squareBitmap = Bitmap.createBitmap(bmp, x, y, squarewidth,
					squareheight);
		} else {
			squareBitmap = bmp;
		}
		// ��squareBitmap��������Ϊdiameter�߳���������λͼ
		if (squareBitmap.getWidth() != diameter
				|| squareBitmap.getHeight() != diameter) {
			scaledBitmap = Bitmap.createScaledBitmap(squareBitmap, diameter,
					diameter, true);
		} else {
			scaledBitmap = squareBitmap;
		}

		Bitmap outputbmp = Bitmap.createBitmap(scaledBitmap.getWidth(),
				scaledBitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(outputbmp);// ����һ����ͬ��С�Ļ���
		Paint paint = new Paint();// ���廭��
		paint.setAntiAlias(true);// ���ÿ����
		paint.setFilterBitmap(true);
		paint.setDither(true);
		canvas.drawARGB(0, 0, 0, 0);

		Path path = new Path();

		path.moveTo(diameter, diameter / 2);
		path.lineTo(diameter, diameter);
		path.lineTo(diameter / 2, diameter);

		path.close();
		canvas.drawPath(path, paint);

		// if ("star".equals(shape_type)) {// ������Ƶ���״Ϊ�������
		// Path path = new Path();
		// float radian = degree2Radian(36);// 36Ϊ����ǵĽǶ�
		// float radius_in = (float) (radius * Math.sin(radian / 2) / Math
		// .cos(radian)); // �м�����εİ뾶
		//
		// path.moveTo((float) (radius * Math.cos(radian / 2)), 0);// �˵�Ϊ����ε����
		// path.lineTo((float) (radius * Math.cos(radian / 2) + radius_in
		// * Math.sin(radian)),
		// (float) (radius - radius * Math.sin(radian / 2)));
		// path.lineTo((float) (radius * Math.cos(radian / 2) * 2),
		// (float) (radius - radius * Math.sin(radian / 2)));
		// path.lineTo((float) (radius * Math.cos(radian / 2) + radius_in
		// * Math.cos(radian / 2)),
		// (float) (radius + radius_in * Math.sin(radian / 2)));
		// path.lineTo(
		// (float) (radius * Math.cos(radian / 2) + radius
		// * Math.sin(radian)), (float) (radius + radius
		// * Math.cos(radian)));
		// path.lineTo((float) (radius * Math.cos(radian / 2)),
		// (float) (radius + radius_in));
		// path.lineTo(
		// (float) (radius * Math.cos(radian / 2) - radius
		// * Math.sin(radian)), (float) (radius + radius
		// * Math.cos(radian)));
		// path.lineTo((float) (radius * Math.cos(radian / 2) - radius_in
		// * Math.cos(radian / 2)),
		// (float) (radius + radius_in * Math.sin(radian / 2)));
		// path.lineTo(0, (float) (radius - radius * Math.sin(radian / 2)));
		// path.lineTo((float) (radius * Math.cos(radian / 2) - radius_in
		// * Math.sin(radian)),
		// (float) (radius - radius * Math.sin(radian / 2)));
		//
		// path.close();// ʹ��Щ�㹹�ɷ�յĶ����
		// canvas.drawPath(path, paint);
		// } else if ("triangle".equals(shape_type)) {// ������Ƶ���״Ϊ������
		// Path path = new Path();
		//
		// path.moveTo(diameter, 0);
		// path.lineTo(diameter, diameter);
		// path.lineTo(0, diameter);
		//
		// path.close();
		// canvas.drawPath(path, paint);
		// } else if ("heart".equals(shape_type)) {// ������Ƶ���״Ϊ����
		// Path path = new Path();
		//
		// path.moveTo(diameter / 2, diameter / 5);
		// path.quadTo(diameter, 0, diameter / 2, diameter / 1.0f);
		// path.quadTo(0, 0, diameter / 2, diameter / 5);
		//
		// path.close();
		// canvas.drawPath(path, paint);
		// } else {// ����Ĭ����״��Բ��
		// // ����Բ��
		// canvas.drawCircle(scaledBitmap.getWidth() / 2,
		// scaledBitmap.getHeight() / 2, scaledBitmap.getWidth() / 2,
		// paint);
		// }
		// ����Xfermode��Mode
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(scaledBitmap, 0, 0, paint);

		bmp = null;
		squareBitmap = null;
		scaledBitmap = null;
		return outputbmp;

	}

	/**
	 * �Ƕ�ת���ȹ�ʽ
	 * 
	 * @param degree
	 * @return
	 */
	private float degree2Radian(int degree) {
		// TODO Auto-generated method stub
		return (float) (Math.PI * degree / 180);
	}

	/**
	 * ���ͼƬΪԲ�Σ���÷���Ϊ����Բ��ͼƬ����ɫ�߿�
	 * 
	 * @param canvas
	 * @param radius
	 *            �߿�뾶
	 * @param color
	 *            �߿���ɫ
	 */
	private void drawCircleBorder(Canvas canvas, int radius, int color) {
		// TODO Auto-generated method stub
		Paint paint = new Paint();

		paint.setAntiAlias(true);// �����
		paint.setFilterBitmap(true);
		paint.setDither(true);
		paint.setColor(color);// ���û�����ɫ
		paint.setStyle(Paint.Style.STROKE);// ���û��ʵ�styleΪSTROKE������
		paint.setStrokeWidth(border_size);// ���û��ʵĿ��
		// ��������Բ��Ҳ���Ǳ߿�
		canvas.drawCircle(width / 2, height / 2, radius, paint);
	}

}
