package com.clearlove.servlet.user;

import com.alibaba.fastjson.JSONObject;
import com.clearlove.pojo.Role;
import com.clearlove.pojo.User;
import com.clearlove.service.role.RoleServiceImpl;
import com.clearlove.service.user.UserService;
import com.clearlove.service.user.UserServiceImpl;
import com.clearlove.util.Constants;
import com.clearlove.util.PageSupport;
import com.mysql.cj.util.StringUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author promise
 * @date 2022/12/27 - 23:11
 */
public class UserServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String method = req.getParameter("method");
    if ("savepwd".equals(method) && method != null) {
      updatePwd(req, resp);
    } else if ("pwdmodify".equals(method) && method != null) {
      pwdModify(req, resp);
    } else if ("query".equals(method) && method != null) {
      query(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doGet(req, resp);
  }


  // 修改密码
  public void updatePwd(HttpServletRequest req, HttpServletResponse resp) {
    // 从session里面拿id
    Object o = req.getSession().getAttribute(Constants.USER_SESSION);
    String newpassword = req.getParameter("newpassword");

    boolean flag = false;

    if (o != null && !StringUtils.isNullOrEmpty(newpassword)) {
      UserService userService = new UserServiceImpl();
      flag = userService.updatePwd(((User) o).getId(), newpassword);
      if (flag) {
        req.setAttribute("message", "修改密码成功，请退出，使用新密码登录");
        // 密码修改成功，移除当前session
        req.getSession().removeAttribute(Constants.USER_SESSION);
      } else {
        req.setAttribute("message", "密码修改失败");
      }
    } else {
      req.setAttribute("message", "新密码有问题");
    }

    try {
      req.getRequestDispatcher("jsp/pwdmodify.jsp").forward(req, resp);
    } catch (ServletException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // 验证旧密码，session中有用户的旧密码
  public void pwdModify(HttpServletRequest req, HttpServletResponse resp) {
    // 从session里面拿id
    Object o = req.getSession().getAttribute(Constants.USER_SESSION);
    String oldpassword = req.getParameter("oldpassword");

    Map<String, String> resultMap = new HashMap<>();

    // session失效，过期了
    if (o == null) {
      resultMap.put("result", "sessionerror");
    } else if (StringUtils.isNullOrEmpty(oldpassword)) {
      // 输入的密码为空
      resultMap.put("result", "error");
    } else {
      // session中用户的密码
      String userPassword = ((User) o).getUserPassword();
      if (oldpassword.equals(userPassword)) {
        resultMap.put("result", "true");
      } else {
        resultMap.put("result", "false");
      }
    }
    try {
      resp.setContentType("application/json");
      PrintWriter writer = resp.getWriter();
      writer.write(JSONObject.toJSONString(resultMap));
      writer.flush();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void query(HttpServletRequest req, HttpServletResponse resp) {

    // 查询用户列表

    // 从前端获取数据
    String queryUserName = req.getParameter("queryName");
    String temp = req.getParameter("queryUserRole");
    String pageIndex = req.getParameter("pageIndex");
    int queryUserRole = 0;


    // 获取用户列表
    UserServiceImpl userService = new UserServiceImpl();
    List<User> userList;

    // 第一次走这个请求，一定是第一页，页面大小固定的
    // 可以把这个写到配置文件中，方便后期修改
    int pageSize = 5;
    int currentPageNo = 1;

    if (queryUserName == null) {
      queryUserName = "";
    }

    if (temp != null && !temp.equals("")) {
      // 给查询赋值
      queryUserRole = Integer.parseInt("temp");
    }

    if (pageIndex!=null) {
      try {
        currentPageNo = Integer.parseInt(pageIndex);
      } catch (Exception e) {
        try {
          resp.sendRedirect("error.jsp");
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    }

    // 获取用户的总数
    int totalCount = userService.getUserCount(queryUserName, queryUserRole);

    // 总页数支持
    PageSupport pageSupport = new PageSupport();
    pageSupport.setCurrentPageNo(currentPageNo);
    pageSupport.setPageSize(pageSize);
    pageSupport.setTotalCount(totalCount);

    // 总共有几页
    int totalPageCount = pageSupport.getTotalPageCount();

    // 控制首页和尾页
    if (totalPageCount < 1) {
      // 如果页面要小于1了，就显示第一页的东西
      currentPageNo = 1;
    } else if (currentPageNo > totalPageCount) {
      // 如果当前页面大于最后一页
      currentPageNo = totalPageCount;
    }

    // 获取用户列表展示
    userList = userService.getUserList(queryUserName, queryUserRole, currentPageNo, pageSize);
    req.setAttribute("userList", userList);

    RoleServiceImpl roleService = new RoleServiceImpl();
    List<Role> roleList = roleService.getRoleList();
    req.setAttribute("roleList", roleList);

    req.setAttribute("totalCount", totalCount);
    req.setAttribute("currentPageNo", currentPageNo);
    req.setAttribute("totalPageCount", totalPageCount);
    req.setAttribute("queryUserName", queryUserName);
    req.setAttribute("queryUserRole", queryUserRole);

    // 返回前端
    try {
      req.getRequestDispatcher("userlist.jsp").forward(req,resp);
    } catch (ServletException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }


  }
}
