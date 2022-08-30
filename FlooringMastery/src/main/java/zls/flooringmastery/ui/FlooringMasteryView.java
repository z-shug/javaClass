/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.flooringmastery.ui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import zls.flooringmastery.dto.Inventory;
import zls.flooringmastery.dto.Order;
import zls.flooringmastery.dto.Tax;

/**
 *
 * @author zshug
 */
public class FlooringMasteryView {
    private UserIO io;
    
    public FlooringMasteryView(UserIO io){
        this.io = io;
    }
    
    public void startMessage(){
        io.print("Welcome to the Flooring Mastery Ordering System!");  
    }
    
    public int mainMenu(){
        io.print("-----------Main Menu-----------");
        int userChoice = io.readInt("Please select the action you wish to perform: \n\t1.Dispay Orders \n\t2.Add an Order \n\t3.Edit an Order \n\t4.Remove an Order\n\t5.Quit", 1,5);
        return userChoice;        
    }
    
    public String displayOrdersInput()throws InvalidDateException{
        return io.readDate("Please enter the date you would like to view the orders of in format MM/dd/yyyy:");
    }
    
    public void displayReturnedOrderList(List<Order> orders){
        
        if(!orders.isEmpty()){
            orders.forEach(order ->{
            io.print(order.getCustomerName() + " " + order.getState() + " " + order.getTaxRate() + " " +
                    order.getProductType() + " " + order.getArea() + " " + order.getCostSqFt() + " " + order.getlaborPerSqFt() +
                    " " + order.getMaterialCost() + " " + order.getLaborCost() + " " + order.getTax() + " " + order.getTotal()); 
                            });
        }else{
            io.print("No orders for given date. Returning to main menu. ");
        } 
    }
    
    public int displayCreatedOrder(Order order){
       
        io.print("You order is:");
        io.print("\tCustomer Name:" +order.getCustomerName() + " State:" + order.getState() + " Tax Rate:" + order.getTaxRate() + " Product:" +
                    order.getProductType() + " Area:" + order.getArea() + " CostPerSqFt:" + order.getCostSqFt() + " Cost of LaborPerSqFt:" + order.getlaborPerSqFt() +
                    " Material Cost:" + order.getMaterialCost() + " Labor Cost:" + order.getLaborCost() + " Tax:" + order.getTax() + " Total:" + order.getTotal()); 
        io.print("Would you like to accept your order.");
        int userChoice = io.readInt("1.YES \n2.NO", 1, 2);
        return userChoice;
    }
    
    public Order addOrderUserInput(HashMap<String,Tax> stateTax, HashMap<String,Inventory> inventory){
        io.print("Please enter the values for your order.");
        String userDate = io.readFutureDate("\tEnter a future date for your order:");
        String customerName = readCustomerName("\tEnter a user name. Valid characters are [a-z][0-9][.,]:");
        String stateAbb = readOrderStateTax(stateTax);
        String productType = readProductType(inventory);
        BigDecimal area = io.readBigDecimal("\tEnter an area amount. Must be atleast 100 sq ft.");
        Order newOrder = new Order();
        newOrder.setOrderDate(userDate);
        newOrder.setCustomerName(customerName);
        newOrder.setProductType(productType);
        newOrder.setState(stateAbb);
        newOrder.setArea(area);
        return newOrder;
    }
    
    public Order editOrder(Order order,HashMap<String,Tax> stateTax,HashMap<String,Inventory> products){
        
        if(order != null){
            String editedCustomerName = readEditCustomerName("Enter customer name or hit enter to keep previous entry ("+ order.getCustomerName()+ ") :");
            if(editedCustomerName != null){
                order.setCustomerName(editedCustomerName);
            }
            String editedStateTax = readEditOrderStateTax(stateTax, order);
            if(editedStateTax != null){
                order.setState(editedStateTax);
            }
            String editedProductType = readEditProductType( products, order);
            if(editedProductType != null){
                order.setProductType(editedProductType);
            }
            BigDecimal editedArea = readEditArea("Enter a new area total or hit enter to keep previous entry ("+order.getArea()+ ") :"); 
            if(editedArea != null){
                order.setArea(editedArea);
            }
            
        }else{
            io.print("The specified order number and date does not exist.");
        }
        return order;
    }
    
