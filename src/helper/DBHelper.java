package helper;

import java.sql.DriverManager;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * The Class DBHelper.
 */
public class DBHelper {

  /** The con. */
  public static java.sql.Connection con = null;

  /**
   * Instantiates a new DB helper.
   *
   * @throws InstantiationException the instantiation exception
   * @throws SQLException the SQL exception
   * @throws ClassNotFoundException the class not found exception
   */
  public DBHelper() throws InstantiationException, SQLException, ClassNotFoundException {
    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "root");
  }
}
