/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package executable;

import database.*;
import login.interfazGrafica.ventana.*;
import reproductor.*;

/**
 *
 * @author android
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Explorador ex=new Explorador();
        ex.abrirExplorador();
        for(String cancion:ex.getCanciones()){
            System.out.println(cancion);
        }
    }
    
}
