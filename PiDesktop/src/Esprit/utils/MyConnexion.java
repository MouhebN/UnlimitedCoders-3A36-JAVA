package Esprit.utils;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnexion {

    final String URL="jdbc:mysql://127.0.0.1:3306/healthifiedindiv";
    final String USER ="root";
    final String PWD ="";
    private static java.sql.Connection cnx ;
    private static MyConnexion instance ;


    private  MyConnexion() {

        try {
            cnx = DriverManager.getConnection(URL, USER, PWD);
            System.out.println("connexion etablie ......");
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
    }
    public static MyConnexion getInstance(){

        if (instance == null){
            instance = new MyConnexion();
        }

        return instance;
    }
    public static java.sql.Connection getCnx (){
        return cnx;
    }
}
