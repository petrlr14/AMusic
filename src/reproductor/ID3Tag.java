/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reproductor;

import java.io.File;
import java.util.*;
import org.blinkenlights.jid3.MP3File;

/**
 *
 * @author android
 */
public class ID3Tag {
    
    private static List<String[]> tags;
    private static File file;
    private static String[] datosCancion;
    
    public static String[] getID3TagList(String direccion){
        String direccionCompleta=direccion;
        String[] datos=null;
        try{
            datos=new String[4];
            file=new File(direccionCompleta);
            MP3File mp3=new MP3File(file.getAbsoluteFile());
            datos[0]=(mp3.getID3V2Tag().getTitle()==null)?"unknown":mp3.getID3V2Tag().getTitle();
            datos[1]=(mp3.getID3V2Tag().getArtist()==null)?"unknown":mp3.getID3V2Tag().getArtist();
            datos[2]=(mp3.getID3V2Tag().getAlbum()==null)?"unknown":mp3.getID3V2Tag().getAlbum();
            datos[3]=(mp3.getID3V2Tag().getGenre()==null)?"unknown":mp3.getID3V2Tag().getGenre();
        }catch(Exception e){
            System.out.println("Error");
        }finally{
            return datos;
        }
    }
    
    
}
