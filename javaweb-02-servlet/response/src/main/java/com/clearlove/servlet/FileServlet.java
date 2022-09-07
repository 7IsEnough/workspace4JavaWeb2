package com.clearlove.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author promise
 * @date 2022/9/7 - 23:03
 */
public class FileServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // 1.获取文件下载的路径
    //    String realPath = this.getServletContext().getRealPath("/科比.jpg");
    String realPath =
        "E:\\workspace\\workspace4JavaWeb2\\javaweb-02-servlet\\response\\target\\classes\\科比.jpg";
    System.out.println("下载文件的路径：" + realPath);
    // 2.下载的文件名是什么？
    String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
    // 3.让浏览器能够支持(Content-Disposition)下载我们需要的东西
    // 中文文件名用URLEncoder.encode编码，否则有可能乱码
    resp.setHeader(
        "Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
    // 4.获取下载文件的输入流
    FileInputStream in = new FileInputStream(realPath);
    // 5.创建缓冲区
    int len = 0;
    byte[] buffer = new byte[1024];
    // 6.获取OutputStream对象
    ServletOutputStream out = resp.getOutputStream();
    // 7.将FileInputStream流写入到buffer缓冲区，使用outputstream将缓冲区中的数据输出到客户端
    while ((len = in.read(buffer)) > 0) {
      out.write(buffer, 0, len);
    }
    in.close();
    out.close();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doGet(req, resp);
  }
}
