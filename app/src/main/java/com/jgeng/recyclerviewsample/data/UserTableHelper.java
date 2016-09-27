package com.jgeng.recyclerviewsample.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.nio.channels.FileChannel;
//import java.util.HashSet;
//import java.util.Set;

/**
 * Created by jgeng on 8/16/16.
 */

public class UserTableHelper {
  Context mContext;
  Cursor mCursor;
  
  public UserTableHelper(Context context) {
    mContext = context;
    refresh();
  }

  public int getCount() {
    if (mCursor != null) {
      return mCursor.getCount();
    }
    return 0;
  }

  public User getItem(int position) {
    if (!mCursor.moveToPosition(position)) {
      throw new IllegalStateException("couldn't move cursor to position " + position);
    }
    return User.MAPPER.map(mCursor);
  }

//TODO: use CursorLoader
  private void refresh() {
    SQLiteDatabase db = SqLiteHelper.getInstance(mContext).getReadableDatabase();
    mCursor = db.rawQuery(User.SELECT_ALL, new String[0]);
  }

  public void updateStatus(String userId, long status) {
    SQLiteDatabase db = SqLiteHelper.getInstance(mContext).getWritableDatabase();
    int row = db.update(User.TABLE_NAME,
        User.FACTORY.marshal().status(status).asContentValues(),
        User.USERID + "=?",
        new String[]{userId});
    db.close();
    refresh();
  }
//    File src = new File(db.getPath());
//    File dst = new File(mContext.getExternalCacheDir().getPath()+"user.db");
//    try {
//      copy(src, dst);
//    } catch (Exception e) {
//      Log.e(TAG, e.toString());
//    }
//  }

//  private void copy(File src, File dst) throws IOException {
//    Log.v(TAG, "copy " + src.getPath() + " to " + dst.getPath());
//    FileInputStream inStream = new FileInputStream(src);
//    FileOutputStream outStream = new FileOutputStream(dst);
//    FileChannel inChannel = inStream.getChannel();
//    FileChannel outChannel = outStream.getChannel();
//    inChannel.transferTo(0, inChannel.size(), outChannel);
//    inStream.close();
//    outStream.close();
//  }
}
