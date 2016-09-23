package com.jgeng.recyclerviewsample.data;
//import java.util.Date;
import com.google.auto.value.AutoValue;
/**
 * Created by jgeng on 8/16/16.
 */
@AutoValue
public abstract class User implements UserModel {

  public static final Factory<User> FACTORY = new Factory<>(new Creator<User>() {
    @Override
    public User create(String userId, String name, String address) {
      return new AutoValue_User(userId,name,address);
    }
  });
}