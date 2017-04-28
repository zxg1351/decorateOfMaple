package com.example.administrator.zxg.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.zxg.MainActivity;
import com.example.administrator.zxg.R;
import com.example.administrator.zxg.adapter.ProjectListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/22.
 */
public class ProjectListActivity extends Activity {

    private List<String> list = new ArrayList<String>();
    private ListView lv_projectList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);
        initView();
        initData();
    }

    /**
     * 初始化页面
     */
    private void initView() {
        lv_projectList = (ListView) findViewById(R.id.lv_project_list);
    }
    private void initData() {


        for (int i = 0; i <100; i++) {
            list.add(String.valueOf(i));
        }
        ProjectListAdapter adapter = new ProjectListAdapter(ProjectListActivity.this,list);


        lv_projectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =new Intent(ProjectListActivity.this, SystemSetActivity.class);
                startActivity(intent);
                finish();
            }
        });
        lv_projectList.setAdapter(adapter);
    }
}
