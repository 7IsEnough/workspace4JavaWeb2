package com.clearlove.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author promise
 * @date 2022/9/3 - 13:30
 */
public class HelloServlet extends HttpServlet {

  // 由于get或者post只是请求实现的不同的方式，可以相互调用，因为业务逻辑都一样

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("进入了doGet方法");
//    ServletOutputStream outputStream = resp.getOutputStream();
    // 响应流
    PrintWriter writer = resp.getWriter();

    writer.print("Hello, Servlet1");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doGet(req, resp);
  }
}
