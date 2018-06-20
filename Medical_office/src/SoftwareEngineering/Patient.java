/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftwareEngineering;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 *
 *
 * @author Giannis
 */
public class Patient implements Serializable{
    
    private static final long serialVersionUID= 2L;
    private String name;
    private String surname;
    private Integer AMKA;
    private LocalDate birth_date;
    private String phone_number;
    private short gender;
    
    
    public Patient(){
        this.AMKA=0;
        this.birth_date=null;
        this.gender=0;
        this.name="";
        this.surname="";
        this.phone_number="";
        
    }
    
    public Patient(Patient p){
        this.AMKA=p.AMKA;
        this.birth_date=p.birth_date;
        this.gender=p.gender;
        this.name=p.name;
        this.surname=p.surname;
        this.phone_number=p.phone_number;
    }
    
    public Patient(Integer AMKA,String name,String surname,String phonenumber,short g,LocalDate d){
        this.AMKA = AMKA;
        this.name = name;
        this.surname = surname;
        this.phone_number = phonenumber;
        this.birth_date = d;
        this.gender = (short ) g;
        
        
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    
    public void setSurname(String surname){
        this.surname = surname;
    }


    public void setPhoneNumber(String number){
        this.phone_number = number;
    }
        
    

    public void setAMKA(Integer AMKA){
        this.AMKA = AMKA;
    }

    public void setGender(short gender){
        this.gender = gender;
    }
    

    public void setBirth(LocalDate birthdate){
        this.birth_date = birthdate;
    }
    
    public String getName(){
        return this.name;
    }
    
    @Override
    public String toString(){
        return this.name+"$"+this.surname+"$"+this.phone_number+"$"+this.AMKA+String.valueOf(this.gender)+"$"+this.birth_date.toString();
    }

}
