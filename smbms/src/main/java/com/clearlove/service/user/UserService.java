package com.clearlove.service.user;

import com.clearlove.pojo.User;
import java.util.List;

/**
 * @author promise
 * @date 2022/12/13 - 23:23
 */
public interface UserService {

  // 用户登录
  public User login(String userCode, String password);

  // 根据用户id修改密码
  public boolean updatePwd(int id, String password);

  // 查询记录数
  public int getUserCount(String username, int userRole);

  // 通过条件查询userList
  public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize);
}
