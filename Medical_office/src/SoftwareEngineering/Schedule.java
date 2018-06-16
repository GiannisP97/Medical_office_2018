/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftwareEngineering;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author DeRed
 */

public class Schedule {
    private ArrayList<Appointment> appointment_list;
    
    
    public Schedule(){
        this.appointment_list = new ArrayList<Appointment>();
    }
    public boolean AddAppointment(int doctorID, Patient p, LocalDateTime d){
        
        // ΔΗΜΙΟΥΡΓΙΑ ΕΝΟΣ TMP APPOINTMENT
        Appointment tmp = new Appointment(p,d,doctorID);
        
        // ΕΛΕΓΧΟΣ ΕΑΝ ΜΠΟΡΕΙ ΝΑ ΜΠΕΙ ΣΤΟ ΠΡΟΓΡΑΜΜΑ
        for ( Appointment booking : this.appointment_list ){
            if (booking.getDate() == tmp.getDate())
                return false; //ΥΠΑΡΧΕΙ ΗΔΗ ΚΑΠΟΙΟ ΡΑΝΤΕΒΟΥ 
        }
        
        // ΕΙΣΑΓΩΓΗ ΤΟΥ ΡΑΝΤΕΒΟΥ ΕΦΟΣΟΝ ΓΙΝΕΤΕ
        this.appointment_list.add(tmp);
        return true;
    }
    
    
    
     public ArrayList getSchedule(){
         
        Collections.sort(appointment_list);
        return this.appointment_list;
    }
}
