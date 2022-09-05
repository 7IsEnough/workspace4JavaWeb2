package com.clearlove.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author promise
 * @date 2022/9/5 - 23:08
 */
public class ServletDemo04 extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    ServletContext context = this.getServletContext();
    System.out.println("进入了ServletDemo04");
    // 转发的请求路径
    RequestDispatcher requestDispatcher = context.getRequestDispatcher("/gp");
    // 调用forward实现请求转发
    requestDispatcher.forward(req, resp);


  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doGet(req, resp);
  }
}
