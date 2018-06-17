/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftwareEngineering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
        String h="";
        int p =0;
        String[] params = new String[2];
        params[0]="0";
        
        if (connection_info.isFile() == true){
            FNF = true;
            
            try (BufferedReader br = new BufferedReader(new FileReader(connection_info))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.startsWith("#")) continue;
                    String[] split = line.split(":");
                    if (split[0].equals("hostname")) h = split[1];
                    else if (split[0].equals("port")) p = Integer.parseInt(split[1]);   



                }
            }
            catch (Exception ex){
                params[0]="-1";
                LoginUI.main(params);
            }
            LoginUI.setConnectionInfo(h, p);
            
        }
        System.out.println(h+":"+p);
       LoginUI.main(params);

        
    }
    
}
