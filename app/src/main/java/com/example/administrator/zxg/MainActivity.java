package com.example.administrator.zxg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zxg.adapter.HomeAdapter;
import com.example.administrator.zxg.common.CommonActivity;
import com.example.administrator.zxg.util.ListItemDecoration;
import com.example.administrator.zxg.ui.ContactListActivity;
import com.example.administrator.zxg.ui.ThreeActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends CommonActivity {

    private RecyclerView recyclerview;
    private List<String> mData;
    private HomeAdapter mHomeAdapter;
    private TextView tv_title;//标题

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDate();
        tv_title.setText("天涯海角");
        recyclerview = (RecyclerView) findViewById(R.id.id_recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setHasFixedSize(true);
        mHomeAdapter = new HomeAdapter(MainActivity.this,mData);
        recyclerview.addItemDecoration(new ListItemDecoration(this, LinearLayoutManager.VERTICAL));
        mHomeAdapter.setOnItemClickLitener(new HomeAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, ContactListActivity.class);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
//                Toast.makeText(MainActivity.this,"Long",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ThreeActivity.class);
                startActivity(intent);
            }
        });
        recyclerview.setAdapter(mHomeAdapter);
    }

    private void initDate() {
        tv_title= (TextView)  findViewById(R.id.tv_title);
        mData = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mData.add("" + (char) i);
        }
    }

    /**
     * 夜间模式切换
     * @param view
     */
    public void darkandlight(View view){
        Toast.makeText(MainActivity.this,"切换成功",Toast.LENGTH_SHORT).show();

    }

}

