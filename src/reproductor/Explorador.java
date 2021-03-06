/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reproductor;
import database.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
/**
 *
 * @author android
 */
public class Explorador {
    
    private JFileChooser chooser;
    private String folder;
    private List<String> canciones;
    private DBQuery query;
    
    public Explorador(){
        query=new DBQuery();
        folder=query.getDirectorioDeAdmin();
    }
    
    public String abrirExplorador(){//Metodo para abrir ventana y buscar un directorio
        String aux=null;
        chooser=new JFileChooser(new File(folder));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("Seleccione carpeta predeterminada");
        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return(f.isDirectory());
            }

            @Override
            public String getDescription() {
                return "Directorio";
            }
        });
        
        int resultado=chooser.showDialog(null,"Seleccionar");
        if(resultado==JFileChooser.APPROVE_OPTION){
            return folder=chooser.getSelectedFile().getAbsolutePath(); 
        }else{
            return folder;
        }
    }
    
    public List<String> getCanciones(String directorio){//Devuelve las canciones en el directorio
        canciones=new ArrayList<String>();
        if(directorio!=""){
            folder=directorio;
            query.updateDirectorioAdmin(folder);
        }
        File[] archivosCanciones=new File(folder).listFiles();
        if(archivosCanciones!=null){
            for(File file: archivosCanciones){
                if(file.isFile()){
                    if(esMP3(file.getName())){
                        canciones.add(file.getName());
                    }
                }
            }
            return canciones;
        }else{
            return null;
        }
        
    }
    
    private boolean esMP3(String cancion){//Metodo para identificar si el archivo es un formato disponible
        int indice=cancion.length()-4;
        String extension1=".mp3";
        String extension2=".Mp3";
        String extension3=".mP3";
        String extension4=".MP3";
        int contador=0;
        for(int i=indice, j=0; i<cancion.length(); i++,j++){
            if(cancion.charAt(i)==extension1.charAt(j) || cancion.charAt(i)==extension2.charAt(j) || cancion.charAt(i)==extension3.charAt(j) || cancion.charAt(i)==extension4.charAt(j)){
                contador++;
            }
        }
        return contador==4;
    }
    
}
