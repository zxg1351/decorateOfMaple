package com.example.administrator.zxg.ui.ContactListNoBug;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.zxg.R;


public class MyLetterView extends View {

	public static String[] letters = { "A", "B", "C", "D", "E", "F", "G", "H",
			"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
			"V", "W", "X", "Y", "Z", "#" };

	private Paint paint = new Paint();
	/**
	 * ��ʾѡ��״̬
	 */
	private int choose = -1;

	private TextView mTextDialog;

	public void setTextDialog(TextView mTextDialog) {
		this.mTextDialog = mTextDialog;
	}

	private OnTouchingLetterChangedListener listener;

	public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener listener) {
		this.listener = listener;
	}

	public MyLetterView(Context context) {
		super(context);
	}

	public MyLetterView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyLetterView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// ��ȡ���Զ���View�Ŀ�Ⱥ͸߶�
		int width = getWidth();
		int height = getHeight();

		// ������ĸ�ĸ߶�
		int singleHeight = height / letters.length;

		for (int i = 0; i < letters.length; i++) {
			paint.setColor(getResources().getColor(
					R.color.color_bottom_text_normal));
			paint.setTypeface(Typeface.DEFAULT_BOLD);
			paint.setAntiAlias(true);
			paint.setTextSize(PixelUtil.sp2px(12, getContext()));

			// ���ѡ�еĻ�,�ı���ʽ����ɫ
			if (i == choose) {
				paint.setColor(Color.parseColor("#3399ff"));
				paint.setFakeBoldText(true);
			}

			// ����ȷ��ÿ����ĸ�ĺ������λ�ã������꣺���Զ���View��һ�� -����ȥ�� ������ĸ��ȵ�һ��
			float xPos = width / 2 - paint.measureText(letters[i]) / 2;
			float yPos = singleHeight * (i + 1);

			canvas.drawText(letters[i], xPos, yPos, paint);

			// ���û���
			paint.reset();
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {

		int action = event.getAction();
		float y = event.getY();

		int oldChoose = choose;
		// ����y����ȷ����ǰ�ĸ���ĸ��ѡ��
		int pos = (int) (y / getHeight() * letters.length);

		switch (action) {
		case MotionEvent.ACTION_UP:
			// ����ָ̧��ʱ,����View�ı���Ϊ��ɫ
			setBackgroundDrawable(new ColorDrawable(0x00000000));
			// ����Ϊ��ʼ״̬
			choose = -1;
			// ��View�ػ�
			invalidate();

			// ���Ի�������Ϊ���ɼ�
			if (mTextDialog != null) {
				mTextDialog.setVisibility(View.INVISIBLE);
			}

			break;
		default:
			// �����ұ���ĸView�ı���ɫ
			setBackgroundResource(R.drawable.v2_sortlistview_sidebar_background);
			if (pos != oldChoose) {
				// ���֮ǰѡ�еĺ͵�ǰ�Ĳ�һ������Ҫ�ػ�
				if (pos >= 0 && pos < letters.length) {
					if(listener != null) {
						//��ǰ��ĸ��ѡ�У���Ҫ��ListViewȥ������ʾ��λ��
						listener.onTouchingLetterChanged(letters[pos]);
					}
					//�������ʾѡ�е���ĸ������ĸ����TextView�ϣ��൱��һ��dialog
					if (mTextDialog != null) {
						mTextDialog.setText(letters[pos]);
						mTextDialog.setVisibility(View.VISIBLE);
					}
					choose = pos;
				}
			}
			break;
		}

		return true;
	}

	/**
	 * �ûص��ӿ�����֪ͨListView����״̬
	 */
	public interface OnTouchingLetterChangedListener {
		public void onTouchingLetterChanged(String s);
	}

}
