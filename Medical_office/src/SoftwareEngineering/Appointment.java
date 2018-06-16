/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftwareEngineering;

import java.io.Serializable;

import java.time.LocalDateTime;


/**
 *
 * @author Giannis
 */
public class Appointment implements Serializable{
    
    private int id ;
    private Patient p;
    private LocalDateTime date;
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
    
    public Appointment(Patient p,LocalDateTime d,String pres,int did)
    {  
        this.p = p;
        this.date = d;
        this.prescription = pres;
        this.doctor_id = did;
    }
    
    public Appointment(Patient p,LocalDateTime d,int did)
    {  
        this.p = p;
        this.date = d;
        this.prescription = "";
        this.doctor_id = did;
    }
    
    public Patient getPetient(){
        return this.p;
    }
    
    public LocalDateTime getDate(){
        return this.date;
    }
    
    public String getPrescription(){
        return this.prescription;
    }
    
    public int getDoctorId(){
        return this.doctor_id;
    }
    

    
    
}
