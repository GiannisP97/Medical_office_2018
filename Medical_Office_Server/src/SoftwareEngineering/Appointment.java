/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftwareEngineering;

import java.io.Serializable;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;


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
        p = new Patient();
        date = date.now();
        prescription  = " ";
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
    
    public LocalDateTime getLocalDate(){
        return this.date;
    }
    
    public String getPrescription(){
        return this.prescription;
    }
    
    public int getDoctorId(){
        return this.doctor_id;
    }
    
    public Date getDate(){
        
        ZonedDateTime zdt = this.date.atZone(ZoneId.systemDefault());
        Date output = Date.from(zdt.toInstant());
        if(output == null){
            return new Date(0);
        }
        return output;
    }
    
    public int getID(){
        return this.id;
    }
    
}
