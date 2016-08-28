package com.jgeng.recyclerviewsample.listview;

import android.view.View;

import com.jgeng.recyclerviewsample.ViewHolderImpl;
import com.jgeng.recyclerviewsample.data.User;

/**
 * Created by jgeng on 8/27/16.
 */

public class LVViewHolder {
  private ViewHolderImpl mImpl;
  public LVViewHolder(View itemView) {
    mImpl = new ViewHolderImpl(itemView);
  }

  public void bind(User user) {
    mImpl.bind(user);
  }

  public User getUser()  {
    return mImpl.getUser();
  }
}
