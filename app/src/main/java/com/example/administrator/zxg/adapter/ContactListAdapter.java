package com.example.administrator.zxg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.zxg.R;
import com.example.administrator.zxg.common.CommonAdapter;
import com.example.administrator.zxg.entity.ContactEntity;
import com.example.administrator.zxg.ui.ContactListActivity;

import java.util.ArrayList;

/**
 * @Description
 * @Author bayonet1351
 * Created 2017/5/10 09:15
 */

public class ContactListAdapter extends CommonAdapter {
    private Context mContext;
    private ArrayList<ContactEntity> mContacts = new ArrayList<ContactEntity>();

    public ContactListAdapter(Context mContext, ArrayList<ContactEntity> mContacts) {
        this.mContext = mContext;
        this.mContacts = mContacts;
    }

    @Override
    public int getCount() {
        if (mContacts != null && mContacts.size() > 0) {
            return mContacts.size();
        } else {
            return 0;
        }

    }

    @Override
    public Object getItem(int i) {

        if (mContacts!=null && mContacts.size()>0){
            return mContacts.get(i);
        }else {

            return null;
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null){
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_contact,null);
            holder.name = (TextView) view.findViewById(R.id.tv_name);
            holder.photo = (ImageView) view
                    .findViewById(R.id.iv_photo);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        ContactEntity contact = mContacts.get(i);
        holder.name.setText(contact.getName()+"");
        holder.photo.setImageBitmap(contact.getPhoto());
        return view;
    }
    class ViewHolder{
        TextView name;
        ImageView photo;
    }
}
