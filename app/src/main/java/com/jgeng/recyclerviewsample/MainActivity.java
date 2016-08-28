package com.jgeng.recyclerviewsample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.jgeng.recyclerviewsample.listview.ListViewFragment;
import com.jgeng.recyclerviewsample.recyclerview.RecyclerViewFragment;

public class MainActivity extends AppCompatActivity {
  Fragment mCurrentFragment;
  private static final String FragmentTag = "ListView.Fragment";
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//      getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//      getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
//    }
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    switchToRecyclerViewNormal();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    switch (id) {
      case R.id.action_recycler_view:
        item.setChecked(true);
        switchToRecyclerViewNormal();
        return true;
      case R.id.action_recycler_view_2:
        switchToRecyclerViewDecoration();
        item.setChecked(true);
        return true;
      case R.id.action_list_view:
        switchToListView();
        item.setChecked(true);
        return true;

      default:
        break;
    }

    return super.onOptionsItemSelected(item);
  }

  private void switchToRecyclerViewNormal() {
    setTitle("RecyclerVeiw 1");
    if(mCurrentFragment instanceof RecyclerViewFragment) {
      ((RecyclerViewFragment)mCurrentFragment).switchStyle(RecyclerViewFragment.Style.Normal);
    } else {
      mCurrentFragment = RecyclerViewFragment.newInstance(RecyclerViewFragment.Style.Normal);
      getSupportFragmentManager().
          beginTransaction().
          replace(R.id.fragment_container, mCurrentFragment, FragmentTag)
          .commit();
    }
  }

  private void switchToRecyclerViewDecoration() {
    setTitle("RecyclerVeiw 2");
    if(mCurrentFragment instanceof RecyclerViewFragment) {
      ((RecyclerViewFragment)mCurrentFragment).switchStyle(RecyclerViewFragment.Style.ItemDecoration);
    } else {
      mCurrentFragment = RecyclerViewFragment.newInstance(RecyclerViewFragment.Style.ItemDecoration);
      getSupportFragmentManager().
          beginTransaction().
          replace(R.id.fragment_container, mCurrentFragment, FragmentTag).
          commit();
    }
  }

  private void switchToListView() {
    setTitle("ListView");
    if(!(mCurrentFragment instanceof ListViewFragment)) {
      mCurrentFragment = ListViewFragment.newInstance();
      getSupportFragmentManager().
          beginTransaction().
          replace(R.id.fragment_container, mCurrentFragment, FragmentTag).
          commit();
    }
  }
}
