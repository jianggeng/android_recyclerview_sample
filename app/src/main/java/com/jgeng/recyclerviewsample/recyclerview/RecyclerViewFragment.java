package com.jgeng.recyclerviewsample.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jgeng.recyclerviewsample.R;
import com.jgeng.recyclerviewsample.data.UserTableHelper;

/**
 * Created by jgeng on 8/16/16.
 */

public class RecyclerViewFragment extends Fragment {
  public static final String TAG = "RecyclerViewFragment";
  public enum Style {
    Normal,
    ItemDecoration,
    GridView
  }

  private class Args {
    private static final String Style = "style";
  }
  static public RecyclerViewFragment newInstance(Style style) {
    Log.i(TAG, "newInstance " + style);
    RecyclerViewFragment instance = new RecyclerViewFragment();
    Bundle args = new Bundle();
    args.putString(Args.Style, style.toString());
    instance.setArguments(args);
    return instance;
  }

  private Style mStyle;
  private RecyclerView.ItemDecoration mDivider;
  private RecyclerView.ItemDecoration mVerticalSpace;
  private RecyclerView.ItemDecoration mBackground;
  private RVAdapter mAdapter;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    RecyclerView view = (RecyclerView)inflater.inflate(R.layout.fragment_recycler_view, container,false);
    Bundle args = getArguments();
    Style style = Style.valueOf(args.getString(Args.Style));

    mAdapter = new RVAdapter(new UserTableHelper(inflater.getContext()), style);
    view.setAdapter(mAdapter);
    mAdapter.setOnItemClickListener(new RVAdapter.OnItemClickListener() {
      @Override
      public void onItemClick(RecyclerView parent, View view, int position, long id) {
        Toast.makeText(parent.getContext(), "item clicked " + position + " " + id, Toast.LENGTH_SHORT).show();
      }
    });
    initViewForStyle(view, style);
    return view;
  }

  private void addDivider(RecyclerView view) {
    if (mDivider == null) {
      mDivider = new DividerItemDecoration(getContext(), R.drawable.recycler_view_divider,
          view.getContext().getResources().getDimensionPixelSize(R.dimen.vertical_margin));
      view.addItemDecoration(mDivider);
    }
  }

  private void removeDivider(RecyclerView view) {
    if (mDivider != null) {
      view.removeItemDecoration(mDivider);
      mDivider = null;
    }
  }

  private void addBackground(RecyclerView view) {
    if (mBackground == null) {
      mBackground = new BackgroundItemDecoration(view.getContext(),
          R.drawable.selectable_background);
      view.addItemDecoration(mBackground);
    }
  }

  private void removeBackground(RecyclerView view) {
    if (mBackground != null) {
      view.removeItemDecoration(mBackground);
      mBackground = null;
    }
  }

  private void addVerticalSpace(RecyclerView view) {
    if (mVerticalSpace == null) {
      mVerticalSpace = new VerticalSpaceItemDecoration(view.getContext().getResources().getDimensionPixelSize(R.dimen.vertical_margin));
      view.addItemDecoration(mVerticalSpace);
    }
  }

  private void removeVerticalSpace(RecyclerView view) {
    if(mVerticalSpace != null) {
      view.removeItemDecoration(mVerticalSpace);
      mVerticalSpace = null;
    }
  }
  private void initViewForStyle(RecyclerView view, Style style) {
    switch (style) {
      case Normal: {
        LinearLayoutManager lm = new LinearLayoutManager(view.getContext());
        view.setLayoutManager(lm);
        removeDivider(view);
        removeBackground(view);
        removeVerticalSpace(view);
      }
        break;
      case ItemDecoration: {
        LinearLayoutManager lm = new LinearLayoutManager(view.getContext());
        view.setLayoutManager(lm);
        addDivider(view);
        addBackground(view);
        addVerticalSpace(view);
      }
        break;
      case GridView:
        GridLayoutManager lm = new GridLayoutManager(view.getContext(), 3);
        view.setLayoutManager(lm);
        lm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
          @Override
          public int getSpanSize(int position) {
            if(position == 0) return 3;
            else return 1;
          }
        });
        addBackground(view);
        addVerticalSpace(view);
        removeDivider(view);
        break;
      default:
        Log.e(TAG, "unknow style " + style);
        break;
    }
  }

  public void switchStyle(Style style) {
    if(mStyle != style) {
      mStyle = style;
      RecyclerView view = (RecyclerView)getView();
      initViewForStyle(view, style);
      ((RVAdapter)view.getAdapter()).switchStyle(style);
    }
  }
}
