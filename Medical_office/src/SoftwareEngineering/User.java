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
public abstract class User {
    protected int user_id;
    protected int[] permissions;
    protected String name;
    protected String surname;
    protected String phone_number;
    
    
    public User(){
        user_id = -1;
        name = null;
        surname = null;
        phone_number = null;
        permissions = null;
    }
    
    public User(int id,int[] per,String name,String sur,String phone){
        this.user_id = id;
        this.permissions = per;
        this.phone_number = phone;
        this.name = name;
        this.surname = sur;
    }
    
    public int getId(){
        return user_id;
    }
    
    public int[] getPermissions(){
        return permissions;
    }
    
    public String getName(){
        return name;
    }
    
    public String getSurname(){
        return surname; 
    }
    
    public String getPhone(){
        return phone_number;
    }
   
}
