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
    
    
    private String[] buscandoElementos(String contraseña, String user){
        String[] datos=null;
        try{
            con=conexion.open();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios WHERE pass='"+contraseña+"'");
            while (rs.next()){
               datos = new String[1];
               datos[0]=rs.getString("username");
            }
            stmt.close();
            conexion.close(con);
        }catch(SQLException e){
            System.out.println("Ocurrio algun error");
        }finally{
            return datos;
        }
    }
    
    public boolean isInLogin(String contra, String user){//boolean entrada es para ingresar un usuario a la base, y salida para ingresar a la app
        String[] aux=buscandoElementos(contra, user);
        if(aux==null){
            System.out.println("No se encuentra en la base");
            return false;
        }else if(user.matches(aux[0])){//exclusivo para ver login 
            System.out.println("Exito");    
            return true; 
        }else{
            System.out.println("contraseña o username erroneos");
            return false;
        }
    }
    
    public boolean isInSignin(String contra, String user){
        String[] aux=buscandoElementos(contra, user);
        if(aux==null){
            System.out.println("Exito");
            return true;
        }else if(user.matches(aux[0])){
            System.out.println("Ya esta registrado");
            return false;
        }else{
            System.out.println("Exito");
            return true;
        }
    }
    
    public boolean ingresandoADB(String user, String pass, String email){
        try{
            con=conexion.open();
            Statement stmt=con.createStatement();            
            String query="INSERT INTO usuarios VALUES('"+pass+"', '"+user+"',0, '"+email+"')";
            stmt.executeUpdate(query);
        }catch(SQLException e){
            System.out.println("No se pudo agregar el usuario");
            return false;
        }finally{
            conexion.close(con);
        }
        return true;
    }
    
}
