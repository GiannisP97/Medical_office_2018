/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftwareEngineering;

import DBEntities.MediclaUsers;
import java.time.LocalDateTime;
import ServerUtill.*;
import java.io.IOException;

/**
 *
 * @author Giannis
 */
public class MedicalOffice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        DBManager dbmanager = new DBManager();
        ConnectionManager connman = new ConnectionManager();
        
//        

    }

    private static Object LocalDateTime() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}






//LocalDateTime date = null;
//        date = date.now();
//        Patient p = new Patient();
//        p.setAMKA(444444444);
//        Appointment ap = new Appointment(p,date," ",1);
////        dbmanager.createAppointment(ap);
//        ap.setID(4);
//        dbmanager.updateAppointment(ap);
//        dbmanager.deleteAppointment(ap);

//        System.out.println(dbmanager.createRestock(1, "/file01.txt"));
//        System.out.println(dbmanager.updateRestock(1, "/file83.txt" , 1));
//        System.out.println(dbmanager.deleteRestock(2));

//        MediclaUsers md = new MediclaUsers(2,"Nursey Nurse", "987654321", new Integer(2).shortValue(), 665198665);
//        dbmanager.createMedicalUser(md);
//        dbmanager.updateMedicalUser(md);
//        dbmanager.deleteMedicalUser(md);
//        ap = new Appointment(p,LocalDateTime.now(),"farmaka",1000);
//        dbmanager.updateAppointment(ap);
//        dbmanager.createPatient(p);