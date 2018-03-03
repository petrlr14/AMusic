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
    
    public boolean isIn(String contraseña, String user, boolean entradaSalida){//boolean entrada es para ingresar un usuario a la base, y salida para ingresar a la app
        String[] aux;
        aux=buscandoElementos(contraseña, user);
        if(aux==null){
            if(!entradaSalida){
                System.out.println("No se encuentra en la base de datos"); 
                return false; 
            }else{//para sign in
                System.out.println("Exito!");
                return true;
            }
        }else if(user.matches(aux[0])){//exclusivo para ver login
                System.out.println("Exito"); 
                return true; 
        }else{//exclusivo para ver login
                System.out.println("contraseña o username erroneos");
                return false;
        }
    }
    
    public boolean ingresandoADB(String user, String pass, String codigo){
        try{
            con=conexion.open();
            Statement stmt=con.createStatement();
            String query="INSERT INTO usuarios VALUES('"+pass+"', '"+user+"', "+codigo+")";
            stmt.executeQuery(query);
            conexion.close(con);
        }catch(SQLException e){
            System.out.println("No se pudo agregar el usuario");
            return false;
        }
        return true;
    }
    
}
