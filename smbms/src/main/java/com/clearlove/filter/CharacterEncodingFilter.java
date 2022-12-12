package com.clearlove.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author promise
 * @date 2022/12/12 - 23:47
 */
public class CharacterEncodingFilter implements Filter {

  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    servletRequest.setCharacterEncoding("utf-8");
    servletResponse.setCharacterEncoding("utf-8");
    servletResponse.setContentType("text/html;charset=utf-8");
    filterChain.doFilter(servletRequest,servletResponse);
  }
}
