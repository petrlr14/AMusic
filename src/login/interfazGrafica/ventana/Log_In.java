/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.interfazGrafica.ventana;
import database.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JFrame;
import formatos.Password;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;
import reproductor.Explorador;
import reproductor.ID3Tag;
import reproductor.interfazGrafica.ventana.InterfazReproductor;
/**
 *
 * @author usuario
 */
public class Log_In extends javax.swing.JFrame {

    /**
     * Creates new form Interfaz
     */
    public Log_In() {
        initComponents();
        this.setLocationRelativeTo(null);
        con=new DBQuery();
        userPlaceHolder();
        passwordPlaceHolder();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        exitButton = new javax.swing.JButton();
        iconUser = new javax.swing.JLabel();
        userField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        logInButton = new javax.swing.JButton();
        fondoLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exitButton.setBackground(new java.awt.Color(0, 0, 0));
        exitButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        exitButton.setForeground(new java.awt.Color(255, 255, 255));
        exitButton.setText("Exit");
        exitButton.setBorderPainted(false);
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(exitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 10, -1, -1));

        iconUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/login/interfazGrafica/imagenes/logo_icon.png"))); // NOI18N
        getContentPane().add(iconUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, -1));

        userField.setBackground(new java.awt.Color(51, 51, 51));
        userField.setForeground(new java.awt.Color(255, 255, 255));
        userField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userFieldActionPerformed(evt);
            }
        });
        getContentPane().add(userField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 200, -1));


        getContentPane().add(userField, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 250, 27));
        userField.getAccessibleContext().setAccessibleDescription("");

        passwordField.setBackground(new java.awt.Color(51, 51, 51));
        passwordField.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(passwordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 250, 27));

        jLabel3.setForeground(new java.awt.Color(51, 51, 255));
        jLabel3.setText("Don't have account? Create it");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent evt){
                    jLabel3actionperformed(evt);
                    
                }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 450, -1, -1));

        logInButton.setBackground(new java.awt.Color(3, 3, 59));
        logInButton.setForeground(new java.awt.Color(255, 255, 255));
        logInButton.setText("Log In");

        logInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logInButtonActionPerformed(evt);
            }
        });
        getContentPane().add(logInButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 410, 170, -1));
        getContentPane().add(logInButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, 170, -1));


        fondoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/login/interfazGrafica/imagenes/wallpaper_login_3.jpg"))); // NOI18N
        
        fondoLabel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                fondoLabelMouseDragged(evt);
            }
        });
        fondoLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                fondoLabelMousePressed(evt);
            }
        });
        
        getContentPane().add(fondoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, -1));
        

        pack();
    }// </editor-fold>                        
    
    
    /*********MOVIMIENTO DE VENTANAS***********/
    private void fondoLabelMousePressed(java.awt.event.MouseEvent evt) {                                        
        // TODO add your handling code here:
       xMouse = evt.getX(); //se consiguen las coordenadas de donde se ha presionado
       yMouse = evt.getY(); 
    }                                       

    private void fondoLabelMouseDragged(java.awt.event.MouseEvent evt) {                                        
        // TODO add your handling code here:
        int x = evt.getXOnScreen(); //se consiguen las coordenadas del mouse mientras se va moviendo este mismo
        int y = evt.getYOnScreen();
        
        this.setLocation(x - xMouse, y - yMouse); //la locacion actual de la ventana (en movimiento) va a ser la coordenada en movimiento menos las coordenadas de onde se presiono
    }    
    
    /*******************************************/
    
    /***************ACCIONES LOS BOTONES**********************/
    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        System.exit(0); //metodo exit nos permite que al presionar el boton, se cierre el programa
    }                                          

    private void userFieldActionPerformed(java.awt.event.ActionEvent evt) {                                          

        // TODO add your handling code here:
        
    }
    
    private void jLabel3actionperformed(MouseEvent evt){ //metodo para poder acceder a la ventana "signIn si el usuario no tiene cuenta"
        Sign_In ventanaSign = new Sign_In(); //se crea un nuevo objeto del mismo tipo de la ventana que queremos abrir
        ventanaSign.setVisible(true); //se hace visible o se levanta la nueva ventana
        Log_In.this.dispose(); //se cierra la ventana actual accediendo a un metodo de esta clase
    }

    private void logInButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        if(userField.getText().length()==0){
            JOptionPane.showMessageDialog(null, "No ha escrito nombre de usuario", "Error", JOptionPane.ERROR_MESSAGE);
        }else if(passwordField.getPassword().length==0){
            JOptionPane.showMessageDialog(null, "No ha escrito una contraseña", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            if(con.isInLogin(Password.chartoPass(passwordField.getPassword()), userField.getText())){
                username = userField.getText();
                userField.setEnabled(false);
                passwordField.setEnabled(false);
                cambiandoPantalla();
                this.dispose();
            }else{
                userField.setText("");
                passwordField.setText("");
                JOptionPane.showMessageDialog(null, "Usted no se ha registrado en la mejor plataforma para escuchar musica en el mundo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }                
    }                                         
/***********************************************/
    
/***************PROCESO PARA CAMBIAR DE PANTALLA************/
    private void cambiandoPantalla(){
        Explorador ex=new Explorador();
        String directorio=ex.abrirExplorador();
        String directorioA=directorio;
        if(ex.getCanciones("")!=null){
            List<String> canciones=ex.getCanciones("");
            List <String[]> Informacion=new ArrayList<String[]>();
            for(String cancion:canciones){
                directorioA+="\\"+cancion;
                Informacion.add(ID3Tag.getID3TagList(directorioA));
                directorioA=directorio;
            }
            InterfazReproductor ir=new InterfazReproductor(Informacion, canciones, directorio);
            ir.setVisible(true);
        }
    }
/*****************************************************************/    
    
/*********************PLACEHOLDERS EN TEXTFIELD**************************/
    private void userPlaceHolder(){
        userPlaceHolder = new com.placeholder.PlaceHolder(userField, "username");
        
        userPlaceHolder.setFont("Euphemia");
        userPlaceHolder.setColorHolder(Color.gray);
        userPlaceHolder.setSize(16);
    }
    private void passwordPlaceHolder(){
        passwordPlaceHolder = new com.placeholder.PlaceHolder(passwordField, "password");
        passwordPlaceHolder.setFont("Euphemia");
        passwordPlaceHolder.setColorHolder(Color.gray);
        passwordPlaceHolder.setSize(16);
    }
/********************************************************************/    
    

    public String getUserName(){
        return username;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Log_In.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Log_In.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Log_In.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Log_In.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Log_In().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel fondoLabel;
    private javax.swing.JLabel iconUser;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton logInButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField userField;
    // End of variables declaration                   
    private DBQuery con;
    private com.placeholder.PlaceHolder userPlaceHolder;
    private com.placeholder.PlaceHolder passwordPlaceHolder;
    
    private String username;
    
    private int xMouse;
    private int yMouse;

}
