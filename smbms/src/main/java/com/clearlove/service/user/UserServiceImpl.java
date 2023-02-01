package com.clearlove.service.user;

import com.clearlove.dao.BaseDao;
import com.clearlove.dao.user.UserDao;
import com.clearlove.dao.user.UserDaoImpl;
import com.clearlove.pojo.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
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

  @Override
  public boolean updatePwd(int id, String password) {
    Connection connection = null;
    boolean flag = false;
    try {
      connection= BaseDao.getConnection();
      if (userDao.updatePwd(connection, id, password) > 0) {
        flag = true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      BaseDao.closeResource(connection, null, null);
    }
    return flag;
  }

  @Override
  public int getUserCount(String username, int userRole) {
    Connection connection = null;
    int count = 0;

    try {
      connection = BaseDao.getConnection();
      count = userDao.getUserCount(connection, username, userRole);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      BaseDao.closeResource(connection, null, null);
    }

    return count;

  }

  @Override
  public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize) {
    // TODO Auto-generated method stub
    Connection connection = null;
    List<User> userList = null;
    System.out.println("queryUserName ---- > " + queryUserName);
    System.out.println("queryUserRole ---- > " + queryUserRole);
    System.out.println("currentPageNo ---- > " + currentPageNo);
    System.out.println("pageSize ---- > " + pageSize);
    try {
      connection = BaseDao.getConnection();
      userList = userDao.getUserList(connection, queryUserName,queryUserRole,currentPageNo,pageSize);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }finally{
      BaseDao.closeResource(connection, null, null);
    }
    return userList;
  }




  @Test
  public void test() {
    UserServiceImpl userService = new UserServiceImpl();
//    User admin = userService.login("admin", "1234567aa");
//    System.out.println(admin.getUserPassword());
    int count = userService.getUserCount(null, 0);
    System.out.println(count);
  }
}
