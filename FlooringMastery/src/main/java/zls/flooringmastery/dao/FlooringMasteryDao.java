/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.flooringmastery.dao;

import java.util.List;
import zls.flooringmastery.dto.Order;

/**
 *
 * @author zshug
 */
public interface FlooringMasteryDao {
    
    void addOrder(int orderNumber, Order order,String orderDateFile)throws FilePersistenceException;
  
    List<Order> getAllOrders(String dateFileFormat)throws FilePersistenceException;
    
    Order getOrder(String date, int orderNumber)throws FilePersistenceException;
    
    void editOrder(String date, int orderNumber, Order order)throws FilePersistenceException;
    
    void removeOrder(String date, int orderNumber)throws FilePersistenceException;
}
