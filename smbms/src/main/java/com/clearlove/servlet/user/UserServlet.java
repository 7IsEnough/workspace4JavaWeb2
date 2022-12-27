package com.clearlove.servlet.user;

import com.clearlove.pojo.User;
import com.clearlove.service.user.UserService;
import com.clearlove.service.user.UserServiceImpl;
import com.clearlove.util.Constants;
import com.mysql.cj.util.StringUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author promise
 * @date 2022/12/27 - 23:11
 */
public class UserServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // 从session里面拿id
    Object o = req.getSession().getAttribute(Constants.USER_SESSION);
    String newpassword = req.getParameter("newpassword");
    boolean flag = false;


    if (o != null && !StringUtils.isNullOrEmpty(newpassword)) {
      UserService userService = new UserServiceImpl();
      flag = userService.updatePwd(((User)o).getId(), newpassword);
      if (flag) {
        req.setAttribute("message", "修改密码成功，请退出，使用新密码登录");
        // 密码修改成功，移除当前session
        req.getSession().removeAttribute(Constants.USER_SESSION);
      } else {
        req.setAttribute("message", "密码修改失败");
      }
    } else {
      req.setAttribute("message", "新密码有问题");
    }

    req.getRequestDispatcher("pwdmodify.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doGet(req, resp);
  }
}
