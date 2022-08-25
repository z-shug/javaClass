/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.vendingmachine.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import zls.vendingmachine.dao.VendingMachinePersistenceException;
import zls.vendingmachine.dto.Inventory;
import zls.vendingmachine.service.Change;
import zls.vendingmachine.service.InsufficientFundsException;
import zls.vendingmachine.service.VendingMachineServiceLayer;
import zls.vendingmachine.service.VendingMachineServiceLayerImpl;
import zls.vendingmachine.ui.ItemsEnum;
import zls.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author zshug
 */
public class VendingMachineController {
    private VendingMachineView view;
    private VendingMachineServiceLayer service;
    
    String userSelcetion;
    public VendingMachineController (VendingMachineView view, VendingMachineServiceLayer service) {
        this.view = view;
        this.service = service;
    }
    
    public void run()throws InsufficientFundsException{
       try{
        service.loadInventory();
        startMenuSelection();
        BigDecimal userMoney = addMoney();
        BigDecimal itemPrice = makePurchase(userMoney);
        createDisplayChange(itemPrice, userMoney);
        view.displayThankYouBanner(); 
       }catch(VendingMachinePersistenceException e) {
           view.displayErrorMessage(e.getMessage());
       }
       
    }
    
    public void startMenuSelection()throws VendingMachinePersistenceException{
        HashMap<String,Inventory> inventory = service.getItemInventory();
        int userChoice = view.printMenuAndGetSelction(inventory);
            switch (userChoice){
             case 1:
                              
                break; 
             case 2:
                 System.exit(0);
                 break;
         }      
    } 
    
    public BigDecimal addMoney(){
        BigDecimal moneyInput = view.addMoneyMenuAndGetSelction();
        return moneyInput;
    }
    
    public String selectItem()throws VendingMachinePersistenceException{
        HashMap<String,Inventory> inventory = service.getItemInventory();
        return view.selectItemMenuAndGetSelection(inventory);
       
    }
    
    
    public boolean checkFunds(String userSelection, BigDecimal userMoney)throws VendingMachinePersistenceException{
        return service.checkFunds(userSelection, userMoney);
    }
    
    public BigDecimal makePurchase(BigDecimal userMoney)throws VendingMachinePersistenceException, InsufficientFundsException{
        boolean funds = false;
        BigDecimal itemPrice = null;
        while(false == funds){
            String userSelection = selectItem();
            if(checkFunds(userSelection,userMoney) == true){
               itemPrice =  service.makePurchase(userSelection);
                funds = true;
            }else{
             view.displayNotEnoughFundsBanner();
             funds = false; 
            }  
        }
      return itemPrice;  
    }
    
    public void createDisplayChange(BigDecimal itemCost, BigDecimal userMoney){
       Change newChange = service.returnChange(itemCost,userMoney);
       view.displayChange(newChange);
    }
    

    
}

    
    


