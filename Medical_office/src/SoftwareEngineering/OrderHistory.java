/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftwareEngineering;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Giannis
 */
public class OrderHistory implements Serializable{
    
    private ArrayList<Order> order = new ArrayList<Order>();
    
    
    public OrderHistory (){
        
    }
    
    public void addOrder(Order o){
        order.add(o);
    }
    
}
