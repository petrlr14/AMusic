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
public class DBQuery {
    
    private Connection con=null;
    private DBConnection conexion;
    
    public DBQuery(){
        conexion=new DBConnection();
    }
    
    
    private String[] buscandoElementos(String contraseña){
        String[] datos=null;
        try{
            con=conexion.open();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios WHERE pass='"+contraseña+"'");
            while (rs.next()){
               datos = new String[1];
            }
            stmt.close();
            conexion.close(con);
        }catch(SQLException e){
            System.out.println("Ocurrio algun error");
        }finally{
            return datos;
        }
    }
    
    public boolean isIn(String contraseña){
        return (buscandoElementos(contraseña)!=null);//devuelve tru si se encuentra
    }
    
    
    
}
