/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftwareEngineering;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import DBEntities.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author ilias
 */
public final class DBManager {
    
    private static DBManager DBManagerInstance = null;
    
    public static DBManager getInstance(){
        if(DBManagerInstance == null){
            DBManagerInstance = new DBManager();      
        }
        return DBManagerInstance;
    }
    
    DBManager(){}
    
    public synchronized boolean createAppointment(Appointment obj){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Medical_Office_ServerPU" );

        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction( ).begin( );

        Appointments temp = new Appointments();
        temp.setAppointmentDay(obj.getDate());
        temp.setDoctorPrescription(obj.getPrescription());
        MediclaUsers md = findMedicalUser(obj.getDoctorId());
        System.out.println("User ID: " + md.getUserId());
//        MediclaUsers md =  new MediclaUsers();
        temp.setMediclauserId(md);
        System.out.println("amka: " + obj.getPatient().getAMKA());
        Patients pt = entitymanager.find(Patients.class, obj.getPatient().getAMKA());
        System.out.println("Patient: " + pt.getName());
////        Patients pt = new Patients();
        temp.setPatientAMKA(pt);
        entitymanager.persist( temp );
        entitymanager.getTransaction( ).commit( );
//        
////        System.out.println();
        obj.setID(temp.getAppointmentId());
        entitymanager.close( );
        emfactory.close( );

        return true;
    }
    
    public synchronized boolean updateAppointment(Appointment obj){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Medical_Office_ServerPU" );
        
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        
        Appointments temp = entitymanager.find(Appointments.class, obj.getID());
        
//        System.out.println(obj.getDate());
        if(temp == null){
            System.out.println("False");
            return false;
        }

        temp.setAppointmentDay(obj.getDate());
        temp.setDoctorPrescription(obj.getPrescription());
//        temp.setMediclauserId(obj.getDoctorId());
//        temp.setPatientAMKA(Integer.parseInt(obj.getPetient().getAMKA()));
        MediclaUsers md = entitymanager.find(MediclaUsers.class, obj.getDoctorId());
        temp.setMediclauserId(md);
        Patients pt = entitymanager.find(Patients.class, obj.getPatient().getAMKA());
        temp.setPatientAMKA(pt);
        
        entitymanager.getTransaction( ).commit( );
        
        entitymanager.close( );
        emfactory.close( );
        return true;
    }
    
    public synchronized boolean deleteAppointment(Appointment obj){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Medical_Office_ServerPU" );
        
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        
        Appointments temp = entitymanager.find(Appointments.class, obj.getID());
        
        entitymanager.remove(temp);
        
        entitymanager.getTransaction( ).commit( );
        
        entitymanager.close( );
        emfactory.close( );
        
        return true;
    }

   public synchronized Integer createRestock(int meduserid , String fn){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Medical_Office_ServerPU" );

        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction( ).begin( );

        Restocks temp = new Restocks();
        MediclaUsers md = entitymanager.find(MediclaUsers.class, meduserid);
        temp.setMedicaluserId(md);
        temp.setFileName(fn);
        
        entitymanager.persist( temp );
        entitymanager.getTransaction( ).commit( );
        
        entitymanager.close( );
        emfactory.close( );

        return temp.getRestockId();
    }
    
    
    public synchronized boolean updateRestock(int meduserid , String fn , int rid){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Medical_Office_ServerPU" );
        
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        
        Restocks temp = entitymanager.find(Restocks.class, rid);
        
        MediclaUsers md = entitymanager.find(MediclaUsers.class, meduserid);
        temp.setMedicaluserId(md);
        temp.setFileName(fn);
        
        entitymanager.getTransaction( ).commit( );
        
        entitymanager.close( );
        emfactory.close( );
        return true;
    }
    
    public synchronized boolean deleteRestock(int rid){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Medical_Office_ServerPU" );
        
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        
        Restocks temp = entitymanager.find(Restocks.class, rid);
        
        entitymanager.remove(temp);
        
        entitymanager.getTransaction( ).commit( );
        
        entitymanager.close( );
        emfactory.close( );
        
        return true;
    }
    
