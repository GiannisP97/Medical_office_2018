/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerUtill;

import DBEntities.MediclaUsers;
import SoftwareEngineering.Appointment;
import SoftwareEngineering.DBManager;
import SoftwareEngineering.Order;
import SoftwareEngineering.Patient;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StreamCorruptedException;
import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    MediclaUsers md;
    
    //---Singletons
    private ConnectionManager conman;
    private DBManager dbmanager;
//    static ServerGUI gui;
    //-------------
    
    int raw;
    char directive;
    char code; 
    
    boolean logged = false;
    PrintWriter out;
    BufferedReader in;
    
    Connection(Socket s , int ID , ConnectionManager cm , DBManager db){
        super(String.valueOf(ID));
        soc = s;
        id = ID;
        System.out.println(id);
        addr = soc.getInetAddress();      
//        conman = ConnectionManager.getInstance();
        conman = cm; // Need to check for Singleton Behavior
        dbmanager = DBManager.getInstance();
//        gui = ServerGUI.getInstance();
//        System.out.println(addr);
        directiveQueue = new ArrayList<>();
        sendQueue = new ArrayList<>();
        
        System.out.print("Client");
        System.out.println(addr);
//        gui.printToOutput("Client" + addr.toString());
        
        this.start();
    }
    
    @Override
    public void run() {
        try{
            System.out.println(addr);
                
            try{
                out =  new PrintWriter(soc.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
                raw = in.read();
                directive = (char) raw;
                raw = in.read();
                code = (char) raw;
                System.out.print(directive);
                System.out.println(code);
                in.skip(2);
                System.out.println(raw);
                if(directive == 'L' && code == '2'){
                        String cuser;
                        String cpass;
                        cuser = in.readLine();
                        System.out.println("cuser " + cuser);                    
                        cpass = in.readLine();
                        System.out.println("cpass " + cpass);
                        MediclaUsers dbuser = null;
                        try{
                            dbuser = dbmanager.findMedicalUser(cuser);
                            md = dbuser;
                        }
                        catch(NullPointerException e){
                            System.out.println("Login-> IOException: " + e.getMessage());
                            System.out.println("================StackTrace================");  
                            e.printStackTrace();
                            System.out.println("----------------StackTrace----------------");
//                            this.sendMessage("L0");
                            out.println("L0");
                            System.out.println("Client:" + addr + " Failed to Log In!- Wrong Username");
                            
                            //---ServerOutput-------
//                            gui.printToOutput(this.getName() + ":Login-> IOException: " + e.getMessage());
                            //---ServerOutput-------
                            conman.removeConnection(addr);
                            return;
                        }
                        if(dbuser == null){
                            this.sendMessage("L0");
                            System.out.println("Client:" + addr + " Failed to Log In!- Wrong Username");
                            //---ServerOutput-------
//                            gui.printToOutput(this.getName() + ":" + "Client:" + addr + " Failed to Log In!- Wrong Username");
                            //---ServerOutput-------
                            conman.removeConnection(addr);
                            return;
                        }
                        System.out.println("dbuser: " + dbuser.getUsername());
                        System.out.println("dbuser: " + dbuser.getPassword());
                        
                        if(cuser.equals(dbuser.getUsername()) && cpass.equals(dbuser.getPassword())){
//                            out.println("L1");
                            this.sendMessage("L1");
                            logged = true;
                            LocalDateTime date = null;
                            date = date.now();
                            System.out.println("Client:" + addr + " Logged In! - " + date);
                            //---ServerOutput-------
//                            gui.printToOutput(this.getName() + ":" + "Client:" + addr + " Logged In! - " + date);
                            //---ServerOutput-------
                        }
                        else{
//                            out.println("L0");
                            this.sendMessage("L0");
                            System.out.println("Client:" + addr + " Failed to Log In!");
                            //---ServerOutput-------
//                            gui.printToOutput(this.getName() + ":" + "Client:" + addr + " Failed to Log In!- Wrong Username");
                            //---ServerOutput-------
                            conman.removeConnection(addr);
                        }
                }
                
                           
            }        
            catch (IOException ex) {
                System.out.println("Login-> IOException: " + ex.getMessage());
                ex.printStackTrace();
            }
            
//            int raw;
//            char directive;
//            char code;
            while(logged){
                if(in.ready()){
                    raw = in.read();  
                              
                if(raw != -1){
                    directive = (char) raw;
                    
                    System.out.print(directive);
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
//                                    ObjectInputStream ois = new ObjectInputStream(soc.getInputStream());
//                                    Appointment ap = (Appointment)ois.readObject();
                                    Appointment ap = this.recvMessage();
//                                    String str = this.recvMessage();
//                                    System.out.println(str);
                                    System.out.println("Appointment ID: " + ap.getID());
                                    if(dbmanager.createAppointment(ap)){
                                        this.sendMessage("S0");
                                    }
                                }
                                catch(StreamCorruptedException e){
                                    System.out.println("Exception: " + e.getMessage());  
                                    System.out.println("================StackTrace================");  
                                    e.printStackTrace();
                                    System.out.println("----------------StackTrace----------------");
                                    conman.removeConnection(addr);
                                    soc.close();
                                    logged = false;
                                }
                                
                                break;
                            case '2':{
                                Patient pt = this.recvMessage();
                                boolean iscreated = this.dbmanager.createPatient(pt);
                                if(iscreated){
                                    System.out.println("Patient Created Succesfully!!!");
                                    out.println("S0");
                                }
                                else{
                                    System.out.println("Failed to Create!!!");
                                    out.println("F0");
                                }
                                break;                                
                            }
                            case '3':{
                                
                                Order or = this.recvMessage();
                                int orid = this.dbmanager.createRestock(this.md.getUserId(), "" , null);
                                String fin = System.getProperty("user.dir") + "/Orders/order_" + orid + ".ord";
                                if(this.dbmanager.updateRestock(this.md.getUserId(), fin, orid, this.dbmanager.convertToDateViaSqlDate(or.getOrderDate()))){
                                    try{
                                        FileOutputStream fstream = new FileOutputStream(fin);
                                        ObjectOutputStream foos = new ObjectOutputStream(fstream);
                                        foos.writeObject(or);  
                                        out.println("S0");                                        
                                    }
                                    catch(IOException e){
                                        System.out.println("Connection.run()-> IOException: " + e.getMessage());
                                        System.out.println("================StackTrace================");  
                                        e.printStackTrace();
                                        System.out.println("----------------StackTrace----------------");
                                        dbmanager.deleteRestock(orid);
                                        out.println("F0");
                                    }
                                }
                                                              
                                break;
                            }
                        }
                        break;
                    case 'G':
                        raw = in.read();
                        code = (char) raw;
                        System.out.println(code);
                        switch(code){
                            case '0':{
                                LocalDate ldate = LocalDate.now();
                                System.out.println(ldate);
//                                date = date.getDate();
                                List<Appointment> aplist = dbmanager.fetchAppointments(this.md.getUserId(), this.dbmanager.convertToDateViaSqlDate(ldate));
//                                Appointment aplist = new Appointment();
                                boolean issent = this.sendMessage(aplist);
                                if(issent){
                                    System.out.println("Sent Succesfully!!!");
//                                    out.println("S0");
                                }
                                else{
                                    System.out.println("Failed to Sent!!!");
//                                    out.println("F0");
                                }
                                break;
                            }                                
                            case '1':{
                                LocalDate sdate , edate;
                                sdate = this.recvMessage();
                                edate = this.recvMessage();
                                System.out.println("sdate: " + sdate.toString());
                                System.out.println("sdate->Date: " + this.dbmanager.convertToDateViaSqlDate(sdate));
                                System.out.println("edate: " + edate.toString());
                                System.out.println("edate->Date: " + this.dbmanager.convertToDateViaSqlDate(edate));
                                List<Appointment> aplist = dbmanager.fetchAppointments(this.md.getUserId(), this.dbmanager.convertToDateViaSqlDate(sdate) , this.dbmanager.convertToDateViaSqlDate(edate));
                                boolean issent = this.sendMessage(aplist);
                                if(issent){
                                    System.out.println("Sent Succesfully!!!");
                                }
                                else{
                                    System.out.println("Failed to Sent!!!");
                                }
                                break;
                            } 
                            case '2':{
                                boolean issent = this.sendMessage(this.md.getName());
                                System.out.println("Sent: " + md.getName());
                                if(issent){
                                    System.out.println("Sent Succesfully!!!");
                                }
                                else{
                                    System.out.println("Failed to Sent!!!");
                                }
                                break;
                            }
                            case '4':{
                                int uid = this.recvMessage();
                                boolean issent = this.sendMessage(this.dbmanager.findMedicalUser(uid).getName());
                                if(issent){
                                    System.out.println("Sent Succesfully!!!");
                                }
                                else{
                                    System.out.println("Failed to Sent!!!");
                                }
                                break;
                            }
                            
                        }                        
                        break;
                }
                
                }
                else{
                    logged = false;
                }
            }
            }
