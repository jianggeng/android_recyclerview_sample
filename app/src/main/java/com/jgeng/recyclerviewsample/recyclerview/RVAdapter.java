package com.jgeng.recyclerviewsample.recyclerview;

import com.jgeng.recyclerviewsample.R;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jgeng.recyclerviewsample.data.DataProvider;
import com.jgeng.recyclerviewsample.data.User;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jgeng on 8/16/16.
 */

public class RVAdapter extends RecyclerView.Adapter<RVViewHolder> {
  private static final String TAG = "RVAdapter";
  public interface OnItemClickListener {
    void onItemClick(RecyclerView parent, View view, int position, long id);
  }

  OnItemClickListener mOnItemClickListener;
  DataProvider mProvider;
  RecyclerViewFragment.Style mStyle;
  public RVAdapter(DataProvider provider, RecyclerViewFragment.Style style) {
    mProvider = provider;
    mStyle = style;
  }
  @Override
  public int getItemCount() {
    return mProvider.getCount();
  }

  @Override
  public int getItemViewType(int position) {
    return mStyle.ordinal();
  }
  @Override
  public RVViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Log.i(TAG, "onCreateViewHolder " + viewType);
    View view;
    switch (mStyle) {
      case ItemDecoration:
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_2, parent, false);
        break;
      default:
        Log.e(TAG, "Unknow style " + mStyle);
      case Normal:
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        break;
    }
    RVViewHolder viewHolder = new RVViewHolder(view,mInnerListener);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(RVViewHolder holder, int position) {
    Log.i(TAG, "onBindViewHolder " + position);
    holder.bind(mProvider.getItem(position));
     if (mStyle == RecyclerViewFragment.Style.Normal) {
       if (isSelected(position)) {
         holder.itemView.setBackgroundResource(R.color.colorAccent);
       } else {
         holder.itemView.setBackgroundResource(R.drawable.selectable_background);
       }
     }
  }

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    mOnItemClickListener = onItemClickListener;
  }

  private RVViewHolder.OnItemClickListener mInnerListener = new RVViewHolder.OnItemClickListener() {
    @Override
    public void onItemClick(RVViewHolder viewHolder) {
      User user = mProvider.getItem(viewHolder.getAdapterPosition());
      if(isSelected(viewHolder.getAdapterPosition())) {
        mProvider.unselect(user.getId());
      } else {
        mProvider.select(user.getId());
      }
      notifyItemChanged(viewHolder.getAdapterPosition());
      if(mOnItemClickListener != null) {
        mOnItemClickListener.onItemClick((RecyclerView)viewHolder.itemView.getParent(), viewHolder.itemView, viewHolder.getAdapterPosition(), viewHolder.getItemId());
      }
    }
  };

  public void switchStyle(RecyclerViewFragment.Style style) {
    if(mStyle != style) {
      mStyle = style;
      notifyDataSetChanged();
    }
  }

  public boolean isSelected(int position) {
    return mProvider.isSelected(mProvider.getItem(position).getId());
  }
}
