/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.vendingmachine.ui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import zls.vendingmachine.dto.Inventory;
import zls.vendingmachine.service.Change;

/**
 *
 * @author zshug
 */
public class VendingMachineView {
    private UserIO io;
    
    public VendingMachineView(UserIO io){
        this.io = io;
    }
    
    public int printMenuAndGetSelction(HashMap<String,Inventory> items){
        io.print("Welcome to your personal vending machine");
        io.print("The items available are:");
        displayItems(items);
        io.print("What would you like to do?");
        
        return io.readInt("1.Add Money\n2.Exit",1,2);        
    }
    
    public BigDecimal addMoneyMenuAndGetSelction(){
        io.print("Please enter how much money you would like to add:");
        BigDecimal dollarAmount = new BigDecimal("0.00");
        boolean hasErrors = false;
        do{
            String userInput = io.readString("Enter a integer amount ex. 1.50: ");
            try{
            Double.parseDouble(userInput);
            dollarAmount = new BigDecimal(userInput).setScale( 2,RoundingMode.HALF_UP);
            hasErrors = false;
            }
            catch(NumberFormatException e) {
                hasErrors = true; 
                io.print(userInput + " Is not a valid integer");
            }
            
        }while (hasErrors);
        System.out.println(dollarAmount);
        return dollarAmount;  
    }
    
    public String selectItemMenuAndGetSelection(HashMap<String,Inventory> items){
        io.print("Please select which item you would like to purchase:");
        ArrayList<String> validItems = displayItems(items);
        int userEntry = io.readInt("Enter number for the item.",1,validItems.size());
        
        String userChoice = null; 
        switch(userEntry){
            case 1:
                userChoice = validItems.get(0);
                break;
            case 2:
                userChoice = validItems.get(1);
                break;
            case 3:
                userChoice = validItems.get(2);
                break;
            case 4:
                userChoice = validItems.get(3);
                break;
            default: 
                displayUnknownCommandBanner();
        }
        System.out.println(userChoice);
        return userChoice;
    }
    
    public ArrayList<String> displayItems(HashMap<String,Inventory> items){
        ArrayList<String> validItems = new ArrayList<String>(); 

        items.forEach((itemName,itemValue) -> {
            if(itemValue.getInventoryCount() > 0 ){
                validItems.add(itemName);
                io.print((validItems.size()) + ": " + itemName + " $" +itemValue.getItemCost()); 
            }
                });
        return validItems; 
    }
    
    public void displayChange(Change newChange){
        io.print("Your change due is:");
        io.print("\t"+ newChange.getQuarters() + ":Quarters " + newChange.getDimes() + ":Dimes " + newChange.getNickles() + ":Nickles " + newChange.getPennies() + ":Pennies" );   
    }
    
    public void displayThankYouBanner (){
        io.print("Thank you for your purchase!");
    }
    
    public void displayUnknownCommandBanner(){
        io.print("Unknown Command!!!");
    }
    
    public void displayNotEnoughFundsBanner(){
        io.print("Not enough Funds! Please select a different item.");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