//            soc.close();
            conman.removeConnection(addr);
            System.out.println("Closed");
        }
        catch(Exception e){
            System.out.println("Connection.run()-> IOException: " + e.getMessage());
            System.out.println("================StackTrace================");  
            e.printStackTrace();
            System.out.println("----------------StackTrace----------------");
            try {
                conman.removeConnection(addr);
            } catch (IOException ex) {
                System.out.println("================StackTrace================");  
                e.printStackTrace();
                System.out.println("----------------StackTrace----------------");
            }
        }
    }
    
    public InetAddress getInet(){
        return addr;
    }
    
    public int getID(){
        return id;
    }
    
    public <T> boolean sendMessage(T obj) throws IOException{
        ObjectOutputStream oos = new ObjectOutputStream(soc.getOutputStream());
        try{
            oos.writeObject(obj);  
            oos = null;
            return true;
        }
        catch(IOException e){
            System.out.println("sendMessage()-> IOException: " + e.getMessage());
            System.out.println("================StackTrace================");  
            e.printStackTrace();
            System.out.println("----------------StackTrace----------------");
//            oos.close();
            oos = null;
            return false;
        }
    }
    
    public <T> T recvMessage() throws IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream(soc.getInputStream());
        try{
            T temp = (T) ois.readObject();
            ois = null;
            return temp;
        }
        catch(IOException e){
            System.out.println("recvMessage()-> IOException: " + e.getMessage());
            System.out.println("================StackTrace================");  
            e.printStackTrace();
            System.out.println("----------------StackTrace----------------");
            ois = null;
            return null;
        }
        catch(ClassNotFoundException e){
            System.out.println("recvMessage()-> ClassNotFoundException: " + e.getMessage());
            System.out.println("================StackTrace================");  
            e.printStackTrace();
            System.out.println("----------------StackTrace----------------");
            ois = null;
            return null;
        }
    }
    
    public Socket getSoc(){
        return this.soc;
    }
}


