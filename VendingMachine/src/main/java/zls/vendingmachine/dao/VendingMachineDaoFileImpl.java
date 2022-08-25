/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.vendingmachine.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import zls.vendingmachine.dto.Inventory;
import zls.vendingmachine.ui.ItemsEnum;

/**
 *
 * @author zshug
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    private  final String INVENTORY_FILE = "inventory.txt";
    public static final String DELIMITER = "::";
    private HashMap<String, Inventory> inventoryItems = new HashMap<>();
    
    
 
    private Inventory unmarshallVendingMachineItems(String VendingMachineItemsAsText){
        String[] inventoryTokens = VendingMachineItemsAsText.split(DELIMITER);
        String inventoryItem = inventoryTokens[0];
        Inventory inventoryFromFile = new Inventory(inventoryItem);
        inventoryFromFile.setItemName(inventoryTokens[1]);
        inventoryFromFile.setInventoryCount(Integer.parseInt(inventoryTokens[2]));
        inventoryFromFile.setItemCost(new BigDecimal(inventoryTokens[3]));
        return inventoryFromFile;
    }
    
    private void loadInventory()throws VendingMachinePersistenceException{
        Scanner scanner = null;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load inventory data into memory.", e );
                  
        }
        String currentLine;
        Inventory currentVendingMachineItems;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentVendingMachineItems = unmarshallVendingMachineItems(currentLine);
            inventoryItems.put(currentVendingMachineItems.getItemName(), currentVendingMachineItems);
        }

        scanner.close();
    }
    
    private String marshallVendingMachineItems(Inventory aVendingMachineItems){

        String inventoryAsText = aVendingMachineItems.getItemName() + DELIMITER;
        inventoryAsText += aVendingMachineItems.getItemName() + DELIMITER;
        inventoryAsText += aVendingMachineItems.getInventoryCount() + DELIMITER;
        inventoryAsText += aVendingMachineItems.getItemCost();
        return inventoryAsText;
    }
    
    private void writeInventory()throws VendingMachinePersistenceException{

        PrintWriter out = null;

        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
           throw new  VendingMachinePersistenceException(
                   "Could not save inventory data.", e);
        }

        String inventoryItemsAsText;
        List<Inventory> inventoryItemsList = this.getAllInventory();
        for (Inventory currentVendingMachineItems : inventoryItemsList) {
            inventoryItemsAsText = marshallVendingMachineItems(currentVendingMachineItems);            
            out.println(inventoryItemsAsText);
            out.flush();
        }
        out.close();
    }
    
    @Override
    public HashMap<String,Inventory> getItemInventory()throws VendingMachinePersistenceException{
        loadInventory();
        return inventoryItems; 
    }
    
    @Override
    public void createInventory()throws VendingMachinePersistenceException{
        Inventory itemOne = new Inventory("Snickers",0, new BigDecimal("1.50"));
        Inventory itemTwo = new Inventory("Doritos", 3, new BigDecimal("0.75"));
        Inventory itemThree = new Inventory("KitKat", 5, new BigDecimal("0.50"));
        Inventory itemFour = new Inventory("Muffin",2, new BigDecimal("1.85"));
        
        inventoryItems.put(itemOne.getItemName(), itemOne);
        inventoryItems.put(itemTwo.getItemName(), itemTwo);
        inventoryItems.put(itemThree.getItemName(), itemThree);
        inventoryItems.put(itemFour.getItemName(), itemFour);
        writeInventory();
    }
    
    @Override
    public void loadStartInventory()throws VendingMachinePersistenceException{
        loadInventory();
    }
    
    
    @Override
    public List<Inventory> getAllInventory()throws VendingMachinePersistenceException{
        loadInventory();
        return new ArrayList<Inventory>(inventoryItems.values());
    }
    
    @Override
    public boolean checkFunds(String userSelection, BigDecimal money) throws VendingMachinePersistenceException{
        loadInventory();
        boolean hasFunds;
        
        Inventory inventoryObject = inventoryItems.get(userSelection);
        if((inventoryObject.getItemCost().compareTo(money)) == 0 || (inventoryObject.getItemCost().compareTo(money)) == -1 ){
            hasFunds = true;
        } else {
            hasFunds = false;
        }
        return hasFunds; 
    }


    @Override
    public BigDecimal makePurchase(String userSelection)throws VendingMachinePersistenceException {
        loadInventory();
        Inventory inventoryObject = inventoryItems.get(userSelection);
        int currentInventory = inventoryObject.getInventoryCount();
        inventoryObject.setInventoryCount(currentInventory - 1);
        writeInventory();
        return inventoryObject.getItemCost();
    }
    
    
    
}
