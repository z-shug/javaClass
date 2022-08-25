/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.vendingmachine.service;

import java.math.BigDecimal;
import java.util.HashMap;
import zls.vendingmachine.dao.VendingMachinePersistenceException;
import zls.vendingmachine.dto.Inventory;
import zls.vendingmachine.ui.ItemsEnum;

/**
 *
 * @author zshug
 */
public interface VendingMachineServiceLayer {
    boolean checkFunds(String userSelection, BigDecimal money)throws VendingMachinePersistenceException;
    
    HashMap<String,Inventory> getItemInventory()throws VendingMachinePersistenceException;
            
    Change returnChange(BigDecimal itemCost, BigDecimal userMoney);
    
    BigDecimal makePurchase(String item)throws VendingMachinePersistenceException;
    
    void createInventory()throws VendingMachinePersistenceException;
    
    void loadInventory()throws VendingMachinePersistenceException;
}
