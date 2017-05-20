package com.example.administrator.zxg.ui.ContactListNoBug;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.zxg.R;
import com.example.administrator.zxg.common.CommonActivity;
import com.example.administrator.zxg.entity.ContactEntity;
import com.orhanobut.logger.Logger;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description
 * @Author bayonet1351
 * Created 2017/5/20 14:02
 */

public class ContactListNoBugActivity extends CommonActivity {

    private ListView list_friends;
    private TextView dialog;
    private MyLetterView right_letter;
    private List<Friend> friends;

    /**
     * 根据拼音来排列ListView中的数据
     */
    private PinyinComparator pinyinComparator;

    private CharacterParser characterParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_listnobug);

        Logger.d("A[PP");
        initData();
        initView();
        Logger.d("initView");
    }


//
    private void initData() {
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        String[] names = getResources().getStringArray(R.array.names);
        friends = new ArrayList<Friend>();
        Friend f = new Friend();
        for (int i = 0; i <names.length; i++) {

            f.setName(names[i]);
            String pinyin = characterParser.getSelling(names[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();
            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                f.setSortLetters(sortString.toUpperCase());
            } else {
                f.setSortLetters("#");
            }
            friends.add(f);
        }
        Collections.sort(friends, pinyinComparator);
    }

    private void initView() {
        list_friends = (ListView) findViewById(R.id.list_friends);
        dialog = (TextView) findViewById(R.id.dialog);
        right_letter = (MyLetterView) findViewById(R.id.right_letter);
        right_letter.setTextDialog(dialog);
        final FriendsAdapter adapter = new FriendsAdapter(this,friends);
        right_letter
                .setOnTouchingLetterChangedListener(new MyLetterView.OnTouchingLetterChangedListener() {
                    @Override
                    public void onTouchingLetterChanged(String s) {
                        // 该字母首次出现的位置
                        int position = adapter.getPositionForSection(s.charAt(0));
                        if (position != -1) {
                            list_friends.setSelection(position);
                        }
                    }
                });

        list_friends.setAdapter(adapter);
    }
}
