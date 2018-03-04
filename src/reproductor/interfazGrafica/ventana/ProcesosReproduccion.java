/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reproductor.interfazGrafica.ventana;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerError;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author usuario
 */
public class ProcesosReproduccion {
    
    FileInputStream file;
    BufferedInputStream buffer;
    
    public Player player;
    public long total;
    public long restante;
    public String fileLocation;
    
    public void stop(){
        if(player != null){
            player.close();
            total = 0;
            restante = 0;
        }
    }
    public void pause(){
        if(player != null){
            try {
                restante = file.available(); //available es un metodo de FileInputStream para conocer cuantos bytes quedan en el archivo, en este caso, cuanto queda de cancion
                player.close();
            } catch (IOException ex) {
                //Logger.getLogger(ProcesosReproduccion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }    
    }
    public void resume(){ //aunque este metodo sea muy parecido a play, no necesitaremos un path sino que, el resto de duracion
        try{
            file = new FileInputStream(fileLocation);
            buffer = new BufferedInputStream(file);
            
            player = new Player(buffer);
            
            file.skip(total - restante);
            
        } catch (JavaLayerException ex) {
            Logger.getLogger(ProcesosReproduccion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProcesosReproduccion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProcesosReproduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        new Thread(){
            @Override
            public void run(){
                try{
                    player.play();
                }catch(JavaLayerException e){
                    
                }
            }
        }.start();
        
    }
    public void play(String path){
        try{
            file = new FileInputStream(path);
            buffer = new BufferedInputStream(file);
            
            player = new Player(buffer);
            
            total = file.available(); //devuelve el total de lo que queda, como inica, devuelve el total de duracion
        
            fileLocation = path+"";
            
        }catch(FileNotFoundException e){
            
        } catch (JavaLayerException ex) {
            Logger.getLogger(ProcesosReproduccion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProcesosReproduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        new Thread(){
            @Override
            public void run(){
                try{
                    player.play();
                }catch(JavaLayerException e){
                    
                }
            }
        }.start();
        
    }
    
}
