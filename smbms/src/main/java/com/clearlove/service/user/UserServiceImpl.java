package com.clearlove.service.user;

import com.clearlove.dao.BaseDao;
import com.clearlove.dao.user.UserDao;
import com.clearlove.dao.user.UserDaoImpl;
import com.clearlove.pojo.User;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

/**
 * @author promise
 * @date 2022/12/13 - 23:24
 */
public class UserServiceImpl implements UserService {

  // 业务层都会调用Dao层，所以我们要引入Dao层
  private UserDao userDao;

  public UserServiceImpl() {
    userDao = new UserDaoImpl();
  }

  @Override
  public User login(String userCode, String password) {
    Connection connection = null;
    User user = null;

    try {
      connection = BaseDao.getConnection();
      // 通过业务层调用对应的具体的数据库操作
      user = userDao.getLoginUser(connection, userCode);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      BaseDao.closeResource(connection, null, null);
    }
    return user;
  }

  @Test
  public void test() {
    UserServiceImpl userService = new UserServiceImpl();
    User admin = userService.login("admin", "1234567aa");
    System.out.println(admin.getUserPassword());
  }
}
