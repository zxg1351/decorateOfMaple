package com.example.administrator.zxg.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.zxg.R;
import com.example.administrator.zxg.common.CommonActivity;

/**
 * @Description
 * @Author bayonet1351
 * Created 2017/5/15 10:50
 */

public class EmptyListActivity extends CommonActivity implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        ListView list= (ListView)findViewById(R.id.list_view);
        list.getLastVisiblePosition();
        list.getFirstVisiblePosition();
        TextView tv= (TextView)findViewById(R.id.empty_list_view);
        list.setEmptyView(tv);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
