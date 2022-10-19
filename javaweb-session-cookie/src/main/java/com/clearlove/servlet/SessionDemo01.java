package com.clearlove.servlet;

import com.clearlove.pojo.Person;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author promise
 * @date 2022/10/17 - 22:37
 */
public class SessionDemo01 extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    // 解决乱码问题
    req.setCharacterEncoding("utf-8");
    resp.setCharacterEncoding("utf-8");
    resp.setContentType("text/html;charset=utf-8");

    // 得到Session
    HttpSession session = req.getSession();

    // 给Session中存数据
    session.setAttribute("name", new Person("明凯", 1));

    // 获取Session的id
    String sessionId = session.getId();

    // 判断Session是不是新创建的
    if (session.isNew()) {
      resp.getWriter().write("session创建成功，ID：" + sessionId);
    } else {
      resp.getWriter().write("session已经在服务器中存在了，ID：" + sessionId);
    }

    // Session创建的时候做了什么事情
//    Cookie cookie = new Cookie("JSESSIONID", sessionId);
//    resp.addCookie(cookie);


  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    super.doPost(req, resp);
  }
}
