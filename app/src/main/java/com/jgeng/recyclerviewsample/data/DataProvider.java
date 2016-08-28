package com.jgeng.recyclerviewsample.data;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jgeng on 8/16/16.
 */

public class DataProvider {
  private static final int COUNT = 100;
  private User[] mUsers;
  Set<String> mSelected = new HashSet<>();
  private static DataProvider sInstance;
  public static DataProvider getInstance() {
    if (sInstance == null) sInstance = new DataProvider();
    return sInstance;
  }
  
  private DataProvider() {
    mUsers = new User[COUNT];
    for(int i=0;i<COUNT;i++) {
      mUsers[i] = new User("id" + i, "User" +i, "Fairchild 10"+i);
    }
  }
  public int getCount() {
    return COUNT;
  }

  public User getItem(int position) {
    return mUsers[position];
  }

  public void select(String userId) {
    mSelected.add(userId);
  }

  public void unselect(String userId) {
    mSelected.remove(userId);
  }

  public boolean isSelected(String userId) {
    return mSelected.contains(userId);
  }
}
