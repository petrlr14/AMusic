/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reproductor;
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
    public String abrirExplorador(){
        String aux=null;
        chooser=new JFileChooser();
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
            return aux;
        }
    }
    
    public List<String> getCanciones(){
        canciones=new ArrayList<String>();
        File[] archivosCanciones=new File(this.chooser.getSelectedFile().getAbsolutePath()).listFiles();
        for(File file: archivosCanciones){
            if(file.isFile()){
                if(esMP3(file.getName())){
                    canciones.add(file.getName());
                }
            }
        }
        return canciones;
    }
    
    private boolean esMP3(String cancion){
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
