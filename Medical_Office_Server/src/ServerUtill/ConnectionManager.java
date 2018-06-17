/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerUtill;

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

/**
 *
 * @author ilias
 */
public class ConnectionManager implements Runnable{
    private ConcurrentLinkedQueue<Connection> connections;
    private boolean PollCycle;
    static final int portNumber = 4444;
    static int connID = 0;
    ServerSocket serverSocket;
    
    
    public ConnectionManager() throws IOException{
        connections = new ConcurrentLinkedQueue();
        PollCycle = true;
        
        try{
            serverSocket = new ServerSocket(portNumber);
        }
        catch(Exception e){
            
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
                connections.add(new Connection(clientSocket , connID , this));
                connID++;
                int j = 0;
                for(Connection temp: connections){
                    System.out.println("[" + j + "]" + temp.getInet());
                    j++;
                }                
               }
            catch(Exception e){
            
            }            
        }        
    }
    
    public void removeConnection(InetAddress addr){
        for(Connection temp: connections){
                    if(temp.getInet() == addr){
                        connections.remove(temp);
                    }
                }  
//        connections.remove(id);
    }
    
}
