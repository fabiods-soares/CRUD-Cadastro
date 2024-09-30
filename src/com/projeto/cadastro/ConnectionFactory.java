package com.projeto.cadastro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
   
    public Connection conectaBD(){
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3307/biblioteca?useSSL=false";
            String user = "root";
            String password = "";
            conn = DriverManager.getConnection(url,user,password);
        } catch (SQLException erro) {
            System.out.println("Erro "+ erro.getMessage());
        }
        return conn;
    }
    
}    

