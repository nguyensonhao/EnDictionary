package Database;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Nguyen Son Hao CE140386
 */
public class DBConnection {
   private static final String URL = "jdbc:mysql://localhost:3306/haonsce140386";
   private static final String UID = "root";
   private static final String PWD = "";
   private Connection conn = null;
   public DBConnection() throws SQLException{
       DriverManager.registerDriver(new com.mysql.jdbc.Driver());
       this.conn = (Connection)DriverManager.getConnection(URL,UID,PWD);
   }
   
   public Connection getConnection(){
      return this.conn;
   }
   
   public boolean isConnected(){
       return this.conn != null;
   }
   
   public void closeConnection() throws SQLException{
       this.conn.close();
   }
}