    public int displayEditedOrder(Order order){
        io.print("You new order is:");
        io.print("\tCustomer Name:" +order.getCustomerName() + " State:" + order.getState() + " Tax Rate:" + order.getTaxRate() + " Product:" +
                    order.getProductType() + " Area:" + order.getArea() + " CostPerSqFt:" + order.getCostSqFt() + " Cost of LaborPerSqFt:" + order.getlaborPerSqFt() +
                    " Material Cost:" + order.getMaterialCost() + " Labor Cost:" + order.getLaborCost() + " Tax:" + order.getTax() + " Total:" + order.getTotal()); 
        io.print("Would you like to accept your new order.");
        int userChoice = io.readInt("1.YES \n2.NO", 1, 2);
        return userChoice;
    }
    
    public int removeOrder(Order order){
        int userChoice = 2;
        if(order != null){
            io.print("You order is:");
        io.print("\tCustomer Name:" +order.getCustomerName() + " State:" + order.getState() + " Tax Rate:" + order.getTaxRate() + " Product:" +
                    order.getProductType() + " Area:" + order.getArea() + " CostPerSqFt:" + order.getCostSqFt() + " Cost of LaborPerSqFt:" + order.getlaborPerSqFt() +
                    " Material Cost:" + order.getMaterialCost() + " Labor Cost:" + order.getLaborCost() + " Tax:" + order.getTax() + " Total:" + order.getTotal()); 
        io.print("Would you like to delete your order.");
        userChoice = io.readInt("1.YES \n2.NO", 1, 2);
        }else{
            io.print("The specified order number and date does not exist.");
        }
        return userChoice;
    }
    
    public String readOrderStateTax(HashMap<String,Tax> stateTax){
        ArrayList<String> validStates = new ArrayList<>();
        boolean isValid = false;
        String userChoice = null;
        
        while (isValid == false){
            io.print("\tChoose a state tax rate. Options are:");
            stateTax.forEach((stateAbb,tax) -> {
                io.print(stateAbb);
                validStates.add(stateAbb);
                    });
            String userInput = io.readString("\tEnter the state abbriviation you would like:");
            if(validStates.contains(userInput)){
                 userChoice = userInput; 
                 isValid = true;
            }else{
                 io.print("Invalid Entry.");
                 isValid = false;
            }
        }
        return userChoice; 
    }
    
    public String readEditOrderStateTax(HashMap<String,Tax> stateTax, Order order){
        ArrayList<String> validStates = new ArrayList<>();
        boolean isValid = false;
        String userChoice = null;
        
        while (isValid == false){
            io.print("\tChoose a state tax rate. Options are:");
            stateTax.forEach((stateAbb,tax) -> {
                io.print(stateAbb);
                validStates.add(stateAbb);
                    });
            String userInput = io.readString("\tEnter the state abbriviation you would like to change or hit enter to keep previous value ("+ order.getState()+ ") :");
            if(validStates.contains(userInput)|| userInput.isEmpty()){
                 userChoice = userInput; 
                 isValid = true;
            }else{
                 io.print("Invalid Entry.");
                 isValid = false;
            }
        }
        return userChoice; 
    }
  
    public String readCustomerName(String message){
        boolean isValid = false;
        String validUserName = null;
        Pattern regex;
        regex = Pattern.compile("^[a-zA-Z0-9\\.,]([.,](?![.,])|[a-zA-Z0-9]){1,100}[a-zA-Z0-9\\.,]$");
        while(isValid == false){
            String userInput = io.readString(message);
            if (regex.matcher(userInput).matches() == true && !userInput.trim().isEmpty()){
                isValid = true;
                validUserName = userInput;
            }else{
                isValid = false;
                io.print("Invalid Entry. ");
            }
        }
        return validUserName;
    }
    
