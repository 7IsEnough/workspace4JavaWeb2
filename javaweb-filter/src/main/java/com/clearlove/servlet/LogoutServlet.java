package com.clearlove.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author promise
 * @date 2022/11/27 - 22:51
 */
public class LogoutServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Object user_session = req.getSession().getAttribute("USER_SESSION");

    if (user_session != null) {
      req.getSession().removeAttribute("USER_SESSION");
      resp.sendRedirect(req.getContextPath() + "/Login.jsp");
    } else {
      resp.sendRedirect(req.getContextPath() + "/Login.jsp");
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doGet(req, resp);
  }
}
