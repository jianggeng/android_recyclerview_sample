package com.jgeng.recyclerviewsample.listview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.jgeng.recyclerviewsample.R;
import com.jgeng.recyclerviewsample.data.UserTableHelper;
import com.jgeng.recyclerviewsample.data.User;

/**
 * Created by jgeng on 8/27/16.
 */

public class LVAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
  private static final String TAG = "LVAdapter";

  UserTableHelper mDBHelper;

  public LVAdapter(UserTableHelper provider) {
    mDBHelper = provider;
  }
  @Override
  public int getCount() {
    return mDBHelper.getCount();
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public User getItem(int position) {
    return mDBHelper.getItem(position);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View view;
    LVViewHolder viewHolder;
    if(convertView != null) {
      view = convertView;
      viewHolder = (LVViewHolder)convertView.getTag();
    } else {
      view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_lv, parent, false);
      viewHolder = new LVViewHolder(view);
      view.setTag(viewHolder);
    }
    viewHolder.bind(getItem(position));

    return view;
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    LVViewHolder viewHolder = (LVViewHolder)view.getTag();
    int status = viewHolder.getUser().status() == 0 ? 1:0;
    mDBHelper.updateStatus(viewHolder.getUser().userId(), status);
    notifyDataSetChanged();
  }
}
