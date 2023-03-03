package com.clearlove.servlet;

import com.clearlove.pojo.User;
import com.clearlove.utils.SendEmail;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author promise
 * @date 2023/3/1 - 22:15
 */
public class RegisterServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    // 接受用户请求，封装成对象
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    String email = req.getParameter("email");

    System.out.println(username);
    System.out.println(password);
    System.out.println(email);

    User user = new User(username, password, email);

    // 用户注册成功后，给用户发送一封邮件
    // 使用线程来专门发送邮件，防止出现耗时，和网站注册人数过多的情况
    SendEmail send = new SendEmail(user);

    send.start();

    // 注册用户
    req.setAttribute("message", "注册成功，我们已经发了一封带了注册信息的电子邮件，请查收！");
    req.getRequestDispatcher("info.jsp").forward(req,resp);


  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    super.doGet(req, resp);
  }
}
