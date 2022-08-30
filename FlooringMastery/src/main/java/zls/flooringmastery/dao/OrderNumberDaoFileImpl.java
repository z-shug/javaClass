/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.flooringmastery.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 *
 * @author zshug
 */
public class OrderNumberDaoFileImpl implements OrderNumberDao {
    
    public static final String ORDER_NUMBER_FILE = "orderNumber.txt";
    
    
    private void writeOrderNumberEntry(int orderNumber) throws FilePersistenceException {
        PrintWriter out;
       
        try {
            out = new PrintWriter(new FileWriter(ORDER_NUMBER_FILE, false));
        } catch (IOException e) {
            throw new FilePersistenceException("Could not persist audit information.", e);
        }
 

        out.println(orderNumber);
        out.flush(); 
    }

    
    private int loadOrderNumber() throws FilePersistenceException {
        Scanner scanner;
        String currentTextOrderNumber;
        int currentOrderNumber = 0;
        
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ORDER_NUMBER_FILE)));
        } catch (FileNotFoundException e) {
            throw new FilePersistenceException(
                    "-_- Could not load roster data into memory.", e);
        }
        
        

        while (scanner.hasNextLine()) {
            currentTextOrderNumber = scanner.nextLine();
            currentOrderNumber = Integer.parseInt(currentTextOrderNumber);
            
        }

        scanner.close();
        return currentOrderNumber;
    }

    @Override
    public int getOrderNumber() throws FilePersistenceException{
        return loadOrderNumber();
    }
    
    @Override
    public void setOrderNumber(int orderNumber) throws FilePersistenceException{
        loadOrderNumber();
        writeOrderNumberEntry(orderNumber);
    }
        
}
