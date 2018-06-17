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

/**
 *
 * @author ilias
 */
public final class DBManager {
    
    
    DBManager(){}
    
    public boolean createAppointment(Appointment obj){
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
    
    public boolean updateAppointment(Appointment obj){
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
    
    public boolean deleteAppointment(Appointment obj){
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

   public Integer createRestock(int meduserid , String fn){
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
    
    
    public boolean updateRestock(int meduserid , String fn , int rid){
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
    
    public boolean deleteRestock(int rid){
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
    
    public MediclaUsers createMedicalUser(MediclaUsers obj){
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
    
    public boolean updateMedicalUser(MediclaUsers obj){
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
    
    public boolean deleteMedicalUser(MediclaUsers obj){
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
    
//    public Patients createPatient(Patient obj){
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
    
    public boolean updatePatient(Patients obj){
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
    
    public boolean deletePatient(Patients obj){
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
    
    
    
    
    
    
    
    
    public MediclaUsers findMedicalUser(int id){
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
    
    public Patients findPatient(int amka){
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
    
    public Appointment findAppointment(int id){
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
}