    public synchronized MediclaUsers createMedicalUser(MediclaUsers obj){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Medical_Office_ServerPU" );

        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction( ).begin( );
        
        entitymanager.persist( obj );
        entitymanager.getTransaction( ).commit( );
        
        entitymanager.close( );
        emfactory.close( );
        
        System.out.println("Medical User: " + obj.getUserId());
        
        return obj;
    }
    
    public synchronized boolean updateMedicalUser(MediclaUsers obj){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Medical_Office_ServerPU" );
        
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        
        MediclaUsers md = entitymanager.find(MediclaUsers.class, obj.getUserId());
        System.out.println("Medical User: " + md.getName());
        md.setAfm(obj.getAfm());
        md.setName(obj.getName());
        md.setPassword(obj.getPassword());
        md.setUserType(obj.getUserType());
        System.out.println("Medical User: " + md.getName());
//        md.setAfm(156126);
        
        entitymanager.getTransaction( ).commit( );
        
        entitymanager.close( );
        emfactory.close( );
        return true;
    }
    
    public synchronized boolean deleteMedicalUser(MediclaUsers obj){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Medical_Office_ServerPU" );
        
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        
        MediclaUsers md = entitymanager.find(MediclaUsers.class, obj.getUserId());
        
        entitymanager.remove(md);
        
        entitymanager.getTransaction( ).commit( );
        
        entitymanager.close( );
        emfactory.close( );
        
        return true;
    }
    
//    public synchronized Patients createPatient(Patient obj){
//        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Medical_Office_ServerPU" );
//
//        EntityManager entitymanager = emfactory.createEntityManager( );
//        entitymanager.getTransaction( ).begin( );
//        
//        Patients pt = new Patients();
//        pt.setAmka(obj.getAMKA());
//        pt.setBirthDate(obj.g);
//        pt.setContactNumber(obj.getContactNumber());
//        pt.setName(obj.getName());
//        pt.setSex(obj.getSex());
//        
//        entitymanager.persist( pt );
//        entitymanager.getTransaction( ).commit( );
//        
//        entitymanager.close( );
//        emfactory.close( );
//        
//        System.out.println("Patient: " + obj.getAmka());
//        
//        return pt;
//    }
    
    public synchronized boolean updatePatient(Patients obj){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Medical_Office_ServerPU" );
        
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        
        Patients pt = entitymanager.find(Patients.class, obj.getAmka());
        pt.setAmka(obj.getAmka());
        pt.setBirthDate(obj.getBirthDate());
        pt.setContactNumber(obj.getContactNumber());
        pt.setName(obj.getName());
        pt.setSex(obj.getSex());
        System.out.println("Patient: " + pt.getName());
//        md.setAfm(156126);
        
        entitymanager.getTransaction( ).commit( );
        
        entitymanager.close( );
        emfactory.close( );
        return true;
    }
    
    public synchronized boolean deletePatient(Patients obj){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Medical_Office_ServerPU" );
        
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        
        Patients pt = entitymanager.find(Patients.class, obj.getAmka());
        
        entitymanager.remove(pt);
        
        entitymanager.getTransaction( ).commit( );
        
        entitymanager.close( );
        emfactory.close( );
        
        return true;
    }
    //Patients
    
    
    
    
    
    
    
    
    public synchronized MediclaUsers findMedicalUser(int id){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Medical_Office_ServerPU" );
        
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        
        MediclaUsers temp = entitymanager.find(MediclaUsers.class, id);
        
//        System.out.println(obj.getDate());

        entitymanager.getTransaction( ).commit( );
        
        entitymanager.close( );
        emfactory.close( );
        return temp;
    }
    
    public synchronized MediclaUsers findMedicalUser(String username){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Medical_Office_ServerPU" );
        
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        
        TypedQuery<MediclaUsers> query= entitymanager.createQuery("SELECT md FROM MediclaUsers AS md WHERE md.name = :username",MediclaUsers.class); 
        query.setParameter("username", username);
//        query 
        MediclaUsers temp;
        try{
            temp = query.getSingleResult();
        }
        catch(NoResultException e){
            System.out.println("findMedicalUser(String username)-> NoResultException: " + e.getMessage());
            System.out.println("================StackTrace================");  
            e.printStackTrace();
            System.out.println("----------------StackTrace----------------");
            return null;
        }
//        System.out.println(query.getParameter("name"));
//        System.out.println(temp.getName());
//        System.out.println(obj.getDate());
        entitymanager.getTransaction( ).commit( );
        
        entitymanager.close( );
        emfactory.close( );
        return temp;
    }
    
