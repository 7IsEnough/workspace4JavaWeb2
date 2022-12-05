import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author promise
 * @date 2022/12/5 - 23:23
 */
public class TestJDBC2 {

  public static void main(String[] args) throws ClassNotFoundException, SQLException {
    // 配置信息
    // useUnicode=true&characterEncoding=utf-8 解决中文乱码
    String url = "jdbc:mysql://localhost:3306/jdbc3?user=root&password=1234&useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
    String username = "root";
    String password = "1234";

    // 1.加载驱动
    Class.forName("com.mysql.cj.jdbc.Driver");
    // 2.连接数据库，代表数据库
    Connection connection = DriverManager.getConnection(url, username, password);
    // 3.编写SQL
    String sql = "insert into users(id, name, password, email, birthday) values (?,?,?,?,?);";
    String sql2 = "delete from users where id = 4;";
    // 4.预编译
    PreparedStatement preparedStatement = connection.prepareStatement(sql);

    // 给第一个占位符问号的值赋1
    preparedStatement.setInt(1, 4);
    preparedStatement.setString(2, "Clearlove");
    preparedStatement.setString(3, "123456");
    preparedStatement.setString(4, "777@qq.com");
    preparedStatement.setDate(5, new Date(System.currentTimeMillis()));

    // 5.执行SQL
    int i = preparedStatement.executeUpdate();

    if (i > 0) {
      System.out.println("插入成功");
    }

    // 6.关闭连接，释放资源  先开后关
    preparedStatement.close();
    connection.close();
  }

}
