/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftwareEngineering;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ilias
 */
public final class DBManager {
    
    
    DBManager(){}
    
    public boolean createAppointment(Appointment obj){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Medical_officePU" );

        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction( ).begin( );

        Appointments temp = new Appointments();
        temp.setAppointmentDay(obj.getDate());
        temp.setDoctorPrescription(obj.getPrescription());
        temp.setMediclauserId(obj.getDoctorId());
        temp.setPatientAMKA(Integer.parseInt(obj.getPetient().getAMKA()));
        entitymanager.persist( temp );
        entitymanager.getTransaction( ).commit( );

        entitymanager.close( );
        emfactory.close( );

        return true;
    }
    
    public boolean updateAppointment(Appointment obj){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Medical_officePU" );
        
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        
        Appointments temp = entitymanager.find(Appointments.class, obj.getID());
        
//        System.out.println(obj.getDate());
        temp.setAppointmentDay(obj.getDate());
        temp.setDoctorPrescription(obj.getPrescription());
        temp.setMediclauserId(obj.getDoctorId());
        temp.setPatientAMKA(Integer.parseInt(obj.getPetient().getAMKA()));
        
        entitymanager.getTransaction( ).commit( );
        
        entitymanager.close( );
        emfactory.close( );
        return true;
    }
    
    public boolean deleteAppointment(Appointment obj){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Medical_officePU" );
        
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        
        Appointments temp = entitymanager.find(Appointments.class, obj.getID());
        
        entitymanager.remove(temp);
        
        entitymanager.getTransaction( ).commit( );
        
        entitymanager.close( );
        emfactory.close( );
        
        return true;
    }
    
//    public Appointment findAppointment()
    
    public boolean createRestock(int meduserid , String fn , int supid){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Medical_officePU" );

        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction( ).begin( );

        Restocks temp = new Restocks();
        
        temp.setMedicaluserId(meduserid);
        temp.setFileName(fn);
//        temp.setSupplierId(supid); // find supplier
        
        entitymanager.persist( temp );
        entitymanager.getTransaction( ).commit( );

        entitymanager.close( );
        emfactory.close( );

        return true;
    }
    
    public boolean updateARestock(int meduserid , String fn , int supid){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Medical_officePU" );
        
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        
        Restocks temp = entitymanager.find(Restocks.class, supid);
        
        temp.setMedicaluserId(meduserid);
        temp.setFileName(fn);
//        temp.setSupplierId(supid); // find supplier
        
        entitymanager.getTransaction( ).commit( );
        
        entitymanager.close( );
        emfactory.close( );
        return true;
    }
    
    public boolean deleteRestock(int resid){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Medical_officePU" );
        
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        
        Restocks temp = entitymanager.find(Restocks.class, resid);
        
        entitymanager.remove(temp);
        
        entitymanager.getTransaction( ).commit( );
        
        entitymanager.close( );
        emfactory.close( );
        
        return true;
    }
    
    public boolean createSupplier(Supplier obj){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Medical_officePU" );

        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction( ).begin( );

        Suppliers temp = new Suppliers();
        
        temp.setContactMail(obj.getSupMail());
        temp.setContactNumber(obj.getSupNumber());
//        temp.setRestocksCollection(obj.);
        temp.setSupplierName(obj.getSupName());
        
        entitymanager.persist( temp );
        entitymanager.getTransaction( ).commit( );

        entitymanager.close( );
        emfactory.close( );

        return true;
    }
    
    public boolean updateASupplier(Supplier obj){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Medical_officePU" );
        
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        
        Suppliers temp = entitymanager.find(Suppliers.class, obj.getSupID());
        
        temp.setContactMail(obj.getSupMail());
        temp.setContactNumber(obj.getSupNumber());
//        temp.setRestocksCollection(obj.);
        temp.setSupplierName(obj.getSupName());
        
        entitymanager.getTransaction( ).commit( );
        
        entitymanager.close( );
        emfactory.close( );
        return true;
    }
    
    public boolean deleteSupplier(Supplier obj){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Medical_officePU" );
        
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        
        Suppliers temp = entitymanager.find(Suppliers.class, obj.getSupID());
        
        entitymanager.remove(temp);
        
        entitymanager.getTransaction( ).commit( );
        
        entitymanager.close( );
        emfactory.close( );
        
        return true;
    }
    
    
    
}
