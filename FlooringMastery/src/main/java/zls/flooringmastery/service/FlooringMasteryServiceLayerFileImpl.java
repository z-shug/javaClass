/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.flooringmastery.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import zls.flooringmastery.dao.FilePersistenceException;
import zls.flooringmastery.dao.FlooringMasteryDao;
import zls.flooringmastery.dao.FlooringMasteryInventoryDao;
import zls.flooringmastery.dao.FlooringMasteryTaxDao;
import zls.flooringmastery.dao.OrderNumberDao;
import zls.flooringmastery.dto.Inventory;
import zls.flooringmastery.dto.Order;
import zls.flooringmastery.dto.Tax;

/**
 *
 * @author zshug
 */
public class FlooringMasteryServiceLayerFileImpl implements FlooringMasteryServiceLayer {
    FlooringMasteryDao dao;
    FlooringMasteryInventoryDao inventoryDao;
    FlooringMasteryTaxDao taxDao;
    OrderNumberDao orderNumberDao;
    
    public FlooringMasteryServiceLayerFileImpl (FlooringMasteryDao dao, FlooringMasteryInventoryDao inventoryDao,FlooringMasteryTaxDao taxDao, OrderNumberDao orderNumberDao){
        this.dao = dao;
        this.inventoryDao = inventoryDao;
        this.taxDao = taxDao;
        this.orderNumberDao = orderNumberDao;
    }
    @Override
    public List<Order> displayOrders(String date) throws FilePersistenceException{
        String dateFileFormat = formatDateFile(date);
        return dao.getAllOrders(dateFileFormat);
    }
    
    @Override
    public String formatDateFile(String date){
        LocalDate ld = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'Orders_'MMddyyyy'.txt'");
        
        String formattedDateFile = ld.format(formatter);
        return formattedDateFile;
    }
    
    @Override
    public HashMap<String,Tax> getAllTaxes() throws FilePersistenceException{
        return taxDao.getAllTax();
    }
    
    @Override
    public HashMap<String,Inventory> getAllInventory() throws FilePersistenceException{
        return inventoryDao.getAllInventory();
    }
    
    @Override
    public Order getOrder(String date, int orderNumber) throws FilePersistenceException {
       String formatedDate = formatDateFile(date);
        return dao.getOrder(formatedDate, orderNumber);
    }
    
    @Override
    public void removeOrder(String date, int orderNumber) throws FilePersistenceException{
        String formatedDate = formatDateFile(date);
        dao.removeOrder(formatedDate, orderNumber);
    }
    
    @Override
    public void calculateMaterialCost(Order newOrder)throws FilePersistenceException{
        BigDecimal area = newOrder.getArea();
        String product = newOrder.getProductType();
        Inventory productObj = inventoryDao.getInventoryItem(product);
        BigDecimal costPerSqFt = productObj.getCostPerSqFt();
        BigDecimal materialCost = area.multiply(costPerSqFt).setScale( 2,RoundingMode.HALF_UP);
        newOrder.setCostSqFt(costPerSqFt);
        newOrder.setMaterialCost(materialCost);   
    }
    
    @Override
    public void calculateLaborCost(Order newOrder)throws FilePersistenceException{
        BigDecimal area = newOrder.getArea();
        String product = newOrder.getProductType();
        Inventory productObj = inventoryDao.getInventoryItem(product);
        BigDecimal laborPerSqFt = productObj.getLaborPerSqFt();
        BigDecimal laborCost = area.multiply(laborPerSqFt).setScale( 2,RoundingMode.HALF_UP);
        newOrder.setLaborPerSqFt(laborPerSqFt);
        newOrder.setLaborCost(laborCost);
    } 
      
    @Override
    public void calculateTax(Order newOrder) throws FilePersistenceException{
        BigDecimal materialCost = newOrder.getMaterialCost();
        BigDecimal laborCost = newOrder.getLaborCost();
        String stateAbb = newOrder.getState();
        Tax taxObj = taxDao.getStateTax(stateAbb);
        BigDecimal taxRate = taxObj.getTaxRate();
        BigDecimal hundred = new BigDecimal(100);
        BigDecimal tax = (materialCost.add(laborCost).multiply(taxRate.divide(hundred))).setScale( 2,RoundingMode.HALF_UP);
        newOrder.setTaxRate(taxRate);
        newOrder.setTax(tax);
    }
    
    @Override
    public void calculateTotal(Order newOrder) {
        BigDecimal materialCost = newOrder.getMaterialCost();
        BigDecimal laborCost = newOrder.getLaborCost();
        BigDecimal tax = newOrder.getTax();
        BigDecimal total = materialCost.add(laborCost.add(tax)).setScale( 2,RoundingMode.HALF_UP);
        newOrder.setTotal(total);
        
    }
    
    @Override
    public void setOrderNumber(Order newOrder)throws FilePersistenceException{
        int currentOrderNumber = orderNumberDao.getOrderNumber();
        System.out.println(currentOrderNumber);
        int newOrderNumber = currentOrderNumber +1;
        System.out.println(newOrderNumber);
        orderNumberDao.setOrderNumber(newOrderNumber);
        newOrder.setOrderNumber(newOrderNumber);
    }
    
    @Override
    public void createNewOrder(Order newOrder) throws FilePersistenceException{
        int orderNumber = newOrder.getOrderNumber();
        String orderDate = newOrder.getOrderDate();
        String orderDateFile = formatDateFile(orderDate);
        dao.addOrder(orderNumber, newOrder, orderDateFile);
    }
    
    @Override
    public void editOrder(String orderDate,Order editedOrder) throws FilePersistenceException{
        int orderNumber = editedOrder.getOrderNumber();
        String date = orderDate;
        String orderDateFile = formatDateFile(date);
        dao.editOrder(orderDateFile, orderNumber, editedOrder);
    }
}
