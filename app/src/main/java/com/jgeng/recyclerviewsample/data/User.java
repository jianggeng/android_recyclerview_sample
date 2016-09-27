package com.jgeng.recyclerviewsample.data;
//import java.util.Date;
import com.google.auto.value.AutoValue;
import com.squareup.sqldelight.RowMapper;

/**
 * Created by jgeng on 8/16/16.
 */
@AutoValue
public abstract class User implements UserModel {

  public static final Factory<User> FACTORY = new Factory<>(new Creator<User>() {
    @Override
    public User create(String userId, String name, String address, long status) {
      return new AutoValue_User(userId,name,address, status);
    }
  });

  public static final RowMapper<User> MAPPER = FACTORY.select_allMapper();
}