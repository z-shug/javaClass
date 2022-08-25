/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.vendingmachine.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import zls.vendingmachine.dto.Inventory;
import zls.vendingmachine.ui.ItemsEnum;

/**
 *
 * @author zshug
 */
public interface VendingMachineDao {
   
    HashMap<String,Inventory> getItemInventory()throws VendingMachinePersistenceException;
    
    List<Inventory> getAllInventory()throws VendingMachinePersistenceException;
    
    boolean checkFunds(String userSelection, BigDecimal money)throws VendingMachinePersistenceException;
   
    BigDecimal makePurchase(String item)throws VendingMachinePersistenceException;
    
    void createInventory()throws VendingMachinePersistenceException;
    
    void loadStartInventory()throws VendingMachinePersistenceException;
}
