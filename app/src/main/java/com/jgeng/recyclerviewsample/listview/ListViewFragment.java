package com.jgeng.recyclerviewsample.listview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jgeng.recyclerviewsample.R;
import com.jgeng.recyclerviewsample.data.DataProvider;

/**
 * Created by jgeng on 8/16/16.
 */

public class ListViewFragment extends Fragment {
  public static final String TAG = "ListViewFragment";

  static public ListViewFragment newInstance() {
    Log.i(TAG, "newInstance ");
    ListViewFragment instance = new ListViewFragment();
    return instance;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    ListView view = (ListView)inflater.inflate(R.layout.fragment_list_view, container,false);

    final LVAdapter adapter = new LVAdapter(DataProvider.getInstance());
    view.setAdapter(adapter);
    view.setOnItemClickListener(adapter);
    return view;
  }
}
