/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Administrateur
 */
public class MyConnection {

    final String url = "jdbc:mysql://127.0.0.1/healthified";
    final String user = "root";
    final String pwd = "";
    Connection cnx;

    //make db static
    public static MyConnection db;

    //make constr private
    private MyConnection() {

        try {
            cnx = (Connection) DriverManager.getConnection(url, user, pwd);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    //getInstance
    public static MyConnection getInstance() {

        if (db == null) {
            db = new MyConnection();
        }

        return db;

    }

    public Connection getCnx() {

        return cnx;

    }

}
