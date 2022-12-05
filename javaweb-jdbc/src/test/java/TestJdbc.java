import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author promise
 * @date 2022/12/5 - 22:46
 */
public class TestJdbc {

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
    // 3.向数据库发送SQL的对象:CRUD
    Statement statement = connection.createStatement();
    // 4.编写SQL
    String sql = "select * from users;";
    String sql2 = "delete from users where id = 4;";
    // 5.执行查询SQL，返回一个ResultSet--结果集
    ResultSet rs = statement.executeQuery(sql);
    // 受影响的行数
    int i = statement.executeUpdate(sql2);

    while (rs.next()) {
      System.out.println("id=" + rs.getObject("id"));
      System.out.println("name=" + rs.getObject("name"));
      System.out.println("password=" + rs.getObject("password"));
      System.out.println("email=" + rs.getObject("email"));
      System.out.println("birthday=" + rs.getObject("birthday"));
    }

    // 6.关闭连接，释放资源  先开后关
    rs.close();
    statement.close();
    connection.close();
  }



}
