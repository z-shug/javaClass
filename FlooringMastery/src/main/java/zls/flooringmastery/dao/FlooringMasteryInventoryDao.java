/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.flooringmastery.dao;

import java.util.HashMap;
import zls.flooringmastery.dto.Inventory;

/**
 *
 * @author zshug
 */
public interface FlooringMasteryInventoryDao {
    
    HashMap<String,Inventory> getAllInventory()throws FilePersistenceException;
    
    Inventory getInventoryItem(String statAbb)throws FilePersistenceException;
}
