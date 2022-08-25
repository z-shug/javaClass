/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author zshug
 */
public class Inventory {
    String itemName;
    int inventoryCount; 
    BigDecimal itemCost;
    
    public Inventory(String itemName){
        this.itemName = itemName;
    }
    
    public Inventory(String itemName,int inventoryCount, BigDecimal itemCost ){
        this.itemName = itemName;
        this.inventoryCount = inventoryCount;
        this.itemCost = itemCost;
    }
    
    public String getItemName(){
        return itemName;
    }
    
    public void setItemName(String itemName){
        this.itemName = itemName;
    }
    
    public int getInventoryCount(){
        return inventoryCount;
    }
    
    public void setInventoryCount(int inventoryCount){
        this.inventoryCount = inventoryCount;
    }
    
    public BigDecimal getItemCost(){
        return itemCost;
    }
    
    public void setItemCost(BigDecimal itemCost){
        this.itemCost = itemCost;
    }
    
}
