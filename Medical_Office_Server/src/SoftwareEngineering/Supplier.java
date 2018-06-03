/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftwareEngineering;

/**
 *
 * @author ilias
 */
public class Supplier {
    private Integer supplierId;
    private String supplierName;
    private String contactNumber;
    private String contactMail;
    
    public Supplier(){};
    
    private void setSupID(int id){
        this.supplierId = id;
    }
    public void setSupName(String name){
        this.supplierName = name;
    }
    public void setSupNumber(String num){
        this.contactNumber = num;
    }
    public void setSupMail(String mail){
        this.contactMail = mail;
    }
    
    public int getSupID(){
        return this.supplierId;
    }
    public String getSupName(){
        return this.supplierName;
    }
    public String getSupNumber(){
        return this.contactNumber;
    }
    public String getSupMail(){
        return this.contactMail;
    }
}
