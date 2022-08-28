package com.clearlove.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author promise
 * @date 2022/8/28 - 23:01
 */
public class HelloServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // 响应的类型：html
    resp.setContentType("text/html");
    resp.setCharacterEncoding("utf-8");
    // 获取响应的输出流
    PrintWriter writer = resp.getWriter();
    writer.println("<html>");
    writer.println("<head>");
    writer.println("<title>Hello World!</title>");
    writer.println("</head>");
    writer.println("<body>");
    writer.println("<h1>你好2</h1>");
    writer.println("</body>");
    writer.println("</html>");

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    this.doGet(req,resp);
  }
}
