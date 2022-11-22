package com.clearlove.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author promise
 * @date 2022/11/22 - 22:12
 * 统计网站在线人数--统计session
 */
public class OnlineCountListener implements HttpSessionListener {

  // 创建session监听--看你的一举一动
  // 一旦创建Session就会触发一次这个事件
  @Override
  public void sessionCreated(HttpSessionEvent se) {
    ServletContext ctx = se.getSession().getServletContext();
    System.out.println(se.getSession().getId());
    Integer onlineCount = (Integer) ctx.getAttribute("OnlineCount");

    if (onlineCount == null) {
      onlineCount = new Integer(1);
    } else {
      int count = onlineCount.intValue();
      onlineCount = new Integer(count + 1);
    }

    ctx.setAttribute("OnlineCount", onlineCount);
  }

  // 销毁session监听
  // 一旦销毁session就会触发一次这个事件
  @Override
  public void sessionDestroyed(HttpSessionEvent se) {
    ServletContext ctx = se.getSession().getServletContext();


    Integer onlineCount = (Integer) ctx.getAttribute("OnlineCount");

    if (onlineCount == null) {
      onlineCount = new Integer(0);
    } else {
      int count = onlineCount.intValue();
      onlineCount = new Integer(count - 1);
    }

    ctx.setAttribute("OnlineCount", onlineCount);
  }

  /**
   * Session销毁的2种情况
   * 1.手动销毁     se.getSession().invalidate();
   * 2.自动销毁
   *    在web.xml下
   *      <session-config>
   *        <session-timeout>1</session-timeout>
   *      </session-config>
   */
}
