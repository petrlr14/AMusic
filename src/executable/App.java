/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package executable;

import database.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import login.interfazGrafica.ventana.*;
import org.blinkenlights.jid3.MP3File;
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
        String directorio=ex.abrirExplorador();
        String directorioA=directorio;
        List<String> canciones=ex.getCanciones();
        List <String[]> Informacion=new ArrayList<String[]>();
        for(String cancion:canciones){
            directorioA+="\\"+cancion;
            Informacion.add(ID3Tag.getID3TagList(directorioA));
            directorioA=directorio;
        }
        
        for(String[] listas:Informacion){
            for(String info:listas){
                System.out.println(info);
            }
            System.out.println("=======================");
        }
        
        
    }
    
}
