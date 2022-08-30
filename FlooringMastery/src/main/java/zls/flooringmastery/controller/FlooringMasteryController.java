/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.flooringmastery.controller;

import java.util.HashMap;
import java.util.List;
import zls.flooringmastery.dao.FilePersistenceException;
import zls.flooringmastery.dto.Inventory;
import zls.flooringmastery.dto.Order;
import zls.flooringmastery.dto.Tax;
import zls.flooringmastery.service.FlooringMasteryServiceLayer;
import zls.flooringmastery.ui.FlooringMasteryView;
import zls.flooringmastery.ui.InvalidDateException;

/**
 *
 * @author zshug
 */
public class FlooringMasteryController {
    private FlooringMasteryView view;
    private FlooringMasteryServiceLayer service;
    
    public FlooringMasteryController (FlooringMasteryView view,FlooringMasteryServiceLayer service ){
        this.view = view;
        this.service = service;
    }
    
    public void start()throws FilePersistenceException, InvalidDateException{
        //try{        
            while(true){
                int menuChoice; 
                menuChoice = view.mainMenu();
                switch (menuChoice) {
                    case 1: 
                        displayOrders();
                        break;
                    case 2 :
                        createNewOrder();
                        break;
                    case 3 : 
                        editOrder();
                        break;
                    case 4 : 
                        removeOrder();
                        break;
                    case 5 :
                        System.exit(0);
                        break;
                    default:
                        //unknownCommand();

                }       
            }
        //}catch(FilePersistenceException e) {
           // view.displayErrorMessage(e.getMessage());
       // }
 
    }
    
    public void displayOrders() throws InvalidDateException, FilePersistenceException{
        String userDate = displayOrdersDateInput();
        List<Order> returnedOrder = getOrders(userDate);
        view.displayReturnedOrderList(returnedOrder);
    }
    
    public void createNewOrder()throws FilePersistenceException{
        HashMap<String,Tax> taxes = getTaxes();
        HashMap<String,Inventory> products = getInventory();
        Order newOrderInput = view.addOrderUserInput(taxes,products);
        service.calculateMaterialCost(newOrderInput);
        service.calculateLaborCost(newOrderInput);
        service.calculateTax(newOrderInput);
        service.calculateTotal(newOrderInput);
        service.setOrderNumber(newOrderInput);
        int userChoice = view.displayCreatedOrder(newOrderInput);
        switch(userChoice){
            case 1:
                service.createNewOrder(newOrderInput);
                break;
            case 2:
                break;
        }    
    }
    
    public void editOrder() throws FilePersistenceException{
        HashMap<String,Tax> taxes = getTaxes();
        HashMap<String,Inventory> products = getInventory();
        String orderDate = view.readOrderDate();
        int orderNumber = view.readOrderNumber();
        Order retrievedOrder = service.getOrder(orderDate, orderNumber);
        Order editedOrder = view.editOrder(retrievedOrder,taxes,products);
        service.calculateMaterialCost(editedOrder);
        service.calculateLaborCost(editedOrder);
        service.calculateTax(editedOrder);
        service.calculateTotal(editedOrder);
        int userChoice = view.displayEditedOrder(editedOrder);
        switch(userChoice){
            case 1:
                service.editOrder(orderDate, editedOrder);
                break;
            case 2:
                break;
        }
    }
    
    public void removeOrder() throws FilePersistenceException{
        String orderDate = view.readOrderDate();
        int orderNumber = view.readOrderNumber();
        Order retrievedOrder = service.getOrder(orderDate, orderNumber);
        int userChoice = view.removeOrder(retrievedOrder);
        switch(userChoice){
            case 1:
                service.removeOrder(orderDate, orderNumber);
                view.displayRemovedOrderBanner();
              break;
            case 2:
                break;
        }
        
    }
    
    public String displayOrdersDateInput() throws InvalidDateException{
       String userInput = view.displayOrdersInput();
       return userInput;
    }
    
    public List<Order> getOrders(String date)throws FilePersistenceException{
        return service.displayOrders(date);
    }
    
 
    public HashMap<String,Tax> getTaxes()throws FilePersistenceException{
        return service.getAllTaxes();
    }
    
    public HashMap<String,Inventory> getInventory()throws FilePersistenceException{
        return service.getAllInventory();
    }
    
    
    
}
