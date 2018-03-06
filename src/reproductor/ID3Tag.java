/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reproductor;

import java.io.File;
import java.util.*;
import java.util.regex.Pattern;
import org.blinkenlights.jid3.MP3File;

/**
 *
 * @author android
 */
public class ID3Tag {
    
    private static List<String[]> tags;
    private static File file;
    private static String[] datosCancion;
    
    public static String[] getID3TagList(String direccion){//Metodo para poder obtener toda la informacion de una cancion
        String direccionCompleta=direccion;
        
        String[]direccionEspecial=direccion.split(Pattern.quote("\\"));
        System.out.println(direccionEspecial[direccionEspecial.length-1]);
        
        String[] datos=null;
        try{
            datos=new String[4];
            file=new File(direccionCompleta);
            MP3File mp3=new MP3File(file.getAbsoluteFile());
            datos[0]=(mp3.getID3V1Tag().getTitle()==null)?"unknown":mp3.getID3V1Tag().getTitle();
            datos[1]=(mp3.getID3V2Tag().getArtist()==null)?"unknown":mp3.getID3V2Tag().getArtist();
            datos[2]=(mp3.getID3V2Tag().getAlbum()==null)?"unknown":mp3.getID3V2Tag().getAlbum();
            datos[3]=(mp3.getID3V2Tag().getGenre()==null)?"unknown":mp3.getID3V2Tag().getGenre();
        }catch(Exception e){
            datos[0]=direccionEspecial[direccionEspecial.length-1];
            datos[1]="unknown";
            datos[2]="unknown";
            datos[3]="unknown";
        }finally{
            return datos;
        }
    }
    
    
}
