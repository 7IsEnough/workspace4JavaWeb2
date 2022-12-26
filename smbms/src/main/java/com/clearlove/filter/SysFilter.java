package com.clearlove.filter;

import com.clearlove.pojo.User;
import com.clearlove.util.Constants;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author promise
 * @date 2022/12/26 - 23:17
 */
public class SysFilter implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;

    // 过滤器，从Session中获取用户
    User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);

    if (user == null) {
      // 已经被移除或者注销了，或者未登录
      response.sendRedirect(request.getContextPath() + "/error.jsp");
    } else {
      filterChain.doFilter(servletRequest, servletResponse);
    }


  }
}
