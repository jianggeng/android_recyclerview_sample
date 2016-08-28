package com.jgeng.recyclerviewsample.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jgeng.recyclerviewsample.R;
import com.jgeng.recyclerviewsample.ViewHolderImpl;
import com.jgeng.recyclerviewsample.data.User;

/**
 * Created by jgeng on 8/16/16.
 */

public class RVViewHolder extends RecyclerView.ViewHolder {
  public interface OnItemClickListener {
    void onItemClick(RVViewHolder viewHolder);
  }

  private ViewHolderImpl mImpl;
  private OnItemClickListener mOnItemClickListener;

  public RVViewHolder(View itemView, OnItemClickListener onItemClickListener) {
    super(itemView);
    mImpl = new ViewHolderImpl(itemView);
    mOnItemClickListener = onItemClickListener;
    if(mOnItemClickListener != null) {
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(RVViewHolder.this);
          }
        }
      });
    }
  }

  public void bind(User user) {
    mImpl.bind(user);
  }

  public User getUser() {
    return mImpl.getUser();
  }
}
