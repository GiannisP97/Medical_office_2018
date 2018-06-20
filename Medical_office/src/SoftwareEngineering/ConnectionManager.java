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
import java.net.Socket;

/**
 *
 * @author Thanasislt
 */
public class ConnectionManager {
    private Socket conn=null;
    private String hostName;
    private int portNumber;
    private String iBuffer,oBuffer;
    private PrintWriter writer;
    private BufferedReader reader;
    
public <T> T receiveObject() throws IOException, ClassNotFoundException{
        try(ObjectInputStream ois = new ObjectInputStream(conn.getInputStream())){
            T temp = (T) ois.readObject();
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
        iBuffer = "";
        oBuffer = "";
        portNumber = 0;
    }
    public boolean CreateSocket(String host,int port) throws IOException{
        try{
            conn = new Socket(host,port);
            conn.setSoTimeout(3000);
            writer = new PrintWriter(conn.getOutputStream(),true);
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }

    }
    public boolean sendObject(Object obj) throws IOException{
        try(ObjectOutputStream oos = new ObjectOutputStream(conn.getOutputStream())){
            oos.writeObject(obj);
            return true;
        }
        catch(IOException e){
            System.out.println("sendMessage()-> IOException: " + e.getMessage());
            System.out.println("================StackTrace================");  
            e.printStackTrace();
            System.out.println("----------------StackTrace----------------");
            return false;
        }
    }
   
    public boolean sendMessage(String s) throws IOException{
        short i=2;
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
    public void Close() throws IOException{
        this.sendMessage("L3");
        System.out.println("Closing Connection");
        conn.close();
    }
    public boolean isReadReady() throws IOException{
        return reader.ready();
    }
    public String receiveMessage() throws IOException{
        try {
            String recv = reader.readLine();
            return recv;
        }
        catch (Exception x) {
            return "No Response";
        }
    }
    
    public void sendMessage(Serializable s) throws IOException{
        //System.out.println("Sending: "+s);
        
        try{
           // writer.println(s);
            ObjectOutputStream server_objects= new ObjectOutputStream(this.conn.getOutputStream());
            server_objects.writeObject(s);
            server_objects.flush();
        }
        catch (Exception x){
            System.out.println(x.getMessage());
        }
        
    }
}
