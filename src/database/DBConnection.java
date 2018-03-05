/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;

/**
 *
 * @author android
 */
public class DBConnection {
    
    public Connection open(){//Metodo con el que se establece la conexion con la base de datos
        
        String connString="jdbc:postgresql://localhost:5432/users";
        String user="postgres";
        String password="root";
        Connection con=null;
        try{
            con=DriverManager.getConnection(connString, user, password);
        }catch(SQLException e){
            System.out.println("¡¡¡¡¡¡ERROR AL INICIAL LA BASE DE DATOS!!!!!!");
        }finally{
            if(con!=null){
                return con;
            }else{
                return null;
            }
        }
    }
    
    public void close(Connection conexion){//Metodo para cerrar conexion
        try{
            conexion.close();
        }
        catch(SQLException e){
            System.out.println("ERROR: "+e.getMessage());
        }
    }
    
}
