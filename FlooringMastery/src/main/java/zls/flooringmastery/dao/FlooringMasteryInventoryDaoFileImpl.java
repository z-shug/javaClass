/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.flooringmastery.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import zls.flooringmastery.dto.Inventory;
import zls.flooringmastery.dto.Tax;

/**
 *
 * @author zshug
 */
public class FlooringMasteryInventoryDaoFileImpl implements FlooringMasteryInventoryDao {

    private final String INVENTORY_FILE = "Products.txt";
    private HashMap<String, Inventory> products = new HashMap<>();
    public final String DELIMITER = ",";
    
    private Inventory unmarshallInventory(String inventoryAsText){
        String[] inventoryTokens = inventoryAsText.split(DELIMITER);
        String productType = inventoryTokens[0];
        Inventory inventoryFromFile = new Inventory(productType);
        inventoryFromFile.setCostPerSqFt(new BigDecimal(inventoryTokens[1]).setScale(2,RoundingMode.HALF_UP));
        inventoryFromFile.setLaborPerSqFt(new BigDecimal(inventoryTokens[2]).setScale(2,RoundingMode.HALF_UP));
        
        return inventoryFromFile;
    }
    
    private void loadInventory() throws FilePersistenceException {
        Scanner scanner;
        String inventoryFile = INVENTORY_FILE;
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(inventoryFile)));
        } catch (FileNotFoundException e) {
            throw new FilePersistenceException(
                    "-_- Could not load tax file data into memory.", e);
        }
        String currentLine;
        Inventory currentInventory;
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentInventory = unmarshallInventory(currentLine);
            products.put(currentInventory.getProductType(), currentInventory);
        }

        scanner.close();
    }
    
    @Override
    public HashMap<String,Inventory> getAllInventory() throws FilePersistenceException{
        loadInventory();
        return products;
    
    }
    

    @Override
    public Inventory getInventoryItem(String productType) throws FilePersistenceException {
        loadInventory();
        return products.get(productType);
    }
    
    
    
    
}
