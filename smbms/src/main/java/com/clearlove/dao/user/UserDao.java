package com.clearlove.dao.user;

import com.clearlove.pojo.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author promise
 * @date 2022/12/13 - 23:07
 */
public interface UserDao {

  // 得到登录用户
  public User getLoginUser(Connection connection, String userCode) throws SQLException;

  // 修改用户密码
  public int updatePwd(Connection connection, int id, String password) throws SQLException;

  // 根据用户名或角色查询用户总数
  public int getUserCount(Connection connection, String username, int userRole) throws SQLException;

  // 通过条件查询userList
  public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize)throws Exception;
}
