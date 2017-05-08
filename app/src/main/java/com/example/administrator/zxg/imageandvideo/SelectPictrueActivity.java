package com.example.administrator.zxg.imageandvideo;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.administrator.zxg.R;
import com.example.administrator.zxg.common.CommonActivity;
import com.example.administrator.zxg.entity.ImageBean;
import com.example.administrator.zxg.util.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * zhaoxg
 */
public class SelectPictrueActivity extends CommonActivity implements View.OnClickListener{

    public static SelectPictrueActivity selectPicActivity = null;
    private HashMap<String, List<String>> mGruopMap = new HashMap<String, List<String>>();
    private List<ImageBean> list = new ArrayList<ImageBean>();
    private List<String> list1 = new ArrayList<String>();
    private final static int SCAN_OK = 1;
    private final static int code = 100;
    private static File currentFile = null;
    private ProgressDialog mProgressDialog;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SCAN_OK:
                    // 关闭进度条
                    mProgressDialog.dismiss();
                    list = subGroupOfImage(mGruopMap);
                    if ("".equals(list) || null == list) {
                        ToastUtil.showShort(SelectPictrueActivity.this,"您的相册为空请添加图片");
                        SelectPictrueActivity.this.finish();
                    } else {

                        for (int i = 0; i < list.size(); i++) {
                            List<String> childList = mGruopMap.get(list.get(i)
                                    .getFolderName());
                            for (int j = 0; j < childList.size(); j++) {
                                list1.add(childList.get(j));
                            }
                        }
                    }
                    Intent mIntent = new Intent(SelectPictrueActivity.this,
                            ShowImageActivity.class);
                    mIntent.putExtra("touxiang",
                            getIntent().getBooleanExtra("touxiang", false));// 是否为上传头像页面
                    mIntent.putExtra("count", getIntent().getIntExtra("count", 0));
                    mIntent.putStringArrayListExtra("data",
                            (ArrayList<String>) list1);
                    startActivityForResult(mIntent, code);
                    break;
            }
        }

    };
    /***
     * 使用照相机拍照获取图片
     */
    public static final int SELECT_PIC_BY_TACK_PHOTO = 1;
    /***
     * 使用相册中的图片
     */
    public static final int SELECT_PIC_BY_PICK_PHOTO = 2;

    /***
     * 从Intent获取图片路径的KEY
     */
    public static final String KEY_PHOTO_PATH = "photo_path";

    private static final String TAG = "SelectPicActivity";

    private LinearLayout dialogLayout;
    private Button takePhotoBtn, pickPhotoBtn, cancelBtn;

    /**
     * 获取到的图片路径
     */
    private String picPath;

    private Intent lastIntent;

    private Uri photoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_select_pic);
        initView();
    }

    /**
     * 初始化加载View
     */
    private void initView() {
        dialogLayout = (LinearLayout) findViewById(R.id.dialog_layout);
        dialogLayout.setOnClickListener(this);
        takePhotoBtn = (Button) findViewById(R.id.btn_take_photo);
        takePhotoBtn.setText("拍攝照片");
        takePhotoBtn.setOnClickListener(this);
        pickPhotoBtn = (Button) findViewById(R.id.btn_pick_photo);
        pickPhotoBtn.setOnClickListener(this);
        cancelBtn = (Button) findViewById(R.id.btn_cancel);
        cancelBtn.setOnClickListener(this);
        lastIntent = getIntent();
    }

    private void getImages() {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "暂无外部存储", Toast.LENGTH_SHORT).show();
            return;
        }
        // 显示进度条
        mProgressDialog = ProgressDialog.show(this, null, "正在加载...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = SelectPictrueActivity.this
                        .getContentResolver();

                // 只查询jpeg和png的图片
                Cursor mCursor = mContentResolver.query(mImageUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or "
                                + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png"},
                        MediaStore.Images.Media.DATE_MODIFIED);

                while (mCursor.moveToNext()) {
                    // 获取图片的路径
                    String path = mCursor.getString(mCursor
                            .getColumnIndex(MediaStore.Images.Media.DATA));
                    // 获取该图片的父路径名
                    String parentName = new File(path).getParentFile()
                            .getName();
                    // 根据父路径名将图片放入到mGruopMap中
                    if (!mGruopMap.containsKey(parentName)) {
                        List<String> chileList = new ArrayList<String>();
                        chileList.add(path);
                        mGruopMap.put(parentName, chileList);
                    } else {
                        mGruopMap.get(parentName).add(path);
                    }
                }

                mCursor.close();
                // 通知Handler扫描图片完成
                mHandler.sendEmptyMessage(SCAN_OK);

            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_layout:
                finish();
                break;
            case R.id.btn_take_photo:
                takePhoto();
                break;
            case R.id.btn_pick_photo:
                getImages();

                break;
            default:
                finish();
                break;
        }
    }

    /**
     * 拍照获取图片
     */
    private void takePhoto() {

        //因為Android 6.0 系統 需要動態申請權限，所有每次使用相機之前判斷是否有權限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //进入到这里代表没有权限.
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)){
                //
                Toast.makeText(this,"您已經禁止該權限，需要重新開啟", Toast.LENGTH_LONG).show();
            }else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE);
            }
        } else {
            String SDState = Environment.getExternalStorageState();
            if (SDState.equals(Environment.MEDIA_MOUNTED)) {

                File dir = new File(Environment.getExternalStorageDirectory(),
                        "bianbian");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                currentFile = new File(dir, System.currentTimeMillis() + ".jpg");
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(currentFile));
                startActivityForResult(intent, SELECT_PIC_BY_TACK_PHOTO);

                // Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//
                // "android.media.action.IMAGE_CAPTURE"
                /***
                 * 需要说明一下，以下操作使用照相机拍照，拍照后的图片会存放在相册中的 这里使用的这种方式有一个好处就是获取的图片是拍照后的原图
                 * 如果不实用ContentValues存放照片路径的话，拍照后获取的图片为缩略图不清晰
                 */
                // ContentValues values = new ContentValues();
                // photoUri =
                // this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                // values);
                // // this.getContentResolver().insert(MediaStore.Images.Media,
                // // values);
                // intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                /** ----------------- */
                // startActivityForResult(intent, SELECT_PIC_BY_TACK_PHOTO);
            } else {
                Toast.makeText(this, "内存卡不存在", Toast.LENGTH_LONG).show();
            }
        }

    }

    /***
     * 从相册中取图片
     */
    private void pickPhoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, SELECT_PIC_BY_PICK_PHOTO);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }

    /**
     * 选择图片后，获取图片的路径
     *
     * @param requestCode
     * @param data
     */
    private void doPhoto(int requestCode, Intent data) {
        if (requestCode == SELECT_PIC_BY_PICK_PHOTO) // 从相册取图片，有些手机有异常情况，请注意
        {
            if (data == null) {
                Toast.makeText(this, "选择图片文件出错", Toast.LENGTH_LONG).show();
                return;
            }
            photoUri = data.getData();
            if (photoUri == null) {
                Toast.makeText(this, "选择图片文件出错", Toast.LENGTH_LONG).show();
                return;
            }
        }
        String[] pojo = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(photoUri, pojo, null, null, null);
        if (cursor != null) {
            int columnIndex = cursor.getColumnIndexOrThrow(pojo[0]);
            cursor.moveToFirst();
            picPath = cursor.getString(columnIndex);
            cursor.close();
        }
        Log.i(TAG, "imagePath = " + picPath);
        if (picPath != null
                && (picPath.endsWith(".png") || picPath.endsWith(".PNG")
                || picPath.endsWith(".jpg") || picPath.endsWith(".JPG"))) {
            lastIntent.putExtra(KEY_PHOTO_PATH, picPath);
            setResult(Activity.RESULT_OK, lastIntent);
            finish();
        } else {
            Toast.makeText(this, "选择图片文件不正确", Toast.LENGTH_LONG).show();
        }
    }

    private List<ImageBean> subGroupOfImage(
            HashMap<String, List<String>> mGruopMap) {
        if (mGruopMap.size() == 0) {
            return null;
        }
        List<ImageBean> list = new ArrayList<ImageBean>();

        Iterator<Map.Entry<String, List<String>>> it = mGruopMap.entrySet()
                .iterator();
        while (it.hasNext()) {
            Map.Entry<String, List<String>> entry = it.next();
            ImageBean mImageBean = new ImageBean();
            String key = entry.getKey();
            List<String> value = entry.getValue();
            mImageBean.setFolderName(key);
            mImageBean.setImageCounts(value.size());
            mImageBean.setTopImagePath(value.get(0));// 获取该组的第一张图片
            list.add(mImageBean);
        }
        return list;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Intent intent = new Intent();
            if (requestCode == SELECT_PIC_BY_TACK_PHOTO) {// 相机获取
                ArrayList<String> aList = new ArrayList<String>();
                aList.add(currentFile.getAbsolutePath());
                intent.putStringArrayListExtra("list", aList);
            } else if (requestCode == code) {// 相册选择
                intent.putStringArrayListExtra("list",
                        data.getStringArrayListExtra("list"));
            }
            // 设置返回数据
            setResult(RESULT_OK, intent);
            finish();
        } else {
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
