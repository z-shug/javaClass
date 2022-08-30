/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.flooringmastery.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import zls.flooringmastery.dao.FilePersistenceException;
import zls.flooringmastery.dto.Inventory;
import zls.flooringmastery.dto.Order;
import zls.flooringmastery.dto.Tax;

/**
 *
 * @author zshug
 */
public interface FlooringMasteryServiceLayer {
    List<Order> displayOrders (String date)throws FilePersistenceException;
    
    String formatDateFile(String date);
    
    HashMap<String,Tax> getAllTaxes() throws FilePersistenceException;
    
    HashMap<String,Inventory> getAllInventory() throws FilePersistenceException;
    
    Order getOrder(String date, int orderNumber) throws FilePersistenceException;
    
    void removeOrder(String date, int orderNumber) throws FilePersistenceException;
    
    void calculateMaterialCost(Order newOrderInput)throws FilePersistenceException;
    
     void calculateLaborCost(Order newOrder)throws FilePersistenceException;
     
     void calculateTax(Order newOrder) throws FilePersistenceException;
     
     void calculateTotal(Order newOrder);
     
     void setOrderNumber(Order newOrder)throws FilePersistenceException;
     
     void createNewOrder(Order newOrder)throws FilePersistenceException;
     
     void editOrder(String orderDate, Order editedOrder) throws FilePersistenceException;
}
