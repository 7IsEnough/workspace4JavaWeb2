package com.clearlove.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author promise
 * @date 2022/9/4 - 15:13
 */
public class HelloServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("Hello");

    // 初始化参数
//    this.getInitParameter();
    // Servlet 配置
//    this.getServletConfig();
    // Servlet 上下文
//    this.getServletContext();

    ServletContext context = this.getServletContext();

    // 数据
    String username = "明凯";
    // 将一个数据保存在ServletContext
    // Key: username  Value: 传入的值
    context.setAttribute("username", username);

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doGet(req, resp);
  }
}
