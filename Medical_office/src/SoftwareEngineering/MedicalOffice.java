/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftwareEngineering;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Giannis
 */
public class MedicalOffice {
    
    private Schedule schedule;
    private User user;
    private OrderHistory orderHistory;
    
    private static boolean FNF = false; 
    public static boolean ConfigFileExists(){
        return FNF;
    }
    

    public static void main(String[] args) throws IOException {
        File connection_info = new File("config.ini");
        
        
        
        if (connection_info.isFile() == true){
            FNF = true;
        }
       

        LoginUI.main(args);
    }
    
}
