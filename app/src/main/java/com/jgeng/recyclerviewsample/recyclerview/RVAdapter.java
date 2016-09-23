package com.jgeng.recyclerviewsample.recyclerview;

import com.jgeng.recyclerviewsample.R;

import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jgeng.recyclerviewsample.data.DataProvider;
import com.jgeng.recyclerviewsample.data.User;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jgeng on 8/16/16.
 */

public class RVAdapter extends RecyclerView.Adapter<RVViewHolder> {
  private static final String TAG = "RVAdapter";

  @IntDef({ViewType.HEADER, ViewType.NORMAL, ViewType.ITEM_DECORATION, ViewType.Grid})
  @interface ViewType {
    int HEADER = 0;
    int NORMAL = 1;
    int ITEM_DECORATION = 2;
    int Grid = 3;
  }

  public interface OnItemClickListener {
    void onItemClick(RecyclerView parent, View view, int position, long id);
  }

  OnItemClickListener mOnItemClickListener;
  DataProvider mProvider;
  public @ViewType  int mContentViewType;
  public RVAdapter(DataProvider provider, RecyclerViewFragment.Style style) {
    mProvider = provider;
    mContentViewType = styleToViewType(style);
  }
  @Override
  public int getItemCount() {
    return mProvider.getCount() + 1;
  }

  @Override
  public long getItemId(int position) {
    return position - 1;
  }

  private User getItem(int position) {
    if(position == 0) return null;
    return mProvider.getItem(position -1);
  }

  private void select(int position) {
    if(position == 0) return;
    mProvider.select(getItem(position).userId());
  }

  private void unselect(int position) {
    if(position == 0) return;
    mProvider.unselect(getItem(position).userId());
  }

  public boolean isSelected(int position) {
    if(position == 0) return false;
    return mProvider.isSelected(getItem(position).userId());
  }

  @Override
  public @ViewType int getItemViewType(int position) {
    if( position == 0) return ViewType.HEADER;
    else return mContentViewType;
  }
  @Override
  public RVViewHolder onCreateViewHolder(ViewGroup parent, @ViewType int viewType) {
    Log.i(TAG, "onCreateViewHolder " + viewType);
    View view;
    switch (viewType) {
      case ViewType.HEADER:
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_view, parent, false);
        break;
      default:
        Log.e(TAG, "Unknow viewType " + viewType);
      case ViewType.NORMAL:
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        break;
      case ViewType.ITEM_DECORATION:
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_2, parent, false);
        break;
      case ViewType.Grid:
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        break;

    }
    RVViewHolder viewHolder = new RVViewHolder(view,mInnerListener);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(RVViewHolder holder, int position) {
    Log.i(TAG, "onBindViewHolder " + position);
    if (position == 0) return;
    holder.bind(getItem(position));
    if (holder.getItemViewType() == ViewType.NORMAL) {
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
      Log.i(TAG, "onItemClick " + viewHolder.getAdapterPosition());
      int position = viewHolder.getAdapterPosition();
      if (position > 0) {
        if (isSelected(position)) {
          unselect(position);
        } else {
          select(position);
        }
        notifyItemChanged(position);
      }
      if(mOnItemClickListener != null) {
        mOnItemClickListener.onItemClick((RecyclerView)viewHolder.itemView.getParent(), viewHolder.itemView, viewHolder.getAdapterPosition(), viewHolder.getItemId());
      }
    }
  };

  private @ViewType  int styleToViewType(RecyclerViewFragment.Style style) {
    switch (style) {
      default:
      case Normal:
        return ViewType.NORMAL;
      case ItemDecoration:
        return ViewType.ITEM_DECORATION;
      case GridView:
        return ViewType.Grid;
    }
  }



  public void switchStyle(RecyclerViewFragment.Style style) {
    int viewType = styleToViewType(style);
    if(viewType != mContentViewType) {
      mContentViewType = viewType;
      notifyDataSetChanged();
    }
  }
}
