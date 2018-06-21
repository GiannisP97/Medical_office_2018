/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftwareEngineering;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 *
 * @author Thanasislt
 */
public class ConnectionManager {
    private Socket conn=null;
    private String hostName;
    private int portNumber;
   // private String iBuffer,oBuffer;
    private PrintWriter writer;
    private BufferedReader reader;
    
public <T> T receiveObject(){
    
    try{
            ObjectInputStream ois = new ObjectInputStream(conn.getInputStream());    
            T temp = (T) ois.readObject();
            System.out.println("Got object of type "+ temp.getClass().getTypeName());
            return temp;
        }
        catch(IOException e){
            System.out.println("recvMessage()-> IOException: " + e.getMessage());
            return null;
        }
        catch(ClassNotFoundException e){
            System.out.println("recvMessage()-> ClassNotFoundException: " + e.getMessage());
            return null;
        }
    }
    
    public ConnectionManager(){
        hostName = ""; 
      //  iBuffer = "";
      //  oBuffer = "";
        portNumber = 0;
    }
    public boolean CreateSocket(String host,int port){
        try{
            conn = new Socket();
            conn.connect(new InetSocketAddress(host,port), 1000000);
            
            conn.setSoTimeout(1000000);
            writer = new PrintWriter(conn.getOutputStream(),true);
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }

    }
    public boolean sendObject(Object obj){
        
        try{
            System.out.println("Sending object of type "+ obj.getClass().getTypeName());
            ObjectOutputStream oos = new ObjectOutputStream(conn.getOutputStream());
            oos.writeObject(obj);
            return true;
        }
        catch(IOException e){
            System.out.println("Catch: 'sendMessage()-> IOException: " + e.getMessage()+"'");
            return false;
        }
    }
   
    public boolean sendMessage(String s){
        System.out.println("Sending... "+s);
        try{
            writer.println(s);
            return true;
        }
        catch (Exception x){
            System.out.println(x.getMessage());
            return false;
        }
    }
    public void Close(){
        try{
            System.out.println("Closing Connection");
            this.sendMessage("L3");
            
            conn.close();
        }
        catch (Exception ex){
            System.out.println("Could not close connection. Reason -> "+ex.getMessage());
        }
    }
    public boolean isReadReady(){
        try{
            return reader.ready();
        }
        catch (Exception c){
            return false;
        }
    }
    public String receiveMessage() throws Exception{
            String recv = reader.readLine();
            return recv;
    }

}
