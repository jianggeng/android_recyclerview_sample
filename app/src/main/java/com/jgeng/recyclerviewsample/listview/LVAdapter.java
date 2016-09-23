package com.jgeng.recyclerviewsample.listview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.jgeng.recyclerviewsample.R;
import com.jgeng.recyclerviewsample.data.DataProvider;
import com.jgeng.recyclerviewsample.data.User;

/**
 * Created by jgeng on 8/27/16.
 */

public class LVAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
  private static final String TAG = "LVAdapter";

  DataProvider mProvider;

  public LVAdapter(DataProvider provider) {
    mProvider = provider;
  }
  @Override
  public int getCount() {
    return mProvider.getCount();
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public User getItem(int position) {
    return mProvider.getItem(position);
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
    if(isSelected(position)) {
      view.setBackgroundResource(R.color.colorAccent);
    } else {
      view.setBackground(null);
    }
    return view;
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    if (isSelected(position)) {
      mProvider.unselect(getItem(position).userId());
    } else {
      mProvider.select(getItem(position).userId());
    }
    notifyDataSetChanged();
  }

  public boolean isSelected(int position) {
    return mProvider.isSelected(getItem(position).userId());
  }
}
