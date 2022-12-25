package com.clearlove.servlet.user;

import com.clearlove.pojo.User;
import com.clearlove.service.user.UserService;
import com.clearlove.service.user.UserServiceImpl;
import com.clearlove.util.Constants;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author promise
 * @date 2022/12/25 - 22:05
 */
public class LoginServlet extends HttpServlet {

  // Servlet: 控制层，调用业务层代码

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("LoginServlet--start....");

    // 获取用户名和密码
    String userCode = req.getParameter("userCode");
    String userPassword = req.getParameter("userPassword");

    // 和数据库的密码进行对比，调用业务层
    UserService userService = new UserServiceImpl();
    // 这里已经把登录的人查出来了
    User user = userService.login(userCode, userPassword);

    if (user != null) {
      // 查有此人，可以登录
      // 将用户的信息放到Session中
      req.getSession().setAttribute(Constants.USER_SESSION, user);

      // 跳转到内部主页
      resp.sendRedirect("jsp/frame.jsp");

    } else {
      // 查无此人，无法登录
      // 转发回登录页面，提示，用户名或密码错误
      req.setAttribute("error", "用户名或密码不正确");
      req.getRequestDispatcher("login.jsp").forward(req,resp);
    }


  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doGet(req, resp);
  }
}
