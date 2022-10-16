package com.clearlove.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author promise
 * @date 2022/10/16 - 17:15
 * 中文数据传递
 */
public class CookieDemo03 extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    // 解决中文乱码
    req.setCharacterEncoding("utf-8");
    resp.setCharacterEncoding("utf-8");
    resp.setContentType("text/html");

    Cookie[] cookies = req.getCookies();

    PrintWriter out = resp.getWriter();

    // 判断Cookie是否存在
    if (cookies != null) {
      // 如果存在
      out.write("你上次访问的时间是：");
      for (int i = 0; i < cookies.length; i++) {
        Cookie cookie = cookies[i];
        // 获取cookie的名字
        if (cookie.getName().equals("name")) {
          // 获取Cookie中的值
          // 解码
          out.write(URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8));
        }
      }

    } else {
      out.write("这是您第一次访问本站");
    }
    // 编码
    Cookie cookie = new Cookie("name", URLEncoder.encode("明凯", StandardCharsets.UTF_8));
    resp.addCookie(cookie);

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doGet(req,resp);
  }
}
