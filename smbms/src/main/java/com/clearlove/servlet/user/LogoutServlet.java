package com.clearlove.servlet.user;

import com.clearlove.util.Constants;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author promise
 * @date 2022/12/26 - 23:00
 */
public class LogoutServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    // 移除用户的session
    req.getSession().removeAttribute(Constants.USER_SESSION);
    // 返回登录页面
    resp.sendRedirect(req.getContextPath() + "/login.jsp");

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doGet(req, resp);
  }
}
