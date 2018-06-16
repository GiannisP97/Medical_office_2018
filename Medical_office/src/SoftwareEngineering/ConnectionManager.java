/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftwareEngineering;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Thanasislt
 */
public class ConnectionManager {
    private Socket conn;
    private String hostName;
    private int portNumber;
    private String iBuffer,oBuffer;
    public ConnectionManager(){
        hostName = ""; 
        iBuffer = "";
        oBuffer = "";
        portNumber = 0;
        conn = null;
    }
    public void CreateSocket(String host,int port) throws IOException{
        try{
            conn = new Socket(host,port);
        }
        catch(Exception ex){
            
        }
    }
}
