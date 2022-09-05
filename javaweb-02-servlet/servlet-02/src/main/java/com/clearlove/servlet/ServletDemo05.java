package com.clearlove.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author promise
 * @date 2022/9/5 - 23:43
 */
public class ServletDemo05 extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    InputStream is = this.getServletContext()
        .getResourceAsStream("/WEB-INF/classes/com/clearlove/servlet/aa.properties");
    Properties prop = new Properties();
    prop.load(is);
    String username = prop.getProperty("username");
    String password = prop.getProperty("password");

    resp.getWriter().print(username + ":" + password);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doGet(req, resp);
  }
}
