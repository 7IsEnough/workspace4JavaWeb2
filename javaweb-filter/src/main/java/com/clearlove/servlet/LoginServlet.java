package com.clearlove.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author promise
 * @date 2022/11/27 - 22:16
 */
public class LoginServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // 获取前端请求的参数
    String username = req.getParameter("username");
    if ("admin".equals(username)) {
      // 登录成功
      req.getSession().setAttribute("USER_SESSION", req.getSession().getId());
      resp.sendRedirect(req.getContextPath() + "/sys/success.jsp");
    } else {
      // 登录失败
      resp.sendRedirect(req.getContextPath() + "/error.jsp");
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doGet(req, resp);
  }
}
