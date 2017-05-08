package com.example.administrator.zxg.imageandvideo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.administrator.zxg.R;
import com.example.administrator.zxg.common.CommonActivity;

import java.util.ArrayList;
import java.util.List;

public class ShowImageActivity extends CommonActivity {
	private GridView mGridView;
	public List<String> list;
	private ArrayList<String> bitmapList = new ArrayList<String>();
	private ChildAdapter adapter;
	private View myView;
	private TextView textView, tView;
	private String urlString, urlImage;
	public static int PHOTO_MARGIN = 50;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_image_activity);
		textView = (TextView) findViewById(R.id.tv_count);
		if (getIntent().getIntExtra("count", 0) > 0) {
			textView.setText("完成(" + getIntent().getIntExtra("count", 0) + ")");
		}
		tView = (TextView) findViewById(R.id.tv_showimg);
		mGridView = (GridView) findViewById(R.id.child_grid);
		list = getIntent().getStringArrayListExtra("data");
		adapter = new ChildAdapter(this, list, mGridView);
		mGridView.setAdapter(adapter);
		mGridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
									long arg3) {
				myView = arg1;
				urlImage = list.get(arg2);
			}
		});
	}

	public CountImageWsdl cWsdl = new CountImageWsdl() {
		@Override
		public void add() {
			if (adapter.getSelectItems().size() == 0) {
				textView.setText("完成");
			} else {
				int i = adapter.getSelectItems().size()
						+ getIntent().getIntExtra("count", 0);
				textView.setText("完成(" + i + ")");
			}
		}

		@Override
		public void addToList(String bitmap, int i) {
			int k = bitmapList.size();
			if (i == 1) {
				// 避免添加同一张图片
				if (bitmapList.contains(bitmap)) {
					return;
				}
				bitmapList.add(bitmap);
				k = bitmapList.size();
			} else {
				bitmapList.remove(bitmap);
				k = bitmapList.size();
			}
		}
	};

	public CountImageWsdl getCountImageWsdl() {
		return cWsdl;
	}

	public void finishPickPic(View view) {
		Log.e("", bitmapList.size() + "");
		Intent intent = new Intent();
		// 把返回数据存入Intent
		intent.putStringArrayListExtra("list", bitmapList);
		// 设置返回数据
		setResult(RESULT_OK, intent);
		// 关闭Activity
		finish();

	}
}
