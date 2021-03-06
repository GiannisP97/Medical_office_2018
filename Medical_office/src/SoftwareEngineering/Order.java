/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftwareEngineering;

import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 *
 * @author thanasis
 */
public class Order implements Serializable
{
    private ArrayList<StorageItem> item_list;
    private final LocalDate order_date;
    
    
    public Order()
    {
	this.item_list= new ArrayList<>();
	this.order_date= LocalDate.now();
    }
    
    public Order(int y, int m, int d)
    {
	this.item_list= new ArrayList<>();
	this.order_date=  LocalDate.of(y, m, d);
    }
    
    public LocalDate getOrderDate()
    {
	return this.order_date;
    }
    
    public StorageItem getStorageItem(int i)
    {
	if( i<this.item_list.size())
	{
	    return this.item_list.get(i);
	}
	return null;
    }
    
}


