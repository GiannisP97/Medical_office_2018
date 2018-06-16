/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftwareEngineering;

import java.io.Serializable;
import java.time.LocalTime;
/**
 *
 *
 * @author Giannis
 */
public class Patient implements Serializable{
    private String name;
    private String surname;
    private String AMKA;
    private LocalTime birth_date;
    private String phone_number;
    private int gender;
    
    
    public Patient(){
        this.AMKA="";
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
    
    public Patient(String AMKA,String name,String surname,String phonenumber,int g,LocalTime d){
        this.AMKA = AMKA;
        this.name = name;
        this.surname = surname;
        this.phone_number = phonenumber;
        this.birth_date = d;
        this.gender = g;
        
        
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
        
    

    public void setAMKA(String AMKA){
        this.AMKA = AMKA;
    }

    public void setGender(short gender){
        this.gender = gender;
    }
    

    public void setBirth(LocalTime birthdate){
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
