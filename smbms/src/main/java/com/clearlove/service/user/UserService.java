package com.clearlove.service.user;

import com.clearlove.pojo.User;

/**
 * @author promise
 * @date 2022/12/13 - 23:23
 */
public interface UserService {

  // 用户登录
  public User login(String userCode, String password);

}
