/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reproductor.interfazGrafica.ventana;

import database.DBQuery;
import formatos.Tabla;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import login.interfazGrafica.ventana.Log_In;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import reproductor.Explorador;
import reproductor.ID3Tag;

/**
 *
 * @author usuario
 */
public class InterfazReproductor extends javax.swing.JFrame {

    /**
     * Creates new form InterfazReproductor
     */
    public InterfazReproductor(List<String[]> Informacion, List<String> Canciones, String directorio, boolean isAdmin) {
        initComponents();

        Log_In username = new Log_In();
        userName.setText(username.getUserName());

        this.setLocationRelativeTo(null);

        this.informacion=Informacion;
        this.directorio=directorio;
        canciones=Canciones;
        selectFile.setVisible(isAdmin);
        songs=new ArrayList<String>();
        artistas=new ArrayList<String>();
        albumes=new ArrayList<String>();
        genero=new ArrayList<String>();

        for(int i=0; i<Informacion.size(); i++){
            songs.add(Informacion.get(i)[0]);
            artistas.add(Informacion.get(i)[1]);
            albumes.add(Informacion.get(i)[2]);
            genero.add(Informacion.get(i)[3]);
        }
        
        uniqueArtistas=new HashSet<String>(artistas);
        uniqueAlbumes=new HashSet<String>(albumes);
        uniqueGenero=new HashSet<String>(genero);

        
        datos=new Object[Informacion.size()][1];
        
        for(int i=0; i<songs.size(); i++){
            datos[i][0]=songs.get(i);
        }
        DefaultTableModel Model=new DefaultTableModel(datos,
                new String [] {
                    "Songs"}){
                    @Override
                    public boolean isCellEditable(int row, int column){
                        return false;
                    }
                };
        jTable1.setModel(Model);
        jTable1.setAutoCreateRowSorter(true);
        jTable1.addMouseListener(new MouseAdapter(){
             public void mouseClicked(java.awt.event.MouseEvent e) {
                if(e.getClickCount()==2){
                    procesos.stop();
                    procesos.play(direccionPlayer);
                    System.out.println("Se ha hecho doble click");
                }
             }});
        ListSelectionModel model=jTable1.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!model.isSelectionEmpty()){
                    int row=jTable1.getSelectedRow();
                    datoSeleccionado[0]=jTable1.getValueAt(row, 0).toString();
                }
                songAsociadosADetalles=new ArrayList<String>();
                List<Integer>m=getCanciones(datoSeleccionado[0], 0);
                    for(Integer c:m){
                        if(directorio.charAt(directorio.length()-1)=='\\'){
                            direccionPlayer=(directorio+canciones.get(c));
                        } else {
                            direccionPlayer=(directorio+"\\"+canciones.get(c));
                        }
                        
                    }
                
                
            }
        });
        Tabla.setCellsAlignment(jTable1, SwingConstants.CENTER);
        
    }
    
    private void disableSelectFile(boolean admin){
        if(!admin){
            selectFile.setVisible(false);
        }
    }
    private void cambiarACancion(){
        datos=new Object[songs.size()][1];
        for(int i=0; i<songs.size(); i++){
            datos[i][0]=songs.get(i);
        }
        DefaultTableModel Model=new DefaultTableModel(datos,
                new String [] {
                    "Songs"}){
                    @Override
                    public boolean isCellEditable(int row, int column){
                        return false;
                    }
                };
        jTable1.setModel(Model);
        jTable1.setAutoCreateRowSorter(true);
        
        Tabla.setCellsAlignment(jTable1, SwingConstants.CENTER);
    }
    
    private void cambiarAAlbum(){
        contadoPermitirCambio=0;
        datos=new Object[uniqueAlbumes.size()][1];
        Object[] array=uniqueAlbumes.toArray();
        for(int i=0; i<uniqueAlbumes.size(); i++){
            datos[i][0]=array[i];
        }
        DefaultTableModel Model=new DefaultTableModel(datos,
                new String [] {
                    "Albums"}){
                    @Override
                    public boolean isCellEditable(int row, int column){
                        return false;
                    }
                };
        jTable1.setModel(Model);
        jTable1.setAutoCreateRowSorter(true);
        ListSelectionModel model=jTable1.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!model.isSelectionEmpty()){
                    int row=jTable1.getSelectedRow();
                    datoSeleccionado[0]=jTable1.getValueAt(row, 0).toString();
                    if(contadoPermitirCambio==0){
                        mostrarCancionesRelacionadas(2);
                        contadoPermitirCambio++;
                    }   
                }
            }
        });
        Tabla.setCellsAlignment(jTable1, SwingConstants.CENTER);
    }
    private void cambiarAGenero(){
        contadoPermitirCambio=0;
        datos=new Object[uniqueGenero.size()][1];
        Object[] array=uniqueGenero.toArray();
        for(int i=0; i<uniqueGenero.size(); i++){
            datos[i][0]=array[i];
        }
        DefaultTableModel Model=new DefaultTableModel(datos,
                new String [] {
                    "Genres"}){
                    @Override
                    public boolean isCellEditable(int row, int column){
                        return false;
                    }
                };
        jTable1.setModel(Model);
        jTable1.setAutoCreateRowSorter(true);
        ListSelectionModel model=jTable1.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if(!model.isSelectionEmpty()){
                    int row=jTable1.getSelectedRow();
                    datoSeleccionado[0]=jTable1.getValueAt(row, 0).toString();
                    if(contadoPermitirCambio==0){
                        mostrarCancionesRelacionadas(3);
                        contadoPermitirCambio++;
                    }
                }
            }
        });
        Tabla.setCellsAlignment(jTable1, SwingConstants.CENTER);
    }
    private void cambiarAArtistas(){
        contadoPermitirCambio=0;
        datos=new Object[uniqueArtistas.size()][1];
        Object[] array=uniqueArtistas.toArray();
        for(int i=0; i<uniqueArtistas.size(); i++){
            datos[i][0]=array[i];
        }
        DefaultTableModel Model=new DefaultTableModel(datos,
                new String [] {
                    "Artists"}){
                    @Override
                    public boolean isCellEditable(int row, int column){
                        return false;
                    }
                };
        jTable1.setModel(Model);
        jTable1.setAutoCreateRowSorter(true);
        ListSelectionModel model=jTable1.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if(!model.isSelectionEmpty()){
                    int row=jTable1.getSelectedRow();
                    datoSeleccionado[0]=jTable1.getValueAt(row, 0).toString();
                    System.out.println(datoSeleccionado[0]);
                    if(contadoPermitirCambio==0){
                        mostrarCancionesRelacionadas(1);
                        contadoPermitirCambio++;
                    }
                }
            }
        });
        Tabla.setCellsAlignment(jTable1, SwingConstants.CENTER);
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        play = new javax.swing.JLabel();
        stop = new javax.swing.JLabel();
        pause = new javax.swing.JLabel();
        selectFile = new javax.swing.JLabel();
        forward = new javax.swing.JLabel();
        back = new javax.swing.JLabel();
        display = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        songOption = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        albumOption = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        genderOption = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        userName = new javax.swing.JLabel();
        artistOption = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        exit = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        play.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        play.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductor/interfazGrafica/imagenes/play.png"))); // NOI18N
        play.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        play.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                playMouseReleased(evt);
            }
        });
        getContentPane().add(play, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 590, -1, -1));

        stop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductor/interfazGrafica/imagenes/stop.png"))); // NOI18N
        stop.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        stop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                stopMouseReleased(evt);
            }
        });
        getContentPane().add(stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 590, -1, -1));

        pause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductor/interfazGrafica/imagenes/pause.png"))); // NOI18N
        pause.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pause.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pauseMouseReleased(evt);
            }
        });
        getContentPane().add(pause, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 590, -1, -1));

        selectFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductor/interfazGrafica/imagenes/selectFile.png"))); // NOI18N
        selectFile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        selectFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                selectFileMouseReleased(evt);
            }
        });
        getContentPane().add(selectFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 590, -1, -1));

        forward.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductor/interfazGrafica/imagenes/forward.png"))); // NOI18N
        getContentPane().add(forward, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 590, 60, -1));

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductor/interfazGrafica/imagenes/back.png"))); // NOI18N
        getContentPane().add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 590, 50, -1));

        display.setFont(new java.awt.Font("Eras Light ITC", 1, 18)); // NOI18N
        display.setForeground(new java.awt.Color(255, 255, 255));
        display.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(display, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 540, 440, 20));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductor/interfazGrafica/imagenes/logo.png"))); // NOI18N
        getContentPane().add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("AMusic");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("The best way");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, 790, 400));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        songOption.setOpaque(false);
        songOption.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                songOptionMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                songOptionMousePressed(evt);
            }
        });
        songOption.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Song");
        songOption.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 15, -1, -1));

        jPanel1.add(songOption, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 290, 60));

        albumOption.setOpaque(false);
        albumOption.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                albumOptionMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                albumOptionMousePressed(evt);
            }
        });
        albumOption.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Album");
        albumOption.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 15, -1, -1));

        jPanel1.add(albumOption, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 290, 60));

        genderOption.setOpaque(false);
        genderOption.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                genderOptionMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                genderOptionMousePressed(evt);
            }
        });
        genderOption.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Gender");
        genderOption.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 15, -1, -1));

        jPanel1.add(genderOption, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 290, 60));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductor/interfazGrafica/imagenes/userIcon3.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, -1, -1));

        userName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        userName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(userName, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 130, 50));

        artistOption.setOpaque(false);
        artistOption.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                artistOptionMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                artistOptionMousePressed(evt);
            }
        });
        artistOption.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Artist");
        artistOption.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 15, -1, -1));

        jPanel1.add(artistOption, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 290, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 530));

        exit.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        exit.setForeground(new java.awt.Color(255, 255, 255));
        exit.setText("X");
        exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });
        getContentPane().add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 10, -1, -1));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductor/interfazGrafica/imagenes/wallpaper.jpg"))); // NOI18N
        background.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                backgroundMouseDragged(evt);
            }
        });
        background.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                backgroundMousePressed(evt);
            }
        });
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void stopMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stopMouseReleased
        procesos.stop();
    }//GEN-LAST:event_stopMouseReleased

    private void playMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playMouseReleased
        procesos.resume();
    }//GEN-LAST:event_playMouseReleased

    private void pauseMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pauseMouseReleased
        procesos.pause();
    }//GEN-LAST:event_pauseMouseReleased

    private void selectFileMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectFileMouseReleased
        Explorador ex=new Explorador();
        String directorio=ex.abrirExplorador();
        String directorioA=directorio;
        if(ex.getCanciones("")!=null){
            canciones=ex.getCanciones(directorio);
        }
        List <String[]> Informacion=new ArrayList<String[]>();
        for(String cancion:canciones){
            directorioA+="\\"+cancion;
            Informacion.add(ID3Tag.getID3TagList(directorioA));
            directorioA=directorio;
        }
        
        this.informacion=Informacion;
        
        songs=new ArrayList<String>();
        artistas=new ArrayList<String>();
        albumes=new ArrayList<String>();
        genero=new ArrayList<String>();

        for(int i=0; i<Informacion.size(); i++){
            songs.add(Informacion.get(i)[0]);
            artistas.add(Informacion.get(i)[1]);
            albumes.add(Informacion.get(i)[2]);
            genero.add(Informacion.get(i)[3]);
        }
        
        uniqueArtistas=new HashSet<String>(artistas);
        uniqueAlbumes=new HashSet<String>(albumes);
        uniqueGenero=new HashSet<String>(genero);
        
        
        datos=new Object[Informacion.size()][1];
        
        for(int i=0; i<songs.size(); i++){
            datos[i][0]=songs.get(i);
        }
        DefaultTableModel Model=new DefaultTableModel(datos,
                new String [] {
                    "Songs"}){
                    @Override
                    public boolean isCellEditable(int row, int column){
                        return false;
                    }
        };
        
        
        jTable1.setModel(Model);
        jTable1.setAutoCreateRowSorter(true);
        Tabla.setCellsAlignment(jTable1, SwingConstants.CENTER);
    }//GEN-LAST:event_selectFileMouseReleased

    private void songOptionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_songOptionMousePressed
        // TODO add your handling code here:
        setColor(songOption);
        resetColor(albumOption);
        resetColor(genderOption);
        resetColor(artistOption);
        cambiarACancion();
    }//GEN-LAST:event_songOptionMousePressed

    private void albumOptionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_albumOptionMousePressed
        // TODO add your handling code here:
        setColor(albumOption);
        resetColor(songOption);
        resetColor(genderOption);
        resetColor(artistOption);
        cambiarAAlbum();
    }//GEN-LAST:event_albumOptionMousePressed

    private void genderOptionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_genderOptionMousePressed
        // TODO add your handling code here:
        setColor(genderOption);
        resetColor(songOption);
        resetColor(albumOption);
        resetColor(artistOption);
        cambiarAGenero();
    }//GEN-LAST:event_genderOptionMousePressed

    private void artistOptionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistOptionMousePressed
        // TODO add your handling code here:
        setColor(artistOption);
        resetColor(songOption);
        resetColor(genderOption);
        resetColor(albumOption);
        cambiarAArtistas();
    }//GEN-LAST:event_artistOptionMousePressed

    private void backgroundMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backgroundMousePressed
        // TODO add your handling code here:
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_backgroundMousePressed

    private void backgroundMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backgroundMouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_backgroundMouseDragged

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitMouseClicked

    private void songOptionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_songOptionMouseEntered
        // TODO add your handling code here:
        setColor(songOption);
        resetColor(albumOption);
        resetColor(genderOption);
        resetColor(artistOption);
    }//GEN-LAST:event_songOptionMouseEntered

    private void albumOptionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_albumOptionMouseEntered
        // TODO add your handling code here:
        setColor(albumOption);
        resetColor(songOption);
        resetColor(genderOption);
        resetColor(artistOption);
    }//GEN-LAST:event_albumOptionMouseEntered

    private void genderOptionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_genderOptionMouseEntered
        // TODO add your handling code here:
        setColor(genderOption);
        resetColor(songOption);
        resetColor(albumOption);
        resetColor(artistOption);
    }//GEN-LAST:event_genderOptionMouseEntered

    private void artistOptionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistOptionMouseEntered
        // TODO add your handling code here:
        setColor(artistOption);
        resetColor(songOption);
        resetColor(genderOption);
        resetColor(albumOption);
    }//GEN-LAST:event_artistOptionMouseEntered
    
    
    private void setColor(JPanel panel){
        panel.setBackground(new Color(0, 0, 0));
        panel.setOpaque(true);
        
    }
    
    private void resetColor(JPanel panel){
        panel.setBackground(new Color(60,63,65));
        panel.setOpaque(false);
        
    }
    
    
    private int getIndex(String[] infoObtenida, int dato){
        for(String[] info:this.informacion){
            if(info[0]==infoObtenida[dato]){
                return this.informacion.indexOf(info);
            }
        }
        return 1;
    }
    
    private List<Integer> getCanciones(String datoABuscar, int tipoDedato){
        indicesDecanciones=new ArrayList<Integer>();
        for(String[] x:informacion){
            if(x[tipoDedato].matches(datoABuscar)){
                indicesDecanciones.add(informacion.indexOf(x));
            }
        }
        return indicesDecanciones;
    }
    
    /*
    
    */
    
    private void mostrarCancionesRelacionadas(int tipoDeDato){
        List<Integer> m=getCanciones(datoSeleccionado[0],tipoDeDato);
        datos=new Object[m.size()][1];
        int cont=0;
        for(Integer x:m){
            datos[cont][0]=songs.get(x);
            cont++;
        }
        
        DefaultTableModel Model=new DefaultTableModel(datos,
                new String [] {
                    "Songs"}){
                    @Override
                    public boolean isCellEditable(int row, int column){
                        return false;
                    }
                };
        jTable1.setModel(Model);
        jTable1.setAutoCreateRowSorter(true);
        jTable1.addMouseListener(new MouseAdapter(){
             public void mouseClicked(java.awt.event.MouseEvent e) {
                if(e.getClickCount()==1){
                     System.out.println("Se ha hecho un click");
                }
                if(e.getClickCount()==2){
                    System.out.println("Se ha hecho doble click");
                }
             }});
        Tabla.setCellsAlignment(jTable1, SwingConstants.CENTER);
    }
        
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel albumOption;
    private javax.swing.JPanel artistOption;
    private javax.swing.JLabel back;
    private javax.swing.JLabel background;
    public static javax.swing.JLabel display;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel forward;
    private javax.swing.JPanel genderOption;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel pause;
    private javax.swing.JLabel play;
    private javax.swing.JLabel selectFile;
    private javax.swing.JPanel songOption;
    private javax.swing.JLabel stop;
    private javax.swing.JLabel userName;
    // End of variables declaration//GEN-END:variables
    private ProcesosReproduccion procesos = new ProcesosReproduccion();
    private Explorador exp;
    private String directorio;
    String[] datoSeleccionado=new String[4];
    private List<String[]> informacion;
    private List<String> canciones;//Lista de canciones en el directorio
    private DBQuery query;
    private Object[][] datos;




    private List<String> songs;
    private List<String> artistas;
    private List<String> albumes;
    private List<String> genero;
    
    private List<String> songAsociadosADetalles;
    
    private Set<String> uniqueArtistas;
    private Set<String> uniqueAlbumes;
    private Set<String> uniqueGenero;
                   
    private String direccionPlayer;
    
    



    private int xMouse;
    private int yMouse;
    private List<Integer> indicesDecanciones;
    
    
    private boolean permitirCambio=true;
    private int contadoPermitirCambio=0;
    
}