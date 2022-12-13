package com.clearlove.dao.user;

import com.clearlove.pojo.User;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author promise
 * @date 2022/12/13 - 23:07
 */
public interface UserDao {

  // 得到登录用户
  public User getLoginUser(Connection connection, String userCode) throws SQLException;

}
