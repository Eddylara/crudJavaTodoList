package com.example.todolist.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Coneccion {

    private static String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\src\\main\\resources\\db\\bd.db";

    public static Connection Conectar() throws SQLException{
        Connection cx = DriverManager.getConnection(url);
        return cx;
    }
    
    public static String getPath(){
        return url;
    }
    public static void main(String[] args) {
        ArrayList<Contacto> lista;
        try {
            lista = Contacto.getAll();
            lista.forEach(e->{
                System.out.println(e);
            });
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}
