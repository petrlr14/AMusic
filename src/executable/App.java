/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package executable;

import database.*;
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
        DBQuery con=new DBQuery();
        System.out.println(con.isIn("ab860e49413dab0745e2cfa8ab79f44d"));
    }
    
}
