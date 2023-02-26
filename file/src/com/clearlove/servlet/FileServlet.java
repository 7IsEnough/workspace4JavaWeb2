package com.clearlove.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.UploadContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * @author promise
 * @date 2023/2/25 - 18:06
 */
public class FileServlet extends HttpServlet {


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    // 判断上传的文件是普通表单还是带文件的表单
    if (!ServletFileUpload.isMultipartContent(req)) {
      // 终止方法运行，说明这是一个普通的表单
      return;
    }

    try {
    // 创建上传文件的保存路径，建议在WEB-INF下，安全，用户无法直接访问上传的文件；
    String uploadPath = this.getServletContext().getRealPath("/WEB-INF/upload");
    File uploadFile = new File(uploadPath);
    if (!uploadFile.exists()) {
      // 创建这个目录
      uploadFile.mkdir();
    }

    // 缓存，临时文件
    // 临时路径，加入文件超过了预期的大小，我们就把他放到一个临时文件中，过几天自动删除，或者提醒用户转存为永久
    String tmpPath = this.getServletContext().getRealPath("/WEB-INF/tmp");
    File tmpFile = new File(tmpPath);
    if (!tmpFile.exists()) {
      // 创建这个目录
      tmpFile.mkdir();
    }

    // 1.创建DiskFileItemFactory对象，处理文件上传路径或者大小限制
    DiskFileItemFactory factory = new DiskFileItemFactory();
    // 通过这个工厂设置一个缓冲区，当上传的文件大于这个缓冲区的时候，将他放到临时文件中
    // 设置缓冲区大小为1M
    factory.setSizeThreshold(1024 * 1024);
    // 临时目录的保存目录
    factory.setRepository(tmpFile);

    // 2.获取ServletFileUpload
    ServletFileUpload upload = new ServletFileUpload(factory);

    // 监听文件上传进度
    upload.setProgressListener(new ProgressListener() {
      @Override
      // pBytesRead：已经读到的文件大小
      // pContentLength：文件大小
      public void update(long pBytesRead, long pContentLength, int pItems) {
        System.out.println("总大小：" + pContentLength + "已上传：" + pBytesRead);
      }
    });

    // 处理乱码问题
    upload.setHeaderEncoding("UTF-8");
    // 设置单个文件的最大值
    upload.setFileSizeMax(1024 * 1024 * 10);
    // 设置总共能够上传文件的大小 10M
    upload.setSizeMax(1024 * 1024 * 10);


    // 3.处理上传的文件
    // 把前端请求解析，封装成一个FileItem对象，需要从ServletFileUpload对象中获取
      List<FileItem> fileItems = upload.parseRequest(req);

      // fileItem 每一个表单对象
      for (FileItem fileItem : fileItems) {
        // 判断上传的文件是普通的表单还是带文件的表单
        if (fileItem.isFormField()) {
          // getFieldName指的是前端表单控件的name
          String name = fileItem.getFieldName();
          // 处理乱码
          String value = fileItem.getString("UTF-8");
          System.out.println(name + ":" + value);
        } else {
          // 文件
          // 1.处理文件
          String uploadFileName = fileItem.getName();
          // 防止文件名不合法的情况
          if (uploadFileName == null || uploadFileName.trim().equals("")) {
            continue;
          }

          // 获得上传的文件名 技巧：最后一个/ 后面部分就是文件名
          String fileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);

          // 获取文件的后缀名 技巧：最后一个. 后面部分就是后缀名
          String fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);

          // 使用UUID(唯一识别的通用码)，保证文件名唯一;
          // UUID.randomUUID() 随机生成唯一识别的通用码

          // 网络中传输的东西，都需要序列化
          // pojo，实体类，如果想在多个电脑上运行，传输，需要将对象序列化
          // JNI：Java Native Interface
          // implements Serializable : 标记接口，JVM ---> JAVA 栈   本地方法栈  native  --> C++

          String uuidPath = UUID.randomUUID().toString();

          // 2.存放地址
          // 存到 uploadPath
          // 文件真实存在的路径 realPath
          String realPath = uploadPath + "/" + uuidPath;
          // 给每个文件创建一个对应的文件夹
          File realPathFile = new File(realPath);
          if (!realPathFile.exists()) {
            realPathFile.mkdir();
          }

          // 3.文件传输
          // 获取文件上传的流
          InputStream inputStream = fileItem.getInputStream();

          // 创建文件输出流
          // realPath：真实的文件夹
          // 差了一个文件；加上输出文件的名字 + "/" + uuidFileName
          FileOutputStream fos = new FileOutputStream(realPath + "/" + fileName);

          // 创建缓冲区
          byte[] buffer = new byte[1024 * 1024];

          // 判断是否读取完毕
          int len = 0;
          // 如果大于0说明还存在数据
          while ((len = inputStream.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
          }

          // 关闭流
          fos.close();
          inputStream.close();

          // 上传成功，清除临时文件
          fileItem.delete();

        }


      }

      // servlet请求转发消息
      req.setAttribute("msg", "success");
      req.getRequestDispatcher("info.jsp").forward(req, resp);


    } catch (FileUploadException e) {
      e.printStackTrace();
    }


  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    super.doPost(req, resp);
  }
}
