package SoftwareEngineering;

//thanasi eisai vlakas
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thanasis
 */
public class StorageItem implements Serializable
{
    private String name;
    private int quantity;//testing
    
    
    public StorageItem()
    {
	this.name="";
        this.quantity=0;
    }
    
    public StorageItem(String n, int q)
    {
	this.name= n;
	this.quantity= q;
    }
    
    public String getItemName()
    {
	return this.name;
    }
    
    public int getItemQuantity()
    {
	return this.quantity;
    }
    
    public void setItemQuantity(int i)
    {
	this.quantity= i;
    }
    
    public void seyItemName(String n)
    {
	this.name= n;
    }
    
    @Override
    public String toString()
    {
	return this.name+" "+this.quantity;
    }
}
