package com.jgeng.recyclerviewsample.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.View;

import com.jgeng.recyclerviewsample.R;

/**
 * Created by jgeng on 8/27/16.
 */

public class BackgroundItemDecoration extends RecyclerView.ItemDecoration {

  private Drawable mBackground;
  public BackgroundItemDecoration(Context context, int drawableResId) {
    mBackground = context.getResources().getDrawable(drawableResId);
  }

  @Override
  public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

    int childCount = parent.getChildCount();
    for (int i = 0; i < childCount; i++) {
      View child = parent.getChildAt(i);
      RecyclerView.LayoutManager lm = parent.getLayoutManager();
      Rect rect = new Rect(child.getLeft() - lm.getLeftDecorationWidth(child),
                            child.getTop() - lm.getTopDecorationHeight(child),
                            child.getRight() + lm.getRightDecorationWidth(child),
                            child.getBottom() + lm.getBottomDecorationHeight(child));
      RVAdapter adapter = (RVAdapter) parent.getAdapter();
      if (adapter.isSelected(parent.getChildViewHolder(child))) {
        Paint p = new Paint();
        p.setColor(parent.getContext().getResources().getColor(R.color.colorAccent));
        p.setStyle(Paint.Style.FILL);
        c.drawRect(rect,p);
      } else {
        Paint p = new Paint();
        p.setColor(parent.getContext().getResources().getColor(R.color.colorPrimaryDark));
        p.setAlpha(50);
        p.setStyle(Paint.Style.FILL);
        c.drawRect(rect,p);
      }
    }
  }
}
