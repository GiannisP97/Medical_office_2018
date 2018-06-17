/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerUtill;

import SoftwareEngineering.Appointment;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.StreamCorruptedException;
import static java.lang.Thread.sleep;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ilias
 */
public class Connection extends Thread{
    int id;
    InetAddress addr;
    Socket soc;
    ArrayList<String> directiveQueue;
    ArrayList<String> sendQueue;
    private ConnectionManager conman;
    boolean logged = false;
    PrintWriter out;
    BufferedReader in;
    
    Connection(Socket s , int ID , ConnectionManager cm){
        super(String.valueOf(ID));
        soc = s;
        id = ID;
        System.out.println(id);
        addr = soc.getInetAddress();      
        conman = cm;
//        System.out.println(addr);
        directiveQueue = new ArrayList<>();
        sendQueue = new ArrayList<>();
        
        System.out.print("Client");
        System.out.println(addr);
        
        this.start();
    }
    
    @Override
    public void run() {
        try{
            System.out.println(addr);
                
            try{
                out =  new PrintWriter(soc.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
                String cuser;
                String cpass;
                        cuser = in.readLine();
                        System.out.println(cuser);                    
                        cpass = in.readLine();
                        
                        
                        if(cuser.equals("kiriazis") && cpass.equals("12345678")){
                            out.println("L1");
                            logged = true;
                            LocalDateTime date = null;
                            date = date.now();
                            System.out.println("Client:" + addr + " Logged In! - " + date);
                        }
                        else{
                            out.println("L0");
                            System.out.println("Client:" + addr + " Failed to Log In!");
                            conman.removeConnection(addr);
                        }   
            }        
            catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            
            int raw;
            char directive;
            char code;
            while(logged){
                raw = in.read();                
                if(raw != -1){
                    directive = (char) raw;
                    
                    System.out.println(directive);
//                    if(directive.equals("L3")){
//                        logged = false;
//                    }
                switch(directive){
                    case 'L':
                        raw = in.read();
                        code = (char) raw;
                        System.out.println(code);
                        switch(code){
                            case '3':
                                logged = false;
                                break;
                        }
                        break;
                    case 'C':
                        raw = in.read();
                        code = (char) raw;
                        System.out.println(code);
                        switch(code){
                            case '1':
                                try{                                    
                                    ObjectInputStream ois = new ObjectInputStream(soc.getInputStream());
                                    Appointment ap = (Appointment)ois.readObject();
//                                    String str = (String)ois.readObject().toString();
//                                    System.out.println(str);
                                    System.out.println("Appointment ID: " + ap.getID());
                                }
                                catch(StreamCorruptedException e){
                                    System.out.println("Exception: " + e.getMessage());                                    
                                    conman.removeConnection(addr);
                                    soc.close();
                                    logged = false;
                                }
                                
                                break;
                        }
                        break;
                }
                }
                else{
                    logged = false;
                }
            }
            
            conman.removeConnection(addr);
            System.out.println("Closed");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            conman.removeConnection(addr);
        }
    }
    
    public InetAddress getInet(){
        return addr;
    }
    
    public int getID(){
        return id;
    }
    
}
