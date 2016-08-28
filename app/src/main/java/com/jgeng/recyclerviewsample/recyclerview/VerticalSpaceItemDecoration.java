package com.jgeng.recyclerviewsample.recyclerview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by jgeng on 8/16/16.
 */

public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {

  private final int mVerticalSpaceHeight;

  public VerticalSpaceItemDecoration(int mVerticalSpaceHeight) {
    this.mVerticalSpaceHeight = mVerticalSpaceHeight;
  }

  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                             RecyclerView.State state) {
    outRect.top = mVerticalSpaceHeight;
    outRect.bottom = mVerticalSpaceHeight;
    outRect.left = 60;
    outRect.right = 60;
  }
}
