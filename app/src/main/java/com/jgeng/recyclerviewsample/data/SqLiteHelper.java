package com.jgeng.recyclerviewsample.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.jgeng.recyclerviewsample.Module;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by jgeng on 9/23/16.
 */

public class SqLiteHelper extends SQLiteOpenHelper {
  private static final String TAG = Module.DATA_BASE;
  private static final int COUNT = 100;
  private static final int DATABASE_VERSION = 1;
  private static SqLiteHelper instance;
  private Context mContext;
  public static SqLiteHelper getInstance(Context context) {
    if (instance == null) {
      instance = new SqLiteHelper(context);
    }
    return instance;
  }

  public SqLiteHelper(Context context) {
    super(context, "user", null, DATABASE_VERSION);
    mContext = context;
  }

  @Override public void onCreate(SQLiteDatabase db) {
    Log.i(TAG, "onCreate: " + db.getPath());
    db.execSQL(User.CREATE_TABLE);
    for(int i=0;i<COUNT;i++) {
      db.insert(User.TABLE_NAME,null, User.FACTORY.marshal().
          userId("id" + i).
          name("User" +i).
          address("Fairchild 10"+i).
          status(0).asContentValues());
    }
  }

  @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  }
}
