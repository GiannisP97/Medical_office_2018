/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftwareEngineering;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
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
    private PrintWriter writer;
    private BufferedReader reader;
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
            writer = new PrintWriter(conn.getOutputStream(),true);
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

    }
    
    public void sendMessage(String s) throws IOException{
        
        System.out.println("Sending: "+s);
        try{
            writer.println(s);
        }
        catch (Exception x){
            System.out.println(x.getMessage());
        }
    }
    public void Close() throws IOException{
        conn.close();
    }
    public boolean isReadReady() throws IOException{
        return reader.ready();
    }
    public String receiveMessage() throws IOException{
        return reader.readLine();
    }
    
    public void sendSerializable(){
        
    }
}
