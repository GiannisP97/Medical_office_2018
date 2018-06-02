/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftwareEngineering;

/**
 *
 * @author Giannis
 */
public class Doctor extends User{
    
    private String expertise;
    
    
    public Doctor(){
        super();
    }
    
    public Doctor(int id,int[] per,String name,String sur,String phone,String expertise){
        super(id,per,name,sur,phone);
        this.expertise = expertise;
    }
    
    public String getExpertise(){
        return expertise;
    }
    
    
    
}
