/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.flooringmastery.dao;

/**
 *
 * @author zshug
 */
public interface OrderNumberDao {
    
    int getOrderNumber() throws FilePersistenceException;
    void setOrderNumber(int orderNumber) throws FilePersistenceException;
    
    
}
