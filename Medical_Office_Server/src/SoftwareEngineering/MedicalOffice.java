/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftwareEngineering;

import java.time.LocalDateTime;

/**
 *
 * @author Giannis
 */
public class MedicalOffice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DBManager dbmanager = new DBManager();
        Appointment ap = new Appointment();
        dbmanager.createAppointment(ap);
        
        Patient p = new Patient();
        ap = new Appointment(p,LocalDateTime.now(),"farmaka",1000);
        dbmanager.updateAppointment(ap);
    }

    private static Object LocalDateTime() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
