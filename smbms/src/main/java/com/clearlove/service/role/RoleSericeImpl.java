package com.clearlove.service.role;

import com.clearlove.dao.role.RoleDao;
import com.clearlove.dao.role.RoleDaoImpl;
import com.clearlove.pojo.Role;
import java.util.List;

/**
 * @author promise
 * @date 2023/2/2 - 20:44
 */
public class RoleSericeImpl implements RoleService {

  private RoleDao roleDao;

  public RoleSericeImpl(RoleDao roleDao) {
    roleDao = new RoleDaoImpl();
  }

  @Override
  public List<Role> getRoleList() {
    return null;
  }
}
