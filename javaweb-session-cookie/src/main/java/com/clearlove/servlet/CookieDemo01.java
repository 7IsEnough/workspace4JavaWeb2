package com.clearlove.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
public class CookieDemo01 extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // 服务器告诉你，你来的时间，把这个时间封装成为一个信件，你下次带来，我就知道你来了

    // 解决中文乱码
    req.setCharacterEncoding("utf-8");
    resp.setCharacterEncoding("utf-8");
    resp.setContentType("text/html");

    PrintWriter out = resp.getWriter();

    //  Cookie，服务器端从客户端获取
    // 这里返回数组，说明Cookie可能存在多个
    Cookie[] cookies = req.getCookies();

    // 判断Cookie是否存在
    if (cookies != null) {
      // 如果存在
      out.write("你上次访问的时间是：");
      for (int i = 0; i < cookies.length; i++) {
        Cookie cookie = cookies[i];
        // 获取cookie的名字
        if (cookie.getName().equals("lastLoginTime")) {
          // 获取Cookie中的值
          long lastLoginTime = Long.parseLong(cookie.getValue());
          Date date = new Date(lastLoginTime);
          out.write(date.toLocaleString());
        }
      }

    } else {
      out.write("这是您第一次访问本站");
    }

    // 服务器给客户端响应一个cookie
    Cookie cookie = new Cookie("lastLoginTime", System.currentTimeMillis() + "");

    // cookie有效期为一天
    cookie.setMaxAge(24*60*60);

    resp.addCookie(cookie);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    super.doPost(req, resp);
  }
}
