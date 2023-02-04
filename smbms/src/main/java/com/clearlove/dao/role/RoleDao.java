package com.clearlove.dao.role;

import com.clearlove.pojo.Role;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author promise
 * @date 2023/2/2 - 20:41
 */
public interface RoleDao {

  // 获取角色列表
  public List<Role> getRoleList(Connection connection) throws SQLException;

}
