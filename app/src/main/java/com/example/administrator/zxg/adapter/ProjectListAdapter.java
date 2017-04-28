package com.example.administrator.zxg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.zxg.R;

import java.util.List;

/**
 * Created by Administrator on 2016/10/22.
 */
public class ProjectListAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mData;

    public ProjectListAdapter(Context context, List<String> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return  mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_project, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_project = (TextView) view.findViewById(R.id.tv_project);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }


        viewHolder.tv_project.setText(mData.get(i));

        return view;
    }
    static class ViewHolder {

        private TextView tv_project;
    }
}
