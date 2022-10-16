package com.clearlove.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author promise
 * @date 2022/10/12 - 22:33
 * 保存用户上一次访问的时间
 */
public class CookieDemo02 extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    // 创建一个cookie，名字必须要和删除的名字一致
    Cookie cookie = new Cookie("lastLoginTime", System.currentTimeMillis() + "");

    // 将cookie有效期设置为0，立马过期
    cookie.setMaxAge(0);

    resp.addCookie(cookie);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    super.doPost(req, resp);
  }
}