    public String readEditCustomerName(String message){
        boolean isValid = false;
        String validUserName = null;
        Pattern regex;
        regex = Pattern.compile("^[a-zA-Z0-9\\.,]([.,](?![.,])|[a-zA-Z0-9]){1,100}[a-zA-Z0-9\\.,]$");
        while(isValid == false){
            String userInput = io.readString(message);
            if (regex.matcher(userInput).matches() == true && userInput.trim().length() != 0 || userInput.isEmpty()){
                isValid = true;
                validUserName = userInput;
            }else{
                isValid = false;
                io.print("Invalid Entry. ");
            }
        }
        return validUserName;
    }
    
    public String readProductType(HashMap<String,Inventory> inventory){
        ArrayList<String> validItems = new ArrayList<>();
        boolean isValid = false;
        String userChoice = null;
        
        while (isValid == false){
            io.print("\tChoose a product you would like to buy. Options are:");
            inventory.forEach((productName, products) -> {
                io.print(productName + " Cost Per Square Foot:$" + products.getCostPerSqFt() + " Labor Cost Per Square Foot:$"+ products.getLaborPerSqFt());
                validItems.add(productName);
                    });
            String userInput = io.readString("\tEnter the product name you would like add:");
            if(validItems.contains(userInput)){
                 userChoice = userInput; 
                 isValid = true;
            }else{
                 io.print("Invalid Entry.");
                 isValid = false;
            }
        }
        return userChoice; 
    }
    
    public String readEditProductType(HashMap<String,Inventory> inventory, Order order){
        ArrayList<String> validItems = new ArrayList<>();
        boolean isValid = false;
        String userChoice = null;
        
        while (isValid == false){
            io.print("\tChoose the product you would like to switch to. Options are:");
            inventory.forEach((productName, products) -> {
                io.print(productName + " Cost Per Square Foot:$" + products.getCostPerSqFt() + " Labor Cost Per Square Foot:$"+ products.getLaborPerSqFt());
                validItems.add(productName);
                    });
            String userInput = io.readString("\tEnter the product name you would like change or hit enter to keep previous entry (" +order.getProductType()+") :");
            if(validItems.contains(userInput) || userInput.isEmpty()){
                 userChoice = userInput; 
                 isValid = true;
            }else{
                 io.print("Invalid Entry.");
                 isValid = false;
            }
        }
        return userChoice; 
    }
    
    public BigDecimal readEditArea(String prompt){
        BigDecimal result = null;
        boolean hasErrors = false;
        do{
            String userInput = io.readString(prompt);
            try{
                if ( userInput.isEmpty()){
                        hasErrors = false;
                    }else{
                    if(Double.parseDouble(userInput) > 0 && Double.parseDouble(userInput)>= 100){
                        result = new BigDecimal(userInput).setScale( 2,RoundingMode.HALF_UP);
                        hasErrors = false;   
                        }else{
                            System.out.println("Please enter a positive integer that is atleast 100");
                            hasErrors = true;
                        }
                    }
            }
            catch(NumberFormatException e) {
                hasErrors = true; 
                System.out.println(userInput + " Is not a valid integer"); 
            }   
        }while (hasErrors);
        //System.out.println(result);
        return result;   
    } 
    
    public String readOrderDate() {
       return io.readDate("Please enter the order date:");
    }
    
    public int readOrderNumber() {
        return io.readInt("Please enter the order number:");
    }
    
    public void displayRemovedOrderBanner(){
        io.print("Your order has been deleted");
    }
    
    public void displayUnknownCommandBanner(){
        io.print("Unknown Command!!! Please Select From The Menu Choices");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
