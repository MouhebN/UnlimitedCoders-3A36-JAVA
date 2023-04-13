
package utils;

import java.sql.*;



public class MyConnection {
Connection conn;
static MyConnection instance;
String url = "jdbc:mySQl://localhost:3306/healthified";
String login = "root";
String pwd = "";
    private MyConnection() {
    try {
        conn=DriverManager.getConnection(url, login, pwd);
        System.out.println("Connection établie!");
    } catch (SQLException ex) {
        System.out.println("Probleme de connection");
    }
    }
        public static MyConnection getInstance(){
          if(instance==null){
              instance=new MyConnection();
          }
        return instance;
    }

    public Connection getConn() {
        return conn;
    }
        
    
}
