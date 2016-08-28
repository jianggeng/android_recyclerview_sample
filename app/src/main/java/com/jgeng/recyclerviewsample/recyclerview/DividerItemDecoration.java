package com.jgeng.recyclerviewsample.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by jgeng on 8/16/16.
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {

  private Drawable mDivider;
  private int mTopMargin;

  public DividerItemDecoration(Context context, int drawableResId, int topMargin) {
    mDivider = context.getResources().getDrawable(drawableResId);
    mTopMargin = topMargin;

  }

  @Override
  public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
    int left = parent.getPaddingLeft();
    int right = parent.getWidth() - parent.getPaddingRight();

    int childCount = parent.getChildCount();
    for (int i = 0; i < childCount; i++) {
      View child = parent.getChildAt(i);
      if (shouldDrawForItem(parent.getChildViewHolder(child))) {
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

        int top = child.getBottom() + params.bottomMargin + mTopMargin;
        int bottom = top + mDivider.getIntrinsicHeight();

        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(c);
      }
    }
  }

  /**
   * Returns true if divider should be drawn for this item.
   */
  protected boolean shouldDrawForItem(RecyclerView.ViewHolder vh) {
    return true;
  }
}
