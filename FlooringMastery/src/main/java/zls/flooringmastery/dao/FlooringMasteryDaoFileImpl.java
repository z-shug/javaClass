/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.flooringmastery.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import zls.flooringmastery.dto.Order;

/**
 *
 * @author zshug
 */
public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao{
    
    private String ORDER_FILE;
    private HashMap<Integer, Order> orders = new HashMap<>();
    public final String DELIMITER = ",";
    
    private Order unmarshallOrder(String orderAsText){
        String[] orderTokens = orderAsText.split(DELIMITER);
        int orderNumber = Integer.parseInt(orderTokens[0]);
        Order orderFromFile = new Order(orderNumber);
        orderFromFile.setCustomerName(orderTokens[1]) ;
        orderFromFile.setState(orderTokens[2]);
        orderFromFile.setTaxRate( new BigDecimal(orderTokens[3]).setScale(2,RoundingMode.HALF_UP));
        orderFromFile.setProductType(orderTokens[4]);
        orderFromFile.setArea(new BigDecimal(orderTokens[5]).setScale(2,RoundingMode.HALF_UP));
        orderFromFile.setCostSqFt(new BigDecimal(orderTokens[6]).setScale(2,RoundingMode.HALF_UP));
        orderFromFile.setLaborPerSqFt(new BigDecimal(orderTokens[7]).setScale(2,RoundingMode.HALF_UP));
        orderFromFile.setMaterialCost(new BigDecimal(orderTokens[8]).setScale(2,RoundingMode.HALF_UP));
        orderFromFile.setLaborCost(new BigDecimal(orderTokens[9]).setScale(2,RoundingMode.HALF_UP));
        orderFromFile.setTax(new BigDecimal(orderTokens[10]).setScale(2,RoundingMode.HALF_UP));
        orderFromFile.setTotal(new BigDecimal(orderTokens[11]).setScale(2,RoundingMode.HALF_UP));
        return orderFromFile;
    }
    
    private void loadOrder(String orderFile) throws FilePersistenceException {
        Scanner scanner = null;
        ORDER_FILE = orderFile;
        boolean fileFound = false;
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ORDER_FILE)));
            fileFound = true;
        } catch (FileNotFoundException e) {
            orders.clear();
        }
        if(fileFound){
            String currentLine;
            Order currentOrder; 
            boolean readLine = false;
            while (scanner.hasNextLine()) {
                if (readLine){
                    currentLine = scanner.nextLine();
                    currentOrder = unmarshallOrder(currentLine);
                    orders.put(currentOrder.getOrderNumber(), currentOrder);
                }else{
                    currentLine = scanner.nextLine();
                    readLine = true;
                } 
            }
            scanner.close();
        }       
    }
    
    private String marshallOrder(Order aOrder){

        String studentAsText = Integer.toString(aOrder.getOrderNumber())  + DELIMITER;
        studentAsText += aOrder.getCustomerName() + DELIMITER;
        studentAsText += aOrder.getState() + DELIMITER;
        studentAsText += aOrder.getTaxRate().toString() + DELIMITER;
        studentAsText += aOrder.getProductType() + DELIMITER;
        studentAsText += aOrder.getArea().toString() + DELIMITER;
        studentAsText += aOrder.getCostSqFt().toString() + DELIMITER;
        studentAsText += aOrder.getlaborPerSqFt().toString() + DELIMITER;
        studentAsText += aOrder.getMaterialCost().toString() + DELIMITER;
        studentAsText += aOrder.getLaborCost().toString() + DELIMITER;
        studentAsText += aOrder.getTax().toString() + DELIMITER;
        studentAsText += aOrder.getTotal().toString();
        return studentAsText;
    }
    
    private void writeOrder(String orderFile) throws FilePersistenceException {

        ORDER_FILE = orderFile;
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ORDER_FILE));
        } catch (IOException e) {
            throw new FilePersistenceException(
                    "Could not save order data.", e);
        }
        out.println("OrderNumber" + DELIMITER + "CustomerName" + DELIMITER
                + "State" + DELIMITER + "TaxRate" + DELIMITER + "ProductType" + DELIMITER
                + "Area" + DELIMITER + "CostPerSquareFoot" + DELIMITER
                + "LaborCostPerSquareFoot" + DELIMITER + "MaterialCost" + DELIMITER
                + "LaborCost" + DELIMITER + "Tax" + DELIMITER + "Total");

        String orderAsText;
        List<Order> orderList = this.getAllOrders(ORDER_FILE);
        for (Order currentOrder : orderList) {
            orderAsText = marshallOrder(currentOrder);            
            out.println(orderAsText);
            out.flush();
        }
        out.close();
    }
    
    
    @Override
    public void addOrder(int orderNumber, Order order, String orderDateFile)throws FilePersistenceException {
       
            loadOrder(orderDateFile);
            Order newOrder = orders.put(orderNumber, order);
            writeOrder(orderDateFile);
    }
    
    @Override
    public List<Order> getAllOrders(String dateFileFormat) throws FilePersistenceException{
        loadOrder(dateFileFormat);
        ArrayList<Order> orderList=  new ArrayList<Order>(orders.values());
        return orderList;
    }

    @Override
    public Order getOrder(String date, int orderNumber)throws FilePersistenceException {
        loadOrder(date);
        return orders.get(orderNumber);
             
    }

    @Override
    public void editOrder(String date, int orderNumber, Order order)throws FilePersistenceException {
        loadOrder(date);
        orders.replace(orderNumber, order);
        writeOrder(date);
    }

    @Override
    public void removeOrder(String date, int orderNumber)throws FilePersistenceException{
        loadOrder(date);
        orders.remove(orderNumber);
        writeOrder(date);
        
    }

    
}
