package com.clearlove.service.role;

import com.clearlove.dao.BaseDao;
import com.clearlove.dao.role.RoleDao;
import com.clearlove.pojo.Role;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author promise
 * @date 2023/2/2 - 20:44
 */
public class RoleServiceImpl implements RoleService {

  private RoleDao roleDao;

  public RoleServiceImpl() {
  }

  @Override
  public List<Role> getRoleList() {
    Connection connection = null;
    List<Role> roleList = null;
    try {
      connection = BaseDao.getConnection();
      roleList = roleDao.getRoleList(connection);
    } catch (SQLException e) {
      e.printStackTrace();
    }finally {
      BaseDao.closeResource(connection,null, null);
    }
    return roleList;
  }
}
