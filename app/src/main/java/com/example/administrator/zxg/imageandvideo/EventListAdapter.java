package com.example.administrator.zxg.imageandvideo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.zxg.R;
import com.example.administrator.zxg.common.CommonAdapter;


import java.util.List;

/**
 * 我的事件单  适配器
 * Created by zk on 2017/4/19.
 */
public class EventListAdapter extends CommonAdapter {
    private Context mContext;

    private List<String> mItems;

    private LayoutInflater mLayoutInflater;

    public EventListAdapter(Context context, List<String> items) {

        mContext = context;
        mItems = items;
        mLayoutInflater = LayoutInflater.from(mContext);

    }
    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        if (null != mItems && position < getCount()) {
            return mItems.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (null == convertView) {

            convertView = mLayoutInflater.inflate(R.layout.item_gridview, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_cover = (ImageView) convertView.findViewById(R.id.iv_cover);
            viewHolder.iv_play = (ImageView) convertView.findViewById(R.id.iv_play);
            viewHolder.rl_play = (RelativeLayout) convertView.findViewById(R.id.rl_play);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(mContext).load(mItems.get(position)).asBitmap().into(viewHolder.iv_cover);
        return convertView;
    }

    public static class ViewHolder {

        ImageView iv_cover, iv_play;
        RelativeLayout rl_play;

    }

}
