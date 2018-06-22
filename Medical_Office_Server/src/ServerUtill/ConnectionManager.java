/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerUtill;

import SoftwareEngineering.DBManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ilias
 */
public class ConnectionManager implements Runnable{
    
    private static ConnectionManager connectionManagerInstance = null;
    
    private ConcurrentLinkedQueue<Connection> connections;
    private boolean PollCycle;
    static final int portNumber = 4444;
    static int connID = 0;
    ServerSocket serverSocket;
    
    //---Singleton
    DBManager dbmanager;
//    static ServerGUI gui;
    //------------
    
    public static ConnectionManager getInstance(){
        if(connectionManagerInstance == null){
            try {
                connectionManagerInstance = new ConnectionManager();
//                gui = ServerGUI.getInstance();
            } catch (IOException ex) {
                System.out.println("getInstance()-> IOException: " + ex.getMessage());
            }            
        }
        return connectionManagerInstance;
    }
    
    
    public ConnectionManager() throws IOException{
        connections = new ConcurrentLinkedQueue();
        PollCycle = true;
        try{
            DBManager dbmanager = DBManager.getInstance();
        }
        catch(java.lang.NoClassDefFoundError e){
            System.out.println("ConnectionManager()-> NoClassDefFoundError: " + e.getMessage());
        }
        
        
        try{
            serverSocket = new ServerSocket(portNumber);
        }
        catch(Exception e){
            System.out.println("ConnectionManager()-> IOException: " + e.getMessage());
        }
        
        this.run();
        
    }
    
    
    @Override
    public void run() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        while(PollCycle){
            
            try{
                System.out.println("Waiting for client...");
                Socket clientSocket = serverSocket.accept();
                connections.add(new Connection(clientSocket , connID , this , this.dbmanager));
                connID++;
                int j = 0;
                for(Connection temp: connections){
                    System.out.println("[" + j + "]" + temp.getInet());
                    j++;
                }                
               }
            catch(Exception e){
                System.out.println("PollCycle-> Exception: " + e.getMessage());
                
            }            
        }        
    }
    
    public synchronized void removeConnection(InetAddress addr) throws IOException{
        for(Connection temp: connections){
                    if(temp.getInet() == addr){
                        try{
                            temp.getSoc().close();
                        }
                        catch(IOException e){
                            System.out.println("removeConnection-> IOException: " + e.getMessage());
                            System.out.println("================StackTrace================");  
                            e.printStackTrace();
                            System.out.println("----------------StackTrace----------------");
                        }
                        
                        connections.remove(temp);
                    }
                }  
//        connections.remove(id);
    }
    
}
