package com.jgeng.recyclerviewsample;

import android.view.View;
import android.widget.TextView;

import com.jgeng.recyclerviewsample.data.User;
import com.jgeng.recyclerviewsample.recyclerview.RVViewHolder;

/**
 * Created by jgeng on 8/27/16.
 */

public class ViewHolderImpl {
  private View mItemView;
  private TextView mNameTv;
  private User mUser;
  public ViewHolderImpl(View itemView) {
    mItemView = itemView;
    mNameTv = (TextView)itemView.findViewById(R.id.user_name);
  }

  public void bind(User user) {
    mUser = user;
    mNameTv.setText(mUser.name());
  }

  public User getUser() {
    return mUser;
  }
}
