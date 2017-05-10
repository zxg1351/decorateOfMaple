package com.example.administrator.zxg.ui;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.zxg.R;
import com.example.administrator.zxg.adapter.ContactListAdapter;
import com.example.administrator.zxg.common.CommonActivity;
import com.example.administrator.zxg.entity.ContactEntity;
import com.example.administrator.zxg.util.ToastUtil;

import java.io.InputStream;
import java.util.ArrayList;



/**
 *
 * 联系人列表
 * Created by Administrator on 2016/8/27.
 */
public class ContactListActivity extends CommonActivity {

    private TextView tv_title;
    private EditText et_search;
    private String etSearch;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        getPhoneContacts();//获取电话联系人
        initView();
        initList();
    }

    /**
     * 初始化页面
     */
    private void initView() {
        tv_title =  (TextView)findViewById(R.id.tv_title);
        tv_title.setText("联系人列表");
        et_search = (EditText) findViewById(R.id.et_search);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.me);
        return super.onCreateOptionsMenu(menu);
    }

    //联系人名称
    private ArrayList<ContactEntity> mContacts = new ArrayList<ContactEntity>();

    private static final String[] PHONES_PROJECTION = new String[]{
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone.PHOTO_ID,
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID};
    //联系人显示名称
    private static final int PHONES_DISPLAY_NAME_INDEX = 0;
    //电话号码
    private static final int PHONES_NUMBER_INDEX = 1;
    //头像ID
    private static final int PHONES_PHOTO_ID_INDEX = 2;
    //联系人ID
    private static final int PHONES_CONTACT_ID_INDEX = 3;

    private void getPhoneContacts() {
        ContentResolver resolver = getContentResolver();
        Cursor phoneCursor = resolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                PHONES_PROJECTION, null, null, null);

        if (phoneCursor != null) {
            while (phoneCursor.moveToNext()) {
                //得到手机号码
                String phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX);
                //当手机号码为空或者为空字段跳过当前循环
//                if(Textu)
                //得到联系人名称
                // 得到联系人名称
                String contactName = phoneCursor
                        .getString(PHONES_DISPLAY_NAME_INDEX);

                // 得到联系人ID
                Long contactid = phoneCursor
                        .getLong(PHONES_CONTACT_ID_INDEX);

                // 得到联系人头像ID
                Long photoid = phoneCursor.getLong(PHONES_PHOTO_ID_INDEX);

                // 得到联系人头像Bitamp
                Bitmap contactPhoto = null;
                // photoid 大于0 表示联系人有头像 如果没有给此人设置头像则给他一个默认的
                if (photoid > 0) {
                    Uri uri = ContentUris.withAppendedId(
                            ContactsContract.Contacts.CONTENT_URI,
                            contactid);
                    InputStream input = ContactsContract.Contacts
                            .openContactPhotoInputStream(resolver, uri);
                    contactPhoto = BitmapFactory.decodeStream(input);
                } else {
                    contactPhoto = BitmapFactory.decodeResource(
                            getResources(), R.mipmap.icon_head_bg);
                }
                ContactEntity mContact = new ContactEntity(contactName,
                        phoneNumber, contactPhoto);
                mContacts.add(mContact);
            }
            phoneCursor.close();
        }
    }

    private void initList() {
        ListView lv = (ListView) findViewById(R.id.lv_contact);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ContactListActivity.this,LayoutActivity.class);
                startActivity(intent);
            }
        });
        lv.setAdapter(new ContactListAdapter(ContactListActivity.this,mContacts));
    }

    /**
     * 联系查询
     * @param view
     */
    public void contactSearch(View view){
        ToastUtil.showShort(ContactListActivity.this,"查询结果信息为成功");
        etSearch = et_search.getText().toString().trim();

        //TODO 正常走查询接口

    }



}
