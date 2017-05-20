package com.example.administrator.zxg.ui.ContactListNoBug;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.zxg.R;

public class FriendsAdapter extends BaseAdapter {

	List<Friend> friends;
	Context context;
	public FriendsAdapter(Context context, List<Friend> friends) {
		this.friends = friends;
		this.context = context;
	}

	@Override
	public int getCount() {
		return friends.size();
	}

	@Override
	public Object getItem(int position) {
		return friends.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if(convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.item_user_friend, null);
			viewHolder = new ViewHolder();
			viewHolder.name = (TextView) convertView.findViewById(R.id.tv_friend_name);
			viewHolder.alpha = (TextView) convertView.findViewById(R.id.alpha);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		Friend friend = friends.get(position);
		viewHolder.name.setText(friend.getName());
		
		// ����position��ȡ���������ĸ��Char asciiֵ
		int section = getSectionForPosition(position);
		// �����ǰλ�õ��ڸ÷�������ĸ��Char��λ�� ������Ϊ�ǵ�һ�γ���
		if (position == getPositionForSection(section)) {
			viewHolder.alpha.setVisibility(View.VISIBLE);
			viewHolder.alpha.setText(friend.getSortLetters());
		} else {
			viewHolder.alpha.setVisibility(View.GONE);
		}
		return convertView;
	}
	
	static class ViewHolder {
		TextView name;
		TextView alpha;;
	}

	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = friends.get(i).getSortLetters();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (section == firstChar) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * ����ListView�ĵ�ǰλ�û�ȡ���������ĸ��Char asciiֵ
	 */
	private int getSectionForPosition(int position) {
		return friends.get(position).getSortLetters().charAt(0);
	}
}
