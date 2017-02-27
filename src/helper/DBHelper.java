package helper;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

  public static java.sql.Connection con = null;

  public DBHelper() throws InstantiationException, SQLException, ClassNotFoundException {
    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "root");
  }
}
