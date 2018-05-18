/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftwareEngineering;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Giannis
 */
public class Appointment implements Serializable{
    
    private int id ;
    private Patient p;
    private Calendar date;
    private String prescription;
    private int doctor_id;
    
    public Appointment()
    {
        id = 0;
        p = null;
        date = null;
        prescription  = null;
        doctor_id = 0;
    }
    
    
    
    
    
}
