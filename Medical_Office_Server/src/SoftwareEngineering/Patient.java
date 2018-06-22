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
    private static final long serialVersionUID = 2L;
    private String name;
    private String surname;
    private Integer AMKA;
    private LocalDate birth_date;
    private String phone_number;
    private int gender;
    
    
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

    public void setGender(int gender){
        this.gender = gender;
    }
    

    public void setBirth(LocalDate birthdate){
        this.birth_date = birthdate;
    }
    
    public Integer getAMKA(){
        return this.AMKA;
    }
    
    public LocalDate getBDate(){
        return this.birth_date;
    }
    
    public String getPhoneNum(){
        return this.phone_number;
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getSex(){
        return this.gender;
    }
}