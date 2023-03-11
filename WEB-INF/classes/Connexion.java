package connect;

import java.sql.*;

public class Connexion {
  
  String url;
  String user;
  String password;

  public Connection connex(String url, String user, String password) throws Exception
  {
    Connection con = DriverManager.getConnection(url, user, password);
      // System.out.println(con.toString());
    return con;
  }
}
