import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.Test;

/**
 * @author promise
 * @date 2022/12/7 - 23:17
 */
public class TestJDBC3 {

  @Test
  public void test() {
    // 配置信息
    // useUnicode=true&characterEncoding=utf-8 解决中文乱码
    String url = "jdbc:mysql://localhost:3306/jdbc3?user=root&password=1234&useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
    String username = "root";
    String password = "1234";
    Connection connection = null;
    try {
      // 1.加载驱动
      Class.forName("com.mysql.cj.jdbc.Driver");
      // 2.连接数据库，代表数据库
      connection = DriverManager.getConnection(url, username, password);

      // 3.通知数据库开启事务--false开启
      connection.setAutoCommit(false);

      String sql = "update account set money = money - 100 where `name` = 'A';";
      connection.prepareStatement(sql).executeUpdate();

      // 制造错误
//      int i = 1/0;

      String sql2 = "update account set money = money + 100 where `name` = 'B';";
      connection.prepareStatement(sql2).executeUpdate();

      // 以上两条sql都执行成功了，就提交事务
      connection.commit();
      System.out.println("success");
    } catch (Exception e) {
      try {
        // 如果出现异常，就通知数据库回滚事务
        connection.rollback();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

}
