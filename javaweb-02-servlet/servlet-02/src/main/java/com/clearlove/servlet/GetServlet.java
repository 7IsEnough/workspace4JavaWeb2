package com.clearlove.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author promise
 * @date 2022/9/4 - 15:33
 */
public class GetServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    ServletContext context = this.getServletContext();
    String username = (String) context.getAttribute("username");


    resp.setContentType("text/html");
    resp.setCharacterEncoding("utf-8");
    resp.getWriter().print("名字：" + username);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doGet(req, resp);
  }
}