    public synchronized Patients findPatient(int amka){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Medical_Office_ServerPU" );
        
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        
        Patients temp = entitymanager.find(Patients.class, amka);
        
//        System.out.println(obj.getDate());

        entitymanager.getTransaction( ).commit( );
        
        entitymanager.close( );
        emfactory.close( );
        return temp;
    }
    
    public synchronized Appointment findAppointment(int id){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Medical_Office_ServerPU" );
        
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        
         
        Appointments temp = entitymanager.find(Appointments.class, id);
        Appointment output  = temp.toAppointment();
//        System.out.println(obj.getDate());

        entitymanager.getTransaction( ).commit( );
        
        entitymanager.close( );
        emfactory.close( );
        return output;
    }
    
    public synchronized List<Appointment> fetchAppointments(int meduserid , Date date){
        List<Appointment> aplist = new ArrayList<Appointment>();
        
        MediclaUsers md = findMedicalUser(meduserid);
        if(md.getUserType() == 1){
            
            for(Appointments temp: md.getAppointmentsList()){
                System.out.println(temp.getAppointmentDay());
                if(temp.getAppointmentDay().equals(date)){
                    aplist.add(temp.toAppointment());
                }
            }
        }
        else if(md.getUserType() == 2){
            
            for(MediclaUsers mdl: this.fetchAllMedicalUsers()){
                for(Appointments temp: mdl.getAppointmentsList()){
                System.out.println(temp.getAppointmentDay());
                if(temp.getAppointmentDay().equals(date)){
                    aplist.add(temp.toAppointment());
                    }
                }
            }
        }
        else{
            System.out.println("Wrong User Type :" + md.getUserType());
            return null;
        }
        
        return aplist;
    }
    
    public synchronized List<Appointment> fetchAppointments(int meduserid , Date sdate , Date edate){
        List<Appointment> aplist = new ArrayList<Appointment>();
        
        MediclaUsers md = findMedicalUser(meduserid);
        if(md.getUserType() == 1){
            
            for(Appointments temp: md.getAppointmentsList()){
                System.out.println(temp.getAppointmentDay());
                if(sdate.compareTo(temp.getAppointmentDay()) >= 0 && edate.compareTo(temp.getAppointmentDay()) <= 0 ){
                    aplist.add(temp.toAppointment());
                    }
            }
        }
        else if(md.getUserType() == 2){
            
            for(MediclaUsers mdl: this.fetchAllMedicalUsers()){
                for(Appointments temp: mdl.getAppointmentsList()){
                System.out.println(temp.getAppointmentDay());
                if(sdate.compareTo(temp.getAppointmentDay()) >= 0 && edate.compareTo(temp.getAppointmentDay()) <= 0 ){
                    aplist.add(temp.toAppointment());
                    }
                }
            }
        }
        else{
            System.out.println("Wrong User Type :" + md.getUserType());
            return null;
        }
        
        return aplist;
    }
    

    public synchronized List<MediclaUsers> fetchAllMedicalUsers(){
        
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Medical_Office_ServerPU" );
        
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        
        TypedQuery<MediclaUsers> query= entitymanager.createQuery("SELECT md FROM MediclaUsers md",MediclaUsers.class); 
        List<MediclaUsers> temp;
        try{
            temp = query.getResultList();
        }
        catch(NoResultException e){
            System.out.println("fetchAllMedicalUsers()-> NoResultException: " + e.getMessage());
            System.out.println("================StackTrace================");  
            e.printStackTrace();
            System.out.println("----------------StackTrace----------------");
            return null;
        }
//        System.out.println(query.getParameter("name"));
//        System.out.println(temp.getName());
//        System.out.println(obj.getDate());
        entitymanager.getTransaction( ).commit( );
        
        entitymanager.close( );
        emfactory.close( );
        return temp;
        
    }
    
public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
    return java.sql.Date.valueOf(dateToConvert);
}
